#ifndef BUDGET_H
#define BUDGET_H

#include "data.h"

void displayEntries(Entry *entries, int nmbEntries);
float calculateTotalType(Entry *entries, int nmbEntries, char type[]);
float calculateTotalSubtype(Entry *entries, int nmbEntries, char subtype[]);
void addEntry(Entry **entries, int *count);
void modifyEntry(Entry *entries, int count);
void filterByMonth(Entry *entries, int count);

#endif