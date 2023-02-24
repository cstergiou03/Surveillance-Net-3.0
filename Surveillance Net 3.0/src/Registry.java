 import java.util.ArrayList;

public class Registry { 
	
	ArrayList<Suspect> suspects = new ArrayList<>();
	ArrayList<Communication> communication = new ArrayList<>();
	
	public void addSuspect(Suspect aSuspect) {
		suspects.add(aSuspect);
	}
	
	public void addCommunication(Communication aCommunication) {
		
		communication.add(aCommunication);
		
		int count;
		Suspect sus1 = null, sus2 = null;
		int i;
		boolean flag1 = false;
		boolean flag2 = false;
		
		for(Suspect suspects: suspects) {
			count = suspects.getPhoneNumbers().size();
			for(i = 0;i<count;i++) {
				if(suspects.PhoneNumbers.get(i).equals(aCommunication.getNumber1())) {
					flag1 = true;
					sus1 = suspects;
					break;
				}
			}
			if(flag1 == true)
				break;
		}
		
		for(Suspect suspects: suspects) {
			count = suspects.getPhoneNumbers().size();
			for(i = 0;i<count;i++) {
				if(suspects.PhoneNumbers.get(i).equals(aCommunication.getNumber2())) {
					flag1 = true;
					sus2 = suspects;
					break;
				}
			}
			if(flag2 == true)
				break;
		}
		
		sus1.addRelatedSuspects(sus2);
		sus2.addRelatedSuspects(sus1);
	}
	
	public Suspect getSuspectWithMostPartners() {
		
		int max = 0;
		Suspect mostPartners = null;
		
		for(Suspect suspects: suspects) {
			if(max<suspects.relatedSuspects.size());
				mostPartners = suspects;
		}
		
		return mostPartners;
		
	}
	
	
	public PhoneCall getLongestPhoneCallBetween(String number1, String number2) {
		
		int maxDuration = 0;
		PhoneCall call = null;
		
		for(Communication comms: communication) {
			if(comms.getNumber1().equals(number1) && comms.getNumber2().equals(number2)) {
				if(maxDuration<comms.getDuration()) {
					maxDuration = comms.getDuration();
					call = new PhoneCall(comms.getNumber1(), comms.getNumber2(), comms.getDay(), comms.getMonth(), comms.getYear(), comms.getDuration());
				}
			}
		}
		return call;
	}
	
	public ArrayList<SMS> getMessagesBetween(String number1, String number2){
		
		String w1 = "Bomb", w2 = "Attack", w3 = "Explosives", w4 = "Gun";
		ArrayList<SMS> messages = new ArrayList<>();
		boolean flag = false;
		
		for(Communication comms: communication) {
			if(comms.getNumber1().equals(number1) && comms.getNumber2().equals(number2)) {
				if((comms.getMessage().indexOf(w1) != -1) || (comms.getMessage().indexOf(w2) != -1) || (comms.getMessage().indexOf(w3) != -1) || (comms.getMessage().indexOf(w4) != -1)) {
					for(SMS message: messages) {
						if(message.equals(null)) 
							flag = false;
						else if(!message.getMessage().equals(comms.getMessage()))
							flag = false;
						else
							flag = true;
					}
					if(flag == false) {
						messages.add(new SMS(comms.getNumber1(), comms.getNumber2(), comms.getDay(), comms.getMonth(), comms.getYear(), comms.getMessage()));			   
					}
				}
			}
			
		}
		
		return messages;
		
	}
	
	public void printSuspectsFromCountry(String country) {
		
		System.out.print("Suspects coming from " + country + ": \n");
		for(Suspect sus: suspects) {
			if(sus.getCountry().equals(country)) {
				System.out.println(sus.getName() + " " + "(" + sus.getCodeName() + ")");
			
			}
		}
		
	}
}
