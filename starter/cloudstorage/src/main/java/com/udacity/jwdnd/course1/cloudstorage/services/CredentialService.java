package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentials(Integer userId) {
        return this.credentialMapper.getCredentials(userId);
    }

    public Credential getCredentialById(Integer credentialId) {
        return this.credentialMapper.getCredentialById(credentialId);
    }

    public int addCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);

        String password = credential.getPassword();
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = this.encryptionService.encryptValue(password, encodedKey);

        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);

        return this.credentialMapper.addCredential(credential);
    }

    public int deleteCredential(Integer credentialId) {
        return this.credentialMapper.deleteCredential(credentialId);
    }

    public int editCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);

        String password = credential.getPassword();
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = this.encryptionService.encryptValue(password, encodedKey);

        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);

        return this.credentialMapper.updateCredential(credential);
    }
}
