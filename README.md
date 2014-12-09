vs-aufgabe3
===========

Abweichungen vom Entwurf:
1. Delimiter in ObjektReferenz sind nun ':'.
Das war notwendig, da ObjektReferenzen als Parameter vorkommen koennen.


Ausfuehrung
===========

Nameservice: 
Bank: java -cp .:bank.jar bank.Bank <ns­host> <ns­port> <bank­name>
Filiale: java -cp .:filiale.jar filiale.Filiale <ns­host> <ns­port> <bank­name> <filial­Nr.>
Geldautomat: java -cp .:geldautomat.jar geldautomat.Geldautomat <ns­host> <ns­port> <filiale>