package com.example.news.ui.feature

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.news.ui.intent.MainIntent
import com.example.news.ui.state.MainState
import com.example.news.viewModel.MainViewModel

@Composable
fun NewsSearch(viewModel: MainViewModel) {

    val text = remember {
        mutableStateOf("")
    }

    Log.v("testData2", text.value)

    LaunchedEffect(text.value) {
        viewModel.newsSearchIntent.send(MainIntent.GetNewsBySearch(text.value))

        viewModel.state.collect { state ->
            when (state) {
                is MainState.Idle -> {

                }

                is MainState.Loading -> {

                }

                is MainState.NewsSearch -> {
                    Log.v("testData1", text.value)
                    Log.v("testData", state.toString())
                }

                is MainState.Error -> {

                }
            }
        }

    }



    Column(
        Modifier.fillMaxSize()
    ) {

        SearchBox(edtValue = text.value, icon = Icons.Default.Search, hint = "Search News") {
            text.value = it
        }

    }


}

@Composable
fun SearchBox(edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit) {
    OutlinedTextField(
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        shape = ShapeDefaults.Medium,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null, tint = Color.Black) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            focusedTextColor = Color.Black,
        )
    )

}
