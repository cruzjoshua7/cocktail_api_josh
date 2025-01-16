package com.verycool.cocktailapi.view.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.verycool.cocktailapi.view.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    loginAuth: FirebaseAuth) {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image placeholder
        Box(
            modifier = Modifier
                .size(122.dp)
                .background(Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("Image", color = Color.White)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Email TextField
        var emailText by remember { mutableStateOf("") }

        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = null
                )
            },
            value = emailText,
            onValueChange = { emailText = it },
            label = { Text("Email") },
            modifier = Modifier.padding(10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        var passwordText by remember { mutableStateOf("") }
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            value = passwordText,
            onValueChange = { passwordText = it },
            label = { Text("Password") },
            modifier = Modifier.padding(10.dp)

        )

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password Text
        Text(
            text = "Forgot Password?",
            color = Color.Blue,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* Add your onClick functionality */ }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Buttons (Login and Register)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            var enabledState by remember { mutableStateOf(true) }
            if (emailText.isEmpty() || passwordText.isEmpty()) {
                enabledState = false
            } else {
                enabledState = true
            }
            Button(
                onClick = {
                    verifyFirebaseUser(
                        emailText,
                        passwordText,
                        loginAuth,
                        context,
                        navController
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /* Add your onClick functionality */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Register")
            }
        }
    }
}

private fun verifyFirebaseUser(
    email: String,
    password: String,
    auth: FirebaseAuth,
    context: Context,
    navController: NavController
) {
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val user = auth.currentUser
            Toast.makeText(context, "Good Login by ${user?.email}", Toast.LENGTH_LONG).show()
            //navigate to home screen
            navController.navigate(Screen.CockTailListScreen.route)

        } else {
            Toast.makeText(context, "Bad Login: ${task.exception?.message}", Toast.LENGTH_LONG)
                .show()
        }
    }
}