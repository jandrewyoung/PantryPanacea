package com.example.pantrypanacea;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ViewIngredientsActivity extends Activity{
	protected ListView ingList;
	protected List<userCustomIngredients> usering;
	protected IngredientListAdapter listAdapt;
	protected AlertDialog.Builder alert;
	protected EditText input;
	protected String ingSelected, newValue;
	protected Context context;
	
    protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_ingredients);
		ingList = (ListView)findViewById(R.id.inglist);
		alert = new AlertDialog.Builder(this);
		alert.setTitle("Update Ingredient");
		alert.setMessage("Enter the New Quantity");
		input = new EditText(this);
		alert.setView(input);
		
		alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				newValue = input.getText().toString();
				DatabaseHelper db = new DatabaseHelper(getApplicationContext());
				try {
					db.openDataBase();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				userCustomIngredients update = db.getUserCustomIngredients(ingSelected);
				update.setIngredientQuantity(newValue);
				db.updateUserCustomIngredients(update);
				listAdapt = new IngredientListAdapter(context,db.selectAllUserIngerdient());
				ingList.setAdapter(listAdapt);
				listAdapt.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), update.getIngredientName()+" updated to "+update.getIngredientQuantity()+" "+update.getIngredientBaseUnit(), Toast.LENGTH_LONG).show();
				db.close();
			}
		});
		
		alert.setNegativeButton("Canel", new DialogInterface.OnClickListener(){
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
		usering = db.selectAllUserIngerdient();
		db.close();
		if(usering.isEmpty() == false){
			context = this;
			listAdapt = new IngredientListAdapter(this,usering);
			ingList.setAdapter(listAdapt);
			ingList.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> a, View parent, int position, long id) {
					userCustomIngredients select = (userCustomIngredients) ingList.getItemAtPosition(position);
					ingSelected = select.getIngredientName();
					dialog.show();
				}
				
			});
		}
		else{
			Toast.makeText(getApplicationContext(), "No Ingredients in Inventory", Toast.LENGTH_LONG).show();
		}
    }
}
