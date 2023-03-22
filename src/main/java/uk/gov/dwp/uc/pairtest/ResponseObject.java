package uk.gov.dwp.uc.pairtest;

public class ResponseObject {

    public int noOfSeats;
    public int totalAmountCost;

    public ResponseObject(int noOfSeats, int totalAmountCost) {
        this.noOfSeats = noOfSeats;
        this.totalAmountCost = totalAmountCost;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }


    public int getTotalAmountCost() {
        return totalAmountCost;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "noOfSeats=" + noOfSeats +
                ", totalAmountCost=" + totalAmountCost +
                '}';
    }
}
