#ifndef MSG_ENCODER_H_INCLUDED
#define MSG_ENCODER_H_INCLUDED
uint8_t * msg_encoder(int code, char *str);
int getMsgLength(uint8_t *msg_addr);
#endif
