package uz.mbr.quiz.datasource

import uz.mbr.quiz.R
import uz.mbr.quiz.data.BookData

class DataSource {
    fun getRecommendedBookData(): MutableList<BookData> {
        return mutableListOf(
            BookData(R.drawable.k01, "Bahtiyor oila", 13),
            BookData(R.drawable.k02, "Mo'minning qalqoni", 35),
            BookData(R.drawable.k03, "Mo'minning umr safari", 61),
            BookData(R.drawable.k04, "Qazr", 16),
            BookData(R.drawable.k05, "Yolg'on", 15),
            BookData(R.drawable.k06, "Hadis va Hayot", 77)
        )
    }

    fun getBookSoonData(): MutableList<BookData> {
        return mutableListOf(
            BookData(R.drawable.k07, "Hadis va Hayot", 15),
            BookData(R.drawable.k08, "Hidoyat imomi", 77),
            BookData(R.drawable.k09, "Isrof", 77)
        )
    }
}
