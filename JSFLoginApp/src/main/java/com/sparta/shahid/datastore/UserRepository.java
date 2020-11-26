package com.sparta.shahid.datastore;

import com.sparta.shahid.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
@Named
@ApplicationScoped
public class UserRepository {
    private HashMap<String,User> adminUsers = new HashMap<>();{

        adminUsers.put("shahid",User.createUser("shahid","potato", false,"shahid enayat" ));
        adminUsers.put("ringo", User.createUser("ringo","star",true,"ringo star"));
        adminUsers.put("John",User.createUser("John","Lennon",false,"John Lennon"));
        adminUsers.put("bob",User.createUser("bob","builder",true, "bob The Builder"));

    }

    public User exists(String username){
        if(adminUsers.containsKey(username)){
            return adminUsers.get(username);
        }else{
            return null;
        }

    }

}
