import javax.swing.JButton;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
public class GetInput extends JFrame {
	public String name;
	public boolean morning,afternoon,night;
	public GetInput()
	{
		super("MEDICINE HELPER"); 
		setLayout(new FlowLayout());
		obtainValue();
		System.out.println("Medicine Helper Invoked");
	
	}
	public void obtainValue() {
		JTextField medname=new JTextField();
		final JCheckBox C1=new JCheckBox("MORNING");
		final JCheckBox C2=new JCheckBox("AFTERNOON");
		final JCheckBox C3=new JCheckBox("EVENING");
		Object[] params= {medname,C1,C2,C3};
		this.name=JOptionPane.showInputDialog(params);
		this.morning=C1.isSelected();
		this.afternoon=C2.isSelected();
		this.night=C3.isSelected();
		
	}
	public String toString() {
		String m=new String("      ");
		String a=new String("      ");
		String n=new String("      ");
		if(this.morning)
			{m="MORNING";}
		if(this.afternoon)
			{a="AFTERNOON";}
		if(this.night)
			{n="NIGHT";}
		
		return String.format("%s      %s %s %s",this.name,m,a,n);
	}
	public static void main(String args[])
 {   int n;
	 n=Integer.parseInt(JOptionPane.showInputDialog("Enter the Number of medicines"));
	 GetInput[] g=new GetInput[n];
 	 String meds[]=new String[n];
	 for(int i=0;i<n;i++)
	 {
	  g[i]=new GetInput();
	  }
	 for(int i=0;i<n;i++)
	 {
		 meds[i]=g[i].toString();
	 }
	 Calendar cal = Calendar.getInstance();
     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    while(sdf.format(cal.getTime())!="01:29:00")
    	{System.out.println(sdf.format(cal.getTime()));cal=Calendar.getInstance();
    		if(sdf.format(cal.getTime())=="01:29:00") {System.out.println("AWOOGA");break;}    		
    	}
    JOptionPane.showMessageDialog(null,meds);
	 	 
 }
}
