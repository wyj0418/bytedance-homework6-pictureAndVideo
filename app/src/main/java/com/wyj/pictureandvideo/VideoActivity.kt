package com.wyj.pictureandvideo

import android.view.View.*
import android.widget.*
import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.os.Bundle
import android.os.Handler
import android.os.Looper
//import android.R
import android.app.Activity
import android.net.Uri
import android.view.View


class VideoActivity : AppCompatActivity() {
    private var mVideoView: VideoView? = null
    private var playBtn: Button? = null
    private var stopBtn: Button? = null
    var mMediaController: MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        mVideoView = VideoView(this)
        mVideoView = findViewById(R.id.video) //as VideoView
        mMediaController = MediaController(this)
        playBtn = findViewById<View>(R.id.playbutton) as Button
        stopBtn = findViewById<View>(R.id.stopbutton) as Button
        playBtn!!.setOnClickListener(mClick())
        stopBtn!!.setOnClickListener(mClick())
    }

    internal inner class mClick : OnClickListener {
        override fun onClick(v: View) {
            val uri2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";  //网络
            mVideoView!!.setVideoURI(Uri.parse(uri2));  //网络
            mMediaController!!.setMediaPlayer(mVideoView)
            mVideoView!!.setMediaController(mMediaController)
            if (v === playBtn) {
                mVideoView!!.start()
            } else if (v === stopBtn) {
                mVideoView!!.stopPlayback()
            }
        }
    }
}
