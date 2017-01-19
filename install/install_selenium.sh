#! /bin/bash
su -

# create default directories 
echo "Creating default directories"
mkdir /root/logs
mkdir /opt/selenium
mkdir /opt/selenium/results
mkdir /opt/selenium/tests

# copy selenium server binaries and libraries for TESTs executions
echo "Copying selenium server binaries and libraries"
cp selenium-server-standalone-2.53.1.jar /opt/selenium/
cp -R selenium-java-2.53.1 /opt/selenium/

# copy default selenium configuration for ROOT user
echo "Creating default profile for ROOT user"
cp root /etc/default/
chmod +x /etc/default/root

# copy selenium service
echo "Creating selenium service with default configuration"
cp selenium /etc/init.d/
chmod +x /etc/init.d/selenium

# export :0 display as current configuration
echo "Exporting DISPLAY=:0 as current configuration"
echo "export DISPLAY=:0" >> /etc/profile

exit