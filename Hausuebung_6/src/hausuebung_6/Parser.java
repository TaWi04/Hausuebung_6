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

    final private File fileName;
    final List<String> lines = new ArrayList<>();
    // final private List<String> notClosedTags = new ArrayList<>();
    //final private List<String> finishedLines = new ArrayList<>();

    public Parser(File fileName) {
        this.fileName = fileName;
    }

    public String readFileWithStream() {
        String result = "";
        try {
            result = Files.lines(fileName.toPath()).reduce((string1, string2) -> string1 + string2)
                    .orElse("");
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        /*
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
        return lines;*/
    }

    public static List<PrioritisedOutput> splitLine(String content) {
        List<PrioritisedOutput> outputs = new ArrayList<>();
        String temp = "";
        int j = 1;//counter
        for (int i = 0; i < content.length(); i++) //i = index
        {
            if (i + 1 < content.length() && content.charAt(i) == '<' && content.charAt(i + 1) != '/') {
                if (!temp.equals("")) {
                    outputs.add(new PrioritisedOutput(j, temp));
                    j++;
                }
                temp = "";
                int tagIn;// index of Tag
                for (tagIn = i; content.charAt(tagIn) != '>'; tagIn++) {
                    temp += content.charAt(tagIn);
                }
                temp += ">";
                i = tagIn;

                int counter = 1;
                //TODO 78
            }
        }
        return outputs;
    }

    /*
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
    }*/
}
