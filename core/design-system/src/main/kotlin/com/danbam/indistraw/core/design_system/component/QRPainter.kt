package com.danbam.indistraw.core.design_system.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType

@Composable
fun QRPainter(
    modifier: Modifier = Modifier,
    url: String
) {
    if (BarcodeType.QR_CODE.isValueValid(url)) {
        Barcode(
            modifier = modifier
                .fillMaxSize(0.55F),
            type = BarcodeType.QR_CODE,
            value = url
        )
    }
}