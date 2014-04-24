// ****************************************************************************
// * Module Name: unitConversions                                             *
// * Description: sets up the schema for the unitConversions table            *
// * Date Created: March 30, 2014                                             *
// * Author: Daniel Hoekman                                                   *
// ****************************************************************************

package com.example.pantrypanacea;

public class unitConversions
{
	// table schema
	int _id;
	String OriginalUnits;
	String ConvertedUnits;
	String Multiplier;
	
	//
	// constructors
	//
	
	// constructor with no variables
	public unitConversions()
	{
	
	}
	
	// constructor with original units, converted units, and multiplier
	public unitConversions(String OriginalUnits, String ConvertedUnits, String Multiplier)
	{
		this.OriginalUnits = OriginalUnits;
		this.ConvertedUnits = ConvertedUnits;
		this.Multiplier = Multiplier;
	}
	
	// constructor with ID, original units, converted units, and multiplier
	public unitConversions(int _id, String OriginalUnits, String ConvertedUnits, String Multiplier)
	{
		this._id = _id;
		this.OriginalUnits = OriginalUnits;
		this.ConvertedUnits = ConvertedUnits;
		this.Multiplier = Multiplier;
	}
	
	//
	// getters
	//
	
	// getter for ID
	public int get_id()
	{
		return this._id;
	}
	
	// getter for original units
	public String getOriginalUnits()
	{
		return this.OriginalUnits;
	}
	
	// getter for converted units
	public String getConvertedUnits()
	{
		return this.ConvertedUnits;
	}
	
	// getter for multiplier
	public String getMultiplier()
	{
		return this.Multiplier;
	}
	
	//
	// setters
	//
	
	// setter for ID
	public void set_id(int _id)
	{
		this._id = _id;
	}
	
	// setter for original units
	public void setOriginalUnits(String OriginalUnits)
	{
		this.OriginalUnits = OriginalUnits;
	}
	
	// setter for converted units
	public void setConvertedUnits(String ConvertedUnits)
	{
		this.ConvertedUnits = ConvertedUnits;
	}
	
	// setter for multiplier
	public void setMultiplier(String Multiplier)
	{
		this.Multiplier = Multiplier;
	}
}