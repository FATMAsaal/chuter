# Le Chutteur : Projet avec Raspberry PI3

18/03/2020



****
## Présentation du Chutteur:
Le Chutteur est un objet connecté dont le nom est dérivé du mot 'CHUT!', ce qui justifie la fonctionnalité primaire de ce dispositif qui est de détecter si un bruit est trop fort dans la pièce où il se trouve.
Il permet également de collecter des données supplémentaires sur l'environnement de cette pièce, notamment des données sur la température, l'humidité et l'intensité de la lumière.
****
## Mode d'emploi:
1. Le Chutteur fonctionne sous le système d'exploitation Linux (Raspbian -- une version de Debian destinée au Raspberry Pi). Ce dernier peut-être installé à partir du bootable Noobs qui se trouve sur une carte sd.
2. Pour pouvoir assurer un bon fonctionnement du Chutteur, il faudrait effectuer un branchement correct des capteurs sur le Raspberry, et ceci grâce au GrovePi+ qui permet de relier ces deux derniers.
3. Le GrovePi+ est relié au Raspberry grâce aux pins GPIO.
4. Les capteurs doivent, à leur tour, être reliés grâce à des fils au GrovePi+ sur les ports suivants :
    * Port A0 : Capteur de lumière (Light snesor v1.1)
    * Port A1 : Capteur de son (Sound sensor v1.6)
    * Port D3 : LED (LED Socket Kit v1.5)
    * Port D4 : Bouton (Button v1.2)
    * Port D7 : Buzzer (Grove Buzzer v1.2)
    * Port D8 : Capteur de température et d'humidité (Grove Temperature&Humidity Sensor v1.2)
    * Port I2C-2 : Ecran LCD RGB Backlight (Grove-LCD RGB Backlight v4.0)
5. Installer la bibliothèque Dexter pour Python, afin de pouvoir importer les fonctions nécessaires pour manipuler les capteurs. L'installation se fait grâce à la commande "curl -kL dexterindustries.com/update_grovepi | bash". (N.B. L'installation a déjà été faite pour sur ce dispositif)
6. Exécuter le script du chutteur sur le terminal via la commande "sudo python3 scriptChutteur.py"
****
## Lors de l'exécution:
Lorsque le script est exécuté, un message d'accueil sur un fond jaune s'affiche pendant quelques secondes sur l'écran.
Ensuite, un écran vert apparaît en affichant la date et l'heure actuelles.
Dans le cas où il y a un bruit fort, le buzzer se déclanche brièvement ey l'écran devient rouge en affichant le message "CHUT!". Après quelques secondes, l'écran reprend son affichage initial de la date et l'heure sur un fond vert.
Dans l'autre cas où l'on clique sur le bouton un voyant LED indique qu'on a bien cliqué sur le bouton, et un écran jaune s'affiche pour quelques secondes avec des données concernant la température, l'humidité et la lumière recueillies au moment du clique du bouton. Puis l'affichage initial réapparaît.

## Source des scripts utilisés:
https://github.com/DexterInd/GrovePi/tree/master/Software/Python
****
## La base de données:
  - le SGBD utilisé est la bibliothèque Sqlite3
  - le langage de programmation utilisé est le JAVA
  - pour les tables :
    * table user : pour ajouter des utilisateur de l'application .
    * table Temperature : pour enregistrer la temperature(en degre celcius et en fahrenheit) de la salle la où se trouve le chucher ainsi que l'humidité .
    * table Led : pour enregistrer l'etat du led utilisé pour le chucher dont la valeur est booléene .
    * table Son : enregistrer la valeur du son capté .
    * table Lumiere : enregistrer l'intensité de la lumière captée .
### Schéma de la base de donnée :
![Drag Racing](diagramme.png)
****
## L'application
> **NB:** *L'application n'est pas entiérement terminée*

<p>L'application, écrite à l'aide de Java et sa librairie Swing, récupére les données enregistrées par le Chutteur et les affiche sous forme de graphes. L'utilisateur peut choisir les données à afficher. Ainsi, il lui sera facile de comprendre l'ambiance de sa classe selon l'heure, le temps, la luminosité...
# chuter


### Success story: one particularly successful use of the software 


    + Since the confinement, the number of users of video conferencing tools has exploded, especially Jisti.
    + This software can be used without creating an account, it also offers the possibility of exchanging professional documents and videos .
    + So jitsi is starting to become popular and interesting, it was proposed during a meeting with the CNED(National Distance Learning Center) as a solution for distance learning (for videoconference calls) in national education.
    + For the security side, Jitsi is recommended by both Edward Snowden and cybersecurity expert Bruce Schneier.
    
