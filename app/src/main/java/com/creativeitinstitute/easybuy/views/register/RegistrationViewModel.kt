package com.creativeitinstitute.easybuy.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.easybuy.core.DataState
import com.creativeitinstitute.easybuy.data.repository.AuthRepository

class RegistrationViewModel :ViewModel(){

   private val _registrationResponse = MutableLiveData<DataState<UserRegister>>()
    val registrationResponse: LiveData<DataState<UserRegister>> = _registrationResponse


    fun userRegistration(user:UserRegister){
        _registrationResponse.postValue(DataState.Loading())

        var authService = AuthRepository()

        authService.userRegistration(user).addOnSuccessListener {

            _registrationResponse.postValue(DataState.Success(user))
            Log.d("TAG", "userRegistration: Success")

        }.addOnFailureListener {error->

            _registrationResponse.postValue(DataState.Error("${error.message}"))
            Log.d("TAG", "userRegistration: ${error.message} ")

        }
    }

}