package model;

import java.util.Date;

public class Transaction {
    private double amount;
    private String description;
    private Date date;
    private Category category;

    public Transaction(double amount, String description, Category category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = new Date(); // التاريخ الحالي
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %.2f USD (%s)",
                date.toString(), description, amount, category.getName());
    }
}
