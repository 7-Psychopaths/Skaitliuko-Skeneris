package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Point;

import java.util.Calendar;

public class CameraWindow extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{
    private static final String TAG="CameraWindow";
    JavaCameraView javaCameraView;
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SkaitliukoSkeneris"; // path to storage
    final BackgroundWorker backgroundWorker = new BackgroundWorker(this);
    int sl; // used to count frames
    String digit = "Digit"; // This will return every scanned number of every frame of the camera
    // we cant return the digit value to the screen because the user wouldn't be able to follow its speed
    // so we need a second variable "digitView2" which will return the 40th frame of the "digit" variable
    String digitView2 = "Number"; // number that will be outputted to screen
    TextView number;
    static String type =""; // meter type

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            number.setText(digitView2); // output number to screen
        }
    };

    Mat mRgba;
    BaseLoaderCallback mLoaderCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case BaseLoaderCallback.SUCCESS:{
                    javaCameraView.enableView();
                    break;
                }
                default:{
                    super.onManagerConnected(status);
                    break;
                }
            }

        }
    };

    static {
        System.loadLibrary("MyOpencvLibs");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_window);

        javaCameraView = (JavaCameraView)findViewById(R.id.java_camera_view); // creating a camera instance
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
        number = findViewById(R.id.textView5);

        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, MainWindow.class);

        Log.d(TAG, path);

        final Button saveButton = findViewById(R.id.button10); // Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {  // Once the user presses save, the number will be saved into the database
                                            // And the user will be reverted back to the main window

                Calendar c = Calendar.getInstance();
                final int year = c.get(Calendar.YEAR);
                final int month = c.get(Calendar.MONTH)+1;
                String date = year + "-" + month;

                backgroundWorker.execute(type, digitView2, date, MainWindow.id(getApplicationContext())); // save to database

                saveButton.setEnabled(false);
                saveButton.postDelayed(new Runnable() {
                    @Override
                    public void run() { // This method is used to achieve a 2 second delay to avoid application crashes
                        saveButton.setEnabled(true);
                        startActivity(intent);
                        CameraWindow.this.finish();
                    }
                }, 2000);
            }

        });
        final Button exitButton = findViewById(R.id.button11); // This button lets the user go back to the main window
        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
                CameraWindow.this.finish();
            }

        });

    }

    @Override
    protected void onPause(){
        super.onPause();
        if(javaCameraView!=null)
            javaCameraView.disableView();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(javaCameraView!=null)
            javaCameraView.disableView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCV successfully loaded");
            mLoaderCallBack.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else {
            Log.d(TAG, "OpenCV not loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, this, mLoaderCallBack);
        }
    }
    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4); // window frame
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }


    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        Point pt1 = new Point(290,190);
        Point pt2 = new Point(1010,310);
        Core.rectangle(mRgba, pt1, pt2, new Scalar(250, 0, 0, 0), 5); // we draw a rectangular red box in which the user
                                                                                            // shows which numbers he wants to be recognized

        Rect roi = new Rect(300, 200, 700, 100); // we crop out the frame inside the red box
        Mat cropped = new Mat(mRgba, roi);
        digit = String.valueOf(Recognition.getDigits(cropped.getNativeObjAddr(), path)); // we pass the cropped out frame to our
                                                                                        // native c++ code, where our recognition code lies
                                                                                        // and in return get the recognized digits
        if(sl == 40) { // we output the 40th frame digit of our camera and pass it to the screen
            digitView2 = digit;
            handler.sendEmptyMessage(0);
            sl=0;
        }
        sl++;
        return mRgba; // return the frame with green rectangles drawn over our digits
    }

}
