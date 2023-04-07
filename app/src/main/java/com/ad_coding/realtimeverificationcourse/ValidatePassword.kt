package com.ad_coding.realtimeverificationcourse

class ValidatePassword {

    fun execute(password: String): PasswordValidationState {
        val validateSpecialCharacter = validateSpecialCharacter(password)
        val validateCapitalizedLetter = validateCapitalizedLetter(password)
        val validateMinimum = validateMinimum(password)

        val hasError = listOf(
            validateMinimum,
            validateCapitalizedLetter,
            validateSpecialCharacter
        ).all { it }

        return PasswordValidationState(
            hasMinimum = validateMinimum,
            hasSpecialCharacter = validateSpecialCharacter,
            hasCapitalizedLetter = validateCapitalizedLetter,
            successful = hasError
        )
    }

    private fun validateSpecialCharacter(password: String): Boolean =
        password.contains(Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]"))

    private fun validateCapitalizedLetter(password: String): Boolean =
        password.matches(Regex(".*[A-Z].*"))

    private fun validateMinimum(password: String): Boolean =
        password.matches(Regex(".{6,}"))
}