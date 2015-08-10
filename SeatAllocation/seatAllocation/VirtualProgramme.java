package seatAllocation;


public class VirtualProgramme {
	/**
	 * The Unique Code of the Program.
	 */
	private String id; 
	/**
	 * Corresponding Quota
	 */
	private int geq,obcq,scq,stq,gepdq,obcpdq,scpdq,stpdq;
	/**
	 * Last Rank of the candidates who got this Program.
	 */
	private double lastge,lastobc,lastsc,lastst,lastgepd,lastobcpd,lastscpd,laststpd;
	
	/**
	 * Constructor Given all the values.
	 * @param id The Unique Code of the Program.
	 * @param g General Quota
	 * @param o OBC Quota
	 * @param sc SC Quota
	 * @param st ST Quota
	 * @param gp GEPD Quota
	 * @param op OBCPD Quota
	 * @param scp SCPD Quota
	 * @param stp STPD Quota
	 */
	public VirtualProgramme(String id,int g,int o,int sc,int st,int gp,int op,int scp,int stp)
	{
		this.id=id;
		geq=g;obcq=o;scq=sc;stq=st;
		gepdq=gp;obcpdq=op;scpdq=scp;stpdq=stp;
		lastge=lastobc=lastsc=lastst=0;
		lastgepd=lastobcpd=lastscpd=laststpd=0;
	}
	
	// set functions.
	/**
	 * Sets the unique id
	 * @param s the id
	 */
	public void setId(String s)
	{
		this.id=s;
	}
	/**
	 * Sets the General Quota
	 * @param w Updated General Quota
	 */
	public void setGeq(int w)
	{
		geq=w;
	}
	/**
	 * Sets the OBC Quota
	 * @param w Updated OBC Quota
	 */
	public void setObcq(int w)
	{
		obcq=w;
	}
	/**
	 * Sets the SC Quota
	 * @param w Updated SC Quota
	 */
	public void setScq(int w)
	{
		scq=w;
	}
	/**
	 * Sets the ST Quota
	 * @param w Updated ST Quota
	 */
	public void setStq(int w)
	{
		stq=w;
	}
	/**
	 * Sets the General PD Quota
	 * @param w Updated General PD Quota
	 */
	public void setGepdq(int w)
	{
		gepdq=w;
	}
	/**
	 * Sets the OBC PD Quota
	 * @param w Updated OBC PD Quota
	 */
	public void setObcpdq(int w)
	{
		obcpdq=w;
	}
	/**
	 * Sets the SC PD Quota
	 * @param w Updated SC PD Quota
	 */
	public void setScpdq(int w)
	{
		scpdq=w;
	}
	/**
	 * Sets the ST PD Quota
	 * @param w Updated ST PD Quota
	 */
	public void setStpdq(int w)
	{
		stpdq=w;
	}
	
	/**
	 * Sets the Last GE Rank
	 * @param w Updated Last GE Rank
	 */
	public void setLastge(double d)
	{
		lastge=d;
	}
	/**
	 * Sets the Last OBC Rank
	 * @param w Updated Last OBC Rank
	 */
	public void setLastobc(double d)
	{
		lastobc=d;
	}
	/**
	 * Sets the Last SC Rank
	 * @param w Updated Last SC Rank
	 */
	public void setLastsc(double d)
	{
		lastsc=d;
	}
	/**
	 * Sets the Last ST Rank
	 * @param w Updated Last ST Rank
	 */
	public void setLastst(double d)
	{
		lastst=d;
	}
	/**
	 * Sets the Last GE PD  Rank
	 * @param w Updated Last GE PD Rank
	 */
	public void setLastgepd(double d)
	{
		lastgepd=d;
	}
	/**
	 * Sets the Last OBC PD Rank
	 * @param w Updated Last OBC PD Rank
	 */
	public void setLastobcpd(double d)
	{
		lastobcpd=d;
	}
	/**
	 * Sets the Last SC PD Rank
	 * @param w Updated Last SC PD Rank
	 */
	public void setLastscpd(double d)
	{
		lastscpd=d;
	}
	/**
	 * Sets the Last ST PD Rank
	 * @param w Updated Last ST PD Rank
	 */
	public void setLaststdpd(double d)
	{
		laststpd=d;
	}
	
	// get functions.
	/**
	 * returns the id of the Virtual Programme.
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}
	/**
	 * Returns General Category Quota
	 * @return GE Quota
	 */
	public int getGeq()
	{
		return geq;
	}
	/**
	 * Returns OBC Category Quota
	 * @return OBC Quota
	 */
	public int getObcq()
	{
		return obcq;
	}
	/**
	 * Returns SC Category Quota
	 * @return SC Quota
	 */
	public int getScq()
	{
		return scq;
	}
	/**
	 * Returns ST Category Quota
	 * @return ST Quota
	 */
	public int getStq()
	{
		return stq;
	}
	/**
	 * Returns General PD  Category Quota
	 * @return GE PD Quota
	 */
	public int getGepdq()
	{
		return gepdq;
	}
	/**
	 * Returns OBC PD Category Quota
	 * @return OBC PD Quota
	 */
	public int getObcpdq()
	{
		return obcpdq;
	}
	/**
	 * Returns SC PD Category Quota
	 * @return SC PD Quota
	 */
	public int getScpdq()
	{
		return scpdq;
	}
	/**
	 * Returns ST PD Category Quota
	 * @return ST PD Quota
	 */
	public int getStpdq()
	{
		return stpdq;
	}
	/**
	 * Returns the last General Rank.
	 * @return last GE Rank
	 */
	public double getLastge()
	{
		return lastge;
	}
	/**
	 * Returns the last OBC Rank.
	 * @return last OBC Rank
	 */
	public double getLastobc()
	{
		return lastobc;
	}
	/**
	 * Returns the last SC Rank.
	 * @return last SC Rank
	 */
	public double getLastsc()
	{
		return lastsc;
	}
	/**
	 * Returns the last ST Rank.
	 * @return last ST Rank
	 */
	public double getLastst()
	{
		return lastst;
	}
	/**
	 * Returns the last General PD Rank.
	 * @return last GE PD Rank
	 */
	public double getLastgepd()
	{
		return lastgepd;
	}
	/**
	 * Returns the last OBC PD Rank.
	 * @return last OBC PD Rank
	 */
	public double getLastobcpd()
	{
		return lastobcpd;
	}
	/**
	 * Returns the last SC PD Rank.
	 * @return last SC PD Rank
	 */
	public double getLastscpd()
	{
		return lastscpd;
	}
	/**
	 * Returns the last ST PD Rank.
	 * @return last ST PD Rank
	 */
	public double getLaststpd()
	{
		return laststpd;
	}
}
