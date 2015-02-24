package com.example.openglexample.view;

import com.example.openglexample.render.TriangleRenderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class TriangleSurfaceView extends GLSurfaceView{
	
	public TriangleSurfaceView(Context context) {
		super(context);
		
		setRenderer(new TriangleRenderer(context));
		// установим режим циклического запуска метода onDrawFrame
        // в рендерере
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

}
