import java.io.*;

public class guiClient {

   public static void main(String[] args) throws IOException {
      // First, bridge the byte stream of System.in to a Character Stream with the
      // InputStreamReader.
      // Next, for efficiency, wrap the InputStreamReader with a BufferedReader.
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String address;
      String screenname = "";
      String strText = "";
      System.out.println("Type in server IP, then press enter.");
      System.out.println("Enter stop to quit");
         // now read in a line - a line is considered to be terminated by any one of
         // a line feed ('\n'), a carriage return ('\r'), or a carriage return
         // followed immediately by a linefeed.
      address = br.readLine();
       // Create character streams for the socket.
      in = new BufferedReader(new InputStreamReader(
      socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);   
      System.out.println("Please enter your desired username");
      while (true) {
      Systemout.println("SUBMITNAME");
      screenname = br.readLine();
         if (name == null) {
               return;
         }
      synchronized (names) {
         if (!names.contains(name)) {
               names.add(name);
               break;
         }
      }

      System.out.println("Letsgoooo");








      do {
      if(!strText.equalsIgnoreCase("stop")){
          strText = br.readLine();
          //System.out.println("You: " + strText);   // Echo it back out to the terminal
         }}
      while(!strText.equalsIgnoreCase("stop"));
      System.exit(0);
  }
}