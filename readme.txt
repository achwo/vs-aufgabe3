--------------------
Compilieren der Dateien:
--------------------
Zu dem Paket gehören die Packages
bank_access
cash_access
mware_lib
name_service

sowie:
readme.txt;

Logs:
bank.Bank.log
filiale.Filiale.log
geldautomat.Geldautomat.log

--------------------
Starten der Anwendungen
--------------------

1. Nameservice starten (NameService.main(), Parameter: Port (optional, Standardport: 15000))
2. Bank starten (bank.jar, Parameter: NameServiceHost, NameServicePort, Bankname)
3. Filiale starten (filiale.jar, Parameter: NameServiceHost, NameServicePort, Bankname, Filialname)
4. Geldautomat starten (geldautomat.jar, Parameter: NameServiceHost, NameServicePort, Bankname-Filialname)