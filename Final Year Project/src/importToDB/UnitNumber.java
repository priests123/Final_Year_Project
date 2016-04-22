package importToDB;

import java.util.ArrayList;
import java.util.HashSet;

public class UnitNumber {
	public static void addUnitNumber(ArrayList<ArrayList<String>> rawRailDataFile){
		HashSet<String> uniqueUnitNumbersInFile = new HashSet<String>();
		uniqueUnitNumbersInFile.add(rawRailDataFile.get(0).get(0));
		
		ArrayList<String> allUnitNumbers = SQLDatabase.selectAllFromUnit("SELECT Unit_No FROM Unit");
		for(int b = 0; b < allUnitNumbers.size(); b++){
			if(uniqueUnitNumbersInFile.contains(allUnitNumbers.get(b))){
				uniqueUnitNumbersInFile.remove(allUnitNumbers.get(b));
			}
		}
		//if(uniqueUnitNumbersInFile.size() > 0){
			SQLDatabase.addNewUnitNumbers(uniqueUnitNumbersInFile, "INSERT INTO Unit (Unit_No) VALUES (?)");
		//} else {
		//	System.out.println("There were no new units to be added");
		//}
	}
}
