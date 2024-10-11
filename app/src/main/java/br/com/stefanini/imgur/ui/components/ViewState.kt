package br.com.stefanini.imgur.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.stefanini.imgur.R
import br.com.stefanini.imgur.ui.theme.DefaultPadding
import br.com.stefanini.imgur.ui.theme.ImgurAppTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ViewState(
    icon: LottieCompositionSpec,
    message: String,
) {

    val composition by rememberLottieComposition(icon)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(DefaultPadding)
    ) {
        LottieAnimation(
            composition,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ViewStatePreview() {
    ImgurAppTheme {
        ViewState(
            icon = LottieCompositionSpec.RawRes(R.raw.ic_error),
            message = "Esta e uma view padrão que tem por finalidade exibir messagens de alerta durante o processamento de dados. Ex: Loading, error... etc."
        )
    }
}