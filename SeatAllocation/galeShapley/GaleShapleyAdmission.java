package galeShapley;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GaleShapleyAdmission {
	/**
	 * Mapping candidate IDs to the candidates.
	 */
	HashMap<String,Candidate> candidates = new HashMap<String,Candidate>();
	/**
	 * List of the IDs of all the candidates.
	 */
	ArrayList<String> candidateIds = new ArrayList<String>();
	/**
	 * Mapping Virtual Program IDs to the Virtual Programs
	 */
	HashMap<String,VirtualProgramme> programs = new HashMap<String,VirtualProgramme>();
	/**
	 * List of all the Virtual Program IDs
	 */
	ArrayList<String> programIds = new ArrayList<String>();
	/**
	 * Mapping institute code to the dsCount 
	 * <p>
	 * Here,institute code is the first letter in all of its' Programs.
	 * Every institute will have unique first letter.
	 * <p>
	 */
	HashMap<String,Integer> dsCount = new HashMap<String,Integer>();
	/**
	 * Mapping from the candidate IDs to DS
	 * true if the particular candidate if DS false otherwise
	 */
	HashMap<String,Boolean> ds = new HashMap<String,Boolean>();
	/**
	 * Mapping from the candidate IDs to DS Allotment
	 * true if the candidate seat using DS
	 */
	HashMap<String,Boolean> dsAllocated = new HashMap<String ,Boolean>();
	/**
	 * true if the candidate is foreign false otherwise
	 */
	HashMap<String,Boolean> foreign = new HashMap<String,Boolean>();
	/**
	 * Mapping from Virtual Program Name to the Program Name
	 */
	HashMap<String,String> courseName = new HashMap<String , String>();
	/**
	 * Constructor where the whole algo runs.
	 * @param directoryName the name of the directory
	 */
	public GaleShapleyAdmission(String directoryName)
	{
		// TODO : Set everything to linux.
		
		// reading ranklist file
		String ranklistFile = directoryName +"/ranklist.csv"; 
		/**
		 * Buffered Reader to read the files
		 */
		BufferedReader br = null;
		String line = "";
		String csvSeparator =",";
		try {
			 
			br = new BufferedReader(new FileReader(ranklistFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] i = line.split(csvSeparator);// i for info of candidate.
				Double[] d = new Double[20];
				for(int var=3;var<=10;var++)
				{
					d[var]=Double.parseDouble(i[var]);
				}
				Candidate c = new Candidate(i[0],d[3],d[4],d[5],d[6],d[7],d[8],d[9],d[10]);
				candidates.put(i[0], c);
				candidateIds.add(i[0]);
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
		
		// reading choicelist file
		String choiceFile = directoryName+"/choices.csv";
		
		br = null;
		line = "";
		csvSeparator =",";
		try {
			 
			br = new BufferedReader(new FileReader(choiceFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] i = line.split(csvSeparator);// i for info of candidate.
				Candidate c = candidates.get(i[0]);
				c.setChoices(i[3]);
				if(i[1].equals("DS"))
					ds.put(i[0], true);
				else ds.put(i[0], false);
				if(i[1].equals("F"))
					foreign.put(i[0], true);
				else foreign.put(i[0], false);
				dsAllocated.put(i[0], false);
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
		
		// reading programs file.
		String programsFile= directoryName+"/programs.csv";
		
		br = null;
		line = "";
		csvSeparator =",";
		try {
			 
			br = new BufferedReader(new FileReader(programsFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] i = line.split(csvSeparator);// i for info of candidate.
				int ct=1;
				if(i.length <11)
				{
					//Wrong Format..Go Away.
					continue;
				}
				// Creating the Virtual Programs out of Programs
				dsCount.put(i[1].substring(0, 1), 2);
				VirtualProgramme v = new VirtualProgramme(i[1]+"GE",Integer.parseInt(i[3]),ct);
				programIds.add(i[1]+"GE");
				programs.put(i[1]+"GE", v);
				courseName.put(i[1]+"GE", i[2]);
				ct = ct+1;
				v = new VirtualProgramme(i[1]+"OBC",Integer.parseInt(i[4]),ct);
				programIds.add(i[1]+"OBC");
				programs.put(i[1]+"OBC", v);
				courseName.put(i[1]+"OBC",i[2]);
				ct = ct+1;
				v = new VirtualProgramme(i[1]+"SC",Integer.parseInt(i[5]),ct);
				programIds.add(i[1]+"SC");
				programs.put(i[1]+"SC", v);
				courseName.put(i[1]+"SC",i[2]);
				ct = ct+1;
				v = new VirtualProgramme(i[1]+"ST",Integer.parseInt(i[6]),ct);
				programIds.add(i[1]+"ST");
				programs.put(i[1]+"ST", v);
				courseName.put(i[1]+"ST", i[2]);
				ct = ct+1;
				v = new VirtualProgramme(i[1]+"GEPD",Integer.parseInt(i[7]),ct);
				programIds.add(i[1]+"GEPD");
				programs.put(i[1]+"GEPD", v);
				courseName.put(i[1]+"GEPD", i[2]);
				ct = ct+1;
				v = new VirtualProgramme(i[1]+"OBCPD",Integer.parseInt(i[8]),ct);
				programIds.add(i[1]+"OBCPD");
				programs.put(i[1]+"OBCPD", v);
				courseName.put(i[1]+"OBCPD", i[2]);
				ct = ct+1;
				v = new VirtualProgramme(i[1]+"SCPD",Integer.parseInt(i[9]),ct);
				programIds.add(i[1]+"SCPD");
				programs.put(i[1]+"SCPD", v);
				courseName.put(i[1]+"SCPD", i[2]);
				ct = ct+1;
				v = new VirtualProgramme(i[1]+"STPD",Integer.parseInt(i[10]),ct);
				programIds.add(i[1]+"STPD");
				programs.put(i[1]+"STPD", v);
				courseName.put(i[1]+"STPD", i[2]);
				ct = ct+1;
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
		
		
		/**
		 * Final Alloted Program to the candidates.
		 */
		HashMap<String,String> finalAllocation = new HashMap<String,String>();
		for(String s : candidateIds)
		{
			finalAllocation.put(s, "-1");
		}
		
		// Allotting DS Candidates.
		for (String s : candidateIds)
		{
			if(ds.get(s))
			{
				Candidate c = candidates.get(s);
				ArrayList<String> choiceList = c.getChoiceList();
				for (String p : choiceList)
				{
					String pp = p.substring(0, 1);
					if(dsCount.get(pp) >0)
					{
						dsCount.put(pp, dsCount.get(pp)-1);
						dsAllocated.put(s, true);
						finalAllocation.put(s,p);
					}
				}
			}
		}
		
		// Main GS Algorithm.
		
		while (true)
		{
			//checking for end condition 
			boolean done = true;
			for(String s :candidateIds)
			{
				if(dsAllocated.get(s))
					continue;
				if(foreign.get(s))
					continue;	
				Candidate c = candidates.get(s);
				boolean rej = c.getRejected();
				if(rej && c.getIndex() >=0) // I have someone to apply to . Don't break the loop..Please...
				{
					done=false; // Ok..
					break;
				}
			}
			if(done )
				break;
			
			//Each Candidate Applying for their preference  
			for(String s : candidateIds)
			{
				if(dsAllocated.get(s))
					continue;
				if(foreign.get(s))
					continue;
				Candidate c = candidates.get(s);
				boolean rej = c.getRejected();
				int ind = c.getIndex();
				if(!rej)
					continue;
				if(ind <0)
					continue;
				String vp = c.getCourse(ind);
				VirtualProgramme v = programs.get(vp);
				v.addCandidate(s);
				programs.put(vp, v);
			}
			
			// Each Virtual Programme sorting the issues.
			for (String s:programIds)
			{
				VirtualProgramme v = programs.get(s);
				int q = v.getQuota();
				int ct = v.getCat();
				ArrayList<String> wl = v.getWL();
				if(wl.size() <= q)
				{
					for(String p:wl)
					{
						Candidate c = candidates.get(p);
						c.accepted();
						candidates.put(p, c);
					}
					continue;
				}
				ArrayList<Double> db = new ArrayList<Double>();
				for(String t : wl)
				{
					Candidate c = candidates.get(t);
					switch(ct)
					{
						/// To compare based on two ranks x & y i.e x first and then y
						/// Using x*1e5 + y
						case 1:
							db.add(c.getGer());
							break;
						case 2:
							double x = c.getObcr();
							double y = c.getGer();
							double p = (x*1e5) + y;
							db.add(p);
							break;
						case 3:
							db.add(c.getScr());
							break;
						case 4:
							db.add(c.getStr());
							break;
						case 5 :
							x = c.getGepdr();
							y = c.getGer();
							p = (x*1e5)+y;
							db.add(p);
							break;
						case 6:
							x = c.getObcpdr();
							y = c.getGer();
							p = (x*1e5)+y;
							db.add(p);
							break;
						case 7:
							x = c.getScpdr();
							y = c.getGer();
							p = (x*1e5)+y;
							db.add(p);
							break;
						case 8:
							x = c.getStpdr();
							y = c.getStpdr();
							p = (x*1e5)+y;
							db.add(p);
							break;	
					}
				}
				
				// Now, removing the other ones out of wait list.
				ArrayList<String> newWl= new ArrayList<String>();
				Collections.sort(db);
				double lastVal;
				if(q>0)lastVal = db.get(q-1);
				else lastVal = -1.0;  
				for(String t :wl)
				{
					Candidate c = candidates.get(t);
					switch(ct)
					{
					case 1:
						double p = c.getGer();
						if(p < (lastVal+(1e-10)))
							{
								c.accepted();
								newWl.add(t);
							}
						else c.rejected();
						candidates.put(t, c);
						break;
					case 2:
						p = c.getObcr()*(1e5)+c.getGer();
						if(p < (lastVal+(1e-10)))
							{
								c.accepted();
								newWl.add(t);
							}
						else c.rejected();
						candidates.put(t, c);
						break;
					case 3:
						p = c.getScr();
						if(p < (lastVal+(1e-10)))
							{
								c.accepted();
								newWl.add(t);
							}
						else c.rejected();
						candidates.put(t, c);
						break;
					case 4:
						p = c.getStr();
						if(p < (lastVal+(1e-10)))
							{
								c.accepted();
								newWl.add(t);
							}
						else c.rejected();
						candidates.put(t, c);
						break;
					case 5:
						p = c.getGepdr()*(1e5) + c.getGer();
						if(p < (lastVal+(1e-10)))
							{
								c.accepted();
								newWl.add(t);
							}
						else c.rejected();
						candidates.put(t, c);
						break;
					case 6:
						p = c.getObcpdr()*(1e5)+c.getGer();
						if(p < (lastVal+(1e-10)))
							{
								c.accepted();
								newWl.add(t);
							}
						else c.rejected();
						candidates.put(t, c);
						break;
					case 7:
						p = c.getScpdr()*(1e5)+c.getGer();
						if(p < (lastVal+(1e-10)))
							{
								c.accepted();
								newWl.add(t);
							}
						else c.rejected();
						candidates.put(t, c);
						break;
					case 8:
						p = c.getStpdr()*(1e5)+c.getGer();
						if(p < (lastVal+(1e-10)))
							{
								c.accepted();
								newWl.add(t);
							}
						else c.rejected();
						candidates.put(t, c);
						break;
					}
				}
				v.setWL(newWl);
				programs.put(s, v);
			}// for each program.
			
		}// end of while.
		
		// Allocating foreign candidates.
		for (String s : candidateIds)
		{
			if(!foreign.get(s))
				continue;
			Candidate c = candidates.get(s);
			double genr = c.getGer();
			ArrayList<String> choiceList = c.getChoiceList();
			for (String pro : choiceList) // You're noob i'm pro. Just kidding! pro for program.
			{
				VirtualProgramme v = programs.get(pro);
				boolean cangive = false;
				ArrayList<String> wl = v.getWL();
				for (String n : wl)
				{
					Candidate cnew = candidates.get(n);
					if(cnew.getGer() > (genr-(1e-10)) || cnew.getGer() < (1e-10))
					{
						cangive=true; // Better than corresponding GE person.
						break;
					}
				}
				if(cangive)
				{
					finalAllocation.put(s,pro);
					break;
				}
			}
		}
		
		// Updating the finalAllocation using the wait lists.
		for (String s:programIds)
		{
			VirtualProgramme v = programs.get(s);
			ArrayList<String> lst = v.getWL();
			for(String w : lst)
			{
				finalAllocation.put(w, s);
			}
		}
		for(String s : candidateIds)
		{
			System.out.println(s+","+finalAllocation.get(s));
		}
		
		// Printing output.
		try {
			 
 
			File file = new File(directoryName+"/outputGs.csv");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("id,program");
			bw.newLine();
			String content = new String();
			for (String s :candidateIds)
			{
				content = s+","+(finalAllocation.get(s));
				bw.write(content);
				bw.newLine();
			}
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
