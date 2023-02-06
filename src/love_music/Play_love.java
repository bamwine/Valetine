package love_music;







import com.example.valetine.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class Play_love extends Activity {
	VideoView videoview;
	String	id,speechtitle,fileReference;
	TextView ids,titles,by ,dateheld,fuction;
	ImageView imgflag;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

    	setContentView(R.layout.play_love);
        Intent i =getIntent();
        fileReference = i.getStringExtra("fileReference");
        videoview = (VideoView)findViewById(R.id.videoView1); 
		
		videoview.setVideoPath(fileReference);
		//videoview.setVideoURI(uri);
    	MediaController videocontroller=new MediaController(this);
    	videocontroller.setAnchorView(videoview);
    	//Uri video = Uri.parse(fileReference);
    	videoview.setMediaController(videocontroller);
    	//videoview.setVideoURI(video);
    	
    	
    	videoview.requestFocus();
    	videoview.setOnPreparedListener(new OnPreparedListener() {			
			@Override
			public void onPrepared(MediaPlayer mp) {
				videoview.start();
				
			}
		});
        
       
    }
 
}

