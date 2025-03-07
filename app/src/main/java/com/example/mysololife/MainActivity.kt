package com.example.mysololife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mysamplapp.model.Response
import com.example.mysamplapp.model.User
import com.example.mysamplapp.service.ApiService
import com.example.mysololife.auth.IntroActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrofit 객체 생성
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:8080")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        apiService = retrofit.create(ApiService::class.java)


//        findViewById<Button>(R.id.logoutBtn).setOnClickListener {
//            logout()
//        }
    }

    private fun logout(){
        apiService.logout().enqueue(object : retrofit2.Callback<Response> {
            override fun onResponse(call: retrofit2.Call<Response>, response: retrofit2.Response<Response>) {
                if(response.isSuccessful){
                    Toast.makeText(this@MainActivity, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@MainActivity, IntroActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                }else{
                    Toast.makeText(this@MainActivity, "로그아웃 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<Response>, t: Throwable) {
                Toast.makeText(this@MainActivity, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}