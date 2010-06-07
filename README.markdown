CWAC ColorMixer: Appealing To Your Users' Sense of Fashion
==========================================================
Sometimes, you want your users to pick a color. A simple
approach is to give the user a fixed roster of a handful
of colors -- easy, but limited. A fancy approach is to use
some form of color wheel, but these can be difficult to use
on a touchscreen and perhaps impossible without a touchscreen.

`ColorMixer` is a widget that provides a simple set of `SeekBars`
to let the user mix red, green, and blue to pick an arbitrary color.
It is not very big, so it is easy to fit on a form, and it is still
fairly finger-friendly.

It is also packaged as a dialog (`ColorMixerDialog`) and a
preference (`ColorPreference`).

This is available from [the Android Parcel Project](http://andparcel.com) as the
`cwac-colormixer` parcel.

Usage
-----

### ColorMixer

`ColorMixer` is a simple widget. Given that you have the parcel
installed in your project, or have manually merged the source
and resources into your project, you can add the widget to a
layout like any other:

	<com.commonsware.cwac.colormixer.ColorMixer
		android:id="@+id/mixer"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
	/>

You can call `getColor()` and `setColor()` to manipulate the
color at runtime. You can also call `setOnColorChangedListener()`
to register a `ColorMixer.OnColorChangedListener` object, which
will be called with `onColorChanged()` when the color is altered
by the user.

### ColorMixerDialog

`ColorMixerDialog` is an `AlertDialog` subclass. Hence, to create
and show the dialog, all you need to do is create an instance
and `show()` it:

	new ColorMixerDialog(this, someColor, onDialogSet).show();

In the above code snippet, `this` is a `Context` (e.g., an `Activity`),
`someColor` is the color you want to start with, and `onDialogSet`
is a `ColorMixer.OnColorChangedListener` that will be notified
*if* the user clicks the "Set" button on the dialog *and* has
changed the color from the initial value.

### ColorPreference

`ColorPreference` is a `Preference` class, to be referenced
in preference XML and loaded into a `PreferenceActivity`. It
has no attributes beyond the standard ones.

	<PreferenceScreen
		xmlns:android="http://schemas.android.com/apk/res/android">
		<com.commonsware.cwac.colormixer.ColorPreference
			android:key="favoriteColor"
			android:defaultValue="0xFFA4C639"
			android:title="Your Favorite Color"
			android:summary="Blue.  No yel--  Auuuuuuuugh!" />
	</PreferenceScreen>

The preference is stored as an integer under the key you
specify in the XML.

Dependencies
------------
This depends upon the `cwac-parcel` parcel for accessing
project-level resources.

Version
-------
This is version 0.3.3 of this module, meaning it is creeping
towards respectability.

Demo
----
There is a `demo/` directory containing a demo project. If you
have the Android Parcel Project client installed, you can
run the `ant demo` command to install the requisite parcels into
the demo project and install the resulting APK into your
attached emulator or device.

License
-------
The code in this project is licensed under the Apache
Software License 2.0, per the terms of the included LICENSE
file.

Questions
---------
If you have questions regarding the use of this code, please
join and ask them on the [cw-android Google Group][gg]. Be sure to
indicate which CWAC module you have questions about.

Who Made This?
--------------
<a href="http://commonsware.com">![CommonsWare](http://commonsware.com/images/logo.png)</a>

[gg]: http://groups.google.com/group/cw-android
