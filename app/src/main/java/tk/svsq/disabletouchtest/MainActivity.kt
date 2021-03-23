package tk.svsq.disabletouchtest

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import tk.svsq.disabletouchtest.root.DisableStdLauncherRootCommands
import tk.svsq.disabletouchtest.root.DisableSystemUiRootCommands
import tk.svsq.disabletouchtest.root.ExecuteAsRootBase
import java.io.DataOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    var kioskActivated: Boolean? = null

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

        if(kioskActivated == true) enableKioskMode()
    }

    @Suppress("DEPRECATION")
    fun enableKioskMode() {
        /*window.apply {
            //addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }*/

        //disableSystemBar()

        if(ExecuteAsRootBase.canRunRootCommands()) {
            DisableSystemUiRootCommands().execute()

            DisableStdLauncherRootCommands().execute()
        } else {
            Toast.makeText(this, "Cannot run ROOT commands from this app", Toast.LENGTH_SHORT).show()
        }

        kioskActivated = true
    }

    @Suppress("DEPRECATION")
    fun disableKioskMode() {
        /*window.apply {
            //clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }*/

        //enableSystemBar()

        kioskActivated = false
    }

    private fun disableSystemBar() {
        try {
            //REQUIRES ROOT
            Runtime.getRuntime().exec(
                arrayOf(
                    "su", "-c",
                    "service call activity 42 s16 com.android.systemui"
                )
            ).waitFor()
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun enableSystemBar() {
        try {
            Runtime.getRuntime().exec(
                arrayOf(
                    "am", "startservice", "-n",
                    "com.android.systemui/.SystemUIService"
                )
            )
        } catch (e: IOException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
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