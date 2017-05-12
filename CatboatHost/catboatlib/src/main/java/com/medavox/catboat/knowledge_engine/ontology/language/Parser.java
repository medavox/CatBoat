package com.medavox.catboat.knowledge_engine.ontology.language;

import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.tools.javac.util.Assert.check;

/**
 * @author Adam Howard
 * @date 25/04/2017
 */

public class Parser {
    private ParserState currentState = ParserState.TOP_LEVEL;
    private static final int MAX_FILE_BUFFER = 1048576 * 4;//4MB
    private TopLevelCommand[] cmds = {
        new TopLevelCommand("relationship ") {
            @Override
            public int handleBody(String wholeInput, int bodyStart) {
                //skip over any whitespace
                int i = bodyStart;
                while(Character.isWhitespace(wholeInput.charAt(i))) {
                    i++;
                }

                //pull identifier from input
                check(wholeInput.substring(i).matches("[a-zA-Z]\\w+\\s*\\{"),
                        "input does not represent a valid identifier");


                for(; i < wholeInput.length(); i++) {
                    String nextChar =  new String(new char[]{wholeInput.charAt(i)});

                }
                return bodyStart;

            }
        },
        new TopLevelCommand("thing ") {
            @Override
            public int handleBody(String wholeInput, int bodyStart) {
                return bodyStart;
            }
        }
    };
    public String removeComments(String input) {
        //todo:fiddle with these regexes until they work
        Pattern lineComments = Pattern.compile("//.+$", Pattern.MULTILINE);
        Pattern multilineComments = Pattern.compile("/\\*.+\\*/");
        Matcher lineMatcher = lineComments.matcher(input);
        int[] matchStarts = new int[input.length()];
        int[] matchEnds = new int[input.length()];
        int i = 0;
        while(lineMatcher.find()) {
            matchStarts[i] = lineMatcher.start();
            matchEnds[i] = lineMatcher.end();
            i++;
        }


        Matcher multilineMatcher = multilineComments.matcher(input);
        while(multilineMatcher.find()) {
            matchStarts[i] = multilineMatcher.start();
            i++;
        }
        int matches = i;
        //now snip out all of the listed start/end combosfrom the input string, then return it
        int charIndex = 0;
        int matchIndex = 0;
        String output = "";
        boolean insideComment = false;
        while( charIndex < input.length()) {
            //if(charIndex )

            //we've just entered a region of non-copy chars
            if(charIndex == matchStarts[matchIndex]) {
                insideComment = true;
            }
            if(charIndex ==matchEnds[matchIndex]) {
                //we've just exited a non-copy region
                insideComment = false;
                matchIndex++;
            }

            if(!insideComment){
                output += input.charAt(charIndex);
            }
            charIndex++;
        }
        return output;
    }



    public void readInLanguageFile(File f) {
        try {
            FileReader fr = new FileReader(f);
            char[] buf = new char[MAX_FILE_BUFFER];
            int readResult = fr.read(buf, 0, MAX_FILE_BUFFER);

            if(readResult == -1){
                System.err.println("ERROR: provided ontology input file was empty!");
                return;
            }

            boolean moreToRead = (readResult == MAX_FILE_BUFFER);

            String input = new String(buf, 0, readResult);
            input = input.trim();

            String[] tokens = input.split("\\s");
            for(int i = 0; i < tokens.length; i++) {
                if(currentState == ParserState.TOP_LEVEL) {

                }
            }
            //String[] tokens = input.split("");
            int index = 0;


        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    private int handleThingDeclaration(String input, int start) {
        return start;
    }


    /**@return the index to carry on from, after this method has finished its parsing*/
    private int handleRelationshipDeclaration(String input, int start) {
        return start;
    }

    private enum ParserState {
        TOP_LEVEL,
        REL_DEF,
        THING_DEF,
    }

    private abstract class TopLevelCommand {
        private String cmd;
        public TopLevelCommand(String command){
            this.cmd = command;
        }
        /**@param wholeInput the entire input string
         * @param bodyStart the index of the char immediately after the matchuing command string
         * @return the index to carry on from, after this method has finished its parsing*/
        public abstract int handleBody(String wholeInput, int bodyStart);
    }
}
