GpxSplitter
============

Split GPX files too large to fit in GPS handheld devices into multiple files 
that do.

GpxSplitter is meant to be used to generate files to be imported on Handheld 
GPS devices used for hicking, trekking and mountain/road biking.

GPS handheld devices have historically had limited features to minimise power 
consumption as some are powered with consumer-grade cells.
More recent GPS devices have better-performing built-in rechargeable cells. 
However, feature sets are still limited.

Why split Gpx files
-------------------

In 2011 I purchased a Garmin Etrex Legend for road bike training and to load 
Audax routes.
As the milage I could ride went up the amount of waypoints in the routes also 
grew.
The eTrex Legend has a limit of 125 waypoints per route file so I needed to 
start "splitting" Gpx files too long to be imported.
That is when development of GpxSplitter started.

Downloads
---------

Downloadable artefacts for all platforms are published as [GpxSplitter Github releases](https://github.com/AntoCuc/GpxSplitter/releases).

Release Notes
-------------

0.3(30/09/2016)

 - Dropped the facility to convert routes to tracks and viceversa
 - Further addressed performance issues
 - Automated support for splitting of multi-segment track files
 - Technical
   - Migrated from Ant to Maven
   - Migrated from JDOM to JaxB
   - Added checkstyle rule checks
   - Added Unit test coverage checks
   - Added manifest for executable Jar (Maven build)
   - Added Windows executable goal (Maven build)
   - Added Mac executable goal (Maven build)

0.2 (23/06/2011)

 - Addressed issues with speed when splitting large GPX files
 - Introduced manual support for splitting of multi-segment track files

0.1 (16/06/2011)

 - First release
 - Basic splitting support for routes and tracks

Building from source
--------------------

If you are not particularly interested in building GpxSplitter yourself
download one of the executables and jump to "Running the software".

### Dependencies ###

To clone GpxSplitter from Github:
 - git 2.7.0 or greater

To build the source code:
 - Oracle JDK 8 or greater
 - Apache Maven 3.3 or greater
 
To run the downloaded GpxSplitter executable:
 - Oracle/Open Java Runtime 8 or greater

### Building ###

After cloning the github repository building the GpxSplitter source consists of
moving to the clone directory and running the Apache Maven goal "install".
The "install" goal will generate a "target" directory containing the GpxSplitter
JAR file (`gpxsplitter-<version>.jar`).

The commands to obtain the above would be:

 1. Clone the repository - `git clone <repository_path>`
 2. Move to the cloned directory - `cd GpxSplitter`
 3. Invoke maven install - `mvn install`
 4. Move in the artefacts directory generated - `cd target`
 5. Run the generated executable - `java -jar gpxsplitter-<version>.jar`

### Running ###

Running GpxSplitter on any platform requires Java 8 or Open JDK.

Microsoft Windows

 - Double-click the `gpxsplitter-<version>.jar` executable jar
 
Or
 
 - Double-click the `gpxsplitter.exe` native executable

Mac OSX

 - Double-click the `gpxsplitter-<version>.jar` executable jar

Or

 - Download the DMG image (OSX verifies and opens GpxSplitter's directory)
 - Drag the App to your "Applications" folder and/or to your Dock
 - Run via Finder or clicking the icon on the dock

Linux (any recent distro)

 - Run the command `java -jar gpxsplitter-<version>.jar`

Software Builds
---------------

### Master ###

The master branch of the GpxSplitter is a set of code in its most mature state.

[![Build Status](https://travis-ci.org/AntoCuc/GpxSplitter.svg)](https://travis-ci.org/AntoCuc/GpxSplitter)

What does "Build Passing" mean:

 - The code complies with Sun Microsystems' style checks (only exception is EOL)
 - At least 80% of the code's business logic is unit tested
 - A cross-platform executable JAR can be successfully generated
 - A Microsoft Windows executable can be successfully generated
 - An OSX DMG disk image and executable can be successfully generated

Monthly updates
---------------

If no features are developed a monthly review of dependencies and plugins is 
performed.

Issues
------

Have you found a problem with GpxSplitter? 
[Please let us know](https://github.com/AntoCuc/GpxSplitter/issues).
We will fix it ASAP.

License
-------

The GpxSplitter software is free and released under The MIT License. 
Details below.

The MIT License 

Copyright 2011-2016 Antonino Cucchiara. 

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
