package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int roomNumber = sc.nextInt();
            System.out.print("Check-in date (dd/MM/yyyy): ");
            String checkIn = sc.next();
            System.out.print("Check-out date (dd/MM/yyyy): ");
            String checkOut = sc.next();
            Reservation reservation = new Reservation(roomNumber, LocalDate.parse(checkIn, formatter1), LocalDate.parse(checkOut, formatter1));
            System.out.println(reservation);
            System.out.println();
            System.out.println("Enter data to update the reservation:");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sc.next();
            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = sc.next();
            reservation.updateDates(LocalDate.parse(checkIn, formatter1), LocalDate.parse(checkOut, formatter1));
            System.out.println(reservation);
        }
        catch(DateTimeParseException e) {
            System.out.println("Invalid date format!");
        }
        catch(DomainException e){
            System.out.println("Error in Reservation: " + e.getMessage());
        }
        catch(InputMismatchException e){
            System.out.println("The input is not valid!");
        }
        catch(RuntimeException e){
            System.out.println("Unexpected error!");
        }

        sc.close();
    }
}