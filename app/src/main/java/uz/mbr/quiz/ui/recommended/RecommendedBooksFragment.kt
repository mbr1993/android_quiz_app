package uz.mbr.quiz.ui.recommended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mbr.quiz.R
import uz.mbr.quiz.data.BookData
import uz.mbr.quiz.databinding.FragmentRecommendedBooksBinding
import uz.mbr.quiz.datasource.DataSource

class RecommendedBooksFragment : Fragment(), RecommendedFragmentAdapter.OnItemClickListener {
    private var _binding: FragmentRecommendedBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var listRecommended: ArrayList<BookData>
    private lateinit var adapter: RecommendedFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedBooksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listRecommended = DataSource().getRecommendedBookData()
        adapter = RecommendedFragmentAdapter(listRecommended, this)
        binding.rvRecommendedFragment.adapter = adapter

        binding.btnClose.setOnClickListener {
            findNavController().navigate(R.id.action_recommendedBooksFragment_to_navigation_home)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_recommendedBooksFragment_to_testFragment)
    }
}