package com.a7psychopaths.skaitliukoskeneris;

import android.graphics.Camera;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
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
import org.opencv.imgproc.Imgproc;

import java.lang.reflect.Method;

public class Main7Activity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{
    private static final String TAG="Main7Activity";
    JavaCameraView javaCameraView;
    int sl;
    String digit = "Digit";
    TextView number;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //TextView myTextView =
             //       (TextView)findViewById(R.id.textView5);
            number.setText(digit);
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
        setContentView(R.layout.activity_main7);

        javaCameraView = (JavaCameraView)findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
        number = findViewById(R.id.textView5);


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
        mRgba = new Mat(height, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }


    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        Point pt1 = new Point(300,200);
        Point pt2 = new Point(1000,300);
        Core.rectangle(mRgba, pt1, pt2, new Scalar(250, 0, 0, 0), 5);

        Rect roi = new Rect(300, 200, 700, 100);
        Mat cropped = new Mat(mRgba, roi);
        //Recognition.getDigits(mRgba.getNativeObjAddr(), "lol");
        //if(sl == 50) {
        //Log.d(TAG, String.valueOf(Recognition.getDigits(cropped.getNativeObjAddr(), "l")));
        //number.setText(String.valueOf(Recognition.getDigits(cropped.getNativeObjAddr(), "l")));
        digit = String.valueOf(Recognition.getDigits(cropped.getNativeObjAddr(), "l"));
        if(sl == 50) {
            handler.sendEmptyMessage(0);
            sl=0;
        }
        sl++;


        //    sl=0;
        //}
        //sl++;
        return mRgba;

    }

}
