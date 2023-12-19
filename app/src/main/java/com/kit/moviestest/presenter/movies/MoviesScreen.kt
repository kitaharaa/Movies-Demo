@file:OptIn(ExperimentalMaterial3Api::class)

package com.kit.moviestest.presenter.movies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kit.moviestest.R
import com.kit.moviestest.data.room.entity.Movie

@Composable
@Preview
fun MoviesScreen(navigateToDetail: (Int) -> Unit = {}) {
    val list = LocalContext.current.moviesList()
    val viewModel = hiltViewModel<MoviesViewModel>()

    val movieList by viewModel.moviesFlow.collectAsState(initial = null)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MediumTopAppBar(title = {
                Text(
                    text = "Movies",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            },
                actions = {
                    TextButton(
                        modifier = Modifier.padding(end = 15.dp),
                        onClick = { /*TODO add sorting*/ }) {
                        Text(
                            text = "Sort",
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
                items(it) {
                    MovieCard(movie = it) {
                        navigateToDetail(it.id)
                    }
                }
            } ?: item {
                LoadingDataScreen()
            }
        }
    }
}

@Composable
@Preview
fun LoadingDataScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column{
            Text(
                text = stringResource(R.string.wait_a_sec),
                fontSize = 24.sp
            )

            Spacer(Modifier.height(17.dp))

            CircularProgressIndicator(Modifier.size(60.dp))
        }
    }
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClicked: () -> Unit
) {
    Column(
        modifier = modifier.clickable {
               onItemClicked()
        },
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            MovieImage(movie.image, movie.title)

            MovieCardText(
                movie.title,
                movie.releasedDate,
                movie.duration,
                movie.genre
            )
        }

        Spacer(Modifier.height(15.dp))

        Divider(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
    }
}

fun Context.drawableToBitmap(): Bitmap? = try {
    BitmapFactory.decodeResource(resources, R.mipmap.default_image)
} catch (e: Exception) {
    e.printStackTrace()

    null
}

fun Context.moviesList() = listOf(
    Movie(
        title = "Tenet",
        description = "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
        rating = 7.8f,
        duration = "2h 30min",
        genre = "Action, Sci-Fi",
        releasedDate = "3 September 2020",
        trailerLink = "https://www.youtube.com/watch?v=LdOM0x0XDMo",
        isInWatchList = false,
        image = drawableToBitmap() // Будь-яке зображення в форматі Bitmap
    ),
    Movie(
        title = "Spider-Man: Into the Spider-Verse",
        description = "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other dimensions to stop a threat for all realities.",
        rating = 8.4f,
        duration = "1h 57min",
        genre = "Action, Animation, Adventure",
        releasedDate = "14 December 2018",
        trailerLink = "https://www.youtube.com/watch?v=tg52up16eq0",
        isInWatchList = true,
        image = drawableToBitmap() // Будь-яке зображення в форматі Bitmap
    ),
    Movie(
        title = "Knives Out",
        description = "A detective investigates the death of a patriarch of an eccentric, combative family.",
        rating = 7.9f,
        duration = "2h 10min",
        genre = "Comedy, Crime, Drama",
        releasedDate = "27 November 2019",
        trailerLink = "https://www.youtube.com/watch?v=qGqiHJTsRkQ",
        isInWatchList = false,
        image = drawableToBitmap() // Будь-яке зображення в форматі Bitmap
    ),
    Movie(
        title = "Guardians of the Galaxy",
        description = "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.",
        rating = 8.0f,
        duration = "2h 1min",
        genre = "Action, Adventure, Comedy",
        releasedDate = "1 August 2014",
        trailerLink = "https://www.youtube.com/watch?v=d96cjJhvlMA",
        isInWatchList = false,
        image = drawableToBitmap() // Будь-яке зображення в форматі Bitmap
    ),
    Movie(
        title = "Avengers: Age of Ultron",
        description = "When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and it's up to Earth's mightiest heroes to stop the villainous Ultron from enacting his terrible plan.",
        rating = 7.3f,
        duration = "2h 21min",
        genre = "Action, Adventure, Sci-Fi",
        releasedDate = "1 May 2015",
        trailerLink = "https://www.youtube.com/watch?v=tmeOjFno6Do",
        isInWatchList = false,
        image = drawableToBitmap() // Будь-яке зображення в форматі Bitmap
    )
)

// Тепер у вас повний список фільмів готовий для вставки в базу даних
