package com.example.fingerprint_facebiometricauthentication_android

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.fingerprint_facebiometricauthentication_android.databinding.ActivityMainBinding
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    binding.tvAuthStatus.text = "Error " + errorCode
                    binding.tvAuthStatus.setTextColor(Color.parseColor("#FF0000"))
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    binding.tvAuthStatus.text = "Authentication succeeded"
                    binding.tvAuthStatus.setTextColor(Color.parseColor("#00FF00"))
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    binding.tvAuthStatus.text = "Authentication failed"
                    binding.tvAuthStatus.setTextColor(Color.parseColor("#9C27B0"))
                }
            })


        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Log in using fingerPrint")
            .setNegativeButtonText("Cancel")
            .build()

        binding.btnAuth.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
}