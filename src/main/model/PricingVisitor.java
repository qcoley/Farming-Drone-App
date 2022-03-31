package main.model;


//---------------------------------------------------------------------------------------------------------------------------------------------------------------
public class PricingVisitor implements Visitor {
	
	int total = 0;
	  
	public PricingVisitor() {}
	
	public int getTotalPrice() {
		
		int returned_total = total;
		total = 0;
		return returned_total;  }
	  	  
	  
	public void visit(Component comp){
		
		total = total + comp.get_Price();
		
		if (comp instanceof itemContainer) {
			
			for (Component farm_item : ((itemContainer) comp).get_Item_List()) {
			
				if (farm_item instanceof itemContainer) {
				
					visit(farm_item);
				
				} else {
					
					total = total + farm_item.get_Price();
				
				} } } } }
