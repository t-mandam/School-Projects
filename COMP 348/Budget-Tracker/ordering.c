#include "data.h"
#include <stdbool.h>
#include <string.h>
#include <stdio.h>

void sortMenu(Entry *entries, int nmbEntries, int sortChoice) {
    // 1. Sort by ID
    // 2. Sort by Date
    // 3. Sort by Amount
    // 4. Sort by Description
    bool sorted;
    switch (sortChoice) {
        case 1:
            do {
                sorted = false;
                for (int i = 0; i < nmbEntries - 1; i++)
                    if (entries[i].id > entries[i + 1].id) {
                        Entry temp = entries[i];
                        entries[i] = entries[i + 1];
                        entries[i + 1] = temp;
                        sorted = true;
                    }
            } while (sorted);
            break;
        case 2:
            do {
                sorted = false;
                for (int i = 0; i < nmbEntries - 1; i++)
                    if (strcmp(entries[i].date, entries[i + 1].date) > 0) {
                        Entry temp = entries[i];
                        entries[i] = entries[i + 1];
                        entries[i + 1] = temp;
                        sorted = true;
                    }
            } while (sorted);
            break;
        case 3:
            do {
                sorted = false;
                for (int i = 0; i < nmbEntries - 1; i++)
                    if (entries[i].amount > entries[i + 1].amount) {
                        Entry temp = entries[i];
                        entries[i] = entries[i + 1];
                        entries[i + 1] = temp;
                        sorted = true;
                    }
            } while (sorted);
            break;
        case 4:
            do {
                sorted = false;
                for (int i = 0; i < nmbEntries - 1; i++)
                    if (strcmp(entries[i].description, entries[i + 1].description) > 0) {
                        Entry temp = entries[i];
                        entries[i] = entries[i + 1];
                        entries[i + 1] = temp;
                        sorted = true;
                    }
            } while (sorted);
            break;
        default:
            printf("Invalid choice. Returning to main menu.\n");
    }
}