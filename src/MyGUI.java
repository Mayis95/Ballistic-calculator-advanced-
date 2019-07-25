import Artilery.*;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;

public class MyGUI extends JFrame
{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton startButton;
    private JPanel DataPanel;
    private JPanel DrawArea;
    private JPanel OutputPanel;
    private JTextField textField4;
    private JTextField textField5;
    private JPanel v0Panel;
    private JPanel anglePanel;
    private JPanel coefPanel;
    private JPanel coef2Panel;
    private JPanel weightPanel;
    private JPanel outputPanel1;
    private JPanel outputPanel2;
    private JPanel outputPanel3;
    private JLabel outLabel1;
    private JLabel outLabel2;
    private JLabel outLabel3;
    private JMenuBar myMenuBar;
    private JMenuItem myMenu;
    private JMenu jMenu;
    private JMenuItem jMenuItem;
    private JMenu settingItem;
    private JMenu mySetting;
    private JCheckBoxMenuItem clearNext;
    private double v0,angle,coef,coef2,weight;
    private D30 d30=new D30();
    private drArea drContent;
    private Vector<Integer> xCoord=new Vector<Integer>();
    private Vector<Integer> yCoord=new Vector<Integer>();

    private class drArea extends JPanel
    {
        public void paint(Graphics g)
        {
            g.setColor(Color.RED);
            int maxX=getWidth();
            int maxY=getHeight();
            int xcoef=20000/maxX;
            int ycoef=20000/maxY;
            int x,y,maxHeight=0, maxLength=0;
            for(int i=0; i<xCoord.size(); i++)
            {
                x=xCoord.elementAt(i)/xcoef+10;
                y=maxY-yCoord.elementAt(i)/ycoef-10;
                if(y==0)
                {
                    x=0;
                    maxHeight=0;
                    maxLength=0;
                }
                if(xCoord.elementAt(i)>maxLength){maxLength=xCoord.elementAt(i);}
                if(yCoord.elementAt(i)>maxHeight){maxHeight=yCoord.elementAt(i);}
                g.drawLine(x,y,x,y);
            }
            outLabel1.setText(""+maxLength+" մետր");
            outLabel3.setText(""+maxHeight+" մետր");
            if(maxHeight==0){outLabel3.setText("-");}
            if(maxLength==0){outLabel1.setText("-");}
            g.setColor(Color.BLACK);
            //Drawing "X" axis
            g.drawLine(10,maxY-10,maxX,maxY-10);
            for(int i=0; i<maxX; i++)
            {
                if(i%100==0)
                {
                    g.drawLine(i+10,maxY-10,i+10,maxY-20);
                    int stlen=(""+i).length()/2;
                    g.drawString(""+i*xcoef,i-5*stlen,maxY);
                }
                if(i%10==0)
                {
                    g.drawLine(i+10,maxY-10,i+10, maxY-15);
                }
            }
            //Drawing "Y" axis
            g.drawLine(10,10,10,maxY);
            for(int i=0; i<maxY; i++)
            {
                if(i%100==0)
                {
                    g.drawLine(10,maxY-i,20,maxY-i);
                    g.drawString(""+i*ycoef,20,maxY-i);
                }
                if(i%10==0)
                {
                    g.drawLine(10,maxY-i,15, maxY-i);
                }
            }
        }
    }

    public MyGUI()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            System.out.println("Error ocցuired");
        }
        setTitle("Հրետանային կրակի դիպուկության հաշվարկի համակարգ");
        myMenuBar=new JMenuBar();
        myMenu=new JMenu("Ուղեցույց");
        myMenuBar.add(myMenu);
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        DataPanel = new JPanel();
        DataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc1;
        gbc1 = new GridBagConstraints();
        gbc1.gridx = GridBagConstraints.RELATIVE;
        gbc1.gridy = GridBagConstraints.RELATIVE;
        gbc1.weightx = 0.0;
        gbc1.weighty = 0.0;
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.anchor=GridBagConstraints.WEST;
        DataPanel.setMinimumSize(new Dimension(150,600));
        DataPanel.setPreferredSize(new Dimension(150,600));
        panel1.add(DataPanel, gbc1);
        DataPanel.setBorder(BorderFactory.createTitledBorder("Տվյալներ"));
        startButton = new JButton("Հաշվարկել");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DataPanel.add(startButton, gbc);
        OutputPanel = new JPanel();
        OutputPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        DataPanel.add(OutputPanel, gbc);
        OutputPanel.setBorder(BorderFactory.createTitledBorder("Արդյունքներ"));
        outputPanel1 = new JPanel();
        outputPanel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        OutputPanel.add(outputPanel1, gbc);
        outputPanel1.setBorder(BorderFactory.createTitledBorder("Հեռավորությունը"));
        outLabel1 = new JLabel();
        outLabel1.setText("-");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        outputPanel1.add(outLabel1, gbc);
        outputPanel2 = new JPanel();
        outputPanel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        OutputPanel.add(outputPanel2, gbc);
        outputPanel2.setBorder(BorderFactory.createTitledBorder("Թռիչքի ժամանակը"));
        outLabel2 = new JLabel();
        outLabel2.setText("-");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        outputPanel2.add(outLabel2, gbc);
        outputPanel3 = new JPanel();
        outputPanel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        OutputPanel.add(outputPanel3, gbc);
        outputPanel3.setBorder(BorderFactory.createTitledBorder("Առավել. բարձր."));
        outLabel3 = new JLabel();
        outLabel3.setText("-");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        outputPanel3.add(outLabel3, gbc);
        v0Panel = new JPanel();
        v0Panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DataPanel.add(v0Panel, gbc);
        v0Panel.setBorder(BorderFactory.createTitledBorder("Սկզբ. արագություն"));
        textField1 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        v0Panel.add(textField1, gbc);
        anglePanel = new JPanel();
        anglePanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DataPanel.add(anglePanel, gbc);
        anglePanel.setBorder(BorderFactory.createTitledBorder("Կրակի անկյուն"));
        textField2 = new JTextField();
        textField2.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        anglePanel.add(textField2, gbc);
        coefPanel = new JPanel();
        coefPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DataPanel.add(coefPanel, gbc);
        coefPanel.setBorder(BorderFactory.createTitledBorder("Դիմադր. գործակից"));
        textField3 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        coefPanel.add(textField3, gbc);
        coef2Panel = new JPanel();
        coef2Panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DataPanel.add(coef2Panel, gbc);
        coef2Panel.setBorder(BorderFactory.createTitledBorder("Շփման գործակից"));
        textField4 = new JTextField();
        textField4.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        coef2Panel.add(textField4, gbc);
        weightPanel = new JPanel();
        weightPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DataPanel.add(weightPanel, gbc);
        weightPanel.setBorder(BorderFactory.createTitledBorder("Արկի քաշը"));
        textField5 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        weightPanel.add(textField5, gbc);
        DrawArea = new JPanel();
        DrawArea.setLayout(new GridBagLayout());
        DrawArea.setMinimumSize(new Dimension(850, 500));
        DrawArea.setPreferredSize(new Dimension(900, 600));
        drContent=new drArea();
        gbc=new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        DrawArea.add(drContent, gbc);
        gbc1 = new GridBagConstraints();
        gbc1.gridx = GridBagConstraints.RELATIVE;
        gbc1.gridy = 0;
        gbc1.weightx = 1.0;
        gbc1.weighty = 1.0;
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.anchor=GridBagConstraints.CENTER;
        panel1.add(DrawArea, gbc1);
        DrawArea.setBorder(BorderFactory.createTitledBorder("Graphic"));
        DrawArea.setBackground(new Color(189, 183, 107));
        add(panel1);
        jMenu=new JMenu("Ընտրել հրետանու տեսակը");
        jMenuItem=new JMenuItem("Д-30(осколочно-фугасный снаряд)");
        jMenu.add(jMenuItem);
        jMenuItem.addActionListener(e->
        {
            textField1.setText("690");
            textField2.setText("35");
            textField3.setText("0.3");
            textField4.setText("0.0000018");
            textField5.setText("21.76");
        });
        myMenu.add(jMenu);
        settingItem=new JMenu("Կարգավորումներ");
        clearNext=new JCheckBoxMenuItem("Մաքրել նախորդ արդյունքները");
        myMenuBar.add(settingItem);
        settingItem.add(clearNext);
        setJMenuBar(myMenuBar);
        startButton.addActionListener(e ->
        {
            updateData();
            startFire();
            drContent.paint(drContent.getGraphics());
        });
    }
    //Get data from input fields
    public void updateData()
    {
        v0=Double.parseDouble(textField1.getText());
        angle=Double.parseDouble(textField2.getText());
        coef=Double.parseDouble(textField3.getText());
        coef2=Double.parseDouble(textField4.getText());
        weight=Double.parseDouble(textField5.getText());
    }

    public void startFire()
    {
        if(clearNext.isSelected())
        {
            xCoord.clear();
            yCoord.clear();
        }
        double time=d30.fire(angle, weight, v0, coef, coef2, xCoord, yCoord);
        outLabel2.setText(""+(int)time+" վայրկյան");
    }
}
