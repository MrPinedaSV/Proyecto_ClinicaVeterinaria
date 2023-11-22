package edu.udb.splashscreenloginsqlitekotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen) // Establece el diseño de la pantalla de bienvenida

        val rotatingImage = findViewById<ImageView>(R.id.rotatingImage) // Encuentra la ImageView en el diseño con ID "rotatingImage"
        val rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation) // Carga la animación de rotación definida en R.anim.rotate_animation

        rotatingImage.startAnimation(rotation) // Aplica la animación de rotación a la ImageView

        Handler().postDelayed({
            // Iniciar la actividad principal aquí
            val intent = Intent(this, LoginActivity::class.java) // Crea un Intent para iniciar la actividad principal (MainActivity)
            startActivity(intent) // Inicia la actividad principal
            finish() // Finaliza la actividad de bienvenida
        }, 5000) // 5000 ms (5 segundos) de duración de la pantalla de bienvenida
    }
}

