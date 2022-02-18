package io.lojong.com.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.lojong.com.model.Fact
import kotlinx.android.synthetic.main.list_item.view.*
import lojong.R

class FactAdapter(private val context: Context, private val list: ArrayList<Fact>) :
        RecyclerView.Adapter<FactAdapter.FactViewHolder>() {

    class FactViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(fact: Fact) {
            itemView.setOnClickListener {
            }
            itemView.tvTitle.text = fact.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FactViewHolder(context, view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(newList: List<Fact>) {
        list.clear()
        val sortedList = newList.sortedBy { movie -> movie.id }
        list.addAll(sortedList)
        notifyDataSetChanged()
    }
}