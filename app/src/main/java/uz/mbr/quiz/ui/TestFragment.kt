package uz.mbr.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mbr.quiz.R
import uz.mbr.quiz.databinding.FragmentTestBinding

class TestFragment : Fragment() {
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding) {
            btnScore.setOnClickListener {
                findNavController().navigate(R.id.action_testFragment_to_navigation_home)
            }

            question1.setOnClickListener {
                question1.cardElevation = 100F
                question2.cardElevation = 1F
                question3.cardElevation = 1F
                question1.strokeWidth = 2
                question2.strokeWidth = 0
                question3.strokeWidth = 0

                checkQuestion.setCardBackgroundColor(resources.getColor(R.color.md_theme_tertiaryContainer))
            }
            question2.setOnClickListener {
                question2.cardElevation = 100F
                question1.cardElevation = 1F
                question3.cardElevation = 1F
                question2.strokeWidth = 2
                question1.strokeWidth = 0
                question3.strokeWidth = 0

                checkQuestion.setCardBackgroundColor(resources.getColor(R.color.md_theme_tertiaryContainer))
            }
            question3.setOnClickListener {
                question3.cardElevation = 100F
                question2.cardElevation = 1F
                question1.cardElevation = 1F
                question3.strokeWidth = 2
                question2.strokeWidth = 0
                question1.strokeWidth = 0

                checkQuestion.setCardBackgroundColor(resources.getColor(R.color.md_theme_tertiaryContainer))
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}