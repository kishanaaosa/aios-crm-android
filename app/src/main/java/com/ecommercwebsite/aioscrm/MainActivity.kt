package com.ecommercwebsite.aioscrm

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.bind.GenericRecyclerViewAdapter
import com.ecommercwebsite.aioscrm.databinding.ActivityMainBinding
import com.ecommercwebsite.aioscrm.databinding.ItemMenuBinding
import com.ecommercwebsite.aioscrm.ui.slidermenu.SideMenuModel
import com.ecommercwebsite.aioscrm.utils.DebugLog
import com.ecommercwebsite.aioscrm.utils.HomeToolbarClickHandler
import com.ecommercwebsite.aioscrm.utils.MyPreference
import com.ecommercwebsite.aioscrm.utils.PrefKey
import com.ecommercwebsite.aioscrm.utils.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), HomeToolbarClickHandler {

    private var dialog: Dialog? = null
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelBase
    lateinit var navHostFragment: NavHostFragment
    private var sideMenuList = ArrayList<SideMenuModel>()


    @Inject
    lateinit var mPref: MyPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DebugLog.e("onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        init()
        val navView: BottomNavigationView = binding.navView
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(
            binding.navigationView,
            navHostFragment.navController,
            false
        )
        setupSideMenu()
    }

    fun init() {
        binding.layToolbar.clickListener = this
        viewModel = ViewModelProvider(this)[ViewModelBase::class.java]
        setToolbarClickListener(this)
    }

    fun setToolbarClickListener(clickListener: Any) {
        binding.layToolbar.clickListener = clickListener as HomeToolbarClickHandler?
    }

    fun setupSideMenu() {
        binding.layoutSideMenuDrawer.flag = mPref.getValueBoolean(PrefKey.IS_LOGIN, false)

        binding.layoutSideMenuDrawer.btnMyAccount.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            navHostFragment.findNavController().navigate(R.id.myAccountFragment)
        }

        sideMenuList = getSideMenuList()
        binding.layoutSideMenuDrawer.rvMenu.adapter =
            object :
                GenericRecyclerViewAdapter<SideMenuModel, ItemMenuBinding>(
                    this,
                    sideMenuList
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_menu

                override fun onBindData(
                    model: SideMenuModel,
                    position: Int,
                    dataBinding: ItemMenuBinding
                ) {
                    dataBinding.menu = model
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: SideMenuModel, position: Int) {
                    notifyDataSetChanged()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    when (model.id) {

                        "menuOption1" -> {
                            navHostFragment.findNavController().navigate(R.id.toDoFragment)
                        }

                        "menuOption2" -> {
                            navHostFragment.findNavController().navigate(R.id.salesFragment)

                        }

                        "menuOption3" -> {
                            navHostFragment.findNavController().navigate(R.id.settingsFragment)
                        }

                        "menuOption4" -> {
                            viewModel.showSnackbarMessage("logout")

                        }

                        else -> {

                        }

                    }
                }
            }
    }

    private fun getSideMenuList(): java.util.ArrayList<SideMenuModel> {
        val mSideMenuList = ArrayList<SideMenuModel>()
        val menuOption1 =
            SideMenuModel(
                "menuOption1",
                getDrawableValue(R.drawable.tmp_ic_home),
                "ToDo",
                true,
                R.id.toDoFragment,
                true
            )
        if (menuOption1.isVisible == true) {
            mSideMenuList.add(menuOption1)
        }
        val menuOption2 =
            SideMenuModel(
                "menuOption2",
                getDrawableValue(R.drawable.tmp_ic_home),
                "Sales",
                true,
                R.id.salesFragment,
                true
            )
        if (menuOption2.isVisible == true) {
            mSideMenuList.add(menuOption2)
        }
        val menuOption3 =
            SideMenuModel(
                "menuOption3",
                getDrawableValue(R.drawable.ic_settings),
                "Settings",
                true,
                R.id.settingsFragment,
                true
            )
        if (menuOption3.isVisible == true) {
            mSideMenuList.add(menuOption3)
        }
        val menuOption4 =
            SideMenuModel(
                "menuOption4",
                getDrawableValue(R.drawable.ic_logout),
                "Logout",
                true,
                R.id.splashFragment,
                true
            )
        if (menuOption4.isVisible == true) {
            mSideMenuList.add(menuOption4)
        }
        return mSideMenuList
    }


    private fun getDrawableValue(id: Int): Drawable? {
        return ResourcesCompat.getDrawable(resources, id, null)
    }


    /**
     * This method is used for Navigating from One Screen to Next Screen using Navigation
     * Direction graph.
     * @param navigationId This is the direction Id from Navigation graph
     */
    fun navigateToNextScreenThroughDirections(navigationId: NavDirections) {
        try {
            navHostFragment.findNavController().navigate(navigationId)
        } catch (e: Exception) {
            DebugLog.print(e)
        }
    }

    /**
     * Show Progress dialog
     * @param t: true show progress bar
     *
     *  */
    fun displayProgress(t: Boolean) {
        // binding.loading = t
        if (t) {
            if (dialog == null) {
                dialog = Utils.progressDialog(this)
            }
            dialog?.show()
        } else {
            dialog?.dismiss()
        }
    }

    /**
     * This method is used to hide the keyboard.
     */
    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * Toolbar manages items and visibility according to
     */
    fun toolBarManagement(toolbarModel: ToolbarModel?) {
        if (toolbarModel != null) {
            binding.layToolbar.toolbarModel = toolbarModel
            if (toolbarModel.isBottomNavVisible) {
                binding.navView.visibility = View.VISIBLE
            } else {
                binding.navView.visibility = View.GONE
            }

        }
    }

    fun setStatusBarColor(color: Int, isLightStatusBar: Boolean) {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, color)
        WindowCompat.getInsetsController(window, window.decorView)?.apply {
            isAppearanceLightStatusBars = isLightStatusBar
        }
    }

    /*  override fun onBackClick() {
          navHostFragment.navController.popBackStack()
      }

      override fun onTitleClick() {
          // TODO("Not yet implemented")
      }*/

    override fun onMenuClick() {
        sideMenuOpen()
    }

    override fun onNotificationClick() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        navHostFragment.findNavController().navigate(R.id.notificationFragment)
    }

    override fun onBackClick() {
        onBackPressedDispatcher.onBackPressed()
    }

    fun sideMenuOpen() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}