package com.example.tfa.smarttrial;

/**
 * Created by TFA on 28-May-15.
 */
public class User {


        public final String firstname;
        public final String lastname;
        public final String email;

        public User(String fn, String ln, String em){
                this.email = em;
                this.lastname = ln;
                this.firstname = fn;
            }
    }

