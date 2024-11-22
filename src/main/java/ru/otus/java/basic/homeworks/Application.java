package ru.otus.java.basic.homeworks;

public class Application {
    public enum TerrainType {
        FOREST("густой лес"),
        PLAIN("равнина"),
        SWAMP("болото");

        private String rusName;

        TerrainType(String rusName) {
            this.rusName = rusName;
        }

        public String getRusName() {
            return rusName;
        }
    }

    public static void main(String[] args) {
        float distance;
        Human human = new Human("Ivan", 0f, 50f);
        Transport transports[] = new Transport[]{null, new Car(100f, 0f), new Horse(100f, 0f),
                new AllTerrainVehicle(100f, 0f), new Bicycle(0f)};
        TerrainType[] terrainTypes = new TerrainType[]{TerrainType.SWAMP, TerrainType.FOREST, TerrainType.PLAIN};
        for (Transport transport : transports) {
            human.setCurrentTransport(transport);
            for (TerrainType terrainType : terrainTypes) {
                distance = (float) (Math.random() * 10) + 1;
                human.ride(terrainType, distance);
            }
            System.out.println();
            if (transport != null) {
                transport.info();
            }
            System.out.println();
            human.currentInfo();
            System.out.println();
            System.out.println();
        }

    }
}




