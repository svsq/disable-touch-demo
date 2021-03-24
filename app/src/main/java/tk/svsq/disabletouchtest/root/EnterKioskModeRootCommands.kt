package tk.svsq.disabletouchtest.root

class EnterKioskModeRootCommands : ExecuteAsRootBase() {
    override val commandsToExecute = arrayOf("pm disable com.android.launcher3\n", "pm disable com.android.launcher3\n")

}