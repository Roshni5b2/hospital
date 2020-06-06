import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
//Hospital Main Class
class Hospital implements ActionListener
{
JFrame f;
JLabel l1;
JButton b1,b2,b3,b4,b5;
Hospital()
{
f=new JFrame("HOSPITAL MANAGEMENT SYSTEM");
f.setSize(400,400);
f.setVisible(true);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
l1=new JLabel("MINI HOSPITAL");
b1=new JButton("ADMIN");
b2=new JButton("DOCTOR");
b3=new JButton("RECEPTIONIST");
b4=new JButton("PATIENT");
b5=new JButton("PHARMACIST");
f.add(l1);
f.add(b1);
f.add(b2);
f.add(b3);
f.add(b4);
f.add(b5);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
new Admin();
}
if(ae.getSource().equals(b2))
{
new Doctor();
}
if(ae.getSource().equals(b3))
{
new Receptionist();
}
if(ae.getSource().equals(b4))
{
new Plogin();
}
if(ae.getSource().equals(b5))
{
new Pharmcist();
}
}
public static void main(String...sss)
{
new Hospital();
}
}
//Admin Main Class
class Admin extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l2;
JTextField t1;
JPasswordField p1;
JButton b1,b2;
Admin()
{
f=new JFrame("Admin login");
l1=new JLabel("Username");
l2=new JLabel("Password");
t1=new JTextField(10);
p1=new JPasswordField(10);
b1=new JButton("Login");
b2=new JButton("cancel");
f.setVisible(true);
f.setSize(800,800);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);
f.add(l2);
f.add(p1);
f.add(b1);
f.add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
if(t1.getText().equals(""))
{
JOptionPane.showMessageDialog(null,"Enter Username");}
else if(p1.getText().equals(""))
{JOptionPane.showMessageDialog(null,"Enter Password");}
else
{
JOptionPane.showMessageDialog(null,"Login is Sucessfull");
f.setVisible(false);
}
new ShowAll();
}
else
{
f.setVisible(false);
}
}
}
//Admin View After Login
class ShowAll extends JFrame implements ActionListener
{
JFrame f;
JButton b1,b2,b3;
ShowAll()
{
f=new JFrame("Show All Details");
b1=new JButton("Show Doctor info");
b2=new JButton("Show Receptionist info");
b3=new JButton("Show Patient info");
f.setVisible(true);
f.setSize(500,500);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(b1);
f.add(b2);
f.add(b3);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
new DTable();
}
else if(ae.getSource().equals(b2))
{
new RTable();
}
else
{
new PTable();
}
}
}
//Doctor Main Class
class Doctor extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l2;
JTextField t1;
JPasswordField t2;
JButton b1,b2,b3;
Doctor()
{
f=new JFrame("Doctor login");
l1=new JLabel("Username");
l2=new JLabel("Password");
t1=new JTextField(10);
t2=new JPasswordField(10);
b1=new JButton("Login");
b2=new JButton("cancel");
b3=new JButton("Sign up");
f.setVisible(true);
f.setSize(500,500);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);
f.add(l2);
f.add(t2);
f.add(b1);
f.add(b2);
f.add(b3);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection cc=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement psb=cc.createStatement();
ResultSet rs=psb.executeQuery("select * from doctor");
String u=t1.getText();
String p=t2.getText();
String pp="";
String qq="";
int i=0;
while(rs.next())
{
pp=rs.getString("usern");
qq=rs.getString("pass");
if(u.equals(pp) && p.equals(qq))
{
i++;
}
}
if(i>0)
{
new Doctorlogin();
}
if(i==0)
{
JOptionPane.showMessageDialog(null,"user_name or password invalid");
t1.setText("");
t2.setText("");
}
cc.close();
psb.close();
rs.close();
}
catch(Exception ee){}
}
if(ae.getSource().equals(b3))
{
new Doctorsignup();
}
if(ae.getSource().equals(b2))
{
f.setVisible(false);
}
}
}
//After Doctor Login
class Doctorlogin extends JFrame implements ActionListener
{
JFrame f;
JButton b0,b1,b2;
String a,b,c,d;
Doctorlogin()
{
f=new JFrame("Doctor login");
f.setSize(500,500);
f.setVisible(true);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
b1=new JButton("Show Appointment");
b0=new JButton("Prescription");
b2=new JButton("Log out");
f.add(b1);
f.add(b0);
f.add(b2);
b1.addActionListener(this);
b0.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
new ShowAppointment();
}
else if(ae.getSource().equals(b0))
{
new Prescription();
}
else
{
f.setVisible(false);
}
}
}
//Prescription page for doctor to fill it and send
class Prescription extends JFrame implements ActionListener
{
JFrame f;
JTextField t;
JTextArea a;
JButton b;
Prescription()
{
f=new JFrame("Set Prescription");
f.setSize(500,500);
f.setVisible(true);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
t=new JTextField(10);
a=new JTextArea("Prescription");
b=new JButton("Send");
f.add(t);
f.add(a);
f.add(b);
b.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b))
{
JOptionPane.showMessageDialog(null,"Prescription is sent");
String name=(String)t.getText();
String pres=(String)a.getText();

try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
PreparedStatement ps=c.prepareStatement("insert into prescription values(?,?)");
ps.setString(1,name);
ps.setString(2,pres);
int m=ps.executeUpdate();
if(m==1)
{
JOptionPane.showMessageDialog(null,"sent");
}
ps.close();
c.close();
}
catch(SQLException ee){}
}
}
//Receptionist Login Page
class Receptionist extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l2;
JTextField t1;
JPasswordField t2;
JButton b1,b2,b3;
Receptionist()
{
f=new JFrame("Receptionist login");
l1=new JLabel("Username");
l2=new JLabel("Password");
t1=new JTextField(10);
t2=new JPasswordField(10);
b1=new JButton("Login");
b2=new JButton("cancel");
b3=new JButton("Sign up");
f.setVisible(true);
f.setSize(500,500);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);
f.add(l2);
f.add(t2);
f.add(b1);
f.add(b2);
f.add(b3);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection cc=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement psb=cc.createStatement();
ResultSet rs=psb.executeQuery("select * from receptionist");
String u=t1.getText();
String p=t2.getText();
String pp="";
String qq="";
int i=0;
while(rs.next())
{
pp=rs.getString("usern");
qq=rs.getString("pass");
if(u.equals(pp) && p.equals(qq))
{
i++;
}
}
if(i>0)
{
new Recep();
}
if(i==0)
{
JOptionPane.showMessageDialog(null,"user_name or password invalid");
t1.setText("");
t2.setText("");
}
cc.close();
psb.close();
rs.close();
}
catch(Exception ee){}
}
if(ae.getSource().equals(b3))
{
new Receptionistsignup();
}
if(ae.getSource().equals(b2))
{
f.setVisible(false);
}
}
}
//After Receptionist login
class Recep extends JFrame implements ActionListener
{
JFrame f;
JButton b1,b2,b3;
Recep()
{
f=new JFrame("Receptionist login");
f.setSize(500,500);
f.setVisible(true);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
b1=new JButton("Patient form");
b2=new JButton("Set appointment");
b3=new JButton("Logout");
f.add(b1);
f.add(b2);
f.add(b3);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
new Patientform();
}
else if(ae.getSource().equals(b2))
{
new Addappointment();
}
else
{
f.setVisible(false);
}
}
}
//Receptionist adds appointement to patient
class Addappointment extends JFrame implements ActionListener
{
JFrame f;
JLabel l0,la,lb,lc,l1,l2,l3,l4,l5;
JTextField t1,t2,t3,t4,t5;
JComboBox c1,c2,c3;
JButton b1,b2;
Addappointment()
{
f=new JFrame("Set Appointment");
l0=new JLabel("Patient name");
la=new JLabel("Patient Age");
lb=new JLabel("Patient disease");
l1=new JLabel("Select Category");
l2=new JLabel("Select Doctor");
l3=new JLabel("consultancy fees");
l4=new JLabel("select date");
l5=new JLabel("select time");
String[] specialist={"Cardialogist","Gynocolagist","Neuro surgeon","Dermatologists","Nephrologists"};
  c1=new JComboBox(specialist);
  c1.setSelectedIndex(0);
String[] doctors={"Dr.kiran","Dr.Madhavi","Dr.Sumanth","Dr.Kesavi","Dr.Alifa"};
  c2=new JComboBox(doctors);
  c2.setSelectedIndex(0);
String[] fees={"consultancy fees","1000"};
  c3=new JComboBox(fees);
  c3.setSelectedIndex(0);
t1=new JTextField(10);
t2=new JTextField(10);
t3=new JTextField(10);
t4=new JTextField(10);
t5=new JTextField(10);
b1=new JButton("Submit");
b2=new JButton("Cancel");
f.setVisible(true);
f.setSize(800,800);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l0);
f.add(t3);
f.add(la);
f.add(t4);
f.add(lb);
f.add(t5);
f.add(l1);
f.add(c1);
f.add(l2);
f.add(c2);
f.add(l3);
f.add(c3);
f.add(l4);
f.add(t1);
f.add(l5);
f.add(t2);
f.add(b1);
f.add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
JOptionPane.showMessageDialog(null,"Appointment is submitted");

String name=(String)t3.getText();
String age=(String)t4.getText();

String disease=(String)t5.getText();
String category=c1.getSelectedItem().toString();
String docto=c2.getSelectedItem().toString();
String fees=c3.getSelectedItem().toString();
String dat=(String)t1.getText();
String time=(String)t2.getText();

try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
PreparedStatement ps=c.prepareStatement("insert into appointment values(?,?,?,?,?,?,?,?)");
System.out.println("enter doctor details");
ps.setString(1,name);
ps.setString(2,age);
ps.setString(3,disease);
ps.setString(4,category);
ps.setString(5,docto);
ps.setString(6,fees);
ps.setString(7,dat);
ps.setString(8,time);

int m=ps.executeUpdate();
if(m==1)
{
JOptionPane.showMessageDialog(null,"inserted");
}
ps.close();
c.close();
}
catch(SQLException ee){}

}
else
{
f.setVisible(false);
}
}
}
class Plogin extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l2;
JTextField t1;
JPasswordField p1;
JButton b1,b2;
//Patient Login page
Plogin()
{
f=new JFrame("Patient login");
l1=new JLabel("Username");
l2=new JLabel("Password");
t1=new JTextField(10);
p1=new JPasswordField(10);
b1=new JButton("Login");
b2=new JButton("cancel");
f.setVisible(true);
f.setSize(800,800);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);
f.add(l2);
f.add(p1);
f.add(b1);
f.add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection ccp=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement psbp=ccp.createStatement();
ResultSet rst=psbp.executeQuery("select * from patient");
String u=t1.getText();
String p=p1.getText();
String pp="";
String qq="";
int i=0;
while(rst.next())
{
pp=rst.getString("usern");
qq=rst.getString("pass");
if(u.equals(pp) && p.equals(qq))
{
i++;
}
}
if(i>0)
{
new ShowPrescription();
}
if(i==0)
{
JOptionPane.showMessageDialog(null,"user_name or password invalid");
t1.setText("");
p1.setText("");
}
ccp.close();
psbp.close();
rst.close();
}
catch(Exception ee){}
}
else
{
f.setVisible(false);
}
}
}
//Prescription will be shown to patient
class ShowPrescription extends JFrame
{
JFrame f;
JTable table;
ShowPrescription()
{
f=new JFrame("Patient Prescription");
f.setSize(800,800);
f.setLayout(new FlowLayout());
f.setVisible(true);
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection ckn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement st=ckn.createStatement();
ResultSet rs=st.executeQuery("select * from prescription");
String ss="";
int op=0,k=0,l=0;
String s1[]=new String[100];
String s2[]=new String[1000];

while(rs.next())
{
s1[k]=rs.getString("name");
s2[k]=rs.getString("pres");
op++;
k++;
}
ckn.close();
st.close();
String col[]={"1","2","3"};
String row[][]=new String[op][3];
int y=1;
for(int i=0;i<op;i++)
{
for(int j=0;j<3;j++)
{
if(j==0)
{
row[i][j]=""+y;
y++;
}
if(j==1)
row[i][j]=""+s1[i];
if(j==2)
row[i][j]=""+s2[i];

}
}
table=new JTable(row,col);
}
catch(Exception ee)
{}
f.add(table);
}
}
//If doctor new to hospital he can signup to add his profile to database
class Doctorsignup extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l4,l5,l6,l7,l8,l9,l10,l11,l12;
JTextField t1,t4,t6,t8,t9,t10,t11;
JPasswordField t12;
JButton b1,b2;
JComboBox c1,c2;
Doctorsignup()
{
f=new JFrame("Doctor signup");
l1=new JLabel("Name");
l4=new JLabel("Age");
l5=new JLabel("Gender");
l6=new JLabel("Blood group");
l7=new JLabel("Specialist");
l8=new JLabel("Phone no.");
l9=new JLabel("email id");
l10=new JLabel("Address");
l11=new JLabel("Username");
l12=new JLabel("Password");
t1=new JTextField(10);

t4=new JTextField(10);
String[] gender={"Male","Female","Others"};
  c1=new JComboBox(gender);
  c1.setSelectedIndex(0);
t6=new JTextField(10);
String[] specialist={"Cardialogist","Gynocolagist","Neuro surgeon","Dermatologists","Nephrologists"};
  c2=new JComboBox(specialist);
  c2.setSelectedIndex(0);
t8=new JTextField(10);
t9=new JTextField(10);
t10=new JTextField(10);
t11=new JTextField(10);
t12=new JPasswordField(10);
b1=new JButton("Register");
b2=new JButton("cancel");
f.setVisible(true);
f.setSize(800,800);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);

f.add(l4);
f.add(t4);
f.add(l5);
f.add(c1);
f.add(l6);
f.add(t6);
f.add(l7);
f.add(c2);
f.add(l8);
f.add(t8);
f.add(l9);
f.add(t9);
f.add(l10);
f.add(t10);
f.add(l11);
f.add(t11);
f.add(l12);
f.add(t12);
f.add(b1);
f.add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
JOptionPane.showMessageDialog(null,"Registration done");
f.setVisible(false);

String name=(String)t1.getText();
String age=(String)t4.getText();
String gender=c1.getSelectedItem().toString();
String bg=(String)t6.getText();
String sp=c2.getSelectedItem().toString();
String pn=(String)t8.getText();
String ema=(String)t9.getText();
String adde=(String)t10.getText();
String usern=(String)t11.getText();
String pass=(String)t12.getText();
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
PreparedStatement ps=c.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?,?,?)");
System.out.println("enter doctor details");
ps.setString(1,name);
ps.setString(2,age);
ps.setString(3,gender);
ps.setString(4,bg);
ps.setString(5,sp);
ps.setString(6,pn);
ps.setString(7,ema);
ps.setString(8,adde);
ps.setString(9,usern);
ps.setString(10,pass);
int m=ps.executeUpdate();
if(m==1)
{
JOptionPane.showMessageDialog(null,"inserted");
}
ps.close();
c.close();
}
catch(SQLException ee){}

}
else
{
f.setVisible(false);
}
}
}
//If Receptionist new to hospital they can signup to add their profile to database
class Receptionistsignup extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l4,l5,l6,l8,l9,l10,l11,l12;
JTextField t1,t4,t6,t8,t9,t10,t11;
JPasswordField t12;
JButton b1,b2;
JComboBox c1;
Receptionistsignup()
{
f=new JFrame("Receptionist signup");
l1=new JLabel("Name");
l4=new JLabel("Age");
l5=new JLabel("Gender");
l6=new JLabel("Blood group");
l8=new JLabel("Phone no.");
l9=new JLabel("email id");
l10=new JLabel("Address");
l11=new JLabel("Username");
l12=new JLabel("Password");
t1=new JTextField(10);

t4=new JTextField(10);
String[] gender={"Male","Female","Others"};
  c1=new JComboBox(gender);
  c1.setSelectedIndex(0);
t6=new JTextField(10);
t8=new JTextField(10);
t9=new JTextField(10);
t10=new JTextField(10);
t11=new JTextField(10);
t12=new JPasswordField(10);
b1=new JButton("Register");
b2=new JButton("cancel");
f.setVisible(true);
f.setSize(800,800);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);

f.add(l4);
f.add(t4);
f.add(l5);
f.add(c1);
f.add(l6);
f.add(t6);
f.add(l8);
f.add(t8);
f.add(l9);
f.add(t9);
f.add(l10);
f.add(t10);
f.add(l11);
f.add(t11);
f.add(l12);
f.add(t12);
f.add(b1);
f.add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
JOptionPane.showMessageDialog(null,"Registration done");
f.setVisible(false);

String name=(String)t1.getText();
String age=(String)t4.getText();
String gender=c1.getSelectedItem().toString();
String bg=(String)t6.getText();
String pn=(String)t8.getText();
String ema=(String)t9.getText();
String adde=(String)t10.getText();
String usern=(String)t11.getText();
String pass=(String)t12.getText();
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
PreparedStatement psa=con.prepareStatement("insert into receptionist values(?,?,?,?,?,?,?,?,?)");
System.out.println("enter doctor details");
psa.setString(1,name);
psa.setString(2,age);
psa.setString(3,gender);
psa.setString(4,bg);
psa.setString(5,pn);
psa.setString(6,ema);
psa.setString(7,adde);
psa.setString(8,usern);
psa.setString(9,pass);
int mi=psa.executeUpdate();
if(mi==1)
{
JOptionPane.showMessageDialog(null,"inserted");
}
psa.close();
con.close();
}
catch(SQLException ee){}

}
else
{
f.setVisible(false);
}
}
}
//patient details will be filled in this form
class Patientform extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l4,l5,l6,l2,l8,l9,l10,l11,l12;
JTextField t1,t4,t6,t2,t8,t9,t10,t11;
JTextField t12;
JButton b1,b2;
JComboBox c1;
Patientform()
{
f=new JFrame("Patient form");
l1=new JLabel("Name");
l4=new JLabel("Age");
l5=new JLabel("Gender");
l6=new JLabel("Blood group");
l2=new JLabel("Disease");
l8=new JLabel("Phone no.");
l9=new JLabel("email id");
l10=new JLabel("Address");
l11=new JLabel("Username");
l12=new JLabel("Password");
t1=new JTextField(10);
t2=new JTextField(10);
t4=new JTextField(10);
String[] gender={"Male","Female","Others"};
  c1=new JComboBox(gender);
  c1.setSelectedIndex(0);
t6=new JTextField(10);
t8=new JTextField(10);
t9=new JTextField(10);
t10=new JTextField(10);
t11=new JTextField(10);
t12=new JTextField(10);
b1=new JButton("Register");
b2=new JButton("cancel");
f.setVisible(true);
f.setSize(800,800);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);

f.add(l4);
f.add(t4);
f.add(l5);
f.add(c1);
f.add(l6);
f.add(t6);
f.add(l2);
f.add(t2);
f.add(l8);
f.add(t8);
f.add(l9);
f.add(t9);
f.add(l10);
f.add(t10);
f.add(l11);
f.add(t11);
f.add(l12);
f.add(t12);
f.add(b1);
f.add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
JOptionPane.showMessageDialog(null,"Registration done");
f.setVisible(false);

String name=(String)t1.getText();
String age=(String)t4.getText();
String gender=c1.getSelectedItem().toString();
String bg=(String)t6.getText();
String dis=(String)t2.getText();
String pn=(String)t8.getText();
String ema=(String)t9.getText();
String adde=(String)t10.getText();
String usern=(String)t11.getText();
String pass=(String)t12.getText();
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection ckon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
PreparedStatement psat=ckon.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?)");
System.out.println("enter doctor details");
psat.setString(1,name);
psat.setString(2,age);
psat.setString(3,gender);
psat.setString(4,bg);
psat.setString(5,dis);
psat.setString(6,pn);
psat.setString(7,ema);
psat.setString(8,adde);
psat.setString(9,usern);
psat.setString(10,pass);
int min=psat.executeUpdate();
if(min==1)
{
JOptionPane.showMessageDialog(null,"inserted");
}
psat.close();
ckon.close();
}
catch(SQLException ee){}

}
else
{
f.setVisible(false);
}
}
}
//Doctor data visible to admin
class DTable 
{
JFrame f;
JTable table;
DTable()
{
f=new JFrame("Doctor info");
f.setSize(800,800);
f.setLayout(new FlowLayout());
f.setVisible(true);
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection ckn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement st=ckn.createStatement();
ResultSet rs=st.executeQuery("select * from doctor");
String ss="";
int op=0,k=0,l=0;
String s1[]=new String[100];
String s2[]=new String[100];
String s3[]=new String[100];
String s4[]=new String[100];
String s5[]=new String[100];
String s6[]=new String[100];
String s7[]=new String[100];
String s8[]=new String[100];
String s9[]=new String[100];
String s10[]=new String[100];
while(rs.next())
{
s1[k]=rs.getString("name");
s2[k]=rs.getString("age");
s3[k]=rs.getString("gender");
s4[k]=rs.getString("bg");
s5[k]=rs.getString("sp");
s6[k]=rs.getString("pn");
s7[k]=rs.getString("ema");
s8[k]=rs.getString("adde");
s9[k]=rs.getString("usern");
s10[k]=rs.getString("pass");
op++;
k++;
}
ckn.close();
st.close();
String col[]={"1","2","3","4","5","6","7","8","9","10","11"};
String row[][]=new String[op][11];
int y=1;
for(int i=0;i<op;i++)
{
for(int j=0;j<11;j++)
{
if(j==0)
{
row[i][j]=""+y;
y++;
}
if(j==1)
row[i][j]=""+s1[i];
if(j==2)
row[i][j]=""+s2[i];
if(j==3)
row[i][j]=""+s3[i];
if(j==4)
row[i][j]=""+s4[i];
if(j==5)
row[i][j]=""+s5[i];
if(j==6)
row[i][j]=""+s6[i];
if(j==7)
row[i][j]=""+s7[i];
if(j==8)
row[i][j]=""+s8[i];
if(j==9)
row[i][j]=""+s9[i];
if(j==10)
row[i][j]=""+s10[i];
}
}
table=new JTable(row,col);
}
catch(Exception ee)
{}
f.add(table);
}
}
//Receptionist data visible to admin
class RTable 
{
JFrame f;
JTable table;
RTable()
{
f=new JFrame("Receptionist info");
f.setSize(800,800);
f.setLayout(new FlowLayout());
f.setVisible(true);
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection ckn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement st=ckn.createStatement();
ResultSet rs=st.executeQuery("select * from receptionist");
String ss="";
int op=0,k=0,l=0;
String s1[]=new String[100];
String s2[]=new String[100];
String s3[]=new String[100];
String s4[]=new String[100];
String s5[]=new String[100];
String s6[]=new String[100];
String s7[]=new String[100];
String s8[]=new String[100];
String s9[]=new String[100];
while(rs.next())
{
s1[k]=rs.getString("name");
s2[k]=rs.getString("age");
s3[k]=rs.getString("gender");
s4[k]=rs.getString("bg");
s5[k]=rs.getString("pn");
s6[k]=rs.getString("ema");
s7[k]=rs.getString("adde");
s8[k]=rs.getString("usern");
s9[k]=rs.getString("pass");
op++;
k++;
}
ckn.close();
st.close();
String col[]={"1","2","3","4","5","6","7","8","9","10"};
String row[][]=new String[op][11];
int y=1;
for(int i=0;i<op;i++)
{
for(int j=0;j<10;j++)
{
if(j==0)
{
row[i][j]=""+y;
y++;
}
if(j==1)
row[i][j]=""+s1[i];
if(j==2)
row[i][j]=""+s2[i];
if(j==3)
row[i][j]=""+s3[i];
if(j==4)
row[i][j]=""+s4[i];
if(j==5)
row[i][j]=""+s5[i];
if(j==6)
row[i][j]=""+s6[i];
if(j==7)
row[i][j]=""+s7[i];
if(j==8)
row[i][j]=""+s8[i];
if(j==9)
row[i][j]=""+s9[i];
}
}
table=new JTable(row,col);
}
catch(Exception ee)
{}
f.add(table);
}
}
//Patient data visible to admin
class PTable 
{
JFrame f;
JTable table;
PTable()
{
f=new JFrame("Patient info");
f.setSize(800,800);
f.setLayout(new FlowLayout());
f.setVisible(true);
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection ckn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement st=ckn.createStatement();
ResultSet rs=st.executeQuery("select * from patient");
String ss="";
int op=0,k=0,l=0;
String s1[]=new String[100];
String s2[]=new String[100];
String s3[]=new String[100];
String s4[]=new String[100];
String s5[]=new String[100];
String s6[]=new String[100];
String s7[]=new String[100];
String s8[]=new String[100];
String s9[]=new String[100];
String s10[]=new String[100];
while(rs.next())
{
s1[k]=rs.getString("name");
s2[k]=rs.getString("age");
s3[k]=rs.getString("gender");
s4[k]=rs.getString("bg");
s5[k]=rs.getString("disease");
s6[k]=rs.getString("pn");
s7[k]=rs.getString("ema");
s8[k]=rs.getString("adde");
s9[k]=rs.getString("usern");
s10[k]=rs.getString("pass");
op++;
k++;
}
ckn.close();
st.close();
String col[]={"1","2","3","4","5","6","7","8","9","10","11"};
String row[][]=new String[op][11];
int y=1;
for(int i=0;i<op;i++)
{
for(int j=0;j<12;j++)
{
if(j==0)
{
row[i][j]=""+y;
y++;
}
if(j==1)
row[i][j]=""+s1[i];
if(j==2)
row[i][j]=""+s2[i];
if(j==3)
row[i][j]=""+s3[i];
if(j==4)
row[i][j]=""+s4[i];
if(j==5)
row[i][j]=""+s5[i];
if(j==6)
row[i][j]=""+s6[i];
if(j==7)
row[i][j]=""+s7[i];
if(j==8)
row[i][j]=""+s8[i];
if(j==9)
row[i][j]=""+s9[i];
if(j==10)
row[i][j]=""+s10[i];
}
}
table=new JTable(row,col);
}
catch(Exception ee)
{}
f.add(table);
}
}
//Appointments will be shown to doctors
class ShowAppointment
{
JFrame f;
JTable table;
ShowAppointment()
{
f=new JFrame("Appointment Details");
f.setSize(800,800);
f.setLayout(new FlowLayout());
f.setVisible(true);
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection ckn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement st=ckn.createStatement();
ResultSet rs=st.executeQuery("select * from appointment");
String ss="";
int op=0,k=0,l=0;
String s1[]=new String[100];
String s2[]=new String[100];
String s3[]=new String[100];
String s4[]=new String[100];
String s5[]=new String[100];
String s6[]=new String[100];
String s7[]=new String[100];
String s8[]=new String[100];

while(rs.next())
{
s1[k]=rs.getString("name");
s2[k]=rs.getString("age");
s3[k]=rs.getString("disease");
s4[k]=rs.getString("category");
s5[k]=rs.getString("docto");
s6[k]=rs.getString("fees");
s7[k]=rs.getString("dat");
s8[k]=rs.getString("time");

op++;
k++;
}
ckn.close();
st.close();
String col[]={"1","2","3","4","5","6","7","8","9"};
String row[][]=new String[op][11];
int y=1;
for(int i=0;i<op;i++)
{
for(int j=0;j<9;j++)
{
if(j==0)
{
row[i][j]=""+y;
y++;
}
if(j==1)
row[i][j]=""+s1[i];
if(j==2)
row[i][j]=""+s2[i];
if(j==3)
row[i][j]=""+s3[i];
if(j==4)
row[i][j]=""+s4[i];
if(j==5)
row[i][j]=""+s5[i];
if(j==6)
row[i][j]=""+s6[i];
if(j==7)
row[i][j]=""+s7[i];
if(j==8)
row[i][j]=""+s8[i];

}
}
table=new JTable(row,col);
}
catch(Exception ee)
{}
f.add(table);
}
}
}
//Pharmacist login page
class Pharmcist extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l2;
JTextField t1;
JPasswordField t2;
JButton b1,b2,b3;
Pharmcist()
{
f=new JFrame("Pharmcist login");
l1=new JLabel("Username");
l2=new JLabel("Password");
t1=new JTextField(10);
t2=new JPasswordField(10);
b1=new JButton("Login");
b2=new JButton("cancel");
b3=new JButton("Sign up");
f.setVisible(true);
f.setSize(500,500);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);
f.add(l2);
f.add(t2);
f.add(b1);
f.add(b2);
f.add(b3);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection cc=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
Statement psb=cc.createStatement();
ResultSet rs=psb.executeQuery("select * from pharmcist");
String u=t1.getText();
String p=t2.getText();
String pp="";
String qq="";
int i=0;
while(rs.next())
{
pp=rs.getString("usern");
qq=rs.getString("pass");
if(u.equals(pp) && p.equals(qq))
{
i++;
}
}
if(i>0)
{
new ShowPrescription();
}
if(i==0)
{
JOptionPane.showMessageDialog(null,"user_name or password invalid");
t1.setText("");
t2.setText("");
}
cc.close();
psb.close();
rs.close();
}
catch(Exception ee){}
}
if(ae.getSource().equals(b3))
{
new Pup();
}
if(ae.getSource().equals(b2))
{
f.setVisible(false);
}
}
}
//Pharmacist signup
class Pup extends JFrame implements ActionListener
{
JFrame f;
JLabel l1,l4,l5,l6,l8,l9,l10,l11,l12;
JTextField t1,t4,t6,t8,t9,t10,t11;
JPasswordField t12;
JButton b1,b2;
JComboBox c1;
Pup()
{
f=new JFrame("Pharmcist signup");
l1=new JLabel("Name");
l4=new JLabel("Age");
l5=new JLabel("Gender");
l6=new JLabel("Blood group");
l8=new JLabel("Phone no.");
l9=new JLabel("email id");
l10=new JLabel("Address");
l11=new JLabel("Username");
l12=new JLabel("Password");
t1=new JTextField(10);

t4=new JTextField(10);
String[] gender={"Male","Female","Others"};
  c1=new JComboBox(gender);
  c1.setSelectedIndex(0);
t6=new JTextField(10);
t8=new JTextField(10);
t9=new JTextField(10);
t10=new JTextField(10);
t11=new JTextField(10);
t12=new JPasswordField(10);
b1=new JButton("Register");
b2=new JButton("cancel");
f.setVisible(true);
f.setSize(800,800);
f.setLayout(new FlowLayout(FlowLayout.CENTER,2000,5));
f.add(l1);
f.add(t1);

f.add(l4);
f.add(t4);
f.add(l5);
f.add(c1);
f.add(l6);
f.add(t6);
f.add(l8);
f.add(t8);
f.add(l9);
f.add(t9);
f.add(l10);
f.add(t10);
f.add(l11);
f.add(t11);
f.add(l12);
f.add(t12);
f.add(b1);
f.add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource().equals(b1))
{
JOptionPane.showMessageDialog(null,"Registration done");
f.setVisible(false);

String name=(String)t1.getText();
String age=(String)t4.getText();
String gender=c1.getSelectedItem().toString();
String bg=(String)t6.getText();
String pn=(String)t8.getText();
String ema=(String)t9.getText();
String adde=(String)t10.getText();
String usern=(String)t11.getText();
String pass=(String)t12.getText();
try
{
DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password");
PreparedStatement psa=con.prepareStatement("insert into pharmcist values(?,?,?,?,?,?,?,?,?)");
System.out.println("enter doctor details");
psa.setString(1,name);
psa.setString(2,age);
psa.setString(3,gender);
psa.setString(4,bg);
psa.setString(5,pn);
psa.setString(6,ema);
psa.setString(7,adde);
psa.setString(8,usern);
psa.setString(9,pass);
int mi=psa.executeUpdate();
if(mi==1)
{
JOptionPane.showMessageDialog(null,"inserted");
}
psa.close();
con.close();
}
catch(SQLException ee){}

}
else
{
f.setVisible(false);
}
}
}

