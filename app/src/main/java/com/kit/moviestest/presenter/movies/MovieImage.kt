package com.kit.moviestest.presenter.movies

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kit.moviestest.R

@Composable
fun RowScope.MovieImage(image: Bitmap?, title: String, weight: Float = 1f) {
    val imageModifier = Modifier
        .weight(weight)
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))

    image?.let {
        Image(
            modifier = imageModifier,
            bitmap = image.asImageBitmap(),
            contentDescription = title
        )
    } ?: Image(
        modifier = imageModifier,
        painter = painterResource(R.mipmap.default_image),
        contentDescription = title
    )
}