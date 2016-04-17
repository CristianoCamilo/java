package br.com.cvc.expedia;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cvc.expedia.algorithm.CharacterSubstitutionInterface;
import br.com.cvc.expedia.algorithm.LongestCommonSubSequence;
import br.com.cvc.expedia.algorithm.WeightedLevenshtein;

public class Mapping {

	private Map<RegionFather, List<RegionSun>> regioes;
	private static Mapping mapp;

	private Mapping() {
		this.regioes = new HashMap<>();
	}

	public static Mapping getInstance() {
		if (mapp == null) {
			mapp = new Mapping();
		}
		return mapp;
	}

	public Map<RegionFather, List<RegionSun>> getRegioes() {
		return regioes;
	}

	public void setRegioes(Map<RegionFather, List<RegionSun>> regioes) {
		this.regioes = regioes;
	}

	public void ordernar() {
		this.regioes.keySet().forEach(o -> {
			ordenaLista(o);
		});
	}

	private void ordenaLista(RegionFather o) {
		this.regioes.get(o).forEach(i -> {
			this.alimentaSimi(o, i);
		});
		o.setSub(this.getMaior(this.regioes.get(o)));
		// System.out.println(o.getRegionName() + "\n\t" +
		// this.regioes.get(o).get(lista.iterator().next().index).getRegionName());
	}

	private Region getMaior(List<RegionSun> list) {
		list.sort(new Comparator<RegionSun>() {
			@Override
			public int compare(RegionSun o1, RegionSun o2) {
				final int MAIOR_MENOR = -1, MENOR_MAIOR = 1; 
				int order = MAIOR_MENOR;
				if (o1.getPontosSimilaridade() > o2.getPontosSimilaridade()) {
					return -1 * order;
				} else if (o1.getPontosSimilaridade() == o2.getPontosSimilaridade()) {
					if (o1.getQtdHotel() < o2.getQtdHotel()) {
						return -1 * order;
					} else {
						return 1 * order;
					}
				}
				return 1 * order;
			}
		});
		return list.get(0);
	}

	private void alimentaSimi(RegionFather v, RegionSun o) {
		
		/*
		StringComparator.changeAlgo(new WeightedLevenshtein(new CharacterSubstitutionInterface() {
			public double cost(char c1, char c2) {

				// The cost for substituting 't' and 'r' is considered
				// smaller as these 2 are located next to each other
				// on a keyboard
				if (c1 == v.getRegionName().charAt(0) && c2 == v.getRegionName().charAt(1)) {
					return 1;
				}

				// For most cases, the cost of substituting 2 characters
				// is 1.0
				return 0.9;
			}
		}));*/
		StringComparator.changeAlgo(new LongestCommonSubSequence());
		o.setPontosSimilaridade(StringComparator.similarity(v.getRegionName().replace("(and vicinity)", ""), o.getRegionName()));
		StringComparator.printSimilarity(v.getRegionName(), o.getRegionName());
	}
}
