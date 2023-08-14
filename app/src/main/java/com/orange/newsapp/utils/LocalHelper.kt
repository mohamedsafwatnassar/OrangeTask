package com.orange.newsapp.utils

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LocalHelper {

    fun onAttach(context: Context): Context {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocal(context, lang)
    }

    fun setLocal(context: Context, lang: String?): Context {
        persist(context, lang)
        return updateResources(context, lang)
    }

    private fun updateResources(context: Context, lang: String?): Context {
        val config = Configuration(context.resources.configuration)
        config.fontScale = .92f
        val locale = Locale(lang!!)
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        Locale.setDefault(locale)
        return context.createConfigurationContext(config)
    }

    private fun persist(context: Context, lang: String?) {
        SharedPreferenceManager(context).setLanguage(lang!!)
    }

    private fun getPersistedData(context: Context?, language: String?): String? {
        return SharedPreferenceManager(context!!).getLanguage()
    }
}
