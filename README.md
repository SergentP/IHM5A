# TP3 IHM INFO5 - Schanen Loïc - Pelisson Antoine

## Fonctions implémentées

* Translation
<br/> Toucher une image ou une vidéo et déplacer son doigt permet de déplacer l'image. Lâcher l'image la bloque dans sa nouvelle position.

* Rotozoom
<br/> Poser un deuxième doigt sur une image/vidéo en translation permet de passer en mode rotozoom. Dans ce mode, écarter les doigt zoome sur l'image/vidéo, les resserer dézoome l'image/vidéo, et effectuer une rotation des doigts permet de tourner l'image.
<br/> Il est possible de faire tourner l'image autour des deux doigts mais il est conseillé de bouger un seul doigt à la fois. Dans le cas contraire, l'image ne bougera qu'en suivant le premier doigt qui bouge et s'actualisera sur l'emplacement du deuxième doigt dès que le premier arrêtera de bouger.
<br/> Ajouter un(des) doigt(s) sur une image/vidéo en mode rotozoom n'a aucun effet.

* Autre
<br/> Il est également possible de déplacer/rotozoomer plusieurs images/vidéos simultanément en considérant que l'utilisateur à la coordination nécessaire ou qu'il y a plusieurs utilisateurs.

## Limites
* Si l'utilisateur reste appuyé sur une image/vidéo assez longtemps pour déclencher un "clic droit (version smartphone)", le premier pointeur est enregistré et quand l'utilisateur touche l'image/vidéo concernée, celle-ci entre en mode rotozoom et tourne autour du point précédent.
* Pour que le toucher sur une vidéo soit pris en compte, il faut bien toucher la vidéo sur une zone autre que le bouton play ou la barre de lecture.
* Il n'y a qu'une personne qui a pu push sur le projet car l'ordinateur de l'autre personne n'arrivait pas à se connecter au partage de données et ne pouvait donc pas réaliser de tests. Toute la programmation a donc été faite sur un seul ordinateur.
