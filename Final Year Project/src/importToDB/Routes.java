package importToDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Routes {

	public static ArrayList<String> addRoutes(ArrayList<ArrayList<String>> rawRailDataFile){
		//System.out.println(rawRailDataFile);	
		ArrayList<ArrayList<String>> uniqueRoutes = new ArrayList<ArrayList<String>>();
		for(int a = 0; a < rawRailDataFile.size(); a++){
			ArrayList<String> currRoute = new ArrayList<String>();
			if((rawRailDataFile.get(a).size() == 5) && (rawRailDataFile.get(a).get(4).equals("FIRST"))){
				for(int b = a; b < rawRailDataFile.size(); b+=2){
					if((rawRailDataFile.get(b+1).size() == 5) && (rawRailDataFile.get(b+1).get(4).equals("LAST"))){
						currRoute.add(rawRailDataFile.get(b).get(3));
						currRoute.add(rawRailDataFile.get(b+1).get(3));
						//System.out.println(currRoute);
						break;
					}
					currRoute.add(rawRailDataFile.get(b).get(3));
					//System.out.println(currRoute);
				}
			}
			if((!(uniqueRoutes.contains(currRoute))) && currRoute.size() > 0){
				uniqueRoutes.add(currRoute);
			}
		}
		//for(int a = 0; a < uniqueRoutes.size(); a++)
		//		System.out.println(uniqueRoutes.get(a));
		ArrayList<String> uniqueRoutesInFile = new ArrayList<String>();
	
		
		
		//ArrayList<String> testcurrUnique = new ArrayList<String>();
		
		
		//testcurrUnique.add("London Victoria to Dartford");
		//testcurrUnique.add("London Victoria, Lewisham, Hither Green, Lee, Mottingham, New Eltham, Sidcup, Albany Park, Bexley, Crayford, Dartford");
		
		//ArrayList<String> data1 = new ArrayList<String>();
		//data1.add("Dartford");data1.add("Slade Green");data1.add("Erith");data1.add("Belvedere");data1.add("Abbey Wood");data1.add("Plumstead");data1.add("Woolwich Arsenal");data1.add("Woolwich Dockyard");data1.add("Charlton");data1.add("Westcombe Park");data1.add("Maze Hill");data1.add("Greenwich");data1.add("Deptford");data1.add("London Bridge");data1.add("London Cannon Street");
		//ArrayList<String> data2 = new ArrayList<String>();
		//data2.add("Dartford");data2.add("Barnehurst");data2.add("Bexleyheath");data2.add("Welling");data2.add("Falconwood");data2.add("Eltham");data2.add("Kidbrooke");data2.add("Blackheath");data2.add("Lewisham");data2.add("New Cross");data2.add("London Bridge");data2.add("London Cannon Street");
		

		//ArrayList<String> data3 = new ArrayList<String>();
		//data3.add("Dartford");data3.add("Barnehurst");data3.add("Bexleyheath");data3.add("Welling");data3.add("Falconwood");data3.add("Eltham");data3.add("Kidbrooke");data3.add("Blackheath");data3.add("Lewisham");data3.add("London Bridge");data3.add("London Cannon Street");
		
		
		//testcurrUnique.add(data1);
		//testcurrUnique.add(data2);
		//testcurrUnique.add(data3);
		//System.out.println(data1);
		//System.out.println(data2);
		//System.out.println(data3);
		
		
		
		int countsize = uniqueRoutes.size();
		for(int c = 0; c < countsize; c++){
			ArrayList<ArrayList<String>> currUniqueRoute = new ArrayList<ArrayList<String>>();
			if(uniqueRoutes.size() == 0) break;
			String routeStart = uniqueRoutes.get(0).get(0);
			String routeEnd = uniqueRoutes.get(0).get(uniqueRoutes.get(0).size()-1);
			for(int d = 0; d < uniqueRoutes.size(); d++){
				if((routeStart.equals(uniqueRoutes.get(d).get(0))) && (routeEnd.equals(uniqueRoutes.get(d).get(uniqueRoutes.get(d).size()-1)))){
					currUniqueRoute.add(uniqueRoutes.get(d));
					uniqueRoutes.remove(uniqueRoutes.get(d));
					if(uniqueRoutes.size() == 0)
						break;
					if((routeStart.equals(uniqueRoutes.get(d).get(0))) && (routeEnd.equals(uniqueRoutes.get(d).get(uniqueRoutes.get(d).size()-1)))){
						currUniqueRoute.add(uniqueRoutes.get(d));
						uniqueRoutes.remove(uniqueRoutes.get(d));}
				}
			}
			//System.out.println(currUniqueRoute);
			//finalRoutes.add(currUniqueRoute.get(0).get(0) + " to " + currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1));
			
			
		//	String firstname = testcurrUnique.get(0).get(0) + " to " + testcurrUnique.get(0).get(testcurrUnique.get(0).size()-1);
			
				
						
				//System.out.println(n		
						
						
			ArrayList<String> currStation = new ArrayList<String>();
			
			if(currUniqueRoute.size() == 1){
				uniqueRoutesInFile.add(currUniqueRoute.get(0).get(0) + " to " + currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1));
				uniqueRoutesInFile.add(currUniqueRoute.get(0).toString().replaceAll("\\[", "").replaceAll("\\]",""));
			}else{
				for(int e = 0; e < currUniqueRoute.size(); e++){
					outerloop:
					for(int f = 0; f < currUniqueRoute.get(e).size(); f++){	
						
						currStation.add(currUniqueRoute.get(0).get(f));
						for(int g = 1; g < currUniqueRoute.size(); g++){
							if(!currUniqueRoute.get(g).get(f).contains(currStation.get(f))){
								if(currUniqueRoute.size() == 2){
									//if(!uniqueRoutesInFile.contains(currUniqueRoute.get(1).get(0) + " to " + currUniqueRoute.get(1).get(currUniqueRoute.get(1).size()-1) + " via " + currUniqueRoute.get(1).get(f))){
										uniqueRoutesInFile.add(currUniqueRoute.get(0).get(0) + " to " + currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1));
										uniqueRoutesInFile.add(currUniqueRoute.get(0).toString().replaceAll("\\[", "").replaceAll("\\]",""));
										uniqueRoutesInFile.add(currUniqueRoute.get(1).get(0) + " to " + currUniqueRoute.get(1).get(currUniqueRoute.get(1).size()-1) + " via " + currUniqueRoute.get(1).get(f));
										uniqueRoutesInFile.add(currUniqueRoute.get(1).toString().replaceAll("\\[", "").replaceAll("\\]",""));
										currUniqueRoute.clear();
										break outerloop;
									//}
								}else{
									uniqueRoutesInFile.add(currUniqueRoute.get(1).get(0) + " to " + currUniqueRoute.get(1).get(currUniqueRoute.get(1).size()-1) + " via " + currUniqueRoute.get(1).get(f));
									uniqueRoutesInFile.add(currUniqueRoute.get(1).toString().replaceAll("\\[", "").replaceAll("\\]",""));
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
		//Have check for if names are the same, loop through finding any that are the same then remoe one of them, Dartford to London Cannon Street via Barnehurst for example
		
		
		
		
		
		for(int m = 0; m < uniqueRoutesInFile.size(); m+=2){
			String currRouteName = uniqueRoutesInFile.get(m);
			int count = Collections.frequency(uniqueRoutesInFile, currRouteName);
			if(count > 1){
				//System.out.println(uniqueRoutesInFile.indexOf(currRouteName));
			ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
			ArrayList<Integer> loc = new ArrayList<Integer>();
			for(int b = 0; b < uniqueRoutesInFile.size(); b+=2){
				if(uniqueRoutesInFile.get(b).equals(currRouteName)){
					//ArrayList<String> one = new ArrayList<String>();
					//one.add(uniqueRoutesInFile.get(b));
					ArrayList<String> two = new ArrayList<String>();
					List<String> val = Arrays.asList(uniqueRoutesInFile.get(b+1).split("\\s*,\\s*"));
					two.addAll(val);
					//temp.add(one);
					temp.add(two);
					loc.add(b);
				}
			}
			
			//System.out.println(temp);
			ArrayList<String> currStation = new ArrayList<String>();
			
			
			
			for(int e = 0; e < temp.size(); e++){
				outerloop:
				for(int f = 0; f < temp.get(e).size(); f++){	
					
					currStation.add(temp.get(0).get(f));
					for(int g = 1; g < temp.size(); g++){
						if(!temp.get(g).get(f).contains(currStation.get(f))){
							
								//if(!uniqueRoutesInFile.contains(currUniqueRoute.get(1).get(0) + " to " + currUniqueRoute.get(1).get(currUniqueRoute.get(1).size()-1) + " via " + currUniqueRoute.get(1).get(f))){
									for(int p = 0; p < loc.size(); p++){
									uniqueRoutesInFile.set(loc.get(p), temp.get(p).get(0) + " to " + temp.get(p).get(temp.get(p).size()-1) + " via " + temp.get(p).get(f));
									uniqueRoutesInFile.set(loc.get(p)+1, temp.get(p).toString().replaceAll("\\[", "").replaceAll("\\]",""));
									
									}
									//uniqueRoutesInFile.add(temp.get(1).get(0) + " to " + temp.get(1).get(temp.get(1).size()-1) + " via " + temp.get(1).get(f));
									//uniqueRoutesInFile.add(temp.get(1).toString().replaceAll("\\[", "").replaceAll("\\]",""));
									
									temp.clear();
									break outerloop;
								//}
							
						}
					
					}
				}
			}
			
			
			
			
			
			
			}
		}
		
		
		
		
				
						/**for(int e = 0; e < currUniqueRoute.size(); e++){
							//if(done == true)break;
							for(int f = 0; f < currUniqueRoute.get(e).size(); f++){
								//if(done == true)break;
								currStation.add(currUniqueRoute.get(e).get(f));
								
								for(int g = 1; g < currUniqueRoute.size(); g++){
									try{
									if(!currUniqueRoute.get(g).get(f).contains(currStation.get(f))){
										if(count == 0){
											uniqueRoutesInFile.add(currUniqueRoute.get(0).get(0) + " to " + currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1) + " via " + currUniqueRoute.get(0).get(f));
											uniqueRoutesInFile.add(currUniqueRoute.get(0).toString().replaceAll("\\[", "").replaceAll("\\]",""));
											currUniqueRoute.remove(currUniqueRoute.get(0));
											count++;
											if(initalise != 2)
											currUniqueRoute.remove(currUniqueRoute.get(0));
											//qwerty.clear();
										}else{
											uniqueRoutesInFile.add(currUniqueRoute.get(1).get(0) + " to " + currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1) + " via " + currUniqueRoute.get(0).get(f));
											
											uniqueRoutesInFile.add(currUniqueRoute.get(1).toString().replaceAll("\\[", "").replaceAll("\\]",""));
											count++;
											if (initalise == count){
												done = true;
												break;
											}
													
										}
									}
									}catch(Exception e1){
										
									}
								}
							}
								//if(testCurrUnique.get(e).get(f))
							}**/
						
		
	//for(int a = 0; a<uniqueRoutesInFile.size(); a++)
		//	System.out.println(uniqueRoutesInFile.get(a));
						
				ArrayList<String> allRoutes = SQLDatabase.selectAllFromRoute("SELECT Route_Name, Route_Listed FROM Route", 2);
				//System.out.println("allroutes"+allRoutes);
				//System.out.println(allRoutes.get(2));
				//int count = uniqueRoutesInFile.size();
				for(int a = 0; a < uniqueRoutesInFile.size(); a+=0){
					//if(uniqueRoutesInFile.get(a).toString() == "Clock House to London Cannon Street")
						//System.out.println(testcurrUnique.get(a).toString());
					//if(uniqueRoutesInFile.size() <= 10)break;
					if(allRoutes.contains(uniqueRoutesInFile.get(a).toString()) && (allRoutes.contains(uniqueRoutesInFile.get(a+1).toString()))){
						uniqueRoutesInFile.remove(uniqueRoutesInFile.get(a+1));
						uniqueRoutesInFile.remove(uniqueRoutesInFile.get(a));
						
					}else{a+=2;}
				}
				if(uniqueRoutesInFile.size() > 1){
					SQLDatabase.addNewRoutes(uniqueRoutesInFile, "INSERT INTO Route (Route_Name, Route_Listed) VALUES (?, ?)");
				} else {
					System.out.println("There were no new routes to be added");
				}
				
				
		
		
		
		
		
		
		
				//for(int a = 0; a < uniqueRoutesInFile.size(); a++)
					//System.out.println(uniqueRoutesInFile.get(a));
						/*if(!currUniqueRoute.get(0).get(f).toString().equals(currUniqueRoute.get(e).get(f).toString())){
							
							String name = currUniqueRoute.get(e).get(0) + " to " + currUniqueRoute.get(e).get(currUniqueRoute.get(e).size()-1) + " via " + currUniqueRoute.get(e).get(f);
							//WHAT IF 3
							if(!(finalRoutes.contains(name))){
								if(!currUniqueRoute.get(0).get(f).equals(currUniqueRoute.get(e).get(f).toString())){
								finalRoutes.add(name);
								break;
								}
							}
							
							//finalRoutes.add(currUniqueRoute.get(e).get(0) + " to " + currUniqueRoute.get(e).get(currUniqueRoute.get(e).size()-1) + " via " + currUniqueRoute.get(e).get(f));
							//break;
						}*/
					
				
				
				
		
		
		//for(int a = 0; a < finalRoutes.size(); a++)
		//		System.out.println(finalRoutes.get(a));
		
		
		
	/**	
				
			
			//ArrayList<String> stndRoute = currRoute.get(0);
			//System.out.println();
			//System.out.println(stndRoute);
			
			
			finalRoutes.add(currRoute.get(0).get(0) + " to " + currRoute.get(0).get(currRoute.get(0).size()-1));
			//System.out.println(finalRoutes);
			
			//System.out.println(currRoute);
			
			for(int e = 1; e < currRoute.size(); e++){
				for(int f = 1; f < currRoute.get(e).size(); f++){
					if(!(currRoute.get(0).get(f).toString().equals(currRoute.get(e).get(f).toString()))){
						finalRoutes.add(currRoute.get(e).get(0) + " to " + currRoute.get(e).get(currRoute.get(e).size()-1) + " via " + currRoute.get(e).get(f));
					break;
					}
				}
			}
			
			//System.out.println(currRoute);
			
			**/
			
			//System.out.print(uniqueRoutes.get(c).get(0) + "   ");
			//System.out.println(uniqueRoutes.get(c).get(uniqueRoutes.get(c).size()-1));
		
		
		//for(int a = 0; a < uniqueRoutes.size(); a++)
		//	System.out.println(uniqueRoutes.get(a));
		//for(int a = 0; a < finalRoutes.size(); a++)
		//	System.out.println(finalRoutes.get(a));
		
		
		
		return SQLDatabase.selectAllFromRoute("SELECT Route_ID, Route_Name, Route_Listed FROM Route", 3);
	}
	
}
