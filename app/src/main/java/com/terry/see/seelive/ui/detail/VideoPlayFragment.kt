package com.terry.see.seelive.ui.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pili.pldroid.player.AVOptions
import com.pili.pldroid.player.PLMediaPlayer
import com.pili.pldroid.player.widget.PLVideoView
import com.terry.see.seelive.R
import com.terry.see.seelive.listener.OnVideoListener
import kotlinx.android.synthetic.main.fragment_video_play.*


/**
 * Created by terry on 2017/7/11.
 */

class VideoPlayFragment : Fragment() {

    companion object {

        val MESSAGE_ID_RECONNECTING = 0x01

        fun newInstance(url:String):VideoPlayFragment{
            val videoPlayFragment = VideoPlayFragment()
            val args = Bundle()
            args.putString("path",url)
            videoPlayFragment.arguments =args
            return videoPlayFragment
        }
    }


    private var mVideoPath: String? = null   //播放路径

    private var listener: OnVideoListener? = null

    fun setVideoListener(listener: OnVideoListener){
        this.listener = listener
    }

    private fun setOptions(codecType: Int) {
        val options = AVOptions()

        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000)
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000)
        options.setInteger(AVOptions.KEY_PROBESIZE, 128 * 1024)
        // Some optimization with buffering mechanism when be set to 1
        options.setInteger(AVOptions.KEY_LIVE_STREAMING,0)
        options.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1)
        // 1 -> hw codec enable, 0 -> disable [recommended]
        options.setInteger(AVOptions.KEY_MEDIACODEC, codecType)
        // whether start play automatically after prepared, default value is 1
        options.setInteger(AVOptions.KEY_START_ON_PREPARED, 0)

        play_videoView.setAVOptions(options)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_video_play, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVideoSetting()

        mVideoPath = arguments.getString("path")

        play_videoView.setVideoPath(mVideoPath)
        play_videoView.start()
    }


    private fun initVideoSetting() {
        // 1 -> hw codec enable, 0 -> disable [recommended]
        val codec = 0
        setOptions(codec)

        play_videoView.setBufferingIndicator(play_loading_view)
        play_videoView.setOnInfoListener(mOnInfoListener)
        play_videoView.setOnCompletionListener(mOnCompleteListener)
        play_videoView.setOnErrorListener(mOnErrorListener)
        play_videoView.displayAspectRatio = PLVideoView.ASPECT_RATIO_PAVED_PARENT
    }

    override fun onDestroyView() {
        super.onDestroyView()
        play_videoView.stopPlayback()
        mHandler.removeCallbacksAndMessages(null)
        Log.d("zt", "onDestroyView-----")
    }

    private val mOnCompleteListener = PLMediaPlayer.OnCompletionListener {
        if (listener != null){
            listener!!.onStop()
        }
    }


    private val mOnInfoListener = PLMediaPlayer.OnInfoListener { _, what, extra ->

        Log.d("zt", "onInfo: $what, $extra")
        false
    }

    private val mOnErrorListener = PLMediaPlayer.OnErrorListener { _, errorCode ->
        var isNeedReconnect = false
        Log.e("zt", "Error happened, errorCode = " + errorCode)
        when (errorCode) {
            PLMediaPlayer.ERROR_CODE_INVALID_URI -> showToastTips("Invalid URL !")
            PLMediaPlayer.ERROR_CODE_404_NOT_FOUND -> showToastTips("404 resource not found !")
            PLMediaPlayer.ERROR_CODE_CONNECTION_REFUSED -> showToastTips("Connection refused !")
            PLMediaPlayer.ERROR_CODE_CONNECTION_TIMEOUT ->  isNeedReconnect = true
            PLMediaPlayer.ERROR_CODE_STREAM_DISCONNECTED -> isNeedReconnect = true
            PLMediaPlayer.ERROR_CODE_IO_ERROR -> isNeedReconnect = true

            PLMediaPlayer.ERROR_CODE_PREPARE_TIMEOUT ->    isNeedReconnect = true
            PLMediaPlayer.ERROR_CODE_READ_FRAME_TIMEOUT -> isNeedReconnect = true
            PLMediaPlayer.ERROR_CODE_HW_DECODE_FAILURE -> {
                setOptions(AVOptions.MEDIA_CODEC_SW_DECODE)
                isNeedReconnect = true
            }
            else -> showToastTips("unknown error !")
        }
        if (isNeedReconnect) {
            sendReconnectMessage()
        } else {
            activity.finish()
        }
        true
    }

    fun showToastTips(tips: String) {

        activity.runOnUiThread {
            if (!activity.isFinishing) {
                Toast.makeText(activity,tips,Toast.LENGTH_LONG).show()
            }
        }
    }

    var mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what != MESSAGE_ID_RECONNECTING) {
                return
            }
            play_videoView.setVideoPath(mVideoPath)
            play_videoView.start()
        }
    }

    private fun sendReconnectMessage() {
        mHandler.removeCallbacksAndMessages(null)
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_ID_RECONNECTING), 1500)
    }

}
