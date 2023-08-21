package uz.mbr.quiz.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.quiz.R
import uz.mbr.quiz.data.BookData

class BookRecAdapter(private val newsList: MutableList<BookData>) :
    RecyclerView.Adapter<BookRecAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.coverOfBook)
        val nameOfBook: TextView = itemView.findViewById(R.id.book_name_text)
        val numberOfQuestions: TextView = itemView.findViewById(R.id.numberOfQuestionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.nameOfBook.text = currentItem.name
        holder.image.setImageResource(currentItem.image)
        holder.numberOfQuestions.text = "Test: 0/${currentItem.numberOfQuestions}"
    }
}

