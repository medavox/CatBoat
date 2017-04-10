Catboat
=====

A chatbot using Chomsky Hierarchies and Homoiconic programming concepts

Here are some ideas within the software:

Rewrite Rules
-------
which convert input into output.
The processor attempts to replace everything in the input with S, representing the Sentence.
Once the whole input has been replacedby S, the sentence has been successfully parsed.

The meaning (or at least word type) of any words that Catboat hasn't seen before might be inferred by using the words in the input it does know, and applying a rule that otherwise fits.
In this instance, the word will be assigned a word-type, and any other information which can be inferred about it (eg, that Paul, who is a Person instance, can do it) will also be stored.

Where more than one rule applies, the rule which matches the most of a sentence will win (at least until i've worked out a better way to resolve rule overlaps)

Special Objects
--------
Special instances of Things, which have instrinsic meaning. These include:

First person - the bot
second person - the chat partner (user)
It - special case, which may evaluate to any number of things.
Eg, "It's raining" vs "It didn't like it"
He, she - refer to a previously introduced Person instance. May be an ambiguous reference, possibly resolvable using context.

Things
----
Either resolves to an entity, or a collection of entities.

Whether or not an Action (verb) is valid to be used with the supplied Thing or Things,
    can be Known-Valid, Known-Invalid, or Unkown-Validity.
This relationship may be inferrable by colocation with the words 'X don't Y' or 'X can't Y'
The validity of an action with a particular Thing can be inherited from the categories a Thing belongs to

How it works
------
The processor looks at the input, and tries to match as much of the input as possible to an existing rule.
But only continuous portions of the input.

Verbs
-----
verbs can have special forms, which are different versions/spellings/string of the word. Eg, "is" vs "was".
They can have Side-Effects, which is a body of functionality that happens in some way when they are found in the input.

Nouns
-----
Nouns represent things!
they can have relationships to other things,
they can be in a category (eg, People, Concepts)
they can DO things (have a relationship to a verb, where they are the executor of that verb),
or have things done to them.

Adjectives
----------
These describe properties of a Thing, and may distinguish a specific instance of a Thing,
 or a subcategory, from its more general category or Thing.

 Adverbs
 -------
 These describe the manner in which an action is undertaken/executed/done

Determiners
------

These specify the number and specificity of a Thing,
eg how many of it we're talking about, or the portion of the whole group that the category represents.
