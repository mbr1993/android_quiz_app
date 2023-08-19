package uz.mbr.quiz.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
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
            btnCheck.setOnClickListener { bottomSheetDialog() }
        }

        return root
    }



    private fun bottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

        // on below line we are creating a variable for our button which we are using to dismiss our dialog.
        val btnNext = view.findViewById<Button>(R.id.btn_next)

        // on below line we are adding on click listener for our dismissing the dialog button.
        btnNext.setOnClickListener { dialog.dismiss() }
        // below line is use to set cancelable to avoid closing of dialog box when clicking on the screen.
        dialog.setCancelable(false)

        // on below line we are setting content view to our view.
        dialog.setContentView(view)

        // on below line we are calling a show method to display a dialog.
        dialog.show()
    }

    private fun showDialog(title: String) {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        builder.apply {
            setCancelable(false)
            setView(dialogView)
        }
        val content = dialogView.findViewById(R.id.content_tv) as TextView
        content.text = title
        val yesBtn = dialogView.findViewById(R.id.btnClose) as Button

        val dialog: AlertDialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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