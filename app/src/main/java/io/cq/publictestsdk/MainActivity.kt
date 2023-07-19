package io.cq.publictestsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import io.carrotquest_sdk.android.Carrot
import io.cq.publictestsdk.api.Common
import io.cq.publictestsdk.api.Fact

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

private const val APP_ID = "3060"
private const val API_KEY = "3060-ed66f34a99b114d5aa7161b3619"
private const val USER_AUTH_KEY = "userauthkey-3060-2fd1047cb540b573999a9d101a9e7f54a50cf54d380bea175b1c9adf95b6252"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCarrotSDK()

        findViewById<Button>(R.id.get_movies_btn).setOnClickListener {
            Common.retrofitService.getRandomFact().enqueue(object : Callback<Fact> {
                override fun onFailure(call: Call<Fact>, t: Throwable) {
                    Log.e("GET", t.toString())
                    Toast.makeText(this@MainActivity, "ERROR: $t", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                    if(response.isSuccessful) {
                        val tv = findViewById<TextView>(R.id.random_fact_tv)

                        tv.textSize = Random.nextInt(10, 40).toFloat()
                        tv.text = response.body()?.fact?: "Oops"
                    } else {
                        Toast.makeText(this@MainActivity, "ERROR: ${response.errorBody()}", Toast.LENGTH_LONG).show()
                    }

                }
            })
        }
    }

    private fun initCarrotSDK(){
        Carrot.setup(this, API_KEY, APP_ID)

        findViewById<Button>(R.id.open_chat_btn).setOnClickListener {
            Carrot.openChat(this@MainActivity)
        }
    }
}