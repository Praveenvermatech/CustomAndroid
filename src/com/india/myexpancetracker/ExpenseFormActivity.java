package com.india.myexpancetracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.india.beanclass.ItemInfo;
import com.india.dbhandler.DbFillFormController;

public class ExpenseFormActivity extends Activity {

	public EditText itemName;
	public Spinner category;
	public EditText quantity;
	public EditText price;
	TextView totalPrice;
	ItemInfo itemInfo;
	DbFillFormController db;
	Cursor cursor;
	Calendar cal;
	String modifiedDate,selectCategory;
	Date date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_form);
		
	}

		@Override
		protected void onResume() {
			super.onResume();
			itemName=(EditText) findViewById(R.id.EditTextItemName);
			category=(Spinner) findViewById(R.id.itemCategory);
			quantity=(EditText) findViewById(R.id.EditTextQuantity);
			price=(EditText) findViewById(R.id.EditTextPrice);
			totalPrice=(TextView)findViewById(R.id.textViewTotalPrice);
			 date = new Date();
			 modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			System.out.println("DATE :"+modifiedDate);
			category.setOnItemSelectedListener(new OnItemSelectedListener() {

		        @Override
		        public void onItemSelected(AdapterView<?> parent,
		                View view, int pos, long id) {
		        	selectCategory = parent.getItemAtPosition(pos).toString();
		        }

		        @Override
		        public void onNothingSelected(AdapterView<?> arg0) {
		            // TODO Auto-generated method stub

		        }
		    });
	}
		
		public void submit(View view) {
			
			itemInfo=new ItemInfo(itemName.getText().toString(), selectCategory, quantity.getText().toString(),price.getText().toString(),modifiedDate);
			
			float total=(Integer.parseInt(quantity.getText().toString()) * (Float.parseFloat(quantity.getText().toString())));
			totalPrice.setText(String.valueOf(total));
			db.open();
			long id=db.insert(itemInfo.getItemName(), itemInfo.getCategory(), itemInfo.getQuantity(), itemInfo.getPrice(),modifiedDate);
			db.close();
			System.out.println(itemInfo.toString()+" id :"+id);
			db.open();
			cursor=db.getOneRecord(1);
			
			db.display(cursor);
			db.close();
			/*if (cursor.getString(1).equals("Praveen")) {
				Intent expenseForm=new Intent(getApplicationContext(), ExpenseFormActivity.class);
				startActivity(expenseForm);
			}*/
			
			//Toast.makeText(getApplicationContext(), "submit button !!"+id, Toast.LENGTH_LONG).show();
			
		}
	
}
