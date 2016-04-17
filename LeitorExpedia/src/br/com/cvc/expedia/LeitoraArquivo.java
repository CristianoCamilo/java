package br.com.cvc.expedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class LeitoraArquivo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LeitoraArquivo l = new LeitoraArquivo();
		String path = l.alimentaArquivo();
		Mapping.getInstance().ordernar();
		l.escreverResposta(path);
		System.out.println("# FIM #");
	}
	
	private void escreverResposta(String path) {
		try(Writer arquivo  = new FileWriter(new File(path+"resolved.csv" ))){
			System.out.println("Escrevendo arquivo em...: " + path);
			arquivo.write("ParentRegionID;ParentRegionNameLong;ParentHotels;RegionID;RegionNameLong;Hotels");
			Mapping.getInstance().getRegioes().keySet().forEach( o -> { try {
				arquivo.write( this.geraLinha(o) );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} } );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	private String geraLinha(RegionFather o) {
		return "\n" +
				o.getRegionId() + ";" + 
				o.getRegionName() + ";" + 
				o.getQtdHotel() + ";" +
				o.getSub().getRegionId() + ";" + 
				o.getSub().getRegionName() + ";" + 
				o.getSub().getQtdHotel();
	}

	public String alimentaArquivo(){
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.showOpenDialog(null);
		File arquivo = jfc.getSelectedFile();
		try {
			Scanner leitora = new Scanner(arquivo);
			leitora.nextLine();
			while(leitora.hasNextLine()){
				String line = leitora.nextLine();
				this.alimentaLinha(line);
			}
			leitora.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arquivo.getPath();
		
	}

	private void alimentaLinha(String line) {
		String[] itens = line.split(";");
		int region = Integer.parseInt(itens[0]);
		String name = itens[1];
		int qtdHotel = Integer.parseInt(itens[2]);
		int subRegion = Integer.parseInt(itens[3]);
		String subName = itens[4];
		int subQtdHotel = Integer.parseInt(itens[5]);
		RegionFather vicinity = new RegionFather(region, name, qtdHotel);
		RegionSun city = new RegionSun(subRegion, subName, subQtdHotel);
		this.addOrUpdate(vicinity, city);
	}

	private void addOrUpdate(RegionFather vicinity, RegionSun city) {		
		if(Mapping.getInstance().getRegioes().get(vicinity)==null){
			List<RegionSun> array = new ArrayList<>();
			array.add(city);
			Mapping.getInstance().getRegioes().put(vicinity, array );
		} else {
			Mapping.getInstance().getRegioes().get(vicinity).add(city);
		}
	}

}
