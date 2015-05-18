package com.india.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.india.myexpancetracker.ItemFormInformation;
import com.india.myexpancetracker.StartActivity;

public class DbFillFormController implements ItemFormInformation {

	private static final int database_VERSION = 1;

	StartActivity startActivity = new StartActivity();

	private static final String database_NAME = "ITEMDB";

	private static final String database_TABLE = "ITEMINFO";

	private static final String item_id = "id";

	private static final String item_name = "itemName";

	private static final String item_category = "category";
	private static final String item_quantity = "quantity";
	private static final String item_price = "salary";
	private static final String item_dot="dot";
	private final Context context;
	private static DatabaseHelper dbheplper;
	private static SQLiteDatabase db;

	private static final String[] COLUMNS = { item_id, item_name, item_category,
		item_quantity, item_price,item_dot };

	public DbFillFormController(Context context) {

		this.context = context;
		dbheplper = new DatabaseHelper(context);

	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, database_NAME, null, database_VERSION);

			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			String CREATE_ITEM_TABLE = "CREATE TABLE ITEMINFO ( "
					+ "itemId INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "itemName VARCHAR, " + "category VARCHAR, " + "quantity VARCHAR, "
					+ "price VARCHAR, "
					+ "dot VARCHAR )";
			db.execSQL(CREATE_ITEM_TABLE);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS ITEMINFO");
			this.onCreate(db);

		}

	}

	public DbFillFormController open() throws SQLException {

		db = dbheplper.getWritableDatabase();

		return this;
	}

	public void close() {

		dbheplper.close();
	}

	@Override
	public long insert(String itemName,String category,String quantity,String price,String dot) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(item_name, itemName);
		initialValues.put(item_category, category);
		initialValues.put(item_quantity, quantity);
		initialValues.put(item_price, price);
		initialValues.put(item_dot, dot);
		return db.insert(database_TABLE, null, initialValues);
	}

	@Override
	public boolean edit(Integer itemId, String itemName,String category,String quantity,String price,String dot) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(item_name, itemName);
		initialValues.put(item_category, category);
		initialValues.put(item_quantity, quantity);
		initialValues.put(item_price, price);
		initialValues.put(item_dot, dot);
		return db.update(database_TABLE, initialValues, item_id + "=" + itemId,
				null) > 0;
	}
	public Cursor getAllrecords(){
		
		
		return db.query(database_TABLE, new String[] {item_id,item_name, item_category,
				item_quantity, item_price,item_dot }, null, null, null, null, null, null);
	}
	
	public Cursor getOneRecord(Integer id){
		
		Cursor myCursor=db.query(true, database_TABLE, new String[] {item_id,item_name, item_category,
				item_quantity, item_price,item_dot}, item_id+"="+id, null, null, null, null, null);
		if(myCursor!=null){
			myCursor.moveToFirst();
		}
		return myCursor;
	}

	@Override
	public void display(Cursor cursor) {
		
		System.out.println("ID:"+cursor.getShort(0)+"ITEM Name :"+cursor.getString(1));
		
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
