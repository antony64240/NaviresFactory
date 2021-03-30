# NaviresFactory


#### Utilisation de la librairie :

## Créer un environnement avec une Liste
Exemple :
`EnsembleNavires e = new EnsembleNavire(10,[2,2,3]);` 
Création d'un environnement de 3 bateaux, 2 = Sous_Marin( 3cases), 3 = Torpilleurs (3 cases) dans une grille de 10 cases  et positionné aléatoirement.

## Créer un environnement  de 10 cases avec 5 bateaux de taille aléatoires et positionné aléatoirement.
`EnsembleNavires e = new EnsembleNavires(10,5)` 

## Créer un environnement personnalisé.
**/////UTILISER CE CONSTRUCTEUR POUR PERMETTRE AU JOUEUR DE PLACER LEURS BATEAUX OU ILS VEULENTS, et mettre le boolean à false///////.**

`EnsembleNavires e = new EnsembleNavires(20,boolean)` 
Créer un environnement de 20 cases, true : 5 bateaux de tailles différentes génération aléatoire, et positionné aléatoirement.
**False: créer un environnement de grille de 20 avec aucun bateau.**

## Créer un environnement vide
Utiliser pour reconstruire un environnement via un files ou Json! 

`EnsembleNavires e = new EnsembleNavires()` 


## L'ajout d'un bateau : 
Ici, s'il renvoie faux, ça veut dire que vous essayer de positionner un bateau sur une case déjà occupée, ou alors qu'il n'y a pas une case d'écart entre vos navires.

`Ensemblenavire.add("Porte_Avion", 0, 0, false);` => 5 Cases
`Ensemblenavire.add("Croiseur", 0, 0, false);`=> 4 Cases
`Ensemblenavire.add("Contre_Torpilleur", 0, 0, false);`=> 2 Cases
`Ensemblenavire.add("Torpilleur", 0, 0, false);` => 3 Cases
`Ensemblenavire.add("Sous_Marin", 0, 0, false);` => 3 Cases
	
		
		
## Fonction de tir : 

`System.out.println(Ensemblenavire.toucher(0, 0)); `
`System.out.println(Ensemblenavire.toucher(1, 0)); `
`System.out.println(Ensemblenavire.toucher(2, 0)); `
`System.out.println(Ensemblenavire.toucher(3, 0)); `
`System.out.println(Ensemblenavire.toucher(4, 0)); `
    
`result: 
Case Touché
Case Touché
Case Touché
Case Touché
Porte_Avion coulé !
`

## Retourne le navire au format Json.
`EnsembleNavire.toJson()` 


##  Reconstruit les navires via un Json.
**Le format doit obligatoirement Suivre la structure d'en dessous pour être reconstruit.
Si vous utilisez Angular ou ReactJS, je vous conseil de gerer la construction et la reconstruction des grilles côtés Serveur(Java) (Cad, envoyer juste des X, et Y à votre API)
Sinon si vous voulez utiliser cette façon de Jsonnifer vos navires, essayer de comprendre le code de sérialisation (ToJson) pour le reproduire sur Angular, React ect...**

`EnsembleNavire.JsonToNavire()`

**Format accepter pour reconstruire un navire : **

`{"TailleGrille:",10,"Bateau:0":"Contre_Torpilleur","NbrBateau:":1,"X:00":1,"Y:00":1,"Orientation:":true,"VALUE:00":false,"VALUE:01":false}`

**Explication : **
Reconstruit l'environnement de taille 10 de la grille, avec un bateau (Contre_Torpilleur), Première Case du bateau , X= 1, Y = 1, Orientation : true (Verticale)
Etat du Navire 0 et de sa première box(Case) 0 : Value:00 => False (non détruite)
Etat du Navire 0 et de sa deuxième box(Case) 1 :  Value:01 => False (non détruite)


## Enregistre l'état des navires dans un format JSON dans un fichier. 
`EnsembleNavire.toFiles("C:\Users\Utilisateur\eclipse-workspace3", Joueur1.getUUID(), 12)` => 
Dans le fichier C/Users/Utilisateur/ecplise-workspace3/2234-DFZDA-dAFADAFD/12.txt pour cet exemple.

## Reconstruit les navires du fichier précedent.
`EnsembleNavire e = new EnsembleNavire().FilesToNavires("C:\Users\Utilisateur\eclipse-workspace3", Joueur1.getUUID(), 12);` 
