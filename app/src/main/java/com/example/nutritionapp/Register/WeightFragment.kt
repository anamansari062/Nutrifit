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
import com.example.nutritionapp.databinding.FragmentWeightBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class WeightFragment : Fragment() {
    private var binding: FragmentWeightBinding? = null
    lateinit var next: ExtendedFloatingActionButton
    lateinit var back: ExtendedFloatingActionButton
    lateinit var weight: TextView
    lateinit var incweight: ImageView
    lateinit var decweight: ImageView
    var registerMain: RegisterMain? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentWeightBinding.inflate(inflater, container, false)
        val rootView: View = binding!!.root
        weight = rootView.findViewById(R.id.fragment_weight_currentWeight)
        incweight = rootView.findViewById(R.id.fragment_weight_incrementWeight)
        decweight = rootView.findViewById(R.id.fragment_weight_decrementWeight)
        next = rootView.findViewById(R.id.weight_next)
        back = rootView.findViewById(R.id.weight_back)
        var bundleWeight = 50
        var bundleHeight = 180
        val age = 18
        var active = "light"
        //  to get the data
        val bundle = this.arguments
        if (bundle != null) {
            bundleWeight = bundle.getInt("weight", 50)
            bundleHeight = bundle.getInt("height", 180)
            active = bundle.getString("active", "light")
            weight.setText(bundleWeight.toString())
        }
        if (bundle == null) {
            weight.setText("50")
        }
        val weight1 = intArrayOf(50)
        val weight2 = arrayOf("50")
        incweight.setOnClickListener(View.OnClickListener { view: View? ->
            weight1[0] += 1
            weight2[0] = weight1[0].toString()
            weight.setText(weight2[0])
        })
        decweight.setOnClickListener(View.OnClickListener { view: View? ->
            if (weight1[0] > 0) {
                weight1[0] -= 1
                weight2[0] = weight1[0].toString()
                weight.setText(weight2[0])
            }
        })
        registerMain = activity as RegisterMain?
        val finalBundleHeight = bundleHeight
        val finalActive = active
        next.setOnClickListener(View.OnClickListener { view: View? ->
            var valid = true
            if (weight2[0] == "0") {
                Toast.makeText(context, "Weight cannot be 0", Toast.LENGTH_SHORT).show()
                valid = false
            }
            if (valid) {
                val heightFragment = HeightFragment()
                sendInfo(heightFragment, finalBundleHeight, finalActive)
                requireFragmentManager().beginTransaction()
                        .replace(R.id.register_container, heightFragment)
                        .commit()
                registerMain!!.myEdit!!.putString("weight", weight.getText().toString())
                registerMain!!.myEdit!!.commit()
            }
        })
        back.setOnClickListener(View.OnClickListener { view: View? ->
            val ageFragment = AgeFragment()
            sendInfo(ageFragment, finalBundleHeight, finalActive)
            requireFragmentManager().beginTransaction()
                    .replace(R.id.register_container, ageFragment)
                    .commit()
        })
        return rootView
    }

    private fun sendInfo(fragment: Fragment, height: Int, active: String) {
        var selectedMale = false
        var selectedFemale = false
        val sendWeight = weight!!.text.toString().toInt()

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
        bundle.putInt("weight", sendWeight)
        bundle.putInt("height", height)
        bundle.putString("active", active)
        fragment.arguments = bundle
    }
}