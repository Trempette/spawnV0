package fr.webedia.spawn.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import fr.webedia.spawn.R
import fr.webedia.spawn.databinding.ActivityMainBinding
import fr.webedia.spawn.utils.FragmentUtils

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainActivityVM by viewModels { MainActivityVMFactory(application) }

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
            }

        viewModel.onNavigationClick.observe(this@MainActivity, Observer {
            onNavigationClick(it)
        })

        binding.bottomNavigation.inflateMenu(R.menu.menu)

    }

    fun onNavigationClick(itemId: Int) {
        when (itemId) {
            R.id.action_coming_soon -> {
                openComingSoon()
            }
            R.id.action_games -> {
                openGames()
            }
            R.id.action_search-> {
                openSearch()
            }
        }
    }

    private fun openComingSoon() {
        //openFragment("coming_soon", ComingSoonFragment.instance())
    }

    private fun openGames() {
        //openFragment("games", GamesFragment.instance())
    }

    private fun openSearch() {
        //openFragment("search", SearchFragment.instance())
    }

    private fun openFragment(type: String, fragment: Fragment) {
        FragmentUtils.addOrResurfaceFragment(supportFragmentManager, R.id.container, fragment, type)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            return intent
        }
    }

}