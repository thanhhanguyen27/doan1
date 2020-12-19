package com.example.qunltisn.fragment.user

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.qunltisn.*
import com.example.qunltisn.databinding.ChangePasswordFragmentBinding
import com.example.qunltisn.retrofit.WebInterface
import com.example.qunltisn.model.response.user.ChangePasswordRes
import com.example.qunltisn.viewmodel.ChangePasswordViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChangePasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ChangePasswordFragment()
    }
    private lateinit var viewModel: ChangePasswordViewModel
    private lateinit var binding: ChangePasswordFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
     (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding= DataBindingUtil.inflate(inflater,R.layout.change_password_fragment, container, false)
        val pref= requireContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        val username= pref.getString(KEY_USERNAME, "")
        val token= pref.getString(KEY_TOKEN,"")
        binding.apply {
            buttonSave.setOnClickListener {
                changePass(username.toString(), token.toString(), edtpass.text.toString(), edtNewPass.text.toString())
            }
            buttonCancel.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        return binding.root
    }
    private fun changePass(username: String, token:String, password:String, passwordnew:String){
        val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service= retrofit.create(WebInterface::class.java)
        val request= service.ChangePassword(username, token, password, passwordnew)
        request.enqueue(object : Callback<ChangePasswordRes>{
            override fun onResponse(
                call: Call<ChangePasswordRes>,
                response: Response<ChangePasswordRes>
            ) {
                Log.d("GetDataFromAPI", "${response.body()!!.toMapChangePassword()}")
                val pref= requireContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
                val changePassRes= response.body()
                if (changePassRes!!.respcode == 0) {
                    Toast.makeText(context, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT ).show()
                    pref.edit().clear().apply()
                   // requireActivity().onBackPressed()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }else {
                    Toast.makeText(context, "Thông tin chưa chính xác / Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT ).show()
                }
            }

            override fun onFailure(call: Call<ChangePasswordRes>, t: Throwable) {
                Log.d("Main", "ERROR ${t.message}" )
            }

        })

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}