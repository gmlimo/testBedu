package com.example.projectassembly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.projectassembly.clases.User
import com.example.projectassembly.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText

//Global variables to use
var mapa: Map<String, String> = mutableMapOf(
    "1234" to "gmlimon",
    "hola" to "teaby",
    "5678" to "mbravo"
)

var match = false

class LoginActivity : AppCompatActivity() {

    //Views declaration
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Views declaration to use with program
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //On ClickListener for Sign Up Text to register a new user
        binding.signText.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        //On ClickListener for loginButton
        binding.loginButton.setOnClickListener{it
            val usuario = binding.userNameText.getText().toString() //Gets the userName
            val contraseña = binding.passText2.getText().toString() //Gets the password

            //Gets information from the RegisterActivity
            val bundle = intent.extras
            val R_user = bundle?.getString(USER_NAME)
            val R_passwrd = bundle?.getString(PASSWRD)

            //If the a registration process is done, the login data is overwritten
            if (R_user != null) {
                if (R_passwrd != null) {
                    mapa = mutableMapOf(R_passwrd to R_user)
                }
            }

            //Use the login validation to go to the Product Selection Activity
            if (login(usuario, contraseña)){
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()

                //Next activity call on a successfull login
                val intent = Intent(this, ActivityProductSel::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//New login function, used to been able to received user data from the RegisterActivity
fun login(user: String, password: String): Boolean {

    fun validate(input: String) = input.isNotEmpty()

    val userValidated = validate(user)
    val passwordValidated = validate(password)

    for ((key, value) in mapa){
        //println("The password is $key and the user is $value")
        if (key == password && value == user) match = true else match = false
        if (match == true) break
    }

    return userValidated && passwordValidated && match
}

fun getRegisterData() {

}