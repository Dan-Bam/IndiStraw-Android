package com.danbam.mobile.ui.search.searching

import com.danbam.domain.entity.search.RelatedSearchEntity

data class SearchingState(
    val relatedSearchPager: List<RelatedSearchEntity>? = null
)