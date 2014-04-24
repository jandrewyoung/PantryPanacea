package com.example.pantrypanacea;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ViewPrevActivity extends Activity {
	protected ListView recList;
	protected List<userCustomIngredients> usering;
	protected List<recipeDirections> recipeDir;
	protected AlertDialog.Builder alert;
	protected Context context;
	protected recListAdapter listAdapt;
	protected List<recipeInfo> recipes;
	protected TextView procedure;
	protected String recSelected;
	
    protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prev_used);
		recList = (ListView)findViewById(R.id.prevreclist);
		alert = new AlertDialog.Builder(this);
		alert.setTitle("View This Recipe?");
		procedure = new TextView(this);
		alert.setView(procedure);
		context = this;
		
		alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(context, PrevRecipe.class);
				intent.putExtra("value", recSelected);
				startActivity(intent);
			}
		});
		
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		final AlertDialog dialog = alert.create();
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		try {
			db.openDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		recipes = db.recipeUseSearch();
		db.close();
		if(recipes.isEmpty() == false){
			context = this;
			listAdapt = new recListAdapter(this,recipes);
			recList.setAdapter(listAdapt);
			recList.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> a, View parent, int position, long id) {
					recipeInfo select = (recipeInfo) recList.getItemAtPosition(position);
					recSelected = select.getRecipeName();
					DatabaseHelper db = new DatabaseHelper(getApplicationContext());
					try {
						db.openDataBase();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					recipeDir = db.getRecipeDir(recSelected);
					db.close();
					String recSteps = new String();
					for(int i = 0; i < recipeDir.size(); i++)
						recSteps = recSteps+Integer.toString(recipeDir.get(i).getDirectionNumber())+": "+recipeDir.get(i).getDirectionText()+"\n";
					procedure.setText(recSteps);
					dialog.show();
				}
				
			});
		}
		else{
			Toast.makeText(getApplicationContext(), "No Recipes Match", Toast.LENGTH_LONG).show();
		}
    }
}
