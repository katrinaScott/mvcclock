//import java.util.ArrayList;

// i did this
import java.util.Stack;

/**
 * For storing a history of all commands that have been made
 */
class CommandStore {
	/*ArrayList<DigitalClockCmd> _cmdList;
	int _indexCurCmd;
	
	public CommandStore() {
		_cmdList = new ArrayList<DigitalClockCmd>();
		_indexCurCmd = -1;
	}
	public void Execute(DigitalClockCmd cmd) {
		// execute the command, add cmd to list (and clear out old cmd if needed...)
		// TBD
	}
	public void Undo() {
		// TBD
	}
	public void Redo() {
		// TBD
	}*/
	
	Stack<DigitalClockCmd> undos;
	Stack<DigitalClockCmd> redos;
	
	public CommandStore() {
		
		undos = new Stack<DigitalClockCmd>();
		redos = new Stack<DigitalClockCmd>();
		
	} // end of constructor
	
	public void Execute(DigitalClockCmd command) {
		
		command.Execute();
		undos.push(command);
		
		if (!redos.empty())
			redos.clear();
		
	} // end of method Execute
	
	public void Undo() {
		
		if (!undos.empty()) {
			DigitalClockCmd undid = undos.pop();
			undid.UnExecute();
			redos.push(undid);
		} // end if
		
	} // end of method Undo
	
	public void Redo() {
		
		if (!redos.empty()) {
			DigitalClockCmd redid = redos.pop();
			redid.Execute();
			undos.push(redid);
		} // end if
		
	} // end of method Redo

} // end of class CommandStore
