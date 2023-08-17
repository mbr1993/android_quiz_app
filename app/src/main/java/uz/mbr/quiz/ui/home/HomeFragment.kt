package uz.mbr.quiz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.quiz.datasource.DataSource
import uz.mbr.quiz.adapter.BookRecAdapter
import uz.mbr.quiz.adapter.BookSoonAdapter
import uz.mbr.quiz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val listRecommended = DataSource().getRecommendedBookData()
        val adapterForRecommended = BookRecAdapter(listRecommended)
        binding.rvRecommended.adapter = adapterForRecommended

        val listSoon = DataSource().getBookSoonData()
        val adapterSoon = BookSoonAdapter(listSoon)
        binding.rvSoon.adapter = adapterSoon


        return root
    }

    private fun getData() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}