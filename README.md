VS-2013-WS-A3-LMJM
==================

Eine einfache OO-Middleware um Methodenaufrufe auf entfernten Objekten zu ermöglichen.

Zum Testen der Middleware liegt eine Testapplikation von Hartmut Schulz bei.
Zum starten existieren 3 start-skripte, die die Applikation in 3 Prozessen starten. Zusätzlich
wird die JVM mit Debugging Server gestartet, sodass aus der IDE der Debugger an den Prozess angehängt
werden kann.

Für IntelliJ:
Run -> Edit Configurations -> Add New -> Remote
Nun sollte die Maske bereits ausgefüllt sein - normalerweise für Localhost:5005
(-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005)

Für die verschiedenen Prozesse müssen nun nur die Ports angepasst werden und die jeweilige Konfiguration
speichern.

Für Bank Prozess Port 5005
Für Filiale Prozess Port 5006
Für Automaten Prozess Port 5007

Um das ganze zu starten und zu debuggen, muss zu erst GlobalNameService aus dem Packcage name_service gestartet
werden.
Danach können die Start-Skripte in out/production/VS-2013-WS-A3-LMJM verwendet werden.
In der IDE kann nun für jeden Prozess der Debugger gestartet werden.

(Mehr Infos und Bilderchen unter http://www.javaranch.com/journal/200408/DebuggingServer-sideCode.html)
