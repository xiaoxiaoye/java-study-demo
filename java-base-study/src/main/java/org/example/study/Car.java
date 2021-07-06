package org.example.study;

public class Car {
    private int batch;

    public Car(int batch) {
        this.batch = batch;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car) {
            Car c = (Car) obj;
            return this.batch == c.batch;
        }
        return false;
    }
}
