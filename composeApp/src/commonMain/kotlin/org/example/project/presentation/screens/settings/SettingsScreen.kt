package org.example.project.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.domain.model.UserProfile
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    userProfile: UserProfile,
    onProfileChange: (UserProfile) -> Unit
) {
    var profile by remember(userProfile) { mutableStateOf(userProfile) }
    var isEditingProfile by remember { mutableStateOf(false) }
    var showBugDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState())) {
            // Header removed intentionally — keep content below status bar

            ProfileSection(profile = profile, onEditClick = { isEditingProfile = true }, modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            BugReportSection(onReportClick = { showBugDialog = true }, modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            AppInfoSection(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    if (isEditingProfile) {
        EditProfileDialog(
            profile = profile,
            onProfileChange = {
                profile = it
                onProfileChange(it)
            },
            onDismiss = { isEditingProfile = false }
        )
    }

    if (showBugDialog) {
        BugReportDialog(
            onSend = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Dziękujemy za zgłoszenie błędu")
                }
            },
            onDismiss = { showBugDialog = false }
        )
    }
}

@Composable
private fun ProfileSection(profile: UserProfile, onEditClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Profil", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
            TextButton(onClick = onEditClick) { Text("✏️") }
        }

        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.size(80.dp).background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), shape = CircleShape), contentAlignment = Alignment.Center) {
                    Text(text = "👤", fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                }
                Text(text = profile.name, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.Center)
                Text(text = profile.email, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
private fun BugReportSection(onReportClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = "Wsparcie", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
        Button(onClick = onReportClick, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer, contentColor = MaterialTheme.colorScheme.error)) {
            Text(text = "📧 Zgłoś błąd", style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
private fun AppInfoSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = "O aplikacji", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                InfoRow(label = "Nazwa aplikacji", value = "Mini-Recipe App")
                HorizontalDivider()
                InfoRow(label = "Wersja", value = "1.0.0")
                HorizontalDivider()
                InfoRow(label = "Autor", value = "Your Company")
                HorizontalDivider()
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "Opis", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
                    Text(text = "Mini-Recipe App to prosta aplikacja do przeglądania przepisów, wyszukiwania dań i zarządzania listą zakupów.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), textAlign = TextAlign.Justify)
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
        Text(text = value, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun EditProfileDialog(profile: UserProfile, onProfileChange: (UserProfile) -> Unit, onDismiss: () -> Unit) {
    var name by remember(profile) { mutableStateOf(profile.name) }
    var email by remember(profile) { mutableStateOf(profile.email) }
    val isNameValid = name.isNotBlank()
    val isEmailValid = email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edytuj profil") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Imię") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = !isNameValid
                )
                if (!isNameValid) {
                    Text(text = "Imię nie może być puste", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = email.isNotBlank() && !isEmailValid
                )
                if (email.isBlank()) {
                    Text(text = "Email nie może być pusty", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                } else if (!isEmailValid) {
                    Text(text = "Podaj poprawny adres email", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onProfileChange(profile.copy(name = name, email = email))
                    onDismiss()
                },
                enabled = isNameValid && isEmailValid
            ) { Text("Zapisz") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Anuluj") } }
    )
}

@Composable
private fun BugReportDialog(onSend: () -> Unit, onDismiss: () -> Unit) {
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Zgłoś błąd") },
        text = {
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Opis problemu") }, modifier = Modifier.fillMaxWidth(), minLines = 4)
        },
        confirmButton = {
            Button(
                onClick = {
                    onSend()
                    onDismiss()
                },
                enabled = description.isNotBlank()
            ) { Text("Wyślij") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Anuluj") } }
    )
}