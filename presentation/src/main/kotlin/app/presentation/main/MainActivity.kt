package app.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import app.domain.extension.allowReads
import app.presentation.R
import app.presentation.base.preference.Settings
import app.presentation.databinding.ActivityMainBinding
import app.presentation.datastore.DataStoreManager
import app.presentation.extension.collectIn
import app.presentation.extension.setOnReactiveClickListener
import app.presentation.extension.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val navController: NavController by lazy {
        findNavController(R.id.activityMainHostFragment)
    }
    private var uiStateJob: Job? = null

    @Inject
    lateinit var dataStoreManager: DataStoreManager
    val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
        subscribeHeader()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    override fun onBackPressed() {
        if (isTaskRoot
            && supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount == 0
            && supportFragmentManager.backStackEntryCount == 0
        ) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (isTaskRoot && isFinishing) {
            finishAfterTransition()
        }
        super.onDestroy()
    }

    private fun setupUI() {
        lifecycleScope.launch {
            dataStoreManager.themeMode.collectIn(this@MainActivity) { mode ->
                setNightMode(mode)
            }
        }

        binding.headerView.openCV.setOnReactiveClickListener {
            findNavController(R.id.activityMainHostFragment).navigate(R.id.virtualCvFragment)
        }
        subscribeHeader()

    }

    private fun subscribeHeader() {
        mainActivityViewModel.viewSettingsUIModel.observe(this) {
            binding.headerView.viewSettingsUIModel = it
            binding.viewSettingsUIModel = it
        }
    }

    private fun setNightMode(mode: Int) {
        allowReads {
            uiStateJob = lifecycleScope.launchWhenStarted {
                dataStoreManager.setThemeMode(mode)
            }
        }
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_no_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_yes_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(Settings.MODE_NIGHT_DEFAULT)
                }
            }
            else -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_default_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        if (AppCompatDelegate.getDefaultNightMode() != mode)
            AppCompatDelegate.setDefaultNightMode(mode)
    }

}