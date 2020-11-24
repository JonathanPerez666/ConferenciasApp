package com.platzi.conferenciasapp.view.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.platzi.conferenciasapp.model.Conference
import com.platzi.conferenciasapp.R
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.RecyclerView
import com.platzi.conferenciasapp.model.Speaker

class SpeakersAdapter (val SpeakersListener: SpeakersListener) : RecyclerView.Adapter<SpeakersAdapter.ViewHolder>() {

    var listConference = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false))

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: SpeakersAdapter.ViewHolder, position: Int) {
        val speaker = listConference[position] as Speaker

        holder.tvSpeakerPhoto.text = speaker.image
        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerWork.text = speaker.workplace

    }

    fun updateData(data: List<Conference>) {
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvSpeakerPhoto = itemView.findViewById<ImageView>(R.id.ivItemSpeakerPhoto)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvItemSpeakerName)
        val tvSpeakerWork = itemView.findViewById<TextView>(R.id.tvItemSpeakerWork)
    }

}