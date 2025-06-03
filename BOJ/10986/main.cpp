#include <iostream>
#include <vector>
#include <map>
#include <string>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	// Input
	int numCnt;
	int modNum;

	std::cin >> numCnt >> modNum;

	std::vector<int> numVec(numCnt + 1);
	std::map<int, int> modEqualCnt;
	int numInput;
	
	for(int loop = 1; loop < numCnt + 1; ++loop)
	{
		std::cin >> numInput;
		numVec[loop] = (numVec[loop - 1] + numInput) % modNum;
		modEqualCnt[numVec[loop]]++;
	}
	
	// Solve
	long long zeroCnt = 0;
	for(std::pair<int, int> num : modEqualCnt)
	{
		if(num.first == 0)
			zeroCnt += num.second;
		if(num.second > 1)
			zeroCnt += ((long long)num.second * (long long)(num.second - 1)) / 2;
	}
		
	std::cout << zeroCnt;
}