package com.creativeitinstitute.easybuy.data.models

import android.net.Uri
import com.creativeitinstitute.easybuy.data.Product
import com.creativeitinstitute.easybuy.views.login.UserLogin
import com.creativeitinstitute.easybuy.views.register.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.UploadTask

interface SellerSource {
    fun uploadProductImage(productImageUri: Uri) : UploadTask

    fun uploadProduct(product:Product):Task<Void>

    fun getAllProductByUserID(userID:String): Task<QuerySnapshot>
}