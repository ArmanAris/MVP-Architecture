package com.aris.mvp.presenter.contract

import com.aris.mvp.model.Post
// GET: /Posts
interface IPost {

    interface View {
        fun onSuccess(postList: List<Post>)
        fun onError(massage: String)
        fun onFail(massage: String)
    }

    interface Presenter {
        suspend fun getAllPostRequest()
    }
}