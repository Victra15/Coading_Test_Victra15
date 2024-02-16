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

	int N, L;
	std::cin >> N >> L;
	std::vector<int> numVec;
	std::vector<int> DVec(N, 0);
	int inputNum;

	for(int loop = 0; loop < N; loop++)
	{
		std::cin >> inputNum;
		numVec.push_back(inputNum);
	}
	
	std::priority_queue<int, std::vector<int>, std::greater<int>> DQueue;
	for(int loop = 0; loop < L; loop++)
	{
		DQueue.push(numVec[loop]);
		DVec[loop] = DQueue.top();
	}
	
	std::priority_queue<int, std::vector<int>, std::greater<int>> delQueue;
	for(int loop = L; loop < N; loop++)
	{
		DQueue.push(numVec[loop]);
		if(DQueue.top() == numVec[loop - L])
		{
			DQueue.pop();
			while(!delQueue.empty())
			{
				if (DQueue.top() == delQueue.top())
				{
					DQueue.pop();
					delQueue.pop();
				}
				else
					break;
			}
		}
		else
		{
			delQueue.push(numVec[loop - L]);
		}

		DVec[loop] = DQueue.top(); 
	}

	for(const int& num : DVec)
		std::cout << num << " ";
	std::cout << "\n";

}