package com.app.peyza.views.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.peyza.databinding.AdapterStatesLayoutBinding
import com.app.peyza.utils.Constants
import com.app.peyza.views.ItemClicks
import com.app.peyza.views.models.StatesData
import kotlinx.android.synthetic.main.adapter_states_layout.view.*

class StatesListAdapter(val activity: Activity, var callback: ItemClicks) :
    RecyclerView.Adapter<StatesListAdapter.ViewHolder>() {

    var listData = arrayListOf<StatesData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterStatesLayoutBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatesListAdapter.ViewHolder, position: Int) {
        holder.onBind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(val binding: AdapterStatesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            itemView.layoutParams = LinearLayout.LayoutParams(
                (Constants.getWidth(activity)),
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            itemView.requestLayout()
            itemView.btnCheck.setOnClickListener {
                val data = listData[adapterPosition]
                data.isFav = if (data.isFav == 1) 0 else 1
                listData.set(adapterPosition, data)
                notifyItemChanged(adapterPosition)
                callback.onItemClicked(adapterPosition)
            }
        }

        fun onBind(item: StatesData) {
            binding.data = item
            binding.executePendingBindings()

            itemView.btnCheck.isChecked = item.isFav == 1
        }
    }

    fun addList(list: ArrayList<StatesData>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

}