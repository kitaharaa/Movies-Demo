package com.kit.moviestest.presenter.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kit.moviestest.R
import com.kit.moviestest.domain.parseIntoString
import java.util.Date

@Composable
fun BaseDetails(genre: String, releasedDate: Date) {
    SectionDivider()

    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 15.dp),
        columns = GridCells.Fixed(3)
    ) {
        header()
        itemTitle("Genre")
        itemText(genre)

        itemTitle("Released date")

        itemText(releasedDate.parseIntoString())
    }
}

fun LazyGridScope.header() {
    item(span = { GridItemSpan(3) }) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.details),
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )

        Spacer(Modifier.height(8.dp))
    }
}

fun LazyGridScope.itemTitle(text: String) {
    item {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}

fun LazyGridScope.itemText(text: String) {
    item(span = { GridItemSpan(2) }) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            text = text,
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}