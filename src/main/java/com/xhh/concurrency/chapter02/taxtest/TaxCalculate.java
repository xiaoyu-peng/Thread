package com.xhh.concurrency.chapter02.taxtest;

public class TaxCalculate {

    private final double salary;
    private final double bonus;

    private CalculatorStrategy calculatorStrategy;

    public TaxCalculate(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    protected double calTax(){
        return 0.0d;
    }

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    /**
     * 模拟 run方法
     * @return
     */
    public double runCal(){
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }
}
