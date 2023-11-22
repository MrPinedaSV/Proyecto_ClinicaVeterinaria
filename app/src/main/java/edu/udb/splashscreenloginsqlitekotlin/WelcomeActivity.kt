package edu.udb.splashscreenloginsqlitekotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Toast.makeText(this@WelcomeActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
    }
}