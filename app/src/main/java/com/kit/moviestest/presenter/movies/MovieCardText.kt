package com.kit.moviestest.presenter.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kit.moviestest.R

@Composable
fun RowScope.MovieCardText(
    title: String,
    releasedDate: String,
    duration: String,
    genre: String,
    inWatchList: Boolean,
) {
    Column(
        modifier = Modifier
            .weight(2f)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "$title ($releasedDate)",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$duration - $genre",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.ExtraLight
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (inWatchList)
            Text(
                text = stringResource(R.string.on_my_watch_list).uppercase(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
    }
}
