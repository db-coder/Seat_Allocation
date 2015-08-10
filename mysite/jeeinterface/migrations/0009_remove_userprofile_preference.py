# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('jeeinterface', '0008_auto_20141031_1351'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='userprofile',
            name='preference',
        ),
    ]
