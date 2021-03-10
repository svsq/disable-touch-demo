package tk.svsq.disabletouchtest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    private var touchDisabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()

        if(touchDisabled) disableTouch()
    }

    @Suppress("DEPRECATION")
    fun disableTouch() {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }

        touchDisabled = true
    }

    @Suppress("DEPRECATION")
    fun enableTouch() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }

        touchDisabled = false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?) =
        when (keyCode) {
            KeyEvent.KEYCODE_HOME -> {
                Log.i("TEST", "PRESSED HOME")
                true
            }
            KeyEvent.KEYCODE_MENU -> {
                Log.i("TEST", "PRESSED MENU")
                true
            }
            KeyEvent.KEYCODE_BACK -> {
                Log.i("TEST", "PRESSED BACK")
                true
            }

            KeyEvent.KEYCODE_VOLUME_UP -> {
                Log.i("TEST", "PRESSED VOLUME UP")
                true
            }

            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Log.i("TEST", "PRESSED VOLUME DOWN")
                true
            }

            else -> super.onKeyDown(keyCode, event)
        }
}