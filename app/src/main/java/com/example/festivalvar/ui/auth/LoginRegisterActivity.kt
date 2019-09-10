package com.example.festivalvar.ui.auth

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.auth.login.LoginRequest
import com.example.festivalvar.data.remote.model.auth.register.RegisterRequest
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.utils.AppConstants
import kotlinx.android.synthetic.main.activity_login_register.*
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.register_screen.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginRegisterActivity : BaseActivity(), ILoginRegisterNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_login_register

    private val viewModel by viewModel<LoginRegisterViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        layoutRegister.visibility = View.VISIBLE

        observeViewModel()


    }

    override fun initListener() {
        handleSegmentedButton()

        btnRegister.setOnClickListener {
            if (validateEdittext()) {

                val etFullName = etFullname1.text
                val etMail = etMail1.text
                val etPassword = etPassword1.text

                val request = RegisterRequest(
                    "$etFullName",
                    "$etMail",
                    "$etPassword",
                    "",
                    ""
                )

                viewModel.getRegister(request)

            }
        }

        btnLogin.setOnClickListener {

            val textMail = etMailLogin1?.text
            val textPassword = etPasswordLogin1?.text

            if (textMail.toString().trim().isNullOrEmpty() || textPassword.toString().trim().isNullOrEmpty()) {
                //showPopupError("E-posta adresi veya şifre gereklidir.")
                Toast.makeText(this, "E - posta veya şifre gereklidir.", Toast.LENGTH_SHORT).show()
            } else {
                val request = LoginRequest("$textMail", "$textPassword")
                viewModel.getLogin(request)
            }
        }


    }

    private fun handleSegmentedButton() {
        if (AppConstants.TAB_VALUE.equals(AppConstants.ONLINE)) {
            layoutRegister.visibility = View.VISIBLE

            segmentedButtonGroup.setPosition(0, true)


        } else if (AppConstants.TAB_VALUE.equals(AppConstants.CLINIC)) {
            segmentedButtonGroup.setPosition(1, true)
            layoutRegister.visibility = View.GONE


        }
        segmentedButtonGroup.onPositionChangedListener = SegmentedButtonGroup.OnPositionChangedListener {
            // Handle stuff here
            //Üye Ol butonu aktifse
            if (it == 0) {
                layoutRegister.visibility = View.VISIBLE
                layoutLogin.visibility = View.GONE

                AppConstants.TAB_VALUE = AppConstants.ONLINE
            }
            //Giriş yap butonu aktifse
            else if (it == 1) {
                layoutRegister.visibility = View.GONE
                layoutLogin.visibility = View.VISIBLE

                AppConstants.TAB_VALUE = AppConstants.CLINIC

            }
        }

// Get current position
        segmentedButtonGroup.position
    }


    private fun observeViewModel(){

        viewModel.loginData.observe(this, Observer {
        })

        viewModel.registerData.observe(this, Observer {
        })

    }


    private fun validateEdittext(): Boolean {

        val etFullName = etFullname1.text
        val etMail = etMail1.text
        val etPassword = etPassword1.text

        if (etFullName.toString().isEmpty() || etMail.toString().isEmpty() || etPassword.toString().isEmpty() || (!cbAgreement.isChecked)) {
            Toast.makeText(this, "Bilgileri kontorl ediniz", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

}
