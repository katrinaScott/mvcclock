
public class DTUpdateMonthCmd implements DigitalClockCmd {
	
	private DigitalClockModel model;
	
	public DTUpdateMonthCmd(DigitalClockModel model) {
		
		this.model = model;
		
	} // end of constructor

	public void Execute() {
		
		model.incrementMonth();

	} // end of method Execute

	public void UnExecute() {
		
		model.decrementMonth();

	} // end of method UnExecute
 
} // end of class DTUpdateMonthCmd
