#include <iostream>
#include <algorithm>
#include <vector>
#include <stack>
#include <string>

typedef struct s_node
{
	int idx;
	int num;
}t_node;

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int N;
	std::cin >> N;

	std::stack<t_node> numStack;
	std::vector<int> ngeVec(N, -1);
	int inputNum;

	for(int loop = 0; loop < N; ++loop)
	{
		std::cin >> inputNum;
		while(!numStack.empty() && numStack.top().num < inputNum)
		{
			ngeVec[numStack.top().idx] = inputNum;
			numStack.pop();
		}
		t_node node;
		node.idx = loop;
		node.num = inputNum;
		numStack.push(node);
	}

	for(const int& num : ngeVec)
		std::cout << num << " ";
	std::cout << '\n';
}