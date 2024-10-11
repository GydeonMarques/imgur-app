package br.com.stefanini.imgur.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.stefanini.imgur.ui.theme.ImgurAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImgurAppTheme {
                Scaffold { contentPadding ->
                    ImgurListScreen(
                        viewModel = viewModel(),
                        modifier = Modifier.padding(contentPadding)
                    )
                }
            }
        }
    }
}

