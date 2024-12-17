package com.creativeitinstitute.easybuy.data.models

import com.creativeitinstitute.easybuy.views.login.UserLogin
import com.creativeitinstitute.easybuy.views.register.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthSource {

    fun userRegistration(user:UserRegister):Task<AuthResult>
    fun userLogin(user: UserLogin) : Task<AuthResult>
    fun userForgetPassword()
    fun createUser(user: UserRegister): Task<Void>
}