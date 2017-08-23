#! /bin/bash
su -

# remove default debian browser
echo "Removing iceweasel browser"
apt-get -y remove iceweasel

# update repository and install firefox
echo "Updating repositories and installing Firefox"
deb http://packages.linuxmint.com debian import
apt-get update
apt-get -y install firefox

# install firefox dependencies if X is not installed (maybe not necessary)
echo "Installing X dependencies if they are not installed"
apt-get -y install xdpyinfo
apt-get -y install gdk-pixbuf2
apt-get -y install libfontconfig
apt-get -y install libxrender1
apt-get -y install libxdamage1
apt-get -y install libxcomposite1
apt-get -y install libasound2
apt-get -y install libdbus-glib-1-2
apt-get -y install libgtk-x11-2
apt-get -y install libgtk2.0-0

# install xvfb
apt-get install xvfb
exit
