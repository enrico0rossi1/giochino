versione aggiornata al 26/04/2024 \n

NOVITà

aggiunta di mappa DarkWoods da modificare e implementare

aggiunta clsse per gestire eventi come trappole,recupero vita e teletrasporto chiamata EventHandler

cambiato colore dello sfondo in verde per ridurre l'effetto sfarfallio

aggiunta classe ObjHeart

aggiunto attributo vita e vitaMax per entità e giocatore

aggiunto indicatore di vita con cuori in alto a sinistra che si aggiorna con la variazione della vita del giocatore

aggiunto il menù principale

ridenominazione della calsse AssetSetter in ObjectPlacer per eliminare ambiguità e rendere il nome più esplicativo \n

ingrandimento dell' arrray degli oggetti e aggiunta di oggetti nel momdo di gioco \n

aggiunta di interfaccia rudimentale con contatore chiavi e notifiche diverse alla collisione con vari oggetti

ora per correre è necessario raccogliere prima la moneta dorata e poi si ptrà correre premendo il tasto O \n

aggiunta di musica ed effetti sonori (una canzone durante il gioco e un effetto sonoro alla raccolta di ogggetti o apertura porte)

la collisione con l'oggetto BigTreasure causa la fine del gioco

aggiunto un menù di pausa da attivare e disattivare col tasto "m"

PROBLEMI da risolvere notati finora

l'animazione dell'attacco causa un blocco temporaneo delle animazioni forse a causa el fatto che sono 4 immagini mentre il ciclo di animazione ne ha 6

l'animazione dell'attacco prosegue solo fintanto che il tasto P è premuto e non prosegue fino al termine naturale in caso di rilascio del pulsante

mentre il gioco è in pausa il personaggio viene animato quando si tenta di farlo muovere nella direzione a cui punta o attaccare, si potrebbe ignorare il problema e coprire il personaggio con un'interfaccia

tenere premuto il tasto m attiva e disattiva ripetutamente il menù di pausa
