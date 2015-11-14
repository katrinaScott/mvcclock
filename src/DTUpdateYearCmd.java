package src;

public class DTUpdateYearCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	private int yearNum;
	
	public DTUpdateYearCmd(DigitalClockModel model, String year) {
		
		this.model = model;
		this.yearNum = Integer.parseInt(year);
		
	} // end of constructor

	public void Execute() {
		
		//model.incrementYear();
		model.setYear(this.yearNum);

	} // end of method Execute

	public void UnExecute() {
		
		//model.decrementYear();

	} // end of method UnExecute

} // end of class DTUpdateYearCmd
