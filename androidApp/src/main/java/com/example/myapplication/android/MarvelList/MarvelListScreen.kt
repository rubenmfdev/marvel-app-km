package com.example.myapplication.android.MarvelList


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.domain.Entities.CharacterEntity
import com.example.myapplication.utils.LoaderState
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelListScreen(
    viewModel: MarvelListViewModelAndroid = viewModel(factory = MarvelListViewModelFactory())
) {
    val state by viewModel.delegate.state.collectAsState()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Marvel Characters") }) }
    ) { innerPadding ->
        when (val currentState = state) {
            is LoaderState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is LoaderState.Success -> {
                LazyColumn(contentPadding = innerPadding) {
                    items(currentState.data) { character ->
                        CharacterItem(character)
                    }
                }
            }

            is LoaderState.Failed -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text("Error: ${currentState.error}", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}


@Composable
fun CharacterItem(character: CharacterEntity) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(character.name, style = MaterialTheme.typography.titleLarge)
            Text(character.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
