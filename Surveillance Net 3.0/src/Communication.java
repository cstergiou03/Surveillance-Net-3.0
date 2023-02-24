
public abstract class Communication{
	//extend τις κλασεις PhoneCall και SMS με την κλαση Communication 
	
	protected String number1;
	protected String number2;
	protected int day;
	protected int month;
	protected int year;
	
	
	public Communication(String number1, String number2, int day, int month, int year) {
		super();
		this.number1 = number1;
		this.number2 = number2; 
		this.day = day;
		this.month = month; 
		this.year = year; 
	}
	
	public void printInfo() {
		System.out.println("Between " + number1 + " --- " + number2);
		System.out.println("on " + year + "/" + month + "/" + day);
	}

	public String getNumber1() {
		return number1;
	}

	public String getNumber2() {
		return number2;
	}	
	
	//χρήση abstract για να παρουμε απο τις κλασεις sms και PhoneCall το message και το Duration αντιστοιχα
	public abstract int getDuration();
	
	public abstract String getMessage();

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
}
