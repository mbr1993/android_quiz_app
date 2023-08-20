package uz.mbr.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mbr.quiz.R
import uz.mbr.quiz.adapter.RecommendedFragmentAdapter
import uz.mbr.quiz.databinding.FragmentRecommendedBooksBinding
import uz.mbr.quiz.datasource.DataSource

class RecommendedBooksFragment : Fragment() {
    private var _binding: FragmentRecommendedBooksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedBooksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listRecommended = DataSource().getRecommendedBookData()
        val adapterForRecommended = RecommendedFragmentAdapter(listRecommended)
        binding.rvRecommendedFragment.adapter = adapterForRecommended

        binding.btnClose.setOnClickListener {
            findNavController().navigate(R.id.action_recommendedBooksFragment_to_navigation_home)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}