package com.terry.see.seelive.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.terry.see.seelive.R
import com.terry.see.seelive.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


/**
 * Created by terry on 2017/8/18.
 */
class LoginAct : AppCompatActivity(){

    lateinit var instance: LoginAct

    lateinit var loginP : PresenterLogin
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        instance = this

        setSupportActionBar(toolbar as Toolbar)
        setTitle("登录")

        btn_user_login_submit.setOnClickListener { view ->

            if (edtTxt_user_login_username.text.isNotEmpty()) Toast.makeText(instance,"账户不能为空",Toast.LENGTH_LONG).show()

            if (edtTxt_user_login_password.text.isNotEmpty()) Toast.makeText(instance,"密码不能为空",Toast.LENGTH_LONG).show()

            loginP.login(edtTxt_user_login_username.text.toString(),edtTxt_user_login_password.text.toString())

//            instance.startActivity(Intent(instance, MainActivity::class.java))

        }

    }

}