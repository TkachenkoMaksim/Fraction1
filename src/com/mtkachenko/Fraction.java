package com.mtkachenko;

public class Fraction implements Comparable<Fraction> {
    // Задаем переменные обозначающие числитель и знаменатель
    private int numerator;
    private int denominator;

    // Первый конструктор в котором через параметры передаются значения числителя и знаменателя
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        // Проверяем условие не равен ли знаменатель нулю, если равен то генерируем исключение
        //ArithmeticException
        if(denominator == 0){ throw new ArithmeticException();}
    }

    // Во втором конструкторе передаем через параметр только значение числителя (согласно условия),
    // значение значенателя устанавливаем равным единице
    public Fraction(int numerator) {
        this.numerator = numerator;
        denominator = 1;
    }
    // получаем значение числителя
    public int getNumerator() {
        return numerator;
    }
    // получаем значение знаменателя
    public int getDenominator() {
        return denominator;
    }
    // сокращение дробей
    public static Fraction reduction(int n, int d){
        int gcd = Fraction.gcd(n, d);
        n /= gcd;//n=n/gcd;
        d /= gcd;//d=d/gcd;
        return new Fraction(n, d);
    }

    public static int gcd(int a, int b){
        return (b == 0 ? a : gcd(b, a % b));
    }

    // операция сложения дробей
    public Fraction add(Fraction fraction){
        if(denominator == fraction.getDenominator()) {
            int newNumerator = numerator + fraction.getNumerator();
            return Fraction.reduction(newNumerator, denominator);
        }
        int newNumerator = numerator * fraction.getDenominator() + fraction.getNumerator() * denominator;
        int newDenominator = denominator * fraction.getDenominator();
        return Fraction.reduction(newNumerator, newDenominator);
    }

    // операция умножения дробей
    public Fraction multiply(Fraction fraction){
        int newNumerator = numerator * fraction.getNumerator();
        int newDenominator = denominator * fraction.getDenominator();
        return Fraction.reduction(newNumerator, newDenominator);
    }
    // сравнение дробей
    @Override
    public int compareTo(Fraction o) {
        if(denominator == o.getDenominator()) {
            return numerator - o.getNumerator();
        }
        int newCurrentNumerator = numerator * o.getDenominator();
        int newObjectNumerator = o.getNumerator() * denominator;
        return newCurrentNumerator - newObjectNumerator;
    }


    @Override
    public String toString() {
        if(denominator == 1 || numerator == 0)
            return String.valueOf(numerator);
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (numerator != fraction.numerator) return false;
        return denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        if(numerator == 0){
            return 0;
        }
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }
}
