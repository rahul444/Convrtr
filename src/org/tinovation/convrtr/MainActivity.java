package org.tinovation.convrtr;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	protected TextView mMottoTextView,mSlideToStartTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMottoTextView = (TextView)findViewById(R.id.motto_text_view);
		mSlideToStartTextView = (TextView)findViewById(R.id.swipe_to_start_text_view);
		setFont(mMottoTextView, "STHeiti Light.ttc");
		setFont(mSlideToStartTextView,"STHeiti Light.ttc");
		mMottoTextView.setTextColor(getResources().getColor(R.color.motto_text_color));
	}
	
	
	private void setFont(TextView v, String fontname){
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/" + fontname); 
		v.setTypeface(type);
	}

}
