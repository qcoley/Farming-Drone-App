package main.model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



//Team 2, Farming Drone App  -----------------------------------------------------------------------------------------------------------------------------------
public class itemContainer extends Component {

    private final StringProperty itemContainer_name;
    private final IntegerProperty itemContainer_price;
    private final IntegerProperty itemContainer_location_x;
    private final IntegerProperty itemContainer_location_y;
    private final IntegerProperty itemContainer_length;
    private final IntegerProperty itemContainer_width;
    private final IntegerProperty itemContainer_height;
    
    private ArrayList<Component> held_items = new ArrayList<Component>();

    public itemContainer(String name, Integer price, Integer location_x, Integer location_y, Integer length, Integer width, Integer height) {


    	this.itemContainer_name = new SimpleStringProperty(name);
        this.itemContainer_price = new SimpleIntegerProperty(price);
        this.itemContainer_location_x = new SimpleIntegerProperty(location_x);
        this.itemContainer_location_y = new SimpleIntegerProperty(location_y);
        this.itemContainer_length = new SimpleIntegerProperty(length);
        this.itemContainer_width = new SimpleIntegerProperty(width);
        this.itemContainer_height = new SimpleIntegerProperty(height); }

    public StringProperty itemProperty(){ return itemContainer_name; }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public String get_Name()
    { return itemContainer_name.get(); }

    public void set_Name(String item_name)
    { this.itemContainer_name.set(item_name); }

    
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Price()
    { return itemContainer_price.get(); }
    
    public void set_Price(Integer item_price)
    { this.itemContainer_price.set(item_price); }

    
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------- 
    public int get_Location_X()
    { return itemContainer_location_x.get(); }

    public void set_Location_X(Integer item_location_x)
    { this.itemContainer_location_x.set(item_location_x); }


    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Location_Y()
    { return itemContainer_location_y.get(); }

    public void set_Location_Y(Integer item_location_y)
    { this.itemContainer_location_y.set(item_location_y); }

    
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Length()
    { return itemContainer_length.get(); }

    public void set_Length(Integer item_length)
    { this.itemContainer_length.set(item_length); }

    
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Height()
    { return itemContainer_height.get(); }

    public void set_Height(Integer item_height)
    { this.itemContainer_height.set(item_height); }

    
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int get_Width()
    { return itemContainer_width.get(); }

    public void set_Width(Integer item_width)
    { this.itemContainer_width.set(item_width); }

    
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public void add_item(Component new_item)
    { this.held_items.add(new_item); }
    
    
    public void delete_item(String target_item_name){
    	
    	int found_index;
    	
    	for (Component items : this.held_items) {
        	
    		if (items.get_Name().equals(target_item_name)) {
        		
    			found_index = this.held_items.indexOf(items);
        		this.held_items.remove(found_index);
        		break;
        	
    		} } }
    
    
    public void print_item_list() {
    	
    	System.out.println("Items stored in container are: ");
    	
    	for (Component items : this.held_items) {
    		System.out.println(items.get_Name()); } 
    	
    	System.out.println("------------"); } 
    
    public ArrayList<Component> get_Item_List() {
    	return this.held_items;
    }
    
    
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public void accept(Visitor vis) 
    { vis.visit(this); } 
    
}



