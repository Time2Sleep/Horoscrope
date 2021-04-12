package com.rusgamesapps.horoscrope.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.rusgamesapps.horoscrope.R
import com.rusgamesapps.horoscrope.main.dto.Sign
import org.json.JSONObject

class CompatibilityFragment : Fragment(R.layout.fragment_compatibility) {
    private var mInterstitialAd: InterstitialAd? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireActivity(),"ca-app-pub-2075070947382135/1649177686", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.e("ads", adError?.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("ads", "Ad was loaded.")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.show(requireActivity())
            }
        })


        val view = inflater.inflate(R.layout.fragment_compatibility, container, false)

        var maleSignImg: ImageView = view.findViewById(R.id.maleSignImg)
        var femaleSignImg: ImageView = view.findViewById(R.id.femaleSignImg)
        var maleSignTextView: TextView = view.findViewById(R.id.maleSignTitleCompat)
        var femaleSignTextView: TextView = view.findViewById(R.id.femaleSignTitleCompat)
        var compatPercentage: TextView = view.findViewById(R.id.compatPercentage)
        var compatProgressBar: ProgressBar = view.findViewById(R.id.compatProgressBar)
        var compatLoveProgressBar: ProgressBar = view.findViewById(R.id.compatLoveProgressBar)
        var compatLovePercentage: TextView = view.findViewById(R.id.compatLovePercentage)
        var compatLoveText: TextView = view.findViewById(R.id.compatLoveText)
        var compatFriendshipProgressBar: ProgressBar =
            view.findViewById(R.id.compatFriendshipProgressBar)
        var compatFriendshipPercentage: TextView =
            view.findViewById(R.id.compatFriendshipPercentage)
        var compatFriendshipText: TextView = view.findViewById(R.id.compatFriendshipText)
        var compatMarriageProgressBar: ProgressBar =
            view.findViewById(R.id.compatMarriageProgressBar)
        var compatMarriagePercentage: TextView = view.findViewById(R.id.compatMarriagePercentage)
        var compatMarriageText: TextView = view.findViewById(R.id.compatMarriageText)

        var maleSignId: Int = arguments?.getInt("maleSignId")!!
        var femaleSignId: Int = arguments?.getInt("femaleSignId")!!

        maleSignImg.setImageResource(
            resources.obtainTypedArray(R.array.signsImages)
                .getResourceId(maleSignId, 0)
        )
        femaleSignImg.setImageResource(
            resources.obtainTypedArray(R.array.signsImages)
                .getResourceId(femaleSignId, 0)
        )
        maleSignTextView.text = resources.getStringArray(R.array.signs)[maleSignId]
        femaleSignTextView.text = resources.getStringArray(R.array.signs)[femaleSignId]
        val male = Sign.values()[maleSignId]
        val female = Sign.values()[femaleSignId]


        AndroidNetworking.get("https://guarded-escarpment-96153.herokuapp.com/api/compat")
            .setPriority(Priority.LOW)
            .addQueryParameter("male", male.name)
            .addQueryParameter("female", female.name)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val totalPercentage = response?.getInt("totalPercentage")
                    compatPercentage.text = toPercent(totalPercentage)
                    compatProgressBar.progress = totalPercentage ?: 0

                    val lovePercentage = response?.getInt("lovePercentage")
                    compatLovePercentage.text = toPercent(lovePercentage)
                    compatLoveProgressBar.progress = lovePercentage ?: 0
                    compatLoveText.text = response?.getString("loveDescription")

                    val friendshipPercentage = response?.getInt("friendshipPercentage")
                    compatFriendshipPercentage.text = toPercent(friendshipPercentage)
                    compatFriendshipProgressBar.progress = friendshipPercentage ?: 0
                    compatFriendshipText.text = response?.getString("friendshipDescription")

                    val marriagePercentage = response?.getInt("marriagePercentage")
                    compatMarriagePercentage.text = toPercent(marriagePercentage)
                    compatMarriageProgressBar.progress = marriagePercentage ?: 0
                    compatMarriageText.text = response?.getString("marriageDescription")
                }

                override fun onError(anError: ANError?) {
                    compatLoveText.text = "Не удалось загрузить данные"
                    compatMarriageText.text = "Не удалось загрузить данные"
                    compatFriendshipText.text = "Не удалось загрузить данные"
                }
            })

        return view
    }

    fun toPercent(percentage: Int?): String {
        return percentage.toString() + "%";
    }
}