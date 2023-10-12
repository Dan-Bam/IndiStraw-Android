package com.danbam.indistraw.mobile.ui.funding.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.AddFileList
import com.danbam.indistraw.core.design_system.component.DatePicker
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.util.android.toFile
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import okhttp3.internal.toLongOrDefault
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WriteTargetScreen(
    makeFundingViewModel: MakeFundingViewModel,
    onNext: () -> Unit
) {
    val container = makeFundingViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val context = LocalContext.current
    var targetAmount by remember { mutableStateOf(state.targetAmount.toString()) }
    var year by remember { mutableStateOf(state.endDate.year) }
    var month by remember { mutableStateOf(state.endDate.monthValue) }
    var day by remember { mutableStateOf(state.endDate.dayOfMonth) }
    val fileList = remember { mutableStateListOf(*state.fileList.toTypedArray()) }

    Spacer(modifier = Modifier.height(36.dp))
    IndiStrawBottomSheetLayout(sheetContent = {
        Row(
            modifier = Modifier.padding(58.dp)
        ) {
            DatePicker(
                itemList = (1..12).toList(),
                currentItem = month,
                dateType = DatePicker.Month
            ) {
                month = it
            }
            Spacer(modifier = Modifier.weight(1F))
            DatePicker(itemList = (1..31).toList(), currentItem = day, dateType = DatePicker.Day) {
                day = it
            }
            Spacer(modifier = Modifier.weight(1F))
            DatePicker(
                itemList = LocalDate.now().let { (it.year..it.year + 5).toList() },
                currentItem = year,
                dateType = DatePicker.Year
            ) {
                year = it
            }
        }
    }) { _, openSheet ->
        IndiStrawColumnBackground(
            scrollEnabled = true
        ) {
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, bottom = 16.dp),
                text = stringResource(id = R.string.target_money)
            )
            IndiStrawTextField(
                hint = stringResource(id = R.string.require_target_money),
                value = targetAmount,
                onValueChange = { targetAmount = it })
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
                text = stringResource(id = R.string.end_date)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .background(
                        IndiStrawTheme.colors.darkGray,
                        IndiStrawTheme.shapes.defaultRounded
                    )
                    .padding(horizontal = 13.dp, vertical = 23.dp)
                    .indiStrawClickable { openSheet() },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExampleTextMedium(text = "${year}년 ${month}월 ${day}일")
                IndiStrawIcon(icon = IndiStrawIconList.DownArrow)

            }
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
                text = stringResource(id = R.string.file)
            )
            Spacer(modifier = Modifier.height(16.dp))
            AddFileList(fileList = fileList, onDelete = { fileList.removeAt(it) }) {
                it?.let {
                    makeFundingViewModel.uploadImage(it.toFile(context)) {
                        fileList.add(it)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 36.dp))
            IndiStrawButton(text = stringResource(id = R.string.next)) {
                makeFundingViewModel.saveTarget(
                    targetAmount = targetAmount.toLongOrDefault(0),
                    endDate = "$year:${"%02d".format(month)}:${"%02d".format(day)}",
                    fileList = fileList
                ) {
                    onNext()
                }
            }
        }
    }
}