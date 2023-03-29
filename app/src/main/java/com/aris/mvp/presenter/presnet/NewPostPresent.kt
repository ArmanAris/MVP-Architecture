package com.aris.mvp.presenter.presnet


import com.aris.mvp.model.Post
import com.aris.mvp.network.ApiClient
import com.aris.mvp.presenter.contract.INewPost
import com.aris.mvp.presenter.contract.IPost

// POST: /posts   one
class NewPostPresent(var view: INewPost.View) : INewPost.Presenter {

    override suspend fun createNewPost(post: Post) {
        val response = try {
            ApiClient.api.createNewPost(post)
        } catch (ex: Exception) {
            view.onFail("fail request: ${ex.message}")
            return
        }

        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { post ->
                view.onSuccess("the new post is: $post")
            }
        } else {
            view.onError("error")
        }
    }

}