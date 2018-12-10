
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class ImageViewer {
    /**
     * Creates a JFrame viewer with an image and if the result is a cow
     * @param path the file path for the image
     * @param isCow if the image is a cow
     * @throws IOException
     */
    public ImageViewer(String path, boolean isCow) throws IOException
    {



        BufferedImage srcImg=ImageIO.read(new File(path));
        Image resize = srcImg.getScaledInstance (640,480,0); // Resize to make it a consistent size
        BufferedImage img = new BufferedImage (640,480,BufferedImage.TYPE_INT_RGB);
        img.getGraphics ().drawImage (resize,0,0,null);
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(800,800);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        JLabel text = new JLabel();
        if(isCow){ text.setText("This is a cow");}else{text.setText("This is not a cow");}
        text.setFont(new Font("Courier", Font.BOLD, 75));
        frame.add(lbl);
        frame.add(text);
        frame.setTitle("Cow Tester");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
