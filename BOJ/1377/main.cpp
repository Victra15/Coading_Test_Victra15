#include <iostream>
#include <algorithm>
#include <vector>

void printBubbleSortCnt(std::vector<int> A, int N)
{
	bool changed = false;
	for (int i=1; i<=N+1; i++) {
		changed = false;
		for (int j=1; j<=N-i; j++) {
			if (A[j] > A[j+1]) {
				changed = true;
				std::swap(A[j], A[j+1]);
			}
		}
		if (changed == false) {
			std::cout << i << '\n';
			break;
		}
	}
}

void printArr(const std::vector<int> arr)
{
	for(const int& num : arr)
	{
		std::cout << num << " ";
	}
	std::cout << '\n';
}

void printArr(const std::vector<int*> arr)
{
	for(const int* num : arr)
	{
		std::cout << *num << " ";
	}
	std::cout << '\n';
}

void printArr(const std::vector<std::pair<int, int>> arr)
{
	for(const std::pair<int, int> num : arr)
	{
		std::cout << "(" << num.first << ", " << num.second << ") ";
	}
	std::cout << '\n';
}

bool compare(int* a, int* b)
{
	return *a < *b;
}

int main()
{
	std::ios::sync_with_stdio(0); std::cin.tie(0); std::cout.tie(0);

	int N;
	std::cin >> N;

	std::vector<int> A(N + 1, 0);
	std::vector<int*> ptrA(N + 1, 0);
	
	ptrA[0] = &A[0];
	for(int loop = 1; loop < N + 1; ++loop)
	{
		std::cin >> A[loop];
		ptrA[loop] = &A[loop];
	}

	std::stable_sort(ptrA.begin() + 1, ptrA.end(), compare);
	for(int loop = 0; loop < N + 1; ++loop)
		*ptrA[loop] = loop;

	int maxSortCnt = 1;
	int tempNum;
	for(int loop = 1; loop < N + 1; ++loop)
	{
		tempNum = loop - A[loop] + 1;
		if(tempNum > maxSortCnt)
			maxSortCnt = tempNum;
	}	
	

	std::cout << maxSortCnt;
}

