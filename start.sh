#!/bin/bash

CLUSTER_IP=<code>/sbin/ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1}'</code> /opt/app/bin/clustering $@