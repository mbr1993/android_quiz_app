package uz.mbr.quiz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.mbr.quiz.R
import uz.mbr.quiz.databinding.FragmentHomeBinding
import uz.mbr.quiz.datasource.DataSource

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        with(binding) {
            btnStart.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_testFragment) }
            tvRecommended.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_recommendedBooksFragment) }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}