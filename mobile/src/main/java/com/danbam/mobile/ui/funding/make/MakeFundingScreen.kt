package com.danbam.mobile.ui.funding.make

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawMakeProgress
import com.danbam.design_system.component.MakeFundingProgress

@Composable
fun MakeFundingScreen(
    navController: NavController
) {
    var makeProgress: MakeFundingProgress by remember { mutableStateOf(MakeFundingProgress.WriteIntroduce) }
    BackHandler {
        when (makeProgress) {
            is MakeFundingProgress.WriteIntroduce -> navController.popBackStack()
            is MakeFundingProgress.WriteTarget -> makeProgress =
                MakeFundingProgress.WriteIntroduce

            is MakeFundingProgress.AddReward -> makeProgress =
                MakeFundingProgress.WriteTarget

            is MakeFundingProgress.WriteReward -> makeProgress =
                MakeFundingProgress.AddReward

            is MakeFundingProgress.WriteAccount -> makeProgress =
                MakeFundingProgress.AddReward
        }
    }
    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = {
                when (makeProgress) {
                    is MakeFundingProgress.WriteIntroduce -> navController.popBackStack()
                    is MakeFundingProgress.WriteTarget -> makeProgress =
                        MakeFundingProgress.WriteIntroduce

                    is MakeFundingProgress.AddReward -> makeProgress =
                        MakeFundingProgress.WriteTarget

                    is MakeFundingProgress.WriteReward -> makeProgress =
                        MakeFundingProgress.AddReward

                    is MakeFundingProgress.WriteAccount -> makeProgress =
                        MakeFundingProgress.AddReward
                }
            }
        )
        Spacer(modifier = Modifier.height(28.dp))
        IndiStrawMakeProgress(position = makeProgress)
        when (makeProgress) {
            is MakeFundingProgress.WriteIntroduce -> {
                WriteIntroduceScreen {
                    makeProgress = MakeFundingProgress.WriteTarget
                }
            }

            is MakeFundingProgress.WriteTarget -> {
                WriteTargetScreen {
                    makeProgress = MakeFundingProgress.AddReward
                }
            }

            is MakeFundingProgress.AddReward -> {
                AddRewardScreen(onAdd = { makeProgress = MakeFundingProgress.WriteReward }) {
                    makeProgress = MakeFundingProgress.WriteAccount
                }
            }

            is MakeFundingProgress.WriteReward -> {
                WriteRewardScreen {
                    makeProgress = MakeFundingProgress.AddReward
                }
            }

            is MakeFundingProgress.WriteAccount -> {
                WriteAccountScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}