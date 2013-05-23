all:
	javac -cp . ./middleware/getQuote/*.java 
	javac -cp . ./middleware/test/*.java 
	#jaco -cp . ./middleware/stockExchangeAMI/*.java 
	#javac -cp . ./middleware/stockExchangeOnewayCall/*.java 
	javac -cp . ./middleware/stockExchangeSII/*.java 

server:
	jaco middleware.stockExchangeSII.Server ServerIOR

client_sii:
	java middleware.stockExchangeSII.Client ServerIOR adidas AG

client_ami:
	jaco middleware.stockExchangeAMI.Client ServerIOR adidas AG

client_oneway_call:
	jaco middleware.stockExchangeOnewayCall.Client adidas AG

test:
	java middleware.test.Run

clean:
	find middleware/ -name *.class -exec rm {} \;
