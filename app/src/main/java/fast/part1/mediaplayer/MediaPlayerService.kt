package fast.part1.mediaplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MediaPlayerService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            MEDIA_PLAYER_PLAY -> {
                if(mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(baseContext, R.raw.han_volcano)
                }
                mediaPlayer?.start()
            }
            MEDIA_PLAYER_PAUSE -> {
                mediaPlayer?.pause()
            }
            MEDIA_PLAYER_STOP -> {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
                stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}