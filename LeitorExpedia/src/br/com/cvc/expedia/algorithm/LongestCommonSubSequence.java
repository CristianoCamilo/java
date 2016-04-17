package br.com.cvc.expedia.algorithm;

import br.com.cvc.expedia.Algorithm;

public class LongestCommonSubSequence extends Algorithm{
	
    /**
     * Return the LCS distance between strings s1 and s2, computed as |s1| +
     * |s2| - 2 * |LCS(s1, s2)|
     *
     * @param s1
     * @param s2
     * @return the LCS distance between strings s1 and s2, computed as |s1| +
     * |s2| - 2 * |LCS(s1, s2)|
     */
    public double distance(String s1, String s2) {
        return s1.length() + s2.length() - 2 * length(s1, s2);
    }

    /**
     * Return the length of Longest Common Subsequence (LCS) between strings s1
     * and s2.
     *
     * @param s1
     * @param s2
     * @return the length of LCS(s1, s2)
     */
    protected int length(String s1, String s2) {
        /* function LCSLength(X[1..m], Y[1..n])
         C = array(0..m, 0..n)
        
         for i := 0..m
         C[i,0] = 0
        
         for j := 0..n
         C[0,j] = 0
        
         for i := 1..m
         for j := 1..n
         if X[i] = Y[j]
         C[i,j] := C[i-1,j-1] + 1
         else
         C[i,j] := max(C[i,j-1], C[i-1,j])
         return C[m,n]
         */
        int m = s1.length();
        int n = s2.length();
        char[] X = s1.toCharArray();
        char[] Y = s2.toCharArray();

        int[][] C = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            C[i][0] = 0;
        }

        for (int j = 0; j <= n; j++) {
            C[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X[i - 1] == Y[j - 1]) {
                    C[i][j] = C[i - 1][j - 1] + 1;

                } else {
                    C[i][j] = Math.max(C[i][j - 1], C[i - 1][j]);
                }
            }
        }

        return C[m][n];
    }

	@Override
	public double similarity(String s1, String s2) {
		// TODO Auto-generated method stub
		return this.distance(s1, s2);
	}

}
