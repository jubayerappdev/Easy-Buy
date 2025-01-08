package com.creativeitinstitute.easybuy.views.login

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.easybuy.R
import com.creativeitinstitute.easybuy.base.BaseFragment
import com.creativeitinstitute.easybuy.core.DataState
import com.creativeitinstitute.easybuy.databinding.FragmentLoginBinding
import com.creativeitinstitute.easybuy.isEmpty
import com.creativeitinstitute.easybuy.views.dashboard.seller.SellerDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    private val viewModel: LoginViewModel by viewModels()


    override fun setListener() {

        with(binding) {
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()


                if (!etEmail.isEmpty() && !etPassword.isEmpty()) {

                    Toast.makeText(context, "All input done !", Toast.LENGTH_LONG).show()
                    val user = UserLogin(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                    viewModel.userLogin(user)
                    loading.show()
                }
            }
        }


    }

    override fun allObserver() {
        viewModel.loginResponse.observe(viewLifecycleOwner){

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
                    startActivity(Intent(requireContext(), SellerDashboard::class.java))
                    requireActivity().finish()

                }
            }

        }


    }


}