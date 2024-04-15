package com.matrix159.thecatapp.core.ui.theme.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.matrix159.thecatapp.core.ui.R
import com.matrix159.thecatapp.core.ui.theme.CatAppPreviews
import com.matrix159.thecatapp.core.ui.theme.CatAppTheme

/**
 * Generic error indicator.
 * @param modifier The modifier to apply to the text.
 */
@Composable
fun ErrorIndicator(
  modifier: Modifier = Modifier,
) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Icon(
      painter = painterResource(id = R.drawable.baseline_error_outline_24),
      contentDescription = null,
      tint = MaterialTheme.colorScheme.error,
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.s_padding)))
    Text(
      text = stringResource(R.string.error),
      style = MaterialTheme.typography.titleLarge,
      modifier = modifier
    )
  }
}

@CatAppPreviews
@Composable
fun ErrorIndicatorPreview() {
  CatAppTheme {
    Surface {
      ErrorIndicator()
    }
  }
}