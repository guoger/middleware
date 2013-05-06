#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <Message.h>
#include <msg_encoder.h>
#include <msg_decoder.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>

typedef uint8_t u8;

int main(int argc, char **argv) {
	/* define variables */
	char *randStr;	// random string
	struct sockaddr_in serv_addr;
	int sockfd = 0, n = 0;
	char server[] = "127.0.0.1";
	u8 recvBuffer[32];
	u8 * sendBuffer;
	Message_t *message;
	int size;
	
	/* verify arguments */
	if(argc != 2) {
		printf("\n [CLIENT] ERROR : arguments amount error !\n");
		return 1;
	}

	/* memory set */
	memset(&serv_addr, 0, sizeof(serv_addr));
	memset(&recvBuffer, 0, sizeof(recvBuffer));
	memset(&sendBuffer, 0, sizeof(sendBuffer));
	randStr = calloc(1, sizeof(Message_t));	
	
	/* create socket */
	if((sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1){
		printf("\n [CLIENT] ERROR : Could not create socket \n");
		return 1;
	} else {
		printf("\n [CLIENT] Socket created ! \n");
		//return 0;
	}
	
	/* set serv_addr structure */
	serv_addr.sin_port = htons(5000);
	serv_addr.sin_family = AF_INET;
	if(inet_pton(AF_INET, server, &serv_addr.sin_addr) <= 0)
	{
		printf("\n [CLIENT] ERROR : inet_pton error occured \n");
		return 1;
	}
	
	/* connect to server */
	if(connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
	{
		printf("\n [CLIENT] ERROR : Connect Failed \n");
		return 1;
	}
	
	/* set arguments */
	int code = atoi(argv[1]);
	randStr = "";
	
	/* encode into message */
	sendBuffer = msg_encoder(code, randStr);
	int msg_len = getMsgLength(sendBuffer);
	
	/* send message */
  printf("\n [CLIENT]\t[SEND] %d\n", code);
	if ((n = write(sockfd, sendBuffer, (int)msg_len)) < 0) {
		printf("\n [CLIENT] ERROR : Write Failed !\n");
		return 1;
	}

	/* receive message */
	if ((n = read(sockfd, recvBuffer, sizeof(recvBuffer))) < 0) {
		printf("\n [CLIENT] ERROR : Read Failed ! %d\n", n);
		return 1;
	}
	
	/* decoding */
	size = getMsgLength(recvBuffer);
	message = msg_decoder(recvBuffer, size);
	randStr = (char *)message->randStr.buf;
	printf(" [CLIENT]\t[RECV] Random string is %s\n", randStr);
	
	/* swap case */
    int lenStr = strlen(randStr);
    int i;
	for (i=0; i < lenStr ; i++){
		if (randStr[i] >= 'A' && randStr[i] <= 'Z')
			randStr[i] += ('a' - 'A');
		else if (randStr[i] >= 'a' && randStr[i] <= 'z')
			randStr[i] -= ('a' - 'A');
	}	
	
	/* encoding swaped case string */
	sendBuffer = msg_encoder(code, randStr);
	msg_len = getMsgLength(sendBuffer);
	
	/* send message */
  printf("\n [CLIENT]\t[SEND] %s\n", randStr);
	if ((n = write(sockfd, sendBuffer, (int)msg_len)) < 0) {
		printf("\n [CLIENT] ERROR : Write Failed !\n");
		return 1;
	}
	
	/* receive message */
	if ((n = read(sockfd, recvBuffer, sizeof(recvBuffer))) < 0) {
		printf("\n [CLIENT] ERROR : Read Failed ! %d\n", n);
		return 1;
	}
	
	/* decoding */
	size = getMsgLength(recvBuffer);
	message = msg_decoder(recvBuffer, size);
	randStr = (char *)message->randStr.buf;
	printf(" [CLIENT]\t[RECV] %s\n", randStr);
	
	shutdown(sockfd, 2);
	
	return 0;
}
