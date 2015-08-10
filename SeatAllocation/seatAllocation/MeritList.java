package seatAllocation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MeritList {
	/**
	 * The final Merit List to be Generated.
	 */
	private ArrayList<Candidate> meritList = new ArrayList<Candidate>();
	
	/**
	 * Constructor that read the ranklist file.
	 * @param csvAddress Address of the ranklist file
	 */
	MeritList(String csvAddress)
	{
		/**
		 * Initial List without Sorting
		 */
		ArrayList<Candidate> initialList = new ArrayList<Candidate>();
		
		/**
		 * BufferedReader to read the file ranklist.csv
		 */
		BufferedReader br = null;
		/**
		 * temporary String to be read by buffered reader.
		 */
		String line = "";
		/**
		 * comma string to separate the string obtained from br.
		 */
		String csvSeparator =",";
		try {
			 
			br = new BufferedReader(new FileReader(csvAddress));
			br.readLine();
			while ((line = br.readLine()) != null) {
				
				String[] candidate = line.split(csvSeparator);
				Candidate pres = new Candidate(candidate[0],Double.parseDouble(candidate[3]),Double.parseDouble(candidate[4]),Double.parseDouble(candidate[5]),Double.parseDouble(candidate[6]),Double.parseDouble(candidate[8]),Double.parseDouble(candidate[9]),Double.parseDouble(candidate[10]),Double.parseDouble(candidate[11]));
				initialList.add(pres); 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// InitialList is ready.Converting to merit list.By Sorting Individually.
		/**
		 * GE Initial List.To be sorted.
		 */
		ArrayList<Candidate> geL = new ArrayList<Candidate>();
		/**
		 * OBC Initial List.To be sorted.
		 */
		ArrayList<Candidate> obcL = new ArrayList<Candidate>();
		/**
		 * SC Initial List.To be sorted.
		 */
		ArrayList<Candidate> scL = new ArrayList<Candidate>();
		/**
		 * ST Initial List.To be sorted.
		 */
		ArrayList<Candidate> stL = new ArrayList<Candidate>();
		/**
		 * GE PD initial List.To be sorted.
		 */
		ArrayList<Candidate> gepdL = new ArrayList<Candidate>();
		/**
		 * OBC PD initial list to be sorted.
		 */
		ArrayList<Candidate> obcpdL = new ArrayList<Candidate>();
		/**
		 * SC PD initial list to be sorted.
		 */
		ArrayList<Candidate> scpdL = new ArrayList<Candidate>();
		/**
		 * ST PD initial list to be sorted.
		 */
		ArrayList<Candidate> stpdL = new ArrayList<Candidate>();
		
		// Adding non-zero rank candidates to the corresponding merit lists.
		for(Candidate pres : initialList)
		{
			if(pres.getgeR() >0)
			{
				geL.add(pres);
			}
			if(pres.getobcR() >0)
			{
				obcL.add(pres);
			}
			if(pres.getscR()>0)
			{
				scL.add(pres);
			}
			if(pres.getstR()>0)
			{
				stL.add(pres);
			}
			if(pres.getgepdR()>0)
			{
				gepdL.add(pres);
			}
			if(pres.getobcpdR()>0)
			{
				obcpdL.add(pres);
			}
			if(pres.getscpdR()>0)
			{
				scpdL.add(pres);
			}
			if(pres.getstpdR()>0)
			{
				stpdL.add(pres);
			}
		}
		// Sorting each of lists using Insertion Sort.
		int x = geL.size();
		for(int i=0;i<x;i++)
		{
			for(int j=i+1;j<x;j++)
			{
				double f = geL.get(i).getgeR();
				double g =geL.get(j).getgeR();
				if(f >g)
				{
					Candidate w = geL.get(i);
					Candidate u = geL.get(j);
					geL.set(i,u);
					geL.set(j,w);
				}
			}
		}
		x = obcL.size();
		for(int i=0;i<x;i++)
		{
			for(int j=i+1;j<x;j++)
			{
				double f = obcL.get(i).getobcR();
				double g =obcL.get(j).getobcR();
				if(f >g)
				{
					Candidate w = obcL.get(i);
					Candidate u = obcL.get(j);
					obcL.set(i,u);
					obcL.set(j,w);
				}
			}
		}
		x = scL.size();
		for(int i=0;i<x;i++)
		{
			for(int j=i+1;j<x;j++)
			{
				double f = scL.get(i).getscR();
				double g =scL.get(j).getscR();
				if(f >g)
				{
					Candidate w = scL.get(i);
					Candidate u = scL.get(j);
					scL.set(i,u);
					scL.set(j,w);
				}
			}
		}
		x = stL.size();
		for(int i=0;i<x;i++)
		{
			for(int j=i+1;j<x;j++)
			{
				double f = stL.get(i).getstR();
				double g =stL.get(j).getstR();
				if(f >g)
				{
					Candidate w = stL.get(i);
					Candidate u = stL.get(j);
					stL.set(i,u);
					stL.set(j,w);
				}
			}
		}
		x = gepdL.size();
		for(int i=0;i<x;i++)
		{
			for(int j=i+1;j<x;j++)
			{
				double f = gepdL.get(i).getgepdR();
				double g =gepdL.get(j).getgepdR();
				if(f >g)
				{
					Candidate w = gepdL.get(i);
					Candidate u = gepdL.get(j);
					gepdL.set(i,u);
					gepdL.set(j,w);
				}
			}
		}
		x = obcpdL.size();
		for(int i=0;i<x;i++)
		{
			for(int j=i+1;j<x;j++)
			{
				double f = obcpdL.get(i).getobcpdR();
				double g =obcpdL.get(j).getobcpdR();
				if(f >g)
				{
					Candidate w = obcpdL.get(i);
					Candidate u = obcpdL.get(j);
					obcpdL.set(i,u);
					obcpdL.set(j,w);
				}
			}
		}
		x = scpdL.size();
		for(int i=0;i<x;i++)
		{
			for(int j=i+1;j<x;j++)
			{
				double f = scpdL.get(i).getscpdR();
				double g =scpdL.get(j).getscpdR();
				if(f >g)
				{
					Candidate w = scpdL.get(i);
					Candidate u = scpdL.get(j);
					scpdL.set(i,u);
					scpdL.set(j,w);
				}
			}
		}
		x = stpdL.size();
		for(int i=0;i<x;i++)
		{
			for(int j=i+1;j<x;j++)
			{
				double f = stpdL.get(i).getstpdR();
				double g =stpdL.get(j).getstpdR();
				if(f >g)
				{
					Candidate w = stpdL.get(i);
					Candidate u = stpdL.get(j);
					stpdL.set(i,u);
					stpdL.set(j,w);
				}
			}
		}
		
		// Merging the lists.
		meritList.addAll(geL);
		meritList.addAll(obcL);
		meritList.addAll(scL);
		meritList.addAll(stL);
		meritList.addAll(gepdL);
		meritList.addAll(obcpdL);
		meritList.addAll(scpdL);
		meritList.addAll(stpdL);
	}
	/**
	 * to return the final MeritList obtained during construction.
	 * @return MeritList
	 */
	public ArrayList<Candidate> getCandidateList()
	{
		return meritList;
	}
}
