package com.creativeitinstitute.easybuy.data.repository

import com.creativeitinstitute.easybuy.core.Nodes
import com.creativeitinstitute.easybuy.data.models.AuthSource
import com.creativeitinstitute.easybuy.views.login.UserLogin
import com.creativeitinstitute.easybuy.views.register.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val jAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) :AuthSource {
    override fun userRegistration(user: UserRegister) :Task<AuthResult>{

       return jAuth.createUserWithEmailAndPassword(user.email, user.password)

    }

    override fun userLogin(user: UserLogin) : Task<AuthResult> {

      return jAuth.signInWithEmailAndPassword(user.email, user.password)


    }


    override fun userForgetPassword() {

    }

    override fun createUser(user: UserRegister): Task<Void> {

     return db.collection(Nodes.USER).document(user.userID).set(user)

    }
}