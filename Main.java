//Hashtable for several integer values

package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


class Hashelement{
    int value;
    Hashelement child;

    public Hashelement(int value){
        this.value = value;
        this.child = null;
    }

    public void addChildren(int value){
        if(child==null){
            child= new Hashelement(value);
        }
        else{
            child.addChildren(value);
        }
    }

    public Hashelement search(int value){
        if(child.value == value){
            return child;
        }

        else if(child == null){
            return null;
        }

        else{
            Hashelement searchedElement = child.search(value);
            return searchedElement;
        }
    }

    public boolean delete(int value){
        if(this.child == null){
            return false;
        }

        if(this.child.value==value){
            this.child =child.child;
            return true;
            }

        else{
            return(child.delete(value));
        }
    }





}

class LinkedHashTable {
    int m = 10;
    Hashelement [] table;
    int [] numberoflinks;
    //float A = (Math.sqrt(5)-1)/2);

    public LinkedHashTable(int m) {
        this.m = m;
        table = new Hashelement[m];
        numberoflinks = new int[m];
        for(int i=0;i<m;i++){
            this.numberoflinks[i] = 0;
            this.table[i] =null;
        }
    }


    public int hashfunction(int value){
        return(Math.abs(value%m));
    }


    public void insert(int value){
        int position = hashfunction(value);

        numberoflinks[position]++;
        if(table[position]==null){
            table[position]=new Hashelement(value);
        }

        else{
            table[position].addChildren(value);
        }
    }

    public Hashelement search(int value){
        int position = hashfunction(value);

        if(table[position]==null){
            return null;
        }
        else if(table[position].value == value){
            return table[position];
        }

        else{
            Hashelement searchedElement = table[position].search(value);
            return searchedElement;
        }
    }

    public boolean delete(int value){
        int position = hashfunction(value);

        numberoflinks[position]--;

        //Element is not in the hashtable
        if(table[position]==null){
            return false;
        }

        else{
            if(table[position].value==value){
                table[position]=table[position].child;
                return true;
            }

            else{
                return(table[position].delete(value));
            }
        }
    }
    public void printliks(){
        int filed=0;
        for(int i=0; i<numberoflinks.length; i++){
            if(numberoflinks[i]>0) filed++;
        }

        System.out.println((filed*100)/(numberoflinks.length-1) + "% of list is filed with objects");
    }
    public void longestlink(){
        int max_value=0;
        int max_position=0;
        for(int i=0; i<numberoflinks.length; i++){
            if(numberoflinks[i]>max_value){
                max_position=i;
                max_value=numberoflinks[i];
            }
        }
        System.out.println("Longest link in the list at position " + max_position +  " with " + max_value +" links ");
    }




}








public class Main {
    public static int[] reader(String path) {
        Path path1 = Paths.get(path);
        long lines = 0;
        try {
            lines = Files.lines(path1).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = (int) lines;
        int[] numbers = new int[length];
        try {
            Scanner scanner = new Scanner(new File(path));
            int i = 0;
            while (scanner.hasNextInt()) {
                numbers[i++] = scanner.nextInt();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return numbers;
    }


    public static void main(String[] args) {
        int[] Werte;
        Werte = reader("C:/Users/renne/Desktop/Master/Semester2/Algorithmen_Datenstrukturen/Hofmann/Praktikum/Praktikum4-5/seq1.txt");

        LinkedHashTable HT = new LinkedHashTable(196);

        for (int i = 0; i < Werte.length; i++) {
            HT.insert(Werte[i]);
        }

        int searchedvalue = 30;
        Hashelement searched = HT.search(searchedvalue);
        System.out.println("Searching for value : "+ searchedvalue);
        if(searched!=null)System.out.println(searched);
        else System.out.println("Element not found");

        int deletingvalue = 30;
        boolean result = HT.delete(deletingvalue);
        System.out.println("Deleting value : "+ deletingvalue);
        if(result)System.out.println("Deleting successful");
        else System.out.println("Deleting not successful");

        System.out.println("\n####Excerise 2 a ####");
        HT.printliks();
        System.out.println("\n####Excerise 2 b ####");
        HT.longestlink();

        for (int i = 0; i < Werte.length; i++) {
            HT.insert(Werte[i]);
        }

        for (int i = 0; i < Werte.length; i++) {
            HT.delete(Werte[i]);
        }

        System.out.println("\n####Excerise 2 c ####");
        HT.printliks();
        HT.longestlink();
    }
}




