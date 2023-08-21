package uz.mbr.quiz.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.mbr.quiz.R
import uz.mbr.quiz.databinding.BottomSheetDialogLanguageBinding
import uz.mbr.quiz.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding) {

            // Contribution
            contribution.setOnClickListener {
                val telegram =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/hilol_loyiha"))
                startActivity(telegram)
            }

            // darkMode
            switchDarkMode.setOnCheckedChangeListener { _, iwsChecked ->
                if (binding.switchDarkMode.isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

            // App on Play Market
            apps.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(
                            "https://play.google.com/store/apps/developer?id=Hilol+Nashr+nashriyoti&hl=en&gl=US"
                        )
                    )
                )
            }

            // Share app
            share.setOnClickListener {
                try {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    val shareMessage =
                        "https://play.google.com/store/apps/details?id=uz.hilolnashr.hilolquiz&hl=ru&gl=US"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    startActivity(Intent.createChooser(shareIntent, "choose one"))
                } catch (e: Exception) {
                    //e.toString();
                }
            }

            // Language
            linearLanguage.setOnClickListener { chooseLanguageDialog() }
        }

        return root
    }

    private fun chooseLanguageDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val dialogBinding =
            BottomSheetDialogLanguageBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        with(dialogBinding) {
            tvLatin.setOnClickListener {
                binding.tvLanguage.text = getString(R.string.uzbek_latin)
                dialog.dismiss()
            }
            tvKiril.setOnClickListener {
                Toast.makeText(requireContext(), "Ishlov jarayonida", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}