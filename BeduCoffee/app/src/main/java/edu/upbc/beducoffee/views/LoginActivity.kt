package edu.upbc.beducoffee.views

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.upbc.beducoffee.R
import edu.upbc.beducoffee.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {

    //Declaración de binding y activities
    private lateinit var binding: ActivityLoginBinding
    private lateinit var registerActivity: Activity
    private lateinit var productSelAct: Activity


     private lateinit var googleClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicialización de FirebaseAuth
        FirebaseApp.initializeApp(this)
        //auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
          googleClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth

        registerActivity = RegisterActivity()
        productSelAct = ProductSelActivity()

        with (binding) {

            signText.setOnClickListener {
                configTransition()
                changeActivity(registerActivity)
            }

            loginButton.setOnClickListener {
               // val signInIntent = googleClient.signInIntent
              //  startActivityForResult(signInIntent, RC_SIGN_IN)

                signIn()

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

    private fun updateUI(user: FirebaseUser?, exception: Exception?) {
        if (exception != null) {
            showMessage(exception.message.toString())
        } else {
            showMessage(user.toString() + " " + getString(R.string.success))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Result returned from launching the Intent
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //Google Sign In was successful, autheticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle: " + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                //Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                showMessage("Google Sign In failed")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential: success")
                    val user = auth.currentUser
                    updateUI(user, null)
                } else {
                    Log.w(TAG, "signInWithCredential: failure")
                    updateUI(null, task.exception)
                }

            }
    }


    companion object {
        private const val TAG = "FirebaseActivity"
        private const val RC_SIGN_IN = 9001
    }
}