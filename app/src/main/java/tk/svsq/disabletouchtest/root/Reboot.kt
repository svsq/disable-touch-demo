package tk.svsq.disabletouchtest.root

class Reboot : ExecuteAsRootBase() {
    override val commandsToExecute = arrayOf("reboot")
}