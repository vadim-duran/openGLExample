package com.example.openglexample.activity;

import com.example.openglexample.render.BackgroundRenderer;
import com.example.openglexample.render.TrianglesRenderer;
import com.example.openglexample.utils.CheckOpenGLES2;
import com.example.openglexample.view.BackgroundView;

import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class TrianglesActivity extends ActionBarActivity{

	private GLSurfaceView mSurfaceView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mSurfaceView = new GLSurfaceView(this);
//		if(CheckOpenGLES2.checkES2((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE))){
//			mSurfaceView.setEGLContextClientVersion(2);
			mSurfaceView.setRenderer(new TrianglesRenderer());
//		} else {
//			
//		}
		setContentView(mSurfaceView);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mSurfaceView.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mSurfaceView.onResume();
	}
	
}
