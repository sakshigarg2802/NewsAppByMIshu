package com.example.newsappbymishu.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappbymishu.model.ArticlesModel
import com.example.newsappbymishu.repo.INewsRepo
import com.example.newsappbymishu.states.ViewStateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: INewsRepo) : ViewModel() {
    private val _uiState = MutableStateFlow<NewsUiState<List<ArticlesModel>>>(NewsUiState.Loading)
    val uiState: StateFlow<NewsUiState<Any?>> = _uiState
    fun getTopNewsHeadline() {
        viewModelScope.launch {
            repository.getTopNewsHeadline().collect {
                it.apply {
                    when(it){
                        is ViewStateResult.Success -> {
                            _uiState.value = NewsUiState.Success(it.data)
                        }
                        is ViewStateResult.Error ->{

                        }
                        is ViewStateResult.Loading ->{

                        }
                    }
                }
            }
        }
    }


    sealed class NewsUiState<out T> {
        data class Success(val articleList: List<ArticlesModel>): NewsUiState<List<ArticlesModel>>()
        data class Error(val message: String): NewsUiState<Nothing>()
        object Loading: NewsUiState<Nothing>()
    }
}