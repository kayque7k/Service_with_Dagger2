package com.wolfdevelloper.estudo.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wolfdevelloper.estudo.R
import com.wolfdevelloper.estudo.viewmodel.Statement
import com.wolfdevelloper.estudo.databinding.ItemStatementBinding
import com.wolfdevelloper.estudo.recyclerview.viewholder.StatementViewHolder

class StatementAdapter(private val list: MutableList<Statement>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        StatementViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_statement,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StatementViewHolder)
            holder.binding.statement = list[position]
    }

    override fun getItemCount(): Int = list.size


    fun getStatements(): MutableList<Statement> = this.list

}