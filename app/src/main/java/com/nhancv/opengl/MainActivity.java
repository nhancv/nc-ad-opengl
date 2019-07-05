package com.nhancv.opengl;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nhancv.opengl.fps.FpsListener;
import com.nhancv.opengl.fps.StableFps;

//https://developer.android.com/training/graphics/opengl/environment.html#java
public class MainActivity extends AppCompatActivity {
    private StableFps stableFps;
    private MyGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        setContentView(R.layout.activity_main);
        glSurfaceView = findViewById(R.id.my_glsurfaceview);
        glSurfaceView.setZOrderOnTop(true);

        stableFps = new StableFps(30);

    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();

        if (!stableFps.isStarted()) {
            stableFps.start(new FpsListener() {
                @Override
                public void update(int fps) {
                    glSurfaceView.captureBitmap(new MyGLSurfaceView.BitmapReadyCallbacks() {
                        @Override
                        public void onBitmapReady(Bitmap bitmap) {
                            ((ImageView) findViewById(R.id.iv)).setImageBitmap(bitmap);
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onPause() {
        stableFps.stop();
        glSurfaceView.onPause();
        super.onPause();
    }
}
