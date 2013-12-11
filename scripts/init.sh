#!/bin/sh

rm -rf ../run
mkdir ../run

mkdir ../run/bank
mkdir ../run/filiale
mkdir ../run/automat

bank_folder=./../out/production/VS-2013-WS-A3-LMJM/bank_access
acc_folder=./../out/production/VS-2013-WS-A3-LMJM/cash_access
mware_folder=./../out/production/VS-2013-WS-A3-LMJM/mware_lib

cp -r $bank_folder ./../run/bank/
cp -r $mware_folder ./../run/bank/

cp -r $acc_folder ./../run/filiale/
cp -r $bank_folder ./../run/filiale/
cp -r $mware_folder ./../run/filiale/

cp -r $acc_folder ./../run/automat/
cp -r $mware_folder ./../run/automat/

cp ./bank.jar ./../run/bank/
cp ./filiale.jar ./../run/filiale/
cp ./geldautomat.jar ./../run/automat/
