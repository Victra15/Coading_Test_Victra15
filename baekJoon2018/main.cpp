#include <iostream>
#include <vector>
#include <map>
#include <string>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int num;

	std::cin >> num;

	int total;
	int startNum = 1;
	int endNum = 1;
	
	int seqSumCnt = 0;
	while(startNum <= num)
	{
		total = (endNum - startNum + 1) * (endNum + startNum) / 2;
		if (total > num)
			++startNum;
		else
			++endNum;

		if (total == num)
			++seqSumCnt;
	}

	std::cout << seqSumCnt;
}