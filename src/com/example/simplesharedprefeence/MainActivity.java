package com.example.simplesharedprefeence;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	//Defining Buton and Edittext
	EditText username_et,password_et;
	Button save_b,load_b;
	
	//Default Value 
	public static final String DEFAULT="N/A";
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Refering username and password from xml which as id as id_username and id_password  
        username_et=(EditText)findViewById(R.id.id_username);
        password_et=(EditText)findViewById(R.id.id_password);
      //Refering save and next button from xml which as id as id_save & id_next
        save_b=(Button)findViewById(R.id.id_save);
        load_b=(Button)findViewById(R.id.id_next);
        //To Save Data i.e, username and password in Shared Preferences
        save_b.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				//Getting reference from Shared preference
				//File name should not contain any extension as the it will atomatically save in XML format
				//MODE_PRIVATE is used give access only to the app
				SharedPreferences sp=getSharedPreferences("MySavedData",Context.MODE_PRIVATE);
				//Editor object give access to edit the shared preference file
				//Editing the shared preference file is achieved by accessing the shared preference object sp
				SharedPreferences.Editor et=sp.edit(); 
				//The first parameter is the key and the next parameter is the value we want to stor in the key and the value is pushed from 
				//Edit text username_et
				et.putString("key_username",username_et.getText().toString());
				et.putString("key_password",password_et.getText().toString());
				//After it is the responsibilty to commit it , if not Data will not be saved in Shared Preferences
				et.commit();
				
			}
		});
        //To Goto Next Activity
        load_b.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				SharedPreferences sp=getSharedPreferences("MySavedData",Context.MODE_PRIVATE);
				//The 2nd parameter is the default value it is saved by default if no value is present or given by the user
				String name=sp.getString("key_username","DEFAULT");
				String pass=sp.getString("key_password","DEFAULT");
				//If no Saved Data in shared Preference
				if(name.equals(DEFAULT)||pass.equals(DEFAULT))
				{
					Toast.makeText(MainActivity.this,"NO Saved Data in Shared preference",Toast.LENGTH_LONG).show();
					
				}
				//If Data is found
				else
				{
					Toast.makeText(MainActivity.this,"Data is loaded from Shared preference"+'\n'+"Username is : "+name+'\n'+"Password is : "+pass,Toast.LENGTH_LONG).show();
				}
			}
		});
        
        
    }

    
}
