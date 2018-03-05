package app.leftshift.com.mvpmockitoweatherapp;

/**
 * Created by vrushali on 3/4/18.
 */

public class Calculator {


    public int add(int First, int Second){

        return First + Second;
    }

    //2nd way

    CalculatorService service;

    public int addWithCalculatorService(int first, int second){

        return service.add(first, second);
    }

}








