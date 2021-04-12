package com.rusgamesapps.horoscrope.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.rusgamesapps.horoscrope.R

class NameFragment : Fragment(R.layout.fragment_name) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_name, container, false)
        val nextButton: Button = view.findViewById(R.id.nextButtonLove)
        val textName: EditText = view.findViewById(R.id.editTextName)
        nextButton.isEnabled = false
        textName.doAfterTextChanged {
            nextButton.isEnabled = true
        }

        nextButton.setOnClickListener {
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                ?.edit()
                ?.putString("Name", textName.text.toString())
                ?.apply()
            activity?.supportFragmentManager?.beginTransaction()?.apply {

                setCustomAnimations(R.anim.fragments_in,R.anim.fragments_out)
                replace(
                    R.id.frameLayout,
                    GenderFragment()
                )
                addToBackStack(null)
                commit()
            }
        }

        return view
    }


}