/*
This is an EV radio desktop application which allows for quick streaming of past shows & links to the site magazine & archive.
It also plays mp3s & wavs, & can stream live from evrn.net

 */
package evrnmediaplayer;


import ddf.minim.*;
import ddf.minim.analysis.FFT;
import static evrnmediaplayer.EVRNMediaPlayer.minim;
import java.applet.AudioClip;
import java.awt.AWTException;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import static java.awt.Cursor.CUSTOM_CURSOR;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Robot;
import static java.awt.SystemColor.DESKTOP;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.APPROVE_SELECTION;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import static javax.swing.Spring.width;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.UIManager.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.jtattoo.plaf.AbstractTheme;
import com.jtattoo.plaf.ColorHelper;
import ddf.minim.spi.AudioRecordingStream;
import java.awt.Color;
import javax.swing.plaf.ColorUIResource;
import uk.co.caprica.vlcj.player.TrackType;
import org.jsoup.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import java.io.IOException;
import org.jsoup.select.Elements;
/**
 *
 * @author Kobevader
 */
public class EVRNMediaPlayer   {

public static Minim minim;
public static AudioPlayer ap;
public static AudioMetaData meta;     
public static int VK_MINUS;
public static AudioPlayer radio;
public static int decreaseVol = KeyEvent.VK_MINUS;
public static AudioRecordingStream rad;
public static MultiChannelBuffer mcb; 
public static EmbeddedMediaPlayerComponent mediaPlayerComponent;
public static Image icon;

     
public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException, URISyntaxException, AWTException {
      
    
    
    try {
            
            // setTheme(String themeName, String licenseKey, String logoString)
            com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme("Small-Font", "INSERT YOUR LICENSE KEY HERE", "EV Radio");
            
            // select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }


      
     
        
        EVRNMediaPlayer listen = new EVRNMediaPlayer(); 
        EVRNMediaPlayer main = new EVRNMediaPlayer();       
        minim = new Minim(listen);
       
         NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\vlc");
         Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

           final JFrame frame3 = new JFrame("Ev Radio");

            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

           
   
        final JFrame frame = new JFrame();        //The following is all related to the GUI, adding menus, menu bars, and items.
        final JFrame frame2 = new JFrame();
  
        Toolkit.getDefaultToolkit().getImage(EVRNMediaPlayer.class.getResource("EVMast.png"));
        
        JLabel background = new JLabel(new ImageIcon(EVRNMediaPlayer.class.getResource(("EVMast.png"))));
        background.setLayout( new BorderLayout() );
        background.setOpaque(false);
        background.setFocusable(false);
        frame.setFocusTraversalKeysEnabled(false);
        frame.setFocusable(true);
        frame.setContentPane( background );
        frame.setVisible(true);
        frame.setEnabled(true);
        frame.setResizable(true);
        
        
        JMenuBar menu = new JMenuBar();
        final JTextArea metainfo = new JTextArea();
        metainfo.setFocusable(false);
  
        final JFileChooser fc = new JFileChooser();
        frame2.setEnabled(true);
        frame2.setSize(400,400);
        
        
        Font f = new Font("Zapfino",  Font.ROMAN_BASELINE,  12);
        UIManager.put("Menu.font", f);
    
        
        
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Magazine");
        JMenu m3 = new JMenu ("Archives");
        JMenu m4 = new JMenu ("Tools");
        JMenu m5 = new JMenu ("About");
  final JMenuItem item2 = new JMenuItem("Open");       
        JMenuItem item1 = new JMenuItem("Listen Live!");
        JMenuItem item3 = new JMenuItem("The Daily Llama");
        JMenuItem item4 = new JMenuItem("Past Shows");
        JMenuItem item5 = new JMenuItem("Author");
        JMenuItem item6 = new JMenuItem("Quote of the Day");
        JMenuItem item7 = new JMenuItem("Stop");
        JMenuItem item8 = new JMenuItem("Anti-Podcast #01 (Featuring Kobevader)");
        JMenuItem item9 = new JMenuItem("Anti-Broadcast (Live from the Shed 8-10-14)");
        JMenuItem item10 = new JMenuItem("Pause");
        JMenuItem item11 = new JMenuItem("Play");
        JMenuItem item12 = new JMenuItem("+ volume");
        final JMenuItem item13 = new JMenuItem("- volume");
        JMenuItem item14 = new JMenuItem("Contact Show");
        JMenuItem itema = new JMenuItem("Current Song");
        JMenuItem itemb = new JMenuItem("Isla Vista Psyop #2 (with Jeff C.)");
        JMenuItem itemc = new JMenuItem("Isla Vista Psyop #1 (WWMS)");
        JMenuItem itemd = new JMenuItem("Kyle & Andrea (Anti-Broadcast 14/2/14)");
        JMenuItem iteme = new JMenuItem("2nd Anniversary Show - Celebrating our Radio Heroes");
       
        
        
        metainfo.setSize(50, 50);
        metainfo.setOpaque(false);
        metainfo.setLineWrap(true);
        metainfo.setEditable(false);
       // Font font = new Font("Baskerville", Font.BOLD, 11);
        Font font = new Font("Bauhaus 93", Font.BOLD, 12);
        metainfo.setFont(font);
        metainfo.setForeground(Color.orange);
      
       
        
        
        frame.setVisible(true);
        frame.setSize(299, 750);
        frame.setResizable(false);
        frame.setTitle("EV Radio Player");
        frame.setJMenuBar(menu);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.add(metainfo);
        frame.addKeyListener(null);

        
        
        
        menu.setBackground(Color.BLACK);
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(m4);
        menu.add(m5);
    

        m1.setForeground(Color.white);
        m1.add(item1);
        m1.add(item2);
        m1.add(item6);
        m1.add(item14);
        m2.setForeground(Color.white);
        m2.add(item3);
        m3.setForeground(Color.white);
        m3.add(item4);
        m3.add(item8);
        m3.add(item9);
        m3.add(itemb);
        m3.add(itemc);
        m3.add(itemd);
        m3.add(iteme);
        m4.setForeground(Color.white);
        m4.add(item11);
        m4.add(item10);
        m4.add(item12);
        m4.add(item13);
        m4.add(item7);      
        m4.add(itema);
        m5.setForeground(Color.white);
        m5.add(item5);
        
        item13.setFocusable(true);
      
        
       
        Image image2 = Toolkit.getDefaultToolkit().createImage("c.jpg");
        final PopupMenu popup = new PopupMenu();
        popup.setLabel("Ev radio media player");
        final TrayIcon trayIcon = new TrayIcon(image2);
        trayIcon.setImageAutoSize(true);
        final SystemTray tray = SystemTray.getSystemTray();
   
       
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
        
        trayIcon.setToolTip("Ev Radio Media Player");
    
  
      
    

    
       
        

        

        
   frame.addKeyListener(new KeyListener () { //Controls increase/decrease volume keyListeners
 
           
           
           
            public void keyTyped(KeyEvent e) {
                
            }
            public void keyPressed(KeyEvent e) {
             
                
                
          int keyCode = e.getKeyCode();
          switch( keyCode ) { 
              
          case KeyEvent.VK_MINUS:
          ap.setGain(ap.getGain()-5);
          break;
              
          case KeyEvent.VK_EQUALS:
          ap.setGain(ap.getGain()+5);
          break;
     
     }
} 
            
            
            
            public void keyReleased(KeyEvent e) {
          
              
            }
           });
           

    
        itemb.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e)
                {
                    metainfo.setText(null);    
                    mediaPlayerComponent.getMediaPlayer().stop();
                    minim.stop();
                    ap = minim.loadFile("http://evrn.net/archives/2014/July/WWMS%202%2026-7-2014.mp3");
                    ap.play();  
                }                    
                
});

        
 itemc.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e)
                {
                    metainfo.setText(null);    
                    mediaPlayerComponent.getMediaPlayer().stop();
                    minim.stop();
                    ap = minim.loadFile("http://evrn.net/archives/2014/July/WWMS%2012-7-2041.mp3");
                    ap.play();  
                }                    
                
});
 
  itemd.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e)
                {
                    metainfo.setText(null);    
            mediaPlayerComponent.getMediaPlayer().stop();
            minim.stop();
            ap = minim.loadFile("http://evrn.net/archives/2014/February/TAB%2013-2-14.mp3");
            ap.play();  
                }                    
                
});
  
   iteme.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e)
                {
                    metainfo.setText(null);    
            mediaPlayerComponent.getMediaPlayer().stop();
            minim.stop();
            ap = minim.loadFile("http://evrn.net/archives/2014/February/The%20Anti-Broadcast%202nd%20Anniversary%20Show.mp3");
            ap.play();  
                }                    
                
});
             
               
           item1.addActionListener(new ActionListener()  { //The crucial action which streams audio data directly from evrn.net
 
            public void actionPerformed(ActionEvent e) 
            {
                metainfo.setText(null);
                 minim.stop();
                 frame3.setContentPane(mediaPlayerComponent);

            frame3.setLocation(100, 100);
            frame3.setSize(20, 20);
            frame3.setVisible(true);
            

            mediaPlayerComponent.getMediaPlayer().playMedia("http://37.187.193.36:8104/");                    
            frame3.setVisible(false);
            
             Document doc;
                try {
                     doc = Jsoup.connect("http://37.187.193.36:8104/index.html").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
                     String title = doc.title();
                     Element body = doc.body();
                     Elements bTags  =  body.getElementsByTag("b");
   
      
               for(Element i : bTags){
          
                   if(i.toString().contains("-") && !i.toString().contains("Nullsoft")){
     
                      System.out.println(i.text());
                      metainfo.setText("Now Playing: " + "\n" + i.text());
                      metainfo.setWrapStyleWord(true);
          
                }
                }
                } catch (IOException ex) {
                    Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
   
    
            
            }
           });
           
           

        
           item2.addActionListener(new ActionListener() { //Brings up the open file dialog (JFileChooser)
 
            public void actionPerformed(ActionEvent e)
            {
               //JFileChooser opens here
         
            fc.setVisible(true);
            fc.setEnabled(true);
            frame2.setVisible(true);
            frame2.add(fc);
            fc.setControlButtonsAreShown(false);
           
        }});

           fc.addActionListener(new ActionListener(){ //Prints metadata to screen when file is chosen & plays the file if it is an mp3 or wav.
            
           public void actionPerformed(ActionEvent e){
                
           
           frame2.setVisible(false);
           String filePath = fc.getSelectedFile().getAbsolutePath();           
           System.out.println(filePath);
           
           if(filePath.endsWith(".mp3")){
              mediaPlayerComponent.getMediaPlayer().stop();
              minim.stop();              
              metainfo.setText(null);
              ap =  minim.loadFile(filePath);
              ap.play();
              meta = ap.getMetaData();              
              System.out.println(" Song: " +  meta.title() + " \nAlbum:   " + meta.album() + " \nArtist:   " +meta.author() + " \nGenre: " + meta.genre() +  "\nYear: " + meta.date());              
              metainfo.append("Now Playing: " + " \nSong:    " +  meta.title() + " \nAlbum: " + meta.album() + " \nArtist:  " +meta.author() + " \nGenre:  " + meta.genre() +  " \nYear:    " + meta.date());
              frame2.setVisible(false);
              metainfo.getLocation();
       
           }
           
              else if(filePath.endsWith(".wav")){
                                           
              mediaPlayerComponent.getMediaPlayer().stop();
              minim.stop();              
              metainfo.setText(null);
              ap =  minim.loadFile(filePath);
              ap.play();
              meta = ap.getMetaData();              
              System.out.println("Song: " +  meta.title() + "\nAlbum: " + meta.album() + "\nArtist: " +meta.author() + "\nGenre: " + meta.genre() +  "\nYear: " + meta.date());              
              metainfo.append("  Now Playing: " + "\n  Song: " +  meta.title() + "\n  Album: " + meta.album() + "\n  Artist: " +meta.author() + "\n  Genre: " + meta.genre() +  "\n  Year: " + meta.date());
              frame2.setVisible(false);
              metainfo.getLocation();
              
             
           }}});

        
        

          item3.addActionListener(new ActionListener() { //Brings up the default browser and loads the Daily Llama archive page
 
          public void actionPerformed(ActionEvent f)
            {
          if(Desktop.isDesktopSupported())
{
           try {
                Desktop.getDesktop().browse(new URI("http://evrn.net/tdl/all/"));
                } catch (IOException ex) {
                Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
}     
               
    }
        
    });
    
          item4.addActionListener(new ActionListener() { //Brings up the default browser and loads the podcast archive page
 
            public void actionPerformed(ActionEvent g)
            {
            if(Desktop.isDesktopSupported())
{
            try {
                  Desktop.getDesktop().browse(new URI("http://evrn.net/shows/all-shows/"));
                } catch (IOException ex) {
                  Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                  Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
}     
                  
    }
        
    });
    
    
    
          item5.addActionListener(new ActionListener() { //Brings up a dialog when 'about' is clicked
 
            public void actionPerformed(ActionEvent h)
            {
            UIManager UI=new UIManager();
            UI.put("OptionPane.background",new ColorUIResource(0,0,0));
            UI.put("OptionPane.foreground", new ColorUIResource(0,0,0));
            URL url = EVRNMediaPlayer.class.getResource("/resources/a.jpg");
            ImageIcon icon = new ImageIcon(url);
            UIManager.put("OptionPane.messageForeground", Color.white);
            JOptionPane.showMessageDialog(null,"Copyright Kobevader 2015\nRadio & mp3/wav player for evrn.net\nDedicated to Howard & Mike" +"\nThank you to the creators of Minim & Vlcj", "About", 1,icon);
            
                
    }
        
    });
    
    
    
          item6.addActionListener(new ActionListener() { //Executes the quote of the day .jar when it is selected under 'File'
 
            public void actionPerformed(ActionEvent h)
            {
           
            try {
                  Runtime.getRuntime().exec("cmd /c start EVRNQuote.jar");
                } catch (IOException ex) {
                  Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
        
    });
    
          item7.addActionListener(new ActionListener() { //ActionListener for stop button
 
            public void actionPerformed(ActionEvent e)
            {
             minim.stop();
             mediaPlayerComponent.getMediaPlayer().stop();
             frame3.setVisible(false);
             metainfo.setText(null);
            }
           });
     
     
          item8.addActionListener(new ActionListener() { //Plays the Anti-Podcast : 1
 
            public void actionPerformed(ActionEvent e)
            {
            metainfo.setText(null);    
            mediaPlayerComponent.getMediaPlayer().stop();
            minim.stop();
            ap = minim.loadFile("http://evrn.net/archives/2014/November/TAP1.mp3");
            ap.play(); 
            }
           });
    
     
         item9.addActionListener(new ActionListener() { //Plays the Anti-broadcast file
 
            public void actionPerformed(ActionEvent e)
            {
            metainfo.setText(null);
            mediaPlayerComponent.getMediaPlayer().stop();
            minim.stop();
            ap = minim.loadFile("http://evrn.net/archives/2014/October/TAB%208-10-2014.mp3");
            ap.play(); 
          
            }
           });
     
     
         item10.addActionListener(new ActionListener() { //ActionListener for pause button
 
            public void actionPerformed(ActionEvent e)
            {
        
            ap.pause(); 
            mediaPlayerComponent.getMediaPlayer().pause();
            }
           });
     
     
        item11.addActionListener(new ActionListener() { //Action Listener for play button
 
            public void actionPerformed(ActionEvent e)
            {
        
            ap.play(); 
          
            }
           });
     
        
        
        item12.addActionListener(new ActionListener() { //ActionListener for increase volume menuItem
 
            public void actionPerformed(ActionEvent e)
            {
            
            ap.setGain(ap.getGain()+5);
            radio.setGain(radio.getGain()+5);
            int currentVol = mediaPlayerComponent.getMediaPlayer().getVolume();
            mediaPlayerComponent.getMediaPlayer().setVolume(currentVol +5);
            }
           });
    
        
        
         item13.addActionListener(new ActionListener() { //ActionListener for decrease volume menuItem
 
            public void actionPerformed(ActionEvent e)
            {
            
            ap.setGain(ap.getGain()-5);
            radio.setGain(radio.getGain()+5);
            int currentVol = mediaPlayerComponent.getMediaPlayer().getVolume();
            mediaPlayerComponent.getMediaPlayer().setVolume(currentVol -5);
            }
           });
         


item14.addActionListener(new ActionListener() { //Opens a form through which an email can be sent to the radio's email address
 
            public void actionPerformed(ActionEvent e)
            {
                

          if(Desktop.isDesktopSupported())
{
          try {
                Desktop.getDesktop().browse(new URI("http://howard.evrn.net/contact/"));
                } catch (IOException ex) {
                Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } 
    
    
}     
          
            }
           });



itema.addActionListener(new ActionListener(){
    
    public void actionPerformed(ActionEvent e){
    
    metainfo.setText(null);
        
    Document doc;
    
                try {
                     doc = Jsoup.connect("http://37.187.193.36:8104/index.html").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
                     String title = doc.title();
                     Element body = doc.body();
                     Elements bTags  =  body.getElementsByTag("b");
   
      
               for(Element i : bTags){
          
                   if(i.toString().contains("-") && !i.toString().contains("Nullsoft")){
     
                      System.out.println(i.text());
                      metainfo.setText("Now Playing: " + "\n" + i.text());
          
                }
                }
                } catch (IOException ex) {
                    Logger.getLogger(EVRNMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
   
}
} );

}



        public String sketchPath(String fileName){ //Following two methods are necessary for minim to work, don't touch
        return fileName;
    }
        public InputStream createInput(String fileName){
       InputStream in2;
try {
     in2 = new FileInputStream(fileName);
     System.out.println("InputStream: created! " + fileName);
     return in2;
     
} catch (Exception ex) {
                        System.out.println("Error Catch Triggered: "+ex);
                        in2 = null;
}
                        return in2;
}
}

        
        


    
