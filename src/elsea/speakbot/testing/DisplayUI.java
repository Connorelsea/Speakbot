package elsea.speakbot.testing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import elsea.speakbot.brain.*;
import elsea.speakbot.repositories.ElementRepository;

import java.awt.event.*;
import java.util.*;

public class DisplayUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	public JTextField INPUT;
	public JEditorPane OUTPUT;
	
	public Document DOCUMENT;
	public SimpleAttributeSet SET;
	public SimpleAttributeSet SET_US;
	
	public Speakbot SPEAKBOT;
	
	public String INPUT_CURRENT;
	public String OUTPUT_CURRENT;
	
	public ArrayList<IntelligenceElement> IES;

	public DisplayUI() {
		
		setTitle("Speakbot BETA");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane PANEL_SCROLL = new JScrollPane();
		PANEL_SCROLL.setAutoscrolls(true);
		PANEL_SCROLL.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		PANEL_SCROLL.setBorder(null);
		contentPane.add(PANEL_SCROLL, BorderLayout.CENTER);
		
		OUTPUT = new JEditorPane();
		OUTPUT.setEditable(false);
		OUTPUT.setSelectedTextColor(Color.WHITE);
		OUTPUT.setBackground(UIManager.getColor("control"));
		OUTPUT.setBorder(null);
		OUTPUT.setSelectionColor(new Color(255, 51, 51));
		OUTPUT.setContentType("text/html");
		PANEL_SCROLL.setViewportView(OUTPUT);
		
		INPUT = new JTextField();
		contentPane.add(INPUT, BorderLayout.SOUTH);
		INPUT.setColumns(10);
		
		INPUT.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					fire_enterEvent();
				}
			}
			
		});
		
		DOCUMENT = OUTPUT.getDocument();
		
		SET_US = new SimpleAttributeSet();
		StyleConstants.setFontFamily(SET_US, "SansSerif");
		StyleConstants.setForeground(SET_US, Color.GRAY);
		
		SET = new SimpleAttributeSet();
		StyleConstants.setForeground(SET, Color.BLACK);
		StyleConstants.setFontFamily(SET, "SansSerif");
		
		ElementRepository.generate();
		
		SPEAKBOT = new Speakbot();
		SPEAKBOT.IES.addAll(ElementRepository.ALL);
		
	}
	
	public void fire_enterEvent() {
		addUSLine(INPUT.getText());
		SPEAKBOT.interpret(INPUT.getText());
		INPUT.setText("");
		INPUT.requestFocus();
		addSBLine(SPEAKBOT.getOutput());
	}
	
	public void addSBLine(String string) {
		try {
			DOCUMENT.insertString(DOCUMENT.getLength(), string + "\n", SET);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	public void addUSLine(String string) {
		try {
			DOCUMENT.insertString(DOCUMENT.getLength(), string + "\n", SET_US);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

}