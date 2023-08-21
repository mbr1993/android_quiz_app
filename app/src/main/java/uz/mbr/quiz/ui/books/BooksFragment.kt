package uz.mbr.quiz.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mbr.quiz.R
import uz.mbr.quiz.data.BookData
import uz.mbr.quiz.databinding.FragmentBooksBinding
import uz.mbr.quiz.datasource.DataSource
import java.util.Locale

class BooksFragment : Fragment(), BooksAdapter.OnItemClickListener {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var list: ArrayList<BookData>
    private lateinit var adapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        list = DataSource().getRecommendedBookData()
        adapter = BooksAdapter(list, this)
        binding.rvDashboard.adapter = adapter

        with(binding) {
            iconSearch.setOnClickListener {
                if (cvSearchView.visibility != View.VISIBLE) {
                    cvSearchView.visibility = View.VISIBLE
                    iconSearch.setIconResource(R.drawable.ic_clear)
                } else {
                    cvSearchView.visibility = View.GONE
                    iconSearch.setIconResource(R.drawable.ic_search)
                }
            }
            searchView.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText)
                    return true
                }
            })
        }

        return root
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<BookData>()
            for (i in list) {
                if (i.name.toLowerCase(Locale.ROOT).contains(query))
                    filteredList.add(i)
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getText(R.string.search_view_no_result_text),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_navigation_books_to_testFragment)
    }
}