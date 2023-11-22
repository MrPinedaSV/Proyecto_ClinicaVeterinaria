package edu.udb.splashscreenloginsqlitekotlin

// MainActivity.kt
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import edu.udb.pacientesqlite.Paciente


class MainActivityPaciente : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelperPaciente
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_paciente)

        dbHelper = DatabaseHelperPaciente(this)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextDireccion = findViewById<EditText>(R.id.editTextDireccion)
        val editTextDui = findViewById<EditText>(R.id.editTextDui)
        val editTextMascota = findViewById<EditText>(R.id.editTextMascota)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)

        btnAgregar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val direccion = editTextDireccion.text.toString()
            val dui = editTextDui.text.toString()
            val mascota = editTextMascota.text.toString()

            val paciente = Paciente(nombre = nombre, direccion = direccion, dui = dui, mascota = mascota)
            dbHelper.addPaciente(paciente)
            actualizarListaPacientes()

            // Limpiar los campos de texto
            editTextNombre.text.clear()
            editTextDireccion.text.clear()
            editTextDui.text.clear()
            editTextMascota.text.clear()
        }

        val listViewPacientes = findViewById<ListView>(R.id.listViewPacientes)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listViewPacientes.adapter = adapter

        listViewPacientes.setOnItemClickListener { _, _, position, _ ->
            val paciente = dbHelper.getAllPacientes()[position]

            val opciones = arrayOf("Editar", "Eliminar")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Selecciona una opción")
            builder.setItems(opciones) { _, which ->
                when (which) {
                    0 -> editarPaciente(paciente)
                    1 -> eliminarPaciente(paciente.id)
                }
            }
            builder.show()
        }
        actualizarListaPacientes()
    }

    private fun actualizarListaPacientes() {
        val pacientes = dbHelper.getAllPacientes().map { paciente ->
            "ID: ${paciente.id}\n" +
                    "Nombre: ${paciente.nombre}\n" +
                    "Dirección: ${paciente.direccion}\n" +
                    "DUI: ${paciente.dui}\n" +
                    "Mascota: ${paciente.mascota}"
        }
        adapter.clear()
        adapter.addAll(pacientes)
        adapter.notifyDataSetChanged()
    }

    private fun editarPaciente(paciente: Paciente) {
        val intent = Intent(this, EditarActivityPaciente::class.java)
        intent.putExtra("paciente", paciente)
        editarPacienteLauncher.launch(intent)
        //startActivity(intent)
        //actualizarListaPacientes()
    }

    private fun eliminarPaciente(pacienteId: Long) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmar eliminación")
        builder.setMessage("¿Estás seguro de que quieres eliminar este paciente?")
        builder.setPositiveButton("Sí") { _, _ ->
            dbHelper.deletePaciente(pacienteId)
            actualizarListaPacientes()
            Toast.makeText(this, "Paciente eliminado", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.show()
    }

    private val editarPacienteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Si el resultado es OK, actualiza la lista
            actualizarListaPacientes()
        }
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

