package com.example.openglexample.render;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class TriangleRenderer implements Renderer {

	private Context mContext;
	
	public TriangleRenderer(Context context) {
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
        
        FloatBuffer vertexBuffer;
        ByteBuffer bb = ByteBuffer.allocateDirect(72);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();      
        
        float [] trianglecoord={0,0,0,  1,0,0,  0,1,0,  0,0,0,  -1,0,0,  0,-1,0};
        vertexBuffer.position(0);
        vertexBuffer.put(trianglecoord);
        vertexBuffer.position(0);
        
        FloatBuffer colorBuffer;     
        ByteBuffer bbColor = ByteBuffer.allocateDirect(96);
        bbColor.order(ByteOrder.nativeOrder());
        colorBuffer = bbColor.asFloatBuffer();
        
        float [] colorArray={1,0,0,1,  0,1,0,1,  0,0,1,1,  1,0,0,1,  0,1,0,1,  0,0,1,1};
        colorBuffer.position(0);
        colorBuffer.put(colorArray);
        colorBuffer.position(0);
        
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4,GL10.GL_FLOAT,0,colorBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLES,0,9); 
        
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
//		// ��������� ������� ��������� ������ ������� ������
		gl.glViewport(0, 0, width, height);
//		// ���������� ��������� ������/������
//		float ratio = (float) width / height;
//		// �������� � ����� ������ � �������� ��������
//		gl.glMatrixMode(GL10.GL_PROJECTION);
//		// ������� ������� �������� �� ���������
//		gl.glLoadIdentity();
//        // ������������� ������������� ��������
//        // ���� ������ 60 ��������
//        // �������� ���������� ��������� 0.1
//        // ������ ���������� ��������� 100
//		GLU.gluPerspective(gl, 60, ratio, 0.1f, 100f);
//		// �������� � ����� ������ � �������� ������-����
//		gl.glMatrixMode(GL10.GL_MODELVIEW);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// ������� �������� �������� �� ��������� �����
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
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
