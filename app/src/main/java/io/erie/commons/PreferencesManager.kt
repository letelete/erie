package io.erie.commons

interface PreferencesManager {
    fun removeValue(key: String)
    fun clearAllPreferences()
    fun putInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int): Int
}