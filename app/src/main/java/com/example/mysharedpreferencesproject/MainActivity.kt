package com.example.mysharedpreferencesproject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.mysharedpreferencesproject.restapi.ApiClient
import com.example.mysharedpreferencesproject.restapi.model.CharacterModelItem

import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var photo: ImageView
    private lateinit var characterName: TextView
    private lateinit var nickName: TextView
    private lateinit var genderRadio: RadioGroup
    private lateinit var genderMale: RadioButton
    private lateinit var genderFemale: RadioButton
    private lateinit var occupationText: TextView
    private lateinit var btn_Update: Button
    private lateinit var btn_Delete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photo = findViewById(R.id.civ_user_image)
        characterName = findViewById(R.id.nametextViewId)
        nickName = findViewById(R.id.userNameTextViewId)
        genderRadio = findViewById(R.id.radioGroupId)
        genderMale = findViewById(R.id.radioMale)
        genderFemale = findViewById(R.id.radioFemale)
        occupationText = findViewById(R.id.occupationTextId)
        btn_Update = findViewById(R.id.update_id)
        btn_Delete = findViewById(R.id.delete_id)

        //photo = view.findViewById(R.id.civ_user_image)

        var SharedPreferences =
            this@MainActivity.getSharedPreferences("UserInformation", Context.MODE_PRIVATE)
        var editor = SharedPreferences.edit()

        if (SharedPreferences.getString("flag", "") == "true") {

            Toast.makeText(this, "Local Data", Toast.LENGTH_SHORT).show()

            nickName.text = SharedPreferences.getString("nickName", "")
            characterName.text = SharedPreferences.getString("name", "")
            occupationText.text = SharedPreferences.getString("occupation", "")
            var imgUrl = SharedPreferences.getString("imageUrl", "")
            Picasso.get().load(imgUrl).placeholder(R.drawable.ic_waiting_foreground).into(photo)
            if (SharedPreferences.getString("gender", "") == "male") {
                genderMale.isChecked = true
            } else {
                genderFemale.isChecked = true
            }

        } else {
            getUser()
        }

        btn_Update.setOnClickListener {
            getUser()
        }
        btn_Delete.setOnClickListener {
            editor.clear().apply()
        }

    }

    fun getUser() {
        Toast.makeText(this, "Api", Toast.LENGTH_SHORT).show()
        ApiClient.getApiService().getUser().enqueue(object : Callback<List<CharacterModelItem>> {
            override fun onFailure(call: Call<List<CharacterModelItem>>, t: Throwable) {
                //Log.d("muhammed", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<CharacterModelItem>>,
                response: Response<List<CharacterModelItem>>
            ) {

                var SharedPreferences =
                    this@MainActivity.getSharedPreferences("UserInformation", Context.MODE_PRIVATE)
                var editor = SharedPreferences.edit()

                val array: List<CharacterModelItem> = response.body()!!
                val image_Url = array[0].img
                Picasso.get().load(image_Url).placeholder(R.drawable.ic_waiting_foreground).into(photo)

                characterName.text = array[0].name
                nickName.text = array[0].nickname
                if (array[0].name == "Walter White") {
                    genderMale.isChecked = true
                    editor.putString("gender", "male")
                } else {
                    genderFemale.isChecked = true
                    editor.putString("gender", "female")
                }
                occupationText.text = array[0].occupation.toString()

                //editor.putString("name",array[0].name.toString()).apply()


                editor.putString("name", array[0].name.toString()).apply()
                editor.putString("nickName", array[0].nickname.toString()).apply()
                editor.putString("imageUrl", array[0].img.toString()).apply()
                editor.putString("occupation", array[0].occupation.toString()).apply()
                editor.putString("flag", "true").apply()

                //editor.putBoolean("flag",true).apply()
                //editor.clear().apply()
                //editor.remove("flag").apply()
            }
        })
    }
}

