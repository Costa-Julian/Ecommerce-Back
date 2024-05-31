package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.controllers.auth.*;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequestDto request);

    AuthenticationResponse register(UserAttributesRequestDto request);

    AuthenticationResponse login(AuthenticationRequestDto request);

    MessageDto changeAccountData(UserNewPasswordDto request);
}
