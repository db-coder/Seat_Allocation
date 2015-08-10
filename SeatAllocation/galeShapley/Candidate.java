package galeShapley;

import java.util.ArrayList;

public class Candidate {
	/**
	 * Unique id of the candidate.
	 */
	String id;
	/**
	 * choice list of the candidate
	 */
	ArrayList<String> choices;
	/**
	 * category 1 :gen,2:obc,3:sc,4:st,5 :gepd,6-obcpd,7-scpd,8-stpd 
	 */
	int cat; 
	/**
	 * true if PD false otherwise
	 */
	boolean pd; 
	/**
	 * Corresponding ranks.
	 */
	double ger,obcr,scr,str,gepdr,obcpdr,scpdr,stpdr;
	/**
	 * Index of processing in the GS Algorithm
	 */
	int gsIndex;
	/**
	 * true if currently can apply to the next index, false otherwise
	 */
	boolean rejected;
	/**
	 * Constuctor given all the parameters.
	 * @param s the unique id. 
	 * @param g General Rank
	 * @param o OBC Rank
	 * @param sc SC Rank 
	 * @param st ST Rank
	 * @param gp General PD Rank
	 * @param op OBC PD Rank
	 * @param scp SC PD Rank
	 * @param stp ST PD Rank
	 */
	Candidate(String s,double g,double o,double sc,double st,double gp,double op,double scp,double stp)
	{
		id=s;
		rejected=true; gsIndex=0;// By default, the candidate can apply to the first preference.
		ger=g;obcr=o;scr=sc;str=st;
		gepdr=gp;obcpdr=op;scpdr=scp;stpdr=stp;
		setCat();
	}
	/**
	 * Setting the category of the candidate using the ranks in 8 categories.
	 */
	void setCat()
	{
		if(obcpdr > 0)
		{
			cat=6;
			pd=true;
			return;
		}
		if(scpdr > 0)
		{
			cat=7;
			pd=true;
			return;
		}
		if(stpdr > 0)
		{
			cat=8;
			pd=true;
			return;
		}
		if(obcr > 0)
		{
			cat=2;
			pd=false;
			return;
		}
		if(scr >0)
		{
			cat=3;
			pd=false;
			return;
		}
		if(str>0)
		{
			cat=4;
			pd=false;
			return;
		}
		if(gepdr >0)
		{
			cat=5;
			pd=true;
			return;
		}
		cat=1;
		pd=false;
	}
	/**
	 * Sets the id
	 * @param w updated id
	 */
	void setId(String w)
	{
		id=w;
	}
	/**
	 * Sets the category
	 * @param x updated category
	 */
	void setCategory(int x) // 1<=x<=8
	{
		cat=x;
	}
	/**
	 * Sets the choices taking the original string as parameter 
	 * <p>
	 * Adding all categories corresponding to the choices he can apply.
	 * <p>
	 * @param choice raw input i.e in the form of B1_B2
	 */
	void setChoices(String choice) // Recognising patterns.
	{
		choices = new ArrayList<String>();
		String[] individualChoices;
		individualChoices = choice.split("_");
		for(int i=0;i<individualChoices.length;i++)
		{
			String code = individualChoices[i];
			switch(cat)
			{
			case 1 :  // General can apply to GE > OBC > GE-PD > OBC-PD
				String newCode;
				newCode=code+"GE";
				choices.add(newCode);
				newCode=code+"OBC";
				choices.add(newCode);
				newCode = code + "GEPD";
				choices.add(newCode);
				newCode = code + "OBCPD";
				choices.add(newCode);
				break;
			case 2: // OBC can apply to GE > OBC > GE-PD > OBC-PD
				newCode=code+"GE";
				choices.add(newCode);
				newCode=code+"OBC";
				choices.add(newCode);
				newCode = code + "GEPD";
				choices.add(newCode);
				newCode = code + "OBCPD";
				choices.add(newCode);
				break;
			case 3: // SC can apply to GE > SC > OBC > GE-PD > OBC-PD > SC-PD
				newCode=code+"GE";
				choices.add(newCode);
				newCode = code + "SC";
				choices.add(newCode);
				newCode=code+"OBC";
				choices.add(newCode);
				newCode = code + "GEPD";
				choices.add(newCode);
				newCode = code + "OBCPD";
				choices.add(newCode);
				newCode = code + "SCPD";
				choices.add(newCode);
				break;
			case 4: // ST can apply to GE > ST > OBC > GE-PD > OBC-PD > ST-PD
				newCode=code+"GE";
				choices.add(newCode);
				newCode = code + "ST";
				choices.add(newCode);
				newCode=code+"OBC";
				choices.add(newCode);
				newCode = code + "GEPD";
				choices.add(newCode);
				newCode = code + "OBCPD";
				choices.add(newCode);
				newCode = code + "STPD";
				choices.add(newCode);
				break;
			case 5: // GE PD can apply to GE > GE-PD > OBC > OBC-PD
				newCode=code+"GE";
				choices.add(newCode);
				newCode = code + "GEPD";
				choices.add(newCode);
				newCode=code+"OBC";
				choices.add(newCode);
				newCode = code + "OBCPD";
				choices.add(newCode);
				break;
			case 6: // OBC PD can apply to GE > GE-PD > OBC > OBC-PD
				newCode=code+"GE";
				choices.add(newCode);
				newCode = code + "GEPD";
				choices.add(newCode);
				newCode=code+"OBC";
				choices.add(newCode);
				newCode = code + "OBCPD";
				choices.add(newCode);
				break;
			case 7: // SC PD can apply to GE > SC > GE-PD > SC-PD > OBC > OBC-PD
				newCode=code+"GE";
				choices.add(newCode);
				newCode = code + "SC";
				choices.add(newCode);
				newCode = code + "GEPD";
				choices.add(newCode);
				newCode = code + "SCPD";
				choices.add(newCode);
				newCode=code+"OBC";
				choices.add(newCode);
				newCode = code + "OBCPD";
				choices.add(newCode);
				break;
			case 8: // ST PD can apply to GE > ST > GE-PD > ST-PD > OBC > OBC-PD
				newCode=code+"GE";
				choices.add(newCode);
				newCode = code + "ST";
				choices.add(newCode);
				newCode = code + "GEPD";
				choices.add(newCode);
				newCode = code + "STPD";
				choices.add(newCode);
				newCode=code+"OBC";
				choices.add(newCode);
				newCode = code + "OBCPD";
				choices.add(newCode);
				break;
			}
			
		}
	}
	/**
	 * Returns the GS index.
	 * @return GS present level
	 */
	public int  getIndex()
	{
		return gsIndex;
	}
	/**
	 * true,if the candidate can apply now, false otherwise.
	 * @return applying status
	 */
	public boolean getRejected()
	{
		return rejected;
	}
	/**
	 * Updating gsIndex & rejected when a candidate is rejected by a program.
	 */
	public void rejected()
	{
		gsIndex = gsIndex+1;
		if(gsIndex >= choices.size())
		{
			gsIndex=-1;
			rejected=false;
		}
		rejected=true;
	}
	/**
	 * Updating rejected when a candidate is wait listed.
	 */
	public void accepted()
	{
		rejected = false;
	}
	/**
	 * To get a code of the program that the candidate applies to at present
	 * @param x  gsIndex 
	 * @return string corresponding to the program the candidate applies now.
	 */
	public String getCourse(int x)
	{
		return choices.get(x);
	}
	/**
	 * Returns the General Rank
	 * @return GE Rank
	 */
	public double getGer()
	{
		return ger;
	}
	/**
	 * Returns the OBC Rank
	 * @return OBC Rank
	 */
	public double getObcr()
	{
		return obcr;
	}
	/**
	 * Returns the SC Rank
	 * @return SC Rank
	 */
	public double getScr()
	{
		return scr;
	}
	/**
	 * Returns the ST Rank
	 * @return ST Rank
	 */
	public double getStr()
	{
		return str;
	}
	/**
	 * Returns the General PD Rank
	 * @return GE PD Rank
	 */
	public double getGepdr()
	{
		return gepdr;
	}
	/**
	 * Returns the OBC PD Rank
	 * @return OBC PD Rank
	 */
	public double getObcpdr()
	{
		return obcpdr;
	}
	/**
	 * Returns the SC PD Rank
	 * @return SC PD Rank
	 */
	public double getScpdr()
	{
		return scpdr;
	}
	/**
	 * Returns the ST PD Rank
	 * @return ST PD Rank
	 */
	public double getStpdr()
	{
		return stpdr;
	}
	
	/**
	 * Getting the choice list of the candidate
	 * @return Choice List
	 */
	public ArrayList<String> getChoiceList()
	{
		return choices;
	}
}
