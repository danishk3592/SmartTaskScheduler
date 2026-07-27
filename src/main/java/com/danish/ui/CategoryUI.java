package com.danish.ui;

import com.danish.model.Category;
import com.danish.service.CategoryService;

import java.util.List;
import java.util.Scanner;

public class CategoryUI {

    private final Scanner scanner = new Scanner(System.in);

    private final CategoryService service = new CategoryService();


    public void show() {

        while (true) {

            System.out.println("\n========== CATEGORIES ==========");
            System.out.println("1. View Categories");
            System.out.println("2. Back");

            System.out.print("Choose Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {

                case 1:
                    viewCategories();
                    break;


                case 2:
                    return;


                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }


    private void viewCategories() {

        List<Category> categories = service.getAllCategories();


        if (categories.isEmpty()) {

            System.out.println("\nNo Categories Found!");
            return;
        }


        System.out.println("\n========== CATEGORY LIST ==========");


        for (Category category : categories) {

            System.out.println(
                    category.getCategoryId()
                            + ". "
                            + category.getCategoryName()
            );
        }
    }
}