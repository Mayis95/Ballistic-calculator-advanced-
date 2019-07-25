import javax.swing.*;

public class GUITest
{
    public static void main(String args[])
    {
        MyGUI mg=new MyGUI();
        mg.setSize(750,650);
        mg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mg.setVisible(true);
    }
}
