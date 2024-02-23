#include <iostream>
#include <algorithm>
#include <vector>
#include <stack>
#include <queue>
#include <string>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n;
	std::cin >> n;

	std::stack<int> numStack;
	std::queue<int> pushNumQueue;
	std::string cmdStr = "";

	numStack.push(0);
	for(int loop = 1; loop <= n; ++loop)
		pushNumQueue.push(loop);

	int inputNum;
	for(int loop = 0; loop < n; ++loop)
	{
		std::cin >> inputNum;
		if(inputNum > numStack.top())
		{
			while(numStack.top() < inputNum)
			{
				cmdStr.push_back('+');
				numStack.push(pushNumQueue.front());
				pushNumQueue.pop();
			}
			cmdStr.push_back('-');
			numStack.pop();
		}
		else
		{
			if(numStack.top() == inputNum)
			{
				cmdStr.push_back('-');
				numStack.pop();
			}
			else
			{
				cmdStr.clear();
				std::cout << "NO" << '\n';
				break;
			}
		}
	}

	if(!cmdStr.empty())
	{
		for(int loop = 0; loop < cmdStr.length(); ++loop)
		{
			std::cout << cmdStr[loop] << '\n';
		}
	}
}

// 굳이 pushNumQueue를 사용하지 않았어도 됬었던것 같다. (2회차 풀이때 반영할 것)