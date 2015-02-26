package com.example.openglexample.utils;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;

public class CheckOpenGLES2 {

	public static boolean checkES2(ActivityManager manager){
		ConfigurationInfo info = manager.getDeviceConfigurationInfo();
		boolean check = info.reqGlEsVersion >= 0x20000;
		return check;
	}
	
}
