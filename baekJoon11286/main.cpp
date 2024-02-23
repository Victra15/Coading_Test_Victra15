#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <string>

struct compare
{
	bool operator()(int& a, int& b)
	{
		if(abs(a) == abs(b))
			return a > b;
		return abs(a) > abs(b);
	}
};

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int N;
	std::cin >> N;

	std::priority_queue<int, std::vector<int>, compare> numPQ;

	int inputNum;
	for(int loop = 0; loop < N; ++loop)
	{
		std::cin >> inputNum;
		if(inputNum == 0)
		{
			if(numPQ.empty())
			{
				std::cout << 0 << '\n';
			}
			else
			{
				std::cout << numPQ.top() << '\n';
				numPQ.pop();
			}
		}
		else
		{
			numPQ.push(inputNum);
		}
	}
}