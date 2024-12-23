@file:Suppress("DEPRECATION")

package com.alz19.novelis.ui.login

import LoginScreen
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.alz19.novelis.MainActivity
import com.alz19.novelis.R
import com.alz19.novelis.ui.theme.NovelisTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    // Menyimpan instance launcher untuk sign-in
    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        handleSignInResult(task)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Konfigurasi Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // Pastikan ID klien web Anda benar
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Set konten menggunakan Compose
        setContent {
            NovelisTheme {
                LoginScreen(
                    onGoogleSignInClicked = {
                        // Menggunakan launcher untuk sign-in Google
                        val signInIntent = googleSignInClient.signInIntent
                        googleSignInLauncher.launch(signInIntent)
                    }
                )
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        lifecycleScope.launch {
            try {
                val account = completedTask.getResult(Exception::class.java)
                val googleIdToken = account?.idToken

                if (googleIdToken != null) {
                    // Kirim token ke Firebase Authentication
                    val credential = GoogleAuthProvider.getCredential(googleIdToken, null)
                    firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener { authTask ->
                            if (authTask.isSuccessful) {
                                val user = firebaseAuth.currentUser
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login berhasil: ${user?.displayName}",
                                    Toast.LENGTH_LONG
                                ).show()

                                // Navigasi ke MainActivity
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Log.e("LoginActivity", "Firebase login gagal", authTask.exception)
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Firebase login gagal: ${authTask.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                } else {
                    throw Exception("Token Google ID kosong.")
                }
            } catch (e: Exception) {
                Log.e("LoginActivity", "Login gagal", e)
                Toast.makeText(this@LoginActivity, "Login gagal: ${e.message}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
