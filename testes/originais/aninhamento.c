// Aninhamento
#include <stdio.h>
void main() {
    int i;
    int j;
    i = 0;
    j = i;
    while(i < 3) {
        if(i == 0)
            printf("Zero");
        else
            printf("Outro");
            i++;
    }
    if(i == 3) {
        if(j == 0)
            printf("Ok!");
        else
            printf("Erro");
    }
}