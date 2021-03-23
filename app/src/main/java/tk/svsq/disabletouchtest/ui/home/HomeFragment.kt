package tk.svsq.disabletouchtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tk.svsq.disabletouchtest.MainActivity
import tk.svsq.disabletouchtest.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var textView: TextView? = null
    var button: Button? = null

    private val onClickListener = View.OnClickListener {
        (activity as? MainActivity)?.let {
            when (it.kioskActivated) {
                false -> {
                    turnKioskOn()
                }
                else -> {
                    turnKioskOff()
                }
            }
        }
    }

    /*private fun startTimer() {

        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = (millisUntilFinished / 1000).toInt()
                textView?.text = "Touch is disabled. You can't press anything for\n$secondsLeft seconds"
            }

            override fun onFinish() {
                button?.isEnabled = true
                textView?.text = "This is home Fragment"
                turnKioskOff()
            }
        }

        timer.start()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        textView = root.findViewById(R.id.text_home)
        button = root.findViewById(R.id.button)

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView?.text = it
        }

        button?.setOnClickListener(onClickListener)

        return root
    }

    private fun turnKioskOn() {
        (activity as MainActivity).enableKioskMode()
        textView?.text = "KIOSK mode is ON"
        button?.text = "KIOSK off"
    }

    private fun turnKioskOff() {
        (activity as MainActivity).disableKioskMode()
        textView?.text = "KIOSK mode is OFF"
        button?.text = "KIOSK on"
    }
}