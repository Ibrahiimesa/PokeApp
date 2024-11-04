package com.esa.poke.pokemon.presentation.poke_list.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.kmpalette.palette.graphics.Palette

@Composable
internal fun Palette?.paletteBackgroundColor(): State<Color> {
    val defaultBackground = MaterialTheme.colorScheme.background
    return remember(this) {
        derivedStateOf {
            val rgb = this?.dominantSwatch?.rgb
            if (rgb != null) {
                Color(rgb)
            } else {
                defaultBackground
            }
        }
    }
}