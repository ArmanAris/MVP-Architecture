package com.aris.mvp.presenter.presnet

import com.aris.mvp.model.Login
import com.aris.mvp.presenter.contract.ILogin

class LoginPresenter(var view: ILogin.View) : ILogin.Presenter {

    override fun login(userName: String, password: String) {
        val userLogin = Login()

        if (userName == userLogin.userName && password == userLogin.password) {
            view.onSuccess("Ok")
        } else {
            view.onFail("fail")
        }
    }


}