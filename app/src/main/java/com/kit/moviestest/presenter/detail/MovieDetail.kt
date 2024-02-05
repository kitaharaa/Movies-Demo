package com.kit.moviestest.presenter.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kit.moviestest.R
import com.kit.moviestest.data.room.entity.Movie
import com.kit.moviestest.presenter.movies.MovieImage

@Composable
fun MovieDetail(
    pickedMovie: Movie,
    onWatchClicked: () -> Unit,
    onFavouriteClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),

        ) {
        MovieImage(image = pickedMovie.image, title = pickedMovie.title, weight = 1.5f)

        Column(
            Modifier
                .weight(2f)
                .padding(top = 8.dp, start = 15.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = pickedMovie.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 29.sp
                )

                Text(
                    modifier = Modifier.weight(.5f),
                    textAlign = TextAlign.End,
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        ) {
                            append(pickedMovie.rating.toString())
                        }

                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = Color.LightGray
                            )
                        ) {
                            append(stringResource(R.string._10))
                        }
                    })
            }

            Spacer(modifier = Modifier.height(20.dp))

            FilledTonalButton(
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                onClick = onFavouriteClicked
            ) {
                if (!pickedMovie.isInWatchList)
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(R.string.drop_down)
                    )

                Text(
                    text = (if (!pickedMovie.isInWatchList) stringResource(R.string.add_to_watchlist)
                    else
                        stringResource(R.string.remove_from_watchlist)
                            ).uppercase(),
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(1.dp))

            OutlinedButton(onClick = onWatchClicked) {
                Text(
                    text = stringResource(R.string.watch_trailer).uppercase(),
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }
        }
    }
}
