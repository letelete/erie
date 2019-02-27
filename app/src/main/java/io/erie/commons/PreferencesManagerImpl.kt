package io.erie.commons

import android.content.Context
import android.content.SharedPreferences

class PreferencesManagerImpl(private val context: Context) : PreferencesManager {

    companion object {
        const val PREF_NAME = "io.erie.pref_name"
        const val KEY_THEME = "io.erie.key_theme"
    }

    private val pref: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun removeValue(key: String) {
        if (pref.contains(key)) {
            pref.edit().remove(key).apply()
        }
    }

    override fun clearAllPreferences() {
        pref.edit().clear().apply()
    }

    override fun putInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int = pref.getInt(key, defaultValue)
}