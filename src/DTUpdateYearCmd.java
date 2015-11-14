package src;

public class DTUpdateYearCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	
	public DTUpdateYearCmd(DigitalClockModel model) {
		
		this.model = model;
		
	} // end of constructor

	public void Execute() {
		
		model.incrementYear();

	} // end of method Execute

	public void UnExecute() {
		
		model.decrementYear();

	} // end of method UnExecute

} // end of class DTUpdateYearCmd
