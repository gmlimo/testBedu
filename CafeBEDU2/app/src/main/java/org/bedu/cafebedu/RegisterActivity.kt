package org.bedu.cafebedu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.bedu.cafebedu.databinding.ActivityRegisterBinding
import org.bedu.cafebedu.utils.executeOrRequestPermission
import org.bedu.cafebedu.utils.touchNotification

//Constantes para grabar en memoria usando Shared Preferences
val PREFS_NAME = "org.bedu.cafebedu"
val USER_KEY = "USER_KEY"
val PASS_KEY = "PASS_KEY"
lateinit var preferences: SharedPreferences

class RegisterActivity : AppCompatActivity() {

    //Declaración del binding y la actividad de acceso
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var loginActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Aquí se inicializan Shared Preferences y la actividad de acceso
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        loginActivity = LoginActivity()

        with (binding) {
            //Si se presiona el botón de registro se guardan los datos
            registerButton.setOnClickListener {
                val user = emailText.getText().toString()
                val pass = regPass.getText().toString()

            if (register(user, pass)) {
                preferences.edit()
                    .putString(USER_KEY, user)
                    .putString(PASS_KEY, pass)
                    .apply()

                //Notificación local de las promociones
                executeOrRequestPermission(this@RegisterActivity) {
                    touchNotification(this@RegisterActivity)
                }

                changeActivity(loginActivity)

            }
                else {
                    Toast.makeText(this@RegisterActivity, getString(R.string.not_empty), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //Método para validar el registro, campos de email (usuario) y contraseña no deben quedar vacíos
    private fun register(user: String, password: String): Boolean {
        fun validate(input: String) = input.isNotEmpty()

        val userValited = validate(user)
        val passwordValidated = validate(password)

        return userValited && passwordValidated
    }

    //Método para cambio de actividad
    private fun changeActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}