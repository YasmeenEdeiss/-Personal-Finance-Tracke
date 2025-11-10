package service;

import java.util.*;
import model.*;

public class PersonalFinanceTracker {
    private List<Transaction> transactions = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private Budget budget;
    private Scanner input = new Scanner(System.in);

    public void run() {
        System.out.println("==== Welcome to Personal Finance Tracker ====");
        setupBudget();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Category");
            System.out.println("2. Add Transaction");
            System.out.println("3. View Transactions");
            System.out.println("4. View Budget Status");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> addCategory();
                case 2 -> addTransaction();
                case 3 -> viewTransactions();
                case 4 -> viewBudget();
                case 5 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void setupBudget() {
        System.out.print("Enter your monthly budget limit: ");
        double limit = input.nextDouble();
        budget = new Budget(limit);
    }

    private void addCategory() {
        System.out.print("Enter category name: ");
        String name = input.nextLine();
        System.out.print("Type (Income/Expense): ");
        String type = input.nextLine();
        categories.add(new Category(name, type));
        System.out.println("Category added successfully!");
    }

    private void addTransaction() {
        if (categories.isEmpty()) {
            System.out.println("Please add a category first!");
            return;
        }

        System.out.println("Choose category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        int catIndex = input.nextInt() - 1;
        input.nextLine();

        Category category = categories.get(catIndex);

        System.out.print("Enter description: ");
        String desc = input.nextLine();
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();

        Transaction t = new Transaction(amount, desc, category);
        transactions.add(t);

        if (category.getType().equalsIgnoreCase("Expense")) {
            budget.addExpense(amount);
        }

        System.out.println("Transaction added successfully!");
    }

    private void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }
        System.out.println("\n--- Transaction List ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    private void viewBudget() {
        System.out.println("\n--- Budget Status ---");
        System.out.println(budget);
        if (budget.isExceeded()) {
            System.out.println(" You have exceeded your budget limit!");
        }
    }
}
