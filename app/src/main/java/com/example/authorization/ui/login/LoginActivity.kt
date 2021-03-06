package com.example.authorization.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.example.authorization.R
import com.example.authorization.ui.account.AccountActivity
import com.example.authorization.ui.base.BaseMvpActivity
import com.example.authorization.ui.recoverpassword.RecoverPassword
import com.example.authorization.utils.extensions.gone
import com.example.authorization.utils.extensions.setImage
import com.example.authorization.utils.extensions.visible
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : BaseMvpActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        loginPresenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateActivity(savedInstanceState: Bundle?) {

        loginPresenter.onCreate()
        showTextLogo()
        vIvLogo.setImage(R.drawable.fox)
        initOnClickListeners()
    }

    private fun showTextLogo() {
        val spannableString = SpannableString(getString(R.string.logo))
        val foregroundSpanFirst = ForegroundColorSpan(Color.WHITE)
        val foregroundSpanSecond = ForegroundColorSpan(ContextCompat.getColor(this, R.color.orange))
        spannableString.setSpan(foregroundSpanFirst, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(
            foregroundSpanSecond,
            3,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        vTvLogo.text = spannableString
    }

    private fun initOnClickListeners() {

        vFlLogInGoogle.setOnClickListener {
            loginPresenter.signInGoogle(this)
        }

        vFlSignUp.setOnClickListener {
            loginPresenter.doSignUp(
                vEtEmail.text.toString(),
                vEtPassword.text.toString(),
                vEtConfirmPassword.text.toString()
            )
        }
        vFlLogIn.setOnClickListener {
            loginPresenter.doLogIn(
                vEtEmail.text.toString(),
                vEtPassword.text.toString(),
                vMcbKeepLoggedIn.isKeepLoggedIn
            )
        }
        vTvLogIn.setOnClickListener {
            loginPresenter.onSwitchedLogInClicked()
        }
        vTvSignUp.setOnClickListener {
            loginPresenter.onSwitchedSignUpClicked()
        }
        vTvRecoverPass.setOnClickListener {
            loginPresenter.onRecoverPassClicked()
        }
        vTvKeepLoggedIn.setOnClickListener {
            loginPresenter.onKeepLogInClicked()
        }
    }

    // MARK: View Implementation
    override fun goToAccountFromGoogle(mGoogleSignInClient: GoogleSignInClient,rcSignIn: Int ) {
        startActivityForResult(mGoogleSignInClient.signInIntent, rcSignIn)
    }

    override fun saveOrNotAuthData() {
        vMcbKeepLoggedIn.changeIsKeepLogIn()
    }

    override fun showMsg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMsg(@StringRes message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun goToLogInForm() {
        vTvLogIn.setTextColor(ContextCompat.getColor(this, R.color.orange))
        vTvSignUp.setTextColor(ContextCompat.getColor(this, R.color.white))
        vLlRepeatPass.gone()
        vFlSignUp.gone()
        vFlLogIn.visible()
        vLlKeepLogIn.visible()
        vFlLogInGoogle.visible()
    }

    override fun goToSignUpForm() {
        vTvLogIn.setTextColor(ContextCompat.getColor(this, R.color.white))
        vTvSignUp.setTextColor(ContextCompat.getColor(this, R.color.orange))
        vLlKeepLogIn.gone()
        vFlLogInGoogle.gone()
        vFlLogIn.gone()
        vFlSignUp.visible()
        vLlRepeatPass.visible()
    }

    override fun recoverPassword() {
        startActivity(Intent(this, RecoverPassword::class.java))
    }

    override fun goToAccount() {
        startActivity(Intent(this, AccountActivity::class.java))
        finish()
    }
}

