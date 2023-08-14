package com.orange.newsapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferenceManager(private val context: Context) {

    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(
            "com.orange.newsapp",
            Context.MODE_PRIVATE
        )

    private val sharedPreferencesLanguage: SharedPreferences
        get() = context.getSharedPreferences("Your_Preference_Name", Context.MODE_PRIVATE)

    // Functions to read/write preferences
    fun getSharedStringValue(key: String, value: String): String {
        return sharedPreferences.getString(key, value) ?: value
    }

    fun getSharedBooleanValue(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setSharedStringValue(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    fun setSharedBooleanValue(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }

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
