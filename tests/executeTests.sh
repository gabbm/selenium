#!/bin/sh

current_time=$(date "+%Y.%m.%d-%H.%M.%S")

# Is necessary to configure the DISPLAY for Firefox
Xvfb :0 & export DISPLAY=:0

# CaixaImpulse tests

echo "Accessing CaixaImpulse tests folder"
cd /opt/selenium/tests/caixaimpulse

echo "If Java classes are not compiled, compile them"
if [ -n "$(ls -l *.class | wc -l)" ]
then 
	echo "Compiling java classes"
	javac -classpath ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestCaixaImpulseRunner.java
fi 

echo "Executing CaixaImpulse tests"
java -cp ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestCaixaImpulseRunner > /opt/selenium/results/caixaimpulse/caixaimpulse-test-results-$current_time.log
echo "CaixaImpulse tests executed"

echo "Sending email"
if grep -q "Test successful ? false" "/opt/selenium/results/caixaimpulse/caixaimpulse-test-results-$current_time.log"; then
	subject="[KO] Tests CaixaImpulse $current_time"
else
	subject="[OK] Tests CaixaImpulse $current_time"
fi
cat /opt/selenium/results/caixaimpulse/caixaimpulse-test-results-$current_time.log | mail -s "$subject" bustia.fundacio@vass.es

# RepteEmpren tests

current_time=$(date "+%Y.%m.%d-%H.%M.%S")

echo "Accessing RepteEmpren tests folder"
cd /opt/selenium/tests/repteempren

echo "If Java classes are not compiled, compile them"
if [ -n "$(ls -l *.class | wc -l)" ]
then 
	echo "Compiling java classes"
	javac -classpath ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestRepteEmprenRunner.java
fi 

echo "Executing RepteEmpren tests"
java -cp ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestRepteEmprenRunner > /opt/selenium/results/repteempren/repteempren-test-results-$current_time.log
echo "RepteEmpren tests executed"

echo "Sending email"
if grep -q "Test successful ? false" "/opt/selenium/results/repteempren/repteempren-test-results-$current_time.log"; then
	subject="[KO] Tests RepteEmpren $current_time"
else
	subject="[OK] Tests RepteEmpren $current_time"
fi
cat /opt/selenium/results/repteempren/repteempren-test-results-$current_time.log | mail -s "$subject" bustia.fundacio@vass.es

# Becaris tests

current_time=$(date "+%Y.%m.%d-%H.%M.%S")

echo "Accessing Becaris tests folder"
cd /opt/selenium/tests/becaris

echo "If Java classes are not compiled, compile them"
if [ -n "$(ls -l *.class | wc -l)" ]
then 
	echo "Compiling java classes"
	javac -classpath ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestBecarisRunner.java
fi 

echo "Executing Becaris tests"
java -cp ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestBecarisRunner > /opt/selenium/results/becaris/becaris-test-results-$current_time.log
echo "Becaris tests executed"

echo "Sending email"
if grep -q "Test successful ? false" "/opt/selenium/results/becaris/becaris-test-results-$current_time.log"; then
	subject="[KO] Tests Becaris $current_time"
else
	subject="[OK] Tests Becaris $current_time"
fi
cat /opt/selenium/results/becaris/becaris-test-results-$current_time.log | mail -s "$subject" bustia.fundacio@vass.es

# Incorporer tests

current_time=$(date "+%Y.%m.%d-%H.%M.%S")

echo "Accessing Incorporer tests folder"
cd /opt/selenium/tests/incorporer

echo "If Java classes are not compiled, compile them"
if [ -n "$(ls -l *.class | wc -l)" ]
then 
	echo "Compiling java classes"
	javac -classpath ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestIncorporerRunner.java
fi 

echo "Executing Incorporer tests"
java -cp ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestIncorporerRunner > /opt/selenium/results/incorporer/incorporer-test-results-$current_time.log
echo "Incorporer tests executed"

echo "Sending email"
if grep -q "Test successful ? false" "/opt/selenium/results/incorporer/incorporer-test-results-$current_time.log"; then
	subject="[KO] Tests Incorporer $current_time"
else
	subject="[OK] Tests Incorporer $current_time"
fi
cat /opt/selenium/results/incorporer/incorporer-test-results-$current_time.log | mail -s "$subject" bustia.fundacio@vass.es

# Incorpora tests

current_time=$(date "+%Y.%m.%d-%H.%M.%S")

echo "Accessing Incorpora tests folder"
cd /opt/selenium/tests/incorpora

echo "If Java classes are not compiled, compile them"
if [ -n "$(ls -l *.class | wc -l)" ]
then 
	echo "Compiling java classes"
	javac -classpath ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestIncorporaRunner.java
fi 

echo "Executing Incorpora tests"
java -cp ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestIncorporaRunner > /opt/selenium/results/incorpora/incorpora-test-results-$current_time.log
echo "Incorpora tests executed"

echo "Sending email"
if grep -q "Test successful ? false" "/opt/selenium/results/incorpora/incorpora-test-results-$current_time.log"; then
	subject="[KO] Tests Incorpora $current_time"
else
	subject="[OK] Tests Incorpora $current_time"
fi
cat /opt/selenium/results/incorpora/incorpora-test-results-$current_time.log | mail -s "$subject" bustia.fundacio@vass.es