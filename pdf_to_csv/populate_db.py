import csv
from jeeinterface.models import Course
# If you're using different field names, change this list accordingly.
# The order must also match the column order in the CSV file.

fields = ['college', 'code', 'name', 'orankgen', 'crankgen', 'oranksc', 'cranksc', 'orankst', 'crankst', 'orankgenpd', 'crankgenpd', 'orankscpd', 'crankscpd', 'orankstpd', 'crankstpd']
for row in csv.reader(open('output.csv')):
    Course.objects.get_or_create(**dict(zip(fields, row)))