package fr.webedia.spawn.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.webedia.spawn.R
import fr.webedia.spawn.ui.home.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //launchMainActivity()
    }

    private fun launchMainActivity() {
        val intent = MainActivity.newIntent(this@SplashActivity)
        startActivity(intent)
    }

}