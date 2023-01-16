/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pharmacypos;

/**
 *
 * @author Jonas Lacandola
 */
import java.util.*;

public class PharmacyPOS {
    //scanner
    static Scanner scan = new Scanner(System.in);
    //an instances of object Items
    static Items[] _Cart = new Items[1];
    static Items[] medicineItems = {new Items(1, 8.75, "Tylenol Paracetamol 650mg 1 Extended Release Caplet"),
            new Items(2, 129.75, "Paracetamol 250 mg/5 mL Strawberry-Flavored Oral Suspension 60 mL"),
            new Items(3, 7.50, "Fern C Plus+ 500mg/10mg capsule 1s"),
            new Items(4, 490.00, "Puritans Pride (Heart Health) Garlic Odorless 1000mg 100 Softgels"),
            new Items(5, 7.50, "Potencee (Kids Vitamins) +ZN (Ascorbic Acid + Zinc) 500mg/10mg 1 Capsule (sold per piece)"),
            new Items(6, 19.50, "Ponstan Mefenamic Acid 250mg 1 Capsule"),
            new Items(7, 15.00, "Cetirizine hydrochloride 10mg 1 Tablet"),
            new Items(8, 15.00, "Glucosamine Tablet 1500mg x 30 Tablet"),
            new Items(9, 83.00, "United TIKI-TIKI B Complex; Vitamins A; C; D and E Drops 30ml"),
            new Items(10, 316.00, "Stresstabs Multivitamins + Iron 30 Tablets")};

    static Items[] beautyItems = {new Items(1, 109.00, "PUREDERM Make up Remover Cleansing Tissue Argan Oil 30sheets"),
            new Items(2, 165.00, "ChicBobbie Baby-doll Eyes Bold Liquid Eyeliner 1.6gv"),
            new Items(3, 185.00, "ChicBobbie Beauty Tools- Make-up Blending Sponge"),
            new Items(4, 248.00, "NIVEA Body Lotion Intensive Moisture Body Milk for dry skin 250ml"),
            new Items(5, 157.00, "Johnsons & Johnsons Regular Lotion 200ml"),
            new Items(6, 229.00, "Maybelline Fit Me Flawless Natural Concealer - 10 Light"),
            new Items(7, 30.00, "COLLAGEN WS Moisturising and Repairing Serum 30ml"),
            new Items(8, 152.00, "NIVEA Lip Caring Lip Scrub with Rosehip Oil 5.5ml"),
            new Items(9, 69.00, "WATSONS Anti - Wrinkle Serum Mask 1 sheet"),
            new Items(10, 97.00, "PONDS Acne Clear Facial Foam with Scrub 50g")};
    //an instance of object Cashier
    static Cashier cashier = new Cashier();

    static ArrayList<Items> ItemsMedicine = new ArrayList<>(Arrays.asList(medicineItems));
    static ArrayList<Items> ItemsBeauty = new ArrayList<>(Arrays.asList(beautyItems));
    static ArrayList<Items> ItemsCart = new ArrayList<>(Arrays.asList(_Cart));

    static int counter = 0;
    static double userCash = 0;
    static double[] _SubTotals;

    public static void main(String[] args) {

        int code, qty, cartIndex;
        char userInput, cartInput = 'D';

        System.out.println("Welcome to Watsons!");
        System.out.print("\nPress 'A' to Medicine Section.\nPress 'B' to Beauty Section.\nPress 'C' to Cart.\nPress 'E' to Exit.\n- ");
        userInput = Character.toUpperCase(scan.next().charAt(0));
        //shopping loop, loops all the inputs unless exited.
        while (userInput != 'E') {
            switch (userInput) {
                case 'A' -> {
                    printMedicineMenu();
                    System.out.print("\nPlease select an item: ");
                    code = scan.nextInt();
                    System.out.print("Enter the quantity: ");
                    qty = scan.nextInt();
                    ItemsMedicine.get(code - 1).setQuantity(qty);
                    addtoCart(ItemsMedicine.get(code - 1));
                }
                case 'B' -> {
                    printBeautyMenu();
                    System.out.print("\nPlease select an item: ");
                    code = scan.nextInt();
                    System.out.print("Enter the quantity: ");
                    qty = scan.nextInt();
                    ItemsBeauty.get(code - 1).setQuantity(qty);
                    addtoCart(ItemsBeauty.get(code - 1));
                }
                case 'C' -> {
                    System.out.println("\nCart:");
                    displayItems();
                    counter--;
                    while (cartInput != 'E' && ItemsCart.get(0) != null) {
                        System.out.print("\nPress 'R' to remove an item.\nPress 'E' to exit.\n- ");
                        cartInput = Character.toUpperCase(scan.next().charAt(0));

                        if (cartInput == 'R') {
                            System.out.print("\nPlease select item: ");
                            cartIndex = scan.nextInt();

                            ItemsCart.remove(cartIndex - 1);
                            counter--;
                            System.out.println("\nThe item has been removed successfully.");
                            cartInput = 'E';
                        }
                    }
                    cartInput = 'D';
                }
            }

            System.out.print("\nPress 'A' to Medicine Section.\nPress 'B' to Beauty Section.\nPress 'C' to Cart.\nPress 'E' to Exit.\n- ");
            userInput = Character.toUpperCase(scan.next().charAt(0));
            counter++;
        }

        //Check if the cart is empty
        if (ItemsCart.get(0) != null) {
            System.out.print("\nAre you a PWD, Pregnant, Senior/Elderly? [y/n] : ");
            userInput = Character.toUpperCase(scan.next().charAt(0));
            displayCashier(userInput == 'Y');
        }
    }

    public static void printBeautyMenu() {
        System.out.println("\nBeauty Section:\n");
        for (Items beautyItem : ItemsBeauty) {
            System.out.println(beautyItem.getCode() + "...." + beautyItem.getItem() + "....P" + beautyItem.getPrice());
        }
    }

    public static void printMedicineMenu() {
        System.out.println("\nMedicine Section:\n");
        for (Items medicineItem : ItemsMedicine) {
            System.out.println(medicineItem.getCode() + "...." + medicineItem.getItem() + "....P" + medicineItem.getPrice());
        }
    }

    public static void displayItems() {
        if (ItemsCart.get(0) != null) {
            for (Items items : ItemsCart) {
                if (items != null) {
                    System.out.println(items.getItem());
                    System.out.printf("%d\t\t%,.3f\t\t\t\t%,.3f\n", items.getQuantity(), items.getPrice(), (items.getPrice() * items.getQuantity()));
                }
            }
        } else {
            System.out.println("You don't have any items.");
        }
    }

    public static void addtoCart(Items product) {
        if (!ItemsCart.contains(product)) {
            ItemsCart.add(counter, product);
        } else {
            for (Items comparable : ItemsCart) {
                if (comparable.equals(product)) {
                    ItemsCart.set((ItemsCart.indexOf(comparable)), product);
                    counter--;
                    break;
                }
            }
        }
    }

    public static void displayCashier ( boolean discount){
        System.out.println();
        displayItems();

        _SubTotals = new double[counter];
        double originalTotal;

        for (int x = 0; x < counter; x++) {
            _SubTotals[x] = cashier.getPerItemTotal(ItemsCart.get(x).getPrice(), ItemsCart.get(x).getQuantity());
        }

        cashier.setTotal(_SubTotals, discount);
        originalTotal = cashier.Total;
        cashier.Total += cashier.getVAT(cashier.Total);
        System.out.printf("\nTotal: \t\t\t\t\t\t%,.3f\n", cashier.Total);

        if (discount) {
            cashier.Total = cashier.Total - cashier.Discount;
            System.out.printf("Discounted: \t\t\t\t%,.3f\n", cashier.Total);
        } else
            System.out.printf("Discounted: \t\t\t\t%,.3f\n", 0.000);


        System.out.print("Cash:\t\t\t\t\t\t");
        userCash = scan.nextDouble();

        while (userCash < cashier.Total) {
            System.out.println("Enter a valid cash amount!");
            System.out.print("Cash:\t\t\t\t\t\t");
            userCash = scan.nextDouble();
        }

        System.out.printf("Change: \t\t\t\t\t%,.3f\n", (cashier.setgetChange(userCash)));

        System.out.printf("\nVatable: \t\t\t\t\t%,.3f\n", cashier.Total);
        System.out.printf("VAT: \t\t\t\t\t\t%,.3f\n", cashier.getVAT(originalTotal));
    }

}