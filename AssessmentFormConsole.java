
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
 * @author Gauthier Fossion, Melvin Campos, Julien Pluquet
 */
public final class AssessmentFormConsole 
{
    public static int getAnswer(Question q) 
        {
            // Affiche la question
            System.out.println(q.getWording());
        
            // Récupération du tableau des choix possibles 
            Choice [] choices = q.getChoices();
            
            // On itère sur les choix (ici aussi on pourrait le faire dans un ordre aléatoire)
            for (int i = 0; i < choices.length; i++) 
                {
                    Choice choice = choices[i];
                    // Affichage du choix à l'indice i
                    System.out.println("["+i+"]\t"+choice);
                }
            // On propose à l'utilisateur de ne pas répondre
            System.out.println("["+choices.length+"]\tJe ne désire pas répondre");
      
            System.out.println("Please, input an answer and press Enter ...");
      
            // on demande à l'étudiant de taper au clavier sa réponse (un entier parmi les choix possibles)
            // ceci est equivalent à TextIO.getIntLn() vu au cours avec Olivier Bonaventure et Charles Pecheur
            Scanner in = new Scanner(System.in);
            return in.nextInt();
        }
        
        
    public static void main(String[] args) 
        {
            
            // On demande les points du QCM
            Scanner saisieUtilisateur = new Scanner(System.in);
            System.out.println("Sur combien de points le QCMM doit-il être ?");
            double pointsTotal = saisieUtilisateur.nextInt();
            System.out.println("Combien vaut une bonne réponse ?");
            double repB = saisieUtilisateur.nextDouble(); 
            System.out.println("Combien vaut une mauvaise réponse ?");
            double repM = saisieUtilisateur.nextDouble(); 
            System.out.println("Combien vaut une réponse blanche ?");
            double repN = saisieUtilisateur.nextDouble(); 
            
            // On les affiches
            System.out.println(pointsTotal);
            System.out.println(repB);
            System.out.println(repM);
            System.out.println(repN);
            
            String filename = "QCM.txt";
            // Charchement du QCM depuis le fichier (questions et réponses aux questions)
            AssessmentForm mcq = AssessmentFormLoader.buildQuestionnaire(filename);

            // Recupération du tableau des questions
            Question [] questions = mcq.getQuestions();
        
            double totalScore = 0.0;
            // On itère sur chaque question pour evalue le score de l'étudiant à cette question
            // Suggestion: on pourrait iterer dans un ordre aleatoire ou sur un sous ensemble aleatoire des questions
            for (int i = 0; i < questions.length; i++) 
            {
                Question q = questions[i];
                //getAnswer(q) te renvoie la réponse entrée par l'utilisateur.
                int reponseUtilisateur = getAnswer(q);
                Choice [] choices = q.getChoices();
                
                if (q.isCorrectChoice(reponseUtilisateur))
                    {
                        double simpleGrader = repB;
                    }
                else 
                    {
                        if (reponseUtilisateur == choices.length)
                        {
                           double simpleGrader = repN;
                        }
                        else
                        {
                            double simpleGrader = repM;   
                        }
                    }
                
                // A toi de jouer mon ami ! 
                totalScore = 0.0; // A compléter
            }
      
            // affichage du score final
            System.out.println("your score is:"+totalScore);

    }


}