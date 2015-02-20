package com.example.openglexample.view;

import com.example.openglexample.render.SimpleRenderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class SimpleSurfaceView extends GLSurfaceView{
	
	public SimpleSurfaceView(Context context) {
		super(context);
		
		setRenderer(new SimpleRenderer(context));
		// установим режим циклического запуска метода onDrawFrame
        // в рендерере
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

}
