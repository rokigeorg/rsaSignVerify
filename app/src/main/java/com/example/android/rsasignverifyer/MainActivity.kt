package com.example.android.rsasignverifyer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val TAG = "MAIN"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
startSignProcess()

    }

    fun startSignProcess(){
        val apdu = byteArrayOf(0xAA.toByte(),0xAA.toByte(),0xAA.toByte(),0xAA.toByte(),0xAA.toByte(),0xAA.toByte(),0xAA.toByte(),0xAA.toByte())
        val alias = "cgsignerKeyAlias"
        val rsa = KeystoreRsaUtils(alias)
        rsa.createKeys(this)

        Log.i(TAG, "START signing the ByteArray" )
        val signature = rsa.signData(apdu)

        Log.i(TAG, "START verify the ByteArray" )
        val isOK = rsa.verifyData(apdu, signature)

        if(isOK){
            Log.i(TAG,"OK" )
        }else{
            Log.i(TAG,"Failed" )

        }

    }
}
