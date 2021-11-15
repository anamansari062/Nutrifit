package com.example.nutritionapp.Register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> pass = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> mobile = new MutableLiveData<>();
    private MutableLiveData<String> age = new MutableLiveData<>();
    private MutableLiveData<String> height = new MutableLiveData<>();
    private MutableLiveData<String> weight = new MutableLiveData<>();
    private MutableLiveData<String> gender = new MutableLiveData<>();
    private MutableLiveData<String> active = new MutableLiveData<>();
    private MutableLiveData<Double> calories = new MutableLiveData<>();
    private double a;
    private double calorie;

    public void setCalories(String height, String weight, String age, String gender, String active){
        if(active.equals("Light"))
            a= 1.2;
        else if(active.equals("Moderate"))
            a= 1.55;
        else
            a= 1.9;
        if(gender.equals("Male"))
            calorie= (66.5 + 13.8 * Integer.parseInt(weight) + 5* Integer.parseInt(height) ) - (6.8 * Integer.parseInt(age)) * a;
        else
            calorie= (655.1 + 9.5 * Integer.parseInt(weight) + 1.8* Integer.parseInt(height) ) - (4.6* Integer.parseInt(age)) * a;
        calories.setValue(calorie);

    }

    public void setEmailMobile(String input1, String input2) {
        email.setValue(input1);
        mobile.setValue(input2);
    }

    public void setPass(String input3){
        pass.setValue(input3);
    }

    public void setName(String input4){
        name.setValue(input4);
    }

    public void setAge(String input5){
        age.setValue(input5);
    }

    public void setHeight(String input6){
        height.setValue(input6);
    }

    public void setWeight(String input7){
        weight.setValue(input7);
    }

    public void setGender(String input8){
        gender.setValue(input8);
    }

    public void setActive(String input9){
        active.setValue(input9);
    }

    public MutableLiveData<String> getPass() {
        return pass;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getAge() {
        return age;
    }

    public MutableLiveData<String> getHeight() {
        return height;
    }

    public MutableLiveData<String> getWeight() {
        return weight;
    }

    public MutableLiveData<String> getGender() {
        return gender;
    }

    public MutableLiveData<String> getActive() {
        return active;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getMobile() {
        return mobile;
    }

    public MutableLiveData<Double> getCalories() {
        return calories;
    }
}
