
public class EditDistance {

	public static void main(String[] args) {//Lihao Liu 
		//Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
		//You have the following 3 operations permitted on a word:
		//1.Insert a character
		//2.Delete a character
		//3.Replace a character
		System.out.println(minDistance("horse", "ros"));
		System.out.println(minDistance("intention", "execution"));
	}
    public static int minDistance(String word1, String word2) {
        int length1 = word1.length(); 
        int length2 = word2.length();
        int[][] dp = new int[length1+1][length2+1];
        for (int i = 0; i <= length1; i++){
            for (int j = 0; j <= length2; j++){
                if (i == 0){
                    dp[i][j] = j;
                }
                else if (j == 0){
                    dp[i][j] = i; 
                }
                else if (word1.charAt(i-1) == word2.charAt(j-1)){
                     dp[i][j] = dp[i-1][j-1];
                }
                else {
                     dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]);
                     dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[length1][length2]; 
    }
}
