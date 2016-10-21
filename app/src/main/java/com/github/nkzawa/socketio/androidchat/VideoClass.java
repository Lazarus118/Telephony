package com.github.nkzawa.socketio.androidchat;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoClass extends ActionBarActivity {
    ImageButton video_chat, search, video_list;
    VideoView vv;
    static final int REQUEST_VIDEO_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_chat);
        // ------------------------------------ //
        video_list = (ImageButton)findViewById(R.id.list_video);
        video_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("video/*");
                startActivityForResult(intent, 1);
            }
        });
        // ------------------------------------ //
        video_chat = (ImageButton)findViewById(R.id.video_chat);
        video_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakeVideoIntent();
            }
        });
        // ------------------------------------ //
        search = (ImageButton)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(VideoClass.this, SearchClass.class);
                VideoClass.this.startActivity(search);
            }
        });

    }

    // RECORD VIDEO METHOD
    // ----------------------------------- //
    private void dispatchTakeVideoIntent()
    {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    // VIEW VIDEO METHOD
    // ----------------------------------- //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        vv = (VideoView)findViewById(R.id.videoView);
        if (requestCode == REQUEST_VIDEO_CAPTURE && requestCode == RESULT_OK)
        {
            String path = data.getData().toString();
            vv.setMediaController(new MediaController(VideoClass.this));
            vv.setVideoPath(path);
            vv.start();
            vv.requestFocus();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
