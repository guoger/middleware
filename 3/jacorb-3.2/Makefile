all:
	javac -cp . ./middleware/getQuote/*.java 
	javac -cp . ./middleware/test/*.java 
	javac ./middleware/stockExchangeAMI/*.java 
	javac ./middleware/stockExchangeOnewayCall/*.java 
	javac -cp . ./middleware/stockExchangeSII/*.java 
	javac -cp . ./middleware/stockExchangeDII/*.java 

server_sii:
	jaco middleware.stockExchangeSII.Server ServerIOR

server_ami:
	jaco middleware.stockExchangeAMI.Server ServerIOR

server_oneway_call:
	jaco middleware.stockExchangeOnewayCall.Server ServerIOR

client_sii:
	java middleware.stockExchangeSII.Client ServerIOR adidas AG

client_dii:
	java middleware.stockExchangeDII.Client ServerIOR adidas AG

client_ami:
	jaco middleware.stockExchangeAMI.Client ServerIOR adidas AG

client_oneway_call:
	jaco middleware.stockExchangeOnewayCall.Client adidas AG

test:
	java middleware.test.Run

clean:
	find middleware/ -name *.class -exec rm {} \;
