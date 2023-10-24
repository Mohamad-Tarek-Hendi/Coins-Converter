package com.example.coinsconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coinsconverter.presentation.main_screen.MainScreen
import com.example.coinsconverter.presentation.main_screen.MainScreenViewModel
import com.example.coinsconverter.presentation.theme.CoinsConverterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinsConverterTheme {
                val viewModel: MainScreenViewModel = hiltViewModel()
                Surface {
                    MainScreen(state = viewModel.state, onEvent = viewModel::onEvent)
                }
            }
        }
    }
}