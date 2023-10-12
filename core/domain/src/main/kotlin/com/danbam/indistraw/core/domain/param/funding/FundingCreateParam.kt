package com.danbam.indistraw.core.param.funding

data class FundingCreateParam(
    val title: String,
    val description: String,
    val targetAmount: Long,
    val directorAccount: DirectorAccountParam,
    val reward: List<RewardParam>,
    val endDate: String,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val fileList: List<String>,
) {
    data class DirectorAccountParam(
        val bank: String,
        val account: String,
    )

    data class RewardParam(
        val title: String,
        val description: String,
        val price: Long,
        val imageList: List<String>,
        val isReal: Boolean,
        val totalCount: Long?,
    )
}

