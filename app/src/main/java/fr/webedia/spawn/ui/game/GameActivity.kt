package fr.webedia.spawn.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import fr.webedia.spawn.R
import fr.webedia.spawn.ui.home.MainActivity

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, GameActivity::class.java)
            return intent
        }
    }

}