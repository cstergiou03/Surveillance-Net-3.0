import java.util.ArrayList;

public class Suspect {
	
	private String name;
	private String codeName;
	private String country; 
	private String city;
	
	ArrayList<String> PhoneNumbers = new ArrayList<>();
	ArrayList<Suspect> relatedSuspects = new ArrayList<>();
	ArrayList<Suspect> suggestedPartners = new ArrayList<>();
	
	public Suspect(String name, String fakeName, String country, String city) {
		super();
		this.name = name;
		this.codeName = fakeName;
		this.country = country;
		this.city = city; 
	}
	
	
	public ArrayList<Suspect> suggestedPartners() {
		
		ArrayList<Suspect> temp = new ArrayList<>();
		
		for(Suspect partners: relatedSuspects) {
			for(Suspect suggested: partners.relatedSuspects) {
				if(!suggested.name.equals(name))
					temp.add(suggested);
			}
		}
		
		for(Suspect suspects: relatedSuspects) {
			for(int i = 0;i<temp.size();i++) {
				if(suspects.equals(temp.get(i))) {
					temp.remove(i);
					
				}
			}
		}
		
		suggestedPartners.addAll(temp);
		

		
		return suggestedPartners;
		
	}
	
	public void addNumber(String aNumber) {
		PhoneNumbers.add(aNumber);
	}
	
	public void addRelatedSuspects(Suspect aSuspect) {
		
		boolean flag = true;
		
		/*for(Suspect suspects: relatedSuspects) {
			if(suspects.equals(aSuspect))
				flag = false;
				break;
		}*/
		
		int count = 0;
		for(Suspect suspects: relatedSuspects) {
			if(aSuspect.name.equals(suspects.name)) {
				count++;
			}
		}
		
		if(count == 0) {
			relatedSuspects.add(aSuspect);
		}
		
	}
	
	public boolean isConnectedTo(Suspect aSuspect) {
		
		for(Suspect suspects: relatedSuspects) {
			if(suspects.equals(aSuspect)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) {
		
		ArrayList<Suspect> commonSuspects = new ArrayList<>();
		boolean flag = false;
		
		for(Suspect sus1: relatedSuspects) {
			for(Suspect sus2: aSuspect.relatedSuspects) {
				if(sus1.name.equals(sus2.name)){
					for(Suspect sus: commonSuspects) {
						if(sus.name.equals(null))
							flag = false;
						else if(!sus.name.equals(sus1.name))
							flag = false;
						else
							flag = true;
					}
					
					if(flag == false)
						commonSuspects.add(new Suspect(sus1.name, sus1.codeName, sus1.country, sus1.city));		
				}
			}
		}
		
		return commonSuspects;
	}
	
	public void printCommonPartners() {
		
		for(Suspect sus: relatedSuspects) {
			if(sus.country.equals(country)) 
				System.out.println(sus.name + ", " + sus.codeName + ", *");
			else
				System.out.println(sus.name + ", " + sus.codeName);
		}
		
	}
	
	public String getName() {
		return name;
	}

	public String getCodeName() {
		return codeName;
	}

	public ArrayList<String> getPhoneNumbers() {
		return PhoneNumbers;
	}

	public String getCountry() {
		return country;
	}


	public String getCity() {
		return city;
	}
	
	

	
	
	
	
}
