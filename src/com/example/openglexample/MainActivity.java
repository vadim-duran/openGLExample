package com.example.openglexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnItemClickListener{

	private Context mContext;
	private final static int TRIANGLE_ITEM = 0;
	private final static int BACKGROUND_ITEM = 1;
	private final static int CUBE_ITEM = 2;
	private ListView mList;
	private String[] mItems = {"Triangle", "Background", "Cube"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		mList = (ListView) findViewById(R.id.listView);
		mList.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, mItems));
		mList.setOnItemClickListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
		case TRIANGLE_ITEM:
			Intent intentTriangle = new Intent(mContext, TriangleActivity.class);
			startActivity(intentTriangle);
			break;
		case BACKGROUND_ITEM:
			Intent intentBackground = new Intent(mContext, BackgroundActivity.class);
			startActivity(intentBackground);
			break;
		case CUBE_ITEM:
			Intent intentCube = new Intent(mContext, CubeActivity.class);
			startActivity(intentCube);
			break;

		default:
			break;
		}
	}
}
