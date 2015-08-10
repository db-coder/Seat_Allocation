package seatAllocation;


public class Candidate {
	/**
	 * The unique string of the candidate.
	 */
	String uniqueID; 
	/**
	 * Corresponding ranks.
	 */
	double geR,obcR,scR,stR,gepdR,obcpdR,scpdR,stpdR; // ranks. 0 if not eligible.

	
	/**
	 * Default Constructor.
	 */
	public Candidate()
	{
		uniqueID="";
		geR=obcR=scR=stR=gepdR=obcpdR=scpdR=stpdR=0.0;
	}
	/**
	 * Constructor taking all the values as input.
	 * @param id the unique id.
	 * @param geR the general rank
	 * @param obcR OBC Rank
	 * @param scR SC Rank 
	 * @param stR ST Rank
	 * @param gepdR GEPD Rank
	 * @param obcpdR OBC PD Rank
	 * @param scpdR SC PD Rank
	 * @param stpdR ST PD Rank
	 */
	public Candidate(String id,double geR,double obcR,double scR,double stR,double gepdR,double obcpdR,double scpdR,double stpdR)
	{
		uniqueID=id;
		this.geR=geR;
		this.obcR=obcR;
		this.gepdR=gepdR;
		this.obcpdR=obcpdR;
		this.scpdR=scpdR;
		this.stpdR=stpdR;
	}
	

	// Setting functions.
	/**
	 * Setting id
	 * @param s the id
	 */
	public void setUniqueID(String s)
	{
		uniqueID=s;
	}
	/**
	 * Setting General Rank
	 * @param r General Rank
	 */
	public void setgeR(double r)
	{
		geR=r;
	}
	/**
	 * Setting OBC Rank
	 * @param r OBC Rank
	 */
	public void setobcR(double r)
	{
		obcR=r;
	}
	/**
	 * Setting SC Rank
	 * @param r SC Rank
	 */
	public void setscR(double r)
	{
		scR=r;
	}
	/**
	 * Setting ST Rank
	 * @param r ST Rank
	 */
	public void setstR(double r)
	{
		stR=r;
	}
	/**
	 * Setting GE PD Rank
	 * @param r GE PD Rank
	 */
	public void setgepdR(double r)
	{
		gepdR=r;
	}
	/**
	 * Setting OBC PD Rank
	 * @param r OBC PD Rank
	 */
	public void setobcpdR(double r)
	{
		obcpdR=r;
	}
	/**
	 * Setting SC PD Rank
	 * @param r SC PD Rank
	 */
	public void setscpdR(double r)
	{
		scpdR=r;
	}
	/**
	 * Setting ST PD Rank
	 * @param r ST PD Rank
	 */
	public void setstpdR(double r)
	{
		stpdR=r;
	}

	// Get functions.
	/**
	 * Getting the string id
	 * @return unique id
	 */
	public String getUniqueID()
	{
		return uniqueID;
	}
	/**
	 * Returns General Rank
	 * @return GE Rank
	 */
	public double getgeR()
	{
		return geR;
	}
	/**
	 * Returns OBC Rank
	 * @return OBC Rank
	 */
	public double getobcR()
	{
		return obcR;
	}
	/**
	 * Returns SC Rank
	 * @return SC Rank
	 */
	public double getscR()
	{
		return scR;
	}
	/**
	 * Returns ST Rank
	 * @return ST Rank
	 */
	public double getstR()
	{
		return stR;
	}
	/**
	 * Returns General PD Rank
	 * @return GE PD Rank
	 */
	public double getgepdR()
	{
		return gepdR;
	}
	/**
	 * Returns OBC PD Rank
	 * @return OBC PD Rank
	 */ 
	public double getobcpdR()
	{
		return obcpdR;
	}
	/**
	 * Returns SC PD Rank
	 * @return SC PD Rank
	 */
	public double getscpdR()
	{
		return scpdR;
	}
	/**
	 * Returns ST PD Rank
	 * @return ST PD Rank
	 */
	public double getstpdR()
	{
		return stpdR;
	}
}
