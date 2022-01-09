# TreeManager

## Prérequis
* Une base de données locale MariaDB

## Configuration
* Dans la BD, lancer le script TreeManager.sql (resources/bd_creation_script/TreeManager.sql)
* Dans le code source, changer la configuration afin de pointer vers la base de données locale dans Main.java (src/main/java/treeManager/Main.java)

## Execution
Dans une interface de ligne de commande (Invite de commandes Windows):

Ligne rapide : ``treeManager.jar /data.csv 127.0.0.1 root <MDP>``

Syntaxe : ``<chemin du jar> <chemin du fichier csv> <IP de la BD> <nom de l'utilisateur de la BD> <Mot de passe de la BD>``