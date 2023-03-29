package com.example.projectassembly

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.projectassembly.clases.Data
import com.example.projectassembly.clases.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Views declaration to use with program
        val userName = findViewById<EditText>(R.id.userName)
        val textPass = findViewById<EditText>(R.id.textPass)
        val loginButton = findViewById<Button>(R.id.loginButton)

        val user = User("William", 2, "1234", "wlimonQcituspower.com")
        val datos = Data()

        //On ClickListener for loginButton
        loginButton.setOnClickListener{it
            val usuario = userName.getText().toString() //Gets the userName
            val contraseña = textPass.getText().toString() //Gets the password

            if (user.login(usuario, contraseña)){
                //println("Login exitoso")
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()

                //Next activity call on a successfull login
                val intent = Intent(this, ActivityProductSel::class.java).also{
                    it.putExtra("extraG", "Bienvenido")
                }
                startActivity(intent)

            } else {
                //println("Usuario y/o contraseña incorrectos")
                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}