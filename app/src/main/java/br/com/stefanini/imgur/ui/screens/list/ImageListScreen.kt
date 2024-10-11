package br.com.stefanini.imgur.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.stefanini.imgur.R
import br.com.stefanini.imgur.ui.components.CardImageItem
import br.com.stefanini.imgur.ui.components.ViewState
import com.airbnb.lottie.compose.LottieCompositionSpec

@Composable
internal fun ImageListScreen(
    viewModel: ImageListViewModel,
    modifier: Modifier = Modifier,
) {
    val viewState by viewModel.data.collectAsStateWithLifecycle()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        when (viewState) {
            ImageListViewState.Loading -> RenderLayoutLoading()
            ImageListViewState.Failure -> RenderLayoutFailure()
            is ImageListViewState.Success -> RenderLayoutSuccess(
                viewState as ImageListViewState.Success
            )
        }
    }
}

@Composable
private fun RenderLayoutLoading() {
    ViewState(
        icon = LottieCompositionSpec.RawRes(R.raw.ic_loading),
        message = stringResource(id = R.string.loading_data_please_wait)
    )
}

@Composable
private fun RenderLayoutFailure() {
    ViewState(
        icon = LottieCompositionSpec.RawRes(R.raw.ic_error),
        message = stringResource(id = R.string.there_was_an_error_loading_the_data)
    )
}


@Composable
private fun RenderLayoutSuccess(viewState: ImageListViewState.Success) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 6.dp,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(viewState.galleries) { gallery ->
            CardImageItem(
                imageUrl = gallery.images.takeIf { it.isNotEmpty() }?.get(0)?.imageUrl.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}