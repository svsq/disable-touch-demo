package tk.svsq.disabletouchtest.root

class DisableSystemUiRootCommands : ExecuteAsRootBase() {
    override val commandsToExecute = arrayOf("pm","disable","com.android.systemui")
}