#! /bin/bash
su -

# Ubuntu alternative
#sudo apt-get install software-properties-common
#sudo add-apt-repository ppa:webupd8team/java
#sudo apt-get update
#sudo apt-get install oracle-java8-installer

# Update repositories
echo "Updating repositories"
echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list
echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
apt-get update

# Accept Oracle license agreement
echo "Accepting Oracle license agreement"
echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections

# Install JDK 7 and make it the default java version
echo "Installing JDK 7 and makint it the default java version"
apt-get install oracle-java7-installer
sudo apt-get install oracle-java7-set-default
exit
