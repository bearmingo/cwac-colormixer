package com.commonsware.cwac.colormixer.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import com.commonsware.cwac.colormixer.ColorMixer;
import com.commonsware.cwac.colormixer.ColorMixerActivity;
import com.commonsware.cwac.colormixer.ColorMixerDialog;

public class ColorMixerDemo extends Activity {
  private static final int COLOR_REQUEST=1337;
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
  
  @Override
  public void onActivityResult(int requestCode, int resultCode,
                               Intent result) {
    if (requestCode==COLOR_REQUEST && resultCode==RESULT_OK) {
      mixer.setColor(result.getIntExtra(ColorMixerActivity.COLOR,
                                        mixer.getColor()));
    }
    else {
      super.onActivityResult(requestCode, resultCode, result);
    }
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
  
  public void popActivity(View v) {
    Intent i=new Intent(this, ColorMixerActivity.class);
    
    i.putExtra(ColorMixerActivity.TITLE, "Pick a Color");
    i.putExtra(ColorMixerActivity.COLOR, mixer.getColor());
    
    startActivityForResult(i, COLOR_REQUEST);
  }
  
  public void editPrefs(View v) {
    startActivity(new Intent(this, EditPreferences.class));
  }
}