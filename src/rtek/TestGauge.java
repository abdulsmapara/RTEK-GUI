package rtek;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import eu.hansolo.steelseries.gauges.Radial;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class TestGauge {
    private static void createAndShowUI() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        JPanel panel = new JPanel() {
            @Override 
            public Dimension getPreferredSize() {
                return new Dimension(1650, 1080);
            }
        };

        final Radial gauge1 = new Radial();
        gauge1.setTitle("Gauge 1");
        gauge1.setUnitString("Some units");
        final Radial gauge2 = new Radial();
        gauge2.setTitle("Gauge 2");
        gauge2.setUnitString("Some units");

        
        panel.setLayout(new GridLayout());
        panel.add(gauge1, BorderLayout.WEST);
        panel.add(gauge2, BorderLayout.EAST);
        frame.add(panel);

        JPanel buttonsPanel = new JPanel();
        JLabel valueLabel = new JLabel("Value:");
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        final JTextField valueField = new JTextField(7);
        valueField.setText("30");
        JButton b1 = new JButton("B1");
        JPanel jt1 = new JPanel();
        //jt1.setHorizontalAlignment(JTextField.CENTER);
        
        jt1.setSize(10, 50);
        jt1.setBackground(Color.red);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jt1.getBackground() == Color.RED)
                {
                    jt1.setBackground(Color.GREEN);
                }
                else
                {
                    jt1.setBackground(Color.red);
                }
            }
        
        });
        JButton b2 = new JButton("B2");
        JButton b3 = new JButton("B3");
        JButton b4 = new JButton("B4");
        JButton button = new JButton("Set");
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.valueOf(valueField.getText());
                    gauge1.setValueAnimated(value);
                } catch(NumberFormatException ex) { 
                    //TODO - handle invalid input 
                    System.err.println("invalid input");
                }
            }
        });
        JPanel jp1 = new JPanel();
        //jp1.setLayout(new GridLayout());
        jp1.add(b1);
        jp1.add(jt1);
        buttonsPanel.add(jp1,BorderLayout.NORTH);
        JPanel jp2 = new JPanel();
        jp2.add(b2);
        buttonsPanel.add(jp2,BorderLayout.AFTER_LINE_ENDS);
        JPanel jp3 = new JPanel();
        jp3.add(b3);
        buttonsPanel.add(jp3,BorderLayout.CENTER);
        JPanel jp4 = new JPanel();
        jp4.add(b4);
        buttonsPanel.add(jp4,BorderLayout.AFTER_LINE_ENDS);
        
        //buttonsPanel.add(b1);
        //buttonsPanel.add(b2);
        //buttonsPanel.add(b3);
        //buttonsPanel.add(b4);
        buttonsPanel.add(valueLabel);
        buttonsPanel.add(valueField);
        buttonsPanel.add(button);
        frame.add(buttonsPanel, BorderLayout.NORTH);
        
        JSlider slider1 = new JSlider(JSlider.VERTICAL, 0, 100, 10);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.setMinorTickSpacing(1);
        slider1.setMajorTickSpacing(10);
        slider1.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                 JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int fps = (int)source.getValue();
                    gauge1.setValueAnimated(fps);
                }  
            }
        
        });
        
        JSlider slider2 = new JSlider(JSlider.VERTICAL, 0, 100, 10);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
        slider2.setMinorTickSpacing(1);
        slider2.setMajorTickSpacing(10);
        slider2.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                 JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int fps = (int)source.getValue();
                    gauge2.setValueAnimated(fps);
                }  
            }
        
        });
        
        
        JPanel jjp = new JPanel();
        jjp.setLayout(new GridLayout());
        jjp.add(slider1);
        jjp.add(slider2);
        frame.add(jjp,BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
}
