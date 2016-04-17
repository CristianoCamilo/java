package br.com.cvc.expedia;


public abstract class Region {
	
	private int regionId;
	private String regionName;
	private int qtdHotel;
	
	public Region(int regionId, String regionName, int qtdHotel) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.qtdHotel = qtdHotel;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public int getQtdHotel() {
		return qtdHotel;
	}
	public void setQtdHotel(int qtdHotel) {
		this.qtdHotel = qtdHotel;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + regionId;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		if (regionId != other.regionId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  regionName + "[" + this.regionId + "]";
	}
	
	
	

}
