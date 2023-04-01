package com.driver.EntryDto;


import com.driver.model.SeatType;
import com.driver.model.Station;

public class SeatAvailabilityEntryDto {

    private int trainId;

    private SeatType seatType;

    private Station fromStation;

    private Station toStation;

    public SeatAvailabilityEntryDto() {
    }

    public SeatAvailabilityEntryDto(int trainId, SeatType seatType, Station fromStation, Station toStation) {
        this.trainId = trainId;
        this.seatType = seatType;
        this.fromStation = fromStation;
        this.toStation = toStation;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }
}
