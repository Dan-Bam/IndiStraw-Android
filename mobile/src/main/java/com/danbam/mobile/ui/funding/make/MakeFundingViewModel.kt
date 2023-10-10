package com.danbam.mobile.ui.funding.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.FundingCreateParam
import com.danbam.domain.usecase.file.SendFileUseCase
import com.danbam.domain.usecase.crowd_funding.FundingCreateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MakeFundingViewModel @Inject constructor(
    private val sendFileUseCase: SendFileUseCase,
    private val fundingCreateUseCase: FundingCreateUseCase
) : ContainerHost<MakeFundingState, Unit>, ViewModel() {
    override val container = container<MakeFundingState, Unit>(MakeFundingState())

    fun uploadImage(file: File, onUploaded: (String) -> Unit) = intent {
        viewModelScope.launch {
            sendFileUseCase(file = file).onSuccess {
                onUploaded(it.file)
            }
        }
    }

    fun saveIntroduce(
        thumbnailUrl: String?,
        title: String,
        description: String,
        imageList: List<String>,
        onNext: () -> Unit
    ) = intent {
        if (thumbnailUrl.isNullOrBlank()) return@intent
        else if (title.isEmpty()) return@intent
        else if (description.isEmpty()) return@intent
        else if (imageList.isEmpty()) return@intent
        else {
            reduce {
                state.copy(
                    thumbnailUrl = thumbnailUrl,
                    title = title,
                    description = description,
                    imageList = imageList
                )
            }
            onNext()
        }
    }

    fun saveTarget(
        targetAmount: Long,
        endDate: String,
        fileList: List<String>,
        onNext: () -> Unit
    ) = intent {
        if (targetAmount == 0L) return@intent
        else if (endDate.isEmpty()) return@intent
        else if (fileList.isEmpty()) return@intent
        else {
            reduce {
                state.copy(
                    targetAmount = targetAmount,
                    endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy:MM:dd")),
                    fileList = fileList
                )
            }
            onNext()
        }
    }

    fun addReward(
        imageList: List<String>,
        title: String,
        description: String,
        isReal: Boolean,
        price: Long,
        amount: Long?,
        onAdded: () -> Unit
    ) = intent {
        if (imageList.isEmpty()) return@intent
        else if (title.isEmpty()) return@intent
        else if (description.isEmpty()) return@intent
        else if (price == 0L) return@intent
        else if (isReal && amount == 0L) return@intent
        else {
            reduce {
                state.copy(
                    rewardList = state.rewardList.plus(
                        FundingCreateParam.RewardParam(
                            title = title,
                            description = description,
                            price = price,
                            imageList = imageList,
                            isReal = isReal,
                            totalCount = amount
                        )
                    )
                )
            }
            onAdded()
        }
    }

    fun removeReward(removeIndex: Int) = intent {
        reduce {
            state.copy(
                rewardList = state.rewardList.filterIndexed { index, _ -> index != removeIndex }
            )
        }
    }

    fun fundingCreate(
        bank: Bank,
        account: String,
        onFinished: () -> Unit
    ) = intent {
        if (account.isEmpty()) return@intent
        else {
            viewModelScope.launch {
                fundingCreateUseCase(
                    FundingCreateParam(
                        title = state.title,
                        description = state.description,
                        targetAmount = state.targetAmount,
                        directorAccount = FundingCreateParam.DirectorAccountParam(
                            bank = bank.name,
                            account = account
                        ),
                        reward = state.rewardList,
                        endDate = state.endDate.toString(),
                        thumbnailUrl = state.thumbnailUrl!!,
                        imageList = state.imageList,
                        fileList = state.fileList
                    )
                ).onSuccess {
                    onFinished()
                }
            }
        }
    }
}