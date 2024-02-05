#include <iostream>
#include <string>

int main()
{
	int sum = 0;
	int numCnt;
	int num;
	std::string numStr;
	
	std::cin >> numCnt;
	std::cin >> numStr;

	int idx;
	for(int loop = 0; loop < numCnt; ++loop)
	{
		sum += numStr[loop] - '0';		
	}

	std::cout << sum;

}