#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int N, M;
	std::cin >> N >> M;

	std::vector<int> materialNumVec;

	int materialNum;
	for(int loop = 0; loop < N; ++loop)
	{
		std::cin >> materialNum;
		materialNumVec.push_back(materialNum);
	}

	std::sort(materialNumVec.begin(), materialNumVec.end());

	int start_idx = 0;
	int end_idx = materialNumVec.size() - 1;
	int target_num;
	int armor_cnt = 0;

	while(start_idx < end_idx)
	{
		target_num = materialNumVec[start_idx] + materialNumVec[end_idx];
		if(target_num < M)
			++start_idx;
		else
			--end_idx;
		if(target_num == M)
			++armor_cnt;
	}
	std::cout << armor_cnt;
}