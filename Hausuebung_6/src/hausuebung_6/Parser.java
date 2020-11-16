/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tamara
 */
public class Parser {

    final private String fileName;
    final List<String> lines = new ArrayList<>();
    final private List<String> notClosedTags = new ArrayList<>();
    final private List<String> finishedLines = new ArrayList<>();

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readFileWithStream() {
        try {
            File file = new File(fileName);

            if (!file.canRead() || !file.isFile()) {
                System.out.println("ERROR!");
                System.exit(0);
            } else {

            }
            Files.lines(new File(fileName).toPath())
                    .map(s -> s.split("\n"))
                    .forEach(s -> Arrays.stream(s)
                    .forEach(string -> {
                        try {
                            lines.add(string);
                        } catch (NumberFormatException e) {
                        }
                    }));
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lines;
    }

    public void reduceTags() {
        int i = 0;
        int lineCount = 0;
        List<String> tempLines = new ArrayList<>();
        for (String line : lines) {

            if (line.startsWith("<")) {

                // while (line.startsWith("<")) {
                notClosedTags.add(line.substring(line.indexOf("<"), line.indexOf(">") + 1));

                finishedLines.add(line.substring(line.indexOf(">") + 1, line.length()));
                String substring = "";
                // for (String finishedLine : finishedLines) {
                String finishedLine = finishedLines.get(finishedLines.size() - 1);
                do {
                    substring = "";
                    String tag = "";
                    if (notClosedTags.size() != 0) {
                        tag = notClosedTags.get(notClosedTags.size() - 1);
                    } else {
                        System.out.println("ERROR");
                        tag = "";
                    }
                    if (finishedLine.contains(tag.replaceAll("<", "</"))) {
                        //int start = finishedLine.lastIndexOf(tag.replaceAll("<", "</"));
                        substring = finishedLine.replaceFirst(tag.replaceAll("<", "</"), "");
                        // substring = finishedLine.substring(0, start); // tag.length());//(start, start + tag.length() - 2);
                        if (notClosedTags.size() - 1 > 0) {
                            System.out.println("ERROR");
                            break;
                            // notClosedTags.remove(notClosedTags.size() - 1);

                            //return;
                        } else {
                            notClosedTags.remove(notClosedTags.size() - 1);
                        }

                    }
                    if (!substring.equals("") && substring.contains("<")) {
                        tempLines.add(substring);
                    }

                    i++;
                    //finishedLine.replaceAll(line, "");
                    System.out.println(substring);
                } while (substring.contains("<"));
                //}
                finishedLines.clear();
                finishedLines.addAll(tempLines);

            }
            lineCount++;
        }

    }

    public List<String> getNotClosedTags() {
        return notClosedTags;
    }

    public List<String> getFinishedLines() {
        return finishedLines;
    }

}
