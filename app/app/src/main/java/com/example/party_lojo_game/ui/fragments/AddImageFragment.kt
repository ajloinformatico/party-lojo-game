package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.FragmentAddImageBinding
import com.example.party_lojo_game.ui.viewmodel.AddImageViewModel
import com.example.party_lojo_game.ui.vo.AddYourOwnImagesState
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.messages.InfoLojoToastLength
import com.example.party_lojo_game.utils.messages.InfoLojoToastMaker
import com.example.party_lojo_game.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddImageFragment : Fragment() {

    private val viewModel: AddImageViewModel by viewModels()
    private var binding: FragmentAddImageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddImageBinding.inflate(layoutInflater, container, false)
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
                resources.getString(R.string.add_new_questions),
                InfoLojoToastLength.LENGTH_LONG
            )
        }
        binding?.errorScreen?.goBackBtn?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AddYourOwnImagesState.Loading -> showLoading()
                is AddYourOwnImagesState.Render -> render()
                is AddYourOwnImagesState.Error -> showError()
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
