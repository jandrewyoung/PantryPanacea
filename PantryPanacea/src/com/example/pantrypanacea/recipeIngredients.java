// ****************************************************************************
// * Module Name: recipeIngredients                                           *
// * Description: sets up the schema for the recipeIngredients table          *
// * Date Created: March 30, 2014                                             *
// * Author: Daniel Hoekman                                                   *
// ****************************************************************************

package com.example.pantrypanacea;

public class recipeIngredients
{
	// table schema
	int _id;
	String RecipeName;
	String IngredientName;
	String IngredientQuantity;
	String IngredientUnit;
	String IngredientModifier;
	
	//
	// constructors
	//
	
	// constructor with no variables
	public recipeIngredients()
	{
	
	}
	
	// constructor with recipe name and ingredient name
	public recipeIngredients(String RecipeName, String IngredientName)
	{
		this.RecipeName = RecipeName;
		this.IngredientName = IngredientName;
	}
	
	// constructor with ID, recipe name, and ingredient name
	public recipeIngredients(int _id, String RecipeName, String IngredientName)
	{
		this._id = _id;
		this.RecipeName = RecipeName;
		this.IngredientName = IngredientName;
	}
	
	// constructor with recipe name, ingredient name, quantity, unit, and modifier
	public recipeIngredients(String RecipeName, String IngredientName, String IngredientQuantity, String IngredientUnit, String IngredientModifier)
	{
		this.RecipeName = RecipeName;
		this.IngredientName = IngredientName;
		this.IngredientQuantity = IngredientQuantity;
		this.IngredientUnit = IngredientUnit;
		this.IngredientModifier = IngredientModifier;
	}
	
	// constructor with ID, recipe name, ingredient name, quantity, unit, and modifier
	public recipeIngredients(int _id, String RecipeName, String IngredientName, String IngredientQuantity, String IngredientUnit, String IngredientModifier)
	{
		this._id = _id;
		this.RecipeName = RecipeName;
		this.IngredientName = IngredientName;
		this.IngredientQuantity = IngredientQuantity;
		this.IngredientUnit = IngredientUnit;
		this.IngredientModifier = IngredientModifier;
	}
	
	//
	// getters
	//
	
	// getter for ID
	public int get_id()
	{
		return this._id;
	}
	
	// getter for recipe name
	public String getRecipeName()
	{
		return this.RecipeName;
	}
	
	// getter for ingredient name
	public String getIngredientName()
	{
		return this.IngredientName;
	}
	
	// getter for quantity
	public String getIngredientQuantity()
	{
		return this.IngredientQuantity;
	}
	
	// getter for unit
	public String getIngredientUnit()
	{
		return this.IngredientUnit;
	}
	
	// getter for modifier
	public String getIngredientModifier()
	{
		return this.IngredientModifier;
	}
	
	//
	// setters
	//
	
	// setter for ID
	public void set_id(int _id)
	{
		this._id = _id;
	}
	
	// setter for recipe name
	public void setRecipeName(String RecipeName)
	{
		this.RecipeName = RecipeName;
	}
	
	// setter for ingredient name
	public void setIngredientName(String IngredientName)
	{
		this.IngredientName = IngredientName;
	}
	
	// setter for quantity
	public void setIngredientQuantity(String IngredientQuantity)
	{
		this.IngredientQuantity = IngredientQuantity;
	}
	
	// setter for unit
	public void setIngredientUnit(String IngredientUnit)
	{
		this.IngredientUnit = IngredientUnit;
	}
	
	// setter for modifier
	public void setIngredientModifier(String IngredientModifier)
	{
		this.IngredientModifier = IngredientModifier;
	}
}