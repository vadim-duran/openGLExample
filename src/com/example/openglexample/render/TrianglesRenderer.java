package com.example.openglexample.render;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.example.openglexample.view.TrianglesView;

import android.opengl.GLSurfaceView.Renderer;

public class TrianglesRenderer implements Renderer{

	private TrianglesView mView;
	
	public TrianglesRenderer() {
		mView = new TrianglesView();
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		mView.draw(gl);
	}

}
