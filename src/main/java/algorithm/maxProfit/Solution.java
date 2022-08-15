package algorithm.maxProfit;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-12  18:03
 * @Description TODO
 */
class Solution {
    public int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i=0;i < prices.length;i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit,prices[i] - minPrice);
            }
        }
        return maxProfit;
    }
}