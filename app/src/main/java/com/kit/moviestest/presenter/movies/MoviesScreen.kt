@file:OptIn(ExperimentalMaterial3Api::class)

package com.kit.moviestest.presenter.movies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kit.moviestest.R

@Composable
@Preview
fun MoviesScreen(navigateToDetail: (Int) -> Unit = {}) {
    val viewModel = hiltViewModel<MoviesViewModel>()

    val movieList by viewModel.moviesFlow.collectAsState()

    viewModel.pushDefault()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = stringResource(R.string.movies),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                },
                actions = {
                    TextButton(
                        modifier = Modifier.padding(end = 15.dp),
                        onClick = { showBottomSheet = true }) {
                        Text(
                            text = stringResource(R.string.sort),
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            movieList?.let {
                items(it) { iteratedMovie ->
                    MovieCard(movie = iteratedMovie) {
                        navigateToDetail(iteratedMovie.id)
                    }
                }
            } ?: item {
                LoadingDataScreen()
            }
        }

        if (showBottomSheet) {
            FunctionalBottomSheet(
                sheetState = sheetState,
                sortByDate = {
                    viewModel.updateSortedByDate()
                },
                sortByTitle = {
                    viewModel.updateSortedByTitle()
                },
                hideSheet = {
                    showBottomSheet = false
                }
            )
        }
    }
}