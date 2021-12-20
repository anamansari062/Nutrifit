package com.example.nutritionapp.Register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.nutritionapp.R
import com.example.nutritionapp.databinding.FragmentGenderBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class GenderFragment : Fragment() {
    private var binding: FragmentGenderBinding? = null
    var typeOfUser = ""
    lateinit var next: ExtendedFloatingActionButton
    lateinit var back: ExtendedFloatingActionButton
    var registerMain: RegisterMain? = null
    lateinit var male: RelativeLayout
    lateinit var female: RelativeLayout
    private var selectedMale = false
    private var selectedFemale = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentGenderBinding.inflate(inflater, container, false)
        val rootView: View = binding!!.root
        male = rootView.findViewById(R.id.fragment_gender_maleLogo)
        female = rootView.findViewById(R.id.fragment_gender_femaleLogo)
        next = rootView.findViewById(R.id.gender_next)
        back = rootView.findViewById(R.id.gender_back)
        var active = "light"
        var weight = 50
        var age = 18
        var height = 180
        //  to get the data
        val bundle = this.arguments
        if (bundle != null) {
            selectedFemale = bundle.getBoolean("selectedFemale", false)
            selectedMale = bundle.getBoolean("selectedMale", false)
            age = bundle.getInt("age", 18)
            weight = bundle.getInt("weight", 50)
            height = bundle.getInt("height", 18)
            active = bundle.getString("active", "light")
            if (selectedFemale) {
                female.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
                typeOfUser = "Female"
            }
            if (selectedMale) {
                male.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
                typeOfUser = "Male"
            }
        }
        male.setOnClickListener(View.OnClickListener { view: View? ->
            male.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
            // to un-focus the female logo if it was selected
            female.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            selectedMale = true
            selectedFemale = false
            typeOfUser = "Male"
        })
        female.setOnClickListener(View.OnClickListener { view: View? ->
            female.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
            // to un-focus the male logo if it was selected
            male.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            selectedFemale = true
            selectedMale = false
            typeOfUser = "Female"
        })
        registerMain = activity as RegisterMain?
        val finalAge = age
        val finalHeight = height
        val finalWeight = weight
        val finalActive = active
        next.setOnClickListener(View.OnClickListener { view: View? ->
            var valid = true
            if (typeOfUser == "") {
                Toast.makeText(context, "This field cannot be empty", Toast.LENGTH_SHORT).show()
                valid = false
            }
            if (valid) {
                val ageFragment = AgeFragment()
                sendInfo(ageFragment, finalAge, finalWeight, finalHeight, finalActive)
                requireFragmentManager().beginTransaction()
                        .replace(R.id.register_container, ageFragment)
                        .commit()
            }
            registerMain!!.myEdit!!.putString("gender", typeOfUser)
            registerMain!!.myEdit!!.commit()
        })
        back.setOnClickListener(View.OnClickListener { view: View? ->
            val fragmentPassword = FragmentPassword()
            sendInfo(fragmentPassword, finalAge, finalWeight, finalHeight, finalActive)
            requireFragmentManager().beginTransaction()
                    .replace(R.id.register_container, fragmentPassword)
                    .commit()
        })
        return rootView
    }

    private fun sendInfo(fragment: Fragment, age: Int, weight: Int, height: Int, active: String) {

        // get data from SharedPreferences if next/back is pressed
        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         */
        val sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val name = sh.getString("name", "")
        val email = sh.getString("email", "")
        val mobile = sh.getString("mobile", "")
        val passwd = sh.getString("pass", "")

        // add data to bundle
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("email", email)
        bundle.putString("mobile", mobile)
        bundle.putString("pass", passwd)
        bundle.putBoolean("selectedMale", selectedMale)
        bundle.putBoolean("selectedFemale", selectedFemale)
        bundle.putInt("age", age)
        bundle.putInt("weight", weight)
        bundle.putInt("height", height)
        bundle.putString("active", active)
        fragment.arguments = bundle
    }
}