package com.example.horoscrope.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.fragment.app.Fragment
import com.example.horoscrope.R

class LoveFragment : Fragment(R.layout.fragment_love) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var isMaleEditing:Boolean = true;
        val view = inflater.inflate(R.layout.fragment_love, container, false)
        var btn: Button = view.findViewById(R.id.nextButtonLove)
        var maleBtn: ImageButton = view.findViewById(R.id.maleSignBtn)
        var femaleBtn: ImageButton = view.findViewById(R.id.femaleSignBtn)
        var maleTitle: TextView = view.findViewById(R.id.maleSignTitle)
        var femaleTitle: TextView = view.findViewById(R.id.femaleSignTitle)
        var compatGuidline: Guideline = view.findViewById(R.id.compatibilityGuidline)
        var signsScroll: HorizontalScrollView = view.findViewById(R.id.signsScroll)
        var maleSignId: Int = 0;
        var femaleSignId: Int = 0;

        signsScroll?.visibility = View.INVISIBLE;


        fun setSign(signId: Int, btn: ImageButton, textView: TextView){
            btn.setImageResource(resources.obtainTypedArray(R.array.signsImages).getResourceId(signId, 0))
            textView.text = resources.getStringArray(R.array.signs)[signId]
        }

        fun openSignsSelector(){
            compatGuidline?.setGuidelinePercent(0.7f);
            btn?.visibility = View.INVISIBLE;
            signsScroll?.visibility = View.VISIBLE;
        }

        fun closeSignsSelector(index: Int){
            compatGuidline?.setGuidelinePercent(0.8f);
            btn?.visibility = View.VISIBLE;
            signsScroll?.visibility = View.INVISIBLE;
            if(isMaleEditing){
                setSign(index, maleBtn, maleTitle)
                maleSignId = index
            }else{
                setSign(index, femaleBtn, femaleTitle)
                femaleSignId = index
            }
        }


        for(iBtn in 0 until (signsScroll?.getChildAt(0) as ConstraintLayout).childCount){
            (signsScroll?.getChildAt(0) as ConstraintLayout).getChildAt(iBtn).setOnClickListener{
                closeSignsSelector(iBtn)
            }
        }

        setSign(0, maleBtn, maleTitle)
        setSign(0, femaleBtn, femaleTitle)

        maleBtn.setOnClickListener {
            openSignsSelector();
            isMaleEditing = true;
        }
        femaleBtn.setOnClickListener {
            openSignsSelector();
            isMaleEditing = false;
        }

        var compatFragment: Fragment = CompatibilityFragment()
        btn.setOnClickListener {
            compatFragment.arguments = Bundle().apply {
                putInt("maleSignId", maleSignId)
                putInt("femaleSignId", femaleSignId)
            }
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(
                    R.id.mainFrameLayout,
                    compatFragment
                )
                commit()
            }
        }
        return view
    }


}