package src;

public abstract class Tool {
	
	String name;
	
	public Tool(String name) {
		this.name = name;
	}
	
	public abstract void execute();

}