# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('jeeinterface', '0002_auto_20141028_1900'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='college',
            name='course',
        ),
        migrations.DeleteModel(
            name='College',
        ),
        migrations.RemoveField(
            model_name='course',
            name='category',
        ),
        migrations.DeleteModel(
            name='Category',
        ),
        migrations.AddField(
            model_name='course',
            name='college',
            field=models.CharField(default='IIT Bombay', max_length=200),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='crank',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='orank',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
    ]
