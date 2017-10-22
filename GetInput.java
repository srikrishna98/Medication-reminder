import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class GetInput extends JFrame{
	public String name;
	public String additional_Message;
	public static String message=new String();
	public boolean morning,afternoon,night;
	public static ArrayList<GetInput>MorningList=new ArrayList<>();
	public static ArrayList<GetInput>AfternoonList=new ArrayList<>();
	public static ArrayList<GetInput>NightList=new ArrayList<>();
	public GetInput()
	{		super("MEDICINE HELPER"); 
			setLayout(new FlowLayout());
			obtainValue();
			System.out.println("Medicine Helper Invoked");
	}
	public void obtainValue() 
	{       final JTextField additional_info= new JTextField(" ");
			final JCheckBox C1=new JCheckBox("MORNING");
			final JCheckBox C2=new JCheckBox("AFTERNOON");
			final JCheckBox C3=new JCheckBox("NIGHT");
			final String text2=new String("Enter additional info(qty/descrption):");
			final String text =new String("Enter the Medicine Name and choose when it is to be consumed:");
			Object[] params= {C1,C2,C3,text2,additional_info,text};
			this.name=JOptionPane.showInputDialog(params);
			this.morning=C1.isSelected();
			this.afternoon=C2.isSelected();
			this.night=C3.isSelected();
			this.additional_Message=additional_info.getText();
			segregate();
	}
	public void segregate() 
	{
			if(this.morning) {MorningList.add(this);}
			if(this.afternoon) {AfternoonList.add(this);}
			if(this.night) {NightList.add(this);}
			System.out.println("\nSEGREGATED");
	}
	public String toString() {
			String m=new String(" ");
			String a=new String(" ");
			String n=new String(" ");
			if(this.morning)
				{m="MORNING";}
			if(this.afternoon)
				{a="AFTERNOON";}
			if(this.night)
				{n="NIGHT";}
			return String.format("%-20s      %10s %10s %10s",this.name,m,a,n);
	}
	public static void main(String args[]) throws Exception
		 {
			 int MorningSkipped=0,AfternoonSkipped=0,NightSkipped=0;
			 int n;
		 	 boolean flag=true;
		 	 n=Integer.parseInt(JOptionPane.showInputDialog("Enter the Number of medicines"));
		 	 int days=Integer.parseInt(JOptionPane.showInputDialog("Enter the duration of medication (days):"));
		 	 if(n<=0)
		 	 {
		 		 while(n<=0)
		 			 n=Integer.parseInt(JOptionPane.showInputDialog("Enter the Number of medicines (value>0)"));
			 }
			 if(days<=0)
		 	 {
		 		 while(days<=0)
		 			 days=Integer.parseInt(JOptionPane.showInputDialog("Enter the duration of medication (value>0)"));
			 }
			 System.out.println(days);
		 	 boolean m=false,a=false,e=false;
		 	 String MorningTime=new String();
 			 String AfternoonTime=new String(); 
		 	 String EveningTime=new String();
		 	 MorningTime= getInput(MorningTime,"MORNING");
		 	 AfternoonTime= getInput(AfternoonTime,"AFTERNOON");
		 	 EveningTime=getInput(EveningTime,"NIGHT");
		 	 GetInput[] g=new GetInput[n];
		  	 //String meds[]=new String[n];
			 for(int i=0;i<n;i++)
			 {
				 g[i]=new GetInput();
			  }
			/* for(int i=0;i<n;i++)
			 {
				 meds[i]=g[i].toString();
			 }*/
			 Toolkit.getDefaultToolkit().beep();
		     SimpleDateFormat time1 = new SimpleDateFormat("HH:mm");
		     String Current_time=time1.format(Calendar.getInstance().getTime());
		     GregorianCalendar cal =  (GregorianCalendar) GregorianCalendar.getInstance();
			 GregorianCalendar cal2 =  (GregorianCalendar) GregorianCalendar.getInstance();
			 cal2.add((Calendar.DATE),n-1);
			 System.out.println(!cal.after(cal2));
			   while(flag&&!(cal.after(cal2))) 
			   {
				while(!(Current_time.equals(MorningTime)|Current_time.equals(AfternoonTime)|Current_time.equals(EveningTime)))
		    	{
					Current_time = time1.format(Calendar.getInstance().getTime());
				}
		        if(Current_time.equals(MorningTime))
			    {	message=getStringForm(MorningList);	
			    	m=true;
			    }
		    	else if(Current_time.equals(AfternoonTime))
			    {	message=getStringForm(AfternoonList);
		    		a=true;
			    }
		    	else if(Current_time.equals(EveningTime))
			    {	message=getStringForm(NightList);
			    	e=true;
			    }
		        System.out.println(message);
		        message="Your medication for:\n"+cal.getTime().toString()+":\n"+message;
		   		Toolkit.getDefaultToolkit().beep();
			    JOptionPane.showMessageDialog(null,message);
			    if (SystemTray.isSupported())
			    	{
				            try 
			            {
			            	displayTray(message);
			            }
				            catch(Exception ev)
				         {
			            	System.out.println(ev.getMessage());	
				         }
			        }
			   	else 
			    	{
			            System.err.println("System tray not supported!");
			        }
			    String option=JOptionPane.showInputDialog("Did you take the medicine?(y/n)");
			    if(option.equalsIgnoreCase("N")|option.equalsIgnoreCase("NO"))
			    {
			    	if(m) MorningSkipped+=1;
			    	else if(a) AfternoonSkipped+=1;
			    	else if(e) NightSkipped+=1;
			    	JOptionPane.showMessageDialog(null, "PLEASE TAKE THE MEDICINE NEXT TIME");
			    }
			    else
			    {
			    	JOptionPane.showMessageDialog(null, "KEEP UP THE GOOD WORK");
			    }
			    
		     	    flag=false;
		     }
		    		Current_time=time1.format(Calendar.getInstance().getTime());
		    		System.out.println(Current_time);
		    		flag=false;
		     String EndAnalysis="SUCCESSFULLY COMPLETED YOUR COURSE OF MEDICATION\nANALYSIS:\n"+"MORNINGS SKIPPED:"+MorningSkipped+"\nAFTERNOONS SKIPPED:"+AfternoonSkipped+"\nNIGHT SKIPPED:"+NightSkipped;
		     JOptionPane.showMessageDialog(null,EndAnalysis);		
		 }  
	public static String getStringForm(ArrayList<GetInput> s)
		{
			String x=new String(" ");
			for(GetInput y:s)
			{
				x=x+y.name+"--"+y.additional_Message+"\n";
			}
			return x;
			
		}
	public static String getInput(String Time,String when) throws Exception
	   {
		   try
		   {	
			   String Message=String.format("Enter %s reminder time in (HH:MM) format:",when);
			   Time = JOptionPane.showInputDialog(Message);
			   String x[]=Time.split(":");
			   int h=Integer.parseInt(x[0]);
			   int m=Integer.parseInt(x[1]);
			   if(!(h>=0&&h<=23))
			   			{throw Exception.class.cast("INVALID HOUR");
			   			}
			   if(!(m>=0&&m<=60))
	   			{throw Exception.class.newInstance();
	   			}
			}
		   catch(Exception e)
		   {
			   String x=e.toString();
			   JOptionPane.showMessageDialog(null,x);
			   e.getMessage();
			   System.exit(0);
			   }
			   return Time;
	   	   }
	public static void displayTray(String message) throws AWTException, java.net.MalformedURLException 
			{
					SystemTray tray = SystemTray.getSystemTray();
					Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
					TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
					trayIcon.setImageAutoSize(true);
					trayIcon.setToolTip("Medication helper");
					tray.add(trayIcon);
					trayIcon.displayMessage(message, "Medication helper", MessageType.INFO);
					}
		}