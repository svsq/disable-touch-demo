package tk.svsq.disabletouchtest.receiver

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.os.UserHandle
import android.util.Log

open class Admin : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)

        Log.d("Device Admin: ", "Enabled")
    }

    override fun onDisableRequested(context: Context, intent: Intent): CharSequence? {
        return "Admin disable requested"
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)

        Log.d("Device Admin: ", "Disabled")
    }

    override fun onPasswordChanged(context: Context, intent: Intent, user: UserHandle) {
        super.onPasswordChanged(context, intent, user)

        Log.d("Device Admin: ", "Password Changed")
    }
}