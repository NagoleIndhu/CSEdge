import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class RegistrationManager extends Frame implements ActionListener {
    // Components for the registration form
    TextField nameField, ageField, emailField;
    Checkbox maleCheckbox, femaleCheckbox;
    Choice countryChoice;
    TextArea displayArea;
    ArrayList<Registrant> registrants = new ArrayList<>();

    public RegistrationManager() {
        setTitle("Registration Manager");
        //setSize(250, 500);
        setLayout(new FlowLayout());    
        setSize(600, 600); 
        setVisible(true);  

        // Initialize form components
        Label nameLabel = new Label("Name:");
        nameField = new TextField(20);
        nameLabel.setBounds(50,50,100,30);

        Label ageLabel = new Label("Age:");
        ageField = new TextField(20);
        ageLabel.setBounds(50,120,120,30);

        Label emailLabel = new Label("Email:");
        emailField = new TextField(20);
        emailLabel.setBounds(50,190,50,30);

        Label genderLabel = new Label("Gender:");
        CheckboxGroup genderGroup = new CheckboxGroup();
        maleCheckbox = new Checkbox("Male", genderGroup, false);
        femaleCheckbox = new Checkbox("Female", genderGroup, false);

        Label countryLabel = new Label("Country:");
        countryChoice = new Choice();
        countryChoice.add("USA");
        countryChoice.add("Canada");
        countryChoice.add("India");
        countryChoice.add("Australia");
        //System.out.println("\n");
        Button submitButton = new Button("Submit");
        submitButton.setBounds(150,300,70,30);
        Button displayButton = new Button("Display Registrants");
        displayButton.setBounds(400,300,70,30);
        Button exportButton = new Button("Export to .txt");
        exportButton.setBounds(450,300,70,30);

        displayArea = new TextArea(20, 40);
        displayArea.setEditable(false);

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(emailLabel);
        add(emailField);
        add(genderLabel);
        add(maleCheckbox);
        add(femaleCheckbox);
        add(countryLabel);
        add(countryChoice);
        add(submitButton);
        add(displayButton);
        add(exportButton);
        add(displayArea);

        // Register action listeners
        submitButton.addActionListener(this);
        displayButton.addActionListener(this);
        exportButton.addActionListener(this);

        // Set window closing behavior
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals("Submit")) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String email = emailField.getText();
            String gender = maleCheckbox.getState() ? "Male" : "Female";
            String country = countryChoice.getSelectedItem();

            Registrant registrant = new Registrant(name, age, email, gender, country);
            registrants.add(registrant);

            // Clear form fields
            nameField.setText("");
            ageField.setText("");
            emailField.setText("");
            maleCheckbox.setState(true);
            countryChoice.select(0);

            displayArea.setText("Registration successful!");

        } else if (command.equals("Display Registrants")) {
            displayArea.setText("");
            for (Registrant registrant : registrants) {
                displayArea.append(registrant + "\n");
            }

        } else if (command.equals("Export to .txt")) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("registrants.txt"))) {
                for (Registrant registrant : registrants) {
                    writer.println(registrant);
                }
                displayArea.setText("Data exported to registrants.txt successfully!");
            } catch (IOException e) {
                displayArea.setText("Error exporting data!");
            }
        }
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}

class Registrant implements Serializable {
    private String name;
    private int age;
    private String email;
    private String gender;
    private String country;

    public Registrant(String name, int age, String email, String gender, String country) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }

    public String toString() {
        return "Name: " + name + ", \nAge: " + age + ", \nEmail: " + email + ", \nGender: " + gender + ", \nCountry: " + country;
    }
}
    