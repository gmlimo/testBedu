package org.bedu.cafebedu

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.widget.Toast
import org.bedu.cafebedu.databinding.ActivityLoginBinding
import org.bedu.cafebedu.notifications.NotificationApp
import org.bedu.cafebedu.utils.executeOrRequestPermission
import org.bedu.cafebedu.utils.simpleNotification


class LoginActivity : AppCompatActivity() {

    //Declaración de binding y activities
    private lateinit var binding: ActivityLoginBinding
    private lateinit var registerActivity: Activity
    private lateinit var productSelAct: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerActivity = RegisterActivity()
        productSelAct = ProductSelActivity()

        with (binding) {

            signText.setOnClickListener {
                configTransition()
                changeActivity(registerActivity)
            }

            loginButton.setOnClickListener {
                if (getLoginData()) {
                    configTransition()
                    executeOrRequestPermission(this@LoginActivity) {
                        simpleNotification(this@LoginActivity)
                    }
                    changeActivity(productSelAct)
                }
                else {
                    Toast.makeText(this@LoginActivity, getString(R.string.fail), Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    //Métodos a implementar en esta actividad

    //Método para cambiar de actividad
    private fun changeActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    //Método para revisar el acceso a la aplicación
    private fun getLoginData(): Boolean {
        val user = binding.userNameText.getText().toString()
        val password = binding.passText2.getText().toString()

        fun validate(input: String) = input.isNotEmpty()

        val userValidated = validate(user)
        val passwordValidated = validate(password)

        //Se obtienen Shared Preferences de la actividad de registro
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val r_user = preferences.getString(USER_KEY, "")
        val r_pass = preferences.getString(PASS_KEY, "")
        var match = false

        //Aquí se verifica si el usuario y contraseña del registro coincide con los datos recien ingresados
        if (user == r_user && password == r_pass) match = true else match = false
        return userValidated && passwordValidated && match
    }

    //Se configura una pequeña transición entre el acceso y el registro (login and register activities)
    private fun configTransition() {
        val transitionXml = TransitionInflater
            .from(this).inflateTransition(R.transition.activity_transition).apply {
                excludeTarget(android.R.id.statusBarBackground, true)
                excludeTarget(android.R.id.navigationBarBackground, true)
            }

        window.exitTransition = transitionXml
    }
}