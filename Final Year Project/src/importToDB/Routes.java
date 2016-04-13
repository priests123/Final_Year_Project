package importToDB;

import java.util.ArrayList;
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
		ArrayList<String> finalRoutes = new ArrayList<String>();
	
		
		
		//ArrayList<ArrayList<String>> testcurrUnique = new ArrayList<ArrayList<String>>();
		//ArrayList<String> data1 = new ArrayList<String>();
	//	data1.add("Dartford");data1.add("Slade Green");data1.add("Erith");data1.add("Belvedere");data1.add("Abbey Wood");data1.add("Plumstead");data1.add("Woolwich Arsenal");data1.add("Woolwich Dockyard");data1.add("Charlton");data1.add("Westcombe Park");data1.add("Maze Hill");data1.add("Greenwich");data1.add("Deptford");data1.add("London Bridge");data1.add("London Cannon Street");
		//ArrayList<String> data2 = new ArrayList<String>();
		//data2.add("Dartford");data2.add("Barnehurst");data2.add("Bexleyheath");data2.add("Welling");data2.add("Falconwood");data2.add("Eltham");data2.add("Kidbrooke");data2.add("Blackheath");data2.add("Lewisham");data2.add("New Cross");data2.add("London Bridge");data2.add("London Cannon Street");
		

	//	ArrayList<String> data3 = new ArrayList<String>();
		//data3.add("Dartford");data3.add("Barnehurst");data3.add("Bexleyheath");data3.add("Welling");data3.add("Falconwood");data3.add("Eltham");data3.add("Kidbrooke");data3.add("Blackheath");data3.add("Lewisham");data3.add("London Bridge");data3.add("London Cannon Street");
		
		
	//	testcurrUnique.add(data1);
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
						
						
			ArrayList<String> qwerty = new ArrayList<String>();
			int count = 0;
			boolean done = false;
			int initalise = currUniqueRoute.size();
			if(currUniqueRoute.size() == 1){
				finalRoutes.add(currUniqueRoute.get(0).get(0) + " to " + currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1));
				finalRoutes.add(currUniqueRoute.get(0).toString());
			}else{
						for(int e = 0; e < currUniqueRoute.size(); e++){
							if(done == true)break;
							for(int f = 0; f < currUniqueRoute.get(e).size(); f++){
								if(done == true)break;
								qwerty.add(currUniqueRoute.get(e).get(f));
								
								for(int g = 1; g < currUniqueRoute.size(); g++){
									try{
									if(!currUniqueRoute.get(g).get(f).contains(qwerty.get(f))){
										if(count == 0){
											finalRoutes.add(currUniqueRoute.get(0).get(0) + " to " + currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1));
											finalRoutes.add(currUniqueRoute.get(0).toString());
											count++;
											if(initalise != 2)
											currUniqueRoute.remove(currUniqueRoute.get(0));
											//qwerty.clear();
										}else{
											finalRoutes.add(currUniqueRoute.get(0).get(0) + " to " + currUniqueRoute.get(0).get(currUniqueRoute.get(0).size()-1) + " via " + currUniqueRoute.get(0).get(f));
											finalRoutes.add(currUniqueRoute.get(0).toString());
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
							}
						}
		}
		for(int a = 0; a < finalRoutes.size(); a++)
			System.out.println(finalRoutes.get(a));
			
						
				
		
		
		
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
		
		
		
		return null;
	}
	
}
