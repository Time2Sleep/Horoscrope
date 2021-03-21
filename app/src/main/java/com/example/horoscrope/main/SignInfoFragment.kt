package com.example.horoscrope.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.horoscrope.R


class SignInfoFragment : Fragment(R.layout.fragment_sign_info) {

    val bgs = arrayOf(R.drawable.contentbg_today, R.drawable.contentbg_tomorrow,R.drawable.contentbg_week,R.drawable.contentbg_month)
    var textViewSign: TextView? = null
    var signImage: ImageView? = null
    var signsDate: TextView? = null
    var signId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_info, container, false)

        textViewSign = view?.findViewById(R.id.textView_sign)
        signImage = view?.findViewById(R.id.signImage)
        signsDate= view?.findViewById(R.id.signsDate)
        signId = activity!!.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE).getInt("Sign",0)
        var nextSignButton: ImageButton = view.findViewById(R.id.nextSignButton)
        var prevSignButton: ImageButton = view.findViewById(R.id.prevSignButton)

        changeSign(signId)

        nextSignButton.setOnClickListener {
            changeSign(signId+1)
        }
        prevSignButton.setOnClickListener {
            changeSign(signId-1)
        }

        var text:TextView = view.findViewById(R.id.textView2)
        var contentLayout: ConstraintLayout = view.findViewById(R.id.contentConstraintLayout)
        text.text = activity?.resources?.getStringArray(R.array.texts)?.get(0) ?: "Text not found"
        var buttons:Array<TextView> = arrayOf(view.findViewById(R.id.buttonToday),view.findViewById(R.id.buttonTomorrow),view.findViewById(R.id.buttonWeek),view.findViewById(R.id.buttonMonth))
        for (i in buttons.indices){
            buttons[i].setOnClickListener {
                for(btn in buttons) btn.setTextColor(Color.parseColor("#242F4B"))
                buttons[i].setTextColor(Color.parseColor("#8D4E1F"))
                contentLayout.setBackgroundResource(bgs[i])
                text.text = activity?.resources?.getStringArray(R.array.texts)?.get(i) ?: "Text not found"
            }
        }
        return view
    }

    fun changeSign(id:Int){
        if(id>11) signId = 0
        else if(id<0)  signId = 11
        else signId = id

        signImage?.setImageResource(resources.obtainTypedArray(R.array.signsImages).getResourceId(signId, 0))
        textViewSign?.text = resources.getStringArray(R.array.signs)[signId]
        signsDate?.text = resources.getStringArray(R.array.signs_date)[signId]
    }
}