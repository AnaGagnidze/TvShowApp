package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.moviesapp.utils.InternetConnectionChecker
import com.example.moviesapp.utils.extensions.showError

class MainActivity : AppCompatActivity() {
    private lateinit var checkNetworkConnection: InternetConnectionChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callNetworkConnection()
    }

    private fun callNetworkConnection() {
        checkNetworkConnection = InternetConnectionChecker(application)
        checkNetworkConnection.observe(this) { isConnected ->
            if (!isConnected) {
                showError(getString(R.string.internet_connection_error_msg))
            }
        }

    }
}