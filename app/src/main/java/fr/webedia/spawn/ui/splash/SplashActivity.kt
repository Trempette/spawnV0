package fr.webedia.spawn.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import fr.webedia.spawn.R
import fr.webedia.spawn.model.Game
import fr.webedia.spawn.ui.home.MainActivity
import fr.webedia.spawn.utils.InjectorUtils
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            lifecycleScope.launch {
                InjectorUtils.provideRepository(this@SplashActivity).insertListOfGameInDB(Game.getListOfGames())
            }
            launchMainActivity()
        }, 2000)
    }

    private fun launchMainActivity() {
        val intent = MainActivity.newIntent(this@SplashActivity)
        startActivity(intent)
    }

}