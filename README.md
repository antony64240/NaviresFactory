# NaviresFactory


Utilisation de la librairie :


`EnsembleNavires e = new EnsembleNavire(10,[2,2,3]);` => Creer un environnement de 3 bateaux, 2 = Sous_Marin( 3cases), 3 = Torpilleurs (3 cases) dans une grille de 10 cases

`EnsembleNavires e = new EnsembleNavires(10,5)` => Creer un environnement  de 10 cases avec 5 bateaux de taille aléatoires.

`EnsembleNavires e = new EnsembleNavires(20,boolean)` => Creer un environnement de 20 cases,
true : 5 bateaux de taille différente génération aléatoire, 
false: crée un environnement de grille de 20 avec aucun bateau.

`EnsembleNavires e = new EnsembleNavires()` => Creer un environnement vide, par défaut de 10 cases

L'ajout d'un bateau :
`
boolean succes  = Ensemblenavire.add("Porte_Avion", 0, 0, false);
		System.out.println(succes);`
		
Fonction de tir : 

   ` System.out.println(Ensemblenavire.toucher(0, 0));
		System.out.println(Ensemblenavire.toucher(1, 0));
		System.out.println(Ensemblenavire.toucher(2, 0));
		System.out.println(Ensemblenavire.toucher(3, 0));
		System.out.println(Ensemblenavire.toucher(4, 0));`
    
`result: 
Case Touché
Case Touché
Case Touché
Case Touché
Porte_Avion coulé !
`


`EnsembleNavire.toJson()` => Retourne le navire au format Json.

Le format doit obligatoirement Suivre la structure d'en dessous pour être reconstruit.
Si vous utilisez Angular ou ReactJS, je vous conseil de gerer la construction et la reconstruction des grilles côtés Serveur(Java) (Cad, envoyer juste des X, et Y à votre API)
Sinon si vous voulez utiliser cette façon de Jsonnifer vos navires, essayer de comprendre le code de sérialisation (ToJson) pour le reproduire sur Angular, React ect...
`EnsembleNavire.JsonToNavire()`=> Reconstruit les navires.
Format accepter pour reconstruire un navire : 
`{"TailleGrille:",10,"Bateau:":"Contre_Torpilleur","NbrBateau:":1,"X:00":1,"Y:00":1,"Orientation:":true,"VALUE:00":false,"VALUE:01":false}`
=> Reconstruit l'environnement de taille 10 de la grille, avec un bateau (Contre_Torpilleur), Première Case du bateau , X= 0, Y = 0, Orientation : true (Verticale)

Etat du Navire 0 et de sa première box(Case) 0 : Value:00 => False (non détruite)
Etat du Navire 0 et de sa première box(Case) 1 :  Value:01 => False (non détruite)



`EnsembleNavire.toFiles("C:\Users\Utilisateur\eclipse-workspace3", Joueur1.getUUID(), 12)` => Enregistre l'état des navires dans un format JSON. 
Dans le fichier C/Users/Utilisateur/ecplise-workspace3/2234-DFZDA-dAFADAFD/12.txt pour cet exemple.

`EnsembleNavire e = new EnsembleNavire().FilesToNavires("C:\Users\Utilisateur\eclipse-workspace3", Joueur1.getUUID(), 12);` => Reconstruit les navires du fichier précedent.




    
