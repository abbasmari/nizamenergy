package bean;

/**
 * @author NizamEnergy
 *
 */
public class OccupationBean {
	
	private int occupation_id;
	private String occupation_name;
	public OccupationBean() {
		// TODO Auto-generated constructor stub
	}
	public int getOccupation_id() {
		return occupation_id;
	}
	public void setOccupation_id(int occupation_id) {
		this.occupation_id = occupation_id;
	}
	public String getOccupation_name() {
		return occupation_name;
	}
	public void setOccupation_name(String occupation_name) {
		this.occupation_name = occupation_name;
	}
	@Override
	public String toString() {
		return "OccupationBean [occupation_id=" + occupation_id
				+ ", occupation_name=" + occupation_name + "]";
	}
	
	
}
