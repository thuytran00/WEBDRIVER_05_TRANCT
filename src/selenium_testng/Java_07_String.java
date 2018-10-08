package selenium_testng;

public class Java_07_String {
  
  public static void main(String[] args) {
	  String automation = "Automation Testing Tutorials Online 123456";
	  //Đếm số lượng ký tự a
	    char kyTu = 'a';
	   String chars ="Testing";
	   checkA(automation, kyTu);
	   checkChars(automation, chars);
	   checkStart(automation, "Automation");
	   checkEnd(automation, "Online");
	   checkIndex(automation, "Tutorials");
	   replace(automation, "Online", "Offline");
	   countNumber(automation);
  }
  public static void checkA(String text, char kyTu){
	  int count = 0;
	    for (int i = 0; i < text.length(); i++) {
	        if (text.toLowerCase().charAt(i) == kyTu) {
	            count++;
	        }
	    }
	    
	    System.out.println("So lan xuat hien cua ky tu la  " + count);
  }
  
//Kiểm tra chứa 'Testing'
  public static boolean checkChars(String text, String chars)
  {
	  if(text.contains(chars)) {
		  System.out.println("true");
	  return true;
	  }
	  else {
		  System.out.println("false");
		  return false;
	  }
  }
  
  public static boolean checkStart(String text, String chars)
  {
	  if(text.startsWith(chars)) {
		  System.out.println("true");
	  return true;
	  }
	  else {
		  System.out.println("false");
		  return false;
	  }
  }
  
  public static boolean checkEnd(String text, String chars)
  {
	  if(text.endsWith(chars)) {
		  System.out.println("true");
	  return true;
	  }
	  else {
		  System.out.println("false");
		  return false;
	  }
  }
  
  public static void checkIndex(String text, String chars){
	int index = text.indexOf(chars);
	System.out.println("Vi tri cua chuoi la  " + index);
  }
  public static void replace(String text, String oldChar, String newChar){
		String newtext=text.replace(oldChar, newChar);
		System.out.println("Chuoi moi la " + newtext);
	  }
  public static void countNumber(String text){
	  
      int count=0;
      for(int i=0;i<text.length();i++){
         if(Character.isDigit(text.charAt(i))) count++;                         
      }
     
	    System.out.println("So luong chu so trong chuoi la  " + count);
  }
  
}
