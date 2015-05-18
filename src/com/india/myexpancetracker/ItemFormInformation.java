package com.india.myexpancetracker;

import android.database.Cursor;

public interface ItemFormInformation {

	public void delete();
	public long insert(String itemName,String category,String quantity,String price,String dot);
	public boolean edit(Integer itemId, String itemName,String category,String quantity,String price,String dot);
	public void display(Cursor cursor);
}
