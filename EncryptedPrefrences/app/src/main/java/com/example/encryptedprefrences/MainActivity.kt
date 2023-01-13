package com.example.encryptedprefrences

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import com.example.encryptedprefrences.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val Context.dataStore by dataStore(
        fileName = "user-settings.json",
        serializer = UserSettingsSerializer(CryptoManager())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                dataStore.updateData {
                    UserSettings(
                        username = binding.etUserName.text.toString()
                    )
                }
            }
        }

        binding.btnRead.setOnClickListener {
            lifecycleScope.launch {
                var userSettings = dataStore.data.first()
                binding.tvReadValue.text = " Username ${userSettings?.username}"
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}