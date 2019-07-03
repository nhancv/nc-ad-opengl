package com.nhancv.opengl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
}
