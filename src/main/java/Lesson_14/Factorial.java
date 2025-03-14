package Lesson_14;

public class Factorial {
    public static int factorial (int a){
        if (a<0){
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }
        int result = 1;
        for (int i = 1; i <=a; i++){
            result *=i;
        }
        return result;
    }
}
