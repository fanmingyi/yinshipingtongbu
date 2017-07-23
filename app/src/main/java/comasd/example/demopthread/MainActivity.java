package comasd.example.demopthread;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.SurfaceView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public int write(byte[] audioData, int offsetInBytes, int sizeInBytes) {
        return sizeInBytes;
    }
    //初始化播放器
    public AudioTrack audioInit(int sampleRateInHz){
        //音频码流 PCM 16位
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;

        /**
         * 返回 请求成功创建一个在MODE_STREAM{@link #MODE_STREAM}模式上的AudioTrack对象所需最小缓存大小
         *
         * @param sampleRateInHz  以hz表达资源采样率
         * @param channelConfig 描述音频通道配置
         *   请看 {@link AudioFormat#CHANNEL_OUT_MONO} 和
         *   {@link AudioFormat#CHANNEL_OUT_STEREO}
         * @param audioFormat  描述音频数据格式
         *
         *
         *   See {@link AudioFormat#ENCODING_PCM_16BIT} and
         *   {@link AudioFormat#ENCODING_PCM_8BIT},
         *   and {@link AudioFormat#ENCODING_PCM_FLOAT}.
         * @return {@link #ERROR_BAD_VALUE} 如果你传递无效的参数,
         *   or {@link #ERROR} if unable to query for output properties,
         *   or the minimum buffer size expressed in bytes.
         */
        int buffSize = AudioTrack.getMinBufferSize(sampleRateInHz, AudioFormat.CHANNEL_OUT_STEREO, audioFormat);
        /**
         *
         * @param streamType 音频流类型. See
         *   {@link AudioManager#STREAM_VOICE_CALL}, {@link AudioManager#STREAM_SYSTEM},
         *   {@link AudioManager#STREAM_RING}, {@link AudioManager#STREAM_MUSIC},
         *   {@link AudioManager#STREAM_ALARM}, and {@link AudioManager#STREAM_NOTIFICATION}.
         * @param sampleRateInHz 以hz表示的初始化资源采样率
         * @param channelConfig
         *  描述音频通道配置
         *
         *   See {@link AudioFormat#CHANNEL_OUT_MONO} and
         *   {@link AudioFormat#CHANNEL_OUT_STEREO}
         * @param audioFormat 描述音频格式 .
         *   See {@link AudioFormat#ENCODING_PCM_16BIT},
         *   {@link AudioFormat#ENCODING_PCM_8BIT},
         *   and {@link AudioFormat#ENCODING_PCM_FLOAT}.
         * @param bufferSizeInBytes 用于播放音频数据的内部缓存总大小(以字节为单位)
         *
         * @param mode streaming 或者 static buffer. See {@link #MODE_STATIC} and {@link #MODE_STREAM}
         * @throws java.lang.IllegalArgumentException
         */
        AudioTrack	audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz, AudioFormat.CHANNEL_OUT_STEREO, audioFormat, buffSize, AudioTrack.MODE_STREAM);
        return audioTrack;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final File inputFile = new File(Environment.getExternalStorageDirectory(), "a.mov");
        final SurfaceView sample_text = ((SurfaceView) findViewById(R.id.sample_text));
//        audioInit(1);
        new Thread(){
            @Override
            public void run() {
                super.run();
                ffmpeg(inputFile.getAbsolutePath(), sample_text.getHolder().getSurface());
            }
        }.start();


    }

    /**
     * @param input   视频文件的输入路径
     * @param surface 把视频文件解码成yuv格式输出路径
     */
    public native void ffmpeg(String input, Surface surface);

}
