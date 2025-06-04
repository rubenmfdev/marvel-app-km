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
import androidx.compose.material3.pulltorefresh.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelListScreen(
    viewModel: MarvelListViewModelAndroid = viewModel(factory = MarvelListViewModelFactory())
) {
    val state by viewModel.delegate.state.collectAsState()
    val listState = rememberLazyListState()
    val isRefreshing = state is LoaderState.Loading
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Marvel Characters") }) }
    ) { innerPadding ->

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { viewModel.delegate.onPullToRefresh() },
            modifier = Modifier.padding(innerPadding)
        ) {
            when (val currentState = state) {
                is LoaderState.Loading -> {
                    if ((currentState as? LoaderState.Success)?.data.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

                is LoaderState.Success -> {
                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(currentState.data) { character ->
                            CharacterItem(character)
                        }
                    }

                    // 👇 onEndReached logic
                    LaunchedEffect(listState) {
                        snapshotFlow { listState.layoutInfo }
                            .collect { layoutInfo ->
                                val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return@collect
                                val totalItemsCount = layoutInfo.totalItemsCount

                                // Trigger when near the end (e.g., last item visible)
                                if (lastVisibleItemIndex >= totalItemsCount - 1) {
                                    viewModel.delegate.onEndReached()
                                }
                            }
                    }
                }

                is LoaderState.Failed -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            "Error: ${currentState.error}",
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
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
            Text(character.characterDescription, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
