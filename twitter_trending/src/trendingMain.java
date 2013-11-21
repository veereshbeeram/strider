package trendingMain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

public class MainClass {
	private HashMap<String, Boolean> noiseMap;
	private HashMap<String,Boolean> tempMap;
	private HashMap<String,Integer> mainMap;
	public static void main(String[] args){
		MainClass runner = new MainClass();
		runner.startProg();
	}
	private void generateNoiseMap(){
		noiseMap = new HashMap();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("./noise.txt"));
			String str;
			while((str = br.readLine())!=null){
				noiseMap.put(str,true);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Noise file not found");
			
		}
	}
	private void testNoiseMap(){
		Set set = noiseMap.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	        System.out.println(me.getKey() + " : " + me.getValue() );
	    }
	}
	private void getStaticTrend(){
		tempMap = new HashMap();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("./tweet.txt"));
			String str;
			
			while((str = br.readLine())!=null){
				if(!str.equals("--")){
					tempMap.clear();
				String[] strarr = str.split(" ");
				for(int i=0;i<strarr.length;i++){
					tempMap.put(strarr[i],true);
				}
				// call for countincrementer here
				//this.testTempMap();	 
				//System.out.println("!!--");
				this.mainMapUpdate(); // required to prevent last tweets counts coming in
				}
				
				
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("tweet file not found");
			
		}
	}
	private void testTempMap(){
		Set set = tempMap.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	        System.out.println(me.getKey() + " : " + me.getValue() );
	    }
	}
	private void mainMapUpdate(){
		Set set = tempMap.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	        // we are getting each word here.. if exist> inc else add with count 1
	    	if(mainMap.containsKey(me.getKey())){
	    		int count = mainMap.get(me.getKey().toString());
	    		count++;
	    		mainMap.put(me.getKey().toString(),count);
	    	}
	    	else{
	    		mainMap.put(me.getKey().toString(), 1);
	    	}
	    }
	
		
	}
	private void testMainMap(){
		Set set = mainMap.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	        System.out.println(me.getKey() + " : " + me.getValue() );
	    }
	}
	private void printTrends(){
		int counter=0;
		HashMap<String, Integer> someMap = new HashMap();
		someMap = mainMap;
		List mapValues = new ArrayList(someMap.values());
		TreeSet sortedSet = new TreeSet(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		for(int i=0;i<sortedArray.length;i++){
			//System.out.println((Integer)sortedArray[i]);
		}
		for(int i=sortedArray.length-1;i>=0&&counter<5;i--){
			Set set = mainMap.entrySet();
		    Iterator iter = set.iterator();
		    while(iter.hasNext()){
		    	Map.Entry me = (Map.Entry)iter.next();
		        if(me.getValue() == sortedArray[i] && counter<5){
		        	System.out.println(me.getKey());
		        	counter++;
		        }
		    }
		}
	}
	//private void liveTrends(){
	//	BufferedReader br;
	//	br = new bufferedReader(new FileReader(System.in) );
	//}
	private void startProg(){
		mainMap = new HashMap();
		this.generateNoiseMap();
		this.getStaticTrend();
		//this.testNoiseMap();  // It Works
		//this.testTempMap();   // It works
		this.testMainMap();
		System.out.println("---------------------");
		this.printTrends();
		//this.liveTrends();
		
	}

}
