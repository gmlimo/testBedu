package edu.upbc.beducoffee.views

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.upbc.beducoffee.R
import edu.upbc.beducoffee.databinding.ActivityLoginBinding
import edu.upbc.beducoffee.utils.DownloadController
import edu.upbc.beducoffee.utils.checkSelfPermissionCompat
import edu.upbc.beducoffee.utils.requestPermissionsCompat
import edu.upbc.beducoffee.utils.shouldShowRequestPermissionRationaleCompat


lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {

    //Declaración de binding y activities
    private lateinit var binding: ActivityLoginBinding
    private lateinit var registerActivity: Activity
    private lateinit var productSelAct: Activity
    private lateinit var downloadController: DownloadController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicialización de FirebaseAuth
        FirebaseApp.initializeApp(this)

        auth = Firebase.auth

        registerActivity = RegisterActivity()
        productSelAct = ProductSelActivity()

        downloadController = DownloadController(this, urlApp)
       // checkUpdate()

        with (binding) {

            signText.setOnClickListener {
                configTransition()
                changeActivity(registerActivity)
            }

            loginButton.setOnClickListener {
                signIn()
            }

            version.setOnClickListener {
                checkStoragePermission()
            }

        }
    }

    //Métodos a implementar en esta actividad

    //Método para cambiar de actividad
    private fun changeActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
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

    private fun signIn() {
        val user = binding.userNameText.getText().toString()
        val password = binding.passText2.getText().toString()

        fun validate(input: String) = input.isNotEmpty()

        val userValidated = validate(user)
        val passwordValidated = validate(password)

        if (userValidated && passwordValidated) {
            auth.signInWithEmailAndPassword(user, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signIn: success")
                        val user = auth.currentUser
                        updateUI(user, null)
                        configTransition()
                        changeActivity(productSelAct)
                    } else {
                        Log.w(TAG, "signIn: failure", task.exception)
                        task.exception?.let { updateUI(null, it) }
                    }
                }
        }
    }

    fun showMessage(messsage: String) {
        Toast.makeText(this@LoginActivity, messsage, Toast.LENGTH_SHORT).show()
    }

    fun updateUI(user: FirebaseUser?, exception: Exception?) {
        if (exception != null) {
            showMessage(exception.message.toString())
        } else {
            showMessage(user.toString() + " " + getString(R.string.success))
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadController.enqueueDownload()
            } else {
                Toast.makeText(this, R.string.storage_permission_denied, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkStoragePermission() {
        if (checkSelfPermissionCompat(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            downloadController.enqueueDownload()
        } else {
            requestStoragePermission()
        }
    }

    private fun requestStoragePermission() {
        if (shouldShowRequestPermissionRationaleCompat(WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, R.string.storage_permission_denied, Toast.LENGTH_LONG).show()
            requestPermissionsCompat(
                arrayOf(WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_STORAGE
            )
        } else {
            requestPermissionsCompat(
                arrayOf(WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_STORAGE
            )
        }
    }


    companion object {
        private const val TAG = "FirebaseActivity"
        const val PERMISSION_REQUEST_STORAGE = 0
        const val urlApp = "https://github.com/gmlimo/testBedu/raw/apk/bedu_coffee2-release.apk"
        //const val urlCode = "https://github.com/gmlimo/testBedu/raw/apk/versionCode.txt"
    }
}