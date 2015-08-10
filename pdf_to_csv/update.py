import os
import io
import csv

os.system("pdftotext -enc UTF-8 -raw -nopgbrk programmeCode.pdf programmeCode.txt")

output_file = open('output.csv','w',newline='')

data = csv.writer(output_file)
insti=""
course=""
code=""
list1={'1','2','3','4','5','6','7','8','9','0'}
flag=0
with open("programmeCode.txt", "r", encoding="utf-8") as f:
	for read_data in f.readlines():
		read_data=read_data[:len(read_data)-1]

		if read_data=='101' or read_data=='102' or read_data=='103':
			continue 
		elif read_data[:6]=='Course' or read_data[:4]=='Code' or read_data[:5]=='Table':
			continue
		elif read_data=='Varanasi':
			#already added in the last iteration
			continue
		elif read_data[:9]=='Institute':
			#since 'varanasi' occurs on a separate line
			insti=read_data+' Varanasi'
			continue
		elif read_data[:6]=='Indian':
			#store the line in insti and then take all its courses as inputs
			insti=read_data
			continue
		elif read_data[:6]=='Serial':
			#comes after all the courses have been iterated
			break
		if len(read_data)==5 and (read_data[1:2] in list1):
			#if the line contains just the course code
			code=read_data
		elif len(read_data)>5 and flag==1:
			#if the name of the course is too long and hence has been broken down into two lines, this being the second line (checked by the flag)
			course=course+read_data
			flag=0
			continue
		elif (read_data[len(read_data)-2:len(read_data)-1] in list1) and len(read_data)>5:
			#if the course name and the course code occur on the same line, separate and store the two accordingly
			code=read_data[len(read_data)-5:len(read_data)]
			course=read_data[:len(read_data)-6]
		else:
			#if the line only contains the first line a course with too long a name to fit on a single line. That the name is incomplete is marked by changing the flag value
			flag=1
			course=read_data
			continue
		for i in range(3):
			#removing the serial numbers in front of the course names
			if course[:1] in list1:
				course=course[1:]
		course=course[1:]		#removing the space after the serial number
		if code == "D5201":		#typo correction
			code="G5201"
		data.writerow([insti,course,code])
output_file.close()
f.close()