package com.example.mysololife.contentslist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysololife.R

class ContentRVAdapter(val context : Context ,val items: ArrayList<ContentModel>) : RecyclerView.Adapter<ContentRVAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.Viewholder, position: Int) {

        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(content: ContentModel) {
            itemView.setOnClickListener{
                Toast.makeText(context, content.title, Toast.LENGTH_SHORT).show()
                var intent = Intent(context,ContentShowActivity::class.java)
                intent.putExtra("url",content.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            bookmarkArea.setOnClickListener {
                Toast.makeText(context, "북마크 클릭", Toast.LENGTH_SHORT).show()
            }

            contentTitle.text = content.title

            Glide.with(context)
                .load(content.imageUrl)
                .into(imageViewArea)
        }
    }
}
