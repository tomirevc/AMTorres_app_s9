package com.example.app_s9

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatDelegate


class UserProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var tilName: TextInputLayout
    private lateinit var tilAge: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var etAge: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button
    private lateinit var btnClean: Button

    private val USER_NAME_KEY = "username"
    private val USER_AGE_KEY = "user_age"
    private val USER_EMAIL_KEY = "user_email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        applyTheme()

        setContentView(R.layout.activity_user_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Perfil de Usuario"

        initViews()
        setupListeners()
    }

    private fun initViews() {
        tilName = findViewById(R.id.tilName)
        tilAge = findViewById(R.id.tilAge)
        tilEmail = findViewById(R.id.tilEmail)
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        etEmail = findViewById(R.id.etEmail)
        btnSave = findViewById(R.id.btnSave)
        btnLoad = findViewById(R.id.btnLoad)
        btnClean = findViewById(R.id.btnClean)
    }

    private fun setupListeners() {
        btnSave.setOnClickListener {
            if (validateFields()) {
                saveUserProfile()
            }
        }

        btnLoad.setOnClickListener {
            loadUserProfile()
        }

        btnClean.setOnClickListener {
            cleanFields()
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        val name = etName.text.toString().trim()
        if (name.isEmpty()) {
            tilName.error = "El nombre es requerido"
            isValid = false
        } else if (name.length < 2) {
            tilName.error = "El nombre debe tener al menos 2 caracteres"
            isValid = false
        } else if (!name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+".toRegex())) {
            tilName.error = "El nombre solo puede contener letras"
            isValid = false
        } else {
            tilName.error = null
        }

        val ageStr = etAge.text.toString().trim()
        if (ageStr.isEmpty()) {
            tilAge.error = "La edad es requerida"
            isValid = false
        } else {
            try {
                val age = ageStr.toInt()
                if (age < 1 || age > 120) {
                    tilAge.error = "La edad debe estar entre 1 y 120 años"
                    isValid = false
                } else {
                    tilAge.error = null
                }
            } catch (e: NumberFormatException) {
                tilAge.error = "Ingrese una edad válida"
                isValid = false
            }
        }

        val email = etEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.error = "El email es requerido"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Ingrese un email válido"
            isValid = false
        } else {
            tilEmail.error = null
        }

        return isValid
    }

    private fun saveUserProfile() {
        val name = etName.text.toString().trim()
        val age = etAge.text.toString().trim().toInt()
        val email = etEmail.text.toString().trim()

        sharedPreferencesHelper.saveString(USER_NAME_KEY, name)
        sharedPreferencesHelper.saveInt(USER_AGE_KEY, age)
        sharedPreferencesHelper.saveString(USER_EMAIL_KEY, email)

        Toast.makeText(this, "Perfil guardado correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun loadUserProfile() {
        val name = sharedPreferencesHelper.getString(USER_NAME_KEY, "")
        val age = sharedPreferencesHelper.getInt(USER_AGE_KEY, 0)
        val email = sharedPreferencesHelper.getString(USER_EMAIL_KEY, "")

        if (name.isNullOrEmpty()) {
            Toast.makeText(this, "No hay datos de usuario guardados", Toast.LENGTH_SHORT).show()
            return
        }

        etName.setText(name)
        etAge.setText(if (age > 0) age.toString() else "")
        etEmail.setText(email)

        tilName.error = null
        tilAge.error = null
        tilEmail.error = null

        Toast.makeText(this, "Datos cargados correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun cleanFields() {
        etName.setText("")
        etAge.setText("")
        etEmail.setText("")

        tilName.error = null
        tilAge.error = null
        tilEmail.error = null

        Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show()
    }

    private fun applyTheme() {
        val isDarkMode = sharedPreferencesHelper.getDarkModePreference()
        if (isDarkMode) {
            setTheme(R.style.AppTheme_Dark)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            setTheme(R.style.AppTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
