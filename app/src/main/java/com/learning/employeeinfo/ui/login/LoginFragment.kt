package com.learning.employeeinfo.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.learning.employeeinfo.R
import com.learning.employeeinfo.databinding.FragmentLoginBinding
import com.learning.employeeinfo.util.extensions.navigation.AuthenticationNavigation
import com.learning.employeeinfo.util.extensions.navigation.navigate
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        loadPage()

    }

    private fun loadPage() {

        initialization()

        setOnClickListeners()

        observes()

    }

    private fun observes() {

        lifecycleScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->

        }) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

        lifecycle.coroutineScope.launch(Dispatchers.Main) {

            loginViewModel.uiEvent.collectLatest {

            }

        }


    }

    private fun setOnClickListeners() {

        with(binding) {

            btnLogin.setOnClickListener {


            }

            tvRegistration.setOnClickListener {

                navigate(AuthenticationNavigation.LoginToRegistration)

            }

        }

    }

    private fun initialization() {

//        FacebookSdk.sdkInitialize(requireContext())

    }

}