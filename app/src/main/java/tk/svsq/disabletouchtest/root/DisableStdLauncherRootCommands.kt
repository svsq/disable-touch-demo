package tk.svsq.disabletouchtest.root

class DisableStdLauncherRootCommands : ExecuteAsRootBase() {
    override val commandsToExecute = arrayOf("pm","disable","com.android.launcher3")
}