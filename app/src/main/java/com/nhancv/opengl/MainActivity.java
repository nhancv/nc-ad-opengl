package com.nhancv.opengl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//https://developer.android.com/training/graphics/opengl/environment.html#java
public class MainActivity extends AppCompatActivity {

    private MyGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        glSurfaceView = new MyGLSurfaceView(this);
        setContentView(glSurfaceView);
    }
}
