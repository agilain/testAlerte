#!/bin/bash
REUSSITE=0
ECHEC=0

for  MOT in `java -jar $1 pourcen/test/03.04m.jpg`
do
    if [ "$MOT" = "rmalade" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/03.04s.jpg`
do
    if [ "$MOT" = "rsain" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/10.04s.png`
do
    if [ "$MOT" = "rsain" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

echo "REUSSITE = $REUSSITE"
