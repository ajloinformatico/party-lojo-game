package com.example.party_lojo_game.ui.fragments.editquestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.AsksBO
import com.example.party_lojo_game.databinding.FragmentEditQuestionBottomSheetBinding
import com.example.party_lojo_game.ui.vo.EditQuestionBottomSheetState
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.messages.InfoLojoSnackBarMaker
import com.example.party_lojo_game.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditQuestionBottomSheetFragment : Fragment() {

    private val viewModel: EditQuestionBottomSheetViewModel by viewModels()
    private var binding: FragmentEditQuestionBottomSheetBinding? = null
    private val args: EditQuestionBottomSheetFragmentArgs by navArgs()
    private var ask: AsksBO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ask = args.ask
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEditQuestionBottomSheetBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state)  {
                is EditQuestionBottomSheetState.Loading -> showLoading()
                is EditQuestionBottomSheetState.Error -> showError()
                is EditQuestionBottomSheetState.Render -> render()
                is EditQuestionBottomSheetState.Updated -> updated(state.success)
            }
        }
        viewModel.init(ask)
    }

    private fun showLoading() {
        binding?.apply {
            content.gone()
            errorScreen.root.gone()
            loading.root.show()
        }
    }

    private fun render() {
        viewModel.question.observe(viewLifecycleOwner) { ask ->
            binding?.apply {
                loading.root.gone()
                errorScreen.root.gone()
                content.show()
                typeLabel.text = ask.type.value
                editText.setText(ask.text)
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

    /** Go back and reload questions grid with new question info */
    private fun updated(success: Boolean) {
        if (!success) {
            InfoLojoSnackBarMaker.showError(
                requireContext(),
                requireView(),
                resources.getString(R.string.edit_your_question_fail)
            )
            return
        }
        InfoLojoSnackBarMaker.showSuccess(
            requireContext(),
            requireView(),
            resources.getString(R.string.edit_your_question_success)
        )
        activity?.onBackPressed()
    }
}
