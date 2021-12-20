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
import com.example.nutritionapp.databinding.FragmentActiveBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class ActiveFragment : Fragment() {
    lateinit var lactive: RelativeLayout
    lateinit var mactive: RelativeLayout
    lateinit var hactive: RelativeLayout
    private var binding: FragmentActiveBinding? = null
    lateinit var next: ExtendedFloatingActionButton
    lateinit var back: ExtendedFloatingActionButton
    var registerMain: RegisterMain? = null
    var active = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentActiveBinding.inflate(inflater, container, false)
        val rootView: View = binding!!.root
        lactive = rootView.findViewById(R.id.fragment_active_light)
        mactive = rootView.findViewById(R.id.fragment_active_moderate)
        hactive = rootView.findViewById(R.id.fragment_active_high)
        next = rootView.findViewById(R.id.active_next)
        back = rootView.findViewById(R.id.active_back)


        //  to get the data
        val bundle = this.arguments
        if (bundle != null) {
            active = bundle.getString("active", "Light")
            if (active == "Light") {
                lactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
                mactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
                hactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            }
            if (active == "Moderate") {
                lactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
                mactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
                hactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            }
            if (active == "Highly") {
                lactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
                mactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
                hactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
            }
        }
        if (bundle == null) {
            lactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            mactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            hactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
        }
        lactive.setOnClickListener(View.OnClickListener { view: View? ->
            lactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
            mactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            hactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            active = "Light"
        })
        mactive.setOnClickListener(View.OnClickListener { view: View? ->
            mactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
            lactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            hactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            active = "Moderate"
        })
        hactive.setOnClickListener(View.OnClickListener { view: View? ->
            hactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus))
            lactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            mactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalenotfocus))
            active = "Highly"
        })
        registerMain = activity as RegisterMain?
        next.setOnClickListener(View.OnClickListener { view: View? ->
            var valid = true
            if (active.equals("")) {
                Toast.makeText(context, "This field cannot be empty", Toast.LENGTH_SHORT).show()
                valid = false
            }
            if (valid) {
                val fragmentCalorie = FragmentCalorie()
                sendInfo(fragmentCalorie, active)
                requireFragmentManager().beginTransaction()
                        .replace(R.id.register_container, fragmentCalorie)
                        .commit()
            }
            registerMain!!.myEdit!!.putString("active", active)
            registerMain!!.myEdit!!.commit()
        })
        back.setOnClickListener(View.OnClickListener { view: View? ->
            val heightFragment = HeightFragment()
            sendInfo(heightFragment, active)
            requireFragmentManager().beginTransaction()
                    .replace(R.id.register_container, heightFragment)
                    .commit()
        })
        return rootView
    }

    private fun sendInfo(fragment: Fragment, active: String) {
        var selectedMale = false
        var selectedFemale = false

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
        val height = sh.getString("height", "")
        val intWeight = weight!!.toInt()
        val intHeight = height!!.toInt()
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
        bundle.putInt("height", intHeight)
        bundle.putString("active", active)
        fragment.arguments = bundle
    }
}