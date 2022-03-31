package main.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



//Team 2, Farming Drone App  ------------------------------------------------------------------------------------------------------------------------------------
public class Item extends Component {

	private static final long serialVersionUID = 1L;
	private final StringProperty item_name;
    private final IntegerProperty item_price;
    private final IntegerProperty item_location_x;
    private final IntegerProperty item_location_y;
    private final IntegerProperty item_height;
    private final IntegerProperty item_length;
    private final IntegerProperty item_width;
    private int item_marketvalue;

    public Item(String name, Integer price, Integer location_x, Integer location_y, Integer length, Integer width, Integer height)

    { this.item_name = new SimpleStringProperty(name);
      this.item_price = new SimpleIntegerProperty(price);
      this.item_location_x = new SimpleIntegerProperty(location_x);
      this.item_location_y = new SimpleIntegerProperty(location_y);
      this.item_length = new SimpleIntegerProperty(length);
      this.item_width = new SimpleIntegerProperty(width);
      this.item_height = new SimpleIntegerProperty(height); 
      
      this.item_marketvalue = price; }

   
    public StringProperty itemProperty() { return item_name; }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public String get_Name()
	{ return item_name.get(); }

    public void set_Name(String item_name)
    { this.item_name.set(item_name); }


    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Price()
	{ return item_price.get(); }

    public void set_Price(Integer item_price)
    { this.item_price.set(item_price); }


    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Location_X()
	{ return item_location_x.get(); }

    public void set_Location_X(Integer item_location_x)
    { this.item_location_x.set(item_location_x); }


    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Location_Y()
	{ return item_location_y.get(); }

    public void set_Location_Y(Integer item_location_y)
    { this.item_location_y.set(item_location_y); }


    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Length()
	{ return item_length.get(); }

    public void set_Length(Integer item_length)
    { this.item_length.set(item_length); }


    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Height()
	{ return item_height.get(); }

    public void set_Height(Integer item_height)
    { this.item_height.set(item_height); }


    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Width()
	{ return item_width.get(); }

    public void set_Width(Integer item_width)
    { this.item_width.set(item_width); } 
    
    
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Marketvalue()
    {return this.item_marketvalue; }
    
    public void set_Marketvalue(int item_marketvalue)
    {this.item_marketvalue = item_marketvalue; }
    
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public void accept(Visitor vis) 
    { vis.visit(this); } 

}

