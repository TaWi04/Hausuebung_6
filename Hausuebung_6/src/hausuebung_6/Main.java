/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_6;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Tamara
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parser parser = new Parser("simple1.txt");
        parser.readFileWithStream();
        for (String line : parser.lines) {
            System.out.println(line);
        }

        ExecutorService ex = Executors.newCachedThreadPool();

        // parser.reduceTags();
        //System.out.println(parser.getFinishedLines());
        //System.out.println(parser.getNotClosedTags());
    }

}
