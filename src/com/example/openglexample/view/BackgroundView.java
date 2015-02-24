package com.example.openglexample.view;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.widget.Button;

public class BackgroundView {

	private float[] mVertices = {0,0,0,  1,0,0,  0,1,0};
	private FloatBuffer mVertBuff;
	private short[] mIndex = {0, 1, 2};
	private ShortBuffer mBuff;
	
	public BackgroundView() {
		ByteBuffer buffer = ByteBuffer.allocateDirect(mVertices.length * 4);
		buffer.order(ByteOrder.nativeOrder());
		mVertBuff = buffer.asFloatBuffer();
		mVertBuff.position(0);
		mVertBuff.put(mVertices);
		mVertBuff.position(0);
		

		
		
		ByteBuffer bufferShort = ByteBuffer.allocateDirect(mIndex.length * 2);
		bufferShort.order(ByteOrder.nativeOrder());
		mBuff = bufferShort.asShortBuffer();
		mBuff.put(mIndex);
		mBuff.position(0);
	}
	
	public void draw (GL10 gl){
		gl.glFrontFace(GL10.GL_CW);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertBuff);
        gl.glDrawElements(GL10.GL_TRIANGLES, mIndex.length, GL10.GL_UNSIGNED_SHORT, mBuff); 
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
