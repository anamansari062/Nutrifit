package com.example.nutritionapp.ui.update

import android.app.AlertDialog
import com.example.nutritionapp.ui.update.UpdateViewModel
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.RelativeLayout
import android.widget.EditText
import android.widget.Spinner
import com.google.firebase.database.DatabaseReference
import java.util.ArrayList
import android.widget.ArrayAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.nutritionapp.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nutritionapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    private val updateViewModel: UpdateViewModel? = null
    private var binding: FragmentUpdateBinding? = null
    lateinit var mcalculate: Button
    lateinit var mcurrentheight: TextView
    lateinit var mcurrentage: TextView
    lateinit var mcurrentweight: TextView
    lateinit var mincrementage: ImageView
    lateinit var mincrementweight: ImageView
    lateinit var mdecrementage: ImageView
    lateinit var mdecrementweight: ImageView
    lateinit var mheightbar: SeekBar
    lateinit var mmale: RelativeLayout
    lateinit var mfemale: RelativeLayout
    lateinit var editName: EditText
    lateinit var spinner: Spinner
    var a: Double? = null
    var name: String? = null
    var databaseReference: DatabaseReference? = null
    var arrayList_activity: ArrayList<String>? = null
    var arrayAdapter_activity: ArrayAdapter<String>? = null
    var currentProgress = 0
    var mintProgress: String? = "160"
    var age = 0
    var age2: String? = null
    var weight = 0
    var weight2: String? = null
    var typeOfUser = "0"
    var calorie = 0.0
    var heightc = 0
    var weightc = 0
    var agec = 0
    var value: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        mcalculate = root.findViewById(R.id.fragment_update_UpdateBtn)
        mcurrentage = root.findViewById(R.id.fragment_update_currentAge)
        mcurrentheight = root.findViewById(R.id.fragment_update_currentHeight)
        mcurrentweight = root.findViewById(R.id.fragment_update_currentWeight)
        mincrementweight = root.findViewById(R.id.fragment_update_incrementWeight)
        mincrementage = root.findViewById(R.id.fragment_update_incrementAge)
        mdecrementage = root.findViewById(R.id.fragment_update_decrementAge)
        mdecrementweight = root.findViewById(R.id.fragment_update_decrementWeight)
        mheightbar = root.findViewById(R.id.fragment_update_heightBar)
        mmale = root.findViewById(R.id.fragment_update_maleLogo)
        mfemale = root.findViewById(R.id.fragment_update_femaleLogo)
        spinner = root.findViewById(R.id.fragment_update_spinner1)
        editName = root.findViewById(R.id.editTextTextPersonName)
        val user = FirebaseAuth.getInstance().currentUser
        databaseReference = FirebaseDatabase.getInstance().getReference("USERS")
        if (user != null) {
            databaseReference!!.child(user.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val map = snapshot.value as Map<String, String>?
                    age2 = map!!["age"]
                    age = age2!!.toInt()
                    mcurrentage.setText(age2)
                    weight2 = map["weight"]
                    weight = weight2!!.toInt()
                    mcurrentweight.setText(weight2)
                    mintProgress = map["height"]
                    currentProgress = mintProgress!!.toInt()
                    mcurrentheight.setText(mintProgress)
                    name = map["name"]
                    editName.setText(name)
                    value = map["activity"]
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
        arrayList_activity = ArrayList()
        arrayList_activity!!.add(0, "How active are you?")
        arrayList_activity!!.add("Low Physical Activity")
        arrayList_activity!!.add("Average Physical Activity")
        arrayList_activity!!.add("High Physical Activity")
        arrayAdapter_activity = ArrayAdapter(
            context!!,
            R.layout.support_simple_spinner_dropdown_item,
            arrayList_activity!!
        )
        arrayAdapter_activity!!.setDropDownViewResource(R.layout.custom_selected_spinner_layout)
        spinner.setAdapter(arrayAdapter_activity)
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                if (adapterView.getItemAtPosition(i) == "How active are you?") {
                    //do nothing
                    (adapterView.getChildAt(0) as TextView).setTextColor(Color.GRAY)
                } else {
                    (adapterView.getChildAt(0) as TextView).setTextColor(Color.BLACK)
                    value = adapterView.getItemAtPosition(i).toString()
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
        mmale.setOnClickListener(View.OnClickListener { view: View? ->
            mmale.setBackground(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.registermalefemalefocus
                )
            )
            mfemale.setBackground(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.registermalefemalenotfocus
                )
            )
            typeOfUser = "Male"
        })
        mfemale.setOnClickListener(View.OnClickListener { view: View? ->
            mfemale.setBackground(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.registermalefemalefocus
                )
            )
            mmale.setBackground(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.registermalefemalenotfocus
                )
            )
            typeOfUser = "Female"
        })
        mheightbar.setMax(300)
        mheightbar.setProgress(160)
        mheightbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                currentProgress = i
                mintProgress = currentProgress.toString()
                mcurrentheight.setText(mintProgress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        mincrementage.setOnClickListener(View.OnClickListener { view: View? ->
            age += 1
            age2 = age.toString()
            mcurrentage.setText(age2)
        })
        mincrementweight.setOnClickListener(View.OnClickListener { view: View? ->
            weight += 1
            weight2 = weight.toString()
            mcurrentweight.setText(weight2)
        })
        mdecrementage.setOnClickListener(View.OnClickListener { view: View? ->
            if (age > 0) {
                age -= 1
                age2 = age.toString()
                mcurrentage.setText(age2)
            }
        })
        mdecrementweight.setOnClickListener(View.OnClickListener { view: View? ->
            if (weight > 0) {
                weight -= 1
                weight2 = weight.toString()
                mcurrentweight.setText(weight2)
            }
        })
        mcalculate.setOnClickListener(View.OnClickListener { view: View? ->
            if (typeOfUser == "0") {
                Toast.makeText(context, "Select Your Gender", Toast.LENGTH_SHORT).show()
            } else if (mintProgress == "0") {
                Toast.makeText(context, "Select Your Height", Toast.LENGTH_SHORT).show()
            } else if (age == 0 || age < 0) {
                Toast.makeText(context, "Age is incorrect", Toast.LENGTH_SHORT).show()
            } else {
                calorie = calculate(
                    mcurrentheight.getText().toString().toInt(),
                    mcurrentweight.getText().toString().toInt(),
                    mcurrentage.getText().toString().toInt(),
                    typeOfUser,
                    value
                )
                databaseReference!!.child(user!!.uid)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            snapshot.ref.child("age").setValue(age2)
                            snapshot.ref.child("activity").setValue(value)
                            val Calories = java.lang.Double.toString(calorie)
                            snapshot.ref.child("name").setValue(editName.getText().toString())
                            snapshot.ref.child("calories").setValue(Calories)
                            snapshot.ref.child("gender").setValue(typeOfUser)
                            snapshot.ref.child("height").setValue(mintProgress)
                        }

                        override fun onCancelled(error: DatabaseError) {}
                    })
                alertDialog()
            }
        })
        return root
    }

    private fun calculate(
        height: Int,
        weight: Int,
        age: Int,
        gender: String,
        active: String?
    ): Double {
        val calorie: Double
        a = if (active == "Light") 1.2 else if (active == "Moderate") 1.55 else 1.9
        calorie =
            if (gender == "Male") 66.5 + 13.8 * weight + 5 * height - 6.8 * age * a!! else 655.1 + 9.5 * weight + 1.8 * height - 4.6 * age * a!!
        return calorie
    }

    private fun alertDialog() {
        val dialog = AlertDialog.Builder(activity)
        dialog.setMessage(
            "Your details have been updated successfully , The number of " +
                    "calories you need are : " + calorie + " cal"
        )
        dialog.setTitle("Successful")
        dialog.setPositiveButton(
            "Ok"
        ) { dialog1: DialogInterface?, which: Int -> }
        val alertDialog = dialog.create()
        alertDialog.show()
    }
}