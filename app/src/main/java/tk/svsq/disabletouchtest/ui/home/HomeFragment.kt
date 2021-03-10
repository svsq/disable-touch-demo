package tk.svsq.disabletouchtest.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.util.rangeTo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tk.svsq.disabletouchtest.MainActivity
import tk.svsq.disabletouchtest.R
import kotlin.concurrent.timerTask

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var textView: TextView? = null
    var button: Button? = null

    private val disableTouchListener = View.OnClickListener {
        button?.isEnabled = false
        disableTouch()
        startTimer()
    }

    private fun startTimer() {

        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = (millisUntilFinished / 1000).toInt()
                textView?.text = "Touch is disabled. You can't press anything for\n$secondsLeft seconds"
            }

            override fun onFinish() {
                button?.isEnabled = true
                textView?.text = "This is home Fragment"
                enableTouch()
            }
        }

        timer.start()
    }

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

        button?.setOnClickListener(disableTouchListener)

        return root
    }

    private fun disableTouch() {
        (activity as MainActivity).disableTouch()
    }

    private fun enableTouch() {
        (activity as MainActivity).enableTouch()
    }
}