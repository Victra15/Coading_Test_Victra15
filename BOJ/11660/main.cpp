#include <iostream>
#include <vector>
#include <string>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int size;
	int sumCnt;
	int numInput;

	std::cin >> size >> sumCnt;

	std::vector<std::vector<int>> sumMap(size + 1, std::vector<int>(size + 1, 0));
	
	for(int yIdx = 1; yIdx < size + 1; ++yIdx)
	{
		for(int xIdx = 1; xIdx < size + 1; ++xIdx)
		{
			std::cin >> numInput;
			sumMap[yIdx][xIdx] = sumMap[yIdx][xIdx - 1] + numInput;
		}

		for(int xIdx = 1; xIdx < size + 1; ++xIdx)
		{
			sumMap[yIdx][xIdx] = sumMap[yIdx - 1][xIdx] + sumMap[yIdx][xIdx];
		}
	}

	int yIdx1, xIdx1;
	int yIdx2, xIdx2;
	for(int loop = 0; loop < sumCnt; ++loop)
	{
		std::cin >> xIdx1 >> yIdx1 >> xIdx2 >> yIdx2;
		--xIdx1;
		--yIdx1;

		std::cout << sumMap[xIdx1][yIdx1] + sumMap[xIdx2][yIdx2] - sumMap[xIdx2][yIdx1] - sumMap[xIdx1][yIdx2] << '\n';
	}
}