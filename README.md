# Predict-IF
Appli web pour entreprise de voyance

## Le Projet

Une appli web développée dans le cadre d'un projet de 3IF à l'Insa Lyon, pour le compte d'une entreprise de voyance fictive, comprenant backend et frontend.
Il a pour but d'être un exercice pratique de conception de A à Z d'une appli, et est donc un travail de Génie Logiciel ainsi que d'implémentation.
Le projet a été implémenté sous NetBeans. L'arborescence de fichiers correspond ainsi à une utilisation sous NetBeans.

## Dossier de conception

[Le livrable](dossierConcep.pdf) contenant modèle du domaine, maquettes IHM, tableau ICARS, diagramme de navigation des pages.

## Structure
Predict'IF implémente une architecture en couches : 
Présentation <-> Application <-> Métier <-> Persistance

- [Backend de l'appli](TP_DASI/) : contient les couches Application, Métier et Persistance. Implémente un pattern MVC traitant les requêtes envoyées par la partie frontend en navigateur et faisant la liaison avec les couches inférieures.
- [Frontend de l'appli](TP_DASI_Web/TP_DASI_Web/) : Contient la couche Présentation. Implémente les squelettes de pages HTML, ainsi que le code Javascript permettant de naviguer entre les pages et effectuer des appels au serveur backend.

## Auteurs

Emma Neiss & Yann Dupont, 2020.
