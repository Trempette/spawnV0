package fr.webedia.spawn.ui.home

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.webedia.spawn.BR
import fr.webedia.spawn.databinding.ItemComingSoonBinding
import fr.webedia.spawn.model.GameListItem

class ComingSoonAdapter(
    private val games: LiveData<List<GameListItem>>,
    private val globalVM: ComingSoonFragmentVM,
    val context: Context,
    val lifecycleOwner: LifecycleOwner,
    val application: Application
) : ListAdapter<GameListItem, ComingSoonAdapter.ComingSoonViewHolder>(GameItemComparator()) {

    init {
        games.observe(lifecycleOwner, Observer {
            submitList(it)
        })
    }

    private val inflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonViewHolder {
        val binding = ItemComingSoonBinding.inflate(inflater, parent, false)
        binding.gamesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            if (viewType == 0) adapter = GamePosterAdapter(context, lifecycleOwner, globalVM.listMostWaited)
            if (viewType == 1) adapter = GamePosterAdapter(context, lifecycleOwner, globalVM.listSortieDuMois)
            if (viewType == 2) adapter = GamePosterAdapter(context, lifecycleOwner, globalVM.mesSortie)
        }
        return ComingSoonViewHolder(binding, lifecycleOwner, context)
    }

    override fun onBindViewHolder(holder: ComingSoonViewHolder, position: Int) {
        holder.bind(ItemComingSoonVM(application, getItem(position)))

    }

    class ComingSoonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mContext: Context? = null
        private var dataBinding: ItemComingSoonBinding? = null
        private var mLifecycleOwner: LifecycleOwner? = null

        constructor(
            dataBinding: ItemComingSoonBinding,
            lifecycleOwner: LifecycleOwner?,
            context: Context
        ) : this(dataBinding.root) {
            this.dataBinding = dataBinding
            this.mLifecycleOwner = lifecycleOwner
            this.mContext = context
        }

        fun bind(viewModel: ItemComingSoonVM) {
            dataBinding?.apply {
                setVariable(BR.viewModel, viewModel)
                lifecycleOwner = mLifecycleOwner
                executePendingBindings()
            }
        }

    }

}


private class GameItemComparator : DiffUtil.ItemCallback<GameListItem>() {
    override fun areItemsTheSame(oldItem: GameListItem, newItem: GameListItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: GameListItem, newItem: GameListItem): Boolean {
        return oldItem.title == newItem.title && oldItem.listGames.size == oldItem.listGames.size
    }

}