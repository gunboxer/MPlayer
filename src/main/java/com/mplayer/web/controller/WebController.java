package com.mplayer.web.controller;

import com.mplayer.web.domain.Media;
import com.mplayer.web.domain.Number;
import com.mplayer.web.domain.SelectLimitation;
import com.mplayer.web.service.MediaService;
import com.mplayer.web.service.UserService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private MediaService mediaService;
    
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);
    
    private final int leftRight = 3;
    
    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public void header() {

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        Map model = new HashMap();
	if (error != null) {
            logger.info("RequestMethod.GET /login?error");
            model.put("error", "Invalid username and password!");
	}
	if (logout != null) {
            logger.info("RequestMethod.GET /login?logout");
            model.put("msg", "You've been logged out successfully.");
	}
        setHeaderData(model);
        ModelAndView modelAndView = new ModelAndView("Login", model);
		modelAndView.setViewName("login");
        logger.info("RequestMethod.GET /login return modelAndView");
	return modelAndView;
    }
        
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpServletRequest request) {
        logger.info("RequestMethod.GET /login");
        return "redirect:/login";
    }

    @RequestMapping(value="/upload", method = RequestMethod.GET)
    public ModelAndView upload(HttpServletRequest request) {
        try{
            String mediaid = request.getParameter("id");  
            Media media = mediaService.getMediaDetails(mediaid);
            Map model = new HashMap();
            if (media != null) {
                model.put("songName", media.getSongName());
                model.put("id", mediaid);
            }
            setHeaderData(model);
            ModelAndView modelAndView = new ModelAndView("Upload", model);
            modelAndView.setViewName("upload");
            logger.info("RequestMethod.GET /upload return modelAndView");
            return modelAndView;
        } catch (FileNotFoundException ex){
            logger.error("RequestMethod.GET /upload return FileNotFoundException: " + ex.getMessage());
        }
        return new ModelAndView();
    }
    
    @RequestMapping(value="/advancedsearch", method = RequestMethod.GET)
    public ModelAndView advancedserach(HttpServletRequest request) {
        Map model = new HashMap();
        model.put("maxmediasize", this.mediaService.getMaxMediaSize());
        setHeaderData(model);
        SelectLimitation selectLimitation = new SelectLimitation(request);
        model.put("selectLimitation", selectLimitation);
        ModelAndView modelAndView = new ModelAndView("AdvancedSearch", model);
        modelAndView.setViewName("advancedsearch");
        logger.info("RequestMethod.GET /advancedserach return modelAndView");
        return modelAndView;
    }
    
    @RequestMapping(value="/performsearch", method = RequestMethod.POST)
    public String performsearch(HttpServletRequest request,
                                @ModelAttribute(value = "selectLimitation") SelectLimitation selectLimitation){
        try{
            logger.info("RequestMethod.POST /advancedserach return redirect:/medialist" + selectLimitation.getSearchREF());
            return "redirect:/medialist" + selectLimitation.getSearchREF();
        } catch(java.text.ParseException ex){
            logger.error("RequestMethod.GET /upload return ParseException: " + ex.getMessage());
        }
        return "redirect:/medialist";
    }
    
    @RequestMapping(value="/savemedia", method = RequestMethod.POST)
    public String savemedia(HttpServletRequest request, @RequestParam("desc") String desc, @RequestParam("file") MultipartFile file, @RequestParam("id") String id){
        String fileName = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] hashBytes = digest.digest(bytes);
                Media media = new Media();
                media.setFileName(file.getOriginalFilename());
                media.setSongSize(file.getSize()/1024);
                media.setSongData(hashBytes);
                media.setSongName(desc);
                if (id != null && id != "" && tryParseInt(id)){
                    media.setId(Integer.parseInt(id));
                    mediaService.updateMedia(media);
                }
                else{
                    mediaService.insertMedia(media);
                }
                File f = new File(request.getServletContext().getRealPath("/") + "/resources/audio/" + toHexString(hashBytes) + ".mp3");
                if (!f.exists()){
                    f.createNewFile();
                    OutputStream outputStream = new FileOutputStream(f);
                    outputStream.write(bytes);
                    outputStream.close();
                }
                logger.info("RequestMethod.POST /savemedia return redirect:/medialist");
                return "redirect:/medialist";
            } catch (Exception ex) {
                logger.error("RequestMethod.POST /savemedia failed to upload file " + fileName + ": " + ex.getMessage());
                return "You failed to upload " + fileName + ": " + ex.getMessage();
            }
        } else {
            logger.error("RequestMethod.POST /savemedia failed to upload, file  is empty");
            return "Unable to upload. File is empty.";
        }    
    }
        
    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        try{                
            String mediaid = request.getParameter("id");  
            byte[] songData = this.mediaService.getMediaDetails(mediaid).getSongData();
            if (this.mediaService.isMediaUsed(toHexString(songData)) == 1) {
                File f = new File(request.getServletContext().getRealPath("/") + "/resources/audio/" + toHexString(songData) + ".mp3");
                if (f.exists()) {
                    f.delete();
                }
            }
            this.mediaService.deleteMedia(mediaid);
        }catch(Exception ex){
            logger.error("RequestMethod.GET /delete delete file failed: " + ex.getMessage());
        }
        logger.info("RequestMethod.GET /delete return forward:/medialist");
        return "forward:/medialist";
    }   

    @RequestMapping(value={ "/", "/medialist" }, method = RequestMethod.GET)
    public ModelAndView medialist(HttpServletRequest request, HttpServletResponse response) {
        try{
            Map model = new HashMap();
            List mediaList;
            SelectLimitation selectLimitation = new SelectLimitation(request);
            int mediaCount = this.mediaService.getMediaCount(selectLimitation);
            int pagesCount = mediaCount / selectLimitation.getLimit() + (mediaCount % selectLimitation.getLimit() == 0 ? -1 : 0);
            if (!request.getParameterMap().isEmpty()) {
                int currentPage = (Integer) request.getSession().getAttribute("page_number");
                String page = request.getParameter("page");
                if (currentPage > pagesCount)
                    currentPage = pagesCount;
                if (tryParseInt(page)) {
                        currentPage = Integer.parseInt(page) - 1 < 0 ?
                                0 :
                                Integer.parseInt(page) - 1 > pagesCount ?
                                pagesCount:
                                Integer.parseInt(page) - 1;
                } else if ("next".equals(page) && currentPage < (pagesCount)) {
                        currentPage++;
                }
                else if ("previous".equals(page) && currentPage > 0) {
                        currentPage--;
                } else if ("first".equals(page)) {
                        currentPage = 0;
                }
                else if ("last".equals(page)) {
                        currentPage = pagesCount;
                }
                selectLimitation.setPage(currentPage);
            }
            mediaList = this.mediaService.getMediaList(selectLimitation);
            request.getSession().setAttribute("page_number", selectLimitation.getPage());
                    model.put("mediaList", mediaList);

            selectLimitation.NextPage();

            //GOVNOKOD :)
            int start = 1;
            int lastPage = (pagesCount + 1);
            int realPage = selectLimitation.getPage();

            if (realPage - leftRight > 1) {
                if (realPage + leftRight < lastPage) {
                    start = realPage - leftRight;
                } else {
                    start = lastPage - 2 * leftRight;
                }
            }
            int end = (realPage + leftRight < lastPage) ? 
                       realPage + leftRight + (leftRight >= realPage ? leftRight - realPage + 1 : 0) : 
                       lastPage;
            end++;
            //

            List<Number> numbers = new ArrayList<Number>(end - start);
            for (int i=start;i<end;i++){
                numbers.add(new Number(i, i == selectLimitation.getPage()));
            }
            model.put("pagesList", numbers);
            model.put("isFirstPage", selectLimitation.getPage() == 1);
            model.put("isLastPage", selectLimitation.getPage() == (pagesCount) + 1);
            model.put("searchREF", selectLimitation.getSearchREF());
            model.put("search", selectLimitation.getSearchField());
            model.put("advancedsearch", "/advancedsearch" + selectLimitation.getSearchREF());
            model.put("pageREF", "/medialist" + selectLimitation.getSearchREF() + (selectLimitation.getSearchREF() == "" ? "?" : "&") + "page=");
            setHeaderData(model);
            ModelAndView modelAndView = new ModelAndView("MediaList", model);
            modelAndView.setViewName("medialist");
            logger.info("RequestMethod.GET /medialist return modelAndView");
            return modelAndView;
        } catch(Exception ex){
            if (ex instanceof ParseException)
                logger.error("RequestMethod.GET /medialist ParseException: " + ex.getMessage());
            else if (ex instanceof FileNotFoundException)
                logger.error("RequestMethod.GET /medialist FileNotFoundException: " + ex.getMessage());
            else
                logger.error("RequestMethod.GET /medialist Exception: " + ex.getMessage());
        }
        return new ModelAndView();
    }
        
    @RequestMapping(value="/mediadetails", method = RequestMethod.GET)
    public ModelAndView mediadetails(HttpServletRequest request, HttpServletResponse response) {
        try{
            Map model = new HashMap();
            Media media = this.mediaService.getMediaDetails(request.getParameter("id"));
            model.put("media", media);
            model.put("fileName", toHexString(media.getSongData()));
            setHeaderData(model);
            ModelAndView modelAndView = new ModelAndView("MediaDetails", model);
            modelAndView.setViewName("mediadetails");
            logger.info("RequestMethod.GET /mediadetails return modelAndView");
            return modelAndView;
        } catch(FileNotFoundException ex){
            logger.error("RequestMethod.GET /mediadetails FileNotFoundException: " + ex.getMessage());
        }
        return new ModelAndView();
    }
        
    private String toHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0)
            return "";
        char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j*2] = hexArray[v/16];
            hexChars[j*2 + 1] = hexArray[v%16];
        }
        logger.info("toHexString return " + new String(hexChars));
        return new String(hexChars);
    }
                
    private boolean tryParseInt(String value)  
    {  
        try  
        {  
            Integer.parseInt(value);  
            return true;  
        } catch(NumberFormatException nfe)  
        {  
            return false;  
        }  
    }
    
    private boolean isAdministrator(){
        return ((Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    
    private boolean isUser(){
        return ((Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    private String getLogin(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null || user.getClass().toString() == "java.lang.String")
            return "";
        if (user.getClass().toString().contains("org.springframework.security.core.userdetails.UserDetails"))
            return ((UserDetails)user).getUsername();
        if (user.getClass().toString().contains("org.springframework.security.core.userdetails.User"))
            return ((org.springframework.security.core.userdetails.User)user).getUsername();
        return "";
    }
    
    private void setHeaderData(Map model){
        model.put("isAdministrator", isAdministrator());
        model.put("isUser", isUser());
        model.put("login", getLogin());
    }
}