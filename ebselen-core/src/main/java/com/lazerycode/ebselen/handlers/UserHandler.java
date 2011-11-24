/*
Copyright 2010-2012 Ardesco Solutions - http://www.ardescosolutions.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.lazerycode.ebselen.handlers;

import java.util.*;

import com.lazerycode.ebselen.EbselenConfiguration;

public class UserHandler implements EbselenConfiguration {

    private ArrayList<UserRole> roles = new ArrayList<UserRole>();
    private String environmentalHash;
    private String defaultDomain;
    private String rawEmailAddress;
    private String userName;
    private String rawUserName;
    private String firstName;
    private String lastName;
    private String password;
    private String description;
    private String telephoneNumber;
    private String userID;
    private String emailAddress;
    private boolean emailNotify = false;

    public UserHandler(String name, String defaultEmailDomain, String environmentalHash) throws Exception {
        setUserName(name);
        setEmailDomain(this.defaultDomain);
    }

    /**
     * Gets the Email notification status (true sendSoapMessage an email out on generation, false don't)
     *
     * @return boolean true or false
     * @throws Exception
     */
    public boolean getEmailNotificationStatus() throws Exception {
        return this.emailNotify;
    }

    /**
     * Sets the Email notification status (true sendSoapMessage an email out on generation, false don't)
     *
     * @param notify true or false
     * @throws Exception
     */
    public void setEmailNotificationStatus(boolean notify) throws Exception {
        this.emailNotify = notify;
    }

    /**
     * Sets the default e-mail domain to use when creating an user email address without specifying a domain
     * This will prepend the domain with @ if not supplied
     *
     * @param value
     * @throws Exception
     */
    public void setEmailDomain(String value) throws Exception {
        if (!value.startsWith("@")) {
            this.defaultDomain = "@" + value;
        } else {
            this.defaultDomain = value;
        }
    }

    /**
     * Return the default e-mail domain (always starts with @
     *
     * @return
     * @throws Exception
     */
    public String getEmailDomain() throws Exception {
        return this.defaultDomain;
    }

    /**
     * <h2>Sets the UserHandler Email</h2>
     * <ul>
     * <li>Automatically appends the defaultDomain</li>
     * <li>Automatically appends an environmental variable to e-mailUser</li>
     * </ul>
     *
     * @param emailUser The part of the e-mail before the @ sign
     * @throws Exception
     */
    public void setEmail(String emailUser) throws Exception {
        setEmail(emailUser, this.defaultDomain, false);
    }

    /**
     * <h2>Sets the UserHandler Email</h2>
     * <ul>
     * <li>Automatically appends an environmental variable to e-mailUser</li>
     * </ul>
     *
     * @param emailUser   The part of the e-mail before the @ sign
     * @param emailDomain The e-mail domain (@ will be pre-pended if not supplied)
     * @throws Exception
     */
    public void setEmail(String emailUser, String emailDomain) throws Exception {
        setEmail(emailUser, emailDomain, false);
    }

    /**
     * <h2>Sets the UserHandler Email</h2>
     *
     * @param emailUser        The part of the e-mail before the @ sign
     * @param emailDomain      The e-mail domain (@ will be pre-pended if not supplied)
     * @param doNotApplyEnvVar Appends an environmental variable to e-mailUser if true
     * @throws Exception
     */
    public void setEmail(String emailUser, String emailDomain, boolean doNotApplyEnvVar) throws Exception {
        if (!emailDomain.startsWith("@")) {
            emailDomain = "@" + emailDomain;
        }
        this.rawEmailAddress = emailUser + emailDomain;
        if (doNotApplyEnvVar) {
            this.emailAddress = rawEmailAddress;
        } else {
            this.emailAddress = emailUser + this.environmentalHash + emailDomain;
        }
    }

    /**
     * Return the email address associated with this user object
     *
     * @return Full e-mail address
     * @throws Exception
     */
    public String getEmail() {
        return this.emailAddress;
    }

    /**
     * Return the raw email address
     * (The email address without an environmental variable applied)
     *
     * @return Raw e-mail address
     * @throws Exception
     */
    public String getRawEmail() throws Exception {
        return this.rawEmailAddress;
    }

    /**
     * Sets the UserHandler Username (Automatically applies an env var to support paralell runs)
     *
     * @param username
     */
    public final void setUserName(String username) {
        this.rawUserName = username;
        this.userName = username + this.environmentalHash;
    }

    /**
     * Gets the users current Username
     *
     * @return get the username associated with this user
     * @throws Exception
     */
    public String getUserName() throws Exception {
        return this.userName;
    }

    /**
     * Gets the raw username associated with user
     * (the username without and environmental variable applied)
     *
     * @return
     * @throws Exception
     */
    public String getRawUsername() throws Exception {
        return this.rawUserName;
    }

    /**
     * Sets the UserHandler FirstName
     *
     * @param firstname
     * @throws Exception
     */
    public void setFirstName(String firstname) throws Exception {
        this.firstName = firstname;
    }

    /**
     * gets the UserHandler pFirstName
     *
     * @return the FirstName for the Current UserHandler
     * @throws Exception
     */
    public String getFirstName() throws Exception {
        return this.firstName;
    }

    /**
     * Sets the UserHandler LastName
     *
     * @param lastname
     * @throws Exception
     */
    public void setLastName(String lastname) throws Exception {
        this.lastName = lastname;
    }

    /**
     * Gets the UserHandler pLastName
     *
     * @return the LastName for the Current UserHandler
     * @throws Exception
     */
    public String getLastName() throws Exception {
        return this.lastName;
    }

    /**
     * Sets the UserHandler Password
     *
     * @param value
     * @throws Exception
     */
    public void setPassword(String value) throws Exception {
        this.password = value;
    }

    /**
     * Gets the UserHandler pPassword
     *
     * @return the Password for the Current UserHandler
     * @throws Exception
     */
    public String getPassword() throws Exception {
        return this.password;
    }

    /**
     * Sets the UserHandler Description
     *
     * @param value
     * @throws Exception
     */
    public void setDescription(String value) throws Exception {
        this.description = value;
    }

    /**
     * Gets the UserHandler Description
     *
     * @return Description of user
     * @throws Exception
     */
    public String getDescription() throws Exception {
        return this.description;
    }

    /**
     * Sets the UserHandler TelephoneNumber
     *
     * @param value
     * @throws Exception
     */
    public void setTelephoneNumber(String value) throws Exception {
        this.telephoneNumber = value;
    }

    /**
     * Gets the UserHandler TelephoneNumber
     *
     * @return Telephone number in String format
     * @throws Exception
     */
    public String getTelephoneNumber() throws Exception {
        return this.telephoneNumber;
    }

    /**
     * Sets the UserHandler UserID
     * (This is assuming that your website has a unique user ID you may want to store.)
     * (Sometimes stored as int and sometimes as guid hence type String.)
     *
     * @param uid
     * @throws Exception
     */
    public void setUserID(String uid) throws Exception {
        this.userID = uid;
    }

    /**
     * Gets the UserHandler UserID
     *
     * @return User ID
     * @throws Exception
     */
    public String getUserID() throws Exception {
        return this.userID;
    }

    /**
     * Provide a ArrayList of roles to set (must be an ArrayList of UserRole enums)
     *
     * @param roleList ArrayList of UserRole.<userRole>
     * @throws Exception
     */
    public void setRoles(ArrayList<UserRole> roleList) throws Exception {
        this.roles = roleList;
    }

    /**
     * Returns an ArrayList full of type <UserRole> roles
     *
     * @return
     * @throws Exception
     */
    public ArrayList<UserRole> getRoles() throws Exception {
        return this.roles;
    }

    /**
     * Add a role
     *
     * @param role
     * @throws Exception
     */
    public void addRole(UserRole role) throws Exception {
        this.roles.add(role);
    }

    /**
     * Remove a role
     *
     * @param role
     * @throws Exception
     */
    public void removeRole(UserRole role) throws Exception {
        this.roles.remove(role);
    }

    /**
     * Clear out roles
     *
     * @throws Exception
     */
    public void removeAllRoles() throws Exception {
        this.roles.clear();
    }

    /**
     * Find out if a user has the specified role
     *
     * @param role
     * @return boolean
     * @throws Exception
     */
    public boolean doesUserHaveRole(UserRole role) throws Exception {
        return this.roles.contains(role);
    }

    /**
     * Find out if a user has the specified role
     * (This function is case insensitive)
     *
     * @param role
     * @return boolean
     * @throws Exception
     */
    public boolean doesUserHaveRole(String role) throws Exception {
        for (UserRole roleType : UserRole.values()) {
            if (roleType.toString().toLowerCase().equals(role.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
