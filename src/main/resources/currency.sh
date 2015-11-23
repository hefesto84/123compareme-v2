#!/bin/bash
CURRENTIME=$(date +%Y"-"%m"-"%d)
wget -O /tmp/eurofxref-daily.xml http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml
TIME=$(cat /tmp/eurofxref-daily.xml | grep "time" | awk -F "'" '{print $2}')

echo $CURRENTIME
echo $TIME

if [ $CURRENTIME != $TIME  ]; then

echo "Need update"
cp /tmp/eurofxref-daily.xml /var/lib/tomcat7/webapps/123CompareMe-v2/WEB-INF/classes/eurofxref-daily.xml

else

echo "Updated"

fi

