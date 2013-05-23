all:
	javac -cp . ./StockExchange/*.java
	javac -cp . ./StockExchange/test/*.java
	javac -cp . ./StockExchange/QuoterPackage/*.java

server: 
	java StockExchange.Server

client:
	java StockExchange.Client "SAP AG"

test:
	java StockExchange.test.Run

clean:
	find ./StockExchange/ -name "*.class" -exec rm {} \;
