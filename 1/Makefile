all: client Server.class

new: clean all

compress:
	tar czvf middleware1_nsemmler.tgz server.java client.c Makefile run.bash README

Server.class: Server.java
	javac Server.java

client: client.c
	gcc client.c -o client

clean: 
	rm -f Server.class client
