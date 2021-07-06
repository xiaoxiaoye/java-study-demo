package org.example.study;

public class BigCar extends Car {
    private int count;

    public BigCar(int batch, int count) {
        super(batch);
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BigCar) {
            BigCar bc = (BigCar) obj;
            return super.equals(obj) && bc.count == this.count;
        }
        return super.equals(obj);
    }

    public static void main(String[] args) {
        Car c = new Car(1);
        Car bc = new BigCar(1, 20);
        Car bc1 = new BigCar(1, 22);
        System.out.println(bc.equals(c));
        System.out.println(c.equals(bc1));
        System.out.println(bc.equals(bc1));

        boolean b = bc instanceof BigCar;
        System.out.println(b);
    }
}
