package com.example.qunltisn.fragment.user

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.qunltisn.*
import com.example.qunltisn.viewmodel.AccountViewModel
import com.example.qunltisn.databinding.AccountFragmentBinding
import com.example.qunltisn.model.response.user.QLTSUserInfoRes
import com.example.qunltisn.retrofit.WebInterface
import kotlinx.android.synthetic.main.account_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AccountFragment : Fragment() {

    companion object {
        fun newInstance() = AccountFragment()
    }

    private lateinit var viewModel: AccountViewModel
    private lateinit var binding: AccountFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.account_fragment, container, false)
        val pref = requireContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        val username = pref.getString(KEY_USERNAME, "")
        val token = pref.getString(KEY_TOKEN, "")
        getQLTSUserInfo(username.toString(), token.toString())

        binding.buttonLogOut.setOnClickListener{
            pref.edit().clear().apply()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return binding.root
    }

    public fun getQLTSUserInfo(username: String, token: String) {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(WebInterface::class.java)
        val request = service.getQLTSUserInfo(username, token)
        request.enqueue(object : Callback<QLTSUserInfoRes> {
            override fun onFailure(call: Call<QLTSUserInfoRes>, t: Throwable) {
                Log.d("ACCOUNTFRAGMENT", "ERROR ${t.message}")
            }

            override fun onResponse(
                call: Call<QLTSUserInfoRes>,
                response: Response<QLTSUserInfoRes>
            ) {
                Log.d("ACCOUNTFRAGMENT", "${response.body()}")
                val userInfoRes = response.body()
                if (userInfoRes!!.respcode == 0) {
                   // binding.userInfo = userInfoRes.userinfo
                    binding.apply {
                        tvUserName.setText(userInfoRes.userinfo.username)
                        tvFullName.setText(userInfoRes.userinfo.fullname)
                        tvEmail.setText(userInfoRes.userinfo.email)
                        tvAddress.setText(userInfoRes.userinfo.address)
                        tvEType.setText(userInfoRes.userinfo.employeetype)
                    }

                } else {
                    Toast.makeText(requireContext(), userInfoRes.resptext, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_account, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.DoiMatKhau -> {
                requireView().findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToChangePasswordFragment())
                true
            }
            R.id.CapNhatThongTin -> {
                requireView().findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToUpdateUserInfoFragment())
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}