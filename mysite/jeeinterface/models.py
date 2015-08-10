import datetime
from django.db import models
from django.utils import timezone
from django.contrib.auth.models import User

# Create your models here.
class Course(models.Model):
	college = models.CharField(max_length=200, default="IIT Bombay")
	code = models.CharField(max_length=10)
	name = models.CharField(max_length=200)
	orankgen = models.IntegerField(default=0)
	crankgen = models.IntegerField(default=0)
	orankobc = models.IntegerField(default=0)
	crankobc = models.IntegerField(default=0)
	oranksc = models.IntegerField(default=0)
	cranksc = models.IntegerField(default=0)
	orankst = models.IntegerField(default=0)
	crankst = models.IntegerField(default=0)
	orankgenpd = models.IntegerField(default=0)
	crankgenpd = models.IntegerField(default=0)
	orankobcpd = models.IntegerField(default=0)
	crankobcpd = models.IntegerField(default=0)
	orankscpd = models.IntegerField(default=0)
	crankscpd = models.IntegerField(default=0)
	orankstpd = models.IntegerField(default=0)
	crankstpd = models.IntegerField(default=0)
	
	def __str__(self):
		return self.name

class UserProfile(models.Model):
	user = models.OneToOneField(User)

	# The User model comes complete with the username, password, email address, first name and surname attributes.
	General_Rank = models.IntegerField(default=0)
	OBC_Rank = models.IntegerField(default=0)
	SC_Rank = models.IntegerField(default=0)
	ST_Rank = models.IntegerField(default=0)
	General_PD_Rank = models.IntegerField(default=0)
	OBC_PD_Rank = models.IntegerField(default=0)
	SC_PD_Rank = models.IntegerField(default=0)
	ST_PD_Rank = models.IntegerField(default=0)
	
	def __str__(self):
		return self.user.username