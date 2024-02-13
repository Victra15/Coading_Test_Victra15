#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int N;
	std::cin >> N;

	int inputNum;
	std::vector<int> numVec;
	
	for(int loop = 0; loop < N; ++loop)
	{
		std::cin >> inputNum;
		numVec.push_back(inputNum);
	}

	std::sort(numVec.begin(), numVec.end());

	int goodCnt = 0;
	int twoNumSum;
	for(int loop = 0; loop < numVec.size(); ++loop)
	{
		int startIdx = 0;
		int endIdx = numVec.size() - 1;

		if(startIdx == loop)
			startIdx++;
		if(endIdx == loop)
			endIdx--;

		while(startIdx < endIdx)
		{
			twoNumSum = numVec[startIdx] + numVec[endIdx];
			if(twoNumSum == numVec[loop])
			{
				goodCnt++;
				break;
			}
			else if (twoNumSum > numVec[loop])
				endIdx--;
			else
				startIdx++;


			if(startIdx == loop)
				startIdx++;
			if(endIdx == loop)
				endIdx--;
		}
	}
	

	std::cout << goodCnt;
}