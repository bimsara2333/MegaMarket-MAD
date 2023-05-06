package com.example.dashitem

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val itemList :ArrayList<Item>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_item,
        parent,false)
        return MyViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentitem=itemList[position]

        holder.itemname.text=currentitem.itemname
        holder.itemsize.text=currentitem.itemsize
        holder.itemquantity.text=currentitem.itemquantity
        holder.itemcolor.text=currentitem.itemcolor
        holder.itemaddress.text=currentitem.itemaddres
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val itemname:TextView=itemView.findViewById(R.id.tvitemname)
        val itemsize:TextView=itemView.findViewById(R.id.tvitemsize)
        val itemquantity:TextView=itemView.findViewById(R.id.tvitemquantity)
        val itemcolor:TextView=itemView.findViewById(R.id.tvitemcolor)
        val itemaddress:TextView=itemView.findViewById(R.id.tvitemaddress)






    }


}