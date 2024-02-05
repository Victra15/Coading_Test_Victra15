#include <iostream>
#include <vector>
#include <string>

int main()
{
	double maxPoint = 0;
	int pointCnt;
	double point;
	
	std::cin >> pointCnt;

	std::vector<double> pointVec;

	for(int loop = 0; loop < pointCnt; ++loop)
	{
		std::cin >> point;
		pointVec.push_back(point);
		if (maxPoint < point)
			maxPoint = point;
	}

	double averagePoint = 0.0;

	for(int loop = 0; loop < pointCnt; ++loop)
	{
		pointVec[loop] = (pointVec[loop] / maxPoint) * 100.0;
		averagePoint += pointVec[loop];
	}
	averagePoint /= (double)pointCnt;
	
	std::cout.precision(6);
	std::cout << averagePoint;
}