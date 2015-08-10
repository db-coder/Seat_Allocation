from django import forms
from django.contrib.auth.models import User
from jeeinterface.models import Course, UserProfile

class UserForm(forms.ModelForm):
	password = forms.CharField(widget=forms.PasswordInput())

	class Meta:
		model = User
		fields = ('username', 'email', 'password')

class UserProfileForm(forms.ModelForm):
	class Meta:
		model = UserProfile
		fields = ('General_Rank', 'OBC_Rank', 'SC_Rank', 'ST_Rank', 'General_PD_Rank', 'OBC_PD_Rank', 'SC_PD_Rank', 'ST_PD_Rank')

class PrefForm(forms.ModelForm):
	class Meta:
		model = UserProfile
		fields = '__all__'
		