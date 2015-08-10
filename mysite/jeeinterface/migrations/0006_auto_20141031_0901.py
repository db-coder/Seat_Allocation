# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('jeeinterface', '0005_userprofile_preference'),
    ]

    operations = [
        migrations.RenameField(
            model_name='course',
            old_name='crank',
            new_name='crankgen',
        ),
        migrations.RenameField(
            model_name='course',
            old_name='orank',
            new_name='orankgen',
        ),
        migrations.AddField(
            model_name='course',
            name='crankgenpd',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='crankobc',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='crankobcpd',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='cranksc',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='crankscpd',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='crankst',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='crankstpd',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='orankgenpd',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='orankobc',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='orankobcpd',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='oranksc',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='orankscpd',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='orankst',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='course',
            name='orankstpd',
            field=models.IntegerField(default=0),
            preserve_default=True,
        ),
    ]
