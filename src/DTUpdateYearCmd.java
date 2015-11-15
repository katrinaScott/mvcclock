package src;

public class DTUpdateYearCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	private int curYear, prevYear;
	
	public DTUpdateYearCmd(DigitalClockModel model, String year) {
		
		this.model = model;
		this.curYear = Integer.parseInt(year);
		
	} // end of constructor

	public void Execute() {
		
		//model.incrementYear();
		this.prevYear = model.getYear();
		model.setYear(this.curYear);

	} // end of method Execute

	public void UnExecute() {
		
		model.setYear(this.prevYear);
		//model.decrementYear();

	} // end of method UnExecute

} // end of class DTUpdateYearCmd
