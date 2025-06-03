#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <string>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int N;
	std::cin >> N;

	std::queue<int> numQueue;

	for(int loop = 1; loop <= N; ++loop)
	{
		numQueue.push(loop);
	}

	int popNum;
	while(numQueue.size() > 1)
	{
		numQueue.pop();
		popNum = numQueue.front();
		numQueue.pop();
		numQueue.push(popNum);
	}
	
	std::cout << numQueue.front();
}