package tk.svsq.disabletouchtest

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        setupFullScreen()

        if(kioskActivated == true) enableKioskMode()
    }

    private fun setupFullScreen() {
        window.apply {
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    @Suppress("DEPRECATION")
    fun enableKioskMode() {
        if(ExecuteAsRootBase.canRunRootCommands()) {
            /*DisableStdLauncherRootCommands().execute()
            DisableSystemUiRootCommands().execute()*/
            //EnterKioskModeRootCommands().execute()
            runRootCommands()
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

        if(ExecuteAsRootBase.canRunRootCommands()) {
            /*DisableStdLauncherRootCommands().execute()
            DisableSystemUiRootCommands().execute()*/
            //ExitKioskModeRootCommands().execute()
        } else {
            Toast.makeText(this, "Cannot run ROOT commands from this app", Toast.LENGTH_SHORT).show()
        }

        kioskActivated = false
    }

    private fun runRootCommands() {
        val deviceCommands = arrayOf("su", "pm disable com.android.launcher3", "pm disable com.android.systemui", "pm disable com.android.settings")
        try {
            Runtime.getRuntime().exec(deviceCommands)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "error!", Toast.LENGTH_SHORT).show()
        }

        /*try {
            val p = Runtime.getRuntime().exec("su")
            val outputStream = DataOutputStream(p.outputStream)
            outputStream.writeBytes("pm disable com.android.launcher3\n")
            outputStream.flush()
            outputStream.writeBytes("pm disable com.android.systemui\n")
            outputStream.flush()
            outputStream.writeBytes("exit\n")
            outputStream.flush()
            p.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
    }

    /*private fun disableSystemBar() {
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
    }*/

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