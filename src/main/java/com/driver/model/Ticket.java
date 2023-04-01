package com.driver.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;

    //This is also parent wrt to ticketEntity
    @ManyToMany(mappedBy = "bookedTickets",cascade = CascadeType.ALL)
    private List<Passenger> passengersList;

    @ManyToOne
    @JoinColumn
    private Train train;

    SeatType seatType;

    private Station fromStation;

    private Station toStation;

    public Ticket(int ticketId, List<Passenger> passengersList, Train train, SeatType seatType, Station fromStation, Station toStation) {
        this.ticketId = ticketId;
        this.passengersList = passengersList;
        this.train = train;
        this.seatType = seatType;
        this.fromStation = fromStation;
        this.toStation = toStation;
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

    public Ticket() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public List<Passenger> getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(List<Passenger> passengersList) {
        this.passengersList = passengersList;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
