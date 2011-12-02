/*
 * Copyright (c) 2010-2011 Ardesco Solutions - http://www.ardescosolutions.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lazerycode.ebselen.customhandlers;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.common.annotations.Beta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mark Collin
 */
@Beta
public class email {

    public static final Logger logger = LoggerFactory.getLogger(email.class);

    /**
     * Generic send an e-mail functions
     *
     * @author MPLC
     * @version D6
     *
     * @param recipients String[] - Array of E-Mail addresses that we want to send to.
     * @param sender E-Mail address of the sender
     * @param subject The E-Mail subject
     * @param mailBody The mail E-Mail body text
     * @param debug boolean true to enable debug mode
     * @return boolean denoting success
     * @throws Exception
     */
    public static boolean sendMail(String[] recipients, String sender, String subject, String mailBody, boolean debug) throws Exception {
        boolean mailResult = true;
        // create some properties and get the default Session
        Properties props = new Properties();
        //props.put("mail.smtp.host", get("smtpServer"));
        if (debug) {
            props.put("mail.debug", debug);
        }
        Session session = Session.getInstance(props, null);
        session.setDebug(debug);
        try {
            // Create the message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sender));
            // Add a list of recipients to mail
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            // Add Mail Subject
            msg.setSubject("[selenium-grid] " + subject);
            // Set date of Mail
            msg.setSentDate(new Date());
            // Add E-Mail Text
            msg.setText(mailBody);
            // Send E-mail
            Transport.send(msg);
        } catch (MessagingException mex) {
            logger.debug("\n--Error detected when attempting to send e-mail");
            mex.printStackTrace();
            logger.debug("");
            Exception ex = mex;
            do {
                if (ex instanceof SendFailedException) {
                    SendFailedException sfex = (SendFailedException) ex;
                    Address[] invalid = sfex.getInvalidAddresses();
                    if (invalid != null) {
                        logger.debug("    ** Invalid Addresses");
                        mailResult = false;
                        if (invalid != null) {
                            for (int i = 0; i < invalid.length; i++) {
                                logger.debug("         " + invalid[i]);
                            }
                        }
                    }
                    Address[] validUnsent = sfex.getValidUnsentAddresses();
                    if (validUnsent != null) {
                        logger.debug("    ** ValidUnsent Addresses");
                        mailResult = false;
                        if (validUnsent != null) {
                            for (int i = 0; i < validUnsent.length; i++) {
                                logger.debug("         " + validUnsent[i]);
                            }
                        }
                    }
                    Address[] validSent = sfex.getValidSentAddresses();
                    if (validSent != null) {
                        logger.debug("    ** ValidSent Addresses");
                        mailResult = false;
                        if (validSent != null) {
                            for (int i = 0; i < validSent.length; i++) {
                                logger.debug("         " + validSent[i]);
                            }
                        }
                    }
                }
                logger.debug("");
                if (ex instanceof MessagingException) {
                    ex = ((MessagingException) ex).getNextException();
                } else {
                    ex = null;
                }
            } while (ex != null);
        }
        return mailResult;
    }

    /**
     * Generic send an e-mail functions.
     * Debugging is turned off
     *
     * @author MPLC
     * @version D6
     *
     * @param recipients String[] - Array of E-Mail addresses that we want to send to.
     * @param sender E-Mail address of the sender
     * @param subject The E-Mail subject
     * @param mailBody The mail E-Mail body text
     * @return boolean denoting success
     * @throws Exception
     */
    public static boolean sendMail(String[] recipients, String subject, String sender, String mailBody) throws Exception {
        return sendMail(recipients, subject, sender, mailBody, false);
    }
}
