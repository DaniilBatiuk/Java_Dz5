package org.example;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
class Passenger {
    private String name;
    private int timeAtDock;

    public Passenger(String name, int timeAtDock) {
        this.name = name;
        this.timeAtDock = timeAtDock;
    }

    public int getTimeAtDock() {
        return timeAtDock;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Ferry {
    private int capacity;
    private int availableSeats;

    public Ferry(int capacity) {
        this.capacity = capacity;
        this.availableSeats = capacity;
    }

    public boolean hasAvailableSeats() {
        return availableSeats > 0;
    }

    public void boardPassenger() {
        if (hasAvailableSeats()) {
            availableSeats--;
        }
    }

    public void depart() {
        availableSeats = capacity;
    }
}

class Dock {
    private Queue<Passenger> passengersQueue;
    private Queue<Ferry> ferryQueue;
    private int maxPassengers;
    private int currentTime;
    private Random random;

    public Dock(int maxPassengers) {
        this.passengersQueue = new LinkedList<>();
        this.ferryQueue = new LinkedList<>();
        this.maxPassengers = maxPassengers;
        this.currentTime = 0;
        this.random = new Random();
    }

    public void simulate(int totalTime) {
        while (currentTime < totalTime) {
            boolean ch = false;
            int maxTry = 15;
            while (!ch && maxTry > 0){
                int passengersArrivalTime = generatePassengersArrivalTime();
                maxTry--;
                if (passengersArrivalTime == currentTime) {
                    passengersQueue.add(new Passenger("Passenger" + passengersQueue.size(), random.nextInt(100) + 1));
                    ch = true;
                }

            }


            boolean ch2 = false;
            int maxTry2 = 15;
            while (!ch2 && maxTry2 > 0){
                int ferryArrivalTime = generateFerryArrivalTime();
                maxTry2--;

                if (ferryArrivalTime == currentTime) {
                    ferryQueue.add(new Ferry(random.nextInt(5) + 1));
                    ch2 = true;
                }

            }

            while (!passengersQueue.isEmpty() && !ferryQueue.isEmpty() && ferryQueue.peek().hasAvailableSeats()) {
                Passenger passenger = passengersQueue.poll();
                ferryQueue.peek().boardPassenger();
                System.out.println("пассажир " + passenger.toString() + " сів в " + currentTime + "(вимыр часу у цифрах)");
            }

            while (!ferryQueue.isEmpty() && ferryQueue.peek().hasAvailableSeats()) {
                ferryQueue.poll().depart();
                System.out.println("Лодка вийшла с порту в " + currentTime);
            }

            currentTime++;
        }
    }

    private int generatePassengersArrivalTime() {
        return random.nextInt(100);
    }

    private int generateFerryArrivalTime() {
        return random.nextInt(100);
    }
}
