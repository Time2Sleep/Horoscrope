package com.rusgamesapps.horoscrope.settings

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rusgamesapps.horoscrope.R

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

        val nextButton: Button? = view?.findViewById(R.id.nextButtonLove)
        nextButton?.isEnabled = false
        var isMale = true
        val maleButton: ImageButton? = view?.findViewById(R.id.imageButton_man)
        val femaleButton: ImageButton? = view?.findViewById(R.id.imageButton_woman)
        val maleText: TextView = view.findViewById(R.id.maleText)
        val femaleText: TextView = view.findViewById(R.id.femaleText)

        maleButton?.setOnClickListener{
            maleButton.setImageResource(R.drawable.male_choose_selected)
            maleButton.alpha = 1f
            maleText.alpha = 1f
            maleText.setTextColor(Color.parseColor("#8D4E1F"))
            femaleButton?.setImageResource(R.drawable.female_choose)
            femaleButton?.alpha = 0.3f
            femaleText.alpha = 0.3f
            femaleText.setTextColor(Color.parseColor("#242F4B"))
            isMale = true
            nextButton?.isEnabled = true
        }

        femaleButton?.setOnClickListener {
            femaleButton.setImageResource(R.drawable.female_choose_selected)
            maleButton?.alpha = 0.3f
            maleText.alpha = 0.3f
            maleText.setTextColor(Color.parseColor("#242F4B"))
            maleButton?.setImageResource(R.drawable.male_choose)
            femaleButton.alpha = 1f
            femaleText.alpha = 1f
            femaleText.setTextColor(Color.parseColor("#8D4E1F"))

            isMale = false
            nextButton?.isEnabled = true
        }

        nextButton?.setOnClickListener {
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                ?.edit()
                ?.putBoolean("isMale", isMale)
                ?.apply()
            activity?.supportFragmentManager?.beginTransaction()?.apply {

                setCustomAnimations(R.anim.fragments_in,R.anim.fragments_out)
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