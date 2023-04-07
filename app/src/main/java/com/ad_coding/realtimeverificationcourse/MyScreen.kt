@file:OptIn(ExperimentalMaterial3Api::class)

package com.ad_coding.realtimeverificationcourse

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyScreen(
    viewModel: MyScreenViewModel = viewModel()
) {
    val passwordError by viewModel.passwordError.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = viewModel::changePassword,
            visualTransformation = PasswordVisualTransformation(),
            isError = !passwordError.successful
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            ConditionRow(condition = "Minimum 6 characters", check = passwordError.hasMinimum)
            ConditionRow(condition = "Has special character", check = passwordError.hasSpecialCharacter)
            ConditionRow(condition = "Has capitalized letter", check = passwordError.hasCapitalizedLetter)
        }
    }
}

@Composable
fun ConditionRow(
    condition: String,
    check: Boolean
) {
    val color by animateColorAsState(
        targetValue = if (check) Color.Green else Color.Red,
        label = "text color"
    )

    val icon = if (check) {
        Icons.Rounded.Check
    } else {
        Icons.Rounded.Close
    }

    Row {
        Icon(
            imageVector = icon,
            tint = color,
            contentDescription = "status icon"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = condition,
            color = color
        )
    }
}