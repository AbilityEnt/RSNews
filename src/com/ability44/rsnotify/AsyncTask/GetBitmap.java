package com.ability44.rsnotify.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class GetBitmap extends AsyncTask<ImageView, Void, Bitmap>{
	
	ImageView imageView = null;

	// Sets the Bitmap returned by doInBackground
	@Override
	protected void onPostExecute(Bitmap result) {
		imageView.setImageBitmap(result);
	}

	// Creates Bitmap from InputStream and returns it
	private Bitmap downloadImage(String url) {
		Bitmap bitmap = null;
		InputStream stream = null;
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize = 1;

		try {
			stream = getHttpConnection(url);
			bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
			stream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return bitmap;
	}

	// Makes HttpURLConnection and returns InputStream
	private InputStream getHttpConnection(String urlString)
			throws IOException {
		InputStream stream = null;
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();

		try {
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			httpConnection.setRequestMethod("GET");
			httpConnection.connect();

			if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				stream = httpConnection.getInputStream();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stream;
	}

	@Override
	protected Bitmap doInBackground(ImageView... imageViews) {
		this.imageView = imageViews[0];
	    return downloadImage((String)imageView.getTag());
	}
}
