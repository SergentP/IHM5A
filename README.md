# TP1 IHM INFO5

## Structure du projet

Nous avons un package nommé mvc contenant 6 classes, trois classes Model, View et Controller qui permettent de créer et gérer le Range Slider, deux classes Home et HomeFinder permettant de gérer la carte qui affiche les maisons, et enfin une classe Main permettant de créer la fenêtre où l'on va afficher les Range Sliders et la carte.

## Explications

Lorsque nous allons lancer le main de la class Main, nous allons exécuter la fonction affichage qui va créer une nouvelle fenêtre. Cette fenêtre va contenir deux JPanel, un JPanel pour les Range Sliders et un autre pour afficher la carte des biens immobiliers.

Pour le premier JPanel nous créons deux instances de la classe View qui va elle même créer une instance de la classe Model et une autre de la classe Controller afin d'obtenir un range slider fonctionnel. Cela nous donne donc deux range slider correspondant aux deux critères de recherches : le prix et le nombre de pièces.
