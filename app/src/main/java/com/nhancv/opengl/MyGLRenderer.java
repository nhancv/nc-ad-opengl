package com.nhancv.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.nhancv.opengl.shape.Square;
import com.nhancv.opengl.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
    private Square mSquare;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mTriangle.draw();
    }
}
