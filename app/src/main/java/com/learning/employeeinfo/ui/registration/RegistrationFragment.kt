package com.learning.employeeinfo.ui.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.learning.employeeinfo.R
import com.learning.employeeinfo.databinding.FragmentRegistrationBinding
import com.learning.employeeinfo.util.extensions.navigation.AuthenticationNavigation
import com.learning.employeeinfo.util.extensions.navigation.navigate

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var binding: FragmentRegistrationBinding

    private lateinit var viewModel: RegistrationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegistrationBinding.bind(view)

        loadPage()


    }

    private fun loadPage() {

        initialization()

        setOnClickListeners()

    }

    private fun setOnClickListeners() {

        with(binding) {

            btnRegister.setOnClickListener {

            }

            tvLogin.setOnClickListener {

                navigate(AuthenticationNavigation.RegistrationToLogin)

            }

        }

    }

    private fun initialization() {


    }
}