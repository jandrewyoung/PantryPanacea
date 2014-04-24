package com.example.pantrypanacea;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class recListAdapter extends BaseAdapter{
		private static List<recipeInfo> recArray;
		private LayoutInflater inflater;
		
		public recListAdapter(Context context, List<recipeInfo> recipe){
			recArray = recipe;
			inflater = LayoutInflater.from(context);
		}
		
		public int getCount(){
			return recArray.size();
		}
		
		public Object getItem(int pos){
			return recArray.get(pos);
		}
		
		public long getItemId(int pos){
			return pos;
		}
		
		public View getView(int pos, View convertView, ViewGroup parent){
			ViewHolder holder;
			if(convertView == null){
				convertView = inflater.inflate(R.layout.rec_list_row, null);
				holder = new ViewHolder();
				holder.txt = (TextView) convertView.findViewById(R.id.recipetext);
				convertView.setTag(holder);
			}
			else holder = (ViewHolder) convertView.getTag();
			holder.txt.setText(recArray.get(pos).getRecipeName());
			return convertView;
		}
		
		static class ViewHolder{
			TextView txt;
		}
	}

