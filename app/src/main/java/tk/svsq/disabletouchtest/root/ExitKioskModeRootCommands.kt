package tk.svsq.disabletouchtest.root

class ExitKioskModeRootCommands : ExecuteAsRootBase() {
    override val commandsToExecute = arrayOf("pm enable com.android.launcher3\n", "pm enable com.android.launcher3\n")
}