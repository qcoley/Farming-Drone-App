package main.model;


import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import main.jdrone.physical.MultiRotorDrone;



public class Adapter extends MultiRotorDrone {

		
	SVGPath drone;
	
	public Adapter(SVGPath drone) { this.drone = drone; }
	  
		 		
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void takeoff() throws IOException {
		
		System.out.println("Simulated Drone Takeoff"); } 

	
	@Override
	public void land() throws IOException {
        
		System.out.println("Simulated Drone Land");  } 

	
	@Override
	public void increaseAltitude(int up) throws IOException {

		System.out.println("Simulated Drone Increasing Altitude"); } 

	
	@Override
	public void decreaseAltitude(int down) throws IOException {

		System.out.println("Simulated Drone Decreasing Altitude"); } 

	
	@Override
	public void flyForward(int front) throws IOException {

		System.out.println("Simulated Drone Flying Forward"); } 

	
	@Override
	public void flyLeft(int left) throws IOException {
		
        System.out.println("Simulated Drone Flying Left"); } 
		
	
	@Override
	public void flyRight(int right) throws IOException {

		System.out.println("Simulated Drone Flying Left"); } 

	
    @Override
	public void turnCW(int degrees) throws IOException {
    	
		RotateTransition rt = new RotateTransition(); rt.setByAngle(degrees); rt.setDuration(Duration.millis(1000)); rt.setNode(drone);		
		SequentialTransition s = new SequentialTransition(rt); s.play(); } 

	
	@Override
	public void turnCCW(int degrees) throws IOException {
		
		RotateTransition rt = new RotateTransition(); rt.setByAngle(degrees); rt.setDuration(Duration.millis(1000)); rt.setNode(drone);		
		SequentialTransition s = new SequentialTransition(rt); s.play(); } 

	
	@Override
	public int getFlightTime() throws IOException {

		System.out.println("Simulated Drone Getting Flight Time"); return 0; }
	

	@Override
	public int getHeight() throws IOException {

		System.out.println("Simulated Drone Getting Height");  return 0; }

	
	@Override
	public int getAttitudePitch() throws IOException {

		System.out.println("Simulated Drone Getting AttitudePitch");  return 0; }

	
	@Override
	public int getAttitudeRoll() throws IOException {

		System.out.println("Simulated Drone Getting AttitudeRoll");  return 0; }
	
	
	@Override
	public int getAttitudeYaw() throws IOException {

		System.out.println("Simulated Drone Getting AttitudeYaw");  return 0; }

	
	@Override
	public double getAccelerationX() throws IOException {

		System.out.println("Simulated Drone Getting AccelerationX");  return 0; }

	
	@Override
	public double getAccelerationY() throws IOException {

		System.out.println("Simulated Drone Getting AccelerationY");  return 0; }

	
	@Override
	public double getAccelerationZ() throws IOException {

		System.out.println("Simulated Drone Getting AccelerationZ");  return 0; }

	
	@Override
	public int getTOF() throws IOException {

		System.out.println("Simulated Drone Getting TOF");  return 0; }

	
	@Override
	public void flyBackward(int back) throws IOException { }

	
	@Override
	public void flip(String direction) throws IOException { }

	
	@Override
	public void hoverInPlace(int seconds) throws InterruptedException, IOException {

		System.out.println("Simulated Drone Hovering"); } 

	
	@Override
	public void setSpeed(int speed) throws IOException {

		System.out.println("Simulated Drone Setting Speed");  }

	
	@Override
	public double getSpeed() throws NumberFormatException, IOException {

		System.out.println("Simulated Drone Getting Speed");  return 0; }


	@Override
	public int getBattery() throws NumberFormatException, IOException {

		System.out.println("Simulated Drone Getting Battery");  return 0; }
	
	
	public void activateSDK() throws IOException { 
	
	    System.out.println("Simulated Drone Activating SDK");  } 
	
	
	public void gotoXYZ(int x, int y, int z, int speed) throws IOException { 
		
		TranslateTransition tt1 = new TranslateTransition(); tt1.setToX(x); tt1.setToY(y); tt1.setToZ(z); tt1.setDuration(Duration.millis(1000)); 
		tt1.setNode(drone);		
		RotateTransition rt1 = new RotateTransition(); rt1.setByAngle(360); rt1.setDuration(Duration.millis(2000)); rt1.setNode(drone);		
		TranslateTransition tt2 = new TranslateTransition(); tt2.setToX(0); tt2.setToY(0); tt2.setToZ(0); tt2.setDuration(Duration.millis(1000)); 
		tt2.setNode(drone);		
		PauseTransition pt1 = new PauseTransition(); pt1.setDuration(Duration.millis(1000)); 
						
		SequentialTransition st = new SequentialTransition(tt1, rt1, pt1, tt2); st.play(); }  
	
		
	public void gotoXY(int x, int y, int speed) throws IOException { 
			
		TranslateTransition s1 = new TranslateTransition(); s1.setToX(-165); s1.setToY(-140); s1.setDuration(Duration.millis(1000)); 
	  	s1.setNode(drone); TranslateTransition s2 = new TranslateTransition(); s2.setToX(370); s2.setToY(-140); s2.setDuration(Duration.millis(1000)); 	  		  	
	  	s2.setNode(drone);	  		  	
	  	// --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt1 = new RotateTransition(); rt1.setByAngle(90); rt1.setDuration(Duration.millis(500)); rt1.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  		  		  		  		  		  	
	  	TranslateTransition s3 = new TranslateTransition(); s3.setToX(370); s3.setToY(-30); s3.setDuration(Duration.millis(500));
	  	s3.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt2 = new RotateTransition(); rt2.setByAngle(90); rt2.setDuration(Duration.millis(500)); rt2.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s4 = new TranslateTransition(); s4.setToX(-165); s4.setToY(-30); s4.setDuration(Duration.millis(1000)); 	  	
	  	s4.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt3 = new RotateTransition(); rt3.setByAngle(-90); rt3.setDuration(Duration.millis(500)); rt3.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------		  		  		  	
	  	TranslateTransition s5 = new TranslateTransition(); s5.setToX(-165); s5.setToY(80); s5.setDuration(Duration.millis(500)); 
	  	s5.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt4 = new RotateTransition(); rt4.setByAngle(-90); rt4.setDuration(Duration.millis(500)); rt4.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s6 = new TranslateTransition(); s6.setToX(370); s6.setToY(80); s6.setDuration(Duration.millis(1000)); 
	  	s6.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt5 = new RotateTransition(); rt5.setByAngle(90); rt5.setDuration(Duration.millis(500)); rt5.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s7 = new TranslateTransition(); s7.setToX(370); s7.setToY(190); s7.setDuration(Duration.millis(500)); 
	  	s7.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt6 = new RotateTransition(); rt6.setByAngle(90); rt6.setDuration(Duration.millis(500)); rt6.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s8 = new TranslateTransition(); s8.setToX(-165); s8.setToY(190); s8.setDuration(Duration.millis(1000)); 
	  	s8.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt7 = new RotateTransition(); rt7.setByAngle(-90); rt7.setDuration(Duration.millis(500)); rt7.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s9 = new TranslateTransition(); s9.setToX(-165); s9.setToY(300); s9.setDuration(Duration.millis(500)); 
	  	s9.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt8 = new RotateTransition(); rt8.setByAngle(-90); rt8.setDuration(Duration.millis(500)); rt8.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s10 = new TranslateTransition(); s10.setToX(370); s10.setToY(300); s10.setDuration(Duration.millis(1000)); 
	  	s10.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt9 = new RotateTransition(); rt9.setByAngle(90); rt9.setDuration(Duration.millis(500)); rt9.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s11 = new TranslateTransition(); s11.setToX(370); s11.setToY(410); s11.setDuration(Duration.millis(500)); 
	  	s11.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt10 = new RotateTransition(); rt10.setByAngle(90); rt10.setDuration(Duration.millis(500)); rt10.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s12 = new TranslateTransition(); s12.setToX(-165); s12.setToY(410); s12.setDuration(Duration.millis(1000)); 
	  	s12.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt11 = new RotateTransition(); rt11.setByAngle(-90); rt11.setDuration(Duration.millis(500)); rt11.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s13 = new TranslateTransition(); s13.setToX(-165); s13.setToY(520); s13.setDuration(Duration.millis(500)); 
	  	s13.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt12 = new RotateTransition(); rt12.setByAngle(-90); rt12.setDuration(Duration.millis(500)); rt12.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------	  		  	
	  	TranslateTransition s14 = new TranslateTransition(); s14.setToX(370); s14.setToY(520); s14.setDuration(Duration.millis(1000)); 
	  	s14.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt13 = new RotateTransition(); rt13.setByAngle(90); rt13.setDuration(Duration.millis(500)); rt13.setNode(drone);
	    // -------------------------------------------------------------------------------------------------------------------------- 	  		  	
	  	TranslateTransition s15 = new TranslateTransition(); s15.setToX(370); s15.setToY(595); s15.setDuration(Duration.millis(500)); 
	  	s15.setNode(drone);	  		  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt14 = new RotateTransition(); rt14.setByAngle(90); rt14.setDuration(Duration.millis(500)); rt14.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------
	  	TranslateTransition s16 = new TranslateTransition(); s16.setToX(-165); s16.setToY(595); s16.setDuration(Duration.millis(1000)); 
	  	s16.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt15 = new RotateTransition(); rt15.setByAngle(90); rt15.setDuration(Duration.millis(500)); rt15.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------  	
	  	TranslateTransition s17 = new TranslateTransition(); s17.setToX(0); s17.setToY(0); s17.setDuration(Duration.millis(1500)); 
	  	s17.setNode(drone);	  	
	    // --------------------------------------------------------------------------------------------------------------------------
	  	RotateTransition rt16 = new RotateTransition(); rt16.setByAngle(90); rt16.setDuration(Duration.millis(500)); rt16.setNode(drone);
	    // --------------------------------------------------------------------------------------------------------------------------
	  		  		  		  	
	  	// put all transitions in SequentialTransition ---------------------------------------------------------------------------------------------------------
	  	SequentialTransition s = new SequentialTransition(s1, s2, rt1 ,s3, rt2 ,s4, rt3 ,s5, rt4, s6, rt5 ,s7, rt6, s8, rt7 ,s9, rt8, s10, 
	  			
	  			rt9 ,s11, rt10, s12, rt11 ,s13, rt12, s14, rt13, s15, rt14, s16, rt15, s17, rt16); s.play(); } 
	
	
	public void streamOn() throws IOException { 
		
		
		System.out.println("Simulated Drone Stream On"); }  
	
	
	public void streamOff() throws IOException { 
	
		
		System.out.println("Simulated Drone Stream Off"); } 
	
	
	public void end() throws IOException, InterruptedException {
		

		System.out.println("Simulated Drone End"); } }
	
	
	