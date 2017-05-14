public class Solution
{
	public boolean isMatch(String s, String p)
	{
		int m = s.length();
		int n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		// 确定边界调价
		// 当p.length()==0时
		for (int i = 0; i < m; i++)
		{
			dp[i + 1][0] = false;
		}
		// 当s.length()==0时
		for (int i = 0; i < n; i++)
		{
			if (i >= 1 && p.charAt(i) == '*')
			{
				dp[0][i + 1] = dp[0][i - 1];
			}
			else
			{
				dp[0][i+1] = false;
			}
		}
		/*
		 * if p[j]!='*',dp[i+1][j+1]=dp[i][j]&&(s[i]==p[j]||p[j]=='.') if
		 * p[j]=='*',then(we use x denote p[j-1]) 
		 * when x* represents 0 times of x,dp[i+1][j+1]=dp[i+1][j-1] 
		 * when x* represents 1 times of x,dp[i+1][j+1]=dp[i+1][j] 
		 * when x* represemts >=2 times of x,dp[i+1][j+1]=dp[i][j+1]&&(s[i]==p[j-1]||p[j-1]=='.')
		 */
		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (p.charAt(j) != '*')
				{
					dp[i + 1][j + 1] = dp[i][j] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
				}
				else
				{
					dp[i + 1][j + 1] = (j >= 1 && dp[i + 1][j - 1]) || (dp[i + 1][j])
							|| (dp[i][j + 1] && (s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.'));
				}
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args)
	{
		System.out.println(new Solution().isMatch("aa", ".*"));
	}
}