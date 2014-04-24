package com.example.pantrypanacea;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IngredientListAdapter extends BaseAdapter{
	private static List<userCustomIngredients> ingArray;
	private LayoutInflater inflater;
	
	public IngredientListAdapter(Context context, List<userCustomIngredients> ingredients){
		ingArray = ingredients;
		inflater = LayoutInflater.from(context);
	}
	
	public int getCount(){
		return ingArray.size();
	}
	
	public Object getItem(int pos){
		return ingArray.get(pos);
	}
	
	public long getItemId(int pos){
		return pos;
	}
	
	public View getView(int pos, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.ing_list_row, null);
			holder = new ViewHolder();
			holder.txtIng = (TextView) convertView.findViewById(R.id.ingName);
			holder.txtAmt = (TextView) convertView.findViewById(R.id.ingAmount);
			convertView.setTag(holder);
		}
		else holder = (ViewHolder) convertView.getTag();
		holder.txtIng.setText(ingArray.get(pos).getIngredientName());
		holder.txtAmt.setText(ingArray.get(pos).getIngredientQuantity()+" "+ingArray.get(pos).getIngredientBaseUnit());
		return convertView;
	}
	
	static class ViewHolder{
		TextView txtIng;
		TextView txtAmt;
	}
}
