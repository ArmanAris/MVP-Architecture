package com.aris.mvp.presenter.contract

import com.aris.mvp.model.Post
// GET: /Posts/1
interface ISinglePost {

    interface View {
        fun onSuccess(post: Post)
        fun onError(massage: String)
        fun onFail(massage: String)
    }

    interface Presenter {
        suspend fun getPostRequest(postId: String)
    }
}