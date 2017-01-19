Here you can find the steps to install and run successfully a selenium server into a Debian distribution.

1- Install Java JDK 1.7 and make it the default java instruction (install/install_jdk7.sh)
2- Install firefox (install/install_firefox.sh)
3- Install selenium (install/install_selenium.sh)

After the installation you could execute the Selenium tests created with Selenium IDE or handful written. 

You must use the JUnit 4 libraries for the Selenium tests.

You can find an example into tests folder:
	- TestSelenium.java -> Selenium test
	- TestSeleniumRunner -> Main class to run the TestSelenium class

to run them you should execute:
	- Compile Java classes:
		- javac -classpath ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestSeleniumRunner.java
		- javac -classpath ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestSelenium.java
	- Execute Java classes:
		- java -cp ".:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/*:/opt/selenium/selenium-java-2.53.1/selenium-2.53.1/libs/*" TestSeleniumRunner

You would obtain an output like this:
`1 XSELINUXs still allocated at reset`
SCREEN: 0 objects of 176 bytes = 0 total bytes 0 private allocs
DEVICE: 0 objects of 96 bytes = 0 total bytes 0 private allocs
CLIENT: 0 objects of 160 bytes = 0 total bytes 0 private allocs
WINDOW: 0 objects of 48 bytes = 0 total bytes 0 private allocs
PIXMAP: 1 objects of 16 bytes = 16 total bytes 0 private allocs
GC: 0 objects of 56 bytes = 0 total bytes 0 private allocs
CURSOR: 0 objects of 8 bytes = 0 total bytes 0 private allocs
CURSOR_BITS: 0 objects of 8 bytes = 0 total bytes 0 private allocs
DBE_WINDOW: 0 objects of 24 bytes = 0 total bytes 0 private allocs
TOTAL: 1 objects, 16 bytes, 0 allocs
1 PIXMAPs still allocated at reset
PIXMAP: 1 objects of 16 bytes = 16 total bytes 0 private allocs
GC: 0 objects of 56 bytes = 0 total bytes 0 private allocs
CURSOR: 0 objects of 8 bytes = 0 total bytes 0 private allocs
CURSOR_BITS: 0 objects of 8 bytes = 0 total bytes 0 private allocs
DBE_WINDOW: 0 objects of 24 bytes = 0 total bytes 0 private allocs
TOTAL: 1 objects, 16 bytes, 0 allocs
[dix] Could not init font path element /usr/share/fonts/X11/cyrillic, removing from list!
[dix] Could not init font path element /usr/share/fonts/X11/100dpi/:unscaled, removing from list!
[dix] Could not init font path element /usr/share/fonts/X11/75dpi/:unscaled, removing from list!
[dix] Could not init font path element /usr/share/fonts/X11/Type1, removing from list!
[dix] Could not init font path element /usr/share/fonts/X11/100dpi, removing from list!
[dix] Could not init font path element /usr/share/fonts/X11/75dpi, removing from list!
[dix] Could not init font path element /var/lib/defoma/x-ttcidfont-conf.d/dirs/TrueType, removing from list!
Test executed in 40009 ms
`Test successful ? true`
	
the last two lines are System.out from TestSeleniumRunner. If there had been errors, it would have shown you which kind of problem it would have been.
