package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.Dto.*;

public interface IAuthenticationService {
    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);

    AuthenticationResponseDto register(UserAttributesRequestDto request);

    AuthenticationResponseDto login(AuthenticationRequestDto request);

    MessageDto changeAccountData(UserNewPasswordDto request);
}
