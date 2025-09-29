package com.cep.cep_api.auth.service;
import com.cep.cep_api.auth.domain.*;
import com.cep.cep_api.auth.dto.AuthRequest;
import com.cep.cep_api.auth.dto.RegisterRequest;
import com.cep.cep_api.auth.dto.TokenResponse;
import com.cep.cep_api.auth.repository.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.cep.cep_api.auth.domain.Sessions.session_status_enum.inactive;

@Service
public class AuthService {
    private final PasswordsRepository passRepository;
    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;
    private final TokenRepository tokenRepository;
    private final SessionsRepository sessionsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthService(PasswordsRepository passRepository, UsersRepository repository, ProfileRepository profileRepository, TokenRepository tokenRepository, SessionsRepository sessionsRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usersRepository = repository;
        this.passRepository = passRepository;
        this.profileRepository = profileRepository;
        this.tokenRepository = tokenRepository;
        this.sessionsRepository = sessionsRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public TokenResponse register(RegisterRequest request) throws  Exception{
        System.out.println("entra aca");

                List<Passwords> otherPasswords = passRepository.findAll();
                otherPasswords.forEach(p -> {
                    p.set_active(false);
                });
                passRepository.saveAll(otherPasswords);
                var profile = Profile.builder()
                        .documentNumber(request.document_number())
                        .name(request.name())
                        .lastname(request.lastname())
                        .phoneNumber(request.phone_number())
                        .build();
                var user = Users.builder()
                        .email(request.email())
                        .profile(profile)
                        .build();
                var pass = Passwords.builder()
                        .user(user)
                        .password(passwordEncoder.encode(request.password()))
                        .is_active(true)
                        .build();
                var sess = Sessions.builder()
                        .session_status(inactive)
                        .user(user)
                        .build();
                var savedSession = sessionsRepository.save(sess);
                var savedUser = usersRepository.save(user);
                var savedProfile = profileRepository.save(profile);
                var savedPass = passRepository.save(pass);
                var jwtToken = jwtService.generateToken(user, profile);
                var refreshToken = jwtService.generateRefreshToken(user, profile);
                saveUserToken(savedUser, jwtToken);
                return new TokenResponse(jwtToken, refreshToken);
    }

    public TokenResponse login (final AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        final Users user = usersRepository.findByEmail(request.email()).get();
        final Profile profile = profileRepository.findByUserId(user.getId()).get();
        final String accessToken = jwtService.generateToken(user, profile);
        final String refreshToken = jwtService.generateRefreshToken(user, profile);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }

    private void saveUserToken(Users user, String jwtToken) {
        var token = Tokens.builder()
                .user(user)
                .token(jwtToken)
                .token_type(Tokens.token_type_enum.Bearer)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(final Users user) {
        final List<Tokens> validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getId()));
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }
    public TokenResponse refreshToken(@NotNull final String authentication) {

        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String refreshToken = authentication.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return null;
        }

        final Users user = this.usersRepository.findByEmail(userEmail).orElseThrow();
        final Optional<Profile> profile = this.profileRepository.findByUserId(user.getId());
        final boolean isTokenValid = jwtService.isTokenValid(refreshToken, user);
        if (!isTokenValid) {
            return null;
        }

        final String accessToken = jwtService.generateRefreshToken(user, profile.get());
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return new TokenResponse(accessToken, refreshToken);
    }
}
