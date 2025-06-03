#include <iostream>
#include <vector>

void swap(int& a, int& b)
{
	int temp = a;
	a = b;
	b = temp;
}

int main()
{
	std::ios::sync_with_stdio(0); std::cin.tie(0); std::cout.tie(0);

	int N;
	std::cin >> N;

	std::vector<int> numVec;
	
	int inputNum;
	for(int loop = 0; loop < N; ++loop)
	{
		std::cin >> inputNum;
		numVec.push_back(inputNum);
	}

	for(int unsorted = N; unsorted > 1; --unsorted)
	{
		for(int loop = 0; loop < unsorted - 1; ++loop)
		{
			if(numVec[loop] > numVec[loop + 1])
				swap(numVec[loop], numVec[loop + 1]);
		}
	}

	for(const int& num : numVec)
	{
		std::cout << num << '\n';
	}
}