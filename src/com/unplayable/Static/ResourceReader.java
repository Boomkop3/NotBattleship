package com.unplayable.Static;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {
    private ResourceReader instance;

    private ResourceReader(){

    }

    public ResourceReader getInstance(){
        if (this.instance == null){
            this.instance = new ResourceReader();
        }
        return this.instance;
    }

    public List<String> getResourceDirectory() throws IOException {
        List<String> contents = new ArrayList<>();
        try {
            InputStream in = this.getClass().getResourceAsStream("/Sprites/ships");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));

            String resource;
            while ((resource = buffer.readLine()) != null) {
                contents.add(resource);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return contents;
        }
    }
}
