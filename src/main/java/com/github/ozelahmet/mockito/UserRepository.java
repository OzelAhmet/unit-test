package com.github.ozelahmet.mockito;

/**
 * This annotation can be added to prevent this class to be mocked. In order to do this, in <code>build.gradle</code>,
 * dependency type must be changed to <code>implentation</code>.
 */
// @DoNotMock
public interface UserRepository {

    User getUser(String username);

}
