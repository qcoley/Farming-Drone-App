package main.model;


//---------------------------------------------------------------------------------------------------------------------------------------------------------------
public class MarketvalueVisitor implements Visitor {
	
	int total = 0;
	
	public MarketvalueVisitor() {}
	
	public int getTotalMarketvalue() {
		
		int returned_total = total; total = 0; return returned_total;  }
	

	public void visit(Component comp) {
		
		if (comp instanceof Item) { total = total + ((Item) comp).get_Marketvalue();
		
		} else { for (Component farm_item : ((itemContainer) comp).get_Item_List()) {
				
				visit(farm_item); } } } }

