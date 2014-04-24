// Database Helper Class
// Extends SQLiteOpenHelper to allow the use of our external databases we
// have included in the assets folder. This file also allows us to perform
// manipulations on the various tables within the database.
// NOTE: There are many tables and the various operations, such as insertion
// and deletion, are similar code-wise. For the sake of not being potentially
// almost 1000 lines long, I have only included insertion, deletion, select 1, 
// and select all for the User Ingredients table.
// Created: 3/29/14
// Author: Joseph Young

package com.example.pantrypanacea;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static String DB_PATH = "/data/data/com.example.pantrypanacea/databases/";
	private static String DB_NAME = "PantryPanacea.db";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
 
	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	 * @param context
	 **/
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}	
 
	/**
	 * Creates a empty database on the system and rewrites it with imported database
	 **/
	public void createDataBase() throws IOException{
		if(!checkDataBase()){
			this.getReadableDatabase();
			try {
				copyDataBase();
			}
			catch (IOException e) { 
				throw new Error("Error copying database");
			}		
		}
	}
 
	/**
	 * Check if the database already exist to avoid re-copying the file
	 * @return true if it exists, false if it doesn't
	 **/
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
		}
		catch(SQLiteException e){
			//database does't exist 
		}
		if(checkDB != null) 
			checkDB.close(); 
		return checkDB != null;
	}
 
	/**
	 * Copies database from assets folder to the created empty database in the
	 * system folder.
	 * */
	private void copyDataBase() throws IOException{ 
		//Open db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME); 
		// Path to the created empty db
		String outFileName = DB_PATH + DB_NAME; 
		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName); 
		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0)
			myOutput.write(buffer, 0, length);
		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
 
	public void openDataBase() throws SQLException{
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
 
	@Override
	public synchronized void close() {
		if(myDataBase != null)
			myDataBase.close(); 
		super.close(); 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
	
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	/**
	 * Adds an ingredient into the user ingredient table
	 * */	
	public long createUserCustomIngredients(userCustomIngredients ingredient){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		// the various columns within each user ingredient entry
		values.put("IngredientName", ingredient.getIngredientName());
		values.put("IngredientCategory", ingredient.getIngredientCategory());
		values.put("IngredientQuantity", ingredient.getIngredientQuantity());
		values.put("IngredientBaseUnit", ingredient.getIngredientBaseUnit());
		long ingID = db.insert("userCustomIngredients", null, values);
		return ingID;
	}
	
	/**
	 * Updates the values of an already-existing user ingredient
	 * */		
	public int updateUserCustomIngredients(userCustomIngredients ingredient){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("IngredientName", ingredient.getIngredientName());
		values.put("IngredientCategory", ingredient.getIngredientCategory());
		values.put("IngredientQuantity", ingredient.getIngredientQuantity());
		values.put("IngredientBaseUnit", ingredient.getIngredientBaseUnit());
		return db.update("userCustomIngredients", values, "_id"+"=?", new String[]{String.valueOf(ingredient.get_id())});
	}
	
	public long returnLargestCustomIngId(){
		String maxId;
		String query = "SELECT MAX(_id) FROM userCustomIngredients";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst())
			maxId = c.getString(0);
		else maxId = "0";
		return Long.parseLong(maxId);
	}
	/**
	 * Selects all user ingredients into a list
	 * */		
	public List<userCustomIngredients> selectAllUserIngerdient(){
		List<userCustomIngredients> ingredients = new ArrayList<userCustomIngredients>();
		String query = "SELECT * FROM userCustomIngredients";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				userCustomIngredients ing = new userCustomIngredients();
				ing.set_id(c.getInt(c.getColumnIndex("_id")));
				ing.setIngredientName(c.getString(c.getColumnIndex("IngredientName")));
				ing.setIngredientCategory(c.getString(c.getColumnIndex("IngredientCategory")));
				ing.setIngredientQuantity(c.getString(c.getColumnIndex("IngredientQuantity")));
				ing.setIngredientBaseUnit(c.getString(c.getColumnIndex("IngredientBaseUnit")));
				ingredients.add(ing);
			} while(c.moveToNext());
		}
		return ingredients;
	}
	
	/**
	 * Selects a single user ingredients from the given id
	 * */		
	public userCustomIngredients getUserCustomIngredients(String name){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM userCustomIngredients WHERE IngredientName = \'"+name+"\'";
		Log.e("DatabaseHelper", query);
		Cursor c = db.rawQuery(query, null);
		if(c!= null)
			c.moveToFirst();
		userCustomIngredients ing = new userCustomIngredients();
		ing.set_id(c.getInt(c.getColumnIndex("_id")));
		ing.setIngredientName(c.getString(c.getColumnIndex("IngredientName")));
		ing.setIngredientCategory(c.getString(c.getColumnIndex("IngredientCategory")));
		ing.setIngredientQuantity(c.getString(c.getColumnIndex("IngredientQuantity")));
		ing.setIngredientBaseUnit(c.getString(c.getColumnIndex("IngredientBaseUnit")));
		return ing;
	}
	
	public List<String> selectIngCat(){
		List<String> ingredients = new ArrayList<String>();
		String query = "SELECT DISTINCT IngredientCategory FROM userIngredients";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				ingredients.add(c.getString(0));
			} while(c.moveToNext());
		}
		return ingredients;
	}
	
	public List<String> selectIngName(String category){
		List<String> ingredients = new ArrayList<String>();
		String query = "SELECT IngredientName FROM userIngredients WHERE IngredientCategory = \'"+category+"\'";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				ingredients.add(c.getString(0));
			} while(c.moveToNext());
		}
		return ingredients;
	}
	
	public List<String> selectIngUnit(){
		List<String> ingredients = new ArrayList<String>();
		String query = "SELECT DISTINCT IngredientBaseUnit FROM userIngredients WHERE IngredientBaseUnit != \'\'";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				ingredients.add(c.getString(0));
			} while(c.moveToNext());
		}
		return ingredients;
	}
	
	public boolean checkUsrIng(String name){
		String query = "SELECT * FROM userCustomIngredients WHERE IngredientName = \'"+name+"\'";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.getCount() <= 0)
			return false;
		return true;
	}
	
	public List<String> selectCustomIngName(){
		List<String> ingredients = new ArrayList<String>();
		String query = "SELECT IngredientName FROM userCustomIngredients";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				ingredients.add(c.getString(0));
			} while(c.moveToNext());
		}
		return ingredients;
	}

	public List<recipeInfo> recipeSearch(List<userCustomIngredients> usering) {
		List<recipeInfo> recipes = new ArrayList<recipeInfo>();
		String query = "SELECT DISTINCT recipeInfo._id, recipeInfo.RecipeName, recipeInfo.RecipeServings, recipeInfo.RecipeCategory, recipeInfo.RecipeUseCount, recipeInfo.RecipeFavorite FROM recipeInfo, recipeIngredients WHERE recipeIngredients.RecipeName = recipeInfo.RecipeName AND (";
		for(int i = 0; i < usering.size(); i++){
			query = query+"recipeIngredients.IngredientName = \'"+usering.get(i).getIngredientName()+"\'";
			if(i != (usering.size()-1))
				query = query+" OR ";
		}
		query = query+")";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				recipeInfo rec = new recipeInfo();
				rec.set_id(c.getInt(c.getColumnIndex("_id")));
				rec.setRecipeName(c.getString(c.getColumnIndex("RecipeName")));
				rec.setRecipeServings(c.getString(c.getColumnIndex("RecipeServings")));
				rec.setRecipeCategory(c.getString(c.getColumnIndex("RecipeCategory")));
				rec.setRecipeUseCount(Integer.parseInt(c.getString(c.getColumnIndex("RecipeUseCount"))));
				rec.setRecipeFavorite(Integer.parseInt(c.getString(c.getColumnIndex("RecipeFavorite"))));				
				recipes.add(rec);
			} while(c.moveToNext());
		}
		List<recipeInfo> recipiesNarrowed = ingRecSearch(recipes);
		return recipiesNarrowed;
	}

	public List<recipeInfo> ingRecSearch(List<recipeInfo> recipes){
		List<userCustomIngredients> ing = selectAllUserIngerdient();
		List<recipeIngredients> recIng = new ArrayList<recipeIngredients>();
		List<recipeInfo> result = new ArrayList<recipeInfo>();
		int found;
		for(int i = 0; i < recipes.size(); i++){
			found = 0;
			recIng = getRecIng(recipes.get(i).getRecipeName());
			for(int j = 0; j < recIng.size(); j++){
				for(int k = 0; k < ing.size(); k++){
					Fraction recQuant = new Fraction(recIng.get(j).getIngredientQuantity());
					Fraction ingQuant = new Fraction(ing.get(k).getIngredientQuantity());
					if( (recIng.get(j).getIngredientUnit().isEmpty() == false) && (ing.get(k).getIngredientBaseUnit().isEmpty() == false) &&
							(recIng.get(j).getIngredientUnit().equals(ing.get(k).getIngredientBaseUnit()) == false) &&
							(recIng.get(j).getIngredientUnit().equals("units") == false) && (ing.get(k).getIngredientBaseUnit().equals("units") == false)
							&& (recIng.get(j).getIngredientUnit().equals("cans") == false) && (ing.get(k).getIngredientBaseUnit().equals("cans") == false)){
						Fraction modifier = getMod(recIng.get(j).getIngredientUnit(), ing.get(k).getIngredientBaseUnit());
						recQuant = recQuant.multiply(modifier);
					}
					if(recIng.get(j).getIngredientName().equals(ing.get(k).getIngredientName()) && (ingQuant.subtract(recQuant).value >= 0))
						found++;
				}
			}
			if(found == recIng.size())
				result.add(recipes.get(i));

		}
		return result;
	}
	
	public Fraction getMod(String base, String convert){
		List<String> result = new ArrayList<String>();
		String query = "SELECT Multiplier FROM unitConversions WHERE OriginalUnits = \'"+base+"\' AND ConvertedUnits = \'"+convert+"\'";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				result.add(c.getString(0));
			} while(c.moveToNext());
		}
		return new Fraction(result.get(0));
	}
	public List<recipeIngredients> getRecIng(String recipe){
		List<recipeIngredients> ingredients = new ArrayList<recipeIngredients>();
		String query = "SELECT * FROM recipeIngredients WHERE RecipeName = \'"+recipe+"\'";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				recipeIngredients ing = new recipeIngredients();
				ing.set_id(c.getInt(c.getColumnIndex("_id")));
				ing.setRecipeName((c.getString(c.getColumnIndex("RecipeName"))));
				ing.setIngredientName(c.getString(c.getColumnIndex("IngredientName")));
				ing.setIngredientQuantity(c.getString(c.getColumnIndex("IngredientQuantity")));
				ing.setIngredientUnit(c.getString(c.getColumnIndex("IngredientUnit")));
				ing.setIngredientModifier(c.getString(c.getColumnIndex("IngredientModifier")));
				ingredients.add(ing);
			} while(c.moveToNext());
		}
		return ingredients;
	}
	
	public List<recipeDirections> getRecipeDir(String recSelected) {
		List<recipeDirections> dir = new ArrayList<recipeDirections>();
		String query = "SELECT * FROM recipeDirections WHERE RecipeName = \'"+recSelected+"\'";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				recipeDirections ing = new recipeDirections();
				ing.set_id(c.getInt(c.getColumnIndex("_id")));
				ing.setDirectionNumber(Integer.parseInt((c.getString(c.getColumnIndex("DirectionNumber")))));
				ing.setRecipeName(c.getString(c.getColumnIndex("RecipeName")));
				ing.setDirectionText(c.getString(c.getColumnIndex("DirectionText")));
				dir.add(ing);
			} while(c.moveToNext());
		}
		return dir;
	}

	public void updateIngredients(String recipe) {
		List<userCustomIngredients> ing = selectAllUserIngerdient();
		List<recipeIngredients> recIng = getRecIng(recipe);
		for(int j = 0; j < recIng.size(); j++){
			for(int k = 0; k < ing.size(); k++){
				Fraction recQuant = new Fraction(recIng.get(j).getIngredientQuantity());
				Fraction ingQuant = new Fraction(ing.get(k).getIngredientQuantity());
				if( (recIng.get(j).getIngredientUnit().isEmpty() == false) && (ing.get(k).getIngredientBaseUnit().isEmpty() == false) &&
						(recIng.get(j).getIngredientUnit().equals(ing.get(k).getIngredientBaseUnit()) == false) &&
						(recIng.get(j).getIngredientUnit().equals("units") == false) && (ing.get(k).getIngredientBaseUnit().equals("units") == false)){
					Fraction modifier = getMod(recIng.get(j).getIngredientUnit(), ing.get(k).getIngredientBaseUnit());
					recQuant = recQuant.multiply(modifier);
				}
				if(recIng.get(j).getIngredientName().equals(ing.get(k).getIngredientName())){
					ing.get(k).setIngredientQuantity(ingQuant.subtract(recQuant).getString());
					updateUserCustomIngredients(ing.get(k));
				}
					
			}
		}
		addRecUse(recipe);
	}

	public void addRecUse(String recipe) {
		List<recipeInfo> recl = new ArrayList<recipeInfo>();
		String query = "SELECT * FROM recipeInfo WHERE RecipeName = \'"+recipe+"\'";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				recipeInfo rec = new recipeInfo();
				rec.set_id(c.getInt(c.getColumnIndex("_id")));
				rec.setRecipeName(c.getString(c.getColumnIndex("RecipeName")));
				rec.setRecipeServings(c.getString(c.getColumnIndex("RecipeServings")));
				rec.setRecipeCategory(c.getString(c.getColumnIndex("RecipeCategory")));
				rec.setRecipeUseCount(Integer.parseInt(c.getString(c.getColumnIndex("RecipeUseCount"))));
				rec.setRecipeFavorite(Integer.parseInt(c.getString(c.getColumnIndex("RecipeFavorite"))));				
				recl.add(rec);
			} while(c.moveToNext());
		}
		recl.get(0).setRecipeUseCount(recl.get(0).getRecipeUseCount()+1);
		updateRecipe(recl.get(0));
	}
	
	public int updateRecipe(recipeInfo recipe){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("RecipeName", recipe.getRecipeName());
		values.put("RecipeServings", recipe.getRecipeServings());
		values.put("RecipeCategory", recipe.getRecipeCategory());
		values.put("RecipeUseCount", recipe.getRecipeUseCount());
		values.put("RecipeFavorite", recipe.getRecipeFavorite());
		return db.update("recipeInfo", values, "_id"+"=?", new String[]{String.valueOf(recipe.get_id())});
	}

	public List<recipeInfo> recipeUseSearch() {
		List<recipeInfo> recipes = new ArrayList<recipeInfo>();
		String query = "SELECT * FROM recipeInfo WHERE RecipeUseCount > 0";
		Log.e("DatabaseHelper",query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{
				recipeInfo rec = new recipeInfo();
				rec.set_id(c.getInt(c.getColumnIndex("_id")));
				rec.setRecipeName(c.getString(c.getColumnIndex("RecipeName")));
				rec.setRecipeServings(c.getString(c.getColumnIndex("RecipeServings")));
				rec.setRecipeCategory(c.getString(c.getColumnIndex("RecipeCategory")));
				rec.setRecipeUseCount(Integer.parseInt(c.getString(c.getColumnIndex("RecipeUseCount"))));
				rec.setRecipeFavorite(Integer.parseInt(c.getString(c.getColumnIndex("RecipeFavorite"))));				
				recipes.add(rec);
			} while(c.moveToNext());
		}
		List<recipeInfo> recipiesNarrowed = ingRecSearch(recipes);
		return recipiesNarrowed;
	}
}
