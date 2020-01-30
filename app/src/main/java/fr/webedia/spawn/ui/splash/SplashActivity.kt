package fr.webedia.spawn.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import fr.webedia.spawn.R
import fr.webedia.spawn.ui.home.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            launchMainActivity()
        }, 2000)
    }

    private fun launchMainActivity() {
        val intent = MainActivity.newIntent(this@SplashActivity)
        startActivity(intent)
    }

}