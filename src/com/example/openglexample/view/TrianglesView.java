package com.example.openglexample.view;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class TrianglesView {

	
	
	public TrianglesView() {
//		ByteBuffer buffer = ByteBuffer.allocateDirect(mVertices.length * 4);
//		buffer.order(ByteOrder.nativeOrder());
//		mVertBuff = buffer.asFloatBuffer();
//		mVertBuff.put(mVertices);
//		mVertBuff.position(0);
//		
//		ByteBuffer bufferColor = ByteBuffer.allocateDirect(mColorRGBA.length * 4);
//		bufferColor.order(ByteOrder.nativeOrder());
//		mColorBuff = bufferColor.asFloatBuffer();
//		mColorBuff.put(mColorRGBA);
//		mColorBuff.position(0);
//		
//		ByteBuffer bufferShort = ByteBuffer.allocateDirect(mIndex.length * 2);
//		bufferShort.order(ByteOrder.nativeOrder());
//		mBuff = bufferShort.asShortBuffer();
//		mBuff.put(mIndex);
//		mBuff.position(0);
	}
	
	public void draw (GL10 gl){
//		gl.glFrontFace(GL10.GL_CW);
//		gl.glEnable(GL10.GL_CULL_FACE);
//		gl.glCullFace(GL10.GL_BACK);
//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
//        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertBuff);
//        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuff);
//        gl.glDrawElements(GL10.GL_TRIANGLES, mIndex.length, GL10.GL_UNSIGNED_SHORT, mBuff); 
//        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
//        gl.glDisable(GL10.GL_CULL_FACE);
	}
}
