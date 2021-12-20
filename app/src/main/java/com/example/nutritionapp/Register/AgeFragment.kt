package com.example.nutritionapp.Register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nutritionapp.R
import com.example.nutritionapp.databinding.FragmentAgeBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class AgeFragment : Fragment() {
    lateinit var age: TextView
    lateinit var incage: ImageView
    lateinit var decage: ImageView
    lateinit var next: ExtendedFloatingActionButton
    lateinit var back: ExtendedFloatingActionButton
    private var binding: FragmentAgeBinding? = null
    var registerMain: RegisterMain? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAgeBinding.inflate(inflater, container, false)
        val rootView: View = binding!!.root
        age = rootView.findViewById(R.id.fragment_age_currentAge)
        incage = rootView.findViewById(R.id.fragment_age_incrementAge)
        decage = rootView.findViewById(R.id.fragment_age_decrementAge)
        next = rootView.findViewById(R.id.age_next)
        back = rootView.findViewById(R.id.age_back)
        var weight = 50
        val bundleAge: Int
        var bundleHeight = 180
        var active = "light"
        //  to get the data
        val bundle = this.arguments
        if (bundle != null) {
            weight = bundle.getInt("weight", 50)
            bundleAge = bundle.getInt("age", 18)
            bundleHeight = bundle.getInt("height", 180)
            active = bundle.getString("active", "light")
            age.setText(bundleAge.toString())
        }
        if (bundle == null) {
            age.setText("18")
        }
        val age1 = arrayOf(intArrayOf(age.getText().toString().toInt()))
        val age2 = arrayOf("18")
        incage.setOnClickListener(View.OnClickListener { view: View? ->
            age1[0][0] += 1
            age2[0] = age1[0][0].toString()
            age.setText(age2[0])
        })
        decage.setOnClickListener(View.OnClickListener { view: View? ->
            if (age1[0][0] > 0) {
                age1[0][0] -= 1
                age2[0] = age1[0][0].toString()
                age.setText(age2[0])
            }
        })
        registerMain = activity as RegisterMain?
        val finalWeight = weight
        val finalActive = active
        val finalBundleHeight = bundleHeight
        next.setOnClickListener(View.OnClickListener { view: View? ->
            var valid = true
            if (age2[0] == "0") {
                Toast.makeText(context, "Age cannot be 0", Toast.LENGTH_SHORT).show()
                valid = false
            }
            if (valid) {
                val weightFragment = WeightFragment()
                sendInfo(weightFragment, finalWeight, finalBundleHeight, finalActive)
                requireFragmentManager().beginTransaction()
                        .replace(R.id.register_container, weightFragment)
                        .commit()
            }
            registerMain!!.myEdit!!.putString("age", age.getText().toString())
            registerMain!!.myEdit!!.commit()
        })
        back.setOnClickListener(View.OnClickListener { view: View? ->
            val genderFragment = GenderFragment()
            sendInfo(genderFragment, finalWeight, finalBundleHeight, finalActive)
            requireFragmentManager().beginTransaction()
                    .replace(R.id.register_container, genderFragment)
                    .commit()
        })
        return rootView
    }

    private fun sendInfo(fragment: Fragment, weight: Int, height: Int, active: String) {
        var selectedMale = false
        var selectedFemale = false
        // get data from SharedPreferences if next/back is pressed
        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         */
        val sendAge = age!!.text.toString().toInt()
        val sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val name = sh.getString("name", "")
        val email = sh.getString("email", "")
        val mobile = sh.getString("mobile", "")
        val passwd = sh.getString("pass", "")
        val age = sh.getString("age", "")
        val gender = sh.getString("gender", "")
        var intAge = 0
        try {
            intAge = age!!.toInt()
        } catch (e: NumberFormatException) {
            println("not a number")
        }
        if (gender == "Male") selectedMale = true else selectedFemale = true

        // add data to bundle
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("email", email)
        bundle.putString("mobile", mobile)
        bundle.putString("pass", passwd)
        bundle.putBoolean("selectedMale", selectedMale)
        bundle.putBoolean("selectedFemale", selectedFemale)
        bundle.putInt("age", intAge)
        bundle.putInt("weight", weight)
        bundle.putInt("height", height)
        bundle.putString("active", active)
        fragment.arguments = bundle
    }
}