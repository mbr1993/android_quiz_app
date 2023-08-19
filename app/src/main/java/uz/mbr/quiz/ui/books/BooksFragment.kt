package uz.mbr.quiz.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.mbr.quiz.R
import uz.mbr.quiz.adapter.DashboardAdapter
import uz.mbr.quiz.databinding.FragmentBooksBinding
import uz.mbr.quiz.datasource.DataSource

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val list = DataSource().getRecommendedBookData()
        val adapter = DashboardAdapter(list)
        binding.rvDashboard.adapter = adapter

        with(binding) {
            iconSearch.setOnClickListener {
                if (textInputSearch.visibility != View.VISIBLE) {
                    textInputSearch.visibility = View.VISIBLE
                    iconSearch.setIconResource(R.drawable.ic_clear)
                } else {
                    textInputSearch.visibility = View.GONE
                    iconSearch.setIconResource(R.drawable.ic_search)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}