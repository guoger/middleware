CLIENT_EXEC=cClient
CLIENT_CODE=cCode
SERVER_EXEC=javaServer
ASN1C_FLAGS=-fnative-types
CFLAGS += -I. 

all: client Server.class

new: clean all

compress:
	tar czvf middleware1_nsemmler.tgz server.java client.c Makefile run.bash README

client: $(CLIENT_CODE)/client.c
	mkdir -p $(CLIENT_EXEC)
	cp message.asn1 $(CLIENT_EXEC)
	cp $(CLIENT_CODE)/{client.c,msg_*} $(CLIENT_EXEC)
	cd $(CLIENT_EXEC); \
	asn1c $(ASN1C_FLAGS) message.asn1; \
	rm message.asn1 converter-sample.c; \
	gcc $(CFLAGS) *.c -o ../client

clean: 
	rm -Rf $(CLIENT_EXEC)
	rm -f client
