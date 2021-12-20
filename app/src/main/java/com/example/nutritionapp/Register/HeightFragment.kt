package com.example.nutritionapp.Register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nutritionapp.R
import com.example.nutritionapp.databinding.FragmentHeightBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class HeightFragment : Fragment() {
    private var binding: FragmentHeightBinding? = null
    private val sharedViewModel: SharedViewModel? = null
    lateinit var currentHeight: TextView
    lateinit var heightBar: SeekBar
    lateinit var next: ExtendedFloatingActionButton
    lateinit var back: ExtendedFloatingActionButton
    var registerMain: RegisterMain? = null
    var currentProgress = 0
    var mintProgress = "160"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHeightBinding.inflate(inflater, container, false)
        val rootView: View = binding!!.root
        currentHeight = rootView.findViewById(R.id.fragment_height_currentHeight)
        heightBar = rootView.findViewById<View>(R.id.fragment_height_heightBar) as SeekBar
        next = rootView.findViewById(R.id.height_next)
        back = rootView.findViewById(R.id.height_back)
        heightBar!!.max = 300
        heightBar!!.progress = 160
        heightBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                currentProgress = i
                mintProgress = currentProgress.toString()
                currentHeight.setText(mintProgress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        var bundleHeight = 180
        val age = 18
        var active = "Light"
        //  to get the data
        val bundle = this.arguments
        if (bundle != null) {
            bundleHeight = bundle.getInt("height", 180)
            active = bundle.getString("active", "Light")
            heightBar!!.progress = bundleHeight
        }
        if (bundle == null) {
            heightBar!!.progress = 180
        }
        registerMain = activity as RegisterMain?
        val finalActive = active
        next.setOnClickListener(View.OnClickListener { view: View? ->
            var valid = true
            if (mintProgress == "0") {
                Toast.makeText(context, "Height cannot be 0", Toast.LENGTH_SHORT).show()
                valid = false
            }
            if (valid) {
                val activeFragment = ActiveFragment()
                sendInfo(activeFragment, finalActive)
                requireFragmentManager().beginTransaction()
                        .replace(R.id.register_container, activeFragment)
                        .commit()
            }
            registerMain!!.myEdit!!.putString("height", currentHeight.getText().toString())
            registerMain!!.myEdit!!.commit()
        })
        back.setOnClickListener(View.OnClickListener { view: View? ->
            val weightFragment = WeightFragment()
            sendInfo(weightFragment, finalActive)
            requireFragmentManager().beginTransaction()
                    .replace(R.id.register_container, weightFragment)
                    .commit()
        })
        return rootView
    }

    private fun sendInfo(fragment: Fragment, active: String) {
        var selectedMale = false
        var selectedFemale = false
        val sendHeight = heightBar!!.progress

        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         */

        // get data from SharedPreferences if next/back is pressed
        val sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val name = sh.getString("name", "")
        val email = sh.getString("email", "")
        val mobile = sh.getString("mobile", "")
        val passwd = sh.getString("pass", "")
        val age = sh.getString("age", "")
        val gender = sh.getString("gender", "")
        val weight = sh.getString("weight", "")
        val intWeight = weight!!.toInt()
        if (gender == "Male") selectedMale = true else selectedFemale = true
        val intAge = age!!.toInt()
        // add data to bundle
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("email", email)
        bundle.putString("mobile", mobile)
        bundle.putString("pass", passwd)
        bundle.putBoolean("selectedMale", selectedMale)
        bundle.putBoolean("selectedFemale", selectedFemale)
        bundle.putInt("age", intAge)
        bundle.putInt("weight", intWeight)
        bundle.putInt("height", sendHeight)
        bundle.putString("active", active)
        fragment.arguments = bundle
    }
}