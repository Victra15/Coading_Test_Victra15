#include <iostream>
#include <algorithm>
#include <vector>
#include <stack>
#include <string>

int findUnvisited(const std::vector<bool>& visited)
{
	for(int loop = 1; loop < visited.size(); ++loop)
	{
		if(!visited[loop])
			return loop;
	}
	return -1;
}

void DFS(int startVertex, std::vector<bool>& visited, std::vector<std::vector<int>>& connected)
{
	std::stack<int> searchStack;

	int currVertex;
	searchStack.push(startVertex);
	visited[startVertex] = true;
	while(!searchStack.empty())
	{
		currVertex = searchStack.top();
		searchStack.pop();
		
		for(int loop = 0; loop < connected[currVertex].size(); ++loop)
		{
			if(!visited[connected[currVertex][loop]])
			{
				searchStack.push(connected[currVertex][loop]);
				visited[connected[currVertex][loop]] = true;
			}
		}
	}
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int vertex, edge;
	std::cin >> vertex >> edge;

	std::vector<bool> visited(vertex + 1, false);
	std::vector<std::vector<int>> connected(vertex + 1);

	int inputVertex1, inputVertex2;
	for(int loop = 0; loop < edge; ++loop)
	{
		std::cin >> inputVertex1 >> inputVertex2;
		connected[inputVertex1].push_back(inputVertex2);
		connected[inputVertex2].push_back(inputVertex1);
	}

	int startVertex = findUnvisited(visited);
	int connCompCnt = 0;
	while (startVertex != -1)
	{
		DFS(startVertex, visited, connected);
		++connCompCnt;
		startVertex = findUnvisited(visited);
	}

	std::cout << connCompCnt;
	
	return 0;
}