import java.io.IOException;
import java.io.*;

/**
 * Décrivez votre classe QCM ici.
 *
 * @Julien Pluquet, Gauthier Fossion, Melvin Campos
 * @2015-10-20
 */
public class QCM {
//Pour s'aider : "OpenClassroom Programmez en Java"

// Récuperer les données depuis le fichier txt.
// Variable pour les questions : string :  question1,2,3,4,5,6,...
// Variable pour les réponses : string : reponse1a, reponse1b, reponse1c, ...
// Variable pour la bonne réponse : string : repC : variable de la bonne réponse
// --> Melvin : 21/10/2015 : Soir

    public static String lectureMots() {
		//Gestion des Flux IO
		String str = "";
		Fichier fi = ouvrirQCM();				
		int nbrRandom = //Il faudrait faire un lien vers le PRNG ici !			
		str = fi.lire(nbrRandom);				
		try {
				fi.fermer();
			}
		catch (Exception e) {					
				e.printStackTrace();
			}			
		return str;
	}
	public static Fichier ouvrirQCM() {
		String nomFichier = "src/QCM.txt";
		Fichier fi = new Fichier();
		fi.ouvrir(nomFichier, "Lecture");
		return fi;
	}
	
// Méthode de correction :  
// Demander à l'utilisateur : Combien vaut 1 bonne réponse, combien vaut une mauvaise réponse, combien vaut une réponse blanche.
// Demander à l'utilisateur : Le nombre de point total que le QCM doit remplir
// Variable pour la bonne réponse : repB : double
// Variable pour la mauvaise réponse : repM : double
// Variable pour la réponse blanche : repN : double
// Variable pour le nombre de points total : pointsTotal : int
// --> Julien : 20/10/2015 : Soir

// PRNG -> Math.random
// PRNG doit sortir un nombre aléatoire entre 1 & le nombre max de question sans répétitions.


// Vérification de la réponse & Incrémentation des résultats
// Variable de correction : correction1a, correction1b, ... : VALEUR BOOLEEN
// Incrémentation des bonnes réponses suivant la variable repB
// Incrémentation des mauvaises réponses : repM 
// Incrémenation des réponses blanches : repN
// --> Gauthier : 22/10/2015 : soir

// Affichage des résultats : 
// Score final : XX / XX
// Score final : Nombre de mauvaises réponses 
// Score final : Nombre de bonnes réponses
// Score final : Nombres de réponses blanches
// Affichage des réponses fausses avec correction
// --> Gauthier : 22/10/2015 : Soir

// Sauvegarde des résultats dans un fichier texte.
// --> Melvin : 23/10/2015 : Soir

}
