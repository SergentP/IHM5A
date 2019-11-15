# TP1 IHM INFO5

## Structure du projet

Nous avons un package nommé src contenant 10 classes, deux classes DrawingTool et ColorTool qui héritent de la classe Tool et permettent de gérer les outils de couleur et de dessin, une classe Canvas qui gère la fenêtre de dessin, une classe ColoredShape qui sert à définir et stocker les formes dessinées, une classe MenuItem qui crée les boutons pour le menu, trois classes : Model, View et Controller qui constitue le modèle MVC du menu, et un Main qui crée la fenêtre et initialise tout le nécessaire pour le bon fonctionnement du programme.

## Explications

Lorsque nous lançons le main de la class Main, nous invoquons le Runnable run qui crée un nouveau Main pour créer une nouvelle fenêtre. Cette fenêtre va contenir un Canvas et une ToolBar avec un bouton pour activer le "mode expert".

Le Canvas créé prend en paramètre un vecteur de ColoredShape qui va stocker les formes dessinées. Ce canvas initialise un Controller pour les menus et trois View pour le menu circulaire de sélection entre dessin et couleur, le menu de sélection des outils de dessin et le menu de sélection des couleurs. Les DrawingTools créé dispose d'un listener qui permet de dessiner les formes correspondantes sur le Canvas en pressant le clic gauche de la souris et en la déplaçant. Le listener du canvas permet d'ouvrir le menu circulaire avec un clic droit. Pour ce faire, on appelle la fonction printmenu() du View menu qui va ajouter chaque bouton du menu sur le canvas aux positions d'un menu circulaire renseignées par coordonnées polaires. Un clic sur un bouton du premier menu appele les fonctions clearmenu(), pour effacer le premier menu et printmenu(), pour afficher le menu sélectionné. Un clic sur ce deuxième menu appelle clearmenu() pour l'effacer et affecte les changements.

## Utilisation

Par défaut, aucun outil de dessin n'est actif. L'utilisateur doit donc choisir un outil avant de pouvoir dessiner en noir par défaut . S'il désire changer d'outil ou de couleur, il peut le faire en ouvrant le menu circulaire avec un clic droit de la souris ou en choisissant le "mode expert" (en cliquant sur le bouton correspondant de la barre de tâche en haut) qui fonctionne comme un marking menu.
Après avoir ouvert le premier menu, il suffit de cliquer sur un bouton de ce dernier, ce qui le fermera et ouvrira un deuxième menu (soit outils de dessin, soit couleur). En cliquant sur un bouton de ce deuxième menu, il disparait et le changement d'outil ou de couleur est appliqué.
Le "mode expert" permet à l'utilisateur de maintenir le clic droit de sa souris pour passer en marking menu invisible et choisir l'item désiré en glissant la souris vers la position du premier bouton puis du deuxième (le chemin doit être mémorisé).

Pour lancer l'application il suffit de lancer le main de la classe Main.
