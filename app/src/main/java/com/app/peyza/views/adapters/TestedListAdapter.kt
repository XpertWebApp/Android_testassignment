package com.app.peyza.views.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.peyza.databinding.AdapterTestedLayoutBinding
import com.app.peyza.utils.Constants
import com.app.peyza.views.ItemClicks
import com.app.peyza.views.models.TestedData
import kotlinx.android.synthetic.main.adapter_tested_layout.view.*


class TestedListAdapter(val activity: Activity, var callback: ItemClicks) :
    RecyclerView.Adapter<TestedListAdapter.ViewHolder>() {

    var listData = arrayListOf<TestedData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTestedLayoutBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestedListAdapter.ViewHolder, position: Int) {
        holder.onBind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(val binding: AdapterTestedLayoutBinding) :
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

            itemView.tvSource.setOnClickListener {
                Toast.makeText(activity, "Link not available", Toast.LENGTH_SHORT).show()
                /* val browserIntent =
                     Intent(Intent.ACTION_VIEW, Uri.parse(listData[adapterPosition].source))
                 activity.startActivity(browserIntent)*/
            }
        }

        fun onBind(item: TestedData) {
            binding.data = item
            binding.executePendingBindings()
            itemView.btnCheck.isChecked = item.isFav == 1
        }
    }

    fun addList(list: ArrayList<TestedData>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

}