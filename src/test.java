import model.SafePassword;

public class test {
	public static void main(String args[]){
	String addr = "h.matsuzak@gmail.com";
	String hashedAddr = SafePassword.getStretchedPassword(addr, "matsuzhi");
	System.out.print(hashedAddr);
	}
}
