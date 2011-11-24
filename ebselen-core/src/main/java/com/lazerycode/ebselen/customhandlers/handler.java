/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lazerycode.ebselen.customhandlers;

/**
 *
 * @author Mark Collin
 */
public interface handler {
    public static enum availableHandlers {

        XMLHandler, fileDownloader, fileUpload, sThree, squirrelMail, validateExcelReport
    }
    
    sThree s3 = new sThree();
    
}
