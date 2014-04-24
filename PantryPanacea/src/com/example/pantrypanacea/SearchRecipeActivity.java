package com.example.pantrypanacea;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SearchRecipeActivity extends Activity{
	protected List<userCustomIngredients> userIng, selecteding;
	protected IngCheckAdapter listAdapt;
	protected ListView ingList;
	protected Button select, unselect, submit;
	protected Activity activity;
	protected Context context;
	
    protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_recipes);
		ingList = (ListView)findViewById(R.id.listView1);
		select = (Button)findViewById(R.id.selectbutton);
		unselect = (Button)findViewById(R.id.deselectbutton);
		submit = (Button)findViewById(R.id.submitbutton);
		activity = this;
		context = this;
		
		select.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				DatabaseHelper db = new DatabaseHelper(getApplicationContext());
				try {
					db.openDataBase();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				userIng = db.selectAllUserIngerdient();
				db.close();
				if(userIng.isEmpty() == false){
				listAdapt = new IngCheckAdapter(activity, userIng);
				listAdapt.selectall();
				ingList.setAdapter(listAdapt);
				listAdapt.notifyDataSetChanged();
				}
			}
		});
		
		unselect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				DatabaseHelper db = new DatabaseHelper(getApplicationContext());
				try {
					db.openDataBase();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				userIng = db.selectAllUserIngerdient();
				db.close();
				if(userIng.isEmpty() == false){
				listAdapt = new IngCheckAdapter(activity, userIng);
				listAdapt.deselectall();
				ingList.setAdapter(listAdapt);
				listAdapt.notifyDataSetChanged();
				}
			}
		});

		submit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				selecteding = listAdapt.getIng();
				if(selecteding.isEmpty() == false){
				Intent intent = new Intent(context, ViewRecipeList.class);
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("value", (ArrayList<? extends Parcelable>) selecteding);
				intent.putExtras(bundle);
				startActivity(intent);
				}
				else{
					Toast.makeText(getApplicationContext(), "No Ingredients Selected", Toast.LENGTH_SHORT).show();
				}
			}
		});

		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		try {
			db.openDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userIng = db.selectAllUserIngerdient();
		db.close();
		if(userIng.isEmpty() == false){
		listAdapt = new IngCheckAdapter(this, userIng);
		ingList.setAdapter(listAdapt);
		}
    }
}