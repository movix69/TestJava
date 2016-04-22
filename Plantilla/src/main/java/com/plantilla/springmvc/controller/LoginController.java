package com.plantilla.springmvc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.plantilla.springmvc.configuration.AppConfig;
import com.plantilla.springmvc.model.User;
import com.plantilla.springmvc.service.MyUserDetailsService;
import com.plantilla.springmvc.service.UserService;

@Controller
public class LoginController {
	
	private static final String INTERNAL_FILE="irregular-verbs-list.pdf";
	private static final String EXTERNAL_FILE_PATH="C:/mytemp/SpringMVCHibernateManyToManyCRUDExample.zip";
	

	@RequestMapping(value={"/","/login"}, method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {			
		return "login/login";
	}
	
	@RequestMapping(value={"/index"}, method = RequestMethod.GET)
	public String Index(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "login/index";	
	}
	
	@RequestMapping(value="/logon",method = RequestMethod.POST)
    public ModelAndView onSubmit(@Valid User Reg, BindingResult result)
    {			
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
	            System.out.println(error.toString());
	        }
		}else{
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
			UserDetailsService UserServ = (UserDetailsService)context.getBean("userDetailsService");
			UserDetails user= UserServ.loadUserByUsername(Reg.getEmail());			
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			context.close();			
			  
			  model.addObject("user", getPrincipal());			
			  model.addObject("msg", "You've been logged out successfully.");
			  model.setViewName("login/index");
		}
			  return model;
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
	
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

	/*
	 * Download a file from 
	 *   - inside project, located in resources folder.
	 *   - outside project, located in File system somewhere. 
	 */	
	@RequestMapping(value="/download/{type}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
	
		File file = null;
		
		if(type.equalsIgnoreCase("internal")){
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			file = new File(classloader.getResource(INTERNAL_FILE).getFile());
		}else{
			file = new File(EXTERNAL_FILE_PATH);
		}
		
		if(!file.exists()){
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}
		
		String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType==null){
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}
		
		System.out.println("mimetype : "+mimeType);
		
        response.setContentType(mimeType);
        
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));

        
        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        
        response.setContentLength((int)file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

}
