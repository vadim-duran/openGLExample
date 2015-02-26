package com.example.openglexample.activity;

import com.example.openglexample.R;
import com.example.openglexample.R.id;
import com.example.openglexample.R.menu;
import com.example.openglexample.view.TriangleSurfaceView;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class TriangleActivity extends ActionBarActivity {

	private TriangleSurfaceView mSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSurfaceView = new TriangleSurfaceView(this);
		setContentView(mSurfaceView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSurfaceView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSurfaceView.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
