#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "data.h"
#include "budget.h"
#include "ordering.h"
#define MAX_ENTRIES 20

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "%s %s \n", "Try running again by providng the input file", argv[0]);
        return 1;
    }

    FILE *finances = fopen(argv[1], "r");

    int choice, nmbEntries = 0;
    Entry *entries = (Entry *) malloc(MAX_ENTRIES * sizeof(Entry));

    // Check if memory allocation and file opening were successful
    if (entries == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }
    if (finances == NULL) {
        fprintf(stderr, "Could not open file %s\n", argv[1]);
        free(entries);
        return 1;
    }

    char line[100];
    while (fgets(line, sizeof(line), finances) != NULL) {
        line[strcspn(line, "\n")] = 0; // Remove \n
        char *token = strtok(line, "|");
        entries[nmbEntries].id = atoi(token);
        token = strtok(NULL, "|");
        strncpy(entries[nmbEntries].date, token, 11);
        entries[nmbEntries].date[10] = '\0'; // Ensure null termination
        token = strtok(NULL, "|");
        strcpy(entries[nmbEntries].type, token);
        token = strtok(NULL, "|");
        strcpy(entries[nmbEntries].subtype, token);
        token = strtok(NULL, "|");
        strcpy(entries[nmbEntries].description, token);
        token = strtok(NULL, "|");
        entries[nmbEntries].amount = atof(token);
        nmbEntries++;
    }
    
    // Check if entries were loaded
    if (nmbEntries == 0) {
        fprintf(stderr, "No entries found in the file.\n");
        free(entries);
        return 1;
    }

    fclose(finances);

    // Main menu loop
    do {
        // Display the menu}
        printf("\nBudget Tracking System\n"
            "======================\n"
            "1. Display All Entries\n"
            "2. Expense Distribution\n"
            "3. Sort Entries\n"
            "4. Add Income/Expense Entry\n"
            "5. Modify Entry\n"
            "6. Filter by Month\n"
            "7. Exit\n"
            "Choice: ");
        // Read user choice
        scanf("%d", &choice);

        // Handle user choice
        switch (choice) {
            case 1:
                // 1. Display All Entries
                sortMenu(entries, nmbEntries, 1); // Sort by ID
                displayEntries(entries, nmbEntries);
                break;
            case 2:
                // 2. Expense Distribution
                float income = calculateTotalType(entries, nmbEntries, "income"); 
                float expenses = calculateTotalType(entries, nmbEntries, "expense");
                float needs = calculateTotalSubtype(entries, nmbEntries, "Needs");
                float wants = calculateTotalSubtype(entries, nmbEntries, "Wants");
                printf("\n===== Expense Distribution Report =====\n");
                printf("Total Income: $%.2f\n", income);
                printf("Total Expenses: $%.2f\n", expenses);
                printf("Needs: $%.2f (%.2f%% of expenses, %.2f%% of income)\n", needs, needs/expenses*100, needs/income*100);
                printf("Wants: $%.2f (%.2f%% of expenses, %.2f%% of income)\n", wants, wants/expenses*100, wants/income*100);
                printf("Net Balance: $%.2f\n", income - expenses);
                printf("========================================\n");
                break;
            case 3:
                // 3. Sort Entries
                int sortChoice;
                printf("Sort Menu\n"
                    "1. Sort by ID\n"
                    "2. Sort by Date\n"
                    "3. Sort by Amount\n"
                    "4. Sort by Description\n"
                    "Choice: ");
                scanf("%d", &sortChoice);

                // Handle sorting choice
                sortMenu(entries, nmbEntries, sortChoice);
                displayEntries(entries, nmbEntries);
                break;
            case 4:
                // 4. Add Income/Expense Entry
                int id;
                char date[11], type[8], subtype[8], description[20];
                float amount;
                printf("Enter date (YYYY-MM-DD): ");
                scanf("%s", date);
                getchar();
                printf("Type (income/expense): ");
                scanf("%s", type);
                getchar();
                printf("Subtype: ");
                scanf("%s", subtype);
                getchar();
                printf("Description: ");
                fgets(description, sizeof(description), stdin);
                description[strcspn(description, "\n")] = 0; // Remove trailing newline
                printf("Amount: ");
                scanf("%f", &amount);
                getchar();
                if (amount < 0)
                    amount = 0;
                // Create new entry
                id = nmbEntries + 101; // ID starts from 101
                entries[nmbEntries].id = id;
                for (int i = 0; i < 11; i++)
                    entries[nmbEntries].date[i] = date[i];
                for (int i = 0; i < 8; i++) {
                    entries[nmbEntries].type[i] = type[i];
                    entries[nmbEntries].subtype[i] = subtype[i];
                }
                for (int i = 0; i < 20; i++)
                    entries[nmbEntries].description[i] = description[i];
                entries[nmbEntries].amount = amount;
                nmbEntries++;
                printf("Entry added successfully with ID: %d\n", entries[nmbEntries - 1].id);
                break;
            case 5:
                // 5. Modify Entry
                sortMenu(entries, nmbEntries, 1); // Sort by ID
                displayEntries(entries, nmbEntries);
                int idSearch;
                printf("Enter the ID of the entry to modify: ");
                scanf("%d", &idSearch);
                getchar();
                if (idSearch > 100 && idSearch <= nmbEntries + 100) {
                    // ID found, display current details
                    printf("Current Details:\n");
                    printf("ID: %d\n", entries[idSearch - 101].id);
                    printf("Date: %s\n", entries[idSearch - 101].date);
                    printf("Type: %s\n", entries[idSearch - 101].type);
                    printf("Subtype: %s\n", entries[idSearch - 101].subtype);
                    printf("Description: %s\n", entries[idSearch - 101].description);
                    printf("Amount: $%.2f\n", entries[idSearch - 101].amount);

                    printf("\nWhat would you like to modify?\n"
                        "1. Date\n"
                        "2. Amount\n"
                        "Choice: ");
                    int modifyChoice;
                    scanf("%d", &modifyChoice);
                    getchar();
                    switch (modifyChoice) {
                        case 1:
                            printf("Enter new date (YYYY-MM-DD): ");
                            scanf("%s", entries[idSearch - 101].date);
                            getchar();
                            break;
                        case 2:
                            float newAmount;
                            printf("Enter new amount: ");
                            scanf("%f", &newAmount);
                            getchar();
                            if (newAmount >= 0)
                                entries[idSearch - 101].amount = newAmount;
                            break;
                        default:
                            printf("Invalid choice. Returning to main menu.\n");
                    }
                    printf("Entry updated successfully.\n");
                }
                else
                    printf("Entry with ID %d not found.\n", idSearch);
                break;
            case 6:
                // 6. Filter by Month
                int monthChoice;
                printf("Enter month (1-12): ");
                scanf("%d", &monthChoice);
                if (monthChoice < 1 || monthChoice > 12) {
                    printf("Invalid month. Returning to main menu.\n");
                    break;
                }
                printf("Entries for month %d:\n", monthChoice);
                sortMenu(entries, nmbEntries, 2); // Sort by date
                // Display filtered entries
                printf("\nFinances Summary\n"
                        "================\n");
                printf("%-4s %-12s %-10s %-10s %-15s %s\n",
                        "ID",
                        "Date",
                        "Type",
                        "Subtype",
                        "Description",
                        "Amount");
                for (int i = 0; i < nmbEntries; i++) {
                    // Extract month from date
                    char month[3];
                    strncpy(month, entries[i].date + 5, 2);
                    month[2] = '\0';
                    if (atoi(month) == monthChoice)
                        printf("%-4d %-12s %-10s %-10s %-15s $%8.2f\n",
                            entries[i].id, entries[i].date, entries[i].type,
                            entries[i].subtype, entries[i].description, entries[i].amount);
                }
                break;
            case 7:
                // 7. Exit
                printf("Goodbye and thanks for using our budget tracker app!!!\n");
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    } while (choice != 7);
    
    free(entries);

    return 0;
}