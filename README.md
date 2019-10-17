# TP1 IHM INFO5

## Structure du projet

Nous avons un package nommé mvc contenant 6 classes, trois classes Model, View et Controller qui permettent de créer et gérer le Range Slider, deux classes Home et HomeFinder permettant de gérer la carte qui affiche les maisons, et enfin une classe Main permettant de créer la fenêtre où l'on va afficher les Range Sliders et la carte.

## Explications

Lorsque nous allons lancer le main de la class Main, nous allons exécuter la fonction affichage qui va créer une nouvelle fenêtre. Cette fenêtre va contenir deux JPanel, un JPanel pour les Range Sliders et un autre pour afficher la carte des biens immobiliers.

Pour le premier JPanel nous créons deux instances de la classe View qui va elle même créer une instance de la classe Model et une autre de la classe Controller afin d'obtenir un range slider fonctionnel. Cela nous donne donc deux range slider correspondant aux deux critères de recherche : le prix et le nombre de chambres. Ces sliders vont permettre de filtrer les biens immobiliers à afficher sur la carte située dans le second JPanel. Pour ce-dernier, nous créons une instance de la classe HomeFinder qui va générer aléatoirement des biens immobiliers avec des prix et un nombre de chambres aléatoires avant de les afficher sur une carte. Les biens immobiliers sont donc représentés par un cercle sur la carte. Lorsqu'on affine les critères de recherche grâce aux sliders, nous allons effacer la carte et la dessiner de nouveau avec les biens correspondants aux cirtères.

## Utilisation

Pour lancer l'application il suffit de lancer le main de la classe Main.

Pour changer les valeurs minimales et maximales des sliders il faut regarder dans la fonction affichage de la classe Main lorsque nous créons une nouvelle instance de View nous passons en paramètre la valeur minimale et maximale du slider à créer. De même pour le nombre de maisons générées aléatoirement, il suffit de modifier le troisième paramètre passé lors de la création d'une instance de HomeFinder.

Une fois ces données choisies et l'application lancée, l'utilisateur voit la fenêtre apparaître avec à gauche la carte comportant des cercles représentant les biens immobiliers et à gauche les deux sliders pour affiner sa recherche. Il peut déplacer les deux boutons de chaque slider pour change la plage de valeur pour les deux critères présents de deux manières. Soit en cliquant sur un des deux boutons et en le déplaçant. Soit en cliquant directement à l'endroit où il veut déplacer le bouton. A savoir que le bouton se situant à la distance la plus proche de l'endroit cliqué sera déplacé.
