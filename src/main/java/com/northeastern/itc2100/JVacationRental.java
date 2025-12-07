package com.northeastern.itc2100;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 * JVacationRental - A Java Swing application for calculating vacation rental costs
 * Based on JResortCalculator from Programming Exercise 4 (page 584)
 * 
 * Features:
 * - Base vacation rental pricing calculation
 * - Additional service selection with pricing
 * - Resort tax calculation (5.5%)
 * - Resort shuttle with people count
 * - Notes saving to text file
 * - Comprehensive error handling
 * 
 * @author Loyd Johnson (johnson.loy@northeastern.edu)
 * @course ITC2100 - Introduction to Programming Java
 * @assignment Signature Assignment
 * @version 1.0
 * @since 2025-12-07
 * 
 * Note: Core Java concepts (80%) learned from textbook. Advanced features
 * (Maven, JUnit, modern Java APIs) learned through AI consultation with
 * Microsoft Copilot and Claude for educational understanding.
 */
public class JVacationRental extends JFrame implements ActionListener {
    
    // Service pricing constants (using double data type)
    private static final double BASE_RATE_PER_NIGHT = 200.00;
    private static final double WINDSURFING_PRICE = 50.75;
    private static final double SNORKELING_PRICE = 25.65;
    private static final double HORSEBACK_RIDING_PRICE = 75.89;
    private static final double RESORT_TAX_RATE = 0.055; // 5.5%
    private static final double SHUTTLE_PRICE_PER_PERSON = 15.00;
    
    // GUI Layout constants
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 700;
    private static final int TEXT_FIELD_COLUMNS = 10;
    private static final int NOTES_AREA_ROWS = 5;
    private static final int NOTES_AREA_COLUMNS = 30;
    private static final int MAIN_PANEL_SPACING = 10;
    private static final int BORDER_PADDING = 20;
    private static final int COMPONENT_SPACING_SMALL = 5;
    private static final int COMPONENT_SPACING_MEDIUM = 10;
    private static final int COMPONENT_SPACING_LARGE = 15;
    private static final int FONT_SIZE_DEFAULT = 12;
    
    // Validation constants
    private static final int MIN_NIGHTS = 1;
    private static final int MAX_NIGHTS = 365;
    private static final String INITIAL_PRICE_TEXT = "$0.00";
    private static final String TAX_LABEL_FORMAT = "Resort Tax (5.5%): ";
    private static final String TOTAL_LABEL_PREFIX = "Resort Fee Totals including taxes: ";
    private static final String BASE_PRICE_PREFIX = "The base price for your stay is: ";
    
    // GUI Components
    private JTextField nightsField;
    private JTextField shuttlePeopleField;
    private JCheckBox windsurfingCheckBox;
    private JCheckBox snorkelingCheckBox;
    private JCheckBox horsebackCheckBox;
    private JCheckBox shuttleCheckBox;
    private JTextArea notesArea;
    private JLabel basePriceLabel;
    private JLabel taxLabel;
    private JLabel totalLabel;
    private JButton calculateButton;
    private JButton saveNotesButton;
    private JButton clearButton;
    
    // Currency formatter
    private NumberFormat currencyFormat;
    
    /**
     * Constructor - Initialize the GUI components and layout
     */
    public JVacationRental() {
        currencyFormat = NumberFormat.getCurrencyInstance();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        
        setTitle("Vacation Rental Calculator - ITC2100");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // Center the window
        setResizable(true);
    }
    
    /**
     * Initialize all GUI components with default values
     */
    private void initializeComponents() {
        // Input fields
        nightsField = new JTextField(TEXT_FIELD_COLUMNS);
        shuttlePeopleField = new JTextField(TEXT_FIELD_COLUMNS);
        shuttlePeopleField.setEnabled(false); // Initially disabled
        
        // Service checkboxes
        windsurfingCheckBox = new JCheckBox("Windsurfing (" + currencyFormat.format(WINDSURFING_PRICE) + ")");
        snorkelingCheckBox = new JCheckBox("Snorkeling (" + currencyFormat.format(SNORKELING_PRICE) + ")");
        horsebackCheckBox = new JCheckBox("Horseback Riding (" + currencyFormat.format(HORSEBACK_RIDING_PRICE) + ")");
        shuttleCheckBox = new JCheckBox("Resort Shuttle (" + currencyFormat.format(SHUTTLE_PRICE_PER_PERSON) + " per person)");
        
        // Notes area
        notesArea = new JTextArea(NOTES_AREA_ROWS, NOTES_AREA_COLUMNS);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        notesArea.setBorder(BorderFactory.createLoweredBevelBorder());
        
        // Result labels
        basePriceLabel = new JLabel(BASE_PRICE_PREFIX + INITIAL_PRICE_TEXT);
        taxLabel = new JLabel(TAX_LABEL_FORMAT + INITIAL_PRICE_TEXT);
        totalLabel = new JLabel(TOTAL_LABEL_PREFIX + INITIAL_PRICE_TEXT);
        
        // Buttons
        calculateButton = new JButton("Calculate Total");
        saveNotesButton = new JButton("Save Notes");
        clearButton = new JButton("Clear All");
        
        // Set fonts for better readability
        Font labelFont = new Font("Arial", Font.BOLD, 12);
        basePriceLabel.setFont(labelFont);
        taxLabel.setFont(labelFont);
        totalLabel.setFont(labelFont);
    }
    
    /**
     * Setup the layout using BorderLayout and nested panels
     */
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(MAIN_PANEL_SPACING, MAIN_PANEL_SPACING));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        
        // Top panel - Input fields
        JPanel inputPanel = createInputPanel();
        
        // Middle panel - Services
        JPanel servicesPanel = createServicesPanel();
        
        // Bottom panel - Results and buttons
        JPanel resultsPanel = createResultsPanel();
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(servicesPanel, BorderLayout.CENTER);
        mainPanel.add(resultsPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    /**
     * Create the input panel for nights and shuttle people
     */
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(COMPONENT_SPACING_SMALL, COMPONENT_SPACING_SMALL, COMPONENT_SPACING_SMALL, COMPONENT_SPACING_SMALL);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Number of nights
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Number of nights:"), gbc);
        gbc.gridx = 1;
        panel.add(nightsField, gbc);
        
        // Base rate info
        gbc.gridx = 2;
        panel.add(new JLabel("(Base rate: " + currencyFormat.format(BASE_RATE_PER_NIGHT) + " per night)"), gbc);
        
        return panel;
    }
    
    /**
     * Create the services panel with checkboxes
     */
    private JPanel createServicesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Additional Services"));
        
        // Add service checkboxes
        panel.add(windsurfingCheckBox);
        panel.add(Box.createVerticalStrut(COMPONENT_SPACING_SMALL));
        panel.add(snorkelingCheckBox);
        panel.add(Box.createVerticalStrut(COMPONENT_SPACING_SMALL));
        panel.add(horsebackCheckBox);
        panel.add(Box.createVerticalStrut(COMPONENT_SPACING_MEDIUM));
        
        // Shuttle section
        JPanel shuttlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        shuttlePanel.add(shuttleCheckBox);
        panel.add(shuttlePanel);
        
        JPanel shuttlePeoplePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        shuttlePeoplePanel.add(new JLabel("Number of people requiring shuttle:"));
        shuttlePeoplePanel.add(shuttlePeopleField);
        panel.add(shuttlePeoplePanel);
        
        panel.add(Box.createVerticalStrut(COMPONENT_SPACING_LARGE));
        
        // Notes section
        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder("Resort Stay Notes"));
        notesPanel.add(new JLabel("Enter any additional information about your stay:"), BorderLayout.NORTH);
        notesPanel.add(new JScrollPane(notesArea), BorderLayout.CENTER);
        panel.add(notesPanel);
        
        return panel;
    }
    
    /**
     * Create the results panel with pricing display and buttons
     */
    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Results display
        JPanel resultsDisplay = new JPanel();
        resultsDisplay.setLayout(new BoxLayout(resultsDisplay, BoxLayout.Y_AXIS));
        resultsDisplay.setBorder(BorderFactory.createTitledBorder("Pricing Summary"));
        
        resultsDisplay.add(basePriceLabel);
        resultsDisplay.add(Box.createVerticalStrut(COMPONENT_SPACING_SMALL));
        resultsDisplay.add(taxLabel);
        resultsDisplay.add(Box.createVerticalStrut(COMPONENT_SPACING_SMALL));
        resultsDisplay.add(totalLabel);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(calculateButton);
        buttonsPanel.add(saveNotesButton);
        buttonsPanel.add(clearButton);
        
        panel.add(resultsDisplay, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Setup event handlers for all interactive components
     */
    private void setupEventHandlers() {
        calculateButton.addActionListener(this);
        saveNotesButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        // Enable/disable shuttle people field based on checkbox
        shuttleCheckBox.addActionListener(e -> {
            shuttlePeopleField.setEnabled(shuttleCheckBox.isSelected());
            if (!shuttleCheckBox.isSelected()) {
                shuttlePeopleField.setText("");
            }
        });
        
        // Auto-calculate when Enter is pressed in nights field
        nightsField.addActionListener(this);
    }
    
    /**
     * Handle button click events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton || e.getSource() == nightsField) {
            calculateTotal();
        } else if (e.getSource() == saveNotesButton) {
            saveNotesToFile();
        } else if (e.getSource() == clearButton) {
            clearAllFields();
        }
    }
    
    /**
     * Calculate the total vacation rental cost including all services and taxes
     */
    private void calculateTotal() {
        try {
            // Validate and get number of nights
            int nights = validateNumberInput(nightsField.getText().trim(), "Number of nights", MIN_NIGHTS, MAX_NIGHTS);
            
            // Calculate base price
            double basePrice = nights * BASE_RATE_PER_NIGHT;
            
            // Add selected services
            double servicesTotal = 0.0;
            if (windsurfingCheckBox.isSelected()) {
                servicesTotal += WINDSURFING_PRICE;
            }
            if (snorkelingCheckBox.isSelected()) {
                servicesTotal += SNORKELING_PRICE;
            }
            if (horsebackCheckBox.isSelected()) {
                servicesTotal += HORSEBACK_RIDING_PRICE;
            }
            
            // Add shuttle cost if selected
            if (shuttleCheckBox.isSelected()) {
                int shuttlePeople = validateNumberInput(shuttlePeopleField.getText().trim(), 
                    "Number of people for shuttle", 1, 20);
                servicesTotal += shuttlePeople * SHUTTLE_PRICE_PER_PERSON;
            }
            
            // Calculate subtotal and tax
            double subtotal = basePrice + servicesTotal;
            double tax = subtotal * RESORT_TAX_RATE;
            double total = subtotal + tax;
            
            // Update display labels
            basePriceLabel.setText("The base price for your stay is: " + currencyFormat.format(subtotal));
            taxLabel.setText("Resort Tax (5.5%): " + currencyFormat.format(tax));
            totalLabel.setText("Resort Fee Totals including taxes: " + currencyFormat.format(total));
            
        } catch (NumberFormatException ex) {
            showErrorMessage("Input Error", ex.getMessage());
        } catch (Exception ex) {
            showErrorMessage("Calculation Error", "An unexpected error occurred: " + ex.getMessage());
        }
    }
    
    /**
     * Validate numeric input with range checking
     */
    private int validateNumberInput(String input, String fieldName, int minValue, int maxValue) 
            throws NumberFormatException {
        if (input.isEmpty()) {
            throw new NumberFormatException(fieldName + " cannot be empty. Please enter a value between " 
                + minValue + " and " + maxValue + ".");
        }
        
        int value;
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(fieldName + " must be a valid number. Please enter a whole number between " 
                + minValue + " and " + maxValue + ".");
        }
        
        if (value < minValue || value > maxValue) {
            throw new NumberFormatException(fieldName + " must be between " + minValue + " and " + maxValue + ".");
        }
        
        return value;
    }
    
    /**
     * Save resort notes to a text file with timestamp
     */
    private void saveNotesToFile() {
        String notes = notesArea.getText().trim();
        
        if (notes.isEmpty()) {
            showErrorMessage("Save Error", "Please enter some notes before saving.");
            return;
        }
        
        try {
            // Create filename with current date
            String filename = "resort_notes.txt";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
                writer.println("=== Resort Stay Notes ===");
                writer.println("Date: " + LocalDateTime.now().format(formatter));
                writer.println("Notes: " + notes);
                writer.println("-------------------------");
                writer.println();
            }
            
            JOptionPane.showMessageDialog(this, 
                "Notes saved successfully to " + filename, 
                "Save Successful", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            showErrorMessage("File Error", "Error saving notes to file: " + ex.getMessage());
        }
    }
    
    /**
     * Clear all input fields and reset display
     */
    private void clearAllFields() {
        nightsField.setText("");
        shuttlePeopleField.setText("");
        windsurfingCheckBox.setSelected(false);
        snorkelingCheckBox.setSelected(false);
        horsebackCheckBox.setSelected(false);
        shuttleCheckBox.setSelected(false);
        shuttlePeopleField.setEnabled(false);
        notesArea.setText("");
        
        basePriceLabel.setText(BASE_PRICE_PREFIX + INITIAL_PRICE_TEXT);
        taxLabel.setText(TAX_LABEL_FORMAT + INITIAL_PRICE_TEXT);
        totalLabel.setText(TOTAL_LABEL_PREFIX + INITIAL_PRICE_TEXT);
    }
    
    /**
     * Display error message dialog with consistent formatting
     */
    private void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Main method to launch the application
     */
    public static void main(String[] args) {
        // Set look and feel to system default for better integration
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // If setting look and feel fails, continue with default
        }
        
        // Create and show the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new JVacationRental().setVisible(true);
        });
    }
}