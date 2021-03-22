package tk.svsq.disabletouchtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import tk.svsq.disabletouchtest.R

class ClickFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        return inflater.inflate(R.layout.fragment_click_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var count = 0

        view.setOnClickListener {
            count++
            if(count == 5) {
                Toast.makeText(context, "Touch unlocked", Toast.LENGTH_SHORT).show()
                count = 0
            }
        }
    }
}