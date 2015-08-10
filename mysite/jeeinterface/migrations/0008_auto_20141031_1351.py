# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('jeeinterface', '0007_auto_20141031_1336'),
    ]

    operations = [
        migrations.RenameField(
            model_name='userprofile',
            old_name='genpdrank',
            new_name='General_PD_Rank',
        ),
        migrations.RenameField(
            model_name='userprofile',
            old_name='genrank',
            new_name='General_Rank',
        ),
        migrations.RenameField(
            model_name='userprofile',
            old_name='obcpdrank',
            new_name='OBC_PD_Rank',
        ),
        migrations.RenameField(
            model_name='userprofile',
            old_name='obcrank',
            new_name='OBC_Rank',
        ),
        migrations.RenameField(
            model_name='userprofile',
            old_name='scpdrank',
            new_name='SC_PD_Rank',
        ),
        migrations.RenameField(
            model_name='userprofile',
            old_name='scrank',
            new_name='SC_Rank',
        ),
        migrations.RenameField(
            model_name='userprofile',
            old_name='stpdrank',
            new_name='ST_PD_Rank',
        ),
        migrations.RenameField(
            model_name='userprofile',
            old_name='strank',
            new_name='ST_Rank',
        ),
    ]
