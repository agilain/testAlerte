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

for  MOT in `java -jar $1 pourcen/test/04.04m.png`
do
    if [ "$MOT" = "rmalade" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/04.04s.png`
do
    if [ "$MOT" = "rsain" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/05.04s.png`
do
    if [ "$MOT" = "rsain" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/06.04m.png`
do
    if [ "$MOT" = "rmalade" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/07.04s.png`
do
    if [ "$MOT" = "rsain" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/07.04s.png`
do
    if [ "$MOT" = "rsain" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/08.04m.png`
do
    if [ "$MOT" = "rmalade" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/08.04s.png`
do
    if [ "$MOT" = "rsain" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/09.04m.png`
do
    if [ "$MOT" = "rmalade" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/09.04s.png`
do
    if [ "$MOT" = "rsain" ]
then
REUSSITE=$(($REUSSITE+1))

else 
ECHEC=$(($ECHEC+1))

fi

done 

for  MOT in `java -jar $1 pourcen/test/10.04m.png`
do
    if [ "$MOT" = "rmalade" ]
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
