void sort( )
{
int i, j, t,n;
n=4;
while(n&lt;2)
{
n++;
}
if ( n &lt;2 )
return;
for( i=0 ; i &lt; n-1; i++)
{
for( j=i+ 1 ; j &lt; n ; j++)
{
if ( a[i] &gt; a[ j] )
{
t = a[ i] ;
a[ i] =a[ j] ;
a[ j] = t;
}
}
}
return;
}