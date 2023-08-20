package uz.mbr.quiz.ui

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import uz.mbr.quiz.R
import uz.mbr.quiz.data.Question
import uz.mbr.quiz.databinding.BottomSheetDialogBinding
import uz.mbr.quiz.databinding.CustomDialogBinding
import uz.mbr.quiz.databinding.FragmentTestBinding
import uz.mbr.quiz.datasource.QuestionDataSource
import java.util.concurrent.TimeUnit

class TestFragment : Fragment() {
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    private lateinit var checkAnswerDialog: BottomSheetDialog

    private var questionList: MutableList<Question> = QuestionDataSource().getQuestionData()
    private var questionPosition = 0

    private var selectedAnswer = ""
    private var foundCorrectAnswers = 0
    private var chances = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setQuestion()
        clickOptions()

        with(binding) {
            btnClose.setOnClickListener { showBackPressedDialog() }
            btnCheck.setOnClickListener { checkAnswer() }
        }


        //onBackPressed
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showBackPressedDialog()
            }
        })

        return root
    }

    private fun showBackPressedDialog() {
        // Create the object of AlertDialog Builder class
        val builder = AlertDialog.Builder(requireContext())
        // Set the message show for the Alert time

        // Set Alert Title
        builder.setTitle("Testni tark etishga ishonchingiz komilmi?")

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false)

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Ha",
            DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                // When the user click yes button then app will close
                findNavController().navigate(R.id.action_testFragment_to_navigation_home)
            })

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("Yo'q",
            DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                // If user click no then dialog box is canceled.
                dialog.cancel()
            })

        // Create the Alert dialog
        val alertDialog = builder.create()
        // Show the Alert Dialog box
        alertDialog.show()
    }

    private fun checkAnswer() {
        if (questionList[questionPosition].correctAnswer == selectedAnswer) {
            foundCorrectAnswers++
            binding.scoreText.text = foundCorrectAnswers.toString()

            showCorrectAnswerDialog(getString(R.string.showing_correct_answer, selectedAnswer))
        } else {
            chances--
            binding.tvTitle.text = getString(R.string.chance, chances)

            showIncorrectAnswerDialog(
                getString(
                    R.string.showing_incorrect_answer,
                    questionList[questionPosition].correctAnswer
                )
            )
            when (chances) {
                2 -> {
                    binding.star3.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.md_theme_primaryContainer)
                    )
                }

                1 -> {
                    binding.star2.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.md_theme_primaryContainer)
                    )
                }

                0 -> {
                    binding.star1.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.md_theme_primaryContainer)
                    )
                    showResultDialog(getString(R.string.final_dialog, foundCorrectAnswers))
                }
            }
        }
        questionPosition++
    }

    private fun showCorrectAnswerDialog(title: String) {
        val dialog = BottomSheetDialog(requireContext())
        val dialogBinding = BottomSheetDialogBinding.inflate(LayoutInflater.from(requireContext()))
        // on below line we are setting content view to our view.
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)

        with(dialogBinding) {
            bottomSheetLinear.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.md_theme_tertiaryContainer)
            )
            tvContent.text = title
            tvTitle.text = getString(R.string.your_answer_is_correct)
            btnNext.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.md_theme_tertiary)
            )
            btnNext.setOnClickListener {
                setQuestion()
                dialog.dismiss()
            }
        }

        dialog.show()
        playConfettiAnimation()
    }

    private fun showIncorrectAnswerDialog(title: String) {
        checkAnswerDialog = BottomSheetDialog(requireContext())
        val dialogBinding = BottomSheetDialogBinding.inflate(LayoutInflater.from(requireContext()))
        // on below line we are setting content view to our view.
        checkAnswerDialog.setContentView(dialogBinding.root)
        checkAnswerDialog.setCancelable(false)

        with(dialogBinding) {
            bottomSheetLinear.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.md_theme_primaryContainer)
            )
            tvContent.text = title
            tvTitle.text = getString(R.string.your_answer_is_incorrect)
            btnNext.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.md_theme_primary)
            )
            btnNext.setOnClickListener {
                setQuestion()
                checkAnswerDialog.dismiss()
            }
        }
        checkAnswerDialog.show()
    }

    private fun showResultDialog(title: String) {
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(requireContext()))
        builder.apply {
            setCancelable(false)
            setView(dialogBinding.root)
        }
        dialogBinding.textContent.text = title

        val dialog: AlertDialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogBinding.btnClose.setOnClickListener {
            findNavController().popBackStack()
            checkAnswerDialog.dismiss()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setQuestion() {
        defaultOptionsView()
        with(binding) {
            tvQuestion.text = questionList[questionPosition].question
            tvAnswer1.text = questionList[questionPosition].answer1
            tvAnswer2.text = questionList[questionPosition].answer2
            tvAnswer3.text = questionList[questionPosition].answer3
        }
    }

    private fun clickOptions() {
        binding.cvAnswer1.setOnClickListener {
            selectedOption(binding.cvAnswer1)
            selectedAnswer = questionList[questionPosition].answer1
        }
        binding.cvAnswer2.setOnClickListener {
            selectedOption(binding.cvAnswer2)
            selectedAnswer = questionList[questionPosition].answer2
        }
        binding.cvAnswer3.setOnClickListener {
            selectedOption(binding.cvAnswer3)
            selectedAnswer = questionList[questionPosition].answer3
        }
    }

    private fun selectedOption(cv: MaterialCardView) {
        defaultOptionsView()
        cv.setCardBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.md_theme_primaryContainer)
        )
        cv.cardElevation = 50F
        cv.strokeWidth = 2
        binding.btnCheck.visibility = View.VISIBLE
    }

    private fun defaultOptionsView() {
        binding.cvAnswer1.setCardBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.md_theme_background)
        )
        binding.cvAnswer1.cardElevation = 2F
        binding.cvAnswer1.strokeWidth = 2

        binding.cvAnswer2.setCardBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.md_theme_background)
        )
        binding.cvAnswer2.cardElevation = 2F
        binding.cvAnswer2.strokeWidth = 2

        binding.cvAnswer3.setCardBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.md_theme_background)
        )
        binding.cvAnswer3.cardElevation = 2F
        binding.cvAnswer3.strokeWidth = 2

        binding.btnCheck.visibility = View.INVISIBLE
    }

    private fun playConfettiAnimation() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        binding.konfettiView.start(party)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}