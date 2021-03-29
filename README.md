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
`
    
