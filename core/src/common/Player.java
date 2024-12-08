package common;

public class Player {
    private String name;
    private int score;
    private int balance;
    private int shipNumber;
    public boolean[] purchasedShips = {true, false, false, false};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void increaseScoreAndBalance() {
        score += 1;
        balance += 100;
    }

    public void setShipNumber(int shipNumber) {
        this.shipNumber = shipNumber;
    }

    public int getShipNumber() {
        return shipNumber;
    }
}