package com.medavox.catboat.knowledge_engine;

/**
 * @author Adam Howard
 * @date 24/04/2017
 */

public interface QueryInterface {
    /**checks if we've seen this word (or another conjugation of it) before,
     * and if we have, returns its type. */
    PartOfSpeech getWordType(Word word, Utterance enclosingUtterance);
}
