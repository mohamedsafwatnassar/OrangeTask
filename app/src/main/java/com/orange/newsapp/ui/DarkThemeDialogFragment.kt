package com.orange.newsapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import com.orange.newsapp.MainActivity
import com.orange.newsapp.R
import com.orange.newsapp.databinding.FragmentDarkThemeDialogBinding
import com.orange.newsapp.utils.Constant
import com.orange.newsapp.utils.SharedPreferenceManager

class DarkThemeDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDarkThemeDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDarkThemeDialogBinding.inflate(LayoutInflater.from(context))
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
        when (SharedPreferenceManager(requireContext()).getTheme()) {
            Constant.LIGHT -> {
                binding.rbLight.isChecked = true
                binding.rbDark.isChecked = false
            }
            Constant.DARK -> {
                binding.rbDark.isChecked = true
                binding.rbLight.isChecked = false
            }
            else -> {
                binding.rbLight.isChecked = true
                binding.rbDark.isChecked = false
            }
        }
    }

    private fun setBtnListener(dialog: AlertDialog) {
        // Get radio group selected item using on checked change listener
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> // group, checkedId
            val radio: RadioButton = dialog.findViewById(checkedId)
            when (radio.id) {
                R.id.rbLight -> {
                    dismiss()
                    binding.rbLight.isChecked = true
                    binding.rbDark.isChecked = false
                    setTheme(AppCompatDelegate.MODE_NIGHT_NO)
                    SharedPreferenceManager(requireContext()).setTheme(Constant.LIGHT)
                }
                R.id.rbDark -> {
                    dismiss()
                    binding.rbDark.isChecked = true
                    binding.rbLight.isChecked = false
                    setTheme(AppCompatDelegate.MODE_NIGHT_YES)
                    SharedPreferenceManager(requireContext()).setTheme(Constant.DARK)
                }
            }
        }
    }

    private fun setTheme(modeNight: Int) {
        AppCompatDelegate.setDefaultNightMode(modeNight)
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }
}
