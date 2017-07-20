package com.qa.cinema.business;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class CDServiceMapImpl implements CDService {

    @Override
    public String getAllCDs() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String createCD(String cd) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String updateCD(Long id, String cd) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteCD(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}