package com.india.myexpancetracker;

import android.R.integer;
import android.database.Cursor;

public interface FormInformation {

	public void delete();
	public long insert(String name,String mobile,String email,String salary);
	public boolean edit(Integer id, String name, String mobile, String email,String salary);
	public void display(Cursor cursor);
	
}
