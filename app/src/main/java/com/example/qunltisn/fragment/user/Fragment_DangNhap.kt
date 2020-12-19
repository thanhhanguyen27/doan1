package com.example.qunltisn.fragment.user

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.qunltisn.*
import com.example.qunltisn.databinding.FragmentDangNhapFragmentBinding
import com.example.qunltisn.model.response.user.QLTSUserLoginRes
import com.example.qunltisn.retrofit.WebInterface
import com.example.qunltisn.viewmodel.FragmentDangNhapViewModel
import kotlinx.android.synthetic.main.fragment__dang_nhap_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Fragment_DangNhap : Fragment() {

    companion object {
        fun newInstance() = Fragment_DangNhap()
    }

    private lateinit var viewModel: FragmentDangNhapViewModel
    private lateinit var binding: FragmentDangNhapFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,
            R.layout.fragment__dang_nhap_fragment, container, false)
        binding.apply {
            buttonDangNhap.setOnClickListener{
                login(edittextuser.text.toString(), edittextpassword.text.toString())
            }
            buttonDangKy.setOnClickListener {
                open_fragmentDangky()
            }
            textViewQuenMK.setOnClickListener {
                findNavController().navigate(Fragment_DangNhapDirections.actionFragmentDangNhapToFragmentQuenMatKhau())
            }

        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentDangNhapViewModel::class.java)

    }

    private fun open_fragmentDangky(){
        findNavController().navigate(Fragment_DangNhapDirections.actionFragmentDangNhapToFragmentDangKy())
     //  findNavController().navigate(action)
    }

    private fun login(username:String, password:String){
        Log.d("Login_", "Login")
        val pref= requireContext().getSharedPreferences(SHARED_NAME, MODE_PRIVATE)
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service= retrofit.create(WebInterface::class.java)
        val request= service.getQLTSUserLogin(username, password)
        request.enqueue(object :Callback<QLTSUserLoginRes>{
            override fun onFailure(call: Call<QLTSUserLoginRes>, t: Throwable) {
                Log.d("Login_", "ERROR ${t.message}")
            }

            override fun onResponse(
                call: Call<QLTSUserLoginRes>,
                response: Response<QLTSUserLoginRes>
            ) {
                Log.d("Login_", "${response.body()!!.toMapLoginRes()}")
                val loginRes= response.body()
                if (loginRes!!.respcode==0) {
                    if (checkSaveLogin.isChecked.equals(true)){
                        with(pref.edit()){
                            putString(KEY_USERNAME, binding.edittextuser.text.toString())
                            putString(KEY_TOKEN, loginRes.token)
                            apply()

                        }
                        Toast.makeText(context, "Đã lưu thông tin!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                            pref.edit().clear().apply()
                           // pref.edit().commit()
                    }
                    openHomeActivity()
                } else {
                    Toast.makeText(requireContext(), "Thông tin đăng nhập sai", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
    private fun openHomeActivity(){
        startActivity(Intent(requireContext(), HomeActivity::class.java))
    }

}
//0368835536
//A6C58