import javax.swing.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
public class  FileSender1 extends JPanel implements  ActionListener{
   JFrame frame1;
   JTextField tf3,tf4;
   JButton button,button1,button2,br,hs;
   JLabel heading;
   JPanel panel;
   ButtonGroup b;
   JLabel label1,label2;
   int i;
   Date date;
   JTextField ds;
   String filename,ip,filepath;
   int porttoc1=5555;
   int porttof1=9999;
   public static void main(String[] args){
      new FileSender1();
  }
  public FileSender1(){
	super(new BorderLayout());
    frame1 = new JFrame("SSYR");
    date=new Date();
    heading=new JLabel("SSYR",JLabel.CENTER);
    b=new ButtonGroup();
    button=new JButton("Send");
    button1=new JButton("Recieve");
    button2=new JButton("Cancel");
    hs=new JButton("History");
    label2=new JLabel(" OPERATION ");
    button.setBackground(Color.blue);
    button.setForeground(Color.white);
    button.setFont(new Font("Serif",Font.PLAIN,18));

    button1.setBackground(Color.blue);
    button1.setForeground(Color.white);
    button1.setFont(new Font("Serif",Font.PLAIN,18));

    button2.setBackground(Color.blue);
    button2.setForeground(Color.white);
    button2.setFont(new Font("Serif",Font.PLAIN,18));

    hs.setBackground(Color.blue);
    hs.setForeground(Color.white);
    hs.setFont(new Font("Serif",Font.PLAIN,18));

    heading.setForeground(Color.yellow);
    heading.setBackground(Color.blue);
    heading.setOpaque(true);
    heading.setFont(new Font("Impact", Font.BOLD,40));

    label2.setFont(new Font("Serif", Font.PLAIN, 24));
    label2.setBackground(new java.awt.Color(10,25,10));
    label2.setForeground(new java.awt.Color(210,150,10));
    label2.setOpaque(true);

    panel = new JPanel();
    button.addActionListener(this);
    button1.addActionListener(this);
    button2.addActionListener(this);
    hs.addActionListener(this);
    
    heading.setBounds(10,20,400,80);
    label2.setBounds(10,215,150,50);
    button.setBounds(163,225,70,30);
    button2.setBounds(234,210,87,30);
    button1.setBounds(322,225,91,30);
    hs.setBounds(234, 240, 87,30);

    b.add(button);
    b.add(button1);
    b.add(button2);
    panel.add(heading);
    panel.add(label2);panel.add(hs);
    panel.add(button);panel.add(button1);panel.add(button2);//panel.add(js);
    panel.setBackground(new java.awt.Color(235,235,255));
    panel.setLayout(null);

    frame1.add(panel);
    panel.setBackground(new java.awt.Color(0,0,150));
    frame1.setBounds(450, 30, 437,340);
    frame1.setVisible(true);
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  public void actionPerformed(ActionEvent e){
	if(e.getSource()==button2){
		System.exit(1);
    }
	if(e.getSource()==button){
		  Runtime rt = Runtime.getRuntime();
		  try {
  	        Process p1=rt.exec("netsh wlan set hostednetwork mode=allow");
  	        String s=InetAddress.getLocalHost().getHostName();
  	                p1=rt.exec("netsh wlan set hostednetwork ssid="+s);
  	                p1=rt.exec("netsh wlan set hostednetwork key=ssyr@123");
                    p1 = rt.exec("netsh wlan start hostednetwork ");
                    JOptionPane.showMessageDialog(frame1,
                            "Say Your Friends to connect his device with wifi name: "+s, "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(frame1,
                            "Say Your Friends to enter the wifi password as :ssyr@123", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
               } catch (IOException ew) {
	                JOptionPane.showMessageDialog(frame1,
	    		            "Hotspot not started! : Get Failed", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
  }
		JFrame jf=new JFrame("SSYR <-Sender");
		JPanel p=new JPanel();
		JLabel j2=new JLabel("      Browse Your File  ");
		JTextField tf=new JTextField();
		label1=new JLabel(" IP ADDRESS ");
        br=new JButton("Browse");
        j2.setBounds(65, 100, 250, 30);
        br.setBounds(145,150,100,30);
        tf.setBounds(160,50,200,30);
        j2.setFont(new Font("Serif", Font.PLAIN, 20));
 	    j2.setBackground(new java.awt.Color(10,25,10));
 	    j2.setForeground(new java.awt.Color(210,150,10));
 	    j2.setOpaque(true);
 	    ip=tf.getText();
 	    label1.setFont(new Font("Serif", Font.PLAIN, 24));
     	label1.setBackground(new java.awt.Color(10,25,10));
     	label1.setForeground(new java.awt.Color(210,150,10));
    	label1.setOpaque(true);
    	label1.setBounds(10,40,360,50);
 	    p.add(j2);
    	p.add(br);
    	p.add(tf);
    	p.add(label1);
    	jf.add(p);
    	
    	br.addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			JFileChooser chooser=new JFileChooser(new File(""));
    			chooser.setMultiSelectionEnabled(false);
    			chooser.setVisible(true);
    			chooser.showOpenDialog(frame1);
    			chooser.setBackground(new java.awt.Color(20,50,60));
    		    File file=chooser.getSelectedFile();
    		    filename=chooser.getSelectedFile().getName();
    		    try{
    		    SocketChannel socketChannel= SocketChannel.open();
    		    Socket cs=new Socket(ip,porttoc1);
    		    DataOutputStream outToServer =
    		    	new DataOutputStream(cs.getOutputStream());
    		    String s=filename;
    		    outToServer.write(s.getBytes());
    		    cs.close();
    		    SocketAddress socketAddress = new InetSocketAddress(ip,porttof1);
    		    socketChannel.connect(socketAddress);
    		   
    		    RandomAccessFile aFile = null;
    		    JOptionPane.showMessageDialog(frame1,
                        "File has been sent successfully!", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                aFile = new RandomAccessFile(file, "r");
    		    FileChannel inChannel = aFile.getChannel();
    		    ByteBuffer buffer = ByteBuffer.allocate(1024);
    		    while (inChannel.read(buffer) > 0) {
    		    buffer.flip();
    		    socketChannel.write(buffer);
    		    buffer.clear();
    		    }
    		    socketChannel.close();
    		    aFile.close();
    		    BufferedWriter bw = null;
     			File f=new File("C:\\record.dat");
     	        try {
     	         // APPEND MODE SET HERE
     	         bw = new BufferedWriter(new FileWriter("C:\\record.dat", true));
     	         if (!f.exists()) {
    				f.createNewFile();
    			 }
                 bw.write("<-"+filename+" has been successfully Sent at "+date.toGMTString());
     		     bw.newLine();
     		     bw.flush();
     	         } catch (IOException ioe) {
     	    	    JOptionPane.showMessageDialog(frame1,
                         "Transfer Failed: IOException Occurs", "Message",
                         JOptionPane.INFORMATION_MESSAGE);
     	    	    System.out.println("1");
     	         } finally {                       // always close the file
     		     if (bw != null) try {
     		        bw.close();
     		     } catch (IOException ioe2) {
     			    JOptionPane.showMessageDialog(frame1,
                         "Transfer Failed: IOException Occurs", "Message",
                         JOptionPane.INFORMATION_MESSAGE);
     			    System.out.println("2");
     		     }
   		    }
        }catch(Exception ex){ 
        	   JOptionPane.showMessageDialog(frame1,
                   "Transfer Failed: Exception Occurs", "Message",
                   JOptionPane.INFORMATION_MESSAGE);
        	   System.out.println("3");
	    }
    		}});
        p.setLayout(null);
        p.setBackground(new java.awt.Color(150,0,150));
        jf.setBounds(57, 90, 400, 270);
        jf.setVisible(true);
        }
        if(e.getSource()==button1){
        JFrame j=new JFrame("SSYR:-> Reciever");
		JPanel p=new JPanel();
		JLabel l=new JLabel(" Save files to");
		JButton b=new JButton(" OK ");
        ds=new JTextField();
        p.add(l);
        p.add(b);
        p.add(ds);
        l.setFont(new Font("Serif", Font.PLAIN, 20));
     	l.setBackground(new java.awt.Color(10,25,100));
     	l.setForeground(new java.awt.Color(210,150,10));
     	l.setOpaque(true);
        l.setBounds(10,100,110,30);
        ds.setBounds(140, 100, 200, 27);
        b.setBounds(150, 150, 60, 25);
        j.add(p);
        b.addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    		 filepath=ds.getText();  
    		 try{
    		 JOptionPane.showMessageDialog(frame1,
                     "Say Your Friends to Enter The IPaddress:"+Inet4Address.getLocalHost().getHostAddress(), "Message",
                     JOptionPane.INFORMATION_MESSAGE);
    		 }catch(Exception ex){
    			 JOptionPane.showMessageDialog(frame1,
                         "Sorry, some error occurs:", "Message",
                         JOptionPane.INFORMATION_MESSAGE); 
    		 }
    		  ServerSocket ss=null;
    	      ServerSocketChannel serverSocketChannel = null;
    	      SocketChannel socketChannel = null;
    		  try{
     		    	while(true){
     		    		ss=new ServerSocket(5555);
     	                serverSocketChannel = ServerSocketChannel.open();
     	                serverSocketChannel.socket().bind(new InetSocketAddress(9999));
     	                Socket s=ss.accept();
     	                socketChannel = serverSocketChannel.accept();
     		   BufferedReader inFromClient=new BufferedReader(new InputStreamReader(s.getInputStream()));
               filename=inFromClient.readLine();
     		   String path=filepath+"\\"+filename;
     		    ss.close();
     		    RandomAccessFile aFile = null;
     		    aFile = new RandomAccessFile(path, "rw");
     		    ByteBuffer buffer = ByteBuffer.allocate(1024);
     		    FileChannel fileChannel = aFile.getChannel();
     		    while (socketChannel.read(buffer)> 0) {
     		    buffer.flip();
     		    fileChannel.write(buffer);
     		    buffer.clear();
     		    }
     		    Thread.sleep(1000);
     		    fileChannel.close();
     		    socketChannel.close();
     		    JOptionPane.showMessageDialog(frame1,
                       "File has been Received successfully!", "Message",
                       JOptionPane.INFORMATION_MESSAGE);
     		    BufferedWriter bw = null;
     			File file=new File("C:\\record.dat");
     	      try {
     	         // APPEND MODE SET HERE
     	         bw = new BufferedWriter(new FileWriter("C:\\record.dat", true));
     	        if (!file.exists()) {
    				file.createNewFile();
    			}
             bw.write("<-"+filename+" has been successfully Received at "+date.toGMTString());
     		 bw.newLine();
     		 bw.flush();
     	      } catch (IOException ioe) {
     	    	 JOptionPane.showMessageDialog(frame1,
                         "Transfer Failed: IOException Occurs", "Message",
                         JOptionPane.INFORMATION_MESSAGE);
     	      } finally {                       // always close the file
     		 if (bw != null) try {
     		    bw.close();
     		 } catch (IOException ioe2) {
     			 JOptionPane.showMessageDialog(frame1,
                         "Transfer Failed: IOException Occurs", "Message",
                         JOptionPane.INFORMATION_MESSAGE);
     		 }
   		    }
                 System.exit(1); 
     		    	}
     		  }
     		    catch(Exception et)
     	        {
     		    	 JOptionPane.showMessageDialog(frame1,
                             "Transfer Failed: IOException Occurs", "Message",
                             JOptionPane.INFORMATION_MESSAGE);
                 }}
        });
        p.setLayout(null);
        p.setBackground(new java.awt.Color(150,0,150));
        j.setBounds(879, 90, 390, 270);
        j.setVisible(true);
    }
       if(e.getSource()==hs){
	JFrame fr=new JFrame("History");
	JPanel jp=new JPanel();
	JTextArea ta=new JTextArea("                        History");
	JScrollPane js=new JScrollPane(ta);
	ta.setBackground(new java.awt.Color(150,200,100));
	ta.setText("");
	ta.setEditable(false);
	
    BufferedReader br=null;
    try{
    	br=new BufferedReader(new FileReader("C:\\record.dat"));
    	String line;
    	while((line=br.readLine())!=null){
    		ta.append(line+"\n");
    	}
    }catch(Exception ex){
    	 JOptionPane.showMessageDialog(frame1,
                 "Transfer Failed: Exception Occurs", "Message",
                 JOptionPane.INFORMATION_MESSAGE);
    }
	js.setBounds(10,10,515,270);
	js.setBackground(Color.blue);
	jp.add(js);
	jp.setBackground(Color.green);
	jp.setLayout(null);
	fr.add(jp);
	fr.setBounds(400,360,550,330);
	fr.setVisible(true);
     }
}
}

