package ru.otus.java.basic.homeworks;

public interface Transport {
    public boolean ride(Application.TerrainType terrainType, float distance);

    public void info();
}
