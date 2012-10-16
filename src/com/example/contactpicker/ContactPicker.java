package com.example.contactpicker;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Contacts.People;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
//import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ContactPicker extends Activity {

    @Override
    public void onCreate(Bundle icircle) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact_picker);
    	
    	super.onCreate(icircle);
    	setContentView(R.layout.activity_contact_picker);
    	
    	Intent intent = getIntent();
    	String dataPath = intent.getData().toString();
    	
    	final Uri data = Uri.parse(dataPath + "people/");
    	final Cursor c = managedQuery(data, null, null, null, null);
    	
    	String[] from = new String[] {People.NAME};
    	int[] to = new int[] {R.id.itemTextView};
    	
    	SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listitemlayout, c, from, to);
    	
    	ListView lv = (ListView)findViewById(R.id.contactListView);
    	lv.setAdapter(adapter);
    	
    	lv.setOnItemClickListener(new OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
    			c.moveToPosition(pos);
    			int rowId = c.getInt(c.getColumnIndexOrThrow("_id"));
    			Uri outURI = Uri.parse(data.toString() + rowId);
    			Intent outData = new Intent();
    			outData.setData(outURI);
    			setResult(Activity.RESULT_OK, outData);
    			finish();
    		}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_contact_picker, menu);
        return true;
    }

    
}
