package com.creativeitinstitute.easybuy.views.dashboard.seller.upload

import android.Manifest
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.creativeitinstitute.easybuy.base.BaseFragment
import com.creativeitinstitute.easybuy.core.DataState
import com.creativeitinstitute.easybuy.core.areAllPermissionGranted
import com.creativeitinstitute.easybuy.core.extract
import com.creativeitinstitute.easybuy.core.requestPermission
import com.creativeitinstitute.easybuy.data.Product
import com.creativeitinstitute.easybuy.databinding.FragmentUploadProductBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class UploadProductFragment :
    BaseFragment<FragmentUploadProductBinding>(FragmentUploadProductBinding::inflate) {

        private val product : Product by lazy() {
            Product()
        }

        private val viewModel: ProductUploadViewModel by viewModels()

    override fun setListener() {

        permissionRequest = getPermissionsRequest()

        binding.apply {

            ivProduct.setOnClickListener {
                requestPermission(permissionRequest, permissionList)
            }

            btnUploadProduct.setOnClickListener {

                loading.show()
                val name = etProductName.extract()
                val price = etProductPrice.extract()
                val description = etProductDescription.extract()
                val amount = etProductAmount.extract()

                FirebaseAuth.getInstance().currentUser?.let {
                    product.apply {
                        this.productID = UUID.randomUUID().toString()
                        this.sellerID = it.uid
                        this.name = name
                        this.price = price.toDouble()
                        this.description = description
                        this.amount = amount.toInt()
                    }
                }




                uploadProduct(product)

            }


        }

    }

    private fun getPermissionsRequest(): ActivityResultLauncher<Array<String>> {

       return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            if (areAllPermissionGranted(permissionList)){
                //ase

                Toast.makeText(requireContext(), "Ase",Toast.LENGTH_LONG).show()

                ImagePicker.with(this)
                    .compress(1024)         //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(512, 512)  //Final image resolution will be less than 1080 x 1080(Optional)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }
            }else{
                //nai
                Toast.makeText(requireContext(), "Nai",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun uploadProduct(product: Product) {

        viewModel.productUpload(product)

    }

    override fun allObserver() {
        viewModel.productUploadResponse.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                }
                is DataState.Loading -> {
                    loading.show()
                }
                is DataState.Success -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
                    loading.dismiss()
                }
            }
        }



    }

    companion object {
        private val permissionList =
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
    }

    private lateinit var permissionRequest:ActivityResultLauncher<Array<String>>

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                Log.d("TAG", "$fileUri ")
                binding.ivProduct.setImageURI(fileUri)
                product.imageLink = fileUri.toString()


            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


}