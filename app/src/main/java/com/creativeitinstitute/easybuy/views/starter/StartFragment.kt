package com.creativeitinstitute.easybuy.views.starter

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.easybuy.R
import com.creativeitinstitute.easybuy.base.BaseFragment
import com.creativeitinstitute.easybuy.databinding.FragmentStartBinding
import com.creativeitinstitute.easybuy.views.dashboard.seller.SellerDashboard
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {



    override fun setListener() {

        setUpAutoLogin()

        with(binding){
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)

            }
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_registerFragment)

            }
        }


    }

    private fun setUpAutoLogin() {
        FirebaseAuth.getInstance().currentUser?.let {
            startActivity(Intent(requireContext(), SellerDashboard::class.java))
            requireActivity().finish()
        }

    }

    override fun allObserver() {


    }


}