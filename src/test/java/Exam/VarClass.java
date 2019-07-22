package Exam;

public class VarClass {

    int var1;
    int var2;

    private void printVar() {
        System.out.println(var1);
        System.out.println(var2);
    }

    public void setVar1(int value) {
        this.var1 = value;
    }

    public void setVar2(int value) {
        this.var2 = value;
    }

    public int summVar() {
        return var1 + var2;
    }

    public int compareVar() {
        return (var1 > var2) ? var1 : ((var1 == var2) ? 0 : var1);
    }
}
