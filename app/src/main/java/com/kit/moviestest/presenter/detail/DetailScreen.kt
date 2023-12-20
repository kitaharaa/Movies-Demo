@file:OptIn(ExperimentalMaterial3Api::class)

package com.kit.moviestest.presenter.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kit.moviestest.R

@Composable
@Preview
fun DetailScreen(id: Int? = null) {
    val viewModel = hiltViewModel<DetailViewModel>()

    val pickedItem by viewModel.movieDataFlow.collectAsState()

    viewModel.pushValueWithId(id)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies",
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            //todo nav back
                        }
                    ) {
                        Icon(
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

            pickedItem?.let { pickedMovie ->
                //todo add ui
                /*MovieCard(
                    movie = pickedMovie,
                    onItemClicked = {}
                )*/
            } ?: Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "There is no data about movie",
                    fontSize = 24.sp
                )
            }
        }
    }
}