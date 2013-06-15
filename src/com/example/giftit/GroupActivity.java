package com.example.giftit;


import com.classes.giftit.Group;
import com.database.giftit.data.GroupDataSource;
import com.interfaces.giftit.StringConstants;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GroupActivity extends Activity implements StringConstants{

	private GroupDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		dataSource = new GroupDataSource(this);
		dataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			
	// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.main, menu);
			
		return true;
	}

	/**
	* onOptionsItemSelected - get the selected MenuItem and starts the chosen Activity 
	* 
	* @param MenuItem item 
	* @return bool
	* 
	*/
		
	public boolean onOptionsItemSelected(MenuItem item)	
	{		
		switch (item.getItemId()){
			case R.id.contact:
					Intent contactIntent = new Intent(this, ContactCardActivity.class);
					startActivity(contactIntent);
					return true;
			case R.id.group:
					Intent groupIntent = new Intent(this, GroupActivity.class);
					startActivity(groupIntent);
					return true;
			case R.id.gift:
					// TODO Implementing Intent to GiftActivity
					return true;
			case R.id.event:
					// TODO Implementing Intent to EventActivity
					return true;			
			case R.id.action_settings:					
					Intent actionSettingsIntent = new Intent(this, SetupActivity.class);
					startActivity(actionSettingsIntent);
					return true;
			default:
					return super.onOptionsItemSelected(item);
			}
	}
	
	public void saveGroupClick(View view){
		EditText tfDescription = (EditText)findViewById(R.id.tfDescription);
		
		Group group = new Group();
		
		group.setDescription(tfDescription.getText().toString());
		
		try{
			dataSource.createGroup(group);
			Toast.makeText(this, GROUP_SAVED, Toast.LENGTH_SHORT);
		}catch(SQLiteException e){
			AlertDialog ad = new AlertDialog.Builder(this).create();  
		    ad.setCancelable(false); // This blocks the 'BACK' button  
		    ad.setMessage(e.getMessage());  
		    ad.setButton(1, "OK", new DialogInterface.OnClickListener() {  
		        @Override  
		        public void onClick(DialogInterface dialog, int which) {  
		            dialog.dismiss();                      
		        }  
		    }); 
		    ad.show();
		}finally {
			dataSource.close();
		}
	}

}
