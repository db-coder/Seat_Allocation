Group 22: Wizards
--------------------------------------------------------------------------------------------------------------------------------------------------------

	Members				|	Roll Number	|	Contribution
	----------------------------------------------------
	Utkarsh Kumar		|	130050022	|	100%
	Dibyendu Mondal		|	130050046	|	100%
	Sai Sandeep			|	130050052	|	100%


Honour Code:
--------------------------------------------------------------------------------------------------------------------------------------------------------
We hereby pledge on our honour that we have neither given nor received any unauthorised assistance on this project or any previous task.

Late Day Declaration:
We have used up the two remaining late days for this project.



Work Division:
--------------------------------------------------------------------------------------------------------------------------------------------------------
We would like to mention that saying that person X did a particular task means that he was the one to actually "code" that particular part after all of us had discussed and finalised that particular component, and often more than one of us ended up working on the same part at different points of time.


Utkarsh Kumar:
-> pdf to csv conversion
-> Django modules designing
-> Django static files designing
-> Made the Readme file

Dibyendu Mondal:
-> pdf to csv conversion
-> Django static files designing
-> Beamer Executive Presentation

Sandeep Reddy:
-> implementation of Merit List algorithm
-> implementation of Gale-Shapely 
-> Java Docs


System Requirements:
--------------------------------------------------------------------------------------------------------------------------------------------------------

-> Java 8 (easily available on net!)
-> Python 3.4 (comes by default on 13.04 and higher ubuntu distributions!)
-> Django 1.7 installed on python 3.4 (and not python 2.x): figuring out a way to install this took an entire day, in the end we used pip3 to accomplish it.
-> A web browser to run the web application

How to execute:
--------------------------------------------------------------------------------------------------------------------------------------------------------

###########################################################
			Seat Allocation Program (Lab 10)
###########################################################
-> In terminal, cd to the project root directory containing src.
It will have all the 9 files.
Now, run javac Main.java 
then java Main $ADDRESS OF DIRECTORY WHERE THE 3 FILES ARE SENT
Eg : java Main . (Assuming all 3 .csv files are in the current directory)
All the output files are generated.

###########################################################
			Course Eligibility Program (Lab 11)
###########################################################

-> In terminal 'cd' to the project root directory (the folder containing manage.py), and run "python3 manage.py runserver".
  You should get an output as:
	Performing system checks...

	System check identified no issues (0 silenced).
	October 31, 2014 - 16:23:39
	Django version 1.7.1, using settings 'mysite.settings'
	Starting development server at http://127.0.0.1:8000/
	Quit the server with CONTROL-C.

-> Once you have the server running, open your web browser and go to the address "http://127.0.0.1:8000/jeeinterface/".

-> If you have not logged in, you would be redirected to the login page, where you would either login using existing credentials or make a new acccount using the 'register' link there.

-> Once you have registered, your rank etc are stored in the server sqlite database and is used by the application to determine the courses that you are elegible for, which are then displayed once you log in.

-> Do not panic if you see only the college names (and no courses); you need to click the '+' (plus sign) next to the college to expand the list and see the courses you are elegible for in that college.


Task-wise details:
--------------------------------------------------------------------------------------------------------------------------------------------------------

												************************************************************
																			LAB 10
												************************************************************


1. GS Allocation :
	It is relatively simple to implement.
	But we had no clue how to handle foreign candidates.
	And then... it struck to us after reading one of the piazza posts that they can be handled easily after everything is done.
	MeritList class isn't needed.
	We stored the ranks in candidate class and used them.
	
2. MeritList Allocation :
	Checking it's correctness is simply too tough. You need to solve the test cases manually (which is a big pain!) and then check with the program output.
	
	Toughest part :
		Dereservation. It took quite a while for us to understand the dereservation part and decide how to implement it.
	
3. Info : 
	We have used hashmaps a lot.
	"Candidates" will map candidate id to the object candidate.
	"Programs" will map the program id to the corresponding object VirtualProgramme.
	
	We used two arraylists "CandidateIds" and "ProgramIds" to store the list of ids of students and programs.
	
	In GS, we stored the categories,
	1 : General, 2 : OBC 3 : SC, 4 : ST, 5 : GEPD, 6 : OBCPD, 7 : SCPD, 8 : STPD.
	We stored these categories in the Candidate and VirtualProgramme as well. It helped to reduce the code a lot.
	
	And then to compare based on two ranks, we used multiplying factor of 10^5, assuming all ranks will be under 10 ^5.


												************************************************************
																			LAB 11 
												************************************************************


1. pdf to csv conversion

	-> It took us a lot of time and hardwork (and frustation too!) to find the optimal way to parse the pdf file and get the details in csv format. We tried using pdfMiner, pdfTables, pdfpy, but all to no avail. Finally we came across pdftotext by import os to mine it.

	-> On running update.py on the provided pdf file "programmeCode.pdf", it converts it to "programmeCode.txt" using the raw flag (line 5), and then moves through the entire text making order out of seemingly infinite chaos. It creates an organised csv file "output.csv".
	We also had to specifically correct a typo: the Aerospace Engineering course of IIT Kharagpur has wrongly been coded as D5201 instead of G5201 (corrected at line 59 in update.py).

	-> Then we had to order the courses of "output.csv" on the basis of their codes. Even after lots of efforts, we could not sort them via a program, and in the end, we used the 'sort by a column' function available in LibreOffice Calc and saved the sorted file as sorted_list.csv.

	-> Thereafter we had to merge the two files, the rank-data and the course-data files. Since we had already spent too much time on the conversion part, we simply combined the two files by copy-pasting the "college" and "course-name" fields of "sorted_list.csv" to "closingRankData-2012.csv" and saved the file as "final_list.csv". This is the file to be used for populating the sqlite database of our application.

2. Populating the database

	-> For populating the sqlite database, we fired up the python shell in the django environment using "python3 manage.py shell" and ran the commands in populate_db in that shell (manually). Note that the file merely contains the commands that we used for that (csvreader commands), and is NOT to be executed as "python populate_db".

3. Web framework to display a list of elegible courses

	-> We began by making models for "courses" and "UserProfile" class which contained the attributes of the classes (name, id etc) along with their "types" (models.py).

	-> Then we added our django app "jeeinterface" in the mysite/urls.py file to create urls such as "jeeinterface/login". These further urls were added in the jeeinterface/urls.py file along with the actions they triggered. These actions are defined in "jeeinterface/views.py".

	-> The index function defined in views.py "gets" a list of all the courses from the sqlite database, and then filters it out to get only the courses the student was elegible for. By comparing the "college" attribute, we sorted these courses to generate the lists for different IITs (like iitb, iitkgp etc). These lists were passed to the templates/jeeinterface/index.html via dictionary mechanism by mapping the lists to the corresponding variables.

	-> The user register-login system is implemented using the built-in django-forms (which is almost too easy in front of php! :) ) and the built-in authentication system. Once a user registers using the UserForm and UserProfileForm (defined in jeeinterface/forms.py), his details are stored in the database.

	-> When one tries to open "/jeeinterface/" while one is not logged in, one would be redirected to the "/jeeinterface/login", where he has the option to either login using his existing credentials or register himself. Its the rank of the logged user that is used to find the courses one is elegible for.

	-> The courses that the user is elegible for, are displayed via college-wise collapsable lists, implemented using css and javascript (see "/static/css" and "/static/js").
