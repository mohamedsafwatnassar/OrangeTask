package com.orange.newsapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.orange.newsapp.MainActivity
import com.orange.newsapp.R
import com.orange.newsapp.databinding.FragmentLanguageDialogBinding
import com.orange.newsapp.utils.Constant
import com.orange.newsapp.utils.LocalHelper
import com.orange.newsapp.utils.SharedPreferenceManager
import com.orange.newsapp.utils.toast

class LanguageDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentLanguageDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentLanguageDialogBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()

        // Set custom width and height
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        initView()
        setBtnListener(dialog)

        return dialog
    }

    private fun initView() {
        when (SharedPreferenceManager(requireContext()).getLanguage()) {
            Constant.EN -> {
                binding.rbEnglish.isChecked = true
                binding.rbArabic.isChecked = false
            }
            Constant.AR -> {
                binding.rbArabic.isChecked = true
                binding.rbEnglish.isChecked = false
            }
        }
    }

    private fun setBtnListener(dialog: AlertDialog) {
        // Get radio group selected item using on checked change listener
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> // group, checkedId
            val radio: RadioButton = dialog.findViewById(checkedId)
            when (radio.id) {
                R.id.rbEnglish -> {
                    binding.rbEnglish.isChecked = true
                    binding.rbArabic.isChecked = false
                    setLanguage(Constant.EN)
                }
                R.id.rbArabic -> {
                    binding.rbArabic.isChecked = true
                    binding.rbEnglish.isChecked = false
                    setLanguage(Constant.AR)
                }
            }
        }
    }

    private fun setLanguage(language: String) {
        LocalHelper.setLocal(requireActivity(), language)
        toast("${getString(R.string.language)}: $language")
        // restart main activity to change language
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }
}
