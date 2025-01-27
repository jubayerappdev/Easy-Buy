package com.creativeitinstitute.easybuy.views.dashboard.seller.upload

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.easybuy.core.DataState
import com.creativeitinstitute.easybuy.data.Product
import com.creativeitinstitute.easybuy.data.repository.AuthRepository
import com.creativeitinstitute.easybuy.data.repository.SellerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductUploadViewModel @Inject constructor(private val repo : SellerRepository) : ViewModel() {

    private val _productUploadResponse = MutableLiveData<DataState<String>>()
    val productUploadResponse: LiveData<DataState<String>> = _productUploadResponse


    fun productUpload(product: Product) {
        _productUploadResponse.postValue(DataState.Loading())

        val imageUri : Uri = product.imageLink.toUri()

        repo.uploadProductImage(imageUri).addOnSuccessListener {snapshot->

            snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { url->

                product.imageLink  =url.toString()

                repo.uploadProduct(product).addOnSuccessListener{
                    _productUploadResponse.postValue(DataState.Success("Uploaded and Updated Product Successfully !"))
                }.addOnFailureListener {
                    _productUploadResponse.postValue(DataState.Error("${it.message}"))
                }


            }


        }.addOnFailureListener{
            _productUploadResponse.postValue(DataState.Error("Image Upload Failed!"))

        }

    }

}