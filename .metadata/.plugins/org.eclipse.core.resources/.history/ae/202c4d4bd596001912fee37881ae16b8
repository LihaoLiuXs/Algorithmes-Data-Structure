
public class BestTimetoBuyandSellStockIII {

	public static void main(String[] args) {
		//Say you have an array for which the ith element is the price of a given stock on day i.
		//Design an algorithm to find the maximum profit. You may complete at most two transactions.
		//
		//2 pass to store the left max and right max, and then use another 1 pass to find max
		//finally, optimize the code to 2 pass, combine the last 2 for loop
		int[] input = {3,3,5,0,0,3,1,4};
		System.out.println(maxProfit(input));
		int[] input2 = {3};
		System.out.println(maxProfit(input2));
		int[] input3 = {1,2,3,4,5};
		System.out.println(maxProfit(input3));
	}

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int L = prices.length; 
        int min = prices[0]; 
        int[] leftDp = new int[L]; 
        
        for (int i = 1; i < L; i++) {
            min = Math.min(min, prices[i]); 
            leftDp[i] = Math.max(leftDp[i-1], prices[i] - min); 
        }
        
        int max = prices[L-1];
        int[] rightDp = new int[L];  
        int profit = 0;
        for (int i = L-2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            rightDp[i] = Math.max(rightDp[i+1], max - prices[i]); 
            profit = Math.max(profit, rightDp[i] + leftDp[i]); 
        }
        
        //int profit = 0;
        //for (int i = 0; i < L; i++) {
        //    profit = Math.max(profit, rightDp[i] + leftDp[i]); 
        //}
        return profit; 
    }
}
