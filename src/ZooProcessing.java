import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.metal.MetalLabelUI;

public class ZooProcessing {
    ArrayList<Flora> floraList = new ArrayList<>();
    ArrayList<Fauna> faunaList = new ArrayList<>();

    public void processFile(String fileName) {
        try {            
            Scanner scnr = new Scanner(new File(fileName));
            scnr.nextLine();
            while(scnr.hasNextLine()) {
                String organismLine = scnr.nextLine();
                
                addOrganism(organismLine);
            }

            scnr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void interact() {
        System.out.println("CSV File has been loaded, Flora and Fauna lists have been populated!");
        printSelections();
        Scanner scnr = new Scanner(System.in);

        String input = scnr.nextLine();
        while(!input.equalsIgnoreCase("X")) {
            if(input.equalsIgnoreCase("D")) {
                randFact();
            }
            else if(input.equalsIgnoreCase("L")) {
                listCounts();
            }
            else {
                System.out.println("Unrecognized command.");
            }

            printSelections();
            input = scnr.nextLine();
        }

        scnr.close();
    }

    /** Self-Explanation
     * This function takes in a new organism from the file, and sorts it into the correct subtype (flora or fauna). It then calls the specific class constructor in order to
     * create a new object of that type, and adds it to the corresponding list.
     */
    public void addOrganism(String organismLine) {
        Scanner scnr = new Scanner(System.in);
        String[] split = organismLine.split(",");
        
        if (split[0].equals("Fauna")){ //Big ol' if/else tree for Fauna
            if (split[1].equals("Reptile")){
                Fauna temp = new Reptile(organismLine);
                faunaList.add(temp);
            }
            else if (split[1].equals("Amphibian")){
                Fauna temp = new Amphibian(organismLine);
                faunaList.add(temp);
            }
            else if (split[1].equals("Bird")){
                Fauna temp = new Bird(organismLine);
                faunaList.add(temp);
            }
            else if (split[1].equals("Fish")){
                Fauna temp = new Fish(organismLine);
                faunaList.add(temp);
            }
            else if (split[1].equals("Invertebrate")){
                Fauna temp = new Invertebrate(organismLine);
                faunaList.add(temp);
            }
            else if (split[1].equals("Mammal")){
                Fauna temp = new Mammal(organismLine);
                faunaList.add(temp);
            }
        }

        else { //If/else block for Flora
            if (split[1].equals("Angiosperm")){
                Flora temp = new Angiosperm(organismLine);
                floraList.add(temp);
            }
            else if (split[1].equals("Fern")){
                Flora temp = new Fern(organismLine);
                floraList.add(temp);
            }
            else if (split[1].equals("Gymnosperm")){
                Flora temp = new Gymnosperm(organismLine);
                floraList.add(temp);
            }
            else if (split[1].equals("Moss")){
                Flora temp = new Moss(organismLine);
                floraList.add(temp);
            }
        }
        scnr.close();
    }
    
    public void randFact() {
        Random rand = new Random();
        if(rand.nextInt(1) == 0) {
            Fauna randFauna = faunaList.get(rand.nextInt(faunaList.size()-1));
            System.out.println(randFauna.species + " fact: " + randFauna.fact);
        }
        else {
            Flora randFlora = floraList.get(rand.nextInt(floraList.size()-1));
            System.out.println(randFlora.species + " fact: " + randFlora.fact);
        }
    }
    
    /** Self-Explanation
     * This shows the count for each species and scientific name. In order to do this, it should iterate through the file (while loop), adding to different counters depending on the
     * type and scientific name
     */
    public void listCounts() {
        int floraCounter = 0;
        int angiospermCounter = 0;
        int fernCounter = 0;
        int gymnospermCounter = 0;
        int mossCounter = 0;
        int faunaCounter = 0;
        int amphibianCounter = 0;
        int birdCounter = 0;
        int fishCounter = 0;
        int invertebrateCounter = 0;
        int mammalCounter = 0;
        int reptileCounter = 0;
        for (Flora tmpObj : floraList){
            floraCounter += 1;
            String tmpClass = tmpObj.getClass().getName();
            if (tmpClass.equals("Angiosperm")){
                angiospermCounter += 1;
            }
            else if (tmpClass.equals("Fern")){
                fernCounter += 1;
            }
            else if (tmpClass.equals("Gymnosperm")){
                gymnospermCounter += 1;
            }
            else if (tmpClass.equals("Moss")){
                mossCounter += 1;
            }
        }

        for (Fauna tmpObj : faunaList){
            faunaCounter += 1;
            String tmpClass = tmpObj.getClass().getName();
            if (tmpClass.equals("Amphibian")){
                amphibianCounter += 1;
            }
            else if (tmpClass.equals("Bird")){
                birdCounter += 1;
            }
            else if (tmpClass.equals("Fish")){
                fishCounter += 1;
            }
            else if (tmpClass.equals("Invertebrate")){
                invertebrateCounter += 1;
            }
            else if (tmpClass.equals("Mammal")){
                mammalCounter += 1;
            }
            else if (tmpClass.equals("Reptile")){
                reptileCounter += 1;
            }
        }
        System.out.println("Flora Count: " + floraCounter);
        System.out.println("Angiosperm Count: " + angiospermCounter);
        System.out.println("Fern count: " + fernCounter);
        System.out.println("Gymnosperm count: " + gymnospermCounter);
        System.out.println("Moss count: " + mossCounter);
        System.out.println("");
        System.out.println("Fauna Count: " + faunaCounter);
        System.out.println("Amphibian count: " + amphibianCounter);
        System.out.println("Bird count: " + birdCounter);
        System.out.println("Fish count: " + fishCounter);
        System.out.println("Invertebrate count: " + invertebrateCounter);
        System.out.println("Mammal count: " + mammalCounter);
        System.out.println("Reptile count: " + reptileCounter);
    }
    
    public void printSelections() {
        System.out.println("What would you like to do?");
        System.out.println("[D]isplay a random fact?");
        System.out.println("[L]ist current organism type counts?");
        System.out.println("[X] to exit out of the Zoo DB?");
    }
}