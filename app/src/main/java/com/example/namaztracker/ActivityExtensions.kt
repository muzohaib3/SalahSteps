package com.example.namaztracker

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentManager
import com.google.gson.Gson
import java.io.IOException

fun loge(tag:String, msg:Any?){
    Log.e(tag, "e.message = "+msg.toString())
}

fun logi(tag:String, msg:Any?){
    Log.i(tag, msg.toString())
}

fun logd(tag:String, msg:Any?){
    Log.d(tag, msg.toString())
}

fun showToast(context: Context, message:String){
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}

fun popFragment(fragmentManager:FragmentManager) = fragmentManager.popBackStack()

fun getClassName(context: Context):String{
    return ""+context::class.simpleName
}

fun hideStatusBar(window: Window){
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
}

fun getJsonFromRaw(context: Context, resId: Int): String? {
    return try {
        context.resources.openRawResource(resId).bufferedReader().use { it.readText() }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Context.readRawJson(resId: Int): String? {
    return try {
        resources.openRawResource(resId).bufferedReader().use { it.readText() }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun <T> String.toModel(modelClass: Class<T>): T? {
    return try {
        Gson().fromJson(this, modelClass)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun View.isVisible(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}
