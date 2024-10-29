package com.task3.service.JWT;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.task3.Entity.User;
import com.task3.Repository.iUserRepository;

@Service
public class JWTServiceImpl implements IJwtService {

	@Value("${jwtKeys.privateKeyPath}")
	private String privatekeyResource;

	@Value("${jwtKeys.publicKeyPath}")
	private String publickeyResource;

	public JWTServiceImpl(iUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	private final iUserRepository userRepo;
	@Override
	public String generarteJwt(Long id) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException {
		PrivateKey privatekey =loadprivatekey(privatekeyResource);
		Optional<User> userid = userRepo.findById(id);
		JWSSigner signer = new RSASSASigner(privatekey);
		Date now = new Date();
		JWTClaimsSet clainsSet = new JWTClaimsSet.Builder()
				.subject(id.toString())
				.subject(userid.toString())
				.issueTime(now)
				.expirationTime(new Date(now.getTime() + 14400000))
				.build();
		SignedJWT signedjwt = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), clainsSet);
		signedjwt.sign(signer);
		return signedjwt.serialize();
	}
	@Override
	public JWTClaimsSet parseJWT(String jwt) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ParseException, JOSEException {
		PublicKey publickey = loadpublicKey(publickeyResource);

		SignedJWT signedjwt = SignedJWT.parse(jwt);
		JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publickey);
		if (!signedjwt.verify(verifier)) {
			throw new JOSEException("invalid signature/ firma invalida");
		}
		JWTClaimsSet claimsSet = signedjwt.getJWTClaimsSet();
		if(claimsSet.getExpirationTime().before(new Date())) {
			throw new JOSEException("EXPIRED TOKEN");

		}
		return claimsSet;
	}

	private PrivateKey loadprivatekey(String privatekeyResource) throws NoSuchAlgorithmException, InvalidKeySpecException {
		privatekeyResource = this.privatekeyResource
				.replace("-----BEGIN PRIVATE KEY-----", "")
				.replace("-----END PRIVATE KEY-----", "")
				.replaceAll("\\s+", "");

		byte[] decoded = Base64.getDecoder().decode(privatekeyResource);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoded));
	}
	private PublicKey loadpublicKey(String publickeyResource) throws NoSuchAlgorithmException, InvalidKeySpecException {
		publickeyResource = this.publickeyResource
				.replace("-----BEGIN PUBLIC KEY-----", "")
				.replace("-----END PUBLIC KEY-----", "")
				.replaceAll("\\s+", "");

		byte[] decoded = Base64.getDecoder().decode(publickeyResource);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(new X509EncodedKeySpec(decoded));
	}
}
