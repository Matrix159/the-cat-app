package com.matrix159.thecatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.matrix159.thecatapp.core.ui.theme.CatAppTheme
import com.matrix159.thecatapp.ui.CatApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CatAppTheme {
        CatApp()
      }
    }
  }
}
