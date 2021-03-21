package com.example.horoscrope.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.horoscrope.R

class GenderFragment : Fragment(R.layout.fragment_gender) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gender, container, false)
        val helloName: TextView? = view?.findViewById(R.id.textViewHelloName)
        val name: String? = activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.getString("Name", "LOH")
        helloName?.text = "Привет, $name"

        val nextButton: Button? = view?.findViewById(R.id.nextButton)
        nextButton?.isEnabled = false
        var isMale = true
        val maleButton: ImageButton? = view?.findViewById(R.id.imageButton_man)
        val femaleButton: ImageButton? = view?.findViewById(R.id.imageButton_woman)

        maleButton?.setOnClickListener{
            maleButton.setImageResource(R.drawable.settings_man_selected)
            maleButton.alpha = 1f
            femaleButton?.setImageResource(R.drawable.settings_woman)
            femaleButton?.alpha = 0.3f
            isMale = true
            nextButton?.isEnabled = true
        }

        femaleButton?.setOnClickListener {
            femaleButton.setImageResource(R.drawable.setting_woman_selected)
            maleButton?.alpha = 0.3f
            maleButton?.setImageResource(R.drawable.settings_man)
            femaleButton.alpha = 1f
            isMale = false
            nextButton?.isEnabled = true
        }

        nextButton?.setOnClickListener {
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                ?.edit()
                ?.putBoolean("isMale", isMale)
                ?.apply()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(
                    R.id.frameLayout,
                    BirthFragment()
                )
                addToBackStack(null)
                commit()
            }
        }
        return view
    }

}