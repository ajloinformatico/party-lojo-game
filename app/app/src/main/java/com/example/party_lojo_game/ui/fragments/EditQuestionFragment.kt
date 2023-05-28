package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.FragmentEditQuestionBinding
import com.example.party_lojo_game.ui.adapter.EditYourQuestionsEvents
import com.example.party_lojo_game.ui.adapter.EditYourQuestionsGridAdapter
import com.example.party_lojo_game.ui.viewmodel.EditQuestionViewModel
import com.example.party_lojo_game.ui.vo.EditAskState
import com.example.party_lojo_game.ui.vo.toBO
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.messages.InfoLojoSnackBarMaker
import com.example.party_lojo_game.utils.messages.InfoLojoToastMaker
import com.example.party_lojo_game.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditQuestionFragment : Fragment() {

    private val viewModel: EditQuestionViewModel by viewModels()
    private val adapter = EditYourQuestionsGridAdapter(::manageEditYourQuestionEvents)
    private var binding: FragmentEditQuestionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditQuestionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initViews() {
        binding?.apply {
            errorScreen.goBackBtn.setOnClickListener {
                requireActivity().onBackPressed()
            }
            questionsGrid.adapter = adapter
        }
    }

    private fun initViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EditAskState.Loading -> showLoading()
                is EditAskState.Render -> render()
                is EditAskState.Error -> showError()
                is EditAskState.RemoveQuestion -> showRemoveQuestion(state.removed)
                is EditAskState.Edit -> { /* TODO */ }
            }
        }
        viewModel.init()
    }

    private fun showLoading() {
        binding?.apply {
            content.gone()
            errorScreen.root.gone()
            loading.root.show()
        }
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModel.getAllYoNuncaAsks().observe(viewLifecycleOwner) { yoNunca ->
            viewModel.getAllBebeQuienAsks().observe(viewLifecycleOwner) { bebeQuien ->
                with(viewModel) {
                    updateYoNuncaBOAsks(yoNunca)
                    updateBebeQuienBOAsks(bebeQuien)
                    load(render = true)
                }
            }
        }
    }

    private fun render() {
        viewModel.questions.observe(viewLifecycleOwner) { asks ->
            adapter.submitList(asks)
            binding?.apply {
                loading.root.gone()
                errorScreen.root.gone()
                content.show()
            }
        }
    }

    private fun showError() {
        binding?.apply {
            loading.root.gone()
            content.gone()
            errorScreen.root.show()
        }
    }

    /** show feedback to user after remove a question */
    private fun showRemoveQuestion(removed: Boolean) {
        if (removed) {
            InfoLojoSnackBarMaker.showSuccess(
                context = requireContext(),
                view = requireView(),
                getString(R.string.removed_success)
            )
        }
    }

    private fun reload() {
        getAllQuestions()
        render()
    }

    private fun manageEditYourQuestionEvents(event: EditYourQuestionsEvents) {
        when (event) {
            is EditYourQuestionsEvents.Edit -> {
                InfoLojoToastMaker.createSimpleToast(requireContext(), "Edit question")
            }

            is EditYourQuestionsEvents.Remove -> {
                viewModel.remove(
                    questionId = event.id,
                    askTypeBO = event.askTypeVO.toBO()
                )
            }
        }
    }
}
