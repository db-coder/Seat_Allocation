package galeShapley;

import java.util.ArrayList;

public class VirtualProgramme {
	/**
	 * Unique id
	 */
	private String id;
	/**
	 * Quota of the Program
	 */
	private int quota;
	/**
	 * Category : 1-GE,2-OBC,3-SC,4-ST, 5-GEPD,6-OBCPD,7-SCPD,8-STPD
	 */
	private int cat;
	/**
	 * Present Waiting List
	 */
	private ArrayList<String> waitingList;
	
	/**
	 * Constructor setting the values of id,quota and category
	 * @param i id
	 * @param q quota
	 * @param c category
	 */
	public VirtualProgramme(String i,int q,int c)
	{
		id=i;
		quota=q;
		cat=c;
		waitingList = new ArrayList<String>();
	}
	/**
	 * Adding a candidate to the waiting list
	 * @param s id of the candidate
	 */
	public void addCandidate(String s)
	{
		waitingList.add(s);
	}
	/**
	 * Obtaining the present wait list.
	 * @return wait list
	 */
	public ArrayList<String> getWL()
	{
		return waitingList;
	}
	/**
	 * Returns the quota
	 * @return quota
	 */
	public int getQuota()
	{
		return quota;
	}
	/**
	 * Returns the category
	 * @return category
	 */
	public int getCat()
	{
		return cat;
	}
	/**
	 * Setting the waitlist. 
	 * <p>
	 * Used when removing some candidates.
	 * The new candidates are kept in another ArrayList and then this function is called.
	 * <p>
	 * @param newwl Updated wait list.
	 */
	public void setWL(ArrayList<String> newwl)
	{
		waitingList = newwl;
	}
	
}
