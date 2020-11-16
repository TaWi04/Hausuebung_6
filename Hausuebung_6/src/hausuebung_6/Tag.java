/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_6;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tamara
 */
public class Tag {

    private String content = "";
    private String tag;
    private boolean hasMatch = false;
    final private List<String> notClosedTags = new ArrayList<>();
    final private List<String> finishedLines = new ArrayList<>();

    public Tag(String line) {

        if (line.startsWith("<") && line.endsWith(">")) {
            String[] splitValue = line.split(">");
            String startTag = splitValue[0] + ">";
            tag = startTag.substring(1, startTag.length() - 1);

            if (line.endsWith("</" + tag + ">")) {
                content = line.substring(startTag.length(), line.length() - (tag.length() + 3));
                hasMatch = true;
            }
        } else {
            hasMatch = true;
            content = line;
        }
    }

    public String getContent() {
        String temp = "";
        if (!tag.equals("")) {
            temp = "\n";
        }
        if (hasMatch) {
            if (content.equals("")) {
                temp += tag + " has no content";
            } else {
                temp += content;
            }
        } else {
            temp += tag + " has an error!!";
        }
        return temp;
    }

    /*
    public void splitContent() {
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
        } while (substring.contains(
                "<"));
        //}
        finishedLines.clear();

        finishedLines.addAll(tempLines);

    }*/
}
