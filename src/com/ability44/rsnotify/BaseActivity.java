package com.ability44.rsnotify;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;


/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class BaseActivity extends Activity {
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

			case R.id.menu_about :
				new AlertDialog.Builder(this)
						.setTitle("About")
						.setMessage(
								"This application is for use of keeping up with Runescape updates. I hope you find this app useful and feel free to give any feedback you have.\n(By: Ability44)\n\nThis application is not in affiliation with Jagex.")
						.setNeutralButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

									}
								})
						.setPositiveButton("Review RsNews",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										String playuri = "market://details?id=com.ability44.rsnotify";
										if (appInstalledOrNot(playuri) == true) {
											Intent intent = new Intent(
													Intent.ACTION_VIEW, Uri
															.parse(playuri));
											startActivity(intent);
										} else {
											Intent intent = new Intent(
													Intent.ACTION_VIEW,
													Uri.parse("https://play.google.com/store/apps/details?id=com.ability44.rsnotify"));
											startActivity(intent);
										}
									}
								}).show();
				break;

		}

		return true;
	}

	private boolean appInstalledOrNot(String uri) {
		PackageManager pm = getPackageManager();
		boolean app_installed = false;
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	
}
