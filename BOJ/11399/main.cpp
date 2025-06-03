#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

int main()
{
	std::ios::sync_with_stdio(0); std::cin.tie(0); std::cout.tie(0);

	int N;
	std::cin >> N;

	std::vector<int> withdrawTimeVec(N + 1);
	for(int loop = 1; loop < N + 1; ++loop)
		std::cin >> withdrawTimeVec[loop];
	
	int waitTime;
	for(int sortedRange = 1; sortedRange < N + 1; ++sortedRange)
	{
		for(int loop = 1; loop < sortedRange; ++loop)
		{
			if(withdrawTimeVec[sortedRange] > withdrawTimeVec[loop])
			{
				waitTime = withdrawTimeVec[sortedRange];
				withdrawTimeVec.erase(withdrawTimeVec.begin() + sortedRange);
				withdrawTimeVec.insert(withdrawTimeVec.begin() + loop, waitTime);
				break;
			}
		}
	}

	int answer = 0;
	for(int loop = 1; loop < N + 1; ++loop)
	{
		answer += withdrawTimeVec[loop] * loop;
	}
	std::cout << answer << '\n';
}

