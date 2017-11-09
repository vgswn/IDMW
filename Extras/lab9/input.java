class Test1 {
int a, b, x, y;
public void fun1 () {
int c = a + b;
}
public void fun2 (int a1) {
int e = a + 4;
}
private void fun3 (int x1) {
x = x1 + 4;
}
protected void fun4 (int y1) {
y = y1 + 4;
}
}
class Test2 {
int a, b, x, y;
public void fun1 (int a1, int b1) {
int c = a + b;
}

public void fun2 (int a1) {
a = fun3(a1);
x = fun2(a);
}
private int fun3 (int x1) {
x = x1;
return x;
}
protected void fun4 (int y1) {
y = y1;
int z = fun3(y);
}
}
class Main {
public static void main() {
int a1, b1, x1, y1;
x1 = 5;
b1 = 5;
a1 = 10;
y1 = 11;
Test1 t1 = new Test1();
t1.fun1();
t1.fun2(a1);
t1.fun3(x1);
t1.fun4(y1);
Test2 t2 = new Test2();
t2.fun1(a1, b1);
t2.fun2(a1);
t2.fun3(x1);
t2.fun4(y1);
}
}
