package fr.webedia.spawn.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.webedia.optimusprime.utils.livedata.SingleLiveEvent
import fr.webedia.spawn.model.Game


class SectionItemVM(movieShowtime: GamesListItem, index: Int, displayAttribute: LiveData<Boolean>, lifecycleOwner: LifecycleOwner): ShowtimeItemVM(movieShowtime, index) {
    val attributeVisible = ObservableField<Boolean>()
    init {
        displayAttribute.observe(lifecycleOwner, Observer {
            attributeVisible.set(it)
        })
    }
    val groupLabel = movieShowtime.groupingName
    val showtimes = movieShowtime.showtimes
    val clickOnAttributeInfos = SingleLiveEvent<String>()
    fun openAttributeInfos() {
        clickOnAttributeInfos.value = showtimes!![0].id
    }
}


class ComingSoonAdapter(private val games: LiveData<List<Game>>, private val globalVM: ComingSoonFragmentVM, val context: Context, val lifecycleOwner: LifecycleOwner) : ListAdapter<ShowtimeListItem, BindingViewHolder>(GameItemComparator()) {

    val TYPE_SECTION = 1
    val TYPE_ROW = 2

    init {
        games.observe(lifecycleOwner, Observer {
            submitList(it)
        })
    }

    private val inflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SectionItem -> TYPE_SECTION
            else -> TYPE_ROW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        when (viewType) {
            TYPE_SECTION -> {
                val binding = ItemTheaterShowtimeHeaderBinding.inflate(inflater, parent, false)
                binding.filtersList.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = FilterAdapter(context, lifecycleOwner, globalVM.attributes, globalVM.activeFilters)
                }
                BindingViewHolder(binding)
            }
            else -> {
                val binding = ItemTheaterShowtimeRowBinding.inflate(inflater, parent, false)
                BindingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_SECTION -> {
                val vm = TheaterShowtimeHeaderVM(
                        lifecycleOwner,
                        globalVM.selectedDate,
                        globalVM.attributes,
                        getItem(position) as HeaderItem,
                        position,
                        context
                    )
                holder.bind(vm)
            }
            else -> holder.bind(TheaterShowtimeSectionVM(getItem(position), position))
        }
    }
}

private class GameItemComparator : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }

}