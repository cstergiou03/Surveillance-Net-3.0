
public class SMS extends Communication {
	
	protected String message;

	public SMS(String number, String commNumber, int day, int month, int year, String message) {
		super(number, commNumber, day, month, year);  
		this.message = message;
	}
	
	public void printInfo() {
		System.out.println("This SMS has the following info");
		super.printInfo();
		System.out.println("Text: " + message);
	}

	public String getMessage() {
		return message;
	}
	
	public int getDuration() {
		return 0;
	}

	
	

}
