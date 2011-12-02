package com.lazerycode.ebselen.handlers.userhandler;

import com.lazerycode.ebselen.EbselenConfiguration.UserRole;
import com.lazerycode.ebselen.handlers.UserHandler;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class UserHandlerTest {

    @Test
    public void testAddingARole() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        userObject.addRole(UserRole.ADMINISTRATOR);
        assertThat(userObject.getRoles().get(0), is(equalTo(UserRole.ADMINISTRATOR)));
    }

    @Test
    public void testRemovingARole() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        userObject.addRole(UserRole.ADMINISTRATOR);
        assertThat(userObject.getRoles().get(0), is(equalTo(UserRole.ADMINISTRATOR)));
        userObject.removeRole(UserRole.ADMINISTRATOR);
    }

    @Test
    public void testRemovingAllRoles() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        userObject.addRole(UserRole.ADMINISTRATOR);
        userObject.addRole(UserRole.STANDARD_USER);
        assertThat(userObject.getRoles().size(), is(equalTo(2)));
        userObject.removeAllRoles();
        assertThat(userObject.getRoles().size(), is(equalTo(0)));
    }

    @Test
    public void testThatUserHasARole() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        userObject.addRole(UserRole.ADMINISTRATOR);
        assertThat(userObject.doesUserHaveRole(UserRole.ADMINISTRATOR), is(equalTo(true)));
    }

    @Test
    public void testThatUserDoesNotHaveARole() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        userObject.addRole(UserRole.STANDARD_USER);
        assertThat(userObject.doesUserHaveRole(UserRole.ADMINISTRATOR), is(equalTo(false)));
    }

    @Test
    public void testEnvironmentalHashAppliedToUsername() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        assertThat(userObject.getUserName(), is(equalTo("Bill_FFOW")));
    }

    @Test
    public void testRawUsernameIsCorrect() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        assertThat(userObject.getRawUsername(), is(equalTo("Bill")));
    }

    @Test
    public void testModifyUsernameIsCorrect() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        userObject.setUserName("William");
        assertThat(userObject.getRawUsername(), is(equalTo("William")));
        assertThat(userObject.getUserName(), is(equalTo("William_FFOW")));
    }

    @Test
    public void testEnvironmentalHashAppliedToEmail() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        assertThat(userObject.getEmail(), is(equalTo("Bill_FFOW@lazeryattack.com")));
    }

    @Test
    public void testRawEmailIsCorrect() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "@lazeryattack.com", "FFOW");
        assertThat(userObject.getRawEmail(), is(equalTo("Bill@lazeryattack.com")));
    }

    @Test
    public void testDefaultEmailDomainIsSet() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "FFOW");
        assertThat(userObject.getRawEmail(), is(equalTo("Bill@test.com")));
    }

    @Test
    public void testSetDescription() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "FFOW");
        String description = "This is my description";
        userObject.setDescription(description);
        assertThat(userObject.getDescription(), is(equalTo(description)));
    }

    @Test
    public void testSetFirstName() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "FFOW");
        String firstName = "Willian";
        userObject.setFirstName(firstName);
        assertThat(userObject.getFirstName(), is(equalTo(firstName)));
    }

    @Test
    public void testSetLastName() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "FFOW");
        String lastName = "Buthton-Smith";
        userObject.setLastName(lastName);
        assertThat(userObject.getLastName(), is(equalTo(lastName)));
    }

    @Test
    public void testSetPassword() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "FFOW");
        String password = "Password1";
        userObject.setPassword(password);
        assertThat(userObject.getPassword(), is(equalTo(password)));
    }

    @Test
    public void testSetTelephoneNumber() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "FFOW");
        String telephoneNumber = "0844 6751122";
        userObject.setTelephoneNumber(telephoneNumber);
        assertThat(userObject.getTelephoneNumber(), is(equalTo(telephoneNumber)));
    }

    @Test
    public void testSetUserID() throws Exception {
        UserHandler userObject = new UserHandler("Bill", "FFOW");
        String userID = "125";
        userObject.setUserID(userID);
        assertThat(userObject.getUserID(), is(equalTo(userID)));
    }

}
