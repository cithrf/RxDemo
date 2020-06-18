package com.hrf.rxdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrf.rxdemo.bean.HomeBeam
import com.hrf.rxdemo.databinding.ItemListBinding

class ListAdapter : ListAdapter<HomeBeam, RecyclerView.ViewHolder>(ListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ListViewHolder).bind(item)
    }

    class ListViewHolder(
        private val binding : ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : HomeBeam){
            binding.apply {
                bean = item
                executePendingBindings()
            }
        }
    }

}

private class ListCallBack : DiffUtil.ItemCallback<HomeBeam>(){
    override fun areItemsTheSame(oldItem: HomeBeam, newItem: HomeBeam): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomeBeam, newItem: HomeBeam): Boolean {
        return oldItem == newItem
    }
}