package com.aris.mvp.presenter.presnet


import com.aris.mvp.network.ApiClient
import com.aris.mvp.presenter.contract.IPost
// GET: /Posts
class PostPresent(var view: IPost.View) : IPost.Presenter {

    override suspend fun getAllPostRequest() {
        val response = try {
            ApiClient.api.getAllPosts()
        } catch (ex: Exception) {
            view.onFail("fail request: ${ex.message}")
            return
        }

        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { allPosts ->
                view.onSuccess(allPosts)
            }
        } else {
            view.onError("error")
        }
    }

}