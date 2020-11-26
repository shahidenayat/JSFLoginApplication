package com.sparta.shahid.authentication;

import com.sparta.shahid.datastore.UserRepository;
import com.sparta.shahid.entities.User;

import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;
public class InMemoryIdentiityStore implements IdentityStore {
    @Inject
    UserRepository userRepository;
    @Override
    public CredentialValidationResult validate(Credential credential){
        UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
        User user = userRepository.exists(usernamePasswordCredential.getCaller());
        if(user != null){
            if(user.isAdmin()){
                HashSet<String> roles = new HashSet<>();
                roles.add("ADMIN");
                return new CredentialValidationResult(user.getName(),roles);
            }else{
                HashSet<String> roles = new HashSet<>();
                roles.add("USER");
                return new CredentialValidationResult(user.getName(),roles);
            }
        }else{
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }

}
