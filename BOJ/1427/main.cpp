#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

void swap_char(std::string::iterator ch1, std::string::iterator ch2)
{
	char temp = *ch1;
	*ch1 = *ch2;
	*ch2 = temp;
}

int main()
{
	std::ios::sync_with_stdio(0); std::cin.tie(0); std::cout.tie(0);

	std::string str;

	std::cin >> str;
	
	std::string::iterator maxChrPos;
	char maxChr;
	for(int loop = 0; loop < str.size(); ++loop)
	{
		maxChr = '0';
		for(int findLoop = loop; findLoop < str.size(); ++findLoop)
		{
			if(str[findLoop] >= maxChr)
			{
				maxChr = str[findLoop];
				maxChrPos = str.begin() + findLoop;
			}
		}
		swap_char(str.begin() + loop, maxChrPos);
	}
	std::cout << str;
}

