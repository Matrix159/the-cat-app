package com.matrix159.thecatapp.core.ui.theme.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.matrix159.thecatapp.core.ui.theme.CatAppPreviews
import com.matrix159.thecatapp.core.ui.theme.CatAppTheme

/**
 * A centered Loading indicator that displays a circular progress indicator.
 * @param modifier Modifier that will be applied to the layout.
 */
@Composable
fun LoadingIndicator(
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier) {
    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
  }
}

@CatAppPreviews
@Composable
fun LoadingIndicatorPreview() {
  CatAppTheme {
    LoadingIndicator()
  }
}