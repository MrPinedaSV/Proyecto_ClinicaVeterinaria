package edu.udb.splashscreenloginsqlitekotlin

// EditarActivity.kt
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.udb.pacientesqlite.Paciente
import edu.udb.splashscreenloginsqlitekotlin.R

class EditarActivityPaciente : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelperPaciente
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDireccion: EditText
    private lateinit var editTextDui: EditText
    private lateinit var editTextMascota: EditText
    private lateinit var btnGuardarCambios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_paciente)

        dbHelper = DatabaseHelperPaciente(this)

        editTextNombre = findViewById(R.id.editTextNombreEditar)
        editTextDireccion = findViewById(R.id.editTextDireccionEditar)
        editTextDui = findViewById(R.id.editTextDuiEditar)
        editTextMascota = findViewById(R.id.editTextMascotaEditar)
        btnGuardarCambios = findViewById(R.id.btnGuardarCambios)

        // Obtener el paciente pasado desde MainActivity
        val paciente = intent.getSerializableExtra("paciente") as? Paciente

        // Verificar si el paciente es nulo
        if (paciente == null) {
            // Manejar el caso de error o finalizar la actividad
            Toast.makeText(this, "Error: No se pudo obtener la información del paciente", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Mostrar los datos actuales del paciente en los EditText
        editTextNombre.setText(paciente.nombre)
        editTextDireccion.setText(paciente.direccion)
        editTextDui.setText(paciente.dui)
        editTextMascota.setText(paciente.mascota)

        // Configurar el botón para guardar cambios
        btnGuardarCambios.setOnClickListener {
            // Obtener los nuevos datos del paciente
            val nuevoNombre = editTextNombre.text.toString()
            val nuevaDireccion = editTextDireccion.text.toString()
            val nuevoDui = editTextDui.text.toString()
            val nuevaMascota = editTextMascota.text.toString()

            // Actualizar el paciente en la base de datos
            paciente.nombre = nuevoNombre
            paciente.direccion = nuevaDireccion
            paciente.dui = nuevoDui
            paciente.mascota = nuevaMascota

            dbHelper.updatePaciente(paciente)

            // Establecer el resultado para informar a MainActivity que se realizaron cambios
            setResult(RESULT_OK)

            // Cerrar la actividad después de guardar cambios
            finish()
        }
    }
}
