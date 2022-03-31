package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import main.jdrone.physical.tello.TelloDrone;
import main.model.*;

import java.io.IOException;
import java.util.ArrayList;



//Team 2, Farming Drone App  -------------------------------------------------------------------------------------------
public class DashboardController {

	int startX = 0; int startY = 0;	
	
	@FXML private AnchorPane visualizationPane; // ref to visualization area
	@FXML private TreeView<String> treeView;

	private final ArrayList<Region> spawnedRegions = new ArrayList<>(); // store created boxes
	private final ArrayList<Component> farm = new ArrayList<>();

	@FXML private void initialize() {}


	// Singleton design implementation for controller class ------------------------------------------------------------

	public DashboardController() {}


	// -----------------------------------------------------------------------------------------------------------------
	public void drawVisualization(Component farm_item) {
		
		spawnedRegions.add(new Region());
		
		int last_index = spawnedRegions.size() - 1;
		
		spawnedRegions.get(last_index).setPrefSize(farm_item.get_Width(), farm_item.get_Length());
		spawnedRegions.get(last_index).setLayoutX(farm_item.get_Location_X());
		spawnedRegions.get(last_index).setLayoutY(farm_item.get_Location_Y());
		spawnedRegions.get(last_index).setBorder(new Border( new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, new BorderWidths(1))));
		visualizationPane.getChildren().add(spawnedRegions.get(last_index)); }
	
	
	// -----------------------------------------------------------------------------------------------------------------
	public void deleteVisualization(int index) {
	  	
		visualizationPane.getChildren().remove(spawnedRegions.get(index)); // remove target from dash
	  	spawnedRegions.remove(index); } // remove target from list
	
		
	// -----------------------------------------------------------------------------------------------------------------
	public void setMainApp() {

		// Test items to populate items/item containers
		itemContainer test_item = new itemContainer("barn", 20000, 115,
				550, 150, 250, 150);
		itemContainer test_item_2 = new itemContainer("livestock area", 400, 200,
				552, 40, 70, 40);
		Item test_item_3 = new Item("cow", 1800, 202, 568, 20, 40, 20);
		Item test_item_4 = new Item("milk storage", 1200, 117,
				658, 40, 70, 40);
		itemContainer test_item_5 = new itemContainer("command center", 200, 50,
				100, 150, 300, 150);
		Item test_item_6 = new Item("command center store room", 800, 75,
				125, 50, 50, 50);
		Item test_item_7 = new Item("crop", 10000, 420,
				530, 250, 150, 250);
		  	
		farm.add(test_item); drawVisualization(test_item);
		farm.add(test_item_2); drawVisualization(test_item_2);
		farm.add(test_item_3); drawVisualization(test_item_3);
		farm.add(test_item_4); drawVisualization(test_item_4);
		farm.add(test_item_5); drawVisualization(test_item_5);
		farm.add(test_item_6); drawVisualization(test_item_6);        
		farm.add(test_item_7); drawVisualization(test_item_7);
		
		test_item.add_item(test_item_2); test_item.add_item(test_item_4);
		test_item_2.add_item(test_item_3); test_item_5.add_item(test_item_6);
		test_item_3.set_Marketvalue(1620); test_item_4.set_Marketvalue(1080);
		test_item_6.set_Marketvalue(720); test_item_7.set_Marketvalue(10);
	 	
		TreeItem<String> treeRoot = new TreeItem<>("root");					        // root
		treeView.setRoot(treeRoot);
  	
		TreeItem<String> barn = new TreeItem<>(farm.get(0).get_Name());		        // barn
		treeRoot.getChildren().add(barn);
  	
		TreeItem<String> commandCenter = new TreeItem<>(farm.get(4).get_Name());		// command center
		treeRoot.getChildren().add(commandCenter);
  	
		TreeItem<String> drone = new TreeItem<>(farm.get(5).get_Name());		        // drone
		commandCenter.getChildren().add(drone);
  	
		TreeItem<String> livestock = new TreeItem<>(farm.get(1).get_Name());		    // livestock
		barn.getChildren().add(livestock);
  	
		TreeItem<String> cow = new TreeItem<>(farm.get(2).get_Name());		        // cow
		livestock.getChildren().add(cow);

		TreeItem<String> milkStorage = new TreeItem<>(farm.get(3).get_Name());		// milk storage
		barn.getChildren().add(milkStorage);
  	
		TreeItem<String> crop = new TreeItem<>(farm.get(6).get_Name());		        // crop
		treeRoot.getChildren().add(crop); }
  	
  	
	// -----------------------------------------------------------------------------------------------------------------
	@FXML private Label Purchase_Price; @FXML private Label Market_Value;   
	@FXML public void  listenForClicks() {
				
		try {		  
		 
			for (Component farm_item : farm) {

				if (farm_item.get_Name().equals(selectItem().getValue())) {
						 
					farm.indexOf(farm_item); 	
				 				 	
					PricingVisitor pricing_vis = new PricingVisitor(); farm_item.accept(pricing_vis);
					MarketvalueVisitor market_vis = new MarketvalueVisitor(); farm_item.accept(market_vis);
										
					Market_Value.setText(Integer.toString(market_vis.getTotalMarketvalue()));
					Purchase_Price.setText(Integer.toString(pricing_vis.getTotalPrice()));
					
					
					} } } catch(Exception ignored) {	} }
	
	
	// -----------------------------------------------------------------------------------------------------------------
	@FXML public TreeItem<String> selectItem(){

		return (treeView.getSelectionModel().getSelectedItem());
	}
  
  
	//------------------------------------------------------------------------------------------------------------------
	@FXML private void handleDeleteItem() {
	 
		try {   	    
					
			String selected_item = selectItem().getValue(); // store item as String
			String selected_item_parent = selectItem().getParent().getValue();
			int found_index;
  	     		     
			for (Component farm_item : farm) {
  	       	 
				if (farm_item.get_Name().equals(selected_item)) { found_index = farm.indexOf(farm_item);
  		   						    		 
					if (farm_item instanceof Item) {
  			     			       			 
						farm.remove(found_index); deleteVisualization(found_index);  			      			       			 
						selectItem().getParent().getChildren().remove(selectItem());
  			     			       			 
						if (!selected_item_parent.equals("root")) {
  		    	    				       				 
							for (Component farm_items : farm) {
  		    		   		    		      					 
								if (farm_items.get_Name().equals(selected_item_parent)) {
  		    	  		       						 
									((itemContainer) farm_items).delete_item(selected_item); 		    	  	    		    		  
    					 
								} } }
					} else {
  			     			   	   			 
						Alert not_Deletable = new Alert(AlertType.NONE);
   							   			 
						not_Deletable.setAlertType(AlertType.INFORMATION);
						not_Deletable.setHeaderText("You selected item  : " +
								""+selected_item+" You can not delete this using this button. " +
								"Try using the delete button under Item containers");
						not_Deletable.show();

					}
					break;
				} } }
      			
		catch(Exception e) { Alert error = new Alert(AlertType.NONE); 		   		 
   	    error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new"); error.show(); } }
  
     
	@FXML private void handleDeleteItemContainer() {
    
		try {	
  	
			String selected_item = selectItem().getValue(); // store item as String
			String selected_item_parent = selectItem().getParent().getValue();
        	
			int found_index;
    
			for (Component farm_item : farm) {
   	      	  
				if (farm_item.get_Name().equals(selected_item)) { found_index = farm.indexOf(farm_item);
   		      		   
					if (farm_item instanceof Item) {
   			
						Alert not_Deletable = new Alert(AlertType.NONE);
						not_Deletable.setAlertType(AlertType.INFORMATION);
						not_Deletable.setHeaderText("You selected an item; select an item container");
						not_Deletable.show();
   			     			  
						break; } else {
   			      			   
						if (selectItem().getChildren().isEmpty()) {
   				   
							farm.remove(found_index); deleteVisualization(found_index);   				   
							selectItem().getParent().getChildren().remove(selectItem());
   				   
							if (!selected_item_parent.equals("root")) {
    		    	  
								for (Component farm_items : farm) {    		    		  
    		    		  
									if (farm_items.get_Name().equals(selected_item_parent)) {
    		    	  		   
										((itemContainer) farm_items).delete_item(selected_item);
    		    	  	  									
									} } } break; } else {
   				     				
							Alert notDeletable = new Alert(AlertType.NONE);
							notDeletable.setAlertType(AlertType.INFORMATION);
							notDeletable.setHeaderText("You selected item container : " +
									""+selected_item+" You can not delete this until its items have been removed");
							notDeletable.show();
   			     			   
						} } } } }
    
		catch(Exception e) { Alert error = new Alert(AlertType.NONE); 		   		 
   	    error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new"); error.show(); } }
    
    
	// -----------------------------------------------------------------------------------------------------------------
    @FXML public void changeDimensions() {
    	
    	try {
    	
    		String selected_item = selectItem().getValue(); Label length = new Label("enter new length");
    		Label width = new Label("enter new width"); Label height = new Label("enter new height");
    	    	
    		TextInputDialog popup1 = new TextInputDialog("Enter new length");
			TextInputDialog popup2 = new TextInputDialog("Enter new width");
    		TextInputDialog popup3 = new TextInputDialog("Enter new height");
    	    	
    		popup1.setHeaderText(selected_item); popup2.setHeaderText(selected_item);    	
    		popup3.setHeaderText(selected_item);
    	    	
    		popup1.showAndWait(); popup2.showAndWait(); popup3.showAndWait();
    	    	
    		length.setText(popup1.getEditor().getText()); width.setText(popup2.getEditor().getText());    	
    		height.setText(popup2.getEditor().getText());
    		    	    	
    		int l = Integer.parseInt(length.getText()); int w = Integer.parseInt(width.getText());    	
    		int h = Integer.parseInt(height.getText());
        
    		int found_index;
               
    		if (l < 0||w < 0||h < 0) {
     		        	
    			Alert negativeSize = new Alert(AlertType.NONE); negativeSize.setAlertType(AlertType.INFORMATION);     		
    			negativeSize.setHeaderText("item : "+ selected_item +" can not have negative size");     		
    			negativeSize.show(); }
     	        
    		for (Component farm_item : farm) {
     	         	
    			if (farm_item.get_Name().equals(selected_item)) {
     		           		
    				found_index = farm.indexOf(farm_item);    		       			           		
    				farm_item.set_Length(l); farm_item.set_Width(w);     			           		
    				farm_item.set_Height(h);
     			        			          		
    				spawnedRegions.get(found_index).setPrefSize(w, l);    			       			   
        		
    				break; } } }
           	
    	catch(Exception e) { Alert error = new Alert(AlertType.NONE); 		   		 
   	    error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new"); error.show(); } }
    
    
    // -----------------------------------------------------------------------------------------------------------------
    @FXML private void handleAddItem() {
    	
    	try {
    	
    		String selected_item = selectItem().getValue();
    		Label name = new Label("enter item name"); Label price = new Label("enter item price");   	
    		Label location_x = new Label("enter item x location"); Label location_y =
					new Label("enter item y location");
    		Label length = new Label("enter item length"); Label width = new Label("enter item width"); 	
    		Label height = new Label("enter item height");
    	
    		TextInputDialog popup1 = new TextInputDialog("Enter item name"); TextInputDialog popup2 =
					new TextInputDialog("Enter item price");
    		TextInputDialog popup3 = new TextInputDialog("Enter item x location"); TextInputDialog popup4 =
					new TextInputDialog("Enter item y location");
    		TextInputDialog popup5 = new TextInputDialog("Enter item length"); TextInputDialog popup6 =
					new TextInputDialog("Enter item width");
    		TextInputDialog popup7 = new TextInputDialog("Enter item height");
    	    	
    		popup1.setHeaderText(selected_item); popup2.setHeaderText(selected_item);    	
    		popup3.setHeaderText(selected_item); popup4.setHeaderText(selected_item);    	
    		popup5.setHeaderText(selected_item); popup6.setHeaderText(selected_item);    	
    		popup7.setHeaderText(selected_item);    	
    		
    		popup1.showAndWait(); popup2.showAndWait();    	
    		popup3.showAndWait(); popup4.showAndWait();    	
    		popup5.showAndWait(); popup6.showAndWait();    	
    		popup7.showAndWait();
    	    	
    		name.setText(popup1.getEditor().getText()); price.setText(popup2.getEditor().getText());   	
    		location_x.setText(popup3.getEditor().getText()); location_y.setText(popup4.getEditor().getText());   	
    		length.setText(popup5.getEditor().getText()); width.setText(popup6.getEditor().getText());   	
    		height.setText(popup7.getEditor().getText());
    	    	
    		String provided_name = name.getText();
			int provided_price = Integer.parseInt(price.getText());
    		int provided_x = Integer.parseInt(location_x.getText());
			int provided_y = Integer.parseInt(location_y.getText());
    		int provided_len = Integer.parseInt(length.getText());
			int provided_wid = Integer.parseInt(width.getText());
    		int provided_height = Integer.parseInt(height.getText());
    	   	
    		if (provided_price < 0) {
     		   		
    			Alert negativePrice = new Alert(AlertType.NONE); negativePrice.setAlertType(AlertType.INFORMATION);     		
    			negativePrice.setHeaderText("item : "+ provided_name +" price was set to a negative number");     		
    			negativePrice.show(); }
    	    	
    		if (provided_len < 0||provided_wid < 0||provided_height < 0) {
     		
    			Alert negativeSize = new Alert(AlertType.NONE); negativeSize.setAlertType(AlertType.INFORMATION);     		
    			negativeSize.setHeaderText("item : "+ provided_name +" can not have negative size");    		
    			negativeSize.show(); }
    	    	
    		if (selected_item.equals("root")) {
    		    		
    			farm.add(new Item(provided_name, provided_price, provided_x, provided_y, provided_len,
						provided_wid, provided_height));
    			int added_index = farm.size() - 1;  			
    			
    			selectItem().getChildren().add(new TreeItem<>(farm.get(added_index).get_Name()));
    			drawVisualization(farm.get(added_index));
    	    	
    		} else {    	
    	
    			for (Component farm_item : farm) {
    	   	     	  
    				if (farm_item.get_Name().equals(selected_item)) { farm.indexOf(farm_item);
      		     		    		  
    					if (farm_item instanceof itemContainer) {
      			      			      			  
    						farm.add(new Item(provided_name, provided_price, provided_x, provided_y,
									provided_len, provided_wid, provided_height));
    						int added_index = farm.size() - 1;
      			    			      			  
    						((itemContainer) farm_item).add_item(farm.get(added_index));
							((itemContainer) farm_item).print_item_list();
      			     			  
    						selectItem().getChildren().add(new TreeItem<>(farm.get(added_index).get_Name()));
    						drawVisualization(farm.get(added_index));      			     			
      			  
    						break; } else {
      			      			
    						Alert notAContainer = new Alert(AlertType.NONE);
							notAContainer.setAlertType(AlertType.INFORMATION);
    						notAContainer.setHeaderText("You selected item : "+selected_item+
									" please select an item container to add an item");
    						notAContainer.show();
      			     			     		
    					} } } } }
    	
    	catch(Exception e) { Alert error = new Alert(AlertType.NONE); 		   		 
   	    error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new"); error.show(); } }
    
    
    @FXML private void handle_Add_item_Container() {
    	
    	try {
    	    
    		String selected_item = selectItem().getValue();
    	
    	    Label name = new Label("enter item name"); Label price = new Label("enter item price");
    	    Label location_x = new Label("enter item x location"); Label location_y =
					new Label("enter item y location");
    	    Label length = new Label("enter item length"); Label width = new Label("enter item width");
    	    Label height = new Label("enter item height");
    	
    	    TextInputDialog popup1 = new TextInputDialog("Enter item name");
			TextInputDialog popup2 = new TextInputDialog("Enter item price");
    	    TextInputDialog popup3 = new TextInputDialog("Enter item x location");
			TextInputDialog popup4 = new TextInputDialog("Enter item y location");
    	    TextInputDialog popup5 = new TextInputDialog("Enter item length");
			TextInputDialog popup6 = new TextInputDialog("Enter item width");
    	    TextInputDialog popup7 = new TextInputDialog("Enter item height");
    	   	
    	    popup1.setHeaderText(selected_item); popup2.setHeaderText(selected_item);    	
    	    popup3.setHeaderText(selected_item); popup4.setHeaderText(selected_item);   	
    	    popup5.setHeaderText(selected_item); popup6.setHeaderText(selected_item);    	
    	    popup7.setHeaderText(selected_item); popup1.showAndWait();    	
    	    popup2.showAndWait(); popup3.showAndWait(); popup4.showAndWait();    	
    	    popup5.showAndWait(); popup6.showAndWait(); popup7.showAndWait();
    	    	
    	    name.setText(popup1.getEditor().getText()); price.setText(popup2.getEditor().getText());   	
    	    location_x.setText(popup3.getEditor().getText()); location_y.setText(popup4.getEditor().getText());    	
    	    length.setText(popup5.getEditor().getText()); width.setText(popup6.getEditor().getText());   	
    	    height.setText(popup7.getEditor().getText());
    	   	
    	    String provided_name = name.getText();
    	
    	    int provided_price = Integer.parseInt(price.getText());
			int provided_x = Integer.parseInt(location_x.getText());
    	    int provided_y = Integer.parseInt(location_y.getText());
			int provided_len = Integer.parseInt(length.getText());
    	    int provided_wid = Integer.parseInt(width.getText());
			int provided_height = Integer.parseInt(height.getText());
    	   	
    	    if (provided_price < 0){
     		
    	    	Alert negativePrice = new Alert(AlertType.NONE); negativePrice.setAlertType(AlertType.INFORMATION);     		
    	    	negativePrice.setHeaderText("item : "+ provided_name +" price was set to a negative number");		
    	    	negativePrice.show(); }
    	
    	    if (provided_len < 0||provided_wid < 0||provided_height < 0) {
     		
    	    	Alert negativeSize = new Alert(AlertType.NONE); negativeSize.setAlertType(AlertType.INFORMATION);     		
    	    	negativeSize.setHeaderText("item : "+ provided_name +" can not have negative size");     		
    	    	negativeSize.show();  }   	
    	
    	    if (selected_item.equals("root")) {
    		
    	    	farm.add(new itemContainer(provided_name, provided_price, provided_x, provided_y,
						provided_len, provided_wid, provided_height));
    	    	int added_index = farm.size() - 1;
				selectItem().getChildren().add(new TreeItem<>(farm.get(added_index).get_Name()));
    	    	drawVisualization(farm.get(added_index));
    	    	    	
    	    } else {
    	   	
    	    	for (Component farm_item : farm) {
    	   	         	  
    	    		if (farm_item.get_Name().equals(selected_item)) { farm.indexOf(farm_item);
      		
    	    			if (farm_item instanceof itemContainer) {
      			
    	    				farm.add(new itemContainer(provided_name, provided_price, provided_x, provided_y,
									provided_len, provided_wid, provided_height));
    	    				int added_index = farm.size() - 1;
      			
    	    				((itemContainer) farm_item).add_item(farm.get(added_index));
							((itemContainer) farm_item).print_item_list();
      			
    	    				selectItem().getChildren().add(new TreeItem<>(farm.get(added_index).get_Name()));
    	    				drawVisualization(farm.get(added_index));
      			
    	    				break; } else {
      			
    	    				Alert notAnItem = new Alert(AlertType.NONE); notAnItem.setAlertType(AlertType.INFORMATION);      			
    	    				notAnItem.setHeaderText("You selected item : " +
									""+selected_item+" please select an item container to add an item container");
    	    				notAnItem.show();
      			      		
    	    			} } } } }
    	
    	catch(Exception e) { Alert error = new Alert(AlertType.NONE); 		   		 
   	    error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new"); error.show(); } }
    
    
     // ----------------------------------------------------------------------------------------------------------------
     @FXML public void rename() {
    	
    	 try {
    	
    		 String selected_item = selectItem().getValue();
    		 Label l = new Label("enter new name");    	   	
    		 TextInputDialog popup = new TextInputDialog("Enter new name ");    	
    		 
    		 popup.setHeaderText(selected_item); popup.showAndWait();    	
    		 l.setText(popup.getEditor().getText());
    		    	
    		 String name = (l.getText());
    	        
    		 for (Component farm_item : farm) {
     	           	
    			 if (farm_item.get_Name().equals(selected_item)) {
     		           		
    				 farm.indexOf(farm_item); farm_item.set_Name(name);		           			
    				 selectItem().setValue(name);   			   
        			     			   
     				 break; } } }
        
    	 catch(Exception e) { Alert error = new Alert(AlertType.NONE); 		   		 
    	 error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new"); error.show(); } }
    
    
     // ----------------------------------------------------------------------------------------------------------------
     @FXML public void changeCords() {
 	
    	 try {
 	
    		 String selected_item = selectItem().getValue();
    		 Label xCord = new Label("enter new X"); Label yCord = new Label("enter new Y");	
    		 TextInputDialog popup1 = new TextInputDialog("Enter location X");TextInputDialog popup2 =
					 new TextInputDialog("Enter location Y");
 	
    		 popup1.setHeaderText(selected_item); popup2.setHeaderText(selected_item); 	
    		 popup1.showAndWait(); popup2.showAndWait();
 	 	
    		 xCord.setText(popup1.getEditor().getText()); yCord.setText(popup2.getEditor().getText());		
 	
    		 int x = Integer.parseInt(xCord.getText());  int y = Integer.parseInt(yCord.getText());
 	    
    		 int found_index;
  	    
    		 for (Component farm_item : farm) {
  	        	
    			 if (farm_item.get_Name().equals(selected_item)) {  		   
     		
    				 found_index = farm.indexOf(farm_item);     		  		        		    		  			        			
    				 farm_item.set_Location_X(x); farm_item.set_Location_Y(y);
  			      			       			
    				 spawnedRegions.get(found_index).setLayoutX(x); spawnedRegions.get(found_index).setLayoutY(y);
     			 			    			    
    				 break; } } }
     
    	 catch(Exception e) { Alert error = new Alert(AlertType.NONE); 		   		 
    	 error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new"); error.show(); } }
    
        
     // ----------------------------------------------------------------------------------------------------------------
     @FXML public void changePrice() {
 	
    	 try {
 	
    		 String selected_item = selectItem().getValue();
    		 Label l = new Label("enter new price");	
    		 TextInputDialog popup = new TextInputDialog("Enter new price "); 	
    		 
    		 popup.setHeaderText(selected_item); popup.showAndWait(); 	
    		 l.setText(popup.getEditor().getText());
 		   	 	
    		 int price = Integer.parseInt(l.getText());
 	
    		 if (price < 0) {
 		
    			 Alert notAnItem = new Alert(AlertType.NONE);
						
    			 notAnItem.setAlertType(AlertType.INFORMATION);			
    			 notAnItem.setHeaderText("item : "+ selected_item +" was set to a negative number");			
    			 notAnItem.show(); }

    		 
    		 for (Component farm_item : farm) {
  	      	
    			 if (farm_item.get_Name().equals(selected_item)) {
     		
    				 farm.indexOf(farm_item); farm_item.set_Price(price);
     			  			     			    
    				 break; } } }
      	 
    	 catch(Exception e) { Alert error = new Alert(AlertType.NONE); 		   		 
    	 error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new"); error.show(); } }


    // Drone Animation Functions and Variables -------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @FXML private Button Simulator; @FXML private Button Actual_Drone;
    @FXML private RadioButton Visit; @FXML private RadioButton Scan;
    @FXML private ToggleGroup Drone_Action; @FXML private SVGPath sim;
           
    
    // When Buttons Are Pressed
    @FXML private void Drone_Simulated() throws
			InterruptedException, IOException { Drone_Radio_Simulated(); }
    @FXML private void Drone_Actual() throws
			InterruptedException, IOException { Drone_Radio_Physical(); }
       
    
    // -----------------------------------------------------------------------------------------------------------------
    private void Drone_Radio_Simulated() throws InterruptedException, IOException { 
    	    	
    	try {
    	
    		String selected_item = selectItem().getValue(); // store item as String
    		String selected_item_parent = selectItem().getParent().getValue();
		    	  		
    		if (this.Drone_Action.getSelectedToggle().equals(this.Visit)) {             		
    		
    			Drone_Methods.visit(sim, null, true, true, startX, startY,
						selected_item, selected_item_parent, farm);  } }
    		
    	catch(Exception ignored) {}
    	    	
    	finally { if (this.Drone_Action.getSelectedToggle().equals(this.Scan)) {           		
    		
    			Drone_Methods.scan(sim, null, true); } } }
               		  		    	    	
    		    		         
    // -----------------------------------------------------------------------------------------------------------------
    private void Drone_Radio_Physical() throws InterruptedException, IOException { 
    	   	  	
    	try {
    		   		   		
    		String selected_item = selectItem().getValue(); // store item as String
    		String selected_item_parent = selectItem().getParent().getValue();
    	    			
    		if (this.Drone_Action.getSelectedToggle().equals(this.Visit)) {
    			
    			TelloDrone tell = new TelloDrone();
    			Drone_Methods.visit(null, tell, false, true, startX, startY,
						selected_item, selected_item_parent, farm); } }
		
    	catch(Exception ignored) {}
    	   			
    	finally { if (this.Drone_Action.getSelectedToggle().equals(this.Scan)) {
    			
    			TelloDrone tell = new TelloDrone();
    			Drone_Methods.scan(null, tell, false); } } } }


