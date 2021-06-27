package com.octatech.expertmovie.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.octatech.expertmovie.BuildConfig
import com.octatech.expertmovie.R
import com.octatech.expertmovie.core.domain.model.Series
import com.octatech.expertmovie.databinding.ItemListSeriesBinding
import java.util.ArrayList

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.ListViewHolder>() {

    private var listData = ArrayList<Series>()
    var onItemClick: ((Series) -> Unit)? = null

    fun setData(newListData: List<Series>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_series, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListSeriesBinding.bind(itemView)
        fun bind(data: Series) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.baseImage + data.posterPath)
                    .into(ivSeries)
                tvTitleSeries.text = data.name
                tvSeriesRating.text = "⭐ " + data.voteAverage.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}