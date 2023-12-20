package com.kit.moviestest.presenter.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kit.moviestest.data.room.entity.Movie
import com.kit.moviestest.domain.retrieveYear

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
                movie.releasedDate.retrieveYear(),
                movie.duration,
                movie.genre,
                movie.isInWatchList
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