#include "data.h"
#include <string.h>
#include <stdio.h>

void displayEntries(Entry *entries, int nmbEntries) {
    printf("\nFinances Summary\n"
           "================\n");
    printf("%-4s %-12s %-10s %-10s %-15s %s\n",
               "ID",
               "Date",
               "Type",
               "Subtype",
               "Description",
               "Amount");
    printf("------------------------------------------------------------------\n");
    for (int i = 0; i < nmbEntries; i++)
        printf("%-4d %-12s %-10s %-10s %-15s $%8.2f\n",
               entries[i].id,
               entries[i].date,
               entries[i].type,
               entries[i].subtype,
               entries[i].description,
               entries[i].amount);
}

float calculateTotalType(Entry *entries, int nmbEntries, char type[]) {
    float total = 0.0f;
    for (int i = 0; i < nmbEntries; i++)
        if (strcmp(entries[i].type, type) == 0)
            total += entries[i].amount;
    return total;
}

float calculateTotalSubtype(Entry *entries, int nmbEntries, char subtype[]) {
    float total = 0.0f;
    for (int i = 0; i < nmbEntries; i++)
        if (strcmp(entries[i].subtype, subtype) == 0)
            total += entries[i].amount;
    return total;
}