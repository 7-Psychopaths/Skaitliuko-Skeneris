package com.a7psychopaths.skaitliukoskeneris;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    /*!
    \fn doInBackground
    \param String params
    */
    protected String doInBackground(String... params) {
        String type = params[0];
        String dujos_url = "http://milvada.lt/procesas/adddujos.php";
        String elektra_url = "http://milvada.lt/procesas/addelektra.php";
        String vanduo_url = "http://milvada.lt/procesas/addvanduo.php";
        String user_url = "http://milvada.lt/procesas/adduser.php";
        String dujos2_url = "http://milvada.lt/procesas/updateDujos.php";
        String vanduo2_url = "http://milvada.lt/procesas/updateVanduo.php";
        String elektra2_url = "http://milvada.lt/procesas/updateElektra.php";

        if(type.equals("dujos")) {
            try {
                String value = params[1];
                String date = params[2];
                String userId = params[3];
                URL url = new URL(dujos_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("userId","UTF-8")+"="+URLEncoder.encode(userId,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("elektra")) {
            try {
                String value = params[1];
                String date = params[2];
                String userId = params[3];
                URL url = new URL(elektra_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("userId","UTF-8")+"="+URLEncoder.encode(userId,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("vanduo")) {
            try {
                String value = params[1];
                String date = params[2];
                String userId = params[3];
                URL url = new URL(vanduo_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("userId","UTF-8")+"="+URLEncoder.encode(userId,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("dujos2")) {
            try {
                String value = params[1];
                String date = params[2];
                String userId = params[3];
                URL url = new URL(dujos2_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("userId","UTF-8")+"="+URLEncoder.encode(userId,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("vanduo2")) {
            try {
                String value = params[1];
                String date = params[2];
                String userId = params[3];
                URL url = new URL(vanduo2_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("userId","UTF-8")+"="+URLEncoder.encode(userId,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("elektra2")) {
            try {
                String value = params[1];
                String date = params[2];
                String userId = params[3];
                URL url = new URL(elektra2_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("userId","UTF-8")+"="+URLEncoder.encode(userId,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Success");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
