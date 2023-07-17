package model.entities;

import model.exceptions.DomainException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private Integer roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;
    public static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation() {
    }

    public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) throws DomainException {
        if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())){
            throw new DomainException("Reservation dates must be future dates!");
        }
        if(checkOut.isBefore(checkIn)){
            throw new DomainException("Check-out must be after check-in date!");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public long duration(){
        return Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay()).getSeconds()/86400;
    }

    public void updateDates(LocalDate checkIn, LocalDate checkOut) throws DomainException {
        if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())){
            throw new DomainException("Reservation dates for update must be future dates!");
        }
        if(checkOut.isBefore(checkIn)){
            throw new DomainException("Check-out must be after check-in date!");
        }
        setCheckIn(checkIn);
        setCheckOut(checkOut);
    }

    @Override
    public String toString() {
        return "Reservation: Room " + roomNumber +
                ", check-in: " + formatter1.format(checkIn) +
                ", check-out: " + formatter1.format(checkOut) +
                ", " + duration() + " nights";
    }
}
