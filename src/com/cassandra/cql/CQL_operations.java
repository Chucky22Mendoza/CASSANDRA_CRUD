/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cassandra.cql;

import com.cassandra.obj.Video_Game;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author loginlock
 */
public class CQL_operations {
    private static Cluster cluster;
    private static Session session;
    
    public static Cluster connect(String node){
        return Cluster.builder().addContactPoint(node).build();
    }
    
    public static void connection(){
        cluster = connect("localhost");
        session = cluster.connect();
        
        session.execute("CREATE KEYSPACE IF NOT EXISTS CassandraCRUD WITH REPLICATION = "
                + "{ 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
        
        session.execute("USE CassandraCRUD");
        
        session.execute("CREATE TABLE IF NOT EXISTS CassandraCRUD.videogames (" +
                        "id uuid PRIMARY KEY," + 
                        "title text," + 
                        "director text," + 
                        "producer text," + 
                        "platform text," + 
                        "release_date text," + 
                        ");");
        
        session.close();
        cluster.close();
    }
    
    public static List<Video_Game> getRecord(){
        cluster = connect("localhost");
        session = cluster.connect();
        List<Video_Game> lista = new ArrayList<>();
        
        ResultSet rs = session.execute("SELECT * FROM CassandraCRUD.videogames;");
        
        Iterator<Row> iterador = rs.iterator();
        while (iterador.hasNext()){
            Row fila = iterador.next();
            String id = fila.getUUID("id").toString();
            String title = fila.getString("title");
            String director = fila.getString("director");
            String producer = fila.getString("producer");
            String platform = fila.getString("platform");
            String release_date = fila.getString("release_date");
            
            Video_Game obj = new Video_Game();
            obj.setId(id);
            obj.setTitle(title);
            obj.setDirector(director);
            obj.setProducer(producer);
            obj.setPlatform(platform);
            obj.setRelease_date(release_date);
            
            lista.add(obj);
        }
        
        session.close();
        cluster.close();
        
        return lista;
    }
    
    public static List<Video_Game> getOneRecord(String id){
        cluster = connect("localhost");
        session = cluster.connect();
        List<Video_Game> lista = new ArrayList<>();
        
        ResultSet rs = session.execute("SELECT * FROM CassandraCRUD.videogames WHERE id=" + id + ";");
        Iterator<Row> iterador = rs.iterator();
        while (iterador.hasNext()){
            Video_Game obj = new Video_Game();
            obj.setId(iterador.next().getString("id"));
            obj.setTitle(iterador.next().getString("title"));
            obj.setDirector(iterador.next().getString("director"));
            obj.setProducer(iterador.next().getString("producer"));
            obj.setPlatform(iterador.next().getString("platform"));
            obj.setRelease_date(iterador.next().getString("release_date"));
            
            lista.add(obj);
        }
        
        session.close();
        cluster.close();
        
        return lista;
    }
    
    public static void addRecord(Video_Game data_game){
        cluster = connect("localhost");
        session = cluster.connect();
        
        String uuid = UUID.randomUUID().toString();
        
        session.execute("INSERT INTO CassandraCRUD.videogames (id,title,director,producer,platform,release_date) values ("
           + uuid + ",'" 
           + data_game.getTitle() + "','"
           + data_game.getDirector() + "','"
           + data_game.getProducer() + "','" 
           + data_game.getPlatform() + "','" 
           + data_game.getRelease_date()+ "');");
        
        session.close();
        cluster.close();
    }
    
    public static void updateRecord(Video_Game data_game, String uuid){
        cluster = connect("localhost");
        session = cluster.connect();
        
        session.execute("UPDATE CassandraCRUD.videogames SET "
           + "title='" + data_game.getTitle() + "',"
           + "director='" + data_game.getDirector() + "',"
           + "producer='" + data_game.getProducer() + "'," 
           + "platform='" + data_game.getPlatform() + "'," 
           + "release_date='" + data_game.getRelease_date() 
           + "' WHERE id=" + uuid + ";");
        
        session.close();
        cluster.close();
    }
    
    public static void deleteRecord(String uuid){
        cluster = connect("localhost");
        session = cluster.connect();
        
        session.execute("DELETE FROM CassandraCRUD.videogames WHERE id=" + uuid + ";");
        
        session.close();
        cluster.close();
    }
}
