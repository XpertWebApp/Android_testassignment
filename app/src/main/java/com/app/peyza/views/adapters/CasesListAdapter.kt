package com.app.peyza.views.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.peyza.databinding.AdapterCasesLayoutBinding
import com.app.peyza.utils.Constants
import com.app.peyza.views.ItemClicks
import com.app.peyza.views.models.CasesData
import kotlinx.android.synthetic.main.adapter_cases_layout.view.*

class CasesListAdapter(val activity: Activity, var callback: ItemClicks) :
    RecyclerView.Adapter<CasesListAdapter.ViewHolder>() {

    var listData = arrayListOf<CasesData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCasesLayoutBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CasesListAdapter.ViewHolder, position: Int) {
        holder.onBind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(val binding: AdapterCasesLayoutBinding) :
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

        fun onBind(item: CasesData) {
            itemView.confirmed.text = item.dailyconfirmed
            itemView.recovered.text = item.dailyrecovered
            itemView.totalCmformed.text = item.totalconfirmed
            itemView.totalrecovered.text = item.totalrecovered
            itemView.totaldeceaced.text = item.dailydeceased
            itemView.tvDate.text = "Date: ${item.date}"
            itemView.btnCheck.isChecked = item.isFav == 1
        }
    }

    fun addList(list: ArrayList<CasesData>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

}