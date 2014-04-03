// ****************************************************************************
// * Module Name: recipeInfo                                                  *
// * Description: sets up the schema for the recipeInfo table                 *
// * Date Created: March 30, 2014                                             *
// * Author: Daniel Hoekman                                                   *
// ****************************************************************************

package pantrypanacea;

public class recipeInfo
{
	// table schema
	int _id;
	String RecipeName;
	String RecipeServings;
	String RecipeCategory;
	
	//
	// constructors
	//
	
	// constructor with no variables
	public recipeInfo()
	{
	
	}
	
	// constructor with name
	public recipeInfo(String RecipeName)
	{
		this.RecipeName = RecipeName;
	}
	
	// constructor with ID and name
	public recipeInfo(int _id, String RecipeName)
	{
		this._id = _id;
		this.RecipeName = RecipeName;
	}
	
	// constructor with name and servings
	public recipeInfo(String RecipeName, String RecipeServings)
	{
		this.RecipeName = RecipeName;
		this.RecipeServings = RecipeServings;
	}
	
	// constructor with ID, name, and servings
	public recipeInfo(int _id, String RecipeName, String RecipeServings)
	{
		this._id = _id;
		this.RecipeName = RecipeName;
		this.RecipeServings = RecipeServings;
	}
	
	// constructor with name, servings, and category
	public recipeInfo(String RecipeName, String RecipeServings, String RecipeCategory)
	{
		this.RecipeName = RecipeName;
		this.RecipeServings = RecipeServings;
		this.RecipeCategory = RecipeCategory;
	}
	
	// constructor with ID, name, servings, and category
	public recipeInfo(int _id, String RecipeName, String RecipeServings, String RecipeCategory)
	{
		this._id = _id;
		this.RecipeName = RecipeName;
		this.RecipeServings = RecipeServings;
		this.RecipeCategory = RecipeCategory;
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
	
	// getter for servings
	public String getRecipeServings()
	{
		return this.RecipeServings;
	}
	
	// getter for category
	public String getRecipeCategory()
	{
		return this.RecipeCategory;
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
	
	// setter for servings
	public void setRecipeServings(String RecipeServings)
	{
		this.RecipeServings = RecipeServings;
	}
	
	// setter for category
	public void setRecipeCategory(String RecipeCategory)
	{
		this.RecipeCategory = RecipeCategory;
	}
}