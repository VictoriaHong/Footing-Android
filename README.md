# Android Application
## Footing -- Your Travel Assistant

![](http://i.imgur.com/Ixrj7ex.png?1)

### Overview

This is a course project for Java smartphone development (18-654) at Carnegie Mellon University.


- Team name: Puff

- Team members: Xin Victoria Hong, Qiaoyi Chen, I-Huei Huang.

- Time line:

	- Compile Project Requirements  (March 28th)

	- Refine Project Requirements  (April 4th)

	- Design Project (April 14th)

	- Construction Phase 1 (April 25th) 

	- Construction Phase 2 - Final Submission (May 2nd) - Self review checklist (Please use this before submission) 

	- Final Presentations (May 5th) 
		- Prepare a Poster Board 
	
	- Documentation on how Lessons Learned were applied. (May 6th)


### What is Footing?

The main function of Footing app is to report the traveler’s footprint with a photo and rewards them with medals. We also report the number of countries the users have been to, the total distance they fly and percentage of the world they have discovered and reward the users with medals.

[Have a look at our Poster](https://drive.google.com/file/d/0B-xpUAR5wa1RS3FoQmFtMzhuNHc/view?usp=sharing)

[Here is a live demo!](https://youtu.be/3Z8cXjntb8M)

### Basic Functionalities

1.  Support Map and GPS
2.  Write travel journals and upload photos
3.  Make statistical report of the travel information (e.g. number of countries they have visited, the total distance they fly)

### How to Run

- Front-end

	Run the android project on an android device which supports GPS connection (phone, tablet)
	
	Allow all permission requests.

- Back-end

	- Run as maven build..

	goal: package

	- Run Application.java as java app
	
	- Output

		If android device send some message to server, server will print it out on the console.
		
	- Notice

		Replace the url in UI_MainActivity at line 290 with you own pc’s inet 192.168.1.x or 10.0.0.x (get by cmd $ ifconfig on mac). Otherwise, server can’t get the message from android device.

### Contributions

|Xin|Qiaoyi|I-Huei|
|:--:|:--:|:--:|
|1. Entire Google Map Services; 2. Code for Switch Between Tab Fragments: Map, Journal List and Medal List; 3. Backend Server |1. Login/Sign up; 2. Create/Edit Journal; 3. User Drawer Menu and Statistics; 4. General UI Design/Setup |1. Medal/Jounal Model Design and implementation; 2. Database Connector for Medal and Journal; 3. Medal/Journal List UI|

### Documents

[User Story](https://docs.google.com/document/d/1SIY2ByAH0UOm7ixRBW3G_djz6XJipoiSV1H6E80ccVM/edit?usp=sharing)

[Use Cases](https://docs.google.com/document/d/1SF2PKdNr-WFIzyhL0_FjO9fpIIAnM9egRwUbHOyy-is/edit?usp=sharing)

[Page Flow Diagram](https://docs.google.com/document/d/1hDSBfRx1d5NJDMRlthSAy_yLlLDNuckEPCVn1sZDNNs/edit?usp=sharing) 

[Final Presentation](https://drive.google.com/file/d/0B-xpUAR5wa1RMldfenBlblR6LXM/view?usp=sharing)


---
Powered by team Puff