package model;

public class Budget {
    private double limit;
    private double spent;

    public Budget(double limit) {
        this.limit = limit;
        this.spent = 0;
    }

    public void addExpense(double amount) {
        spent += amount;
    }

    public double getRemaining() {
        return limit - spent;
    }

    public boolean isExceeded() {
        return spent > limit;
    }

    @Override
    public String toString() {
        return String.format(
                "Budget limit: %.2f | Spent: %.2f | Remaining: %.2f",
                limit, spent, getRemaining()
        );
    }
}
