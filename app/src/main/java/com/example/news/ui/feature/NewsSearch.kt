package com.example.news.ui.feature

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.news.R
import com.example.news.model.dataClass.NewsBySearchResp
import com.example.news.ui.intent.MainIntent
import com.example.news.ui.state.MainState
import com.example.news.util.EMPTY_DATA
import com.example.news.viewModel.MainViewModel

@Composable
fun NewsSearch(viewModel: MainViewModel) {

    val text = remember {
        mutableStateOf("tesla")
    }
    val newsList = remember {
        mutableStateOf(listOf(EMPTY_DATA))
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
                    newsList.value = state.news
                }

                is MainState.Error -> {

                }
            }
        }

    }



    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        SearchBox(edtValue = text.value, icon = Icons.Default.Search, hint = "Search News") {
            text.value = it
        }

        SetNews(newsList.value)


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

@Composable
fun SetNews(newsList: List<NewsBySearchResp.New>) {

    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (newsList.size > 1) {
            LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(newsList.size) {
                    NewsItem(newsList[it])
                }

            }
        } else {
            Loader()
        }
    }

}

@Composable
fun NewsItem(new: NewsBySearchResp.New) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 24.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {


        AsyncImage(
            model = new.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )


        Column(
            Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {

            Text(
                text = new.title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = new.text,
                maxLines = 3,
                style = TextStyle(
                    fontSize = 14.sp,
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

        }

    }


}

@Composable
fun Loader() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_anime)
    )

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(150.dp),
    )
}