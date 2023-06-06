package com.danbam.presentation.ui.find.find_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.ChangePasswordParam
import com.danbam.domain.usecase.account.ChangePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FindPasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
) : ContainerHost<Unit, Unit>, ViewModel() {
    override val container = container<Unit, Unit>(Unit)

    fun changePassword(phoneNumber: String, password: String, checkPassword: String) = intent {
        viewModelScope.launch {
            changePasswordUseCase(
                changePasswordParam = ChangePasswordParam(
                    phoneNumber = phoneNumber,
                    password = password
                )
            ).onSuccess {

            }.onFailure {

            }
        }
    }
}