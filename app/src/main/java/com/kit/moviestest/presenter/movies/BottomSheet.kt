@file:OptIn(ExperimentalMaterial3Api::class)

package com.kit.moviestest.presenter.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kit.moviestest.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FunctionalBottomSheet(
    sheetState: SheetState,
    sortByTitle: () -> Unit,
    sortByDate: () -> Unit,
    hideSheet: () -> Unit
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        containerColor = Color.Transparent,
        onDismissRequest = hideSheet,
        sheetState = sheetState
    ) {
        // Sheet content
        BottomSheetButton(
            text = stringResource(R.string.title),
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            onItemClicked = {
                sortByTitle()
                scope.animateModalBottomSheetClosing(
                    sheetState,
                    hideSheet
                )
            }
        )

        BottomSheetButton(
            text = stringResource(R.string.released_data),
            shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
            onItemClicked = {
                sortByDate()

                scope.animateModalBottomSheetClosing(
                    sheetState,
                    hideSheet
                )
            }
        )

        Spacer(Modifier.height(4.dp))

        BottomSheetButton(text = stringResource(R.string.cancel)) {
            scope.animateModalBottomSheetClosing(sheetState, hideSheet)
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}


fun CoroutineScope.animateModalBottomSheetClosing(sheetState: SheetState, hideSheet: () -> Unit) {
    launch { sheetState.hide() }.invokeOnCompletion {
        if (!sheetState.isVisible) {
            hideSheet()
        }
    }
}


@Composable
fun BottomSheetButton(
    modifier: Modifier = Modifier,
    text: String,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),
    onItemClicked: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(vertical = 2.dp, horizontal = 50.dp),
        onClick = onItemClicked,
        shape = shape
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 3.dp),
                text = text,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}