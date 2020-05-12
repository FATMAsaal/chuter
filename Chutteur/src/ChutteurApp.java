import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.lang.*;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class ChutteurApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private JCheckBox checkTemp;
	private JCheckBox checkLight;
	private JCheckBox checkHumidity;

	public static Boolean SELECTTEMP;
	public static Boolean SELECTLIGHT;
	public static Boolean SELECTHUMIDITY;

	public static List<Integer> SOUND;
	public static List<Integer> LIGHT;
	public static List<Float> FAHRENHEIT;
	public static List<Float> CELSIUS;
	public static List<Float> HUMIDITY;
	
	public ChutteurApp() {
		super("Chutteur");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = new Dimension(getToolkit().getScreenSize());
		int h = (int) dim.getHeight();
		int w = (int) dim.getWidth();
		int hf = 480;
		int wf = 850;

		setBounds((w - wf) / 2, (h - hf) / 2, wf, hf);
		setResizable(false);

		SQL_JDBC DB = new SQL_JDBC();
		DB.openDB();
		DB.createTable();
		DB.insertUser(Arrays.asList(0),Arrays.asList("User"),Arrays.asList("user@mail.com"));
		DB.insertSound(Arrays.asList(44,544,48,47,47,48,44,46,47,45));
		DB.insertTemp(68, 20, 40);
		DB.insertTemp(69, 21, 49);
		DB.insertLight(14);
		DB.insertLight(13);

		SOUND = DB.selectSound();
		LIGHT = DB.selectLight();
		FAHRENHEIT = DB.selectFahrenheit();
		CELSIUS = DB.selectCelsius();
		HUMIDITY = DB.selectHumidity();

		///////////////

		JPanel checkBoxes = new JPanel(new FlowLayout());
		checkBoxes.setBounds(0,0,850,80);
		Border border = BorderFactory.createTitledBorder("Display informations");
		checkBoxes.setBorder(border);

		checkTemp = new JCheckBox("Temperature");
		checkLight = new JCheckBox("Light");
		checkHumidity = new JCheckBox("Humidity");

		SELECTTEMP = false;
		SELECTLIGHT = false;
		SELECTHUMIDITY = false;

		ActionListener actionListener = new ActionHandler(); 

		checkTemp.addActionListener(actionListener);
		checkLight.addActionListener(actionListener);
		checkHumidity.addActionListener(actionListener);

		checkTemp.setBounds(25,25,125,50);
		checkLight.setBounds(150,25,250,50);
		checkHumidity.setBounds(275,25,375,50);

		checkBoxes.add(checkTemp);
		checkBoxes.add(checkLight);
		checkBoxes.add(checkHumidity);

		add(checkBoxes);

		//////////////////

		JPanel graphPanel = new JPanel(new BorderLayout());
		graphPanel.setBounds(0,80,850,480);
		graphPanel.add(new Graphs(),BorderLayout.CENTER);
		add(graphPanel);
	}

	class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JCheckBox checkbox = (JCheckBox) event.getSource();
			if (checkbox == checkTemp) {
				SELECTTEMP = checkbox.isSelected();
			} else if (checkbox == checkLight) {
				SELECTLIGHT = checkbox.isSelected();
			} else if (checkbox == checkHumidity) {
				SELECTHUMIDITY = checkbox.isSelected();
			}
			repaint();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ChutteurApp().setVisible(true);
			}
		});
	}
}
