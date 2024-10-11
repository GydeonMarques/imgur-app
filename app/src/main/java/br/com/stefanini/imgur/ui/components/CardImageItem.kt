package br.com.stefanini.imgur.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import br.com.stefanini.imgur.R
import br.com.stefanini.imgur.ui.theme.ImgurAppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CardImageItem(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .fallback(R.drawable.image_default)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}


@Preview
@Composable
fun CardImageItemPreview() {
    ImgurAppTheme {
        CardImageItem(
            imageUrl = ""
        )
    }
}
