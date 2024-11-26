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
import com.creativeitinstitute.easybuy.core.DataState
import com.creativeitinstitute.easybuy.databinding.FragmentRegisterBinding
import com.creativeitinstitute.easybuy.isEmpty
import com.creativeitinstitute.easybuy.views.dashboard.DashFragment


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setListener()
        registrationObserver()
        return binding.root
    }

    private fun registrationObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    Toast.makeText(context, "Loading....", Toast.LENGTH_LONG).show()
                }
                is DataState.Success -> {
                    Toast.makeText(context, "created User : ${it.data}", Toast.LENGTH_LONG).show()


                }
            }

        }

    }

    private fun setListener() {

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


}