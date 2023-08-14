package com.orange.newsapp

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.orange.newsapp.databinding.ActivityMainBinding
import com.orange.newsapp.utils.ConnectivityReceiver
import com.orange.newsapp.utils.gone
import com.orange.newsapp.utils.visible
import com.orange.orangetask.ViewsManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    AppCompatActivity(),
    ConnectivityReceiver.ConnectivityReceiverListener,
    ViewsManager {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolBarTitle.text = binding.toolbar.title

        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        initConnectivityReceiver()
    }

    private fun showSnackBar() {
        snackBar = Snackbar.make(
            binding.root,
            getString(R.string.no_internet),
            Snackbar.LENGTH_INDEFINITE
        ).setActionTextColor(ContextCompat.getColor(this, R.color.purple_200))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .setAction(getString(R.string.reconnect)) {
                AlertDialog.Builder(this)
                    .setMessage(R.string.no_internet)
                    .setPositiveButton(R.string.action_settings) { paramDialogInterface, _ ->
                        startActivity(
                            Intent(
                                Settings.ACTION_WIRELESS_SETTINGS
                            )
                        )
                        paramDialogInterface.dismiss()
                    }
                    .setNegativeButton(R.string.cancel) { _, _ ->
                        snackBar?.show()
                    }.setCancelable(false)
                    .show()
            }
        snackBar?.show()
    }

    override fun onStop() {
        super.onStop()
        try {
            // Register or UnRegister your broadcast receiver here
            unregisterReceiver(ConnectivityReceiver())
        } catch (ex: IllegalArgumentException) {
            // ignore this error
        }
    }

    // call in onStart() or onResume()
    private fun initConnectivityReceiver() {
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            showSnackBar()
        } else {
            if (snackBar != null) {
                if (snackBar?.isShown!!) {
                    snackBar?.dismiss()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            // Register or UnRegister your broadcast receiver here
            unregisterReceiver(ConnectivityReceiver())
        } catch (ex: IllegalArgumentException) {
        }
    }

    override fun showLoading() {
        binding.progressBar.visible()
    }

    override fun hideLoading() {
        binding.progressBar.gone()
    }
}
