# Enoncés des TPs hibernate  

Pour effectuer les exercices, tout au long de cette formation, vous disposez :  

    • du diagramme de classe (formation-hibernate.pdf),  
    • de 4 projets Maven (formation-hibernate-init, formation-hibernate-finEx1 ...),  
    • d'un jar h2 pour disposer d'une BDD autonome,  
    • d'un script SQL pour créer le schéma de la BDD et charger des données.  

## Plan

 0. [Installation de la BDD](#0-installation de la BDD)
 1. [TP1 : Mapping simple et première manipulation &rarr; *TP1*](#TP1)
 2. [TP2 : Les associations &rarr; *TP2*](#TP2)
 3. [TP3 : Requêtage &rarr; *TP3*](#TP3)

## Travaux pratiques

### 0. Installation de la BDD

Les opérations décrites dans cette partie ne sont pas liées à Hibernate. Mais, pour travailler avec cette technologie, nous devons disposer d'une base de données H2 sur laquelle seront disponibles les tables et les données, utiles pour vos exercices.

    • Installation de la base de données H2         
	
    • Récupérez le jar de h2 et le déposer dans un dossier de travail par exemple sous d:\temp\user\h2        
	
    • Double cliquez sur le jar, IE ou Firefox s'ouvre et indiquez les paramètres de connexion à la base de données :   
		Configuration enregistrée :  Generic H2 (Server)  
		Nom de configuration :  Generic H2 (Server)  
		Driver Class / JDBC URL / User Name / Password, cf. src/main/resources/fr/insee/config/formation.properties  

    • Dans le projet Maven init ou v0 sous Eclipse, récupérez le script de création du schéma et d'insertion des 
	  données : dans src/main/resources sous scripts ouvrez le script sql shem-data-h2.sql, 
	  puis le copier et le coller dans au niveau de l'interface client h2 ouverte dans le navigateur.


<a name="TP1"></a>
### 1. TP1

MAPPING SIMPLE ET PREMIERE MANIPULATION

***Les objectifs ****généraux de cet exercice****:***

-   configurez le fichier hibernate.cfg.xml (mapping de classe)
-   mappez les classes métiers (qui existent dès le départ), sans mapper les associations : Mise en place du mappage, id + attributs simples. Mettre en place une annotation transient en lieu et place de la mise en commentaires des objets associés par exemple
-   Utilisez basiquement hibernate, sur Medecin ou Maladie
-   Faites plus de mapping simple si vous êtes très rapide...

**TODO :**

On démarre les exercices avec le projet "init" ou "v0".

Les classes métiers de ce projet ne contiennent aucune annotation et le fichier hibernate.cfg.xml ne fait référence à aucun mapping de classes.

Les paramètres d'accès à la base de données sont déjà indiqués dans le fichier hibernate.cfg.xml. Contrairement au diaporama, nous passons par le composant Inseeconfig qui mutualise les paramètres applicatifs et propose un pool de connexion.

La librairie Hibernate est déjà installée à travers le pom.xml Maven (dependency).

-   Observer la base de données pour bien comprendre le lien entre les tables et leurs colonnes et les classes et leurs attributs du diagramme de classe.

-   Commencez par configurer dans hibernate.cfg.xml, le mapping pour la classe Medecin.

-   Décrire dans la classe métier Medecin, le mapping à travers les annotations. Pensez à mettre en place la gestion des identifiants (en utilisant la séquence adéquate).

-   Vous pouvez maintenant manipuler les objets de cette classe métier :
	-   Sur votre projet Maven, dans une classe (par exemple MedecinLanceur) disposant d'une méthode main
	-   Charger l'environnement d'Hibernate (Configuration, SessionFactory, Session, Transaction)
	-   Dans un premier temps :
		-   Créer un nouvelle instance de Medecin
		-   Persister cette instance
		-   commiter ces modifications
		-   Vérifier en BDD que la nouvelle observation existe
	-   Dans un deuxième temps :
		-   Avant le commit, récupérez la dernière observation en BDD de la table,
		-   Afficher en console (system.out.println) les attributs de ce nouvel objet.
	-   Dans un troisième temps :
		-   désactiver le code de la création de la nouvelle instance Medecin et de sa persistance
		-   récupérez cette observation en BDD
		-   supprimer avant le commit cet objet
		-   Vérifier en BDD que cette observation a été supprimée.
		
-   Reproduisez l'expérience pour la classe Maladie, dans l'hibernate.cfg.xml et dans la classe elle-même.
	-   Pour l'attribut, date pensez à ajouter l'annotation @Column et à indiquer le bon type, comme ci après :
		-   Soit en hibernate : @Type(*type*="date")
		-   Soit en JPA : @Temporal(TemporalType.*DATE*).
		
-   Si vous êtes en avance :
	-   Soit vous reproduisez l'expérience sur d'autres classes métiers,
	-   Soit vous manipulez de façon un peu plus avancé en ouvrant une autre session et rendre les objets "detached" puis à nouveau "persistant"

<a name="TP2"></a>
### 2. TP2

LES ASSOCIATIONS

Objectifs :

-   Mettre en place le mapping des associations.
-   Mesurer les effets de Cascade
-   Commencer à utiliser HibernateUtils et un DAO

**TODO :**

-   Fermez toutes vos classes sous Eclipse.

-   Utilisez maintenant le projet Maven "v1" :
	-   Le mapping simple (des attributs de type simple) a déjà été mise en place sur toutes vos classes métiers,
	-   les attributs de type classe métier sont commentés ainsi que leurs mutateurs.
	
-   Mappez l'association DossierMedical -&gt; Maladie (1-to-N)
	-   Indiquer le lien vers Patient en @Transient pour éviter des problèmes,
	-   Dans une classe lanceurDossierMedical avec une méthode main, manipulez et insérez un nouveau DossierMedical en BDD ainsi qu'une maladie associée à ce DossierMedical,
	-   Mettre en place la cascade sur l'opération d'insertion en BDD (persist en JPA ou save en Hibernate ou les deux).
	
-   Mapper l'association Consultation -&gt; Medecin (N-To-1 + Date)

-   Mapper l'association Consultation -&gt; Patient (autre N-To-1, vite fait!),
	-   pensez à mettre en place l'annotation @Embedded pour l'attribut Adresse
	
-   Mapper l'association Ordonnance -&gt; Soin (N-To-N)

-   Mapper l'association Consultation &lt;-&gt; Ordonnance (bidirectionnel 1-N)

-   Mapper l'association Patient &lt;-&gt; DossierMedical (bidirectionnel 1-1)

-   Si vous êtes en avance :
	-   Vous disposez dans le package "fr.insee.tp.dao.impl", des classes DAO, une classe DAO par classe métier.
		-   Cette partie va vous permettre de travailler presque comme dans la réalité,
		-   L'implémentation/classe MaladieDAO permet d'accèder à toutes les méthodes CRUD pour manipuler des objets métiers Maladie.
		-   Manipulez des objets Maladie (insertion, récupération ou suppression)
			-   Dans une classe de test MaladieDAOTest, dans une méthode de test
			-   Créer la classe MaladieDAOTest
			-   créer une méthode de test, en tapant test puis CRTL espace choisir @Test
			-   dans cette méthode, utiliser la classe HibernateUtils, dans le package "fr.insee.tp.dao"
				-   utilisez la méthode open de cette classe pour monter l'environnement Hibernate
				-   utilisez la méthode close pour commiter à la fin de vos manipulations
				-   Entre ces 2 appels :
					-   instanciez le DAO MaladieDAO pour accèder à ses méthodes du CRUD
					-   insérer, récupérer supprimer des objets Maladie

		
<a name="TP3"></a>
### 3. TP3

LES DIFFERENTS REQUETAGES

Objectifs :

-   Manipulez avec HQL
-   Manipulez avec Criteria
-   Mesurez les effets des différentes stratégies de chargement

**TODO:**

-   Fermez toutes vos classes sous Eclipse.

-   Utilisez maintenant le projet Maven "v2" :
	-   Le mapping simple et des associations (des attributs de type simple) a déjà été mise en place sur toutes vos classes métiers,

Partie 1 - Requêtage avec HQL :

-   Mise en place sur Maladie (classe sans lien avec une autre classe) :

	-   Recherchez les maladies "Inconnue" par une requête paramétrée.
	-   Modifiez leur description avant le commit.
	-   Vérifiez que la modification est bien prise en compte en base.

	-   Relancez le script de réinitialisation de la base.
	-   Recherchez les maladies inconnues.
	-   Modifiez leur description après le commit.
	-   Constatez que la modification n'est pas prise en compte en base.

	-   Recherchez le NOMBRE de maladies "Inconnue" par une nouvelle requête

    -   Par une nouvelle requête, mettez en oeuvre la récupération d'objet non lié à la session hibernate via new :
            -   Pour vérifier le bon fonctionnement :
                -   Modifiez la valeur d'un attribut de ces objets avant le commit.
                -   Constatez qu'elles ne sont pas modifiés en BDD après le commit,

-   Mise en place sur Dossier Medical (classe en liens vers du 1 comme du N) :
	-   Requêtez sur dossierMedical, pour ne récupérer que les instances en lien avec la maladie "Malaria".
	-   Remarquez que l'on récupère deux fois le même dossier medical en utilisant join
	-   Mettez en place select dictinct .
		-   Ne récupérez qu'un seul dossier.Medical
		-   Remarquez qu'on récupère l'intégralité des maladies associées au dossier medical.
	-   Commitez dès la fin de la récupération,
	-   Puis itérez sur les dossiers medicaux et leurs maladies
		-   pour constater une LazyInitialisedException (la fonction d'affichage toString itère déja sur les différentes maladies associées à un dossier Medical).
	-   Contourner le lazyLoading pour éviter l'exception précédente par un fetch au niveau de la requête HQL. Que se passe t-il au niveau des maladies ?
	-   Contourner le problème en mettant du fetch au niveau de la classe métier

*Partie 2 - Requêtage avec Critieria (et Criterion): *

-   Mettez en place sur Medecin (classe sans lien vers une autre classe), un requêtage criteria pour rechercher la liste des Medecin qui ont pour secteur "2".

-   Mettez en place sur DossierMedical. (classe en lien vers des liens 1 et N), un requêtage criteria pour recupérer la liste des DossiersMedicaux.
	-   Après recupération, faites une trace avec system.out.println
	-   puis parcourez en java le graphe objet,
		-   Et à l'aide de traces, visualisez les moments où sont générées les requetes en base de données.
		-   Vous pouvez également visualiser en mode Debug d'Eclipse, la liste des Maladies associées à un dossier Medical en parcourant le graph. Vous constaterez que l'attribut Set&lt;Maladies&gt; vaut null avant de parcourir les choses plus en detail...

-   Visualisez une LazyInitialisationException en committant alors que les listes ne sont pas encore chargées intégralement (chargement en lazy loading).
	-   Modifiez l'option fetch pour modifier la stratégie
		- &gt; soit par(fetch = FetchType.*EAGER*) dans l'annotation d'association sur la classe
		-&gt; soit par setFetchMode("propriete", FetchMode.JOIN) dans la requête Criteria
