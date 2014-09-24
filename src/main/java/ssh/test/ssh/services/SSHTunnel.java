package ssh.test.ssh.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service
public class SSHTunnel {

	@Value("${connect.host}")
	String host;
	
	@Value("${connect.port}")
	int port;
	
	@Value("${connect.user}")
	String user = "groupby";
	
	@Value("${connect.password}")
	String password = "gbi123gbi";
	
	@Value("${ssh.key.path}")
	String filePath;
	
	@Value("${remote.host}")
	String remoteHost;
	
	@Value("${remote.port}")
	int remotePort;
	
	@Value("${remote.forward.port}")
	int remoteForwardPort;
	
	public void connect() throws JSchException, FileNotFoundException
	{
		FileInputStream hostFile = new FileInputStream(""+filePath);
		JSch jsch=new JSch();
		jsch.setKnownHosts(hostFile);
		Session session=jsch.getSession(this.user, this.host, this.port);
		session.setPassword(password);
		session.connect();
		session.setPortForwardingR(this.remoteForwardPort,this.remoteHost,this.remotePort);
		
		
	}
}
