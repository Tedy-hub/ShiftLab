package com.example.shiftlab

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val USER_NAME = "name"
    val USER_FAMELY = "famely"
    val USER_PASSWORD = "password"
    val USER_BIRTHDAY = "birthday"
    var USER_FIRST_TIME = "USER_FIRST_TIME"
    var USER_STATE = true
    lateinit var datePicker: DatePicker
    lateinit var InputName: TextView
    lateinit var InputFamely: TextView
    lateinit var InputPassword: TextView
    lateinit var InputCheckPassword: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPref = getSharedPreferences("User Parametrs", Context.MODE_PRIVATE)

        InputName = findViewById(R.id.input_name)
        InputFamely = findViewById(R.id.input_famely)
        InputPassword = findViewById(R.id.input_password)
        InputCheckPassword= findViewById(R.id.input_check_password)
        datePicker = findViewById(R.id.datePicker)

        if(!sharedPref.contains(USER_FIRST_TIME))  {

            var UserName: String
            var UserFamely: String
            var UserPassword: String

            var Registration: ImageView = findViewById(R.id.backgroundreg)



            Registration.setOnClickListener {

                if(InputName.getText().toString()!= "" && InputFamely.getText().toString()!= "" && InputPassword.getText().toString()!= "" && InputCheckPassword.getText().toString()!= ""){
                   if(ParameterCheck(InputName.getText().toString(), InputFamely.getText().toString(), InputPassword.getText().toString(), InputCheckPassword.getText().toString())) {
                       val day: Int = datePicker.getDayOfMonth()
                       val month: Int = datePicker.getMonth() +1
                       val year: Int = datePicker.getYear()
                       var UserBirthDay: String = "" +day+"."+month+"."+year
                       Log.d("проверка", "" +day+"."+month+"."+year)
                       NextActivity(InputName.getText().toString(), InputFamely.getText().toString(), UserBirthDay.toString())

                    val editor: Editor = sharedPref.edit()
                    editor.putBoolean(USER_FIRST_TIME, USER_STATE)
                    editor.putString(USER_BIRTHDAY, UserBirthDay)
                    editor.putString(USER_NAME, InputName.getText().toString())
                    editor.putString(USER_FAMELY, InputFamely.getText().toString())
                    editor.apply()


                   }
                }
                else{onMassage("Введите данные в поля")}

            }



        }
        else {
            momentNextActivity(sharedPref.getString(USER_NAME, "").toString(), sharedPref.getString(USER_FAMELY, "").toString(), sharedPref.getString(USER_BIRTHDAY, "").toString())


        }


    }


    private fun NextActivity(UserName: String, UserFamely: String, UserBirthDay: String){
        val handler2 = android.os.Handler()

        handler2.postDelayed({
            val randomIntent = Intent(this, MainActivity2::class.java)
            randomIntent.putExtra("UserName", UserName.toString());
            randomIntent.putExtra("UserFamely", UserFamely.toString());
            randomIntent.putExtra("UserBirthDay", UserBirthDay.toString());
            startActivity(randomIntent)
            overridePendingTransition(R.anim.diagtranslate, R.anim.alpha)
        }, 1000)
    }

    private fun momentNextActivity(UserName: String, UserFamely: String, UserBirthDay: String){
        val handler2 = android.os.Handler()
        handler2.postDelayed({
            val randomIntent = Intent(this, MainActivity2::class.java)
            randomIntent.putExtra("UserName", UserName);
            randomIntent.putExtra("UserFamely", UserFamely.toString());
            randomIntent.putExtra("UserBirthDay", UserBirthDay.toString());
            startActivity(randomIntent)
            overridePendingTransition(R.anim.diagtranslate, R.anim.alpha)
        }, 100)
    }

    private fun onMassage(massage: String){
        val toast = Toast.makeText(applicationContext, massage.toString(), Toast.LENGTH_LONG)
        toast.show()
    }

    private fun ParameterCheck(UserName: String, UserFamely: String, UserPassword: String, UserCheckPassword: String):Boolean{
        var AllRight = true

        if(UserName.length > 2){if(UserName =="Имя") {onMassage("Впишите в поле \"Имя\" своё имя!"); AllRight = false}}
        else{onMassage("Имя не может содержать меньше трех букв"); AllRight = false}

        if(UserFamely.length > 2){if(UserFamely =="Фамилия") {onMassage("Впишите в поле \"Фамилия\" свою Фамилию!"); AllRight = false}}
        else{onMassage("Фамилия не может содержать меньше трех букв"); AllRight = false}

        if(UserPassword.length > 5 ){if(UserPassword =="Пароль"){ onMassage("Впишите в поле \"Пароль\" другое слово!"); AllRight = false}}
        else{onMassage("Длинна пароля должна быть не меньше 6 символов"); AllRight = false}

        if(UserPassword.toString() == UserCheckPassword.toString())
        else{onMassage("Пароли должны совпадать"); AllRight = false}


        return AllRight
    }


    fun saveUserParametr(USER_PARAMETR: String, textView: TextView) {
        var sPref = getPreferences(MODE_PRIVATE)
        val ed: Editor = sPref.edit()
        ed.putString(USER_PARAMETR, textView.getText().toString())
        ed.commit()

    }

    fun loadUserParametr(USER_PARAMETR: String, textView: TextView) {
        var sPref = getPreferences(MODE_PRIVATE)
        val savedText: String? = sPref.getString(USER_PARAMETR, "");
        textView.setText(savedText)

    }



}


