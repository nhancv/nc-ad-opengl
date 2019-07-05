package com.nhancv.opengl.shape;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.DisplayMetrics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Circle {
    private FloatBuffer vertexBuffer;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";


    // Use to access and set the view transformation
    private int vPMatrixHandle;
    private final int app;

    private int positionHandle;
    private int colorHandle;

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    // basically a circle is a linestring so we need its centre
    // radius and how many segments it will consist of
    public Circle(float cx, float cy, float radius, int segments) {
        calculatePoints(cx, cy, radius, segments);
        int vertexShader = ShaderUtil.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = ShaderUtil.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        app = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(app, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(app, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(app);
    }

    // calculate the segments
    public void calculatePoints(float cx, float cy, float radius, int segments) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();

        float[] coordinates = new float[segments * COORDS_PER_VERTEX];

        for (int i = 0; i < segments * 3; i += 3) {
            float percent = (i / (segments - 1f));
            float rad = percent * 2f * (float) Math.PI;

            //Vertex position
            float xi = cx + radius * (float) Math.cos(rad);
            float yi = cy + radius * (float) Math.sin(rad);

            coordinates[i] = xi;
            coordinates[i + 1] = yi / (((float) dm.heightPixels / (float) dm.widthPixels));
            coordinates[i + 2] = 0.0f;
        }

        // initialise vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(coordinates.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(coordinates);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
    }

    public void draw() {
        int vertexCount = vertexBuffer.remaining() / COORDS_PER_VERTEX;

        // Add program to the environment
        GLES20.glUseProgram(app);

        // get handle to vertex shader's vPosition member
        int mPositionHandle = GLES20.glGetAttribLocation(app, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(app, "vColor");

        // Draw the triangle, using triangle fan is the easiest way
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);

        // Set color of the shape (circle)
        GLES20.glUniform4fv(colorHandle, 1, new float[]{0.5f, 0.3f, 0.1f, 1f}, 0);
    }
}