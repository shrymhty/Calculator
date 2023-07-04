package com.shrymhty.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shrymhty.calculator.ui.theme.CalculatorColors
import com.shrymhty.calculator.ui.theme.CalculatorTheme
import com.shrymhty.calculator.ui.theme.darkThemeColors
import com.shrymhty.calculator.ui.theme.lightThemeColors


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val previousState = viewModel.previousState
                val buttonSpacing = 10.dp
                val isDarkTheme = isSystemInDarkTheme()
                val colors = if (isDarkTheme) darkThemeColors else lightThemeColors
                CalculatorApp(
                    state = state,
                    previousState = previousState,
                    buttonSpacing = buttonSpacing,
                    colors = colors,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun CalculatorApp(
    state: CalculatorState,
    previousState: CalculatorState,
    buttonSpacing: Dp,
    colors: CalculatorColors,
    viewModel: CalculatorViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(
                text = previousState.num1 + (previousState.operation?.symbol?: "") + previousState.num2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 30.sp,
                color = colors.textColor,
                maxLines = 2)


            Text(
                text = state.num1 + (state.operation?.symbol ?: "") + state.num2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp, start = 32.dp, bottom = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 70.sp,
                color = colors.textColor,
                maxLines = 2
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier
                        .background(colors.topRowButtons)
                        .weight(1f)
                        .aspectRatio(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Clear)
                    }
                )
                CalculatorButton(
                    symbol = "DEL",
                    modifier = Modifier
                        .background(colors.topRowButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Delete)
                    }
                )

                CalculatorButton(
                    symbol = "%",
                    modifier = Modifier
                        .background(colors.topRowButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(
                            CalculatorActions.Operations(
                                CalculatorOperations.Percentage
                            )
                        )
                    }
                )

                CalculatorButton(
                    symbol = "/",
                    modifier = Modifier
                        .background(colors.operationButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(
                            CalculatorActions.Operations(
                                CalculatorOperations.Divide
                            )
                        )
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .weight(1f)
                        .aspectRatio(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(7))
                    }
                )
                CalculatorButton(
                    symbol = "8",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(8))
                    }
                )

                CalculatorButton(
                    symbol = "9",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(9))
                    }
                )

                CalculatorButton(
                    symbol = "x",
                    modifier = Modifier
                        .background(colors.operationButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(
                            CalculatorActions.Operations(
                                CalculatorOperations.Multiply
                            )
                        )
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .weight(1f)
                        .aspectRatio(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(4))
                    }
                )
                CalculatorButton(
                    symbol = "5",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(5))
                    }
                )

                CalculatorButton(
                    symbol = "6",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(6))
                    }
                )

                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier
                        .background(colors.operationButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(
                            CalculatorActions.Operations(
                                CalculatorOperations.Subtract
                            )
                        )
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .weight(1f)
                        .aspectRatio(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(1))
                    }
                )
                CalculatorButton(
                    symbol = "2",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(2))
                    }
                )

                CalculatorButton(
                    symbol = "3",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(3))
                    }
                )

                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier
                        .background(colors.operationButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(
                            CalculatorActions.Operations(
                                CalculatorOperations.Add
                            )
                        )
                    }
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .weight(2f)
                        .aspectRatio(2f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Number(0))
                    }
                )
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier
                        .background(colors.numberButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Decimal)
                    }
                )

                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier
                        .background(colors.operationButtons)
                        .aspectRatio(1f)
                        .weight(1f),
                    colors = colors,
                    onClick = {
                        viewModel.onAction(CalculatorActions.Calculate)
                    }
                )

            }
        }
    }

}
