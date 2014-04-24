// ****************************************************************************
// * Module Name: userCustomIngredients                                       *
// * Description: sets up the schema for the userCustomIngredients table      *
// * Date Created: March 30, 2014                                             *
// * Author: Daniel Hoekman                                                   *
// ****************************************************************************

package com.example.pantrypanacea;

import android.os.Parcel;
import android.os.Parcelable;

public class userCustomIngredients implements Parcelable
{
	// table schema
	int _id;
	String IngredientName;
	String IngredientCategory;
	String IngredientQuantity;
	String IngredientBaseUnit;
	boolean selected;
	
	//
	// constructors
	//
	
	// constructor with no variables
	public userCustomIngredients()
	{
		selected = false;
	
	}
	
	// constructor with name
	public userCustomIngredients(String IngredientName)
	{
		this.IngredientName = IngredientName;
		selected = false;
	}
	
	// constructor with ID and name
	public userCustomIngredients(int _id, String IngredientName)
	{
		this._id = _id;
		this.IngredientName = IngredientName;
		selected = false;
	}
	
	// constructor with name, category, quantity, and base unit
	public userCustomIngredients(String IngredientName, String IngredientCategory, String IngredientQuantity, String IngredientBaseUnit)
	{
		this.IngredientName = IngredientName;
		this.IngredientCategory = IngredientCategory;
		this.IngredientQuantity = IngredientQuantity;
		this.IngredientBaseUnit = IngredientBaseUnit;
		selected = false;
	}
	
	// constructor with ID, name, category, quantity, and base unit
	public userCustomIngredients(int _id, String IngredientName, String IngredientCategory, String IngredientQuantity, String IngredientBaseUnit)
	{
		this._id = _id;
		this.IngredientName = IngredientName;
		this.IngredientCategory = IngredientCategory;
		this.IngredientQuantity = IngredientQuantity;
		this.IngredientBaseUnit = IngredientBaseUnit;
		selected = false;
	}
	
	//
	// getters
	//
	
	// getter for ID
	public boolean getSelected(){
		return selected;
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
	}
	
	public int get_id()
	{
		return this._id;
	}
	
	// getter for name
	public String getIngredientName()
	{
		return this.IngredientName;
	}
	
	// getter for category
	public String getIngredientCategory()
	{
		return this.IngredientCategory;
	}
	
	// getter for quantity
	public String getIngredientQuantity()
	{
		return this.IngredientQuantity;
	}
	
	// getter for base unit
	public String getIngredientBaseUnit()
	{
		return this.IngredientBaseUnit;
	}
	
	//
	// setters
	//
	
	// setter for ID
	public void set_id(int _id)
	{
		this._id = _id;
	}
	
	// setter for name
	public void setIngredientName(String IngredientName)
	{
		this.IngredientName = IngredientName;
	}
	
	// setter for category
	public void setIngredientCategory(String IngredientCategory)
	{
		this.IngredientCategory = IngredientCategory;
	}
	
	// setter for quantity
	public void setIngredientQuantity(String IngredientQuantity)
	{
		this.IngredientQuantity = IngredientQuantity;
	}
	
	// setter for base unit
	public void setIngredientBaseUnit(String IngredientBaseUnit)
	{
		this.IngredientBaseUnit = IngredientBaseUnit;
	}

	public userCustomIngredients(Parcel in){
		_id = in.readInt();
		IngredientName = in.readString();
		IngredientCategory = in.readString();
		IngredientQuantity = in.readString();
		IngredientBaseUnit = in.readString();
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(_id);
		dest.writeString(IngredientName);
		dest.writeString(IngredientCategory);
		dest.writeString(IngredientQuantity);
		dest.writeString(IngredientBaseUnit);
	}
	
    public static final Parcelable.Creator<userCustomIngredients> CREATOR = new Parcelable.Creator<userCustomIngredients>()
    {
        public userCustomIngredients createFromParcel(Parcel in)
        {
            return new userCustomIngredients(in);
        }
        public userCustomIngredients[] newArray(int size)
        {
            return new userCustomIngredients[size];
        }
    };
}