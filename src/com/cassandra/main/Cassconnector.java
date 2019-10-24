/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cassandra.main;

import com.cassandra.views.PrincipalView;
import com.cassandra.cql.CQL_operations;
import java.text.ParseException;

/**
 *
 * @author loginlock
 */
public class Cassconnector {
    
    public static void main(String[] args) throws ParseException{
        PrincipalView pv = new PrincipalView();
        pv.setVisible(true);
        
        CQL_operations.connection();
    }
}
