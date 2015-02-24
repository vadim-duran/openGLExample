package com.example.openglexample.service;

import android.content.Context;
import android.view.MotionEvent;

import com.example.openglexample.render.TriangleRenderer;

public class TriangleService extends GLWallpaperService {

	private Context mContext;
	
	@Override
	public Engine onCreateEngine() {
		MyEngine engine = new MyEngine();
		return engine;
	}

	public TriangleService(Context context) {
		super();
		mContext = context;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	class MyEngine extends GLEngine {
		private TriangleRenderer renderer;

		public MyEngine() {
			super();
			setEGLContextClientVersion(2);
			renderer = new TriangleRenderer(mContext);
			setRenderer(renderer);
			setRenderMode(1);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
		}

		@Override
		public void onTouchEvent(MotionEvent event) {
			// если нужно обработать тап по экрану
			// renderer.onTouchEvent(event);
		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset,
				float xOffsetStep, float yOffsetStep, int xPixelOffset,
				int yPixelOffset) {
			// если нужно обработать сдвиг экрана
			// renderer.onOffsetsChanged(xOffset, yOffset, xOffsetStep,
			// yOffsetStep, xPixelOffset, yPixelOffset);
		}
	}
}
