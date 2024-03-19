package com.example.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.repository.MainRepository
import com.example.news.ui.intent.MainIntent
import com.example.news.ui.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val newsSearchIntent = Channel<MainIntent>()
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state : StateFlow<MainState> get() = _state


    init {
        handleIntent()
    }

    private fun handleIntent() {

        viewModelScope.launch {

            newsSearchIntent.consumeAsFlow().collect{

                when (it){
                    is MainIntent.GetNewsBySearch -> getNewsBySearch(it.search)
                }

            }

        }

    }

    private fun getNewsBySearch(search: String) {

        viewModelScope.launch {

            _state.value = MainState.Loading
            _state.value = try {
                MainState.NewsSearch(
                    repository.getNewsBySearch(search)

                )
            }catch (e : Exception){
                MainState.Error(e.localizedMessage)
            }

        }

    }


}