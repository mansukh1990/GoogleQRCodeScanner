package com.example.googleqrscanner

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class QrScannerZxing : AppCompatActivity() {

    private lateinit var scanQrBtn: Button
    private lateinit var scannedValueTv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner_zxing)

        initVars()
        registerUiListener()
    }

    private fun initVars() {
        scanQrBtn = findViewById(R.id.scanQrBtn)
        scannedValueTv = findViewById(R.id.scannedValueTv)
    }

    private fun registerUiListener() {
        scanQrBtn.setOnClickListener {
            scannerLauncher.launch(
                ScanOptions().setPrompt("Scan QR Code")
                    .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            )

        }

    }

    private val scannerLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()

        } else {
            scannedValueTv.text = buildString {
                append("Scanned Value :")
                append(result.contents)
            }
        }


    }

}