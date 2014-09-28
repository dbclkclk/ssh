package ssh.test.ssh.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service
public class SSHService implements SSHServiceInterface {

	@Value("${connect.host}")
	String host;
	
	@Value("${connect.port}")
	int port;
	
	@Value("${connect.user}")
	String user = "groupby";
	
	@Value("${connect.password}")
	String password;
	
	@Value("${ssh.key.path}")
	String filePath;
	
	@Value("${remote.host}")
	String remoteHost;
	
	@Value("${remote.port}")
	int remotePort;
	
	@Value("${remote.forward.port}")
	int remoteForwardPort;
	
	private ThreadPoolTaskExecutor exector;
	
	Session session;

	@Autowired
	public void setExector(ThreadPoolTaskExecutor exector) {
		this.exector = exector;
	}
	public void connect()
	{
		if(this.exector.getActiveCount()<=0)
			try {
				
				this.initiate();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	public void disconnect()
	{
		if(this.session!=null)
		{
			if(this.session.isConnected())
				this.session.disconnect();
		}
	}
	
	protected void initiate() throws FileNotFoundException, JSchException
	{
		JSch jsch=new JSch();
		java.util.Properties config = new java.util.Properties(); 
		config.put("StrictHostKeyChecking", "no");
		this.disconnect();
		this.session=jsch.getSession(this.user, this.host, this.port);
		this.session.setConfig(config);
		this.session.setPassword(password);
		this.session.connect();
		//session.setPortForwardingR(this.remoteForwardPort,this.remoteHost,this.remotePort);
		this.session.setPortForwardingR(this.remoteForwardPort, this.remoteHost, this.remotePort);
	}
	
}
