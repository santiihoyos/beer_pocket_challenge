package com.santiihoyos.beerspocket.ui.feature.detail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.santiihoyos.beerspocket.domain.entity.Beer
import com.santiihoyos.beerspocket.ui.feature.beers.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerDetail(
    beerDetailViewModel: BeerDetailViewModel,
    id: String,
    onBack: () -> Unit
) {
    LaunchedEffect(key1 = "onStart", block = {
        beerDetailViewModel.getBeerById(beerId = id)
    })

    val uiState = beerDetailViewModel.uiState
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(uiState.beer?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        })
    {
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
            PortraitInfoContainer(beerDetailViewModel, id)
        } else {
            LandscapeInfoContainer(beerDetailViewModel, id)
        }
    }
}

@Composable
private fun PortraitInfoContainer(
    beerDetailViewModel: BeerDetailViewModel,
    beerId: String,
) {
    Surface {
        if (beerDetailViewModel.uiState.isLoading) {
            ProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                ErrorDialog(
                    show = beerDetailViewModel.uiState.errorText != null,
                    beerDetailViewModel = beerDetailViewModel,
                    beerId = beerId
                )
                beerDetailViewModel.uiState.beer?.let { beer ->
                    Surface(
                        color = Color.White, modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                    ) {
                        AsyncImage(
                            model = beer.getImageDetailUrl(),
                            contentDescription = beer.name,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Id(beer = beer)
                    Description(beer = beer)
                }
            }
        }
    }
}

@Composable
fun LandscapeInfoContainer(
    beerDetailViewModel: BeerDetailViewModel,
    beerId: String,
) {
    Surface {
        if (beerDetailViewModel.uiState.isLoading) {
            ProgressIndicator()
        } else {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                ErrorDialog(
                    show = beerDetailViewModel.uiState.errorText != null,
                    beerDetailViewModel = beerDetailViewModel,
                    beerId = beerId
                )
                beerDetailViewModel.uiState.beer?.let { beer ->
                    Surface(
                        color = Color.White, modifier = Modifier
                            .fillMaxHeight()
                            .width(400.dp)
                    ) {
                        AsyncImage(
                            model = beer.getImageDetailUrl(),
                            contentDescription = beer.name,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(70.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Id(beer = beer)
                        Description(beer = beer)
                    }
                }
            }
        }
    }
}

@Composable
private fun Id(beer: Beer) {
    Text(
        "ID: ${beer.id}",
        style = TextStyle(
            fontSize = 20.sp
        ),
        color = LocalContentColor.current,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

@Composable
private fun Description(beer: Beer) {
    Text(
        if (!beer.description.isNullOrEmpty())
            beer.description
        else
            stringResource(id = R.string.theres_not_description),
        style = TextStyle(
            fontSize = 16.sp
        ),
        color = LocalContentColor.current,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

@Composable
private fun ProgressIndicator() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}

@Composable
private fun ErrorDialog(
    show: Boolean,
    beerDetailViewModel: BeerDetailViewModel,
    beerId: String,
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
                    onClick = {
                        beerDetailViewModel.getBeerById(beerId)
                    },
                ) {
                    Text(stringResource(id = R.string.error_dialog_button_retry))
                }
            },
            text = {
                val textId = beerDetailViewModel.uiState.errorText
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