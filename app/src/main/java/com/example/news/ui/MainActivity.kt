package com.example.news.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.news.ui.feature.NewsSearch
import com.example.news.ui.theme.NewsTheme
import com.example.news.util.MyScreen
import com.example.news.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            NewsTheme {

                MainUi(viewModel)

            }
        }
    }
}

@Composable
fun MainUi(viewModel: MainViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MyScreen.NewsSearch.route){

        composable(MyScreen.NewsSearch.route){
            NewsSearch(viewModel)
        }

    }

}

