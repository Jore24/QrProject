package com.cursoklotin.intento.activitys.auth
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import com.cursoklotin.intento.R
import com.cursoklotin.intento.managers.UserManager
import com.cursoklotin.intento.bd.DatabaseHelper
import com.cursoklotin.intento.bd.services.AuthQueryHelper
import com.cursoklotin.intento.activitys.admin.HomeAdminActivity
import com.cursoklotin.intento.bd.services.EmployeQueryHelper


class LoginActivity : AppCompatActivity() {
    private lateinit var userManager: UserManager
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var employeQueryHelper: EmployeQueryHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        userManager = UserManager.getInstance(applicationContext)
        employeQueryHelper = EmployeQueryHelper(this)

        // Inicializar vistas
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        // Configurar click listener para el botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val databaseHelper = DatabaseHelper(this)
            val db: SQLiteDatabase = databaseHelper.writableDatabase

            val authQueryHelper = AuthQueryHelper(db)
            val (empleadoId, rol) = authQueryHelper.login(email, password)

            val empleadoData = employeQueryHelper.getEmployeById(empleadoId)

            if (empleadoId != -1) {
                // El inicio de sesión fue exitoso, pasar el ID del usuario a la siguiente actividad
                userManager.empleadoId = empleadoId //verificar que hay en el manager
                userManager.empleadoData = empleadoData //setiar el empleado en el manager

                //rol es int
                if (rol.toInt() == 2) {
                    val intent = Intent(this, HomeAdminActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                finish()
            } else {
                // Las credenciales de inicio de sesión son inválidas, mostrar un mensaje de error
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar click listener para el botón de cerrar sesión
    }

    //funciones


}