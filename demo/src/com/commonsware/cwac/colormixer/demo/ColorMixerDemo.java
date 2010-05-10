package com.commonsware.cwac.colormixer.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.commonsware.cwac.colormixer.ColorMixer;

public class ColorMixerDemo extends Activity {
	private TextView color=null;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		color=(TextView)findViewById(R.id.color);
		
		ColorMixer mixer=(ColorMixer)findViewById(R.id.mixer);
		
		mixer.setOnColorChangedListener(onColorChange);
	}
	
	private ColorMixer.OnColorChangedListener onColorChange=
		new ColorMixer.OnColorChangedListener() {
		public void onColorChange(int argb) {
			color.setText(Integer.toHexString(argb));
		}
	};
}