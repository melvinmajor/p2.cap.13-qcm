
import java.util.Scanner;

import mcq.Choice;
import mcq.AssessmentFormLoader;
import mcq.Question;
import mcq.AssessmentForm;
import util.PRNG;

/**
 * Un simple simulateur de questionnaire à choix multiple fonctionnant en mode console
 * Vous pouvez modifier ce fichier pour votre projet ou vous en inspirer librement.
 * Comme vous pouvez voir nous fournissons à titre d'exemple une simple methode d'évaluation (simpleGrader).
 * Les questions sont toujours présentées dans le même ordre ainsi que les propositions de réponses.
 * N'hésitez pas à implémenter d'autres évaluateurs et ajouter de l'aléatoire dans la présentation/selection des questions
 * et des choix.
 * @author P. Schaus
 */
public final class AssessmentFormConsole {
	
	
	// TODO: imaginer d'autres systèmes d'évaluation 
   
	/**
	 * Evaluateur simple:
	 * @param q est la qestion
	 * @param choiceIdx est l'indice de la réponse choisie
	 * @return +1 si choiceIdx correspond une réponse correcte, 0 sinon
	 */
	public static double simpleGrader(Question q, int choiceIdx) {
      // on teste si choiceIdx est bien un choix correct pour cette question
		if (q.isCorrectChoice(choiceIdx)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 1) Affiche la question dans la console et 
    * 2) propose les réponses/choix possibles à cette question
    * 3) demande à l'utilisateur un choix parmi une ces réponse 
	 * @param q est la question à proposer à l'utilisateur
	 * @return l'indice de la réponse faite par l'utilisateur après présentation de la question et des réponses possibles 
	 */
	public static int getAnswer(Question q) {
      // affiche la question
		System.out.println(q.getWording());
		// recuperation du tableau des choix possibles
      Choice [] choices = q.getChoices();
      // on itère sur les choix (ici aussi on pourrait le faire dans un ordre aléatoire)
		for (int i = 0; i < choices.length; i++) {
			Choice choice = choices[i];
         // affichage du choix à l'indice i
			System.out.println("["+i+"]\t"+choice);
		}
      
		System.out.println("Please, input an answer and press Enter ...");
      
      // on demande à l'étudiant de taper au clavier sa réponse (un entier parmi les choix possibles)
      // ceci est equivalent à TextIO.getIntLn() vu au cours avec Olivier Bonaventure et Charles Pecheur
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}
	
	public static void main(String[] args) {
		String filename = "QCM.txt";

		// Charchement du QCM depuis le fichier (questions et réponses aux questions)
		AssessmentForm mcq = AssessmentFormLoader.buildQuestionnaire(filename);

		// Recupération du tableau des questions
		Question [] questions = mcq.getQuestions();
		double totalScore = 0.0;
      // On itère sur chaque question pour evalue le score de l'étudiant à cette question
      // Suggestion: on pourrait iterer dans un ordre aleatoire ou sur un sous ensemble aleatoire des questions
		for (int i = 0; i < questions.length; i++) {
			Question q = questions[i];
			totalScore += simpleGrader(q,getAnswer(q));
		}
      // affichage du score final
		System.out.println("your score is:"+totalScore);

	}


}