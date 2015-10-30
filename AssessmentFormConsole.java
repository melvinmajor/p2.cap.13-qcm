
import java.util.Scanner;

import mcq.Choice;
import mcq.AssessmentFormLoader;
import mcq.Question;
import mcq.AssessmentForm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import java.lang.Math;

/**
 * 
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
            System.out.println("["+choices.length+"]\tJe ne desire pas repondre");
      
            System.out.println("Votre reponse : ");
      
            // on demande à l'étudiant de taper au clavier sa réponse (un entier parmi les choix possibles)
            // ceci est equivalent à TextIO.getIntLn() vu au cours avec Olivier Bonaventure et Charles Pecheur
            Scanner in = new Scanner(System.in);
            return in.nextInt();
        }
        
        
    public static void main(String[] args) 
        {
            
            System.out.println("Bienvenue ! : ");
            
            // On demande les points du QCM
            Scanner saisieUtilisateur = new Scanner(System.in);
            
            System.out.println("Entrez votre nom : ");
            String pseudo = saisieUtilisateur.nextLine();
            System.out.println();
            System.out.println("Sur combien de points le QCM doit-il être ?");
            double pointsTotal = saisieUtilisateur.nextInt();
            System.out.println("Combien vaut une bonne reponse ?");
            double repB = saisieUtilisateur.nextDouble(); 
            System.out.println("Combien vaut une mauvaise reponse ?");
            double repM = saisieUtilisateur.nextDouble(); 
            System.out.println("Combien vaut une réponse blanche ?");
            double repN = saisieUtilisateur.nextDouble();

            String filename = "QCM2.txt";
            // Charchement du QCM depuis le fichier (questions et réponses aux questions)
            AssessmentForm mcq = AssessmentFormLoader.buildQuestionnaire(filename);

            // Recupération du tableau des questions
            Question [] questions = mcq.getQuestions();            
            
            double totalScore = 0.0;
            int nbreRepB = 0;
            int nbreRepN = 0;
            int nbreRepM =0;
            // On itère sur chaque question pour evalue le score de l'étudiant à cette question
            // Suggestion: on pourrait iterer dans un ordre aleatoire ou sur un sous ensemble aleatoire des questions
            
            int[] numeroUtilise = new int[questions.length];
            for(int i=0; i < questions.length; i++)
            {
                numeroUtilise[i] = -1;
            }
       
            for(int i = 0; i < questions.length; i++)
            {
                while(numeroUtilise[i] == -1)
                {
                    int lower = 0;
                    int higher = questions.length;
                    int random = (int)(Math.random() * (higher-lower)) + lower;
              
                    System.out.println("Chiffre random :"+random);
                    System.out.println("Chiffre Questions lengt :"+questions.length);
              
                    int e=0;
                    boolean noSame = true;
                        while(e < i && noSame == true)
                        {
                            if(numeroUtilise[e] == random)
                            {
                                noSame = false;
                            }
                            e = e+1;
                        }
              
                        if(noSame == true)
                        {
                            numeroUtilise[i] = random;
                        }
                    }
                }
            
            for(int i =0; i < questions.length; i++) 
            {
                int number = numeroUtilise[i];
                Question q = questions[number];
                //getAnswer(q) te renvoie la réponse entrée par l'utilisateur.
                int reponseUtilisateur = getAnswer(q);
                Choice [] choices = q.getChoices();
                
                
                if (q.isCorrectChoice(reponseUtilisateur))
                    {
                        System.out.println("Felicitations !");
                        double simpleGrader = repB;
                        nbreRepB++;
                        totalScore = totalScore + repB;
                    }
                else 
                    {
                        if (reponseUtilisateur == choices.length)
                        {
                           System.out.println("Dommage !");
                           double simpleGrader = repN;
                           nbreRepN++;
                           totalScore = totalScore + repN;
                        }
                        else
                        {
                            System.out.println("Aie,erreur !");
                            double simpleGrader = repM;
                            nbreRepM++;
                            totalScore = totalScore + repM;
                        }
                    }
            }
      
            Date today = new Date();
            DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
            
            // Affichage du score final
            System.out.println();
            System.out.println();
            System.out.println(fullDateFormat.format(today));
            System.out.println("Merci d'avoir répondu à notre QCM.");
            System.out.println("Voici le détail de vos points :");
            System.out.println();
            System.out.println("Sur "+questions.length+" questions !");
            System.out.println("Nombre de bonne(s) reponse(s) : "+nbreRepB);
            System.out.println("Nombre de mauvaise(s) reponse(s) : "+nbreRepM);
            System.out.println("Nombre de question(s) non répondue(s) : "+nbreRepN);
            System.out.println();
            System.out.println("Suivant les criteres de cotation suivant : ");
            System.out.println("Pour une bonne reponse : +"+repB);
            System.out.println("Pour une mauvaise reponse : "+repM);
            System.out.println("Pour une reponse blanche : "+repN);
            System.out.println("Sur un total de : "+pointsTotal);
            System.out.println();
            System.out.println("Vous avez : "+totalScore+"/"+pointsTotal);
            
           
            // Ecriture dans le fichier
           try
           {
                                
                String adressedufichier = System.getProperty("user.dir") + "/MesResultat_"+pseudo+".txt";
                FileWriter fw = new FileWriter(adressedufichier, true);
                
                // le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
                BufferedWriter output = new BufferedWriter(fw);
                
                //on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
                
                output.write(fullDateFormat.format(today));
                output.write("\r\n");
                output.write("Merci d'avoir répondu à notre QCM.");
                output.write("\r\n");
                output.write("\r\n");
                output.write("Voici le détail de vos points :");
                output.write("\r\n");                
                output.write("Sur "+questions.length+" questions !");
                output.write("\r\n");
                output.write("Nombre de bonne(s) reponse(s) : "+nbreRepB);
                output.write("\r\n");
                output.write("Nombre de mauvaise(s) reponse(s) : "+nbreRepM);
                output.write("\r\n");
                output.write("Nombre de question(s) non répondue(s) : "+nbreRepN);
                output.write("\r\n");
                output.write("\r\n");
                output.write("Suivant les criteres de cotation suivant : ");
                output.write("\r\n");
                output.write("Pour une bonne reponse : +"+repB);
                output.write("\r\n");
                output.write("Pour une mauvaise reponse : "+repM);
                output.write("\r\n");
                output.write("Pour une reponse blanche : "+repN);
                output.write("\r\n");
                output.write("Sur un total de : "+pointsTotal);
                output.write("\r\n");
                output.write("\r\n");
                output.write("Vous avez : "+totalScore+"/"+pointsTotal);
                output.write("\r\n");
                output.write("\r\n");
                output.write("-------------------------------------------------------");
                output.write("\r\n");
                output.write("\r\n");
                //on peut utiliser plusieurs fois methode write
                
                output.flush();
                //ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
                
                output.close();
                //et on le ferme
                System.out.println("Resultat enregistrée dans : "+adressedufichier+".");
            }
            catch(IOException ioe){
                System.out.print("Erreur : ");
                ioe.printStackTrace();
            }       
    }
}
