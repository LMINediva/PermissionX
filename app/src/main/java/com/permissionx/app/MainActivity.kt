package com.permissionx.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.permissionx.lcdev.PermissionX

class MainActivity : AppCompatActivity() {

    private lateinit var makeCallBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeCallBtn = findViewById(R.id.makeCallBtn)
        makeCallBtn.setOnClickListener {
            PermissionX.request(
                this,
                Manifest.permission.CALL_PHONE
            ) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(
                        this, "你拒绝了${deniedList}权限",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}