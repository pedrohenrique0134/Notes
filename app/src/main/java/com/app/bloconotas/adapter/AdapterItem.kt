package com.app.bloconotas.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Query
import com.app.bloconotas.databinding.ItemBinding
import com.app.bloconotas.models.Notass

class AdapterItem(
    private var list: ArrayList<Notass>
): RecyclerView.Adapter<AdapterItem.MyViewHolder>() {


    private var listL = list.toMutableList()

    inner class MyViewHolder(
        private var binding: ItemBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(nota: Notass){
            binding.tituloView.text = nota.title.let {it}
            binding.textoView.text = nota.texto.let {it}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterItem.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterItem.MyViewHolder, position: Int) {
        val myNotas = listL[position]
        holder.bind(myNotas)
    }

    override fun getItemCount(): Int {
        return listL.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String) : Boolean{
        listL.clear()
        listL.addAll(list.filter { it.title!!.contains(query, true)  })
        notifyDataSetChanged()
        return listL.isEmpty()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearSearch(){
        listL = list.toMutableList()
        notifyDataSetChanged()
    }


}