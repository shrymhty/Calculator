package com.shrymhty.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
    var previousState by mutableStateOf(CalculatorState())

    fun onAction(actions: CalculatorActions) {
        when (actions) {
            is CalculatorActions.Number -> enterNumber(actions.number)
            is CalculatorActions.Operations -> enterOperation(actions.operation)
            is CalculatorActions.Calculate -> performCalculation()
            is CalculatorActions.Clear -> {state = CalculatorState()
                                          previousState = CalculatorState()}
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Delete -> performDelete()
        }
    }

    private fun performDelete() {
        when {
            state.num2.isNotBlank() -> state = state.copy(num2 = state.num2.dropLast(1))
            state.operation != null -> state = state.copy(operation = null)
            state.num1.isNotBlank() -> state = state.copy(num1 = state.num1.dropLast(1))
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.num1.contains(".") && state.num1.isNotBlank()) {
            state = state.copy(num1 = state.num1 + ".")
            return
        }

        if (!state.num2.contains(".") && state.num2.isNotBlank()) {
            state = state.copy(num2 = state.num2 + ".")
        }
    }

    private fun performCalculation() {
        val number1 = state.num1.toDoubleOrNull()
        val number2 = state.num2.toDoubleOrNull()
        if (number1 != null) {
            val result = when (state.operation) {
                is CalculatorOperations.Add -> number1 + (number2 ?: 0.0)
                is CalculatorOperations.Subtract -> number1 - (number2 ?: 0.0)
                is CalculatorOperations.Multiply -> number1 * (number2 ?: 1.0)
                is CalculatorOperations.Divide -> number1 / (number2 ?: 1.0)
                is CalculatorOperations.Percentage -> {
                    if (number2 != null) {
                        number1 * number2 / 100
                    } else {
                        number1 * 0.01
                    }
                }
                null -> return
            }
            previousState = previousState.copy(num1 = state.num1,
                                               num2 = state.num2,
                                               operation = state.operation)

            state = state.copy(num1 = result.toString().take(15),
                               num2 = "",
                               operation = null)
        }

    }

    private fun enterOperation(operation: CalculatorOperations) {
        if (state.num1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if(state.num1.length >= MAX_NUM_LENGTH)
                return
            state = state.copy(num1 = state.num1 + number)
            return
        }

        if(state.num2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(num2 = state.num2 + number)
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}