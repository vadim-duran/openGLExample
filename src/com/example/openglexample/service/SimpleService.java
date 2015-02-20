package com.example.openglexample.service;

import android.content.Context;
import android.view.MotionEvent;

import com.example.openglexample.render.SimpleRenderer;

public class SimpleService extends GLWallpaperService {

	private Context mContext;
	
	@Override
	public Engine onCreateEngine() {
		MyEngine engine = new MyEngine();
		return engine;
	}

	public SimpleService(Context context) {
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
		private SimpleRenderer renderer;

		public MyEngine() {
			super();
			setEGLContextClientVersion(2);
			renderer = new SimpleRenderer(mContext);
			setRenderer(renderer);
			setRenderMode(1);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
		}

		@Override
		public void onTouchEvent(MotionEvent event) {
			// ���� ����� ���������� ��� �� ������
			// renderer.onTouchEvent(event);
		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset,
				float xOffsetStep, float yOffsetStep, int xPixelOffset,
				int yPixelOffset) {
			// ���� ����� ���������� ����� ������
			// renderer.onOffsetsChanged(xOffset, yOffset, xOffsetStep,
			// yOffsetStep, xPixelOffset, yPixelOffset);
		}
	}
}
