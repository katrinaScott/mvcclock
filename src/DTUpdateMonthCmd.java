package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DTUpdateMonthCmd implements DigitalClockCmd {
	
	private DigitalClockModel model;
	private List<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", 
			"April", "May", "June", "July", "August", "September", "October", "November", "December"));
	private int curMonth, prevMonth;
	
	public DTUpdateMonthCmd(DigitalClockModel model, String month) {
		
		this.model = model;
		this.curMonth = months.indexOf(month);
		
	} // end of constructor

	public void Execute() {
		
		//model.incrementMonth();
		this.prevMonth = model.getMonth();
		model.setMonth(this.curMonth);

	} // end of method Execute
	
	public void UnExecute() {
		
		model.setMonth(this.prevMonth);
		//model.decrementMonth();

	} // end of method UnExecute
 
} // end of class DTUpdateMonthCmd
