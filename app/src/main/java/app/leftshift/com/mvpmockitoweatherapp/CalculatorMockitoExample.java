package app.leftshift.com.mvpmockitoweatherapp;

import view.CalculatorService;

/**
 * Created by vrushali on 3/8/18.
 */

public class CalculatorMockitoExample {

    private CalculatorService service;

    public CalculatorMockitoExample(CalculatorService service) {

        this.service = service;
    }

    public int performAddition(int first, int secondNum) {

        return service.add(first, secondNum);
    }
}
