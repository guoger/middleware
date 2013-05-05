#include <stdio.h>
#include <sys/types.h>
#include <Message.h>   /* Message ASN.1 type */

typedef uint8_t u8;

int getMsgLength(u8 *msg_addr) {
  return msg_addr[1]+2;
}

uint8_t * msg_encoder(int code, char *str) {
	Message_t *message; /* Type to encode		   */
	asn_enc_rval_t ec;	  /* Encoder return value  */
	/* Allocate the Message_t */
  u8 * buf;
  buf = calloc(1, sizeof(Message_t));

  /* calloc message */
	message = calloc(1, sizeof(Message_t)); /* not malloc! */
	if(!message) {
		perror("calloc() failed");
		exit(1);
	}
	/* Initialize the Message members */
	message->code = code;
	message->randStr.buf = calloc(1, strlen(str));
	memcpy(message->randStr.buf, str, strlen(str));
	message->randStr.size = strlen(str);

  /* encode message to buffer */
  ec = der_encode_to_buffer(&asn_DEF_Message, message, buf, sizeof(Message_t));

  xer_fprint(stdout, &asn_DEF_Message, message);
  printf("%ld\n", sizeof(buf));

  int msglen = getMsgLength(buf);
  for (int j = 0; j < msglen; j++) {
    printf("%x ", buf[j]);
  }
  printf("\n");
	
	return buf; /* Encoding finished successfully */
}
