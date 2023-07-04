package com.shrymhty.calculator

data class CalculatorState(
    val num1 : String = "",
    val num2 : String = "",
    val operation : CalculatorOperations? = null
)
