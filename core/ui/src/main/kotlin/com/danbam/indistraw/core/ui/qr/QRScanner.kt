package com.danbam.indistraw.core.ui.qr

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

@Composable
fun QRScanner(
    modifier: Modifier = Modifier,
    onScan: (String?) -> Unit
) {
    AndroidView(
        factory = { context ->
            val cameraExecutor = Executors.newSingleThreadExecutor()
            val previewView = PreviewView(context).also {
                it.scaleX = 1F
                it.scaleY = 0.75F
            }
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                val imageCapture = ImageCapture.Builder().build()
                val imageAnalyzer = ImageAnalysis.Builder()
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, QRAnalyser(callback = onScan))
                    }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        context as ComponentActivity,
                        cameraSelector,
                        preview,
                        imageCapture,
                        imageAnalyzer
                    )
                } catch (e: Exception) {
                }
            }, ContextCompat.getMainExecutor(context))
            previewView
        },
        modifier = modifier
    )
}

private class QRAnalyser(
    val callback: (String?) -> Unit
) : ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
        val scanner = BarcodeScanning.getClient(options)
        imageProxy.image?.let {
            val image = InputImage.fromMediaImage(it, imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener {
                    if (it.isNotEmpty()) {
                        callback(it[0].url?.url)
                    }
                }
                .addOnFailureListener {

                }
        }
        imageProxy.close()
    }
}