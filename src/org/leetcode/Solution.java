package org.leetcode;

/**
 * leetcode problem
 * @author 龙卫兵
 * @since 2018.3.22
 */
class Solution {
    public String countAndSay(int n) {
        String output = "1";
        for (int i = 1; i < n; i++) {
            String temp = "";
            for (int j = 0; j < output.length(); j++) {           
                int m = 1;
                String x = String.valueOf(output.charAt(j));
                for (int k = j + 1; k < output.length(); k++) {
                    if (output.charAt(j) == output.charAt(k)) {
                        m++;
                        j++;
                    } else {
                        break;
                    }
                }
                temp += m + x;
            }
            output = temp;
        }
        return output;
    }
  
    public static void main(String[] args) {
		Solution s = new Solution();
		String x = "哈哈";
		System.out.println(s.countAndSay(6));
		System.out.println("x.intern():" + x.intern());
	}  
}