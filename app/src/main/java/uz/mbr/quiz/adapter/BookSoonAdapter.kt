package uz.mbr.quiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.quiz.R
import uz.mbr.quiz.data.BookData

class BookSoonAdapter(private val newsList: MutableList<BookData>) :
    RecyclerView.Adapter<BookSoonAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.coverOfBook)
        val nameOfBook: TextView = itemView.findViewById(R.id.book_name_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.nameOfBook.text = currentItem.name
        holder.image.setImageResource(currentItem.image)
    }
}

