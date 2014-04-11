package org.tinovation.convrtr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	protected TextView mMottoTextView,mSlideToStartTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMottoTextView = (TextView)findViewById(R.id.motto_text_view);
		mSlideToStartTextView = (TextView)findViewById(R.id.swipe_to_start_text_view);
		//setFont(mMottoTextView, "STHeiti Light.ttc");
		//setFont(mSlideToStartTextView,"STHeiti Light.ttc");
		mMottoTextView.setTextColor(getResources().getColor(R.color.motto_text_color));
		mMottoTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new PerformUnitCalculation().execute();
			}
		});
	}
	
	private void setFont(TextView v, String fontname){
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/" + fontname); 
		v.setTypeface(type);
	}
	
	private class PerformUnitCalculation extends AsyncTask<URL, Integer, Long> {
		
		InputStream is = null;
		
	     protected Long doInBackground(URL... urls) {
	    	 
	    	 HttpClient httpclient = new DefaultHttpClient();
	    	 HttpResponse response = null;
	    	 
	    	 
	    	 HttpPost httppost = new HttpPost("https://neutrinoapi.com/convert?");
	    	 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	    	 nameValuePairs.add(new BasicNameValuePair("from-value", "10"));
	    	 nameValuePairs.add(new BasicNameValuePair("from-type", "NZD"));
	    	 nameValuePairs.add(new BasicNameValuePair("to-type", "GBP"));
	    	 nameValuePairs.add(new BasicNameValuePair("user-id", "harid001"));
	    	 nameValuePairs.add(new BasicNameValuePair("api-key", "igJM9DYk9RzAXZ7miX8ECrH7sAxlKN9i"));  
	    	 
	    	 try {
	    		 httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    		 // Execute HTTP Post Request
	    		 response = httpclient.execute(httppost);
	    		 is = response.getEntity().getContent();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	    	return response.getEntity().getContentLength();
	    	 
	     }

	     protected void onProgressUpdate(Integer... progress) {
	         
	     }

	     protected void onPostExecute(Long result) {
	    	 
	    	String line = "";
		    StringBuilder total = new StringBuilder();
		    
		    // Wrap a BufferedReader around the InputStream
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	
		    // Read response until the end
		    try {
				while ((line = rd.readLine()) != null) { 
				    total.append(line); 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		    try {
				JSONObject jObject = new JSONObject(total.toString());
				Double answer = jObject.getDouble("result");
				Log.d("answer",answer.toString());
				
			} catch (JSONException e) {
				e.printStackTrace();
			}

	     }
	 }
	 
}
