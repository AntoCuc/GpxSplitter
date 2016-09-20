Gpx Splitter
============

Split GPX files too large to fit in GPS handheld devices into multiple files that do.

This software is meant to be used to generate files to be used on Handheld GPS devices used for hicking, trakking and mountain/road biking.

GPS handheld devices have historically had limited features so to minimise power consumption as some of them are powered with consumer grade cells.
Some of the more recent GPS devices have better-performing built-in rechargeable cells. However, their feature sets are still limited.

Why split Gpx files
-------------------

In 2011 I purchased a Garmin Etrex Legend for road bike training and to load Audax routes.
As the milage I could ride went up the amount of waypoints in the routes also grew.
The eTrex Legend has a limit of 125 waypoints per route file so soon I needed to start "splitting" Gpx long files.

Issues
------

Have you found a problem with the software? [Please let me know](https://github.com/AntoCuc/GpxSplitter/issues). I will get it fixed as soon as I can.

Getting started (technical)
---------------------------

### Dependencies ###

To build the software:
 - Oracle JDK 8 or greater
 - Apache Maven 3.3 or greater
 
To run the software:
 - Oracle Java Runtime 8 or greater

### Building the code ###

After cloning the github repository building the GpxSplitter source consists of running the Apache Maven goal "install".
The "install" goal will generate a "target" directory containing the GpxSplitter JAR file.

### Running the software ###

On Microsoft Windows the GpxSplitter<version>.jar file can be double-clicked to run the software.
On Linux and Mac running the command "java -jar GpxSplitter<version>.jar" will run the software.

Software Builds
---------------

### Master ###

The master branch of the Gpx Splitter is a set of code in its most mature state.

[![Build Status](https://travis-ci.org/AntoCuc/GpxSplitter.svg)](https://travis-ci.org/AntoCuc/GpxSplitter)

License
-------
The Gpx Splitter software is free and released under The MIT License. Details below.

The MIT License 

Copyright 2016 Antonino Cucchiara. 

Permission is hereby granted, free of charge, to any person obtaining a copy 
of this software and associated documentation files (the "Software"), to deal 
in the Software without restriction, including without limitation the rights 
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions: 

The above copyright notice and this permission notice shall be included in 
all copies or substantial portions of the Software. 

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
THE SOFTWARE. 