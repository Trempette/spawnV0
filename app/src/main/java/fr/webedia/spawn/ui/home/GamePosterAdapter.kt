package fr.webedia.spawn.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.webedia.spawn.BR
import fr.webedia.spawn.databinding.ItemGamePosterBinding
import fr.webedia.spawn.model.Game
import fr.webedia.spawn.ui.game.GameActivity

class GamePosterAdapter (val context: Context, val lifecycleOwner: LifecycleOwner, games: LiveData<List<Game>>) : ListAdapter<Game, GamePosterAdapter.GamePosterViewHolder>(PosterComparator()) {

    init {
        games.observe(lifecycleOwner, Observer {
            submitList(it)
        })
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamePosterViewHolder {
        val binding = ItemGamePosterBinding.inflate(inflater, parent, false)
        binding.lifecycleOwner = lifecycleOwner
        return GamePosterViewHolder(binding, lifecycleOwner, context)
    }

    override fun onBindViewHolder(holder: GamePosterViewHolder, position: Int) {
        val vm = ItemGamePosterVM(getItem(position))
        vm.onClickEvent.observe(lifecycleOwner, Observer {
            Log.d("TAGLOG", it)

            context.startActivity(GameActivity.newIntent(context, it))
        })
        holder.bind(vm)
    }



    class GamePosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mContext: Context? = null
        private var dataBinding: ItemGamePosterBinding? = null
        private var mLifecycleOwner: LifecycleOwner? = null

        constructor(
            dataBinding: ItemGamePosterBinding,
            lifecycleOwner: LifecycleOwner?,
            context: Context
        ) : this(dataBinding.root) {
            this.dataBinding = dataBinding
            this.mLifecycleOwner = lifecycleOwner
            this.mContext = context
        }

        fun bind(viewModel: ItemGamePosterVM) {
            dataBinding?.apply {
                setVariable(BR.viewModel, viewModel)
                lifecycleOwner = mLifecycleOwner
                executePendingBindings()
            }


        }

    }

}

private class PosterComparator : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id && oldItem.imageUrl == oldItem.imageUrl
    }

}