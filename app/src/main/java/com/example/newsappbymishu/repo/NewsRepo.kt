package com.example.newsappbymishu.repo

import com.example.newsappbymishu.model.ArticlesModel
import com.example.newsappbymishu.network.INewsApi
import com.example.newsappbymishu.states.ViewStateResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepo @Inject constructor(private val apiService: INewsApi): INewsRepo {
    override fun getTopNewsHeadline() : Flow<ViewStateResult<List<ArticlesModel>>> = flow {
        apiService.getTopNewsHeadlineList().run {
            this.body()?.articles.apply {
                if(null != this)
                emit(ViewStateResult.Success(this))
            }

        }
    }
}