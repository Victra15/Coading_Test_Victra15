#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <string.h>

#define A_INDEX 0
#define C_INDEX 1
#define G_INDEX 2
#define T_INDEX 3

void countAlpha(const char& alpha, int* alphaCnt)
{
	switch (alpha)
	{
		case 'A':
			++alphaCnt[A_INDEX];
			break;
		case 'C':
			++alphaCnt[C_INDEX];
			break;
		case 'G':
			++alphaCnt[G_INDEX];
			break;
		case 'T':
			++alphaCnt[T_INDEX];
			break;
	}
}

void uncountAlpha(const char& alpha, int* alphaCnt)
{
	switch (alpha)
	{
		case 'A':
			--alphaCnt[A_INDEX];
			break;
		case 'C':
			--alphaCnt[C_INDEX];
			break;
		case 'G':
			--alphaCnt[G_INDEX];
			break;
		case 'T':
			--alphaCnt[T_INDEX];
			break;
	}
}

bool determineDNAPasswd(const int* alphaCnt, const int* currAlphaCnt)
{
	if(alphaCnt[A_INDEX] > currAlphaCnt[A_INDEX] ||
		alphaCnt[C_INDEX] > currAlphaCnt[C_INDEX] ||
		alphaCnt[G_INDEX] > currAlphaCnt[G_INDEX] ||
		alphaCnt[T_INDEX] > currAlphaCnt[T_INDEX])
		return false;
	else
		return true;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int strLen;
	int subStrLen;
	std::string str;
	int alphaCnt[4]; 

	std::cin >> strLen >> subStrLen;
	std::cin >> str;
	std::cin >> alphaCnt[A_INDEX] >> alphaCnt[C_INDEX] >> alphaCnt[G_INDEX] >> alphaCnt[T_INDEX];

	int currAlphaCnt[4];
	memset(currAlphaCnt, 0, 4 * sizeof(int));

	int subDnaPasswdCnt = 0;

	for(int loop = 0; loop < subStrLen; ++loop)
		countAlpha(str[loop], currAlphaCnt);

	if(determineDNAPasswd(alphaCnt, currAlphaCnt))
		++subDnaPasswdCnt;

	for(int loop = subStrLen; loop < strLen; ++loop)
	{
		countAlpha(str[loop], currAlphaCnt);
		uncountAlpha(str[loop - subStrLen], currAlphaCnt);


		if(determineDNAPasswd(alphaCnt, currAlphaCnt))
			++subDnaPasswdCnt;
	}
	std::cout << subDnaPasswdCnt;
}