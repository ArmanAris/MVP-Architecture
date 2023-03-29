package com.aris.mvp.presenter.contract

interface ILogin {

    interface View {
        fun onSuccess(massage: String)
        fun onFail(massage: String)
    }

    interface Presenter {
        fun login(userName: String, password: String)
    }
}