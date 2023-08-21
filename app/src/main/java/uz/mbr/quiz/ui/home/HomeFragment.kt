package uz.mbr.quiz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.mbr.quiz.R
import uz.mbr.quiz.SharedPref
import uz.mbr.quiz.databinding.FragmentHomeBinding
import uz.mbr.quiz.datasource.DataSource

class HomeFragment : Fragment(), BookRecAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mySharedPref: SharedPref

    private val args: HomeFragmentArgs by navArgs()
    private var bestScore = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadMySharedPref()

        if (args.score > bestScore) {
            bestScore = args.score
            binding.tvBestScore.text = getString(R.string.bestResult, bestScore)
        }

        val listRecommended = DataSource().getRecommendedBookData()
        val adapterForRecommended = BookRecAdapter(listRecommended, this)
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

    //region SharedPreference
    private fun loadMySharedPref() {
        mySharedPref = SharedPref(requireContext())

        bestScore = mySharedPref.loadBestResultScore()
        binding.tvBestScore.text = getString(R.string.bestResult, bestScore)

        if (mySharedPref.loadNightModeState())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun saveMySharedPref() {
        mySharedPref.setBestResultScore(bestScore)
    }
    //endregion


    override fun onPause() {
        super.onPause()
        saveMySharedPref()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_navigation_home_to_testFragment)
    }
}