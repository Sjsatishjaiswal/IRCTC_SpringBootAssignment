package com.driver.services;


import com.driver.EntryDto.BookTicketEntryDto;
import com.driver.model.Passenger;
import com.driver.model.Ticket;
import com.driver.model.Train;
import com.driver.repository.PassengerRepository;
import com.driver.repository.TicketRepository;
import com.driver.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    PassengerRepository passengerRepository;


    public Integer bookTicket(BookTicketEntryDto bookTicketEntryDto)throws Exception{

        //Check for validity
        //Use bookedTickets List from the TrainRepository to get bookings done against that train
        // Incase the there are insufficient tickets
        // throw new Exception("Less tickets are available");
        //otherwise book the ticket, calculate the price and other details
        //Save the information in corresponding DBs and tables
        //Fare System : Check problem statement
        //Incase the train doesn't pass through the requested stations
        //throw new Exception("Invalid stations");

        Train train = trainRepository.findById(bookTicketEntryDto.getTrainId()).get();

        if(train.getNoOfSeats() - train.getBookedTickets().size() < bookTicketEntryDto.getNoOfSeats()){
            throw new Exception("Less tickets are available");
        }

        List<Integer> passengerIds = bookTicketEntryDto.getPassengerIds();

        //If we autowire passenger Repository :-
        List<Passenger> passengerList = new ArrayList<>();
        Ticket ticket = new Ticket();

        //Calculating the fare :
        for(Integer passengerId : passengerIds){
            Passenger passenger = passengerRepository.findById(passengerId).get();
            passengerList.add(passenger);
        }
        ticket.setPassengersList(passengerList);
        String route = train.getRoute();
        String[] result = route.split(",");
        int startIndex = -1;

        for(int i=0;i<result.length;i++){

            if(result[i].equals(bookTicketEntryDto.getFromStation())){
                startIndex = i;
                break;
            }
        }

        int endIndex = -1;
        for(int i=0;i<result.length;i++)
        {
            if(result[i].equals(bookTicketEntryDto.getToStation())){
                endIndex = i;
                break;
            }
        }

        if(startIndex==-1 || endIndex ==-1 ){
            throw new Exception("Invalid stations");
        }

        ticket.setTotalFare(300*(endIndex-startIndex));
        ticket.setFromStation(bookTicketEntryDto.getFromStation());
        ticket.setToStation(bookTicketEntryDto.getToStation());
        ticket.setTrain(train);

        Passenger passenger = passengerRepository.findById(bookTicketEntryDto.getBookingPersonId()).get();

        passenger.getBookedTickets().add(ticket);

        //Setting the parent reference
        ticket.setTrain(train);

        ticket = ticketRepository.save(ticket);

        //Setting in the parent
        train.getBookedTickets().add(ticket);

        train = trainRepository.save(train);

        return ticket.getTicketId();
    }
}
