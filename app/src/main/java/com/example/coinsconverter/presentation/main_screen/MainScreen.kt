package com.example.coinsconverter.presentation.main_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinsconverter.R

@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit
) {

    val listOfSymbol = listOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        ".",
        "0",
        "C"
    )

    // Get Current context for current screen
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = "Exchange Rate",
            fontFamily = FontFamily.Default,
            fontSize = 33.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5E8AE0)
        )

        Box(contentAlignment = Alignment.Center) {

            Column {

                Text(
                    text = "You Pay",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Start
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {

                        CurrencyRow(
                            modifier = Modifier.fillMaxWidth(),
                            currencyCode = state.fromCurrencyCode,
                            currencyName = state.currencyRate[state.fromCurrencyCode]?.name ?: "",
                            onDropDownIconClicked = {}
                        )

                        Text(
                            text = state.fromCurrencyValue,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = {
                                    onEvent(MainScreenEvent.FromCurrencySelect)
                                }),
                            color = if (state.selection == SelectionState.FROM) Color(0xFF5E8AE0) else Color.Black
                        )

                    }

                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "You Receive",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Start
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {


                        Text(
                            text = state.toCurrencyValue,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (state.selection == SelectionState.TO) Color(0xFF5E8AE0) else Color.Black,
                            modifier = Modifier.clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = {
                                    onEvent(MainScreenEvent.ToCurrencySelect)
                                }
                            )

                        )

                        CurrencyRow(
                            modifier = Modifier.fillMaxWidth(),
                            currencyCode = state.toCurrencyCode,
                            currencyName = state.currencyRate[state.toCurrencyCode]?.name ?: "",
                            onDropDownIconClicked = {}
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .clip(CircleShape)
                    .clickable {
                        onEvent(MainScreenEvent.SwapIconClicked)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.exchange),
                    contentDescription = "Exchange Icon",
                    modifier = Modifier
                        .size(55.dp)
                        .padding(8.dp),
                    tint = Color(0xFF20C997)
                )
            }

        }

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 32.dp),
            columns = GridCells.Fixed(3)
        ) {
            items(listOfSymbol) { eachItem ->
                CalculatorButton(
                    symbol = eachItem,
                    backgroundColor = if (eachItem == "C") Color(0xFF5E8AE0) else MaterialTheme.colorScheme.surfaceVariant,
                    onClick = {
                        onEvent(MainScreenEvent.CalculatorButtonItemClicked(eachItem))
                    },
                    modifier = Modifier.aspectRatio(1f)
                )
            }
        }

    }
}

@Composable
fun CurrencyRow(
    modifier: Modifier = Modifier,
    currencyCode: String,
    currencyName: String,
    onDropDownIconClicked: () -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = currencyCode, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        IconButton(onClick = onDropDownIconClicked) {
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop Down Icon")
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(text = currencyName, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}


@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    symbol: String,
    backgroundColor: Color,
    onClick: (String) -> Unit
) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(CircleShape)
            .background(color = backgroundColor)
            .clickable { onClick(symbol) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 32.sp,
            color = if (symbol == "C") Color.White else Color.Black,
            fontWeight = if (symbol == "C") FontWeight.Bold else FontWeight.Normal
        )
    }
}