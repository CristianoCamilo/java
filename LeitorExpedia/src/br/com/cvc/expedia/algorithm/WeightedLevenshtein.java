package br.com.cvc.expedia.algorithm;

import br.com.cvc.expedia.Algorithm;

public class WeightedLevenshtein extends Algorithm{
	

    private final CharacterSubstitutionInterface charsub;

    public WeightedLevenshtein(CharacterSubstitutionInterface charsub) {
        this.charsub = charsub;
    }

    public double similarity(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }

        if (s1.length() == 0) {
            return s2.length();
        }

        if (s2.length() == 0) {
            return s1.length();
        }

        // create two work vectors of integer distances
        double[] v0 = new double[s2.length() + 1];
        double[] v1 = new double[s2.length() + 1];
        double[] vtemp;

        // initialize v0 (the previous row of distances)
        // this row is A[0][i]: edit distance for an empty s
        // the distance is just the number of characters to delete from t
        for (int i = 0; i < v0.length; i++) {
            v0[i] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            // calculate v1 (current row distances) from the previous row v0
            // first element of v1 is A[i+1][0]
            //   edit distance is delete (i+1) chars from s to match empty t
            v1[0] = i + 1;

            // use formula to fill in the rest of the row
            for (int j = 0; j < s2.length(); j++) {
                double cost = (s1.charAt(i) == s2.charAt(j)) ? 0 : charsub.cost(s1.charAt(i), s2.charAt(j));
                v1[j + 1] = Math.min(
                        v1[j] + 1, // Cost of insertion
                        Math.min(
                                v0[j + 1] + 1, // Cost of remove
                                v0[j] + cost)); // Cost of substitution
            }

            // copy v1 (current row) to v0 (previous row) for next iteration
            //System.arraycopy(v1, 0, v0, 0, v0.length);
            // Flip references to current and previous row
            vtemp = v0;
            v0 = v1;
            v1 = vtemp;

        }

        return v0[s2.length()];
    }

}
