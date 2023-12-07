import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class guessthemovie {
    public static void main(String[] args) throws FileNotFoundException {

        //Step 1: Scanning file and add all films in a list
        File file = new File("movielist.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            list.add(line);
        }

        //Step 2:  pick a random string
        int min = 0;
        int max = list.size();
        int randomnumber = (int)Math.floor(Math.random() * (max-min+1)-min);
        System.out.println("Movie name is: " + list.get(randomnumber));

        //Step 3: conver letters to _
        String  randommovie = list.get(randomnumber);
        int len = randommovie.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++){
            char ch = randommovie.charAt(i);
            if(ch!=' ') ch = '_';
            sb.append(ch);
        }
        System.out.println("Guess the movie: " + sb.toString());

        //Step 5: Revealing characters if exist
        int i=10;
        int ind =-1;
        
        while(sb.toString().contains("_")){
            Scanner inp = new Scanner(System.in);
            System.out.print("Enter a letter: ");            
            char guess = inp.next().charAt(0);
            
            if(sb.toString().indexOf(guess)!=-1){
                char repeat = guess;
                while(repeat==guess || sb.toString().indexOf(guess)!=-1 ){
                    System.out.println("Character already used, try a new letter");
                    System.out.print("Enter a letter: ");
                    guess = inp.next().charAt(0);
                }  
                
            }
            ind = randommovie.indexOf(guess);
            
            if(ind>=0){
                System.out.println("The letter is in movie name");
                while(ind>=0){
                    sb.setCharAt(ind, guess);
                    ind = randommovie.indexOf(guess, ind + 1);
                }
                System.out.println("Guess the movie: " + sb.toString());  
            }
            else {
                if(i==0){
                    System.out.println("Game Over");
                    break;
                }
                System.out.println("Oh no!, the letter does not exist. " + "\n" + "You've got " + i + " more chances to go.");
                i--;
            }
        }
        


    }
}
