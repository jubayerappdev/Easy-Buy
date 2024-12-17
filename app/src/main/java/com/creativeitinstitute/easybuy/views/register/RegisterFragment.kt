package com.creativeitinstitute.easybuy.views.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.creativeitinstitute.easybuy.R
import com.creativeitinstitute.easybuy.base.BaseFragment
import com.creativeitinstitute.easybuy.core.DataState
import com.creativeitinstitute.easybuy.databinding.FragmentRegisterBinding
import com.creativeitinstitute.easybuy.isEmpty
import com.creativeitinstitute.easybuy.views.dashboard.DashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {



    private val viewModel: RegistrationViewModel by viewModels()




    override fun setListener() {

        with(binding){
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }

            btnRegister.setOnClickListener {

                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()

                if (!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()){

//                    Toast.makeText(context, "All input done !", Toast.LENGTH_LONG).show()

                    val user = UserRegister(
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        "Seller",
                        ""
                    )


                    viewModel.userRegistration(user)
                }


            }
        }
    }

    override fun allObserver() {
        registrationObserver()
    }

    //OOAD -> Object Oriented Analysis Design (Code Design)
    private fun registrationObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loading.show()

                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context, "created User : ${it.data}", Toast.LENGTH_LONG).show()

                    findNavController().navigate(R.id.action_registerFragment_to_dashFragment)


                }
            }

        }

    }


}