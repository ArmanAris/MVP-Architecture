package com.aris.mvp.presenter.contract

import com.aris.mvp.model.Comments

// GET: /comments?postId=1   Group comment
interface IComments {

    interface View {
        fun onSuccess(commentList: List<Comments>)
        fun onError(massage: String)
        fun onFail(massage: String)
    }

    interface Presenter {
        suspend fun getAllPostComments(postId: Int)
    }
}