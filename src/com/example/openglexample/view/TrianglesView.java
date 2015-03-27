package com.example.openglexample.view;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

public class TrianglesView {

	private final FloatBuffer mTriangle1Vertices;
	private final FloatBuffer mTriangle2Vertices;
	private final FloatBuffer mTriangle3Vertices;
	
	private final int mBytesPerFloat = 4;
	/**
	 * Определяем матрицу ВИДА. Её можно рассматривать как камеру. Эта матрица описывает пространство;
	 * она задает положение предметов относительно нашего глаза.
	 */
	private float[] mViewMatrix = new float[16];
	
	private int mMVPMatrixHandle;
	
	private int mPositionHandle;
	
	private int mColorHandle;
	
	private float[] mModelMatrix = new float[16];
	
	private float[] mMVPMatrix = new float[16];
	
	private final int mStrideBytes = 7 * mBytesPerFloat;	
	
	private final int mPositionOffset = 0;
	
	private final int mPositionDataSize = 3;
	
	private final int mColorOffset = 3;
	
	private final int mColorDataSize = 4;	
	
	private float[] mProjectionMatrix = new float[16];
	
	public TrianglesView() {
		// Треугольники красный, зеленый, и синий.
		final float[] triangle1VerticesData = {
				// X, Y, Z, 
				// R, G, B, A
	            -0.5f, -0.25f, 0.0f, 
	            1.0f, 0.0f, 0.0f, 1.0f,
	            
	            0.5f, -0.25f, 0.0f,
	            0.0f, 0.0f, 1.0f, 1.0f,
	            
	            0.0f, 0.559016994f, 0.0f, 
	            0.0f, 1.0f, 0.0f, 1.0f};
		
		// This triangle is yellow, cyan, and magenta.
		final float[] triangle2VerticesData = {
				// X, Y, Z, 
				// R, G, B, A
	            -0.5f, -0.25f, 0.0f, 
	            1.0f, 1.0f, 0.0f, 1.0f,
	            
	            0.5f, -0.25f, 0.0f, 
	            0.0f, 1.0f, 1.0f, 1.0f,
	            
	            0.0f, 0.559016994f, 0.0f, 
	            1.0f, 0.0f, 1.0f, 1.0f};
		
		// This triangle is white, gray, and black.
		final float[] triangle3VerticesData = {
				// X, Y, Z, 
				// R, G, B, A
	            -0.5f, -0.25f, 0.0f, 
	            1.0f, 1.0f, 1.0f, 1.0f,
	            
	            0.5f, -0.25f, 0.0f, 
	            0.5f, 0.5f, 0.5f, 1.0f,
	            
	            0.0f, 0.559016994f, 0.0f, 
	            0.0f, 0.0f, 0.0f, 1.0f};
		
		// Initialize the buffers.
		mTriangle1Vertices = ByteBuffer.allocateDirect(triangle1VerticesData.length * mBytesPerFloat)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();
		mTriangle2Vertices = ByteBuffer.allocateDirect(triangle2VerticesData.length * mBytesPerFloat)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();
		mTriangle3Vertices = ByteBuffer.allocateDirect(triangle3VerticesData.length * mBytesPerFloat)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();
					
		mTriangle1Vertices.put(triangle1VerticesData).position(0);
		mTriangle2Vertices.put(triangle2VerticesData).position(0);
		mTriangle3Vertices.put(triangle3VerticesData).position(0);
	}
	
	public void setMatrixView(float eyeX, float eyeY, float eyeZ, float lookX, float lookY, float lookZ, float upX, float upY, float upZ){
		Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
	}
	
	public void setMatrixmProjection(float left, float right, float bottom, float top, float near, float far){
		Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
	}
	
	public void createProgramm(){
		final String vertexShader =
				"uniform mat4 u_MVPMatrix;      \n"		// Константа отвечающая за комбинацию матриц МОДЕЛЬ/ВИД/ПРОЕКЦИЯ.
				
			  + "attribute vec4 a_Position;     \n"		// Информация о положении вершин.
			  + "attribute vec4 a_Color;        \n"		// Информация о цвете вершин.			  
			  
			  + "varying vec4 v_Color;          \n"		// Это будет передано в фрагментный шейдер.
			  
			  + "void main()                    \n"		// Начало программы вершинного шейдера.
			  + "{                              \n"
			  + "   v_Color = a_Color;          \n"		// Передаем цвет для фрагментного шейдера.
			  											// Он будет интерполирован для всего треугольника.
			  + "   gl_Position = u_MVPMatrix   \n" 	// gl_Position специальные переменные используемые для хранения конечного положения.
			  + "               * a_Position;   \n"     // Умножаем вершины на матрицу для получения конечного положения 		                                            			 
			  + "}                              \n";    // в нормированных координатах экрана.
			
			final String fragmentShader =
				    "precision mediump float;       \n"     // Устанавливаем по умолчанию среднюю точность для переменных. Максимальная точность
				                                            // в фрагментном шейдере не нужна.
				  + "varying vec4 v_Color;          \n"     // Цвет вершинного шейдера преобразованного
				                                            // для фрагмента треугольников.
				  + "void main()                    \n"     // Точка входа для фрагментного шейдера.
				  + "{                              \n"
				  + "   gl_FragColor = v_Color;     \n"     // Передаем значения цветов.
				  + "}                              \n";										
			
			// Загрузка вершинного шейдера.
			int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
			 
			if (vertexShaderHandle != 0)
			{
			    // Передаем в наш шейдер программу.
			    GLES20.glShaderSource(vertexShaderHandle, vertexShader);
			 
			    // Компиляция шейреда
			    GLES20.glCompileShader(vertexShaderHandle);
			 
			    // Получаем результат процесса компиляции
			    final int[] compileStatus = new int[1];
			    GLES20.glGetShaderiv(vertexShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
			 
			    // Если компиляция не удалась, удаляем шейдер.
			    if (compileStatus[0] == 0)
			    {
			        GLES20.glDeleteShader(vertexShaderHandle);
			        vertexShaderHandle = 0;
			    }
			}
			 
			if (vertexShaderHandle == 0)
			{
			    throw new RuntimeException("Error creating vertex shader.");
			}
			
			// Load in the fragment shader shader.
			int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

			if (fragmentShaderHandle != 0) 
			{
				// Pass in the shader source.
				GLES20.glShaderSource(fragmentShaderHandle, fragmentShader);

				// Compile the shader.
				GLES20.glCompileShader(fragmentShaderHandle);

				// Get the compilation status.
				final int[] compileStatus = new int[1];
				GLES20.glGetShaderiv(fragmentShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

				// If the compilation failed, delete the shader.
				if (compileStatus[0] == 0) 
				{				
					GLES20.glDeleteShader(fragmentShaderHandle);
					fragmentShaderHandle = 0;
				}
			}

			if (fragmentShaderHandle == 0)
			{
				throw new RuntimeException("Error creating fragment shader.");
			}
			
			// Создаем объект программы вместе со ссылкой на нее.
			int programHandle = GLES20.glCreateProgram();
			 
			if (programHandle != 0)
			{
			    // Подключаем вершинный шейдер к программе.
			    GLES20.glAttachShader(programHandle, vertexShaderHandle);
			 
			    // Подключаем фрагментный шейдер к программе.
			    GLES20.glAttachShader(programHandle, fragmentShaderHandle);
			 
			    // Подключаем атрибуты цвета и положения
			    GLES20.glBindAttribLocation(programHandle, 0, "a_Position");
			    GLES20.glBindAttribLocation(programHandle, 1, "a_Color");
			 
			    // Объединяем оба шейдера в программе.
			    GLES20.glLinkProgram(programHandle);
			 
			    // Получаем ссылку на программу.
			    final int[] linkStatus = new int[1];
			    GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);
			 
			    // Если ссылку не удалось получить, удаляем программу.
			    if (linkStatus[0] == 0)
			    {
			        GLES20.glDeleteProgram(programHandle);
			        programHandle = 0;
			    }
			}
			 
			if (programHandle == 0)
			{
			    throw new RuntimeException("Error creating program.");
			}
	        
			// Установить настройки вручную. Это будет позже использовано для передачи значений в программу.
	        mMVPMatrixHandle = GLES20.glGetUniformLocation(programHandle, "u_MVPMatrix");        
	        mPositionHandle = GLES20.glGetAttribLocation(programHandle, "a_Position");
	        mColorHandle = GLES20.glGetAttribLocation(programHandle, "a_Color");        
	        
	     	// Сообщить OpenGL чтобы использовал эту программу при рендеринге.
	        GLES20.glUseProgram(programHandle); 
	}
	
	public void draw (GL10 gl){
		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);			        
        
        // Do a complete rotation every 10 seconds.
        long time = SystemClock.uptimeMillis() % 10000L;
        float angleInDegrees = (360.0f / 10000.0f) * ((int) time);
        
        // Draw the triangle facing straight on.
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 0.0f, 0.0f, 1.0f);        
        drawTriangle(mTriangle1Vertices);
        
        // Draw one translated a bit down and rotated to be flat on the ground.
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, 0.0f, -1.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, 90.0f, 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 0.0f, 0.0f, 1.0f);        
        drawTriangle(mTriangle2Vertices);
        
        // Draw one translated a bit to the right and rotated to be facing to the left.
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, 90.0f, 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 0.0f, 0.0f, 1.0f);
        drawTriangle(mTriangle3Vertices);
	}	
	
	/**
	 * Draws a triangle from the given vertex data.
	 * 
	 * @param aTriangleBuffer The buffer containing the vertex data.
	 */
	private void drawTriangle(final FloatBuffer aTriangleBuffer)
	{		
		// Pass in the position information
		aTriangleBuffer.position(mPositionOffset);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
        		mStrideBytes, aTriangleBuffer);        
                
        GLES20.glEnableVertexAttribArray(mPositionHandle);        
        
        // Pass in the color information
        aTriangleBuffer.position(mColorOffset);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
        		mStrideBytes, aTriangleBuffer);        
        
        GLES20.glEnableVertexAttribArray(mColorHandle);
        
		// This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        
        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);                               
	}
}
