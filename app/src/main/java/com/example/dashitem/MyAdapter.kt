package com.example.dashitem

import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
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

        holder.card.setOnClickListener {
            val intent = Intent(holder.itemView.context, Updateorder::class.java)
            intent.putExtra("itemId", currentitem.itemID)
            intent.putExtra("size", currentitem.itemsize)
            intent.putExtra("name", currentitem.itemname)
            intent.putExtra("qty", currentitem.itemquantity)
            intent.putExtra("color", currentitem.itemcolor)
            intent.putExtra("address", currentitem.itemaddres)

            holder.itemView.context.startActivity(intent)
        }

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
        val card:CardView = itemView.findViewById(R.id.card)

    }


}