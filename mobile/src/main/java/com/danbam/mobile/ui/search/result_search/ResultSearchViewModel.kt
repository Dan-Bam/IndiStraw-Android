package com.danbam.mobile.ui.search.result_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.entity.RecentSearchEntity
import com.danbam.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ResultSearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ContainerHost<Unit, Unit>, ViewModel() {
    override val container = container<Unit, Unit>(Unit)

    fun search(keyword: String) = intent {
        viewModelScope.launch {
            searchUseCase(recentSearchEntity = RecentSearchEntity(search = keyword))
        }
    }
}