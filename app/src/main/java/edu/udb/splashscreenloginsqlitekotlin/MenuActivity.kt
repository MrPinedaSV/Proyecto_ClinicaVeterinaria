package edu.udb.splashscreenloginsqlitekotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuopciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

         if (id == R.id.telefono) {
            Toast.makeText(this, getString(R.string.mensaje_icono_telefono), Toast.LENGTH_LONG).show();
            val intent = Intent(this, Opcion1Activity::class.java)
            startActivity(intent)
        }
        if (id == R.id.camara) {
            Toast.makeText(this, getString(R.string.mensaje_icono_camara), Toast.LENGTH_LONG).show();
            val intent = Intent(this, Opcion2Activity::class.java)
            startActivity(intent)
        }

        if (id == R.id.opcion1) {
            Toast.makeText(this, getString(R.string.mensaje_1), Toast.LENGTH_LONG).show();
            val intent = Intent(this, Opcion4Activity::class.java)
            startActivity(intent)
        }
        if (id == R.id.opcion2) {
            Toast.makeText(this, getString(R.string.mensaje_2), Toast.LENGTH_LONG).show();
            val intent = Intent(this, Opcion2Activity::class.java)
            startActivity(intent)
        }
        if (id == R.id.opcion3) {
            Toast.makeText(this, getString(R.string.mensaje_3), Toast.LENGTH_LONG).show();
            val intent = Intent(this, MainActivityPaciente::class.java)
            startActivity(intent)
        }
        if (id == R.id.opcion4) {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}