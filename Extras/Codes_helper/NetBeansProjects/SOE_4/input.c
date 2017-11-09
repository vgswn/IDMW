# include <stdio.h>
void triangle(int, int, int);
void maxi(int, int, int);
int main()
{
	int a= 10, b= 20, c= 30 ;
	triangle(a, b, c);
	maxi(a, b, c);
return 0;
}
void triangle(int a, int b, int c)
{
	if(a*a == b*b + c*c )
	{
		printf("Pythagorean triplet");
	}
	else
	{
		if( b + c > a )
		{
			printf("triangle");
		}
		else
		{
			printf("Not triangle");
		}
	}
}
void maxi(int a, int b, int c)
{
	if(a > b)
	{
		if(a > c)
		{
			printf("%d", a);
		}
		else
		{
			printf("%d", c);
		}
	}
	else
	{
		if(b > c)
		{
			printf("%d", b);
		}
		else
		{
			printf("%d", c);
		}
	}
}