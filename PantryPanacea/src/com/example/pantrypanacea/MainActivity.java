package com.example.pantrypanacea;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	DatabaseHelper db = new DatabaseHelper(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			db.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContentView(R.layout.activity_main);
		
	}

	public void enterIng(View view){
		Intent intent = new Intent(this,EnterIngredientsActivity.class);
		startActivity(intent);
	}
	
	public void recSearch(View view){
		Intent intent = new Intent(this,SearchRecipeActivity.class);
		startActivity(intent);
	}
	
	public void viewIng(View view){
		Intent intent = new Intent(this,ViewIngredientsActivity.class);
		startActivity(intent);
	}
	
	public void viewPrev(View view){
		Intent intent = new Intent(this,ViewPrevActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
