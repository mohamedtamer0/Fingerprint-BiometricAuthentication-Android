# Fingerprint-BiometricAuthentication-Android


## Preview
<div align="center">
<br/>
  


Authentication Succeeded            |  Authentication Failed           |  Error
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/51374446/152626047-b19ac70e-2162-4049-ab42-8a179d9a3674.gif" width="200" height="400" />  | <img src="https://user-images.githubusercontent.com/51374446/152626046-bbd2a366-444d-489e-8ca8-9623ece6ae16.gif" width="200" height="400" /> | <img src="https://user-images.githubusercontent.com/51374446/152626048-56351bf3-8071-42f0-957c-b1c7efb76bcc.gif" width="200" height="400" />

 </div>
  
  ```groovy
implementation "androidx.biometric:biometric:1.1.0"
```

  ```kotlin
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
```


  ```kotlin
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
```
