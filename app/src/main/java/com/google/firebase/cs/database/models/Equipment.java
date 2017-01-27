package com.google.firebase.cs.database.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Equipment {

    public String uid;
    public String author;
    public String title;
    public String body;
    public String location;
    public String serial;
    public String certid;
    public Map<String, Boolean> stars = new HashMap<>();

    public Equipment() {
        // Default constructor required for calls to DataSnapshot.getValue(Equipment.class)
    }

    public Equipment(String uid, String author, String title, String body, String location, String serial) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.location = location;
        this.serial = serial;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {

        String titfull = title;
        String bdfull = body;
        String locfull = location;
        String modtrim = titfull.substring(0,3);
        String bdtrim = bdfull.substring(0,3);
        String loctrim = locfull.substring(0,3);
        certid = modtrim+"/"+bdtrim+"/"+loctrim+"/"+serial;


        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("location", location);
        result.put("serial", serial);
        result.put("ID", certid);
        return result;
    }
    // [END post_to_map]

}
// [END post_class]
