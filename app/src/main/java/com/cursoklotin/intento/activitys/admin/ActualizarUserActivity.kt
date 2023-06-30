package com.cursoklotin.intento.activitys.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cursoklotin.intento.R
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cursoklotin.intento.bd.services.AdminQueryHelper
import com.cursoklotin.intento.bd.services.UserQueryHelper
import com.cursoklotin.intento.UserData
import com.cursoklotin.intento.utils.DateTimeUtils.getCurrentDateTime
import android.database.sqlite.SQLiteDatabase
import com.cursoklotin.intento.bd.DatabaseHelper
import com.cursoklotin.intento.utils.DateTimeUtils


class ActualizarUserActivity : AppCompatActivity() {
    private lateinit var editTextNombres: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var editTextSexo: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextNumeroCuenta: EditText
    private lateinit var editTextBanco: EditText
    private lateinit var editTextDni: EditText
    private lateinit var editTextFechaNacimiento: EditText
    private lateinit var editTextJefe: EditText
    private lateinit var editTextDireccion: EditText
    private lateinit var editTextDistrito: EditText
    private lateinit var editTextCondicion: EditText
    private lateinit var editTextCargo: EditText
    private lateinit var editTextRol: EditText
    private lateinit var editTextEstadoCuenta: EditText
    private lateinit var editTextImagenPerfil: EditText
    private lateinit var buttonActualizar: Button

    private lateinit var adminQueryHelper: AdminQueryHelper
    private lateinit var userQueryHelper: UserQueryHelper
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var usuario: UserData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_actualizar_user)
//
//        dbHelper = DatabaseHelper(this)
//        db = dbHelper.writableDatabase
//
//        adminQueryHelper = AdminQueryHelper(db)
//        userQueryHelper = UserQueryHelper(db)
//
//
//        // Obtener el ID del usuario desde el intent
//        //val userId = intent.getIntExtra("userId", -1)
//        val userId = 3
//        // Verificar si se proporcionó un ID válido
//        if (userId != -1) {
//            // Realizar consulta para obtener los datos del usuario por su ID
//            usuario = userQueryHelper.getUserById(userId) ?: throw IllegalArgumentException("No se encontró el usuario con el ID proporcionado")
//
//            // Verificar si el usuario no es nulo antes de asignar los datos
//            if (usuario != null) {
//                // Asignar los datos del usuario a los campos de texto correspondientes
//                asignarDatosUsuario(usuario)
//            } else {
//                // Mostrar mensaje de error si el usuario es nulo
//                Toast.makeText(this, "No se encontró el usuario con el ID proporcionado", Toast.LENGTH_SHORT).show()
//                finish()
//            }
//        } else {
//            // Mostrar mensaje de error si no se proporcionó un ID válido
//            Toast.makeText(this, "ID de usuario inválido", Toast.LENGTH_SHORT).show()
//            finish()
//        }
//
//
//        // Configurar el botón de actualización
//        buttonActualizar = findViewById(R.id.buttonActualizarUsuario)
//        buttonActualizar.setOnClickListener {
//            // Obtener los nuevos valores de los campos de texto
//            val nuevoNombres = editTextNombres.text.toString()
//            val nuevoCorreo = editTextCorreo.text.toString()
//            val nuevoContrasena = editTextContrasena.text.toString()
//            val nuevoSexo = editTextSexo.text.toString()
//            val nuevoTelefono = editTextTelefono.text.toString()
//            val nuevoNumeroCuenta = editTextNumeroCuenta.text.toString()
//            val nuevoBanco = editTextBanco.text.toString()
//            val nuevoDni = editTextDni.text.toString()
//            val nuevaFechaNacimiento = editTextFechaNacimiento.text.toString()
//            val nuevoJefe = editTextJefe.text.toString()
//            val nuevaDireccion = editTextDireccion.text.toString()
//            val nuevoDistrito = editTextDistrito.text.toString()
//            val nuevaCondicion = editTextCondicion.text.toString()
//            val nuevoCargo = editTextCargo.text.toString()
//            val nuevoRol = editTextRol.text.toString()
//            val nuevoEstadoCuenta = editTextEstadoCuenta.text.toString()
//            val nuevaImagenPerfil = editTextImagenPerfil.text.toString()
//
//            // Actualizar los datos del usuario en la base de datos
//            val exito = adminQueryHelper.actualizarUsuarioPorId(
//                usuario.id,
//                nuevoNombres,
//                nuevoCorreo,
//                nuevoContrasena,
//                nuevoSexo,
//                nuevoTelefono,
//                nuevoNumeroCuenta,
//                nuevoBanco,
//                nuevoDni,
//                nuevaFechaNacimiento,
//                nuevoJefe,
//                nuevaDireccion,
//                nuevoDistrito,
//                nuevaCondicion,
//                nuevoCargo,
//                nuevoRol,
//                usuario.fechaCreacion,
//                DateTimeUtils.getCurrentDateTime(),
//                nuevoEstadoCuenta,
//                nuevaImagenPerfil
//            )
//
//            // Mostrar mensaje de éxito o error según el resultado de la actualización
//            if (exito) {
//                Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show()
//                finish()
//            } else {
//                Toast.makeText(this, "Error al actualizar el usuario", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    private fun asignarDatosUsuario(usuario: UserData?) {
        editTextNombres = findViewById(R.id.editTextNombres)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        editTextContrasena = findViewById(R.id.editTextContrasena)
        editTextSexo = findViewById(R.id.editTextSexo)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        editTextNumeroCuenta = findViewById(R.id.editTextNumeroCuenta)
        editTextBanco = findViewById(R.id.editTextBanco)
        editTextDni = findViewById(R.id.editTextDni)
        editTextFechaNacimiento = findViewById(R.id.editTextFechaNacimiento)
        editTextJefe = findViewById(R.id.editTextJefe)
        editTextDireccion = findViewById(R.id.editTextDireccion)
        editTextDistrito = findViewById(R.id.editTextDistrito)
        editTextCondicion = findViewById(R.id.editTextCondicion)
        editTextCargo = findViewById(R.id.editTextCargo)
        editTextRol = findViewById(R.id.editTextRol)
        editTextEstadoCuenta = findViewById(R.id.editTextEstadoCuenta)
        editTextImagenPerfil = findViewById(R.id.editTextImagenPerfil)

        editTextNombres.setText(usuario?.nombres)
        editTextCorreo.setText(usuario?.correo)
        editTextContrasena.setText(usuario?.contrasena)
        editTextSexo.setText(usuario?.sexo)
        editTextTelefono.setText(usuario?.telefono)
        editTextNumeroCuenta.setText(usuario?.numeroCuenta)
        editTextBanco.setText(usuario?.banco)
        editTextDni.setText(usuario?.dni)
        editTextFechaNacimiento.setText(usuario?.fechaNacimiento)
        editTextJefe.setText(usuario?.jefe)
        editTextDireccion.setText(usuario?.direccion)
        editTextDistrito.setText(usuario?.distrito)
        editTextCondicion.setText(usuario?.condicion)
        editTextCargo.setText(usuario?.cargo)
        editTextRol.setText(usuario?.rol)
        editTextEstadoCuenta.setText(usuario?.estadoCuenta)
        editTextImagenPerfil.setText(usuario?.imagenPerfil)
    }
}
