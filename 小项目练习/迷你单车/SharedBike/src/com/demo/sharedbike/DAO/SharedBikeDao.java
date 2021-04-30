package com.demo.sharedbike.DAO;

public interface SharedBikeDao {
    void selectAllBike();
    void putBikes();
    void deleteBike();
    void borrowBike();
    void returnBike();
    void sortCounts();
}
