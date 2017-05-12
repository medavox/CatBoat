package com.medavox.catboat.knowledge_engine.ontology.language;

/**@author Adam Howard
* @date 27/04/2017
*
* Third attempt at writing a parser for the ontology language
*
Todo: make a language for describing context-sensitive grammars
* recursive replacement rules?
* so S = one or more X
* X = a j, or a k
* k is of the following form, containing one or more n
*
* etc
*
* use <angle  brackets> to refer to already-declared patterns
* the escape character can be %, to avoid clashes with regex and strings
* I vaguely remember that storing subpatterns this uses arbitrary amounts of memory, but that's fine
*
* parsing this will be bottom-up:
* first we'll recognise/replace patterns where
*          the right hand side of the rule is composed only of string literals
* once they'redone, there'll be rules which those replaced patterns form part of

how do we read these structures into java types, if we have the parameters and details of an object,
before we have red the object itself?

some kind of intermediary store of floating details, which later become attached to an object?
they can become attached by IDing them with their (the details') starting index in the input

so we've just successfully recognised a fairly top-level rule (something like S = relationship\s*{<structures>})
and we recognise the inner structure(s) that form part of the match refer to details we've already parsed and stored. */

public class Parser2 {

    //list of rules we've already successfully parsed

    public void doit() {
        //for every rule where either a) all the references to rules on the RHS are known references, already parsed,
        //or b) there are no references on the RHS

        //build a copy of the text where we replace each portion of text which corresponds to a RHS, with its LHS rule name in angle brackets
        //each time we do this, create an object with a single LHS field (its name) and a mix of
        // text-literals and zero-or-more other LHS rule names (which reference other object like this one)
    }

}
