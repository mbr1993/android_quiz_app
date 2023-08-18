package uz.mbr.quiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.quiz.R
import uz.mbr.quiz.data.BookData

class DashboardAdapter(private val list: MutableList<BookData>) :
    RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.coverOfBook)
        val nameBook: TextView = itemView.findViewById(R.id.book_name_text)
        val nameAuthor: TextView = itemView.findViewById(R.id.book_author_text)
        val numberOfQuestionText: TextView = itemView.findViewById(R.id.numberOfQuestionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.imageView.setImageResource(currentItem.image)
        holder.nameBook.text = currentItem.name
        holder.nameAuthor.text = currentItem.author
        holder.numberOfQuestionText.text = "Test: 0/${currentItem.numberOfQuestions}"
    }
}