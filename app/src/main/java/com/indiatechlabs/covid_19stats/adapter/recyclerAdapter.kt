package com.indiatechlabs.covid_19stats.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.indiatechlabs.covid_19stats.R
import com.indiatechlabs.covid_19stats.model.MainModel

class RecyclerAdapter(val context : Context, private val itemList : ArrayList<MainModel>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>(){

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val etCountry : TextView = view.findViewById(R.id.etCountry)
        val etTotalConf : TextView = view.findViewById(R.id.etTotalConf)
        val etNewConf : TextView = view.findViewById(R.id.etNewConf)
        val etTotalDeaths : TextView = view.findViewById(R.id.etTotalDeaths)
        val etNewDeaths : TextView = view.findViewById(R.id.etNewDeaths)
        val etTotalRec : TextView = view.findViewById(R.id.etTotalRec)
        val etNewRec : TextView = view.findViewById(R.id.etNewRec)
        val etActive : TextView = view.findViewById(R.id.etActive)
        val etNewActive : TextView = view.findViewById(R.id.etNewActive)
        val imgFlag : ImageView = view.findViewById(R.id.imgFlag)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_data, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.etCountry.text = itemList[position].country
        holder.etTotalConf.text = itemList[position].totalConf.toString()
        holder.etNewConf.text = "+("+itemList[position].newConf.toString()+")"
        holder.etTotalDeaths.text = itemList[position].totalDeaths.toString()
        holder.etNewDeaths.text = "+("+itemList[position].newDeaths.toString()+")"
        holder.etTotalRec.text = itemList[position].totalRec.toString()
        holder.etNewRec.text = "+("+itemList[position].newRec.toString()+")"
        holder.etActive.text = (itemList[position].totalConf-itemList[position].totalRec-itemList[position].totalDeaths).toString()
        holder.etNewActive.text = "+("+(itemList[position].newConf-itemList[position].newDeaths-itemList[position].newRec).toString()+")"
        Picasso.get().load(itemList[position].flagUrl)
    }

}