package com.tutorials.hp.jsonlistviewmdetail.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tutorials.hp.jsonlistviewmdetail.DetailActivity;
import com.tutorials.hp.jsonlistviewmdetail.R;
import com.tutorials.hp.jsonlistviewmdetail.m_Model.User;

import java.util.ArrayList;

/**
 * Created by Oclemy on 7/15/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class CustomAdapter  extends BaseAdapter{

    Context c;
    ArrayList<User> users;

    public CustomAdapter(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            view=LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        TextView nameTxt= (TextView) view.findViewById(R.id.nameTxt);
        TextView emailTxt= (TextView) view.findViewById(R.id.emailTxt);
        TextView usernameTxt= (TextView) view.findViewById(R.id.usernameTxt);

        User user= (User) this.getItem(i);

        final String headline=user.getHeadline();
        final String image=user.getImage();
        final String article=user.getArticle();


        nameTxt.setText(headline);
        emailTxt.setText(image);
        usernameTxt.setText(article);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OPEN DETAIL ACTIVITY
               openDetailActivity(headline,image,article);

            }
        });
        return view;
    }
    ////open activity
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c,DetailActivity.class);
        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("EMAIL_KEY",details[1]);
        i.putExtra("USERNAME_KEY",details[2]);

        c.startActivity(i);

    }

}













