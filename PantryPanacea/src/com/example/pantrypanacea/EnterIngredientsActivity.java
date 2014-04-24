package com.example.pantrypanacea;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class EnterIngredientsActivity extends Activity implements OnItemSelectedListener{
	Spinner catSpinner;
	Spinner ingSpinner;
	Spinner unitSpinner;
	Button submit;
	EditText input;
	String catlabel, inglabel, quantity, unitlabel;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_ingredients);
		
		catSpinner = (Spinner) findViewById(R.id.catSpin);
		ingSpinner = (Spinner) findViewById(R.id.ingSpin);
		unitSpinner = (Spinner) findViewById(R.id.unitSpin);
		submit = (Button) findViewById(R.id.submitButton);
		input = (EditText) findViewById(R.id.amountText);
		loadCatSpinner();
		loadUnitSpinner();
		catSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
				catlabel = parent.getItemAtPosition(position).toString();
				loadIngSpinner(catlabel);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		ingSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
				inglabel = parent.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		unitSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
				unitlabel = parent.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		submit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				String blank = "";
				quantity = input.getText().toString();
				if(quantity.equals(blank)){
					Context context = getApplicationContext();
					Toast toast = Toast.makeText(context, "Quantity is missing", Toast.LENGTH_SHORT);
					toast.show();
				}
				else{
					Context context = getApplicationContext();
					DatabaseHelper db = new DatabaseHelper(getApplicationContext());
					try{
						db.openDataBase();
					} catch (SQLException e){
						e.printStackTrace();
					}
					if(db.checkUsrIng(inglabel)){
						Toast toast = Toast.makeText(context, "Ingredient "+inglabel+" already exists", Toast.LENGTH_LONG);
						toast.show();
					}
					else{
						Toast toast = Toast.makeText(context, "Adding "+quantity+" "+unitlabel+" of "+inglabel+" in category "+catlabel, Toast.LENGTH_LONG);
						toast.show();
						userCustomIngredients insert = new userCustomIngredients(inglabel,catlabel,quantity,unitlabel);
						db.createUserCustomIngredients(insert);
					}
					input.setText(null);
					db.close();
				}
			}
		});
	}


	private void loadCatSpinner() {
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		try {
			db.openDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> categories = db.selectIngCat();
		ArrayAdapter<String> catAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
		catAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		catSpinner.setAdapter(catAdapt);
		db.close();
	}

	private void loadIngSpinner(String category){
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		try {
			db.openDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> categories = db.selectIngName(category);
		ArrayAdapter<String> ingAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
		ingAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ingSpinner.setAdapter(ingAdapt);
		db.close();
	}
	
	private void loadUnitSpinner() {
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		try {
			db.openDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> categories = db.selectIngUnit();
		ArrayAdapter<String> catAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
		catAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		unitSpinner.setAdapter(catAdapt);
		db.close();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
