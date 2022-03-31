package main.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.SVGPath;
import main.jdrone.physical.tello.TelloDrone;



// Implemented by Physical_Adpater.java and DashboardController.java -----------------------------------------------------------------------
public class Drone_Methods {
	
					
	public static void visit(SVGPath simu, TelloDrone drone_phys, boolean simulated, boolean move, int location_x, int location_y, String selected_item, String selected_item_parent,
    		ArrayList<Component> farm) throws InterruptedException, IOException {
    	
		
		Adapter drone_sim = new Adapter(simu);
				  					
		if ( simulated == true ) { try {
	   			       		    		
        		for (Component farm_item : farm) {
    	      	  			
        			if (farm_item.get_Name().equals(selected_item)) {
    		   				      		    		      		   				
        				if (farm_item instanceof Item) {   			
					
        					int item_location_X = (int) (farm_item.get_Location_X()-175);   			  					
        					int item_location_Y = (int) (farm_item.get_Location_Y()-150);
    																	
        					drone_sim.activateSDK(); drone_sim.takeoff();							   										
        					drone_sim.gotoXYZ(item_location_X, item_location_Y, 30, 10); 
        					        					        			    	
        					break;
    		      		       				
        				// is a container				
        				} else {
    										    					   					
        					int item_location_X = (int) (((itemContainer)farm_item).get_Location_X()-175);   			  					
        					int item_location_Y = (int) (((itemContainer)farm_item).get_Location_Y()-150);					
    					
        					drone_sim.activateSDK(); drone_sim.takeoff();																
        					drone_sim.gotoXYZ(item_location_X, item_location_Y, 30, 10);
        					       					       				    					    										       		    								    										
        					break;  } } } 
    			
    		
        		// after drone is finished going to wherever		
        		drone_sim.land(); drone_sim.end(); 
    							
    	
        	} catch(Exception e) { Alert error = new Alert(AlertType.NONE);
    		
        		error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new");	error.show(); } } 
		
		
		// physical drone starts from (0,0) or top left of farm. moves straight to top right of farm.	
		else { try {
    	   			        						    		        		
					
			for (Component farm_item : farm) {
    	      	  			        									
				if (farm_item.get_Name().equals(selected_item)) {
    		   			   		      		   				        										
					if (farm_item instanceof Item) {
    								       													
						int item_location_X = (int) (farm_item.get_Location_X()*1.2);   			  					        													
						int item_location_Y = (int) (farm_item.get_Location_Y()*1.2);
    																	       													
						drone_phys.activateSDK(); drone_phys.hoverInPlace(10); drone_phys.takeoff();							   										        					        					
								
						TimeUnit.SECONDS.sleep(1); drone_phys.gotoXYZ(item_location_X, item_location_Y, 30, 20); 						
						TimeUnit.SECONDS.sleep(1); drone_phys.turnCW(360);						
						TimeUnit.SECONDS.sleep(1); drone_phys.gotoXYZ(item_location_X*-1, item_location_Y*-1, 30, 20); 	        					
								
						break;
    		      		       				       				
							
					// is a container				     						
					} else {
    										   					        					
						int item_location_X = (int) (((itemContainer)farm_item).get_Location_X()*1.2);   			  					        													
						int item_location_Y = (int) (((itemContainer)farm_item).get_Location_Y()*1.2);					
    					       					
						drone_phys.activateSDK(); drone_phys.hoverInPlace(10); drone_phys.takeoff();	
        					        					
						TimeUnit.SECONDS.sleep(1); drone_phys.gotoXYZ(item_location_X, item_location_Y, 30, 20); 
						TimeUnit.SECONDS.sleep(1); drone_phys.turnCW(360);
						TimeUnit.SECONDS.sleep(1); drone_phys.gotoXYZ(item_location_X*-1, item_location_Y*-1, 30, 20);
    					    										       		    								    										       					
						break;  } } } 
    			  		       		
					
			// after drone is finished going to wherever		       		
			drone_phys.land(); drone_phys.end(); 
    							    	
        			
		} catch(Exception e) { Alert error = new Alert(AlertType.NONE);
		
		error.setAlertType(AlertType.ERROR); error.setHeaderText(e+" happened, try something new");	error.show(); } } }
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
    public static void scan(SVGPath simu, TelloDrone drone_phys, boolean simulated) throws InterruptedException, IOException { 
    	    	    	    	
    				   						
		if ( simulated == true ) { 			
						
			Adapter drone_sim = new Adapter(simu); drone_sim.gotoXY(0, 0, 0);  }
														
		else {
			
			// physical drone starts from (0,0) or top left of farm. moves straight to top right of farm. sleep between commands to give drone time to process.			
	        drone_phys.activateSDK(); drone_phys.streamOn();	        
	        drone_phys.hoverInPlace(10); drone_phys.takeoff(); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.flyForward((int)(124)); TimeUnit.SECONDS.sleep(1);	        
	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(172*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //40 --------------	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1); 	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //60 -------------	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //80 --------------	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1);//100 ------------	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //120 -------------	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //140 ------------	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //160 -------------	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //180 ------------	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //200 -------------	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //220 ------------	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //240 -------------	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(20*1.2)); TimeUnit.SECONDS.sleep(1); //260 ------------	        
	        drone_phys.turnCCW(90); drone_phys.flyForward((int)(344*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCCW(180); drone_phys.flyForward((int)(182*1.2)); TimeUnit.SECONDS.sleep(1);	        
	        drone_phys.turnCW(90); drone_phys.flyForward((int)(124*1.2)); TimeUnit.SECONDS.sleep(1);	                        
	        
	        drone_phys.land(); drone_phys.streamOff(); drone_phys.end(); 
											
		} } }

