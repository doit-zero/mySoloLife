package com.example.mysololife.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.example.mysamplapp.model.Response
import com.example.mysamplapp.model.User
import com.example.mysamplapp.service.ApiService
import com.example.mysololife.MainActivity
import com.example.mysololife.R
import com.example.mysololife.databinding.ActivityJoinBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback

class JoinActivity : AppCompatActivity() {


    private lateinit var apiService: ApiService
    private lateinit var binding : ActivityJoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrofit 객체 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        apiService = retrofit.create(ApiService::class.java)

        binding.joinBtn.setOnClickListener {

            var isGoToJoin = true

            // 화면에서 데이터를 가져오는 첫번째 방법
            val email = binding.emailArea.text.toString()
            val pwd1 = binding.pwdArea1.text.toString()
            val pwd2 = binding.pwdArea2.text.toString()

            // 값이 비어있는지 확인
            if(email.isEmpty()){
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if(pwd1.isEmpty()){
                Toast.makeText(this, "패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if(pwd2.isEmpty()){
                Toast.makeText(this, "패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }


            // 비밀번호 2개가 같은지 확인
            if(!pwd1.equals(pwd2)){
                Toast.makeText(this, "패스워드가 동일하지 않습니다.", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }

            // 비밀번호가 6자 이상인지
            if(pwd1.length < 6){
                Toast.makeText(this, "비밀번호를 6자리 이상으로 입력해주세요.", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }

            if(isGoToJoin){
                signupUser(User(email, pwd1))
            }
        }

    }

    private fun signupUser(user: User){
        apiService.signup(user).enqueue(object : retrofit2.Callback<Response> {
            override fun onResponse(call: retrofit2.Call<Response>, response: retrofit2.Response<Response>) {
                if(response.isSuccessful){
                    Toast.makeText(this@JoinActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@JoinActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }else{
                    Toast.makeText(this@JoinActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<Response>, t: Throwable) {
                Toast.makeText(this@JoinActivity, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}