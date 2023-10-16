package com.danbam.indistraw.feature.mobile.profile.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.domain.entity.movie.MovieEntity
import com.danbam.indistraw.core.domain.usecase.account.GetProfileUseCase
import com.danbam.indistraw.core.domain.usecase.crowd_funding.FundingMyUseCase
import com.danbam.indistraw.core.domain.usecase.funding.FundingListUseCase
import com.danbam.indistraw.core.domain.usecase.movie.MovieHistoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val fundingMyUseCase: FundingMyUseCase,
    private val fundingListUseCase: FundingListUseCase,
    private val movieHistoryListUseCase: MovieHistoryListUseCase,
) : ContainerHost<ProfileState, Unit>, ViewModel() {
    override val container = container<ProfileState, Unit>(ProfileState())
    fun getProfile() = intent {
        viewModelScope.launch {
            getProfileUseCase().onSuccess {
                reduce { state.copy(id = it.id, name = "${it.name}님", profileUrl = it.profileUrl) }
            }
        }
    }

    fun getMyFunding() = intent {
        viewModelScope.launch {
            fundingMyUseCase().onSuccess {
                reduce { state.copy(myFundingList = it) }
            }
        }
    }

    fun getParticipateFunding() = intent {
        viewModelScope.launch {
            fundingListUseCase().onSuccess {
                reduce { state.copy(fundingList = it) }
            }
        }
    }

    fun movieHistory() = intent {
        viewModelScope.launch {
            movieHistoryListUseCase().onSuccess {
                reduce {
                    state.copy(movieHistoryList = it.map {
                        MovieEntity(
                            movieIdx = it.movieIdx,
                            thumbnailUrl = it.thumbnailUrl
                        )
                    })
                }
            }
        }
    }
}