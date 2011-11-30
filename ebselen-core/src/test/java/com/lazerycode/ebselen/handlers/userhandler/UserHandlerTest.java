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

}
