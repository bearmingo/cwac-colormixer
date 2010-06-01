package com.commonsware.cwac.colormixer.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import com.commonsware.cwac.colormixer.ColorMixer;
import com.commonsware.cwac.colormixer.ColorMixerDialog;

public class ColorMixerDemo extends Activity {
	private TextView color=null;
	private ColorMixer mixer=null;	
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		color=(TextView)findViewById(R.id.color);
		
		mixer=(ColorMixer)findViewById(R.id.mixer);
		mixer.setOnColorChangedListener(onColorChange);
	}
	
	private ColorMixer.OnColorChangedListener onColorChange=
		new ColorMixer.OnColorChangedListener() {
		public void onColorChange(int argb) {
			color.setText(Integer.toHexString(argb));
		}
	};
	
	private ColorMixer.OnColorChangedListener onDialogSet=
		new ColorMixer.OnColorChangedListener() {
		public void onColorChange(int argb) {
			mixer.setColor(argb);
			color.setText(Integer.toHexString(argb));
		}
	};
	
	public void popDialog(View v) {
		new ColorMixerDialog(this, mixer.getColor(), onDialogSet).show();
	}
	
	public void editPrefs(View v) {
		startActivity(new Intent(this, EditPreferences.class));
	}
}