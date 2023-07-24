package edu.upbc.beducoffee.views

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.upbc.beducoffee.R
import edu.upbc.beducoffee.databinding.ActivityRegisterBinding
import edu.upbc.beducoffee.utils.executeOrRequestPermission
import edu.upbc.beducoffee.utils.touchNotification

//Constantes para grabar en memoria usando Shared Preferences
val PREFS_NAME = "edu.upbc.cafebedu"
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

        FirebaseApp.initializeApp(this)

        auth = Firebase.auth

        //Aquí se inicializan Shared Preferences y la actividad de acceso
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        loginActivity = LoginActivity()

        with (binding) {
            //Si se presiona el botón de registro se guardan los datos
            registerButton.setOnClickListener {

                val user = emailText.getText().toString()
                val pass = regPass.getText().toString()

                createAccount()

                if (register(user, pass)) {
                    preferences.edit()
                        .putString(USER_KEY, user)
                        .putString(PASS_KEY, pass)
                        .apply()

                    //Notificación local de bienvenida
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

    private fun createAccount() {
        val user = binding.emailText.getText().toString()
        val pass = binding.regPass.getText().toString()
        auth.createUserWithEmailAndPassword(user, pass)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Log.d(TAG, "createAccount: success")
                    val user = auth.currentUser
                    //updateUI(user, null)
                } else {
                    Log.w(TAG, "createAccount: failure", task.exception)
                    //updateUI(null, task.exception)
                }
            }
    }
}