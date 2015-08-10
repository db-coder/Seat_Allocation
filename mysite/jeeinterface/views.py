from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect
from jeeinterface.models import Course
from jeeinterface.forms import UserForm, UserProfileForm
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from jeeinterface.models import UserProfile

# Create your views here.

def index(request):
	if request.user.is_authenticated():
		course_list = Course.objects.order_by('college')
		user_id = request.user.id
		
		user_list = UserProfile.objects.order_by('General_Rank')
		user = 0
		for u in user_list:
			if u.id == user_id-1:
				user = u
		
		context_dict = {
			'iitb': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Bombay', course_list),
			'iitk': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Kanpur', course_list),
			'iitd': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Delhi', course_list),
			'iitkgp': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Kharagpur', course_list),
			'iitg': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Guwahati', course_list),
			'iitr': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Roorkee', course_list),
			'iitm': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Madras', course_list),
			'iitmnd': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Mandi', course_list),
			'iitgn': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Gandhinagar', course_list),
			'iitbbs': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Bhubaneswar', course_list),
			'iitj': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Rajasthan', course_list),
			'iitp': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Patna', course_list),
			'iith': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Hyderabad', course_list),
			'iiti': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Indore', course_list),
			'ismd': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian School of Mines Dhanbad', course_list),
			'iitrpr': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Indian Institute of Technology Ropar', course_list),
			'iitbhu': filter(lambda c: (c.crankgen > user.General_Rank or (user.OBC_Rank!=0 and c.crankobc > user.OBC_Rank) or (user.SC_Rank!=0 and c.cranksc > user.SC_Rank) or (user.ST_Rank!=0 and c.crankst > user.ST_Rank) or (user.General_PD_Rank!=0 and c.crankgenpd > user.General_PD_Rank) or (user.OBC_PD_Rank!=0 and c.crankobcpd > user.OBC_PD_Rank) or (user.SC_PD_Rank!=0 and c.crankscpd > user.SC_PD_Rank) or (user.ST_PD_Rank!=0 and c.crankstpd > user.ST_PD_Rank)) and c.college == 'Institute of Technology Banaras Hindu University Varanasi', course_list),
		}
		
		return render(request, 'jeeinterface/index.html', context_dict)

	else:
		return HttpResponseRedirect('/jeeinterface/login')

def register(request):

	# A boolean value for telling the template whether the registration was successful.
	# Set to False initially. Code changes value to True when registration succeeds.
	registered = False

	# If it's a HTTP POST, we're interested in processing form data.
	if request.method == 'POST':
		# Attempt to grab information from the raw form information.
		# Note that we need to use both the UserForm and the UserProfileForm
		user_form = UserForm(data=request.POST)
		profile_form = UserProfileForm(data=request.POST)

		# If the two forms are valid...
		if user_form.is_valid() and profile_form.is_valid():
			# Save the user's form data to the database.
			user = user_form.save()

			user.set_password(user.password)
			user.save()

			# Since we need to set the user attribute ourselves, we set commit=False, until we're ready to avoid integrity problems.
			profile = profile_form.save(commit=False)
			profile.user = user
			profile.save()

			registered = True

	else:
		user_form = UserForm()
		profile_form = UserProfileForm()

	return render(request,
			'jeeinterface/register.html',
			{'user_form': user_form, 'profile_form': profile_form, 'registered': registered} )

def user_login(request):
	
	if request.method == 'POST':
		# Gather the username and password provided by the user.
		username = request.POST['username']
		password = request.POST['password']

		#Authenticating the user
		user = authenticate(username=username, password=password)

		if user:
			if user.is_active:
				login(request, user)
				return HttpResponseRedirect('/jeeinterface/')
			else:
				return HttpResponse('Your account is disabled.')
		else:
			print ('Invalid login details: {0}, {1}'.format(username, password))
			return HttpResponse('Invalid login details supplied.')

	else:
		return render(request, 'jeeinterface/login.html', {})

@login_required
def user_logout(request):
	logout(request)

	# Redirect to the homepage.
	return HttpResponseRedirect('/jeeinterface/')
