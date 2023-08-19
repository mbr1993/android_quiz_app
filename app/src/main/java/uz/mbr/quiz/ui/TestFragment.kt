package uz.mbr.quiz.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import uz.mbr.quiz.R
import uz.mbr.quiz.data.Question
import uz.mbr.quiz.databinding.FragmentTestBinding
import uz.mbr.quiz.datasource.QuestionDataSource


class TestFragment : Fragment() {
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    private var dataSource = QuestionDataSource()
    private lateinit var questionlist: MutableList<Question>

    private var currentQuestionPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        questionlist = dataSource.getQuestionData()

        setQuestion()
        clickOptions()

        with(binding) {
            btnClose.setOnClickListener { findNavController().navigate(R.id.action_testFragment_to_navigation_home) }
            btnCheck.setOnClickListener { showDialog("you have a wrong answer") }
        }

        return root
    }

    private fun dia() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog)
        dialog.show()
    }

    private fun showDialog(title: String) {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        val body = dialog.findViewById(R.id.body) as TextView
        body.text = title
        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setQuestion() {
        val question = questionlist[currentQuestionPosition]

        with(binding) {
            tvQuestion.text = question.question
            tvAnswer1.text = question.answer1
            tvAnswer2.text = question.answer2
            tvAnswer3.text = question.answer3
        }
        currentQuestionPosition++
    }

    private fun clickOptions() {
        binding.cvAnswer1.setOnClickListener { selectedOption(binding.cvAnswer1) }
        binding.cvAnswer2.setOnClickListener { selectedOption(binding.cvAnswer2) }
        binding.cvAnswer3.setOnClickListener { selectedOption(binding.cvAnswer3) }
    }

    private fun selectedOption(cv: MaterialCardView) {
        defaultOptions()
        cv.setCardBackgroundColor(resources.getColor(R.color.md_theme_primaryContainer))
        cv.cardElevation = 50F
        cv.strokeWidth = 2
        binding.btnCheck.visibility = View.VISIBLE
    }

    private fun defaultOptions() {
        binding.cvAnswer1.setCardBackgroundColor(resources.getColor(R.color.md_theme_background))
        binding.cvAnswer1.cardElevation = 2F
        binding.cvAnswer1.strokeWidth = 2

        binding.cvAnswer2.setCardBackgroundColor(resources.getColor(R.color.md_theme_background))
        binding.cvAnswer2.cardElevation = 2F
        binding.cvAnswer2.strokeWidth = 2

        binding.cvAnswer3.setCardBackgroundColor(resources.getColor(R.color.md_theme_background))
        binding.cvAnswer3.cardElevation = 2F
        binding.cvAnswer3.strokeWidth = 2

        binding.btnCheck.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}