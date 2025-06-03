#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

void swap(int& a, int& b)
{
	int temp = a;
	a = b;
	b = temp;
}

void printVec(const std::vector<int> vec)
{
	for(const int& num : vec)
		std::cout << num << " ";
	std::cout << '\n';
}
void quick_sort(std::vector<int>& vec, int start, int end, int pivot, const int K)
{
	int left = start;
	int right = end;

	while(left < right)
	{
		while(vec[pivot] > vec[left] && left < end)
			++left;
		while(vec[pivot] <= vec[right] && right > pivot)
			--right;
		if(left <= right)
			swap(vec[left], vec[right]);
	}
	swap(vec[right], vec[pivot]);
	if(right == K)
		return;
	if(right - pivot > 1 && right > K)
		quick_sort(vec, pivot + 1, right - 1, pivot, K);
	if(end - right > 1 && right < K)
		quick_sort(vec, right + 2, end, right + 1, K);
}

int main()
{
	std::ios::sync_with_stdio(0); std::cin.tie(0); std::cout.tie(0);

	int N, K;
	std::cin >> N >> K;

	std::vector<int> numVec(N);
	for(int loop = 0; loop < N; ++loop)
		std::cin >> numVec[loop];

	quick_sort(numVec, 1, N - 1, 0, K - 1);
	//std::sort(numVec.begin(), numVec.end());
	std::cout << numVec[K - 1];
}

