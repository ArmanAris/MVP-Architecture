package com.aris.mvp.presenter.presnet


import com.aris.mvp.network.ApiClient
import com.aris.mvp.presenter.contract.IPost
import com.aris.mvp.presenter.contract.ISinglePost
// GET: /Posts/1
class SinglePostPresent(var view: ISinglePost.View) : ISinglePost.Presenter {

    override suspend fun getPostRequest(postId: String) {
        val response = try {
            ApiClient.api.getPostById(postId)
        } catch (ex: Exception) {
            view.onFail("fail request: ${ex.message}")
            return
        }

        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { Post ->
                view.onSuccess(Post)
            }
        } else {
            view.onError("error")
        }
    }

}