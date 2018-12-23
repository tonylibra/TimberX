package com.naman14.timberx.ui.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.naman14.timberx.R
import com.naman14.timberx.databinding.ItemSongsBinding
import com.naman14.timberx.databinding.ItemSongsHeaderBinding
import com.naman14.timberx.db.QueueEntity
import com.naman14.timberx.db.TimberDatabase
import com.naman14.timberx.vo.Song

class SongsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var songs: List<Song>? = null

    private val typeSongHeader = 0
    private val typeSongItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            typeSongHeader -> return HeaderViewHolder(DataBindingUtil.inflate<ItemSongsHeaderBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_songs_header, parent, false))
            typeSongItem -> return ViewHolder(DataBindingUtil.inflate<ItemSongsBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_songs, parent, false))
            else -> return ViewHolder(DataBindingUtil.inflate<ItemSongsBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_songs, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            typeSongHeader -> (holder as HeaderViewHolder).bind(songs!!.size)
            typeSongItem -> (holder as ViewHolder).bind(songs!![position])

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) typeSongHeader else typeSongItem
    }

    override fun getItemCount(): Int {
        return songs?.let {
            //extra total song count and sorting header
            it.size + 1
        } ?: 0
    }

    class HeaderViewHolder constructor(var binding: ItemSongsHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(count: Int) {
            binding.songCount = count
            binding.executePendingBindings()
        }
    }

    class ViewHolder constructor(var binding: ItemSongsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song) {
            binding.song = song
            binding.executePendingBindings()
        }
    }

    fun updateData(songs: List<Song>) {
        this.songs = songs
        notifyDataSetChanged()
    }
}