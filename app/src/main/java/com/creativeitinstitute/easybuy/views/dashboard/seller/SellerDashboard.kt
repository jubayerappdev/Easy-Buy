package com.creativeitinstitute.easybuy.views.dashboard.seller

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.creativeitinstitute.easybuy.R
import com.creativeitinstitute.easybuy.databinding.ActivitySellerDashboardBinding
import com.creativeitinstitute.easybuy.views.starter.MainActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerDashboard : AppCompatActivity() {

    private lateinit var binding: ActivitySellerDashboardBinding
    lateinit var navController : NavController

    @Inject
    lateinit var jAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


      navController = findNavController(R.id.fragmentContainerView)
        val appBarConfig = AppBarConfiguration(setOf(

            R.id.myProductFragment,
            R.id.uploadProductFragment,
            R.id.sellerProfileFragment,
        ))

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfig)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.seller_top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_logout ->{
                jAuth.signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            R.id.menu_settings ->{
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_LONG).show()
            }
            R.id.menu_info ->{
                Toast.makeText(this, "Info Clicked", Toast.LENGTH_LONG).show()
            }
        }


        return super.onOptionsItemSelected(item)
    }

}