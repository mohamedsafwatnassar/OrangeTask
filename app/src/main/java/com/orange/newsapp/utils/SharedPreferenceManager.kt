package com.orange.newsapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager(private val context: Context) {

    private val sharedPreferencesLanguage: SharedPreferences
        get() = context.getSharedPreferences("com.orange.newsapp", Context.MODE_PRIVATE)

    fun setLanguage(lang: String) {
        with(sharedPreferencesLanguage.edit()) {
            putString(Constant.KEY_LANGUAGE, lang)
            apply()
        }
    }

    fun getLanguage(): String? {
        return sharedPreferencesLanguage.getString(Constant.KEY_LANGUAGE, "en")
    }

    fun setTheme(theme: String) {
        with(sharedPreferencesLanguage.edit()) {
            putString(Constant.KEY_THEME, theme)
            apply()
        }
    }

    fun getTheme(): String? {
        return sharedPreferencesLanguage.getString(Constant.KEY_THEME, "")
    }
}
