# include <stdio.h>
Void fun(int a, int b, int y)
{
if(a>5) // If statement
{
a = 3;
}
else
{
b = 5;
}
while(y < 20)
{
/*
Nested if condition inside while loop
*/
if(y == 3)
{
y++;
}
else if(y == 5)
{
y+=3;
}
}�//End of while loop
}