package com.a7psychopaths.skaitliukoskeneris;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;

    private ArrayList<String> skaitliukai=new ArrayList<>();
    static ArrayList<String> AllSkaitliukai=new ArrayList<>();
    ProgressDialog pd;

    Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.data = data;
        this.lv = lv;
    }

    public Parser(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing ....Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {

        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1)
        {
            //ADAPTER
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,skaitliukai);

            //ADAPT TO LISTVIEW
            lv.setAdapter(adapter);

            //LISTENET
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Snackbar.make(view,skaitliukai.get(position),Snackbar.LENGTH_SHORT).show();;
                }
            });

        }else
        {
            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }

    //PARSE RECEIVED DATA
    private int parse()
    {
        try
        {
            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja=new JSONArray(data);

            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo=null;

            skaitliukai.clear();
            AllSkaitliukai.clear();

//            Main4Activity main4Activity = new Main4Activity();

            //LOOP THRU ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                String Date = jo.getString("Date");
                String Dujos = "Dujos: " + jo.getString("Dujos");
                String Vanduo = "Vanduo: " + jo.getString("Vanduo");
                String Elektra = "Elektra: " + jo.getString("Elektra");


                switch (Filter.getDataType()) {
                    case "Dujos":
                        skaitliukai.add(Date);
                        skaitliukai.add(Dujos);
                        break;
                    case "Elektra":
                        skaitliukai.add(Date);
                        skaitliukai.add(Elektra);
                        break;
                    case "Vanduo":
                        skaitliukai.add(Date);
                        skaitliukai.add(Vanduo);
                        break;
                    default:
                        //ADD IT TO OUR ARRAYLIST
                        skaitliukai.add(Date);
                        skaitliukai.add(Dujos);
                        skaitliukai.add(Vanduo);
                        skaitliukai.add(Elektra);
                        skaitliukai.add("");
                        break;
                }

                AllSkaitliukai.add(Date);
                AllSkaitliukai.add(Dujos);
                AllSkaitliukai.add(Vanduo);
                AllSkaitliukai.add(Elektra);
                AllSkaitliukai.add("");
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
    }
