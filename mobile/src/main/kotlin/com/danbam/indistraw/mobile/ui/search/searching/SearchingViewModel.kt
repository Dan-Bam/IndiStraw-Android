package com.danbam.indistraw.mobile.ui.search.searching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.domain.usecase.search.GetRelatedSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchingViewModel @Inject constructor(
    private val getRelatedSearchUseCase: GetRelatedSearchUseCase
) : ContainerHost<SearchingState, Unit>, ViewModel() {
    override val container = container<SearchingState, Unit>(SearchingState())

    fun getRelatedSearch(keyword: String) = intent {
        viewModelScope.launch {
            getRelatedSearchUseCase(keyword = keyword).onSuccess {
                reduce { state.copy(relatedSearchPager = it) }
            }.onFailure {
                println("안녕 ${it}")
            }
        }
    }
}