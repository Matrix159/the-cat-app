package com.matrix159.thecatapp.core.ui.theme

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark

/**
 * Application compose preview annotation that covers most cases we'd like to preview.
 */
@PreviewLightDark
@DevicePreviews
@PreviewFontScale
annotation class CatAppPreviews

/**
 * Multipreview annotation that represents various device sizes. Add this annotation to a composable
 * to render various devices.
 */
@Preview(name = "Phone", device = Devices.PHONE)
@Preview(name = "Phone - Landscape", device = "spec:width = 411dp, height = 891dp, orientation = landscape, dpi = 420")
@Preview(name = "Unfolded Foldable", device = Devices.FOLDABLE)
@Preview(name = "Tablet", device = Devices.TABLET)
@Preview(name = "Desktop", device = Devices.DESKTOP)
annotation class DevicePreviews
