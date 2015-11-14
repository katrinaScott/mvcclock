package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DTUpdateDayCmd implements DigitalClockCmd {

private DigitalClockModel model;
private int dayNum;
	
	public DTUpdateDayCmd(DigitalClockModel model, String day) {
		
		this.model = model;
		this.dayNum = Integer.parseInt(day);
		
	} // end of constructor
	
	public void Execute() {
		
		//model.incrementDay();
		model.setDay(this.dayNum);
		
	} // end of method Execute
	
	public void UnExecute() {
		
		//model.decrementDay();
		
	} // end of method UnExecute

} // end of class DTUpdateDayCmd
