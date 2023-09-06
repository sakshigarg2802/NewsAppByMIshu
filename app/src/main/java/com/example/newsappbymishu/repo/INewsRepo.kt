package com.example.newsappbymishu.repo

import com.example.newsappbymishu.model.ArticlesModel
import com.example.newsappbymishu.states.ViewStateResult
import kotlinx.coroutines.flow.Flow

interface INewsRepo {
    fun getTopNewsHeadline(): Flow<ViewStateResult<List<ArticlesModel>>>
}