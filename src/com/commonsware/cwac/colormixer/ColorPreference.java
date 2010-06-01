/***
	Copyright (c) 2008-2010 CommonsWare, LLC
	Portions Copyright (c) 2007 The Android Open Source Project
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.commonsware.cwac.colormixer;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.commonsware.cwac.colormixer.ColorMixer;


public class ColorPreference extends DialogPreference {
	private int lastColor=0;

	public ColorPreference(Context ctxt, AttributeSet attrs) {
			this(ctxt, attrs, 0);
	}

	public ColorPreference(Context ctxt) {
			this(ctxt, null);
	}

	public ColorPreference(Context ctxt, AttributeSet attrs, int defStyle) {
    super(ctxt, attrs, defStyle);
  }
	
	@Override
	protected View onCreateDialogView() {
		ColorMixer mixer=new ColorMixer(getContext());
		
		mixer.setOnColorChangedListener(onColorChange);
		
		return(mixer);
	}
	
	@Override
  protected void onBindDialogView(View v) {
    super.onBindDialogView(v);
		
    ((ColorMixer)v).setColor(lastColor);
  }
	
	@Override
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);

		if (positiveResult) {
			if (callChangeListener(lastColor)) {
				setColor(lastColor);
			}
		}
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		return(a.getInt(index, 0xFFA4C639));
	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		setColor(restoreValue ? getPersistedInt(lastColor) : (Integer)defaultValue);
	}
	
	@Override
	protected Parcelable onSaveInstanceState() {
		final Parcelable superState=super.onSaveInstanceState();
		
		if (isPersistent()) {
			// No need to save instance state since it's persistent
			return(superState);
		}

		final SavedState myState=new SavedState(superState);
		myState.color=lastColor;
		
		return(myState);
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state==null || !state.getClass().equals(SavedState.class)) {
			// Didn't save state for us in onSaveInstanceState
			super.onRestoreInstanceState(state);
			return;
		}

		SavedState myState=(SavedState)state;
		super.onRestoreInstanceState(myState.getSuperState());
		setColor(myState.color);
	}
	
	private void setColor(int color) {
		lastColor=color;
		persistInt(color);
	}
	
	private ColorMixer.OnColorChangedListener onColorChange=
		new ColorMixer.OnColorChangedListener() {
		public void onColorChange(int argb) {
			setColor(argb);
		}
	};
	
	private static class SavedState extends BaseSavedState {
		int color;

		public SavedState(Parcel source) {
			super(source);
			color=source.readInt();
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeInt(color);
		}

		public SavedState(Parcelable superState) {
			super(superState);
		}

		public static final Parcelable.Creator<SavedState> CREATOR =
						new Parcelable.Creator<SavedState>() {
			public SavedState createFromParcel(Parcel in) {
				return(new SavedState(in));
			}

			public SavedState[] newArray(int size) {
				return(new SavedState[size]);
			}
		};
	}
}