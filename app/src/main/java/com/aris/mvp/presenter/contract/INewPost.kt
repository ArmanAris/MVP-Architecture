package com.aris.mvp.presenter.contract

import com.aris.mvp.model.Post

// POST: /posts   one
interface INewPost {

    interface View {
        fun onSuccess(massage: String)
        fun onError(massage: String)
        fun onFail(massage: String)
    }

    interface Presenter {
        suspend fun createNewPost(post: Post)
    }
}