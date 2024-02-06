#include <iostream>
#include <vector>

int main()
{
	std::ios::sync_with_stdio(0); std::cin.tie(0); std::cout.tie(0);


	int numCnt;
	int sumCnt;
	
	std::vector<int> sumVec;

	std::cin >> numCnt >> sumCnt;

	int num;
	int sum = 0;
	sumVec.push_back(0);
	for(int loop = 0; loop < numCnt; ++loop)
	{
		std::cin >> num;
		sum += num;

		sumVec.push_back(sum);
	}

	int indexI;
	int indexJ;
	for(int loop = 0; loop < sumCnt; ++loop)
	{
		std::cin >> indexI >> indexJ;
		indexI--;

		std::cout << sumVec[indexJ] - sumVec[indexI] << '\n';
	}
}