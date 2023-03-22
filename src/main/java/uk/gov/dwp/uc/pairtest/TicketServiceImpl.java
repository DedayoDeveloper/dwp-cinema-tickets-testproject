package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationService;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import java.util.Arrays;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    private SeatReservationService seatReservationService = new SeatReservationServiceImpl();
    private TicketPaymentService ticketPaymentService = new TicketPaymentServiceImpl();

    @Override
    public ResponseObject purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        int totalNoOfSeats = 0;
        int totalAmountToPay = 0;
        if(ticketTypeRequests.length <= 20){
            totalAmountToPay = calculateTotalCost(ticketTypeRequests);
            totalNoOfSeats = calculateNoOfSeats(ticketTypeRequests);
            ticketPaymentService.makePayment(accountId,totalAmountToPay);
            seatReservationService.reserveSeat(accountId,totalNoOfSeats);
        }
        else {
            throw new InvalidPurchaseException("Maximum number of tickets purchase exceeded");
        }
        return new ResponseObject(totalNoOfSeats,totalAmountToPay);
    }


    private int getTicketPrice(TicketTypeRequest.Type ticketTypeRequest){
        int price = 1;
       switch (ticketTypeRequest){
           case ADULT:
               price = 20;
               break;
           case CHILD:
               price = 10;
               break;
           case INFANT:
               price = 0;
               break;
       }
       return price;
    }

    private int calculateTicketCost(TicketTypeRequest ticketTypeRequest){
       int price = getTicketPrice(ticketTypeRequest.getTicketType());
       int ticketCost = ticketTypeRequest.getNoOfTickets() * price;
       return ticketCost;
    }

    private int calculateTotalCost(TicketTypeRequest... ticketTypeRequests){
        int totalAmount = 0;
        for(TicketTypeRequest ticketTypeRequest: ticketTypeRequests){
            int amount = calculateTicketCost(ticketTypeRequest);
            totalAmount = totalAmount + amount;
        }
        return totalAmount;
    }

    private boolean checkForAdultTicketPurchaseList(TicketTypeRequest... ticketTypeRequests){
        return Arrays.stream(ticketTypeRequests).filter(ticket -> ticket.getTicketType().equals(ADULT)).findAny().isPresent();
    }

    private int calculateNoOfSeats(TicketTypeRequest... ticketTypeRequests){
        int noOfSeats = 0;
        boolean confirmAdultTicket = checkForAdultTicketPurchaseList(ticketTypeRequests);

        for(TicketTypeRequest ticketTypeRequest: ticketTypeRequests){

           if (ticketTypeRequest.getTicketType().equals(CHILD) && confirmAdultTicket == true){
               noOfSeats = noOfSeats + ticketTypeRequest.getNoOfTickets();
           }

           if(ticketTypeRequest.getTicketType().equals(ADULT)){
               noOfSeats = noOfSeats + ticketTypeRequest.getNoOfTickets();
           }

            if(ticketTypeRequest.getTicketType().equals(INFANT)){
                noOfSeats = noOfSeats + 0;
            }

        }

        return noOfSeats;
    }




}
