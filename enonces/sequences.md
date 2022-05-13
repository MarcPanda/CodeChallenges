# Séquences de 0 et 1

On vous donne une séquence, sous la forme d'une chaîne de caractères comportant uniquement les caractères "0", "1" et "?". Supposons qu'il y ait k "?". Il existe alors 2^k façons de remplacer chaque "?" par un "0" ou un "1", ce qui donne 2^k séquences 0-1 différentes (les séquences 0-1 sont des séquences ne contenant que des zéros et des uns).

Pour chaque séquence 0-1, définissez son nombre d'inversions comme le nombre minimum de permutations adjacentes nécessaires pour trier la séquence dans un ordre croissant. Dans ce problème, la séquence est triée en ordre croissant précisément lorsque tous les zéros apparaissent avant tous les uns. Par exemple, la séquence 11010 a 5 inversions. On peut la trier par les mouvements suivants :
11010 → 11001 → 10101 → 01101 → 01011 → 00111.

Trouver la somme du nombre d'inversions des 2^k séquences, modulo 1000000007 (10^9 + 7).

## Entrée du programme

L’entrée consiste en une unique ligne contenant les caractères "0", "1" et "?", d’une taille entre 1 et 500 000 caractères (inclus).

## Sortie attendue

Nombre entier indiquant la somme d'inversions des 2^k séquences, modulo 1000000007 (10^9 + 7).


## Source
https://open.kattis.com/problems/sequences
