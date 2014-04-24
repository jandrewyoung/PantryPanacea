// ****************************************************************************
// * Module Name: recipeDirections                                            *
// * Description: Sets up the schema of the recipeDirections table.           *
// * Date Created: March 30, 2014                                             *
// * Author: Daniel Hoekman                                                   *
// ****************************************************************************

package com.example.pantrypanacea;

public class recipeDirections
{
	// table schema
	int _id;
	String RecipeName;
	int DirectionNumber;
	String DirectionText;
	
	//
	// constructors
	//
	
	// constructor with no variables
	public recipeDirections()
	{
	
	}
	
	// constructor with name
	public recipeDirections(String RecipeName)
	{
		this.RecipeName = RecipeName;
	}
	
	// constructor with ID and name
	public recipeDirections(int _id, String RecipeName)
	{
		this._id = _id;
		this.RecipeName = RecipeName;
	}
	
	// constructor with name, number, and text
	public recipeDirections(String RecipeName, int DirectionNumber, String DirectionText)
	{
		this.RecipeName = RecipeName;
		this.DirectionNumber = DirectionNumber;
		this.DirectionText = DirectionText;
	}
	
	// constructor with ID, name, number, and text
	public recipeDirections(int _id, String RecipeName, int DirectionNumber, String DirectionText)
	{
		this._id = _id;
		this.RecipeName = RecipeName;
		this.DirectionNumber = DirectionNumber;
		this.DirectionText = DirectionText;
	}
	
	//
	// getters
	//
	
	// getter for ID
	public int get_id()
	{
		return this._id;
	}
	
	// getter for name
	public String getRecipeName()
	{
		return this.RecipeName;
	}
	
	// getter for direction number
	public int getDirectionNumber()
	{
		return this.DirectionNumber;
	}
	
	// getter for direction text
	public String getDirectionText()
	{
		return this.DirectionText;
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
	public void setRecipeName(String RecipeName)
	{
		this.RecipeName = RecipeName;
	}
	
	// setter for direction number
	public void setDirectionNumber(int DirectionNumber)
	{
		this.DirectionNumber = DirectionNumber;
	}
	
	// setter for direction text
	public void setDirectionText(String DirectionText)
	{
		this.DirectionText = DirectionText;
	}
}