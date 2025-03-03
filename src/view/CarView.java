package view;

import model.interfaces.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CarView extends JFrame implements Observer {
    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo On");
    JButton turboOffButton = new JButton("Saab Turbo Off");
    JButton liftBedButton = new JButton("Scania Raise Bed");
    JButton lowerBedButton = new JButton("Lower Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JButton addCarButton = new JButton("Add car");  //Nya knappar
    JButton removeCarButton = new JButton("Remove car");   //Nya knappar

    public static final int width = 800;
    public static final int height = 800;
    private final DrawPanel drawPanel;

    public CarView(String framename, DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        initComponents(framename);
    }

    private void initComponents(String title) {
        setupFrame(title);
        setupDrawPanel();
        setupGasPanel();
        setupControlPanel();
        setupStartStopButtons();
        finalizeFrame();
    }

    private void setupFrame(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    }

    private void setupDrawPanel() {
        this.add(drawPanel);
    }

    private void setupGasPanel() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(event -> gasAmount = (int) ((JSpinner) event.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);
    }

    private void setupControlPanel() {
        controlPanel.setLayout(new GridLayout(2, 3)); // 2 rows, 3 columns for 6 buttons
        controlPanel.add(gasButton);
        controlPanel.add(turboOnButton);
        controlPanel.add(liftBedButton);
        controlPanel.add(brakeButton);
        controlPanel.add(turboOffButton);
        controlPanel.add(lowerBedButton);
        controlPanel.add(addCarButton);
        controlPanel.add(removeCarButton);

        controlPanel.setPreferredSize(new Dimension((width / 2) + 4, 200));
        controlPanel.setBackground(Color.CYAN);

        this.add(controlPanel);
    }

    private void setupStartStopButtons() {
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(width / 5 - 15, 200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(width / 5 - 15, 200));
        this.add(stopButton);
    }

    private void finalizeFrame() {
        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Add these methods to CarView class
    public void addGasListener(ActionListener listener) {
        gasButton.addActionListener(listener);
    }

    public void addBrakeListener(ActionListener listener) {
        brakeButton.addActionListener(listener);
    }

    public void addTurboOnListener(ActionListener listener) {
        turboOnButton.addActionListener(listener);
    }

    public void addTurboOffListener(ActionListener listener) {
        turboOffButton.addActionListener(listener);
    }

    public void addLiftBedListener(ActionListener listener) {
        liftBedButton.addActionListener(listener);
    }

    public void addLowerBedListener(ActionListener listener) {
        lowerBedButton.addActionListener(listener);
    }

    public void addStartListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void addStopListener(ActionListener listener) {
        stopButton.addActionListener(listener);
    }

    public int getGasAmount() {
        return (int) gasSpinner.getValue();
    }

    @Override
    public void update() {
        drawPanel.repaint();
    }
}
