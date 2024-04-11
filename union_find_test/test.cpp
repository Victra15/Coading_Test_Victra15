#include <iostream>
#include <algorithm>
#include <vector>
#include <stack>
#include <string>

void unionOper(std::vector<int>& unionArr, int nodeA, int nodeB);
int findOper(std::vector<int>& unionArr, int nodeNum);

// union
void unionOper(std::vector<int>& unionArr, int nodeA, int nodeB)
{
	int refNodeA = findOper(unionArr, nodeA);
	int refNodeB = findOper(unionArr, nodeB);
	unionArr[refNodeB] = refNodeA;
}

// find
int findOper(std::vector<int>& unionArr, int nodeNum)
{
	int refNodeNum = unionArr[nodeNum];
	if(refNodeNum != nodeNum)
		refNodeNum = findOper(unionArr, refNodeNum);
	unionArr[nodeNum] = refNodeNum;
	return refNodeNum;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int nodeCnt, edgeCnt;
	std::cin >> nodeCnt >> edgeCnt;

	std::vector<int> unionArr(nodeCnt + 1);
	for(int loop = 1; loop <= nodeCnt; loop++)
		unionArr[loop] = loop;

	int nodeA, nodeB;
	for(int loop = 0; loop < edgeCnt; loop++)
	{
		std::cin >> nodeA >> nodeB;
		if (nodeA > nodeB)
			unionOper(unionArr, nodeB, nodeA);
		else
			unionOper(unionArr, nodeA, nodeB);
	}

	for(const int& node : unionArr)
	{
		std::cout << node << " ";
	}
	
	return 0;
}