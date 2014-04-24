package com.example.pantrypanacea;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class IngCheckAdapter extends ArrayAdapter<userCustomIngredients>{
	private final List<userCustomIngredients> list;
	private final Activity context;
	
	public IngCheckAdapter(Activity context, List<userCustomIngredients> list){
		super(context, R.layout.ing_check_row, list);
		this.context = context;
		this.list = list;
	}
	
	public List<userCustomIngredients> getIng(){
		List<userCustomIngredients> checklist = new ArrayList<userCustomIngredients>();
		for(int i = 0; i < list.size(); i++)
			if(list.get(i).selected)
				checklist.add(list.get(i));		
		return checklist;
	}
	
	public void selectall(){
		for(int i =0; i < list.size(); i++)
			list.get(i).setSelected(true);
	}
	
	public void deselectall(){
		for(int i =0; i < list.size(); i++)
			list.get(i).setSelected(false);
	}
	
	static class ViewHolder{
		protected TextView text;
		protected CheckBox checkbox;
	}
	
	@Override
	public View getView(int pos, View view, ViewGroup parent){
		View v = null;
		if(view == null){
			LayoutInflater inflater = context.getLayoutInflater();
			v = inflater.inflate(R.layout.ing_check_row, null);
			final ViewHolder holder = new ViewHolder();
			holder.text = (TextView) v.findViewById(R.id.checklabel);
			holder.checkbox = (CheckBox) v.findViewById(R.id.check);
			holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					userCustomIngredients element = (userCustomIngredients) holder.checkbox.getTag();
					element.setSelected(buttonView.isChecked());
				}
			});
			v.setTag(holder);
			holder.checkbox.setTag(list.get(pos));
		}
		else{
			v = view;
			((ViewHolder) v.getTag()).checkbox.setTag(list.get(pos));
		}
		ViewHolder hold = (ViewHolder) v.getTag();
		hold.text.setText(list.get(pos).getIngredientName());
		hold.checkbox.setChecked(list.get(pos).getSelected());
		return v;
	}
	
}
