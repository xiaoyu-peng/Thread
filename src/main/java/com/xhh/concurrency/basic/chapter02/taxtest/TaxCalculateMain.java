package com.xhh.concurrency.basic.chapter02.taxtest;

/**
 * @author PengHui
 */
public class TaxCalculateMain {

    public static void main(String[] args) {
        /*TaxCalculate tc = new TaxCalculate(1000d, 3000d){
            @Override
            public double calTax(){
                return getSalary() * 0.1 + getBonus() * 0.5;
            }
        };*/

        TaxCalculate tc = new TaxCalculate(1000d, 3000d);
        CalculatorStrategy strategy = new CalculatorStrategyImpl();
        tc.setCalculatorStrategy(strategy);
        double salary = tc.runCal();
        System.out.println(salary);
    }
}
