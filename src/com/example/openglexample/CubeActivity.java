package com.example.openglexample;

import com.example.openglexample.render.CubeRenderer;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class CubeActivity extends ActionBarActivity{

	private GLSurfaceView mSurfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mSurfaceView = new GLSurfaceView(this);
		mSurfaceView.setRenderer(new CubeRenderer());
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
