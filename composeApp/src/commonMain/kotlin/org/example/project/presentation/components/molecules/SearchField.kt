package org.example.project.presentation.components.molecules

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text("Szukaj przepisu...", maxLines = 1, overflow = TextOverflow.Ellipsis) },
        textStyle = MaterialTheme.typography.bodyLarge,
        leadingIcon = { Text("🔍") },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Text("❌")
                }
            }
        },
        singleLine = true,
        maxLines = 1,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    )
}