
import java.util.Scanner;

import mcq.Choice;
import mcq.AssessmentFormLoader;
import mcq.Question;
import mcq.AssessmentForm;
import util.PRNG;

/**
 * Un simple simulateur de questionnaire a choix multiple fonctionnant en mode console
 * Vous pouvez modifier ce fichier pour votre projet ou vous en inspirer librement.
 * Comme vous pouvez voir nous fournissons a titre d'exemple une simple methode d'evaluation (simpleGrader).
 * Les questions sont toujours presentees dans le meme ordre ainsi que les propositions de reponses.
 * N'hesitez pas a implementer d'autres evaluateurs et ajouter de l'aleatoire dans la presentation/selection des questions
 * et des choix.
 * @author Gauthier Fossion, Melvin Campos Casares, Julien Pluquet
 */
public final class AssessmentFormConsole 
{
    public static int getAnswer(Question q) 
        {
            // Affiche la question
            System.out.println(q.getWording());
        
            // Recuperation du tableau des choix possibles 
            Choice [] choices = q.getChoices();
            
            // On it√®re sur les choix (ici aussi on pourrait le faire dans un ordre aleatoire)
            for (int i = 0; i < choices.length; i++) 
                {
                    Choice choice = choices[i];
                    // Affichage du choix a l'indice i
                    System.out.println("["+i+"]\t"+choice);
                }
            // On propose a l'utilisateur de ne pas repondre
            System.out.println("["+choices.length+"]\tJe ne desire pas repondre");
      
            System.out.println("SVP, insÈrez une rÈponse et appuyer sur Entrer...");
      
            // on demande a l'etudiant de taper au clavier sa reponse (un entier parmi les choix possibles)
            // ceci est equivalent a TextIO.getIntLn(), vu au cours avec Olivier Bonaventure et Charles Pecheur
            Scanner in = new Scanner(System.in);
            return in.nextInt();
        }
        
        
    public static void main(String[] args) 
        {
            
            // On demande les points du QCM
            Scanner saisieUtilisateur = new Scanner(System.in);
            System.out.println("Sur combien de points le QCM doit-il etre ?");
            double pointsTotal = saisieUtilisateur.nextInt();
            System.out.println("Combien vaut une bonne reponse ?");
            double repB = saisieUtilisateur.nextDouble(); 
            System.out.println("Combien vaut une mauvaise reponse ?");
            double repM = saisieUtilisateur.nextDouble(); 
            System.out.println("Combien vaut une reponse blanche ?");
            double repN = saisieUtilisateur.nextDouble(); 
            
            // On les affiches
            System.out.println(pointsTotal);
            System.out.println(repB);
            System.out.println(repM);
            System.out.println(repN);
            
            String filename = "QCM.txt";
            // Chargement du QCM depuis le fichier (questions et reponses aux questions)
            AssessmentForm mcq = AssessmentFormLoader.buildQuestionnaire(filename);

            // Recuperation du tableau des questions
            Question [] questions = mcq.getQuestions();
        
            double totalScore = 0.0;
            // On it√®re sur chaque question pour evalue le score de l'etudiant a cette question
            // Suggestion: on pourrait iterer dans un ordre aleatoire ou sur un sous ensemble aleatoire des questions
            for (int i = 0; i < questions.length; i++) 
            {
                Question q = questions[i];
                //getAnswer(q) te renvoie la reponse entree par l'utilisateur.
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
                totalScore = 0.0; // A completer
            }
      
            // affichage du score final
            System.out.println("Votre score est:"+totalScore);

    }


}