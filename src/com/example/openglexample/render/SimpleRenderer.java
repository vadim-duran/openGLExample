package com.example.openglexample.render;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class SimpleRenderer implements Renderer {

	private Context mContext;
	
	public SimpleRenderer(Context context) {
		mContext = context;
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
        // ������� ������ ������� � �����
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // �������� � ����� ������ � �������� ������-����
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // ������� ������� ������-���� �� ���������
        gl.glLoadIdentity();
        // ����� �������� ������ ����� � ��� ���������.......  
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// ��������� ������� ��������� ������ ������� ������
		gl.glViewport(0, 0, width, height);
		// ���������� ��������� ������/������
		float ratio = (float) width / height;
		// �������� � ����� ������ � �������� ��������
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// ������� ������� �������� �� ���������
		gl.glLoadIdentity();
        // ������������� ������������� ��������
        // ���� ������ 60 ��������
        // �������� ���������� ��������� 0.1
        // ������ ���������� ��������� 100
		GLU.gluPerspective(gl, 60, ratio, 0.1f, 100f);
		// �������� � ����� ������ � �������� ������-����
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// ������� �������� �������� �� ��������� �����
        gl.glEnable(GL10.GL_NORMALIZE);
        // ������� ����������� ������
        gl.glShadeModel(GL10.GL_SMOOTH);
        // ������� �������� �������
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // �������� ������������ ���������
        gl.glEnable(GL10.GL_LIGHTING); // �.�.�.
        
        // ����� ��������� �������� .................. 
	}

}
