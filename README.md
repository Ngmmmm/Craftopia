# Projet POO L2 - Craftopia

## Nom
**Craftopia**

## Description
Craftopia est un jeu où le but est de récolter un maximum de ressources afin de les revendre et d'obtenir le plus d'argent possible.

## Visuels
![Aperçu du jeu](https://moule.informatique.u-paris.fr/houat/projet-poo-l2-ouahid-houat-noa-gregoire-mellier-craftopia/-/raw/main/img/ScreenJeu.png)

## Installation
1. Assurez-vous d'avoir une version de **Java 8** ou plus récente installée sur votre machine.
2. Si vous utilisez un éditeur de code :
   - Ouvrez le dossier `/projet-poo-l2-ouahid-houat-noa-gregoire-mellier-craftopia`.
   - Lancez le jeu en utilisant le bouton **Run**.

3. Si vous préférez utiliser le terminal :
   - Déplacez-vous jusqu'au dossier `/projet-poo-l2-ouahid-houat-noa-gregoire-mellier-craftopia`.
   - Exécutez les deux commandes suivantes dans l'ordre :
     ```bash
     javac -d out $(find . -name "*.java")
     java src/app/main/Main.java
     ```

## Utilisation
Voici les fonctionnalités principales du jeu :

### Gestion de la partie
- **Nouvelle Partie** : Cliquez sur le bouton **New Game** après le démarrage du jeu.  
- **Charger une Partie** : Cliquez sur **Continuer** pour reprendre une partie sauvegardée.  

### Paramètres
- **Couper le son** : Cliquez sur **Settings** dans le menu, puis sur **Mute Music**. Cliquez à nouveau pour réactiver le son.  

### Déplacements et interactions
- **Déplacer l'avatar** : Utilisez les flèches directionnelles du clavier.  
- **Ouvrir l'inventaire** : Cliquez sur l'icône en forme de sac située en haut à gauche de l'écran.  
- **Interagir avec un objet ou une ressource** : Cliquez sur le bouton **Interagir** lorsque vous êtes à proximité.

### Utilisation des récolteuses
- **Placer une récolteuse** :
  - Approchez l'avatar de la ressource choisie.
  - Ouvrez votre inventaire et cliquez sur le bouton **Poser** correspondant à la récolteuse souhaitée.
- **Activer une récolteuse** :
  - Interagissez avec elle, puis cliquez sur **Activate** pour démarrer la production.  
- **Modifier la ressource récoltée** :
  - Interagissez avec la récolteuse, cliquez sur **Reconfigure**, puis sélectionnez une nouvelle ressource.  
  - _Note_ : Une récolteuse ne peut récolter que les ressources adjacentes.  
- **Récupérer les ressources** : Interagissez avec la récolteuse et cliquez sur **Add** pour ajouter les ressources récoltées à votre inventaire.

### Utilisation des usines
- **Placer une usine** :
  - Approchez-vous d’une case vide sans objets autour.
  - Ouvrez votre inventaire et cliquez sur le bouton **Poser** correspondant à l'usine souhaitée.
  - Choisissez ensuite la recette à associer à l'usine.
- **Lancer une production** :
  - Interagissez avec l'usine, puis cliquez sur **Build** pour produire la ressource associée.  
  - La recette sera effectuée autant de fois que vous aurez cliqué, tant que vous disposez des ressources nécessaires.
- **Modifier une recette** :
  - Interagissez avec l'usine, cliquez sur **Reconfigure**, puis sélectionnez une nouvelle recette.  
- **Récupérer les ressources** : Interagissez avec l'usine et cliquez sur **Add** pour ajouter les ressources produites à votre inventaire.

### Interaction avec le marché
- **Trouver le marché** : Le marché apparaît toujours sur un côté de la carte. Explorez la map pour le localiser.
- **Acheter des objets** :
  - Interagissez avec le marché. Si vous disposez de l'argent nécessaire, le bouton **Buy** s’affichera en vert.
  - Cliquez dessus, puis sélectionnez la quantité souhaitée.  
- **Vendre des objets** :
  - Interagissez avec le marché. Si vous possédez la ressource dans votre inventaire, le bouton **Sell** s’affichera en rouge.
  - Cliquez dessus, puis sélectionnez la quantité à vendre.

### Fabrication (Crafting)
- **Ouvrir le menu de crafting** : Cliquez sur l'icône en forme de marteau située à côté de l'inventaire.  
- **Créer un objet** : Sélectionnez une recette dans le menu de crafting. Si vous avez les ressources nécessaires, l'objet sera produit.  

### Navigation et sauvegarde
- **Retour à l'accueil** : Cliquez sur **Exit** en haut au centre de l'écran. Vous serez invité à sauvegarder votre progression.  
- **Quitter le jeu** :
  - Cliquez sur **Exit** en haut au centre de l'écran, puis à nouveau sur **Exit** dans le menu principal. Votre partie sera sauvegardée.  
  - **Attention** : Si vous fermez le jeu directement sans passer par ce menu, votre partie ne sera pas sauvegardée.

## Auteurs et remerciements
- **Ouahid HOUAT**  
- **Noa GREGOIRE-MELLIER**
