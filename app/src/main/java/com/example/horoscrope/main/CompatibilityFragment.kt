package com.example.horoscrope.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.horoscrope.R

class CompatibilityFragment : Fragment(R.layout.fragment_compatibility) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_compatibility, container, false)
        var maleSignImg: ImageView = view.findViewById(R.id.maleSignImg)
        var femaleSignImg: ImageView = view.findViewById(R.id.femaleSignImg)
        var maleSignTextView: TextView = view.findViewById(R.id.maleSignTitleCompat)
        var femaleSignTextView: TextView = view.findViewById(R.id.femaleSignTitleCompat)
        var compatPercentage: TextView = view.findViewById(R.id.compatPercentage)
        var compatProgressBar: ProgressBar = view.findViewById(R.id.compatLoveProgressBar)
        var compatLoveProgressBar: ProgressBar = view.findViewById(R.id.compatLoveProgressBar)
        var compatLovePercentage: TextView = view.findViewById(R.id.compatLovePercentage)
        var compatLoveText: TextView = view.findViewById(R.id.compatLoveText)
        var compatFriendshipProgressBar: ProgressBar = view.findViewById(R.id.compatFriendshipProgressBar)
        var compatFriendshipPercentage: TextView = view.findViewById(R.id.compatFriendshipPercentage)
        var compatFriendshipText: TextView = view.findViewById(R.id.compatFriendshipText)
        var compatMarriageProgressBar: ProgressBar = view.findViewById(R.id.compatMarriageProgressBar)
        var compatMarriagePercentage: TextView = view.findViewById(R.id.compatMarriagePercentage)
        var compatMarriageText: TextView = view.findViewById(R.id.compatMarriageText)

        var maleSignId: Int = arguments?.getInt("maleSignId")!!
        var femaleSignId: Int = arguments?.getInt("femaleSignId")!!

        maleSignImg.setImageResource(resources.obtainTypedArray(R.array.signsImages)
            .getResourceId(maleSignId, 0))
        femaleSignImg.setImageResource(resources.obtainTypedArray(R.array.signsImages)
            .getResourceId(femaleSignId, 0))
        maleSignTextView.text = resources.getStringArray(R.array.signs)[maleSignId]
        femaleSignTextView.text = resources.getStringArray(R.array.signs)[femaleSignId]

        return view
    }
}