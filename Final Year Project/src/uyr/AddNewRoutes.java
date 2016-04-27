package uyr;

import java.util.*;

public class AddNewRoutes {

	//First checks the routes  in the file being imported, then only adds new routes to the database
	//There is a lot of checking of the routes, making sure that if routes are the same, like A to B
	//the stations in between may be different, then via needs to be added to the name to ensure the 
	//routes can be differentiated 
	public static ArrayList<String> addRoutes(ArrayList<ArrayList<String>> rawRailDataFile){	
		ArrayList<ArrayList<String>> uniqueRoutes = new ArrayList<ArrayList<String>>();
		for(int a = 0; a < rawRailDataFile.size(); a++){
			ArrayList<String> currRoute = new ArrayList<String>();
			if((rawRailDataFile.get(a).size() == 5) && (rawRailDataFile.get(a).get(4).equals("FIRST"))){
				for(int b = a; b < rawRailDataFile.size(); b+=2){
					if((rawRailDataFile.get(b+1).size() == 5) && (rawRailDataFile.get(b+1).get(4).equals("LAST"))){
						currRoute.add(rawRailDataFile.get(b).get(3));
						currRoute.add(rawRailDataFile.get(b+1).get(3));
						break;
					}
					currRoute.add(rawRailDataFile.get(b).get(3));
				}
			}
			if((!(uniqueRoutes.contains(currRoute))) && currRoute.size() > 0){
				uniqueRoutes.add(currRoute);
			}
		}
		ArrayList<String> uniqueRoutesInFile = new ArrayList<String>();
		int countsize = uniqueRoutes.size();
		for(int c = 0; c < countsize; c++){
			ArrayList<ArrayList<String>> currUniqueRoute = new ArrayList<ArrayList<String>>();
			if(uniqueRoutes.size() == 0) break;
			String routeStart = uniqueRoutes.get(0).get(0);
			String routeEnd = uniqueRoutes.get(0).get(uniqueRoutes.get(0).size()-1);
			for(int d = 0; d < uniqueRoutes.size(); d++){
				if((routeStart.equals(uniqueRoutes.get(d).get(0))) && 
				   (routeEnd.equals(uniqueRoutes.get(d).get(uniqueRoutes.get(d).size()-1)))){
					currUniqueRoute.add(uniqueRoutes.get(d));
					uniqueRoutes.remove(uniqueRoutes.get(d));
					if(uniqueRoutes.size() == 0)
						break;
				}
			}
			
			ArrayList<String> currStation = new ArrayList<String>();
			if(currUniqueRoute.size() == 1){
				uniqueRoutesInFile.add(currUniqueRoute.get(0).get(0) + " to " + currUniqueRoute.get(0)
									   .get(currUniqueRoute.get(0).size()-1));
				uniqueRoutesInFile.add(currUniqueRoute.get(0).toString().replaceAll("\\[", "").replaceAll("\\]",""));
			}else{
				for(int e = 0; e < currUniqueRoute.size(); e++){
					outerloop:
					for(int f = 0; f < currUniqueRoute.get(e).size(); f++){	
						currStation.add(currUniqueRoute.get(0).get(f));
						for(int g = 1; g < currUniqueRoute.size(); g++){
							if(!currUniqueRoute.get(g).get(f).contains(currStation.get(f))){
								if(currUniqueRoute.size() == 2){
									uniqueRoutesInFile.add(currUniqueRoute.get(0).get(0) + " to " + 
														   currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1));
									uniqueRoutesInFile.add(currUniqueRoute.get(0).toString().replaceAll("\\[", "")
														   .replaceAll("\\]",""));
									uniqueRoutesInFile.add(currUniqueRoute.get(1).get(0) + " to " + currUniqueRoute.get(1)
														   .get(currUniqueRoute.get(1).size()-1) + " via " + 
														   currUniqueRoute.get(1).get(f));
									uniqueRoutesInFile.add(currUniqueRoute.get(1).toString().replaceAll("\\[", "")
														   .replaceAll("\\]",""));
									currUniqueRoute.clear();
									break outerloop;
								}else{
									uniqueRoutesInFile.add(currUniqueRoute.get(1).get(0) + " to " + currUniqueRoute.get(1)
											 			   .get(currUniqueRoute.get(1).size()-1) + " via " + currUniqueRoute
											 			   .get(1).get(f));
									uniqueRoutesInFile.add(currUniqueRoute.get(1).toString().replaceAll("\\[", "")
														   .replaceAll("\\]",""));
									currUniqueRoute.remove(currUniqueRoute.get(1));
									currStation.clear();
									break outerloop;
								}
							}
						}
					}
				}	
			}
		}
		
		//Have check for if names are the same, loop through finding any that are the same then remoe one of them, 
		//Dartford to London Cannon Street via Barnehurst for example
		
		for(int m = 0; m < uniqueRoutesInFile.size(); m+=2){
			String currRouteName = uniqueRoutesInFile.get(m);
			int count = Collections.frequency(uniqueRoutesInFile, currRouteName);
			if(count > 1){
				ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
				ArrayList<Integer> loc = new ArrayList<Integer>();
				for(int b = 0; b < uniqueRoutesInFile.size(); b+=2){
					if(uniqueRoutesInFile.get(b).equals(currRouteName)){
						ArrayList<String> two = new ArrayList<String>();
						List<String> val = Arrays.asList(uniqueRoutesInFile.get(b+1).split("\\s*,\\s*"));
						two.addAll(val);
						temp.add(two);
						loc.add(b);
					}
				}
				ArrayList<String> currStation = new ArrayList<String>();
				for(int e = 0; e < temp.size(); e++){
					outerloop:
					for(int f = 0; f < temp.get(e).size(); f++){	
						currStation.add(temp.get(0).get(f));
						for(int g = 1; g < temp.size(); g++){
							if(!temp.get(g).get(f).contains(currStation.get(f))){
								for(int p = 0; p < loc.size(); p++){
									uniqueRoutesInFile.set(loc.get(p), temp.get(p).get(0) + " to " + temp.get(p)
														   .get(temp.get(p).size()-1) + " via " + temp.get(p).get(f));
									uniqueRoutesInFile.set(loc.get(p)+1, temp.get(p).toString().replaceAll("\\[", "")
														   .replaceAll("\\]",""));		
								}		
								temp.clear();
								break outerloop;
							}
						}
					}
				}
			}
		}
		
		ArrayList<String> allRoutes = SQLDatabase.selectAllFromRoute("SELECT Route_Name, Route_Listed FROM Route", 2);
		for(int a = 0; a < uniqueRoutesInFile.size(); a+=0){
			if(allRoutes.contains(uniqueRoutesInFile.get(a).toString()) && (allRoutes.contains(uniqueRoutesInFile.get(a+1).toString()))){
				uniqueRoutesInFile.remove(uniqueRoutesInFile.get(a+1));
				uniqueRoutesInFile.remove(uniqueRoutesInFile.get(a));		
			}else{
				a+=2;
			}
		}
		SQLDatabase.addNewRoutes(uniqueRoutesInFile, "INSERT INTO Route (Route_Name, Route_Listed) VALUES (?, ?)");
		return SQLDatabase.selectAllFromRoute("SELECT Route_ID, Route_Name, Route_Listed FROM Route", 3);
	}
	
}
