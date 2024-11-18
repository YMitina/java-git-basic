package ru.otus.java.basic.homeworks;

public class Application {
    public enum TerrainType {
        FOREST,
        PLAIN,
        SWAMP
    }

    public static void main(String[] args) {
        Transport transport = new Car(100f, 0f);
        transport.info();
        System.out.println();

        Human human = new Human("Ivan", transport, 0f, 100f);
        transport.ride(human, TerrainType.FOREST, 50f);
        System.out.println();

        transport.info();
        System.out.println();

        transport.ride(human, TerrainType.PLAIN, 50f);
        System.out.println();

        transport.info();
        System.out.println();

        human.currentInfo();
        System.out.println();

        Transport transport2 = new AllTerrainVehicle(50f, 0f);
        transport2.ride(human, TerrainType.FOREST, 10f);
        System.out.println();

        transport2.info();
        System.out.println();

        human.setCurrentTransport(transport2);
        transport2.ride(human, TerrainType.FOREST, 10f);
        System.out.println();

        transport2.info();
        System.out.println();

        human.currentInfo();
        System.out.println();

        Transport transport3 = new Bicycle(0f);
        human.setCurrentTransport(transport3);
        transport3.ride(human, TerrainType.SWAMP, 2f);
        transport3.ride(human, TerrainType.FOREST, 2f);
        System.out.println();

        transport3.info();
        System.out.println();

        human.currentInfo();
        System.out.println();

        Transport transport4= new Horse(100f, 0f);
        human.setCurrentTransport(transport4);
        transport4.ride(human, TerrainType.FOREST, 1f);
        System.out.println();

        human.currentInfo();
        System.out.println();

        transport4.info();
        System.out.println();

        human.setCurrentTransport(null);
        transport4.ride(human, TerrainType.SWAMP, 11f);

        System.out.println();
        human.currentInfo();

    }
}
