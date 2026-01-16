package com.learn.pertemuan6sindi.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.pertemuan6sindi.viewModel.QuoteUiState
import com.learn.pertemuan6sindi.viewModel.QuoteViewModel
import com.learn.pertemuan6sindi.model.Quote
import kotlinx.coroutines.launch

@Composable
fun QuoteScreen(viewModel: QuoteViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchNewQuote()
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (state) {
            is QuoteUiState.Loading -> {
                CircularProgressIndicator()
            }

            is QuoteUiState.Success -> {
                QuoteCard(quote = (state as QuoteUiState.Success).quote)
            }

            is QuoteUiState.Error -> {
                Text(
                    text = (state as QuoteUiState.Error).message ?: "Gagal Muat Data",
                    color = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                scope.launch {
                    viewModel.fetchNewQuote()
                }
            }
        ) {
            Text("Refresh Quote")
        }
    }
}

@Composable
fun QuoteCard(quote: Quote) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "\"${quote.quote}\"",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "- ${quote.author}",
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic
            )
        }
    }
}
