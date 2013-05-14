#!/bin/bash
echo "Please add me as so:"
echo
echo "source $0"
echo
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
ANT_HOME="$DIR/apache-ant-1.9.0/"
JACORB_HOME="$DIR/jacorb-3.2"
PATH="$ANT_HOME/bin:$JACORB_HOME/bin:$PATH"
JAVA_HOME="$ANT_HOME:$JACORB_HOME:$JAVA_HOME"
