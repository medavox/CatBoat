package com.medavox.catboat.knowledge_engine.ontology.language;

/**@author Adam Howard
 * @date 12/05/2017 */

import java.util.ArrayList;
import java.util.List;

/**A java object for production rules in our ontology language */
class Rule {
    private String leftHandSide;
    private List<RHSAtom> rightHandSide;

    public Rule(String lhs, String rhs) throws ParseException {
        leftHandSide = lhs;

        List<RHSAtom> atoms = new ArrayList<>();

        //todo:convert the given string for the RHS into a series of RHS atoms
        for(int i = 0; i < rhs.length(); i++) {
            if(new String(new char[]{rhs.charAt(i)}).equals("<")) {
                //reference detected
                boolean closingBracketFound = false;
                int j = i;
                String refName = "";
                for(; j < rhs.length(); j++) {
                    String c = new String(new char[]{rhs.charAt(i)});
                    if(c.equals("<")) {
                        throw new ParseException("found another opening < before expected closing >: can't nest reference names!");
                    }
                    else if(c.equals(">")) {
                        //found closing bracket
                        closingBracketFound = true;
                        break;
                    }
                    else {
                        refName += c;
                    }
                }
                if(!closingBracketFound) {
                    //wereached the end of the input without finding a matching >
                    throw new ParseException("reached end of input before finding expected closing >!");
                }
            }
        }
        //rightHandSide= rhs;
    }
}
