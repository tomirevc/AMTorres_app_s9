package com.example.app_s9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var tvVisitCount: TextView
    private lateinit var btnResetVisits: Button
    private lateinit var btnUserProfile: Button
    private lateinit var switchDarkMode: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        applyTheme()

        setContentView(R.layout.activity_main)

        initViews()
        setupListeners()
        updateVisitCount()
        setupDarkModeSwitch()
    }

    private fun initViews() {
        tvVisitCount = findViewById(R.id.tvVisitCount)
        btnResetVisits = findViewById(R.id.btnResetVisits)
        btnUserProfile = findViewById(R.id.btnUserProfile)
        switchDarkMode = findViewById(R.id.switchDarkMode)
    }

    private fun setupListeners() {
        btnResetVisits.setOnClickListener {
            resetVisitCount()
        }

        btnUserProfile.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            saveDarkModePreference(isChecked)
            applyTheme()
            recreate()
        }
    }

    private fun updateVisitCount() {
        // Solo incrementa la cuenta de visitas si la actividad se inicia por primera vez
        var visitCount = sharedPreferencesHelper.getVisitCount()
        visitCount++

        // Guardar el nuevo valor del contador de visitas
        sharedPreferencesHelper.saveVisitCount(visitCount)

        // Actualizar la UI con el nuevo contador
        tvVisitCount.text = visitCount.toString()
    }

    private fun resetVisitCount() {
        // Resetear el contador de visitas
        sharedPreferencesHelper.saveVisitCount(0)
        tvVisitCount.text = "0"
    }

    private fun setupDarkModeSwitch() {
        val isDarkMode = sharedPreferencesHelper.getDarkModePreference()
        switchDarkMode.isChecked = isDarkMode
    }

    private fun saveDarkModePreference(isDarkMode: Boolean) {
        sharedPreferencesHelper.saveDarkModePreference(isDarkMode)
    }

    private fun applyTheme() {
        // Obtener si está activado el modo oscuro
        val isDarkMode = sharedPreferencesHelper.getDarkModePreference()

        // Aplicar el tema correspondiente según la preferencia
        if (isDarkMode) {
            setTheme(R.style.AppTheme_Dark)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            setTheme(R.style.AppTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
