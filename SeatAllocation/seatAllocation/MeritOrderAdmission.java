package seatAllocation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeritOrderAdmission {
	/**
	 * To return the meritList
	 */
	MeritList singleList;
	/**
	 * the Merit List obtained from singleList
	 */
	ArrayList<Candidate> meritOrder = new ArrayList<Candidate>();
	/**
	 * The final Allotted Program of the each candidate
	 */
	Map <String,String> allottedProgram = new HashMap<String,String>();
	/**
	 * IDs and corresponding candidates.
	 */
	ArrayList<String> studentsIds = new ArrayList<String>();
	
	
	/**
	 * The constructor to run the whole algorithm. 
	 * @param directoryAddress Directory in which all the .csv files are stored.
	 */
	public MeritOrderAdmission(String directoryAddress)
	{
		// TODO : Change windows extensions to linux ones.
		/**
		 * Address of ranklist.csv file. 
		 */
		String ranklistAddress = directoryAddress+"/ranklist.csv";
		singleList = new MeritList(ranklistAddress);
		meritOrder = singleList.getCandidateList();
		/**
		 * Address of choices.csv file
		 */
		String choicelistAddress = directoryAddress+"/choices.csv";
		/**
		 * String of choices corresponding to each candidate id.
		 * <p>
		 * For example, if the choices of 130050052 are B1 and B2, 
		 * the value corresponding to 130050052 will be B1_B2.
		 * This is directly read from choices.csv and processes later.
		 * <p>
		 */
		Map <String,String> choices  = new HashMap<String,String>();
		/**
		 * true if the candidate if foreign, false otherwise.
		 */
		Map <String,Boolean> frgn = new HashMap<String,Boolean>();
		/**
		 * true if the candidate is ds, false otherwise.
		 */
		Map <String,Boolean> ds = new HashMap<String,Boolean>();
		/**
		 * The count of remaining DS seats remaining for an institute.
		 * <p>
		 * Assumption : Each institute has unique letter.
		 * i.e all course codes of that institute start with an unique letter.
		 * So, we store the remaining DS seats of each letter.
		 * <p>
		 */
		Map <String,Integer> dsCount = new HashMap <String,Integer>();
		
		// Reading the choices.csv file and updating frgn,ds and choices.
		BufferedReader br = null;
		String line = "";
		String csvSeparator =",";
		try {
			 
			br = new BufferedReader(new FileReader(choicelistAddress));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] candidateInfo = line.split(csvSeparator);
				if(candidateInfo[1].matches("F"))
					frgn.put(candidateInfo[0], true);
				else 
					frgn.put(candidateInfo[0],false);
				if(candidateInfo[1].matches("DS"))
					ds.put(candidateInfo[0], true);
				else 
					ds.put(candidateInfo[0], false);
				studentsIds.add(candidateInfo[0]);
				choices.put(candidateInfo[0],candidateInfo[3]);
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
		
		// Reading the programs.csv file.
		/**
		 * Maps Program Code to the Program.
		 */
		Map<String,VirtualProgramme> program = new HashMap<String,VirtualProgramme>();
		/**
		 * List of all Program Codes.
		 */
		ArrayList<VirtualProgramme> programs = new ArrayList<VirtualProgramme>();
		/**
		 * Address of the programs.csv file.
		 */
		//TODO :Change to Linux.
		String programsAddress = directoryAddress+"/programs.csv";
		br = null;
		line = "";
		try {
			 
			br = new BufferedReader(new FileReader(programsAddress));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] pI = line.split(csvSeparator); // pI is programInfo
				if(pI.length < 11)//Wrong Format.. Go Away.
				{
					continue;
				}
				VirtualProgramme tmp = new VirtualProgramme(pI[1],Integer.parseInt(pI[3]),Integer.parseInt(pI[4]),Integer.parseInt(pI[5]),Integer.parseInt(pI[6]),Integer.parseInt(pI[7]),Integer.parseInt(pI[8]),Integer.parseInt(pI[9]),Integer.parseInt(pI[10]));
				program.put(pI[1], tmp);
				dsCount.put(pI[1].substring(0,1), 2); //Initializing dsCount to be 2.
				programs.add(tmp);
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
		 
		// Initializing each of the allottedProgram to be empty string.
		for (String s : studentsIds)
		{
			allottedProgram.put(s,"");
		}
		
		//Main Part : Reading and processing the merit list.
		for(int k=1;k<=2;k++){ // Twice because DeReservation happens during the second run.

			for (Candidate c : meritOrder)
			{
				String id = c.getUniqueID();
				if(allottedProgram.get(id) != "") // We don't allot two seats to same person.
					continue;
				String prefList = choices.get(id);
				String[] choiceList = prefList.split("_"); // Splitting the course preference list string.
				for(int i=0;i<choiceList.length;i++)
				{
					if(ds.get(id))
					{
						// ds candidate
						if(dsCount.get(choiceList[i].substring(0,1)) >0)
						{
							// yo!!
							allottedProgram.put(id,choiceList[i]);
							dsCount.put(choiceList[i].substring(0,1),dsCount.get(choiceList[i].substring(0,1))-1);
							break;
						}
					}
					VirtualProgramme v = program.get(choiceList[i]);
					double ger = c.getgeR();
					// trying to allot GE Seat
					if(ger>0)
					{
						if(v.getGeq() >0)
						{
							allottedProgram.put(id,choiceList[i]);
							if(frgn.get(id))
								break;
							v.setGeq(v.getGeq()-1);
							v.setLastge(ger);
							break;
						}
						else 
						{
							if(ger == v.getLastge())
							{
								allottedProgram.put(id,choiceList[i]);
								break;
							}
						}
					}
					// trying to allot OBC Seat.
					double obcr = c.getobcR();
					if(obcr>0)
					{
						if(v.getObcq() >0)
						{
							allottedProgram.put(id,choiceList[i]);
							v.setObcq(v.getObcq()-1);
							v.setLastobc(obcr);
							break;
						}
						else if (obcr == v.getLastobc())
						{
							allottedProgram.put(id,choiceList[i]);
							break;
						}
					}
					// trying to allot SC Seat.
					double scr = c.getscR();
					if(scr>0)
					{
						if(v.getScq() >0)
						{
							allottedProgram.put(id,choiceList[i]);
							v.setScq(v.getScq()-1);
							v.setLastsc(scr);
							break;
						}
						else if (scr == v.getLastsc())
						{
							allottedProgram.put(id,choiceList[i]);
							break;
						}
						
					}
					// trying to allot ST Seat
					double str = c.getstR();
					if(str>0)
					{
						if(v.getStq() >0)
						{
							allottedProgram.put(id,choiceList[i]);
							v.setStq(v.getStq()-1);
							v.setLastst(str);
							break;
						}
						else if (str == v.getLastst())
						{
							allottedProgram.put(id,choiceList[i]);
							break;
						}
					}
					// trying to allot GE PD Seat
					double gepdr = c.getgepdR();
					if(gepdr>0)
					{
						if(v.getGepdq() >0)
						{
							allottedProgram.put(id,choiceList[i]);
							if(frgn.get(id))
								break;
							v.setGeq(v.getGepdq()-1);
							v.setLastge(gepdr);
							break;
						}
						else 
						{
							if(ger == v.getLastge())
							{
								allottedProgram.put(id,choiceList[i]);
								break;
							}
						}
					}
					// trying to allot OBC PD seat.
					double obcpdr = c.getobcpdR();
					if(obcpdr>0)
					{
						if(v.getObcpdq() >0)
						{
							allottedProgram.put(id,choiceList[i]);
							v.setObcpdq(v.getObcpdq()-1);
							v.setLastobcpd(obcpdr);
							break;
						}
						else if (obcpdr == v.getLastobcpd())
						{
							allottedProgram.put(id,choiceList[i]);
							break;
						}
					}
					// trying to allot SC PD Seat
					double scpdr = c.getscpdR();
					if(scpdr>0)
					{
						if(v.getScpdq() >0)
						{
							allottedProgram.put(id,choiceList[i]);
							v.setScpdq(v.getScpdq()-1);
							v.setLastscpd(scpdr);
							break;
						}
						else if (scpdr == v.getLastscpd())
						{
							allottedProgram.put(id,choiceList[i]);
							break;
						}
					}
					// trying to allot ST PD Seat
					double stpdr = c.getstpdR();
					if(stpdr>0)
					{
						if(v.getStpdq() >0)
						{
							allottedProgram.put(id,choiceList[i]);
							v.setStpdq(v.getStpdq()-1);
							v.setLaststdpd(stpdr);
							break;
						}
						else if (stpdr == v.getLaststpd())
						{
							allottedProgram.put(id,choiceList[i]);
							break;
						}
					}
				}
			}
			
			
			// Dereservation.
			for (VirtualProgramme v : programs)
			{
				int w = v.getGeq()+v.getObcpdq()+v.getObcq()+v.getGepdq();
				v.setGeq(w);
				w = v.getScpdq()+v.getScpdq();
				v.setScq(w);
				w = v.getStpdq()+v.getStq();
				v.setStq(w);
			}
		}
		
		
		// Printing output to the file 
				try {
					 
					File file = new File(directoryAddress+"/outputml.csv");
		 
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
		 
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("id,program"); // header.
					bw.newLine();
					String content = new String();
					for (String s :studentsIds)
					{
						content = s+","+(allottedProgram.get(s));
						if(allottedProgram.get(s).length() == 0 )
							content = content+"-1";
						bw.write(content);
						bw.newLine();
					}
					bw.close();
		 
				} catch (IOException e) {
					e.printStackTrace();
				}

	}
	
	// Done ! 
}
