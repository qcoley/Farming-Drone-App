package main.model;


// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
public abstract class Component {

	public abstract void set_Name(String item_name);
	public abstract String get_Name();

	public abstract void set_Price(Integer item_price);
	public abstract int get_Price();

	public abstract void set_Location_X(Integer item_location_x);
	public abstract int get_Location_X();

	public abstract void set_Location_Y(Integer item_location_y);
	public abstract int get_Location_Y();

	public abstract void set_Length(Integer item_length);
	public abstract int get_Length();

	public abstract void set_Width(Integer item_width);
	public abstract int get_Width();

	public abstract void set_Height(Integer item_height);
	public abstract int get_Height();

	
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------
	public abstract void accept(Visitor vis);

}
