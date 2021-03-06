package ssh.test.ssh;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jcraft.jsch.JSchException;

import ssh.test.ssh.services.SSHServiceInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private SSHServiceInterface sshService;
	
	@Autowired
	public void setTunnel(SSHServiceInterface service) {
		this.sshService = service;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/connect", method = RequestMethod.POST)
	public void connect() throws JSchException, FileNotFoundException
	{
			this.sshService.connect();
	}
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/disconnect", method = RequestMethod.POST)
	public void disconnect() 
	{
		this.sshService.disconnect();
	}
	
}
