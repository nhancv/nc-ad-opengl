package com.nhancv.opengl;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

//https://developer.android.com/training/graphics/opengl/environment.html#java
public class MainActivity extends AppCompatActivity {

    private MyGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        setContentView(R.layout.activity_main);
        glSurfaceView = findViewById(R.id.my_glsurfaceview);
        glSurfaceView.setZOrderOnTop(true);

    }

    @Override
    protected void onResume(){
        super.onResume();
        glSurfaceView.onResume();

        glSurfaceView.postDelayed(new Runnable() {
            @Override
            public void run() {
                glSurfaceView.captureBitmap(new MyGLSurfaceView.BitmapReadyCallbacks() {
                    @Override
                    public void onBitmapReady(Bitmap bitmap) {
                        ((ImageView)findViewById(R.id.iv)).setImageBitmap(bitmap);
                    }
                });
            }
        }, 2000);
    }
    @Override
    protected void onPause(){
        super.onPause();
        glSurfaceView.onPause();
    }
}
