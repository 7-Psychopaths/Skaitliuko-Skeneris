package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.StrictMode;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class Main6Activity extends AppCompatActivity {

    ImageView imageView;
    Button buttonCamera, buttonGallery ;
    File file;
    Uri uri;
    Intent CamIntent, GalIntent, CropIntent ;
    public  static final int RequestPermissionCode  = 1 ;
    DisplayMetrics displayMetrics ;
    int width, height;
    private static final String TAG="Main6Activity";
    //static String type ="";

    final BackgroundWorker backgroundWorker = new BackgroundWorker(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        imageView = (ImageView)findViewById(R.id.imageview);
        buttonCamera = (Button)findViewById(R.id.button2);
        buttonGallery = (Button)findViewById(R.id.button1);




        EnableRuntimePermission();

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClickImageFromCamera() ;

            }
        });

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetImageFromGallery();

            }
        });

    }

    public void ClickImageFromCamera() {

        CamIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        file = new File(Environment.getExternalStorageDirectory(),
                "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        uri = Uri.fromFile(file);

        CamIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);

        CamIntent.putExtra("return-data", true);

        startActivityForResult(CamIntent, 0);

    }

    public void GetImageFromGallery(){

        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {

            ImageCropFunction();

        }
        else if (requestCode == 2) {

            if (data != null) {

                uri = data.getData();

                ImageCropFunction();

            }
        }
        else if (requestCode == 1) {

            if (data != null) {

                Bundle bundle = data.getExtras();

                Bitmap bitmap = bundle.getParcelable("data");

                imageView.setImageBitmap(bitmap);

                /////////////////////////////////////
                // Isaugom bitmap i sd
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SkaitliukoSkeneris";
                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                InputStream in = null;
                OutputStream out = null;
                try{
                    ByteArrayOutputStream stre = new ByteArrayOutputStream();

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stre);
                    byte[] byteArray = stre.toByteArray();
                    InputStream myInputStream = new ByteArrayInputStream(byteArray);
                    in = myInputStream;
                    File outFile = new File(path, "dujos.png");
                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                } finally {
                    if(in != null){
                        try{
                            in.close();
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(out != null){
                        try{
                            out.close();
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
                Mat mRgba = new Mat(100, 100, CvType.CV_8UC4);

                Log.d(TAG, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr(), path)));
                /////////////////////////////////////

                Calendar c = Calendar.getInstance();
                final int year = c.get(Calendar.YEAR);
                final int month = c.get(Calendar.MONTH)+1;
                String date = year + "-" + month;

                final Context context =  getApplicationContext();
                final Intent intent = new Intent(context, MainActivity.class);
                //backgroundWorker.execute(type, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr(), path)), date, MainActivity.id(getApplicationContext()));
                Main6Activity.this.finish();
                startActivity(intent);
                ////////////////////////////////////////
                // i SD card issaugo siuos failus
                copyAsset("classifications.xml");
                copyAsset("images.xml");
                ////////////////////////////////////////


                mRgba.release();


            }
        }
    }

    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");

            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
//            CropIntent.putExtra("outputX", 60*0.8);
//            CropIntent.putExtra("outputY", 60*3);
//            CropIntent.putExtra("aspectX", 3);
//            CropIntent.putExtra("aspectY", 0.8);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }
    //Image Crop Code End Here

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(Main6Activity.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(Main6Activity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Main6Activity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(Main6Activity.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Main6Activity.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    ////////////////////////////////////////////////////
    // tiek copyAsset, tiek copyFile metodai naudojami tam, kad issaugotu i SD card

    private  void copyAsset(String filename){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SkaitliukoSkeneris";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try{
            in = assetManager.open(filename);
            File outFile = new File(dirPath, filename);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(in != null){
                try{
                    in.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(out != null){
                try{
                    out.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }



    //////////////////////////////////////////////////////////
}
