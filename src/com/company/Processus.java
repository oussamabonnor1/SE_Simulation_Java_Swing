package com.company;


/**
 * Created by Oussama on 11/04/2017.
 */
public class Processus {
    private int arriveTime;
    private int cpuTime;
    private int priority;
    private int name;
    private boolean passed;


    public int getName() {
        return name;
    }

    public Processus(int name, int arriveTime, int cpuTime, int priority, boolean passed) {
        this.arriveTime = arriveTime;
        this.cpuTime = cpuTime;
        this.priority = priority;
        this.name = name;
        this.passed = passed;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getArriveTime() {
        return arriveTime;
    }


    public int getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public int getPriority() {
        return priority;
    }

    public void processing(int timeToProcess) {
        setCpuTime(getCpuTime() - timeToProcess);

    }

}
