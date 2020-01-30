package fr.webedia.spawn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.webedia.spawn.databinding.FragmentComingSoonBinding

class ComingSoonFragment : Fragment() {

    lateinit var binding: FragmentComingSoonBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentComingSoonBinding.inflate(inflater).apply {
            lifecycleOwner = this@ComingSoonFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: ComingSoonFragmentVM by viewModels { ComingSoonFragmentVMFactory(requireActivity().application) }
        binding.viewModel = viewModel

        binding.showtimesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ComingSoonAdapter(viewModel.games, viewModel, requireContext(), this@ComingSoonFragment)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {

        fun instance() = ComingSoonFragment()

    }

}