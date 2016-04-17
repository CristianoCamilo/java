package br.com.cvc.expedia;

public class RegionSun extends Region{
	
	private double pontosSimilaridade;

	public RegionSun(int regionId, String regionName, int qtdHotel) {
		super(regionId, regionName, qtdHotel);
		// TODO Auto-generated constructor stub
	}

	public double getPontosSimilaridade() {
		return pontosSimilaridade;
	}

	public void setPontosSimilaridade(double pontosSimilaridade) {
		this.pontosSimilaridade = pontosSimilaridade;
	}
	
	

}
