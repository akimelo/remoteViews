package com.example.remoteviews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.squareup.picasso.Picasso
import io.karte.android.tracking.Tracker
import io.karte.android.variables.Variables
import android.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgVal = Variables.get("app_top_krt_image_url")
        val textVal = Variables.get("app_top_krt_label_text")
        val buttonVal = Variables.get("app_top_krt_button_text")
        val colorVal = Variables.get("app_top_krt_text_color")

        val bannerImageView: ImageView = findViewById(R.id.bannerImageView)
        val bannerTextView: TextView = findViewById(R.id.bannerTextView) // TextViewを参照
        val bannerUrl = imgVal?.string("foo.img")?:""

        Picasso.get().load(bannerUrl).into(bannerImageView) // 画像をロードするためにPicassoライブラリを使用

        // バナーのテキストを動的に変更
        bannerTextView.setTextColor(Color.parseColor(colorVal?.string("foo.color")?:""))
        bannerTextView.text = textVal?.string("foo.text")?:""
    }

    override fun onResume() {
        super.onResume()
        Tracker.view("Fragment", "フラグメント")
        showToast("onResume()")
    }

    // バナークリック時の処理をメソッドとして定義
    fun onBannerClicked(view: View) {
        val linkVal = Variables.get("app_top_krt_link_url")
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkVal?.string("foo.link")?:""))
        startActivity(intent)
    }
    private fun showToast(lifecycle: String) {
        Toast.makeText(applicationContext, "call $lifecycle !", Toast.LENGTH_SHORT).show()
    }
}