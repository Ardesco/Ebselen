//TODO pull out anything useful and delete the rest
package com.lazerycode.ebselen.customhandlers;

import java.util.*;

import com.lazerycode.ebselen.EbselenCore;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class to collect e-mails from the webmail server
 *
 * @author MPLC
 */
public class squirrelMail extends EbselenCore {

    private static WebDriver mailBrowser = null;
    private static String webmailUsername;// = get("mailUsername");
    private static String webmailPassword;// = get("mailPassword");
    private static String webmailHost;// = get("mailServer");
    private static String webmailBucket;// = get("mailBucketName").toLowerCase();
    private static String emailNameInList;
    private static String emailSentTo;
    private static String emailSubject;
    private static String truncatedSubject;
    private static String messageSubject;
    private static String messageSentFrom;
    private static String messageDate;
    private static String messageSentTo;
    private static String messagePriority;
    private static String messageBody;
    private static ArrayList messageLinks = new ArrayList();
    private static int attachmentCount = 0;
    private static final Logger logger = LoggerFactory.getLogger(EbselenCore.class);
//
//    //Constructor
//    public squirrelMail() {
//    }
//
//    //Getters
//    /**
//     * The Subject of the e-mail.
//     *
//     * @author MPLC
//     *
//     * @return String e-mail subject
//     * @throws Exception
//     */
//    public String getMessageSubject() throws Exception {
//        return messageSubject;
//    }
//
//    /**
//     * The email address/list of email addresses that the email was sent to/
//     *
//     * @return String email address or a list of email addresses the email was sent to
//     * @throws Exception
//     */
//    public String getMessageSentTo() throws Exception {
//        return messageSentTo;
//    }
//
//    /**
//     * The e-mail address that the e-mail was sent from.
//     *
//     * @author MPLC
//     *
//     * @return String the mail address of the sender
//     * @throws Exception
//     */
//    public String getMessageSentFrom() throws Exception {
//        return messageSentFrom;
//    }
//
//    /**
//     * The date that the email was sent (In String format).
//     *
//     * @author MPLC
//     *
//     * @return String The date the email was sent
//     * @throws Exception
//     */
//    public String getMessageDate() throws Exception {
//        return messageDate;
//    }
//
//    /**
//     * The priority of the e-mail message.
//     *
//     * @author MPLC
//     *
//     * @return
//     * @throws Exception
//     */
//    public String getMessagePriority() throws Exception {
//        return messagePriority;
//    }
//
//    /**
//     * Get the e-mail message body.
//     *
//     * @author MPLC
//     *
//     * @return String the email body text
//     * @throws Exception
//     */
//    public String getMessageBody() throws Exception {
//        return messageBody;
//    }
//
//    /**
//     * Get the number of attachments that the email message had.
//     *
//     * @author MPLC
//     *
//     * @return int number of attchaments found
//     * @throws Exception
//     */
//    public int getAttachmentCount() throws Exception {
//        return attachmentCount;
//    }
//
//    /**
//     * Get a list of links found within the email body text in the format &lt;linkText&gt; -> &lt;linkHREF&gt;
//     *
//     * @author MPLC
//     *
//     * @return A HashMap of links in the format &lt;linkText&gt; -> &lt;linkHREF&gt;
//     * @throws Exception
//     */
//    public ArrayList getMessageLinks() throws Exception {
//        return messageLinks;
//    }
//
//    /**
//     * Get the number of links found within the email body text
//     *
//     * @author MPLC
//     *
//     * @return int showing the number of links found in the body text
//     * @throws Exception
//     */
//    public int getLinkTotal() throws Exception {
//        return messageLinks.size();
//    }
//
//    /**
//     * Return the text of link x
//     *
//     * @author MPLC
//     *
//     * @param linkNumber the link number to get results for
//     * @return String containing the link text
//     * @throws Exception
//     */
//    public String getLinkText(int linkNumber) throws Exception {
//        HashMap links = (HashMap) messageLinks.get(linkNumber - 1);
//        Iterator linkInfo = links.entrySet().iterator();
//        while (linkInfo.hasNext()) {
//            Map.Entry linkData = (Map.Entry) linkInfo.next();
//            return linkData.getKey().toString();
//        }
//        return null;
//    }
//
//    /**
//     * Return the text of link x
//     *
//     * @author MPLC
//     *
//     * @param linkNumber the link number to get results for
//     * @return String containing the HREF data
//     * @throws Exception
//     */
//    public String getLinkHREF(int linkNumber) throws Exception {
//        HashMap links = (HashMap) messageLinks.get(linkNumber - 1);
//        Iterator linkInfo = links.entrySet().iterator();
//        while (linkInfo.hasNext()) {
//            Map.Entry linkData = (Map.Entry) linkInfo.next();
//            return linkData.getValue().toString();
//        }
//        return null;
//    }
//
//    //Setters
//    /**
//     * Set the email address that the email was sent to.
//     *
//     * @param recipient The email address the e-mail we are searching for was sent to
//     * @throws Exception
//     */
//    public void setEmailSentTo(String recipient) throws Exception {
//        emailSentTo = recipient;
//        emailNameInList = recipient.replaceAll("\\.", "_").toLowerCase();
//    }
//
//    /**
//     * Set the subjct that we are searching for with the email bucket.
//     *
//     * @author MPLC
//     *
//     * @param subject Subject of the email that you are looking for
//     * @throws Exception
//     */
//    public void setEmailSubject(String subject) throws Exception {
//        emailSubject = subject;
//        if (subject.length() > 50) {
//            truncatedSubject = subject.substring(0, 50) + "...";
//        } else {
//            truncatedSubject = subject;
//        }
//    }
//
//    //Generic Functions
//    /**
//     * Open a mail browsr and log into the webmail server.
//     *
//     * @author MPLC
//     *
//     * @throws Exception
//     */
//    private void logIntoWebmail() throws Exception {
//        if (mailBrowser == null) {
//            mailBrowser = setBrowser(mailBrowser, settings.browserSetting());
//        }
//        mailBrowser.get(webmailHost);
//        mailBrowser.findElement(By.xpath("//input[@name='login_username']")).sendKeys(webmailUsername);
//        mailBrowser.findElement(By.xpath("//input[@name='secretkey']")).sendKeys(webmailPassword);
//        mailBrowser.findElement(By.xpath("//input[@value='Login']")).submit();
//    }
//
//    /**
//     * Select the email bucket and user bucket to look for an email in.
//     *
//     * @throws Exception
//     */
//    private boolean selectBucket() throws Exception {
//        boolean ableToSelectBucket = true;
//        mailBrowser.switchTo().defaultContent();
//        mailBrowser.switchTo().frame(0);
//        if (waitUntilBucketVisible("//table/descendant::td/span/descendant::a[.='" + webmailBucket + "']")) {
//            //If sub-buckets are currently hidden expose them so that selenium can access them
//            if (mailBrowser.findElement(By.xpath("//table/descendant::td/span[descendant::a[.='" + webmailBucket + "']]/tt/a")).getText().equals("+")) {
//                mailBrowser.findElement(By.xpath("//table/descendant::td/span[descendant::a[.='" + webmailBucket + "']]/tt/a")).click();
//            }
//        }
//        if (waitUntilBucketVisible("//table/descendant::td/span/descendant::a[.='" + emailNameInList + "' and contains(@href, '" + webmailBucket + "')]")) {
//            mailBrowser.findElement(By.xpath("//table/descendant::td/span/descendant::a[.='" + emailNameInList + "' and contains(@href, '" + webmailBucket + "')]")).click();
//        } else {
//            ableToSelectBucket = false;
//        }
//        return ableToSelectBucket;
//    }
//
//    /**
//     * Wait for the mail bucket to become visible.  This will take up to 60 seconds for the mail server to create and display new buckets
//     *
//     * @author MPLC
//     *
//     * @param xPathLocator Location of the bucket we are waiting for
//     * @return True if found, otherwise false
//     * @throws Exception
//     */
//    private boolean waitUntilBucketVisible(String xPathLocator) throws Exception {
//        long start = System.currentTimeMillis();
//        int waitCount = 0;
//        while (System.currentTimeMillis() - start < 90000) {
//            mailBrowser.switchTo().defaultContent();
//            mailBrowser.switchTo().frame(0);
//            if (isElementPresent(xPathLocator, true, "", mailBrowser)) {
//                if (mailBrowser.findElement(By.xpath(xPathLocator)).isEnabled()) {
//                    return true;
//                }
//            }
//            waitCount += 250;
//            if (waitCount == 1250) {
//                waitCount = 0;
//                logOutOFWebmail();
//                quitWebmail();
//                logIntoWebmail();
//            } else {
//                Thread.sleep(250);
//                mailBrowser.navigate().refresh();
//            }
//        }
//        LOGGER.error("Timed out trying to locate Bucket: " + xPathLocator);
//        return false;
//    }
//
//    /**
//     * Select the email that we want to extract data from.
//     *
//     * @author MPLC
//     *
//     * @throws Exception
//     */
//    private void selectMail() throws Exception {
//        mailBrowser.switchTo().defaultContent();
//        mailBrowser.switchTo().frame(1);
//        if (isElementPresent("//table/descendant::tr/td[5]/descendant::a[.='" + truncatedSubject + "']", mailBrowser)) {
//            mailBrowser.findElement(By.xpath("//table/descendant::tr/td[5]/descendant::a[.='" + truncatedSubject + "']")).click();
//        } else {
//            LOGGER.error("Unable to find the subject that you have specified!");
//            LOGGER.error("Unable to locate e-mail!");
//            verifyTrue(false);
//        }
//    }
//
//    /**
//     * Extract the email data from the email that has been loaded.
//     *
//     * @author MPLC
//     *
//     * @throws Exception
//     */
//    private void getMailData() throws Exception {
//        mailBrowser.switchTo().defaultContent();
//        mailBrowser.switchTo().frame(1);
//        messageSubject = mailBrowser.findElement(By.xpath("//table[3]/tbody/tr[2]/td/table/tbody/tr[1]/td[2]")).getText();
//        messageSentFrom = mailBrowser.findElement(By.xpath("//table[3]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]")).getText();
//        messageDate = mailBrowser.findElement(By.xpath("//table[3]/tbody/tr[2]/td/table/tbody/tr[3]/td[2]")).getText();
//        messageSentTo = mailBrowser.findElement(By.xpath("//table[3]/tbody/tr[2]/td/table/tbody/tr[4]/td[2]")).getText();
//        messagePriority = mailBrowser.findElement(By.xpath("//table[3]/tbody/tr[2]/td/table/tbody/tr[5]/td[2]")).getText();
//        messageBody = mailBrowser.findElement(By.xpath("//table[4]/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td")).getText();
//        if (isElementPresent("//table/descendant::td/b[.='Attachments:']", mailBrowser)) {
//            // There is a blank header row above attchments so take the row count and decrement by 1
//            attachmentCount = getElementCount("//table/descendant::td/b[.='Attachments:']/../../following-sibling::tr/descendant::tr") - 1;
//        }
//        for (int i = 1; i <= getElementCount("//table[4]/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/descendant::a"); i++) {
//            messageLinks.add(i - 1, new HashMap());
//            ((HashMap) messageLinks.get(i - 1)).put(mailBrowser.findElement(By.xpath("//table[4]/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/descendant::a[" + i + "]")).getText(), mailBrowser.findElement(By.xpath("//table[4]/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/descendant::a[" + i + "]")).getAttribute("href"));
//        }
//    }
//
//    /**
//     * Log out of webmail and shut down the browser instance used to collect the e-mail.
//     *
//     * @author MPLC
//     *
//     * @throws Exception
//     */
//    private void logOutOFWebmail() throws Exception {
//        mailBrowser.switchTo().defaultContent();
//        mailBrowser.switchTo().frame(1);
//        mailBrowser.findElement(By.xpath("//a[contains(.,'Sign Out')]")).click();
//        mailBrowser.findElement(By.xpath("//a[.='Click here to log back in.']")).click();
//    }
//
//    /**
//     * Close down the browser used for webmail and clean up memory
//     *
//     * @author MPLC
//     *
//     * @throws Exception
//     */
//    private void quitWebmail() {
//        try {
//            mailBrowser.quit();
//            mailBrowser = null;
//        } catch (Exception x) {
//            LOGGER.debug("Error: quitEmail () error: " + x.getMessage());
//
//        }
//    }
//
//    /**
//     * Load data from the email.
//     *
//     * @author MPLC
//     *
//     * @param emailUser The email account that the message was sent to
//     * @param emailSubject The subject of the email
//     * @throws Exception
//     */
//    public void loadEmailData(String emailUser, String emailSubject) throws Exception {
//        try {
//            setEmailSentTo(emailUser);
//            setEmailSubject(emailSubject);
//            logIntoWebmail();
//            if (selectBucket()) {
//                selectMail();
//                getMailData();
//            } else {
//                LOGGER.error("Unable to select bucket");
//                throw new Exception();
//            }
//            logOutOFWebmail();
//            quitWebmail();
//        } catch (Exception x) {
//            quitWebmail();
//        }
//    }
//
//    /**
//     * Load data from the email.
//     * (An emailUser must already have been set for this function to work).
//     *
//     * @author MPLC
//     *
//     * @param emailSubject The subject of the email
//     * @throws Exception
//     */
//    public void loadEmailData(String emailSubject) throws Exception {
//        if (emailSentTo.equals("")) {
//            LOGGER.error("You have not supplied an e-mail address that the e-mail was sent to!");
//            LOGGER.error("Unable to locate e-mail!");
//            verifyTrue(false);
//            return;
//        }
//        loadEmailData(emailSentTo, emailSubject);
//    }
}
