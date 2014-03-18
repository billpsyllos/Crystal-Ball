package com.example.androiddemoproject;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddemoproject.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	
	public static final String TAG = MainActivity.class.getName();

	private CrystalBall mCrustalBall = new CrystalBall();
	private TextView mAnsewrLabel;
	private Button mGetAnswerButton;
	private ImageView mCrystalBallImage;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		//Assign  our view variables
		mAnsewrLabel = (TextView) findViewById(R.id.textView1);
		mGetAnswerButton = (Button) findViewById(R.id.button1);
		mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		mShakeDetector = new ShakeDetector(new OnShakeListener() {
			
			@Override
			public void onShake() {
				handleAnswer();
				
			}
		});
	
		mGetAnswerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {			
				handleAnswer();
			}			
		});
		
		//String toastMessage = "Yay! Oyr activity is work";
		//Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
		
		/*Toast welcomeToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
		welcomeToast.setGravity(Gravity.CENTER, 0, 0);
		welcomeToast.show();
		*/
		
		//Log.d(TAG, "We are logging from the onCreate() method!");
	
		
	}
	
	private void animateCrystallBall(){
	    
		mCrystalBallImage.setImageResource(R.drawable.ball_animation);
		AnimationDrawable ballAnimation = (AnimationDrawable) mCrystalBallImage.getDrawable();
		
		if(ballAnimation.isRunning()){
			ballAnimation.stop();
		}
		
		ballAnimation.start();
	}
	@Override
	public void onResume() {
		super.onResume();
		mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		mSensorManager.unregisterListener(mShakeDetector);
	}
	private void animateAnswer(){
		AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
		fadeInAnimation.setDuration(1500);
		fadeInAnimation.setFillAfter(true);
		
		mAnsewrLabel.setAnimation(fadeInAnimation);
	}
	private void playSound(){
		MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
		player.start();
		player.setOnCompletionListener(new OnCompletionListener() {			
			@Override
			public void onCompletion(MediaPlayer mp) {				
				mp.release();				
			}
		});
	}
	private void handleAnswer() {
		String answer = mCrustalBall.getAnAnswer();
		
		//Update the label with our dynamic answer
		mAnsewrLabel.setText(answer);
		
		animateCrystallBall();
		animateAnswer();
		playSound();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
