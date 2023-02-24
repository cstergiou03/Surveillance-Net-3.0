import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Hypergraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.algorithms.shortestpath.*;

public class Frame extends JFrame{
	
	private JPanel panel = new JPanel();
	
	private JButton findButton = new JButton("Find");
	private JButton visualButton = new JButton("Visualize Network");
	private JTextField suspectName = new JTextField("Please enter suspect's name");
	private Registry list;
	private JList numbers;
	private JList sms;
	private JList partners;
	private JList suggested;
	private JList partnersCountry;
	private Suspect suspect = null;
	private DefaultListModel model2 = new DefaultListModel();
	//smsPanel
	private JTextField sendNumber = new JTextField("Give a number ");
	
	
	public Frame(Registry archive) {
		
		list = archive;
		
		panel.add(suspectName);
		panel.add(findButton);
		panel.add(visualButton);
		
		
		this.setContentPane(panel);
		
		ButtonListener listener = new ButtonListener();
		findButton.addActionListener(listener);
		ButtonListener3 listener3 = new ButtonListener3();
		visualButton.addActionListener(listener3);
		
		this.setVisible(true);
		this.setSize(600, 400);
		this.setTitle("Find Suspect");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}



	class ButtonListener implements ActionListener{
	
	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			numbers = new JList();
			sms = new JList();
			partners = new JList(); 
			suggested = new JList();
			partnersCountry = new JList();
			ArrayList<String> partnersList = new ArrayList<>();
			ArrayList<Suspect> suggestedList = new ArrayList<>(); 
			
			DefaultListModel model = new DefaultListModel();
			DefaultListModel model3 = new DefaultListModel();
			DefaultListModel model4 = new DefaultListModel();
			DefaultListModel model5 = new DefaultListModel();
			
			String name = suspectName.getText();
			boolean flag = true;
			int count = 0;
			
			//stoixeia suspect
			for(Suspect names: list.suspects) {
				if(name.equals(names.getName())) {
					suspect = list.suspects.get(count);
					flag = false; 
					break;
				}
				count++;
			}
			
			
			if(flag == true) {
				
				JOptionPane.showMessageDialog(null, "Suspet " + name + " Not Found","Message", JOptionPane.ERROR_MESSAGE);
				
			}else if (flag == false) {
				
				dispose();
				//suggestedPartners
				
				
				//Frame
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());
				frame.setVisible(true);
				frame.setSize(900,800);
				frame.setTitle("Suspect Page");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				LayoutManager layout = new FlowLayout();
				frame.setLayout(null);
				
				
				//panel borders 
				JPanel panel = new JPanel();
				JPanel panel2 = new JPanel();
				JPanel panel3 = new JPanel();
				JPanel panel4 = new JPanel();
				JPanel panel5 = new JPanel();
				JPanel panel6 = new JPanel();
	
				panel.setLayout(layout);
				panel2.setLayout(layout);
				panel3.setLayout(layout);
				panel4.setLayout(layout);
				panel5.setLayout(layout);
				panel6.setLayout(layout);
				
				
				//panel1
				JPanel namePanel = new JPanel();
				JTextField suspectName = new JTextField(suspect.getName());
				JTextField suspectCode = new JTextField(suspect.getCodeName());
				//gia tous arithmous tou suspect pou psaxnw
				for(String numbers: suspect.PhoneNumbers) {
					model.addElement(numbers);
				}
				numbers.setModel(model);
				JScrollPane suspectNumbers = new JScrollPane(numbers);
				suspectNumbers.setPreferredSize(new Dimension(110, 60));
				
				//panel2
				JPanel smsPanel = new JPanel();
				JButton smsButton = new JButton("Find SMS");
				ButtonListener2 listener = new ButtonListener2();
				smsButton.addActionListener(listener);
				JScrollPane sendSms = new JScrollPane(sms);
				
				//panel3
				JPanel partnersPanel = new JPanel();
				JLabel label = new JLabel("Partners");
				for(Suspect sus: suspect.relatedSuspects) {
						partnersList.add(sus.getName() + ", " + sus.getCodeName());
				}
				for(String partnersL: partnersList) {
					model3.addElement(partnersL);
				}
				partners.setModel(model3);
				JScrollPane partnersScroll = new JScrollPane(partners);
				
				//panel4
				JPanel suggestedPanel = new JPanel();
				JLabel label2 = new JLabel("Suggested Partners ----->");
				suggestedList= suspect.suggestedPartners();
				for(Suspect suggestedPartners: suggestedList) {
					model4.addElement(suggestedPartners.getName());
				}
				suggested.setModel(model4);
				JScrollPane suggestedScroll = new JScrollPane(suggested);
				suggestedScroll.setPreferredSize(new Dimension(200, 100));
				
				//panel5
				JPanel countryPanel = new JPanel();
				model5.addElement("Suspects coming from " + suspect.getCountry());
				for(Suspect susPartners: list.suspects) {
					if(susPartners.getCountry().equals(suspect.getCountry())) {
						model5.addElement(susPartners.getName());
					}
						
				}
				partnersCountry.setModel(model5);
				JScrollPane sameCountry = new JScrollPane(partnersCountry);
				sameCountry.setPreferredSize(new Dimension(300, 80));
				
				//return Button
				JButton returnButton = new JButton("Return to Search Screen");
				returnButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						new Frame(list);
						frame.dispose();
					}
					
				});
				
				
				namePanel.add(suspectName);
				namePanel.add(suspectCode);
				namePanel.add(suspectNumbers);
				namePanel.setBorder(blackline);
				panel.add(namePanel);
				panel.setBounds(250, 0, 400, 77);
				
								
				smsPanel.add(sendNumber);
				smsPanel.add(sendSms);
				smsPanel.add(smsButton);
				smsPanel.setBorder(blackline);
				panel2.add(smsPanel);
				panel2.setBounds(220, 78, 460, 150);
					
				
				partnersPanel.add(label);
				partnersPanel.add(partnersScroll);
				partnersPanel.setBorder(blackline);
				panel3.add(partnersPanel);
				panel3.setBounds(330, 229, 220, 164);
				
				
				suggestedPanel.add(label2);
				suggestedPanel.add(suggestedScroll);
				suggestedPanel.setBorder(blackline);
				panel4.add(suggestedPanel);
				panel4.setBounds(90, 394, 700, 120);
				
				countryPanel.add(sameCountry);
				countryPanel.setBorder(blackline);
				panel5.add(countryPanel);
				panel5.setBounds(240, 513, 400, 100);
				
				panel6.add(returnButton);
				panel6.setBounds(280, 614, 300, 300);
				
				frame.add(panel);
				frame.add(panel2);
				frame.add(panel3);
				frame.add(panel4);
				frame.add(panel5);
				frame.add(panel6);
			}
		}
		
	}
	
	
	
	class ButtonListener2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String aNumber = sendNumber.getText();
			ArrayList<SMS> messages = new ArrayList<>();
			
			for(String numbers: suspect.PhoneNumbers) {
				messages.addAll(list.getMessagesBetween(numbers, aNumber));
			}
			
			for(SMS message: messages) {
				model2.addElement(message.getMessage());
			}
			
			sms.setModel(model2);
			
			
			
		}
		
	}
	
	class ButtonListener3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
				
				Graph<String, String> g = new SparseMultigraph<String, String>();
				for(Suspect suspect: list.suspects) {
					g.addVertex(suspect.getCodeName());
					for(Suspect partners: suspect.relatedSuspects) {
			  			if(!g.containsEdge(partners.getCodeName() + ", " + suspect.getCodeName())){
			  				g.addEdge(suspect.getCodeName() + ", " + partners.getCodeName(), suspect.getCodeName(), partners.getCodeName());
			  			}
			  		}
				}
				
				double diametros = DistanceStatistics.diameter(g);
				
				JPanel graphPanel = new JPanel();
				graphPanel.setBackground(Color.lightGray);
				JPanel diameterPanel = new JPanel();
				JPanel panel = new JPanel();
				JPanel panel2 = new JPanel();
				JLabel diameter = new JLabel("Diameter = " + diametros);
				
				
				
			    VisualizationImageServer vs = new VisualizationImageServer( new CircleLayout(g), new Dimension(670, 550));
			    vs.setBackground(Color.lightGray);
			    vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
			    
			    
			    JFrame frame = new JFrame();
			    
			    frame.setTitle("Suspects Network");
			    frame.setSize(685, 650);
			    frame.setLayout(new BorderLayout());
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setVisible(true);
			    Border blackline = BorderFactory.createLineBorder(Color.black);
				LayoutManager layout = new FlowLayout();
				frame.setLayout(null);
			    
			    graphPanel.add(vs);
			    diameterPanel.add(diameter);
			    
			    panel.setLayout(layout);
			    panel2.setLayout(layout);
			    
			    panel.add(graphPanel);
			    panel.setBounds(0, 0, 670, 570);
			   
			    
			    
			    panel2.add(diameterPanel);
			    panel2.setSize(650, 100);
			    panel2.setBounds(0, 571, 120, 80);
			    
			    
			    
			    frame.add(panel);
			    frame.add(panel2);
			   
			    
			
		}

	}
	
}
