package br.com.stefanini.imgur.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.stefanini.imgur.ui.components.CardImageItem
import br.com.stefanini.imgur.ui.theme.ImgurAppTheme

@Composable
fun ImgurListScreen(
    viewModel: ImgurListViewModel,
    modifier: Modifier = Modifier,
) {
    val images by viewModel.images.observeAsState(emptyList())

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier.fillMaxSize(),
        ) {
            items(images) { image ->
                CardImageItem(
                    imgur = image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun ImgurListScreenPreview() {
    ImgurAppTheme {
        ImgurListScreen(viewModel = viewModel())
    }
}