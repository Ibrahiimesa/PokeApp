package com.esa.poke.pokemon.presentation.poke_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esa.poke.pokemon.domain.Pokemon
import com.esa.poke.pokemon.presentation.models.PokeUi
import com.esa.poke.pokemon.presentation.models.toPokeUi
import com.esa.poke.ui.theme.PokeTheme
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun PokemonListItem(
    pokeUi: PokeUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backgroundColor by palette.paletteBackgroundColor()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(16.dp)) // Apply rounded corners with a 16.dp radius
            .background(backgroundColor)
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            GlideImage(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
                    .size(120.dp),
                imageModel = { pokeUi.imageUrl },
                imageOptions = ImageOptions(contentScale = ContentScale.Inside),
                component = rememberImageComponent {
                    +CrossfadePlugin()
                    +ShimmerPlugin(
                        Shimmer.Resonate(
                            baseColor = Color.Transparent,
                            highlightColor = Color.LightGray,
                        ),
                    )

                    if (!LocalInspectionMode.current) {
                        +PalettePlugin(
                            imageModel = pokeUi.imageUrl,
                            useCache = true,
                            paletteLoadedListener = { palette = it },
                        )
                    }
                },
//                previewPlaceholder = painterResource(
//                    id = R.drawable.pokemon_preview,
//                ),
            )
            Text(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(12.dp),
                text = pokeUi.name,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
//            Spacer(modifier = modifier.height(8.dp))
            Text(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                text = pokeUi.id,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
private fun PokemonListItemPreview() {
    PokeTheme {
        PokemonListItem(
            pokeUi = previewPoke,
            onClick = { /*TODO*/ },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}

internal val previewPoke = Pokemon(
    nameField = "ASAD",
    url = "https://pokeapi.co/api/v2/pokemon/1/",
).toPokeUi()