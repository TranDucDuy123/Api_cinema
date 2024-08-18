package com.example.cinema.services;

import com.example.cinema.dtos.request.AuthenticationRequest;
import com.example.cinema.dtos.request.IntrospectRequest;
import com.example.cinema.dtos.response.AuthenticationResponse;
import com.example.cinema.dtos.response.IntrospectResponse;
import com.example.cinema.entities.User;
import com.example.cinema.exceptions.AppException;
import com.example.cinema.exceptions.ErrorCode;
import com.example.cinema.repositories.RoleRepository;
import com.example.cinema.repositories.UserRepository;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.annotations.Filter;
import org.hibernate.validator.internal.constraintvalidators.hv.ParameterScriptAssertValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

//    key
    @NonFinal
//    @Value("${jwt.signerKey}")
    protected static final String SINGER_KEY ="sZ23z2HCJbBarooMFcwW2A4eIqfJ9p04hPhRl6fp+0/BVWBQI3CmGebw7fnWftca";

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();

        JWSVerifier verifier = new MACVerifier(SINGER_KEY.getBytes());

        SignedJWT signedJwt = SignedJWT.parse(token);

        Date expiryTime = signedJwt.getJWTClaimsSet().getExpirationTime();

        var verified = signedJwt.verify(verifier);

        return  IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticatied = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticatied)
            throw new AppException(ErrorCode.UNAUTHENTICATED_EXCEPTION);
        var token = genarateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
//
    String genarateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date issueTime = new Date();
        Date expirationTime = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getId().toString())
                .issuer("cinema.com")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS )))
                /*custom claim*/
                .claim("username", user.getUsername()) // Thêm username
                .claim("phone", user.getPhone())
                .claim("scope",buildSope(user))
                .build();
        Payload payload = new Payload((jwtClaimsSet.toJSONObject()));

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
            var token =jwsObject.serialize();

            return token;
        }catch (Exception e){
            System.out.println("cannot create token"+ e);
            throw new RuntimeException(e);
        }
    }


//SCOPE
    private String buildSope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> stringJoiner.add(role.getName()));
        return stringJoiner.toString();
    }


}

