package com.example.projectassembly

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.projectassembly.clases.Data
import com.example.projectassembly.clases.User
import com.example.projectassembly.databinding.ActivityRegisterBinding

const val USER_NAME = "USER_NAME"
const val PASSWRD = "PASSWRD"

class RegisterActivity : AppCompatActivity() {

    //Views declaration
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Views links to use with program
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Acquire data from our Data Class
        var datos = Data()

        //Declare bundle for transfer information between activities
        val bundle = Bundle()

        //Register button OnClickListener
        binding.registerButton.setOnClickListener {
            val apellido = binding.lastName.getText().toString() //Gets Last Name
            val nombre = binding.givenName.getText().toString() //Gets Given Name
            val usuario = binding.emailText.getText().toString() //Gets the userName
            val contraseña = binding.regPass.getText().toString() //Gets the password
           // val mapa = datos.copy(loginData = mutableMapOf(contraseña to usuario))
            val user = User(usuario, 0, contraseña, "")

            if (user.register(usuario, contraseña)) {
               // Toast.makeText(this, mapa.toString(), Toast.LENGTH_SHORT).show()
                //Send User Name and Password once the register process is over
                bundle.putString(USER_NAME, "${usuario}")
                bundle.putString(PASSWRD, "${contraseña}")
                val intent = Intent(this, LoginActivity::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(intent)
            }

        }
    }
}