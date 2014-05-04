import java.io.*;

public class guipart {

   public static void main(String[] args) throws IOException {
      // First, bridge the byte stream of System.in to a Character Stream with the
      // InputStreamReader.
      // Next, for efficiency, wrap the InputStreamReader with a BufferedReader.
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String strText;
      System.out.println("Type in anything, then press enter.");
      System.out.println("Enter stop to quit");
      do {
         // now read in a line - a line is considered to be terminated by any one of
         // a line feed ('\n'), a carriage return ('\r'), or a carriage return
         // followed immediately by a linefeed.
         strText = br.readLine();
         if(!strText.equalsIgnoreCase("stop")){
               System.out.println("You" + strText);   // Echo it back out to the terminal
            }
         }
      while(!strText.equalsIgnoreCase("stop"));
      System.exit(0);
  }
}