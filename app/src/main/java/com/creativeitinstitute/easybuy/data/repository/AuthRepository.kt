package com.creativeitinstitute.easybuy.data.repository

import com.creativeitinstitute.easybuy.data.models.AuthSource
import com.creativeitinstitute.easybuy.views.register.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthRepository:AuthSource {
    override fun userRegistration(user: UserRegister) :Task<AuthResult>{

        val jAuth = FirebaseAuth.getInstance()

       return jAuth.createUserWithEmailAndPassword(user.email, user.password)

    }

    override fun userLogin() {

    }

    override fun userForgetPassword() {

    }

    override fun createUser() {

    }
}