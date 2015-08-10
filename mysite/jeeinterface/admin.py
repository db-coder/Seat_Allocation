from django.contrib import admin
from jeeinterface.models import Course
from jeeinterface.models import UserProfile
# Register your models here.

class CourseAdmin(admin.ModelAdmin):
	list_display = ('code', 'college', 'name', 'crankgen')
	search_fields = ['name']
admin.site.register(Course, CourseAdmin)

class UserProfileAdmin(admin.ModelAdmin):
	list_display = ('user', 'General_Rank', 'id')
	search_fields = ['General_Rank']
admin.site.register(UserProfile, UserProfileAdmin)