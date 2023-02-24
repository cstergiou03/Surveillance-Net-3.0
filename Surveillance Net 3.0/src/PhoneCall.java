
public class PhoneCall extends Communication {
	
	protected int duration;

	public PhoneCall(String number, String commNumber, int day, int month, int year, int duration) {
		super(number, commNumber, day, month, year);   
		this.duration = duration;
	}

	
	public void printInfo() {
		System.out.println("This phone call has the following info");
		super.printInfo();
		System.out.println("Duration: " + duration);
	}


	public int getDuration() {
		return duration;
	}

	public String getMessage() {
		return null;
	}


	
	


	
	
	
} 
