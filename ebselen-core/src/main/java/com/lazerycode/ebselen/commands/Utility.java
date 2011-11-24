package com.lazerycode.ebselen.commands;

/**
 * Created by IntelliJ IDEA.
 * User: Mark Collin
 * Date: 24/11/11
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public interface Utility {
    /**
     * Will create a random e-mail address for the supplied domain
     *
     * @param domain - email domain
     * @return String - randomly generated email address
     * @throws Exception
     */
    String generateRandomEmailAddress(String domain);

    /**
     * Strip convert / to _ and strip non word characters out of a string (useful for filename generation)
     *
     * @param garbageFilledString - the String to sanitise.
     * @return String - The string with all non-word characters stripped out and all / converted to _
     */
    String stripGarbage(String garbageFilledString);

    /**
     * Will convert the first character, and the first character after every space to an uppercase and everything else to lowercase
     *
     * @param wordsToConvert - The string to convert
     * @return String - The converted string
     * @throws Exception
     */
    String ucWords(String wordsToConvert);
}
