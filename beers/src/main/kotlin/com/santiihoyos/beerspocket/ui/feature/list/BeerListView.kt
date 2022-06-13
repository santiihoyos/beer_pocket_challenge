package com.santiihoyos.beerspocket.ui.feature.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.santiihoyos.beerspocket.domain.entity.Beer
import com.santiihoyos.beerspocket.ui.feature.beers.R

@Composable
fun BeerList(
    beerListViewModel: BeerListViewModel,
    onNavigateToDetail: (beerId: String) -> Unit
) {

    LaunchedEffect(key1 = "onCreate", block = {
        if (beerListViewModel.uiState.beersListPager == null) {
            beerListViewModel.loadBeersPager()
        }
    })

    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        beersListContainer(
            beerListViewModel = beerListViewModel,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}

@Composable
private fun beersListContainer(
    beerListViewModel: BeerListViewModel,
    onNavigateToDetail: (beerId: String) -> Unit
) {

    Surface {
        val beersState =
            beerListViewModel.uiState.beersListPager?.collectAsLazyPagingItems()
        if (beerListViewModel.uiState.isLoading) {
            ProgressIndicator()
        }
        LazyVerticalGrid(
            columns = androidx.compose.foundation.lazy.grid.GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
        ) {
            items(beersState?.itemCount ?: 0) { index ->
                beerItem(beer = beersState!![index]!!, onNavigateToDetail)
            }
        }
    }
    ErrorContainer(
        beerListViewModel = beerListViewModel,
        show = beerListViewModel.uiState.errorText != null,
    )
}

@Composable
private fun ErrorContainer(
    show: Boolean,
    beerListViewModel: BeerListViewModel,
) {
    if (show) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(
                    text = stringResource(id = R.string.error_dialog_title),
                    style = TextStyle(color = MaterialTheme.colorScheme.error)
                )
            },
            confirmButton = {
                Button(
                    onClick = { beerListViewModel.loadBeersPager() },
                ) {
                    Text(stringResource(id = R.string.error_dialog_button_retry))
                }
            },
            text = {
                val textId = beerListViewModel.uiState.errorText
                Text(
                    if (textId != null) stringResource(id = textId) else "error.",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onErrorContainer,
                    )
                )
            },
        )
    }
}

@Composable
private fun ProgressIndicator() {
    Dialog(
        onDismissRequest = {

        }, properties = DialogProperties()
    ) {
        Surface(
            color = Color.Black.copy(alpha = 0.2f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun beerItem(beer: Beer, onNavigateToDetail: (beerId: String) -> Unit) {
    Surface(
        onClick = {
            onNavigateToDetail(beer.id.toString())
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(all = 6.dp),
        color = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.13f),
        shape = CutCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(

        ) {
            val imageUrl = beer.getPreviewImageUrl()
            AsyncImage(
                model = imageUrl,
                contentDescription = beer.name,
                modifier = Modifier
                    .width(200.dp)
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = beer.name!!, style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(all = 8.dp),
                    maxLines = 3,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}