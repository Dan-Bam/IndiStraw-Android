package com.danbam.mobile.ui.main.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.account.GetProfileUseCase
import com.danbam.domain.usecase.crowd_funding.FundingPopularListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val fundingPopularListUseCase: FundingPopularListUseCase
) : ContainerHost<MainState, Unit>, ViewModel() {
    override val container = container<MainState, Unit>(MainState())

    fun getProfile() = intent {
        viewModelScope.launch {
            getProfileUseCase().onSuccess {
                reduce { state.copy(profileUrl = it.profileUrl) }
            }
        }
    }

    fun fundingPopularLst() = intent {
        viewModelScope.launch {
            fundingPopularListUseCase().onSuccess {
                reduce { state.copy(fundingPopularList = it) }
            }
        }
    }
}