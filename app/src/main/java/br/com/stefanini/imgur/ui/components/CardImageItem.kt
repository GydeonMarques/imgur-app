package br.com.stefanini.imgur.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.stefanini.imgur.R
import br.com.stefanini.imgur.domain.models.ImgurModel
import br.com.stefanini.imgur.ui.theme.ImgurAppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CardImageItem(
    imgur: ImgurModel,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .fallback(R.drawable.image_default)
            .data(imgur.imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.image_default),
        contentDescription = imgur.title,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}


@Preview
@Composable
fun CardImageItemPreview() {
    ImgurAppTheme {
        CardImageItem(
            imgur = ImgurModel(
                id = "cgAz4cB",
                title = "Poor hungry cats!",
                imageUrl = "https://img.freepik.com/premium-photo/colorful-abstract-background_80983-1544.jpg"
            )
        )
    }
}