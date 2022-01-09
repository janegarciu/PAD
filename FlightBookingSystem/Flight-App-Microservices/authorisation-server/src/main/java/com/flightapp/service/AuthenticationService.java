package com.flightapp.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class AuthenticationService {

    public static final String CLIENT_ID = "72pdttek853n3venim8sn02noj";
    private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    public AuthenticationService(final AWSCognitoIdentityProvider awsCognitoIdentityProvider) {
        this.awsCognitoIdentityProvider = awsCognitoIdentityProvider;
    }

    public String login(final String username, final String password) {

        final var authParameters = new HashMap<String, String>();
        authParameters.put("USERNAME", username);
        authParameters.put("PASSWORD", password);

        final var request = new InitiateAuthRequest();
        request.setAuthFlow(AuthFlowType.USER_PASSWORD_AUTH);
        request.setClientId(CLIENT_ID);
        request.setAuthParameters(authParameters);

        log.info("Authenticating user with username {} and password {}", username, password);

        try {
            final var initiateAuthResult = awsCognitoIdentityProvider.initiateAuth(request);
            final var authenticationResult = initiateAuthResult.getAuthenticationResult();
            return authenticationResult.getIdToken();
        } catch (NotAuthorizedException exception) {
            log.info("Unable to authenticate user with username {} because of {}",username,exception.getMessage());
            return "Invalid username or password";
        }
    }

    public String signUp(final String username, final String password) {

        final var signUpRequest = new SignUpRequest();
        signUpRequest.setClientId(CLIENT_ID);
        signUpRequest.setUsername(username);
        signUpRequest.setPassword(password);

        try {
            awsCognitoIdentityProvider.signUp(signUpRequest);
        } catch (UsernameExistsException exception) {
            return "The provided username already exists";
        }
        return "Please confirm your account";
    }


    public String confirmSignUp(final String username, final String confirmationCode) {
        final var confirmSignUpRequest = new ConfirmSignUpRequest();
        confirmSignUpRequest.setClientId(CLIENT_ID);
        confirmSignUpRequest.setUsername(username);
        confirmSignUpRequest.setConfirmationCode(confirmationCode);
        try {
            final var confirmSignUpResult = awsCognitoIdentityProvider.confirmSignUp(confirmSignUpRequest);
            System.out.println(confirmSignUpResult);
        } catch (CodeMismatchException | ExpiredCodeException exception) {
            return "Invalid code";
        }
        return "User was confirmed";
    }
}
