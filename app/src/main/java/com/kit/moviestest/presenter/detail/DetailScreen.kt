@file:OptIn(ExperimentalMaterial3Api::class)

package com.kit.moviestest.presenter.detail

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kit.moviestest.R

@Composable
@Preview
fun DetailScreen(id: Int? = null, onBackPressed: () -> Unit = {}) {
    val viewModel = hiltViewModel<DetailViewModel>()

    Log.e("Id", id.toString())
    val pickedItem by viewModel.pushValueWithId(id).collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.movies),
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed
                    ) {
                        Icon(
                            modifier = Modifier.size(60.dp),
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val c = LocalContext.current

            Divider(Modifier.fillMaxWidth())

            Spacer(Modifier.height(20.dp))

            pickedItem?.let { pickedMovie ->
                Log.e("NewItem", pickedMovie.toString())
                MovieDetail(
                    pickedMovie = pickedMovie,
                    onWatchClicked = {
                        c.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(pickedMovie.trailerLink)
                            )
                        )
                    },
                    onFavouriteClicked = {
                        //update db
                        viewModel.updateFavourite(
                            id = pickedMovie.id
                        )
                    }
                )

                Spacer(Modifier.height(10.dp))

                ShortDescription(pickedMovie.description)

                BaseDetails(pickedMovie.genre, pickedMovie.releasedDate)

            } ?: Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.there_is_no_data_about_movie),
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun SectionDivider() {
    Spacer(Modifier.height(10.dp))

    Divider(Modifier.fillMaxWidth())

    Spacer(Modifier.height(20.dp))
}
