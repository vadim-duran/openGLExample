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
		// ������������� ���� ���� ������ �����.
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
	
		// ��������� �����, ����� ���������� � ������������.
		final float eyeX = 0.0f;
		final float eyeY = 0.0f;
		final float eyeZ = 1.5f;

		// �� ����� ���������� �� ����� ������ ������. �������������� ��������� ������.
		final float lookX = 0.0f;
		final float lookY = 0.0f;
		final float lookZ = -5.0f;

		// ������������� ������. ��������� ��� ���� ������ ���������� �� ���� �� �� ������� ������.
		final float upX = 0.0f;
		final float upY = 1.0f;
		final float upZ = 0.0f;
		
		// ������������� ������� ����. ��� ��������� ��������� ������.
	    // ����������: � OpenGL 1, ������� ModelView ������������ ��� ���������� ������� ������
	    // � ������� ����. � OpenGL 2, �� ����� �������� � ����� ��������� �������� �� ������.
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
