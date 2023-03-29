package com.aris.mvp.presenter.presnet


import com.aris.mvp.network.ApiClient
import com.aris.mvp.presenter.contract.IComments
import com.aris.mvp.presenter.contract.IPost

// GET: /comments?postId=1   Group comment
class CommentPresent(var view: IComments.View) : IComments.Presenter {

    override suspend fun getAllPostComments(postId: Int) {
        val response = try {
            ApiClient.api.getAllPostComments(postId)
        } catch (ex: Exception) {
            view.onFail("fail request: ${ex.message}")
            return
        }

        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { allComments ->
                view.onSuccess(allComments)
            }
        } else {
            view.onError("error" + response.errorBody())
        }
    }

}