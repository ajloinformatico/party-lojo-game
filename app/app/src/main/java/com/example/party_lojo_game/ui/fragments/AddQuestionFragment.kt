package com.example.party_lojo_game.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.party_lojo_game.R
import com.example.party_lojo_game.databinding.FragmentAddQuestionBinding
import com.example.party_lojo_game.ui.viewmodel.AddQuestionViewModel
import com.example.party_lojo_game.ui.vo.AddNewAsKErrorsType
import com.example.party_lojo_game.ui.vo.AddNewAskState
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.messages.InfoLojoSnackBarMaker
import com.example.party_lojo_game.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddQuestionFragment : Fragment() {

    private val viewModel: AddQuestionViewModel by viewModels()
    private var binding: FragmentAddQuestionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddQuestionBinding.inflate(inflater, container, false)
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
            initTypeSpinner()
            errorScreen.goBackBtn.setOnClickListener {
                requireActivity().onBackPressed()
            }
            saveBtn.setOnClickListener {
                viewModel.saveBtnClick(
                    content = editText.text.toString(),
                    type = typeMultiChoiceSpinner.selectedItem.toString(),
                    resources = resources
                )
            }
        }
    }

    /** Prepare spinner by setting adapter */
    private fun initTypeSpinner() {
        val typeMultiChoiceSpinnerAdapter: ArrayAdapter<CharSequence> =
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.type_question_array,
                R.layout.spinner_typed_array_item
            ).apply {
                setDropDownViewResource(R.layout.spinner_typed_array_drop_down_item)
            }
        binding?.typeMultiChoiceSpinner?.adapter = typeMultiChoiceSpinnerAdapter
    }

    private fun initViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AddNewAskState.Loading -> showLoading()
                is AddNewAskState.Render -> render()
                is AddNewAskState.Error -> manageError(state.type)
                is AddNewAskState.AddedToDatabase -> {
                    binding?.root?.let { view ->
                        InfoLojoSnackBarMaker.showSuccess(
                            context = requireContext(),
                            view = view,
                            text = resources.getString(R.string.added_to_database_sucsses)
                        )
                    }
                }
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

    private fun manageError(errorType: AddNewAsKErrorsType) {
        when (errorType) {
            AddNewAsKErrorsType.UNKNOWN -> loadErrorScreen()
            else -> {
                binding?.root?.let { view ->
                    InfoLojoSnackBarMaker.showError(
                        view = view,
                        context = requireContext(),
                        text = when (errorType) {
                            AddNewAsKErrorsType.CONTENT_ERROR -> {
                                resources.getString(R.string.content_error)
                            }
                            AddNewAsKErrorsType.CONTENT_ALREADY_SAVED -> {
                                errorType.value +
                                        " " +
                                        resources.getString(R.string.content_already_saved)
                            }
                            AddNewAsKErrorsType.TYPE_ERROR -> {
                                resources.getString(R.string.type_error)
                            }
                            AddNewAsKErrorsType.CONTENT_ERROR_AND_TYPE_ERROR -> {
                                resources.getString(R.string.content_and_type_error)
                            }
                            else -> ""
                        }
                    )
                }
            }
        }
    }

    private fun loadErrorScreen() {
        binding?.apply {
            loading.root.gone()
            content.gone()
            errorScreen.root.show()
        }
    }
}
