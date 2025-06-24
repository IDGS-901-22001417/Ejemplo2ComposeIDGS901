package org.utl.idgs.ejemplo2composeidgs901

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Calculadora() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var operacionSeleccionada by remember { mutableStateOf("") }

    val operaciones = listOf(
        "suma" to "Suma",
        "resta" to "Resta",
        "multiplicacion" to "Multiplicación",
        "division" to "División"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Calculadora",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Selecciona la operación:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Column(modifier = Modifier.selectableGroup()) {
            operaciones.forEach { (valor, texto) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (operacionSeleccionada == valor),
                            onClick = { operacionSeleccionada = valor },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (operacionSeleccionada == valor),
                        onClick = null
                    )
                    Text(
                        text = texto,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                val n1 = num1.toDoubleOrNull()
                val n2 = num2.toDoubleOrNull()

                resultado = if (n1 != null && n2 != null) {
                    when (operacionSeleccionada) {
                        "suma" -> "Resultado: ${n1 + n2}"
                        "resta" -> "Resultado: ${n1 - n2}"
                        "multiplicacion" -> "Resultado: ${n1 * n2}"
                        "division" -> {
                            if (n2 != 0.0) {
                                "Resultado: ${n1 / n2}"
                            } else {
                                "Error: No se puede dividir entre cero"
                            }
                        }
                        else -> "Operación no válida"
                    }
                } else {
                    "Ingrese números válidos"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Calcular")
        }

        if (resultado.isNotEmpty()) {
            Text(
                text = resultado,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}