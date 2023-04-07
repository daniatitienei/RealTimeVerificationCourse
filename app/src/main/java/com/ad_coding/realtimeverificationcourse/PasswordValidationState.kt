package com.ad_coding.realtimeverificationcourse

data class PasswordValidationState(
    val hasMinimum: Boolean = false,
    val hasCapitalizedLetter: Boolean = false,
    val hasSpecialCharacter: Boolean = false,
    val successful: Boolean = false
)
