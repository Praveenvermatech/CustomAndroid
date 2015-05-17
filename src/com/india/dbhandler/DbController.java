package com.india.dbhandler;

import com.india.myexpancetracker.FormInformation;
import com.india.myexpancetracker.StartActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbController implements FormInformation {

	private static final int database_VERSION = 1;

	StartActivity startActivity = new StartActivity();

	private static final String database_NAME = "USERDB";

	private static final String database_TABLE = "USER";

	private static final String user_id = "id";

	private static final String user_name = "name";

	private static final String user_mobile = "mobile";
	private static final String user_email = "email";
	private static final String user_salary = "salary";
	private final Context context;
	private static DatabaseHelper dbheplper;
	private static SQLiteDatabase db;

	private static final String[] COLUMNS = { user_id, user_name, user_mobile,
			user_email, user_salary };

	public DbController(Context context) {

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

			String CREATE_USER_TABLE = "CREATE TABLE USER ( "
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name VARCHAR, " + "mobile VARCHAR, " + "email VARCHAR, "
					+ "salary VARCHAR )";
			db.execSQL(CREATE_USER_TABLE);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS USER");
			this.onCreate(db);

		}

	}

	public DbController open() throws SQLException {

		db = dbheplper.getWritableDatabase();

		return this;
	}

	public void close() {

		dbheplper.close();
	}

	@Override
	public long insert(String name, String mobile, String email, String salary) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(user_name, name);
		initialValues.put(user_mobile, mobile);
		initialValues.put(user_email, email);
		initialValues.put(user_salary, salary);
		return db.insert(database_TABLE, null, initialValues);
	}

	@Override
	public boolean edit(Integer id, String name, String mobile, String email,
			String salary) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(user_name, name);
		initialValues.put(user_mobile, mobile);
		initialValues.put(user_email, email);
		initialValues.put(user_salary, salary);
		return db.update(database_TABLE, initialValues, user_id + "=" + id,
				null) > 0;
	}
	public Cursor getAllrecords(){
		
		
		return db.query(database_TABLE, new String[] {user_id,user_name,user_mobile,user_email,user_salary }, null, null, null, null, null, null);
	}
	
	public Cursor getOneRecord(Integer id){
		
		Cursor myCursor=db.query(true, database_TABLE, new String[] {user_id,user_name,user_mobile,user_email,user_salary }, user_id+"="+id, null, null, null, null, null);
		if(myCursor!=null){
			myCursor.moveToFirst();
		}
		return myCursor;
	}

	@Override
	public void display(Cursor cursor) {
		
		System.out.println("ID:"+cursor.getShort(0)+"Name :"+cursor.getString(1));
		
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
