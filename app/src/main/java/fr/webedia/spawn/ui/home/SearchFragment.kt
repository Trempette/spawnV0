package fr.webedia.spawn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fr.webedia.spawn.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSearchBinding.inflate(inflater).apply {
            lifecycleOwner = this@SearchFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: SearchFragmentVM by viewModels { SearchFragmentVMFactory(requireActivity().application) }
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {

        fun instance() = SearchFragment()

    }

}