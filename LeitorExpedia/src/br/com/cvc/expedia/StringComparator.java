package br.com.cvc.expedia;

import br.com.cvc.expedia.algorithm.Levenshtein;

public class StringComparator {

	private static Algorithm algo;

	static{
		algo = new Levenshtein();
	}
	
	public static void changeAlgo(Algorithm algori){
		algo = algori;
	}

	public static double similarity(String s1, String s2) {
		return algo.similarity(s1, s2);
	}

	public static void printSimilarity(String s, String t) {
		System.out.println(String.format("%.3f is the similarity between \"%s\" and \"%s\"", similarity(s, t), s, t));
	}

}
