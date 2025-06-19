package com.example.app_s9

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val PREF_NAME = "AppS9Preferences"
        const val KEY_VISIT_COUNT = "visit_count"
        const val KEY_DARK_MODE = "dark_mode"
        const val KEY_USERNAME = "username"
        const val KEY_USER_AGE = "user_age"
        const val KEY_USER_EMAIL = "user_email"
    }

    // Métodos para guardar datos
    fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    // Métodos para obtener datos
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    // Métodos para eliminar datos
    fun remove(key: String) {
        editor.remove(key).apply()
    }

    // Método para limpiar todas las preferencias
    fun clearAll() {
        editor.clear().apply()
    }

    // Método para verificar si existe una clave
    fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    // Métodos específicos para el contador de visitas
    fun saveVisitCount(count: Int) {
        saveInt(KEY_VISIT_COUNT, count)
    }

    fun getVisitCount(): Int {
        return getInt(KEY_VISIT_COUNT)
    }

    // Métodos específicos para el modo oscuro
    fun saveDarkModePreference(isDarkMode: Boolean) {
        saveBoolean(KEY_DARK_MODE, isDarkMode)
    }

    fun getDarkModePreference(): Boolean {
        return getBoolean(KEY_DARK_MODE)
    }
}
