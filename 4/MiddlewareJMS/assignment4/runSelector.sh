# Compile
for j in */*.java
do
  if [[ $j != test/* ]]
  then
    echo Compile $j
    javac $j
  fi
done

# run publisher
echo
echo Run publisher...
echo
echo ***NOTICE***
echo If you want to add/delete stock on publisher side,
echo Please run quotePub.QuotePublisherA in another bash,
echo otherwise you need to kill it yourself afterward.
echo
java quotePub.QuotePublisherA &
sleep 2
echo ...publisher is now running!
echo

# run subMsgSelector
echo Run message selector sub...
echo
echo ***NOTICE***
echo ser file is not the same as for normal subscriber!!
echo
java quoteSubMsgSelector.QuoteSubMsgSelector
