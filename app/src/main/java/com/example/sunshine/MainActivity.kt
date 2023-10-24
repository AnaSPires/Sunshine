package com.example.sunshine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.sunshine.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_activity_forecast) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener{ navController: NavController, navDestination: NavDestination, bundle: Bundle? ->
            if(navDestination.label?.equals(getString(R.string.weather_forecast_list)) == true)
            {
                binding.settingsIcon.visibility = View.VISIBLE
            }
            else{
                binding.settingsIcon.visibility = View.GONE
            }
        }

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.settingsIcon.visibility = View.VISIBLE

        binding.settingsIcon.setOnClickListener {
            val action =
                FragmentForecastListDirections.actionToSettingsFragment()
            binding.settingsIcon.visibility = View.GONE
            navController.navigate(action)
        }

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_activity_forecast) as NavHostFragment
        val navController = navHostFragment.navController

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}