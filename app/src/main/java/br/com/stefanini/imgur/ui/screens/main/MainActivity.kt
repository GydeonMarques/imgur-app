package br.com.stefanini.imgur.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.stefanini.imgur.ui.screens.list.ImageListScreen
import br.com.stefanini.imgur.ui.screens.list.ImageListViewModel
import br.com.stefanini.imgur.ui.theme.ImgurAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImgurAppTheme {
                Scaffold { contentPadding ->
                    ImageListScreen(
                        viewModel = hiltViewModel<ImageListViewModel>(),
                        modifier = Modifier.padding(contentPadding)
                    )
                }
            }
        }
    }
}

