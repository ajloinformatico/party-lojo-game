package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.FragmentEditQuestionBinding
import com.example.party_lojo_game.ui.viewmodel.EditQuestionViewModel
import com.example.party_lojo_game.ui.vo.EditAskState
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.messages.InfoLojoToastMaker
import com.example.party_lojo_game.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditQuestionFragment : Fragment() {

    private val viewModel: EditQuestionViewModel by viewModels()
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
        binding?.title?.setOnClickListener {
            InfoLojoToastMaker.createSimpleToast(
                requireContext(),
                resources.getString(R.string.edit_your_questions)
            )
        }
        binding?.errorScreen?.goBackBtn?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EditAskState.Loading -> showLoading()
                is EditAskState.Render -> render()
                is EditAskState.Error -> showError()
            }
        }
    }

    private fun showLoading() {
        binding?.apply {
            content.gone()
            errorScreen.root.gone()
            loading.root.show()
        }
    }

    private fun render() {
        binding?.apply {
            loading.root.gone()
            errorScreen.root.gone()
            content.show()
        }
    }

    private fun showError() {
        binding?.apply {
            loading.root.gone()
            content.gone()
            errorScreen.root.show()
        }
    }
}
