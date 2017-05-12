Catboat
=====

//A chatbot using grammar and Homoiconic programming concepts

Here are some ideas for the software:

Internal structures
==================

Rewrite Rules
-------
These convert input into output.
The processor attempts to replace everything in the input with S, representing the Sentence.
Once the whole input has been replaced by S, the sentence has been successfully parsed.

The meaning (or at least word type) of any words that Catboat hasn't seen before
might be inferred by using the words in the input it does know,
and applying a rule that otherwise fits.

In this instance, the word will be assigned a word-type,
and any other information which can be inferred about it
(eg, that Paul, who is a Person instance, can do it) will also be stored.

Where more than one rule applies,
the rule which matches the largest part of a sentence will win
(at least until i've worked out a better way to resolve rule overlaps).

Special Objects
--------
Special instances of Things, which have intrinsic meaning (possibly hardcoded). These include:

First person - Catboat itself
Second person - the chat partner (user)
Current references to Thing(s) - the Things (if any) currently referred to by the third person pronouns, eg 'it','he', 'she','they'. Basically the third person.
usually (but not always) previously introduced Person instance. May be an ambiguous reference, possibly resolvable using context.
It - special case, which may evaluate to any number of things.
Eg, "It's raining" vs "It didn't like it"

Things
----
These are what Noun Phrases map to.

Either resolves to an entity, or to a collection of entities.

Whether or not an Action (verb) is valid to be used with the supplied Thing or Things,
    can be Known-Valid, Known-Invalid, or Unknown-Validity.
This relationship may be inferrable by `colocation with the words 'X don't Y' or 'X can't Y'.
The validity of an action with a particular Thing can be inherited from the categories a Thing belongs to

Contexts
-------
A context is a collection of known Things which can be referred to.
There are two contexts:

* Global Context - Things which are preserved from conversation to conversation
* Present Context -  Things introduced in this conversation.
Things may get promoted to Global Context, based on an as-yet undetermined metric.


Categories/tags/types/topics/Associations
-----
One or more named groups that a word can belong to.
there are different types of context,
eg "instanceof" means a Thing is a real example of another Thing: "Paul instanceof Person"
topics are related themes, such as building
associations can be emotional associations,
the type of thing can be an instance (paul), a prototype (person), tangible, intangible, a concept, a statement,
You can nest categories: the category Dog is in the Category animals, etc.

Association
-----
Similar to categories, but without any visible presence in the text.
A kind of metadata for words, to help extract subtext.
FOr instance, the word 'agressive' is in


Parts of Speech Tagging - Formal Grammar Engine
=====================
The processor looks at the input, and tries to match as much of the input as possible to an existing rule.
But only continuous portions of the input.

Verbs
-----
verbs can have special forms, which are different versions/spellings/string of the word. Eg, "is" vs "was".
They can have Side-Effects, which is a body of functionality that happens in some way when they are found in the input.

Nouns
-----
Nouns represent things!

things can have relationships to other things,
they can be in a category (eg, People, Concepts)
they can DO things (have a relationship to a verb, where they are the executor of that verb),
or have things done to them.

Adjectives
----------
These describe properties of a Thing, and may distinguish a specific instance of a Thing,
 or a subcategory, from its more general category or Thing.

Adverbs
-------
These describe the manner in which an action is undertaken/executed/done.

Determiners
------

These describe the number and specificity of a Thing,
eg how many of it we're talking about, or the portion of the whole group that the category represents (none, some, many, all etc).


Questions/Gaps in the Methodology
========

* how does Catboat learn new rules?
* is all semantic knowledge centred around Things?


How it works
============

Catboat has various different analysers/engines which operate on the input text (the conversation).
Some of these pre-process the input into something more digestible for later stages.
Some of them update Catboat's internal state in various ways.
They al co-operate to understand the sentence uttered.

The following stages operate sequentially, on the output of the previous stage:

* basic validation
    - checks that the input is the right length, and appears to be a form of language.
    - probably just a simple regex check or two.
* syntax parser
    - parses the input string, identifying the parts of speech, using X-bar or a similar strategy
    - TODO: need to be able to 'gracefully degrade',
    by still garnering some meaning (in the next step)
    from sentences where we only already know some of the words
* semantic analysis
    - attempts to derive meaning from the syntax tree,
       converting it into an internal representation of a sentence.       
*  typo handler
   - attempts to match any unrecognised words against closely matching known words,
       in case they are mis-typed. The probability that a word is mis-spelled, rather
       than conjugated in an unfamiliar way (eg spent vs spend)
       is informed by the position of the differing letters on the keyboard
* fallback semantic analyser
    - provides the aforementioned graceful degradation
    - treats the sentence as more ofa wordcloud, where words closer to each other in the sentence have stronger affinity (relation)

The following systems operate in parallel,
on the output of the last sequential stage:


* Trust Checker
    - acts as a gatekeeper to new knowledge acquisition
    - if the converser's trust level is already too low,
    their statements won't be incorporated as new knowledge.
    - Also alters the converser's trust level based on the factual accuracy of their statements,
     checked against catboat's own knowledge
    (this includes the global and local contexts).
    - Knowingly lying and just being wrong are not distinguished in this system,
    as the two are often too difficult for even humans to tell apart.

* Confidence
    - the more people make the same statement (corroborating each other),
    the more likely it is to be true,
    unless overridden by facts that prove otherwise.

* Part-of-Speech Inference
    - an optional step, when any words are unknown
    - tries to work out the role and meaning of an unknown word in a sentence.
    - has a number of ClueRules that help it make guesses, eg that words ending in 'ed' are past-tense verbs.
    - these rules are heavily language-dependent


Lexer/Parser
=====

there will be word-level rules and phrase level check rules

word level rules will include: 
* rules as to what combinations of letters can make a valid word
(like phonotactics but for spelling). Used for fairly early validation of input.
If a word fails these check rules, 
it may be a typo, gibberish, an arbitrary but meaninglful string (eg a license plate) or a foreign language
* 'clue' rules which help to hint what part-of-speech a word is, based on its suffixes etc.
these are not definite by default, but rather add to a 'confidence' that a word is of a certain type

We follow a bottom-up approach:

0. Check that all words are valid words, using the check rules 
1. Identify the part-of-speech types of all the words we already know
2. Use these known quantities to make guesses about the phrase structures (eg VP -> V N)
3. check the resulting guesses about the unknown words, against the word-level clue rules
    (eg, that words ending in -ing are verbs in a vertain tense)
4. check the resulting sentence against the knowledge engine, to see if it makes sense etcg
  

Semantic Analyser/Knowledge Engine
======

Operates as a kind of database. This is the part that can be considered to do the understanding.
 

3 concepts:

Things, Relationships and References

Things
----

Anything which can be referred to. Can have a Relationship to other things,
constrained by which Relationships are valid (make sense) for that Thing.
Common structural things include Categories and Properties. 

eg:

a man named Paul would be described as

a thing which has the rel Instanceof to the thing Person 
(which is the thing that has the rel 'IsA' to the thing Category (henceforth being called a Category for brevity),
 the rel 'HasProperty' to the Property Nameable and the rel 'Has' to the thing Name,
 whose rel 'Persons'. , which inherits the valid rel Has Name).
 
Relationships
-------
 
A manner in which two things relate. These often map roughly to Verb Phrases in the syntax level.
Not all possible pairings of things are meaningful (valid); you can't marry an idea.
Relationships are themselves things with the relationship 'IsA' 
(implemented specially, to avoid infinite recursive self-reference) to the thing Relationship.
Their definition describes the conjugation rules (including any irregularities)

Some thought should be given to defining Should and Does: we need to define what something does, yes;
but we can also define schemas for prototypes of things, eg categories like animals. 
These could contain a combination of attributes which are defined,
and attributes which are required but should be defined by members of that category -- all animals are alive,
but the number of legs an animal has  must be defined by its species, a subcategory.
This is similar to the OO inheritance model. 

Less vaguely, a thing will define various constraints or caveats which apply to other things declaring a relationship to it. 

References
----------

Once you've mentioned Paul in a sentence, you can use 'he' to refer to Paul,
until another thing is introduced which qualifies for use with the reference 
-- a member of the Category Person with the property Male. Therefore, a single thing can have multiple  which can point to it.

A reference is a way of pointing to a thing using language. In terms of the formal grammar layer,
this should be some kind of Noun Phrase. For what qualifies as a noun phrase, see the Formal Grammar part (tbw). 

IsA
----
the relationship IsA (i think always but I'm not sure) denotes membership of a Category -- my Ford KA IsA Car.
However, there doesn't have to be a single IsA relationship -- my KA  also IsA Hatchback, a Ford, etc.
In fact, a thing doesn't even need to have a 'chief' or 'primary' category - a Thing can be all of the following,
each one the most relevant category in a different context: Mother, Daughter, Wife, Sister, Woman, Astronaut.
 
What is more pertinent is whether a property CAN be used as a referent -- usually they can grammatically,
but this is often extremely rude or offensive 
(a person can be Black, but they are not a Black -- but they could be referred to as a Black Person).
 
Need examples of the above, both where a category something belongs to cannot be used to refer to it and its category-mates, and where it can. Grammatically.