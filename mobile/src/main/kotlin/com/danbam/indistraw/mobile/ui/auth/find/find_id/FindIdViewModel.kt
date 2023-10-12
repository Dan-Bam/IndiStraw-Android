package com.danbam.indistraw.mobile.ui.auth.find.find_id

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.domain.usecase.account.FindIdUseCase
import com.danbam.indistraw.mobile.util.android.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FindIdViewModel @Inject constructor(
    private val findIdUseCase: FindIdUseCase,
) : ContainerHost<FindIdState, Unit>, ViewModel() {
    override val container = container<FindIdState, Unit>(FindIdState())

    fun findId(phoneNumber: String) = intent {
        viewModelScope.launch {
            findIdUseCase(phoneNumber = phoneNumber).onSuccess {
                reduce { state.copy(id = it.id) }
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }
}