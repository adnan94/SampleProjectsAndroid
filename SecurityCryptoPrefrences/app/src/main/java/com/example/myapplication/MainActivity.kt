package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var save: Button
    lateinit var read: Button
    val FILE_NAME = "some_shared_prefs"

    private val masterKeyAlias: String by lazy {
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    private val sharedPreferences: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            FILE_NAME,
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        save = findViewById(R.id.save)
        read = findViewById(R.id.read)

        save.setOnClickListener {
            saveSecrets(editText.text.toString())
        }

        read.setOnClickListener {
            val secret: String? = sharedPreferences.getString("SECRET_KEY", null)
            secret?.let {
                editText.setText(it);
            }
        }

    }

    private fun saveSecrets(secret: String) {
        with(sharedPreferences.edit()) {
            putString("SECRET_KEY", secret)
            apply()
            editText.setText("")
        }
    }


}