package fr.webedia.spawn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fr.webedia.spawn.databinding.FragmentComingSoonBinding
import fr.webedia.spawn.databinding.FragmentGamesBinding

class GamesFragment : Fragment() {

    lateinit var binding: FragmentGamesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentGamesBinding.inflate(inflater).apply {
            lifecycleOwner = this@GamesFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: GamesFragmentVM by viewModels { GamesFragmentVMFactory(requireActivity().application) }
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {

        fun instance() = GamesFragment()

    }

}