package com.mplayer.web.domain;

import java.sql.Date;
import java.sql.Timestamp;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"bytes"})
public class Media 
{
    private int id;
    private String songName;
    private String fileName;
    private byte[] songData;
    private Long songSize;
    private Long createDate;
    private Long changeDate;
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public void setSongName(String songName){
        this.songName = songName;
    }
    public String getSongName(){
        return this.songName;
    }
    
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return this.fileName;
    }
    
    public void setSongData(byte[] songData){
        this.songData = songData;
    }
    public byte[] getSongData(){
        return this.songData;
    }
    
    public void setSongSize(Long songSize){
        this.songSize = songSize;
    }
    public Long getSongSize(){
        return this.songSize;
    }
    
    public Long getCreateDate(){
        return this.createDate;
    }
    
    public Long getChangeDate(){
        return this.changeDate;
    }
    
    public String getCreateDateAsDate(){
        return new Date(this.createDate * 1000).toString();
    }
    
    public Date getChangeDateAsDate(){
        return new Date(this.changeDate * 1000);
    }
 
    @Override
    public String toString() {
        return songName;
    }
}
