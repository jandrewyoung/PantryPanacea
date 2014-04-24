package com.example.pantrypanacea;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewRecipeActivity extends Activity{
	protected String recipe;
	TextView directions, ingredients, recipename;
	List<recipeIngredients> ingList;
	List<recipeDirections> dirList;
	String ingString, dirString;
	
    protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_recipe);
		Intent intent = getIntent();
		recipe = intent.getStringExtra("value");
		directions = (TextView)findViewById(R.id.dirView);
		ingredients = (TextView)findViewById(R.id.ingView);
		recipename = (TextView)findViewById(R.id.titleView);
		
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		try {
			db.openDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ingList = db.getRecIng(recipe);
		dirList = db.getRecipeDir(recipe);
		db.updateIngredients(recipe);
		db.close();
		ingString = new String();
		dirString = new String();
		for(int i = 0; i < ingList.size(); i++)
			ingString = ingString + ingList.get(i).getIngredientQuantity() +" "+ingList.get(i).getIngredientUnit()+" of "+ingList.get(i).getIngredientName()+" "+ingList.get(i).getIngredientModifier()+"\n";
		for(int i = 0; i < dirList.size(); i++)
			dirString = dirString + Integer.toString(i+1)+": "+dirList.get(i).getDirectionText()+"\n";
		recipename.setText(recipe);
		directions.setText(dirString);
		ingredients.setText(ingString);
    }
}
