package com.india.myexpancetracker;

import com.india.beanclass.UserInfo;
import com.india.dbhandler.DbController;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity  {

	public EditText name;
	public EditText mobile;
	public EditText email;
	public EditText salary;
	UserInfo userInfo;
	DbController db;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		
		db=new DbController(this);
		/*db.open();
		cursor=db.getOneRecord(1);
		
		db.display(cursor);
		db.close();
		if (cursor.getString(1).equals("praveen")) {
			Intent expenseForm=new Intent(getApplicationContext(), ExpenseFormActivity.class);
			startActivity(expenseForm);
		}*/
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		name=(EditText) findViewById(R.id.EditTextName);
		mobile=(EditText) findViewById(R.id.EditTextMobile);
		email=(EditText) findViewById(R.id.EditTextEmail);
		salary=(EditText) findViewById(R.id.EditTextSalary);
		
	}
	

	public void submit(View view) {
		
		userInfo=new UserInfo(name.getText().toString(), mobile.getText().toString(), email.getText().toString(), salary.getText().toString());
		
		/*db.open();
		long id=db.insert(userInfo.getName(), userInfo.getMobileNumber(), userInfo.getEmail(), userInfo.getSalary());
		db.close();
		System.out.println(userInfo.toString()+" id :"+id);*/
		db.open();
		cursor=db.getOneRecord(1);
		
		db.display(cursor);
		db.close();
		if (cursor.getString(1).equals("Praveen")) {
			Intent expenseForm=new Intent(getApplicationContext(), ExpenseFormActivity.class);
			startActivity(expenseForm);
		}
		
		//Toast.makeText(getApplicationContext(), "submit button !!"+id, Toast.LENGTH_LONG).show();
		
	}
}
