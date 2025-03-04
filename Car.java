public class Car {
    String merke;
    String modell;
    int år;
    double kilometer;

    //konstruktør
    public Car(String merke, String modell, int år, double kilometer) {
        this.merke = merke;
        this.modell = modell;
        this.år = år;
        this.kilometer = kilometer;
    }


    void kjør(double km) {
        kilometer += km;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Volvo", "240", 2002, 3004.22);
        System.out.println(car1.merke);
        car1.kjør(22);
        System.out.println(car1.kilometer);
    }
}
