package com.mplayer.web.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringJoiner;
import javax.servlet.http.HttpServletRequest;

public class SelectLimitation 
{
    private String search;
    private String mediaid;
    private String songname;
    private String filename;
    private String minsize;
    private String maxsize;
    private String createdatefrom;
    private String createdateto;
    private String changedatefrom;
    private String changedateto;
    private String searchSQL;
    private int page;
    private final int limit = 10;
    private int offset;
    
    public SelectLimitation(HttpServletRequest request)
    {
        this.page = 0;
        this.offset = 0;
        this.search = request.getParameter("search");
        this.mediaid = request.getParameter("mediaid");
        this.songname = request.getParameter("songname");
        this.filename = request.getParameter("filename");
        this.minsize = request.getParameter("minsize");
        this.maxsize = request.getParameter("maxsize");
        this.createdatefrom = request.getParameter("createdatefrom");
        this.createdateto = request.getParameter("createdateto");
        this.changedatefrom = request.getParameter("changedatefrom");
        this.changedateto = request.getParameter("changedateto");
    }

    public String getSearchREF() throws java.text.ParseException{
        StringJoiner searchREF = new StringJoiner("&");
        addSearchStr(searchREF, "mediaid", this.mediaid);
        addSearchStr(searchREF, "songname", this.songname);
        addSearchStr(searchREF, "filename", this.filename);
        addSearchStr(searchREF, "minsize", (this.minsize == null ? "" : this.minsize.replace(" Kb", "")));
        addSearchStr(searchREF, "maxsize", (this.maxsize == null ? "" : this.maxsize.replace(" Kb", "")));
        addSearchStr(searchREF, "createdatefrom", this.createdatefrom);
        addSearchStr(searchREF, "createdateto", this.createdateto);
        addSearchStr(searchREF, "changedatefrom", this.changedatefrom);
        addSearchStr(searchREF, "changedateto", this.changedateto);
        addSearchStr(searchREF, "search", this.search);
        return (searchREF.length() == 0 ? "" : "?" + searchREF.toString());
    }
    
    public void addSearchStr(StringJoiner search, String name, String val){
        if (val != null && !val.isEmpty())
            search.add(name + "=" + val);
    }
    
    public void addSearchDate(StringJoiner search, String name, String val) throws java.text.ParseException {
        if (val != null && !val.isEmpty()){
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Date date = format.parse(val);
            search.add(name + "=" + date.getTime());
        }
    }
        
    public String getMediaid(){
        return mediaid;
    }
    public void setMediaid(String mediaid){
        this.mediaid = mediaid;
    }
    
    public String getSongnameTXT(){
        return songname;
    }
    
    public String getFilenameTXT(){
        return filename;
    }
    
    public String getSongname(){
        return "%" + (songname == null || songname.isEmpty() ? "" : songname) + "%";
    }
    public void setSongname(String songname){
        this.songname = songname;
    }
    
    public String getFilename(){
        return "%" + (filename == null || filename.isEmpty() ? "" : filename) + "%";
    }
    public void setFilename(String filename){
        this.filename = filename;
    }
    
    public String getMinsize(){
        return minsize;
    }
    public void setMinsize(String minsize){
        this.minsize = minsize;
    }
    
    public String getMaxsize(){
        return maxsize;
    }
    public void setMaxsize(String maxsize){
        this.maxsize = maxsize;
    }
    
    public String getCreatedatefrom() throws java.text.ParseException{
        if (this.createdatefrom != null && !this.createdatefrom.isEmpty()){
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Date date = format.parse(this.createdatefrom);
            return "" + date.getTime()/1000;
        }
        return "";
    }
    public void setCreatedatefrom(String createdatefrom){
        
        this.createdatefrom = createdatefrom;
    }
    
    public String getCreatedateto() throws java.text.ParseException{
        if (this.createdateto != null && !this.createdateto.isEmpty()){
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Date date = format.parse(this.createdateto);
            return "" + date.getTime()/1000;
        }
        return "";
    }
    public void setCreatedateto(String createdateto){
        this.createdateto = createdateto;
    }
    
    public String getChangedatefrom() throws java.text.ParseException{
        if (this.changedatefrom != null && !this.changedatefrom.isEmpty()){
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Date date = format.parse(this.changedatefrom);
            return "" + date.getTime()/1000;
        }
        return "";
    }
    public void setChangedatefrom(String changedatefrom){
        this.changedatefrom = changedatefrom;
    }
    
    public String getChangedateto() throws java.text.ParseException{
        if (this.changedateto != null && !this.changedateto.isEmpty()){
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Date date = format.parse(this.changedateto);
            return "" + date.getTime()/1000;
        }
        return "";
    }
    public void setChangedateto(String changedateto){
        this.changedateto = changedateto;
    }
    
    public String getCreatedatefromTXT()
    {
        return createdatefrom;
    }
    public String getCreatedatetoTXT()
    {
        return createdateto;
    }
    public String getChangedatefromTXT()
    {
        return changedatefrom;
    }
    public String getChangedatetoTXT()
    {
        return changedateto;
    }
    
    public SelectLimitation(){
    }
    
    public String getSearch(){
        return "%" + (search == null || search.isEmpty() ? "" : search) + "%";
    }
    public void setSearch(String search){
        this.search = search;
    }
    
    public String getSearchSQL(){
        return searchSQL;
    }
    
    public void NextPage() {
        this.page++;
    }
    
    public int getPage(){
        return this.page;
    }
    public void setPage(int page)
    {
        this.page = page;
        this.offset = page * limit;
    }
    
    public int getLimit(){
        return this.limit;
    }
   
    public int getOffset(){
        return this.offset;
    }
    
    public String getSearchField(){
        return this.search;
    }
}
