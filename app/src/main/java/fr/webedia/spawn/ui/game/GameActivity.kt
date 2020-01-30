package fr.webedia.spawn.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import fr.webedia.spawn.R
import fr.webedia.spawn.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var id = intent.extras?.let { it.getString("idGame") }
        if (!id.isNullOrEmpty()) {
            val vm: GameActivityVM by viewModels { GameActivityVMFactory(application, id) }

            binding =
                DataBindingUtil.setContentView<ActivityGameBinding>(this, R.layout.activity_game).apply {
                    lifecycleOwner = this@GameActivity
                    viewModel = vm
                }

        }
    }

    companion object {

        fun newIntent(context: Context, id: String): Intent {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra("idGame", id)
            return intent
        }
    }

}