#ifndef DATA_H
#define DATA_H

typedef struct {
    int id;
    char date[11]; // YYYY-MM-DD + "/0"
    char type[8]; // "income", "expense"
    char subtype[8]; // "Passive" etc.
    char description[20];
    float amount;
} Entry;

// int loadEntries(const char *filename, Entry **entries, int *count);

#endif