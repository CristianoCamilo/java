package br.com.cvc.expedia;

public class RegionFather extends Region{
	
	private Region sub;
	
	public RegionFather(int regionId, String regionName, int qtdHotel) {
		super(regionId, regionName, qtdHotel);
		// TODO Auto-generated constructor stub
	}
	
	public Region getSub() {
		return sub;
	}
	public void setSub(Region sub) {
		this.sub = sub;
	}

}
