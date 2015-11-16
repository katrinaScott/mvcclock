package src;

import java.util.Stack;

/**
 * For storing a history of all commands that have been made
 */

class CommandStore {
	
	Stack<DigitalClockCmd> undoCmds;
	Stack<DigitalClockCmd> redoCmds;
	
	public CommandStore() {
		
		undoCmds = new Stack<DigitalClockCmd>();
		redoCmds = new Stack<DigitalClockCmd>();
		
	} // end of constructor
	
	public void Execute(DigitalClockCmd command) {
		
		command.Execute();
		undoCmds.push(command);
		
		if (!redoCmds.empty())
			redoCmds.clear();
		
	} // end of method Execute
	
	public void Undo() {
		
		if (!undoCmds.empty()) {
			
			DigitalClockCmd undid = undoCmds.pop();
			undid.UnExecute();
			redoCmds.push(undid);
			
		} // end if
		
	} // end of method Undo
	
	public void Redo() {
		
		if (!redoCmds.empty()) {
			
			DigitalClockCmd redid = redoCmds.pop();
			redid.Execute();
			undoCmds.push(redid);
			
		} // end if
		
	} // end of method Redo

} // end of class CommandStore
