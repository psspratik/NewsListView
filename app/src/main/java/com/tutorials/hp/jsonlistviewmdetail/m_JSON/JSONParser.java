package com.tutorials.hp.jsonlistviewmdetail.m_JSON;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


import com.tutorials.hp.jsonlistviewmdetail.m_Model.User;
import com.tutorials.hp.jsonlistviewmdetail.m_UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Oclemy on 7/7/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 * -----------------    ROLES -----------------------
 * 1.RECEIVE DOWNLOADED DATA
 * 2.PARSE IT
 * 3.CALL ADAPTER CLASS TO BIND IT TO CUSTOM gridview
 */
public class JSONParser extends AsyncTask<Void,Void,Boolean>{

    Context c;
    String jsonData;
    ListView lv;

    ProgressDialog pd;
    ArrayList<User> users=new ArrayList<>();

    public JSONParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        pd.dismiss();
        if(isParsed)
        {
            //BIND
            lv.setAdapter(new CustomAdapter(c,users));
        }else
        {
            Toast.makeText(c, "Unable To Parse,Check Your Log output", Toast.LENGTH_SHORT).show();
        }

    }

    private Boolean parse()
    {
        try
        {

            users.clear();
            User user;



            String image;
            JSONObject jsonObj = new JSONObject(jsonData);
            // Getting JSON Array node
            //JSONObject responseObject = jsonObj.getJSONObject("response");
            JSONArray responseArray = jsonObj.getJSONArray("posts");
            // looping through All Contacts
            for (int i = 0; i < responseArray.length(); i++) {


                JSONObject firstObject = responseArray.getJSONObject(i);
                //JSONObject titleObject = firstObject.getJSONObject("title");
                // Extract out the title, time, and tsunami values
                JSONObject threadObject = firstObject.getJSONObject("thread");
                //if(threadObject.has("main_image"))
                image = threadObject.getString("main_image");

                //else {
                //  if()
                //image = "https://us.123rf.com/450wm/uasumy/uasumy1504/uasumy150400022/38624301-green-letter-g-and-leaf-eco-technology-logo-mockup-ecology-poster.jpg?ver=6";
                //}
                String title = firstObject.getString("title");
                //if(image==null)
                //String image = "https://us.123rf.com/450wm/uasumy/uasumy1504/uasumy150400022/38624301-green-letter-g-and-leaf-eco-technology-logo-mockup-ecology-poster.jpg?ver=6";
                // tmp hash map for single contact
                //HashMap<String , String> contact = new HashMap<>();

                // adding each child node to HashMap key => value
                //contact.put("pic", image);
                //contact.put("name", title);

                // adding contact to contact list
                //arrayList.add(new NewsElements(title,image));
                String text = firstObject.getString("text");
                user=new User();
                user.setHeadline(title);
                user.setImage(image);
                user.setArticle(text);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }


}



















