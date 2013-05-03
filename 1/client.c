#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>

#define SRV_PORT 5000
#define MAXMSG 200

void swap_case(char text[]) {
    // swap all letters of string
    char *text2 = text;
    while (text2[0] != '\0') {
        if (text2[0] > 64 && text2[0] < 91) {
            text2[0] += 32;
        }
        else if (text2[0] > 96 && text2[0] < 123) {
            text2[0] -= 32;
        }
        text2++;
    }
}

int main(int argc, char *argv[]) {
    int socketfd;
    size_t message_size;
    struct sockaddr_in srv_addr, cli_addr;
    char message[MAXMSG]; 
    int repeat = 1;
    int step = 0;
    char *message2;

    if (argc < 2) {
        printf("Usage client <1-126>\n");
        return 1;
    }
    char num = atoi(argv[1]);
    if (num < 1 || num > 126) {
        printf("Usage client <1-126>\n");
        return 1;
    }

    // sockets
    socketfd = socket(AF_INET, SOCK_DGRAM, 0);

    // addresses
    bzero(&srv_addr, sizeof(srv_addr));
    srv_addr.sin_family = AF_INET;
    srv_addr.sin_addr.s_addr = htonl(INADDR_LOOPBACK);
    srv_addr.sin_port = htons(SRV_PORT); 
    bzero(&cli_addr, sizeof(cli_addr));
    cli_addr.sin_family = AF_INET;
    cli_addr.sin_addr.s_addr = htonl(INADDR_LOOPBACK);
    cli_addr.sin_port = htons(SRV_PORT+num); 

    printf("Started client\n");
    bind(socketfd, (struct sockaddr *) &cli_addr, sizeof(cli_addr));
    
    while(1) {
        // Send number till receiving right response
        sendto (socketfd, &num, sizeof(char), 0, (struct sockaddr *) \
            &srv_addr, sizeof(srv_addr));
        printf("> letter '%c' or number '%d'\n", num, num);
        bzero(&message, MAXMSG);
        recv (socketfd, &message, MAXMSG, 0);
        printf("< '%s'\n", message);
        if ((int) message[0] == num + 1) {
            // if the number was increased and send back, take the rest of
            //  the string
            message2 = message;
            message2 += 2;
            swap_case(message2);
            break;
        }
        sleep(3);
    }
    // Return the response, but swap all letters in the string
    // < 57 asklLDWdsdwq
    sendto (socketfd, message2, strlen(message2) * sizeof(char), \
        0, (struct sockaddr *) &srv_addr, sizeof(srv_addr));
    printf("> '%s'\n", message2);
    bzero(&message, MAXMSG);
    recv (socketfd, &message, MAXMSG, 0);
    printf("< '%s'\n", message);
    if (strcmp(message, "OK") == 0) {
        printf("success :)\n");
        return 0;
    } else if (strcmp(message, "FAIL") == 0) {
        printf("failed :(\n");
        return 0;
    }
    printf("No response :---(");
    return 1;
}
