package com.danbam.design_system.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.design_system.util.indiStrawClickable
import java.time.LocalDate

sealed class Date {
    object Year : Date()
    object Month : Date()
    object Day : Date()
}

data class DatePicker(
    val date: Int,
    val dateType: Date,
    val isExpand: Boolean,
    val maxWidth: Int,
)

@Composable
fun IndiStrawDatePicker(
    modifier: Modifier = Modifier,
    onSelect: (List<Int>) -> Unit,
) {
    val datePicker = LocalDate.now().run {
        remember {
            mutableStateListOf(
                DatePicker(year, Date.Year, false, 90),
                DatePicker(month.value, Date.Month, false, 76),
                DatePicker(dayOfMonth, Date.Day, false, 76),
            )
        }
    }
    val endDate =
        LocalDate.parse("${datePicker[0].date}-${"%02d".format(datePicker[1].date)}-01").run {
            withDayOfMonth(lengthOfMonth()).dayOfMonth
        }
    Row(
        modifier = modifier
            .padding(horizontal = 25.dp)
            .fillMaxWidth()
    ) {
        datePicker.forEachIndexed { i, it ->
            DateBox(
                date = it.date,
                endDate = endDate,
                dateType = it.dateType,
                maxWidth = it.maxWidth,
                isExpand = it.isExpand,
                onDateSelect = { selectDate ->
                    datePicker[i] = it.copy(date = selectDate, isExpand = false)
                    onSelect(datePicker.map { it.date })
                },
                onClick = {
                    datePicker[i] = it.copy(isExpand = !it.isExpand)
                })
            if (i != datePicker.lastIndex) {
                Spacer(modifier = Modifier.width(26.dp))
            }
        }
    }
}

@Composable
private fun DateBox(
    date: Int,
    endDate: Int,
    dateType: Date,
    maxWidth: Int,
    isExpand: Boolean,
    onDateSelect: (Int) -> Unit,
    onClick: () -> Unit,
) {
    var targetValue by remember { mutableStateOf(0F) }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(500)
    )
    val dateList = when (dateType) {
        Date.Month -> (1..12)
        Date.Day -> (1..endDate)
        Date.Year -> LocalDate.now().run {
            (year..year + 100)
        }
    }.toList()

    Column(
        modifier = Modifier.widthIn(max = maxWidth.dp)
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = IndiStrawTheme.colors.textBox,
                    shape = if (isExpand) IndiStrawTheme.shapes.topDefaultRounded else IndiStrawTheme.shapes.defaultRounded
                )
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .indiStrawClickable(onClick = {
                    targetValue = if (isExpand) 0F else 180F
                    onClick()
                }),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExampleTextMedium(text = date.toString())
            IndiStrawIcon(
                modifier = Modifier
                    .align(CenterVertically)
                    .rotate(rotateValue),
                icon = IndiStrawIconList.DateArrow
            )
        }
        ExpandDateBox(dateList = dateList, isExpand = isExpand, onDateSelect = {
            targetValue = if (isExpand) 0F else 180F
            onDateSelect(it)
        })
    }
}

@Composable
private fun ExpandDateBox(
    dateList: List<Int>,
    isExpand: Boolean,
    onDateSelect: (Int) -> Unit,
) {
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }

    AnimatedVisibility(
        visible = isExpand,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Column {
            RemoveOverScrollLazyColumn(
                modifier = Modifier.height(100.dp)
            ) {
                items(dateList) {
                    ExampleTextMedium(
                        modifier = Modifier
                            .background(IndiStrawTheme.colors.textBox)
                            .padding(start = 10.dp, bottom = 13.dp)
                            .indiStrawClickable(onClick = { onDateSelect(it);println("안녕") })
                            .fillMaxWidth(),
                        text = it.toString()
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(13.dp)
                    .background(
                        color = IndiStrawTheme.colors.textBox,
                        shape = IndiStrawTheme.shapes.bottomDefaultRounded
                    )
            )
        }
    }
}