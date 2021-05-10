package com.repose.practical.mvvm.ui.main.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.repose.practical.mvvm.R
import com.repose.practical.mvvm.data.model.Article
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val users: ArrayList<Article>,
    var listener: clickListener
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article, listener: clickListener) {
            itemView.txtTitle.text = article.title
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemView.txtDesc.text =
                    Html.fromHtml(article.description, Html.FROM_HTML_MODE_COMPACT);
            } else {
                itemView.txtDesc.text = Html.fromHtml(article.description);
            }
            itemView.txtName.text = article.author
            itemView.txtDate.text = article.publishedAt
            Glide.with(itemView.imgView.context)
                .load(article.urlToImage)
                .into(itemView.imgView)

            itemView.setOnClickListener {
                listener.click(article)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position], listener)

    fun addData(list: List<Article>) {
        users.addAll(list)
    }

    interface clickListener {
        fun click(data: Article)
    }
}