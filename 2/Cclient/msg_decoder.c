#include <stdio.h>
#include <sys/types.h>
#include <Message.h>   /* Message ASN.1 type */
#include <msg_encoder.h>

typedef uint8_t u8;

Message_t * msg_decoder(uint8_t *msg_addr, int size) {
	Message_t *message = 0;
	asn_dec_rval_t rval;
	char *fail = "FAIL";
	
	rval = ber_decode(0, &asn_DEF_Message, (void **)&message, msg_addr, size);
	if(rval.code != RC_OK) {
		printf("Decoding failed !\n");
	}
  printf("\n [CLIENT] Decoding !\n");
  for (int j = 0; j < size; j++) {
    printf("%x ", msg_addr[j]);
  }
  printf("\n");
	//xer_fprint(stdout, &asn_DEF_Message, message);
	
	return message;
}
/*
int main(int argc, char **argv) {
	int code = atoi(argv[1]);
	char *str = argv[2];
	uint8_t * buf;
	Message_t *message;
	buf = calloc(1, sizeof(Message_t));
	
	buf = msg_encoder(code, str);
	int size = getMsgLength(buf);
	
	message = msg_decoder(buf, size);
	printf("word is %s\n", message->randStr.buf);
	return 0;
}*/
