package com.example.openglexample.render;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.example.openglexample.view.TrianglesView;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class TrianglesRenderer implements Renderer{

	private TrianglesView mView;
	
	public TrianglesRenderer() {
		mView = new TrianglesView();
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Устанавливаем цвет фона светло серый.
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
	
		// Положение глаза, точки наблюдения в пространстве.
		final float eyeX = 0.0f;
		final float eyeY = 0.0f;
		final float eyeZ = 1.5f;

		// На какое расстояние мы можем видеть вперед. Ограничивающая плоскость обзора.
		final float lookX = 0.0f;
		final float lookY = 0.0f;
		final float lookZ = -5.0f;

		// Устанавливаем вектор. Положение где наша голова находилась бы если бы мы держали камеру.
		final float upX = 0.0f;
		final float upY = 1.0f;
		final float upZ = 0.0f;
		
		// Устанавливаем матрицу ВИДА. Она описывает положение камеры.
	    // Примечание: В OpenGL 1, матрица ModelView используется как комбинация матрицы МОДЕЛИ
	    // и матрицы ВИДА. В OpenGL 2, мы можем работать с этими матрицами отдельно по выбору.
		mView.setMatrixView(eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
		mView.createProgramm();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Set the OpenGL viewport to the same size as the surface.
		GLES20.glViewport(0, 0, width, height);

		// Create a new perspective projection matrix. The height will stay the same
		// while the width will vary as per aspect ratio.
		final float ratio = (float) width / height;
		final float left = -ratio;
		final float right = ratio;
		final float bottom = -1.0f;
		final float top = 1.0f;
		final float near = 1.0f;
		final float far = 10.0f;
		mView.setMatrixmProjection(left, right, bottom, top, near, far);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		mView.draw(gl);
	}

}
