package com.kazim.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazim.cryptoapp.databinding.RowlayoutBinding
import com.kazim.cryptoapp.model.CryptoModelItem
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val cryptoList:ArrayList<CryptoModelItem>):RecyclerView.Adapter<RecyclerAdapter.AdapterHolder>() {
    class AdapterHolder(val binding: RowlayoutBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val binding =RowlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.binding.nametext.text =cryptoList[position].currency
        holder.binding.pricetext.text =cryptoList[position].price
        Picasso.get().load(cryptoList.get(position).logo_url).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}