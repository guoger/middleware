import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.Random; 

public class Server {
    /*
     * This server is grounded in the event based paradigm.
     * It does not keep an connection open, but immediately answers incoming
     *  packets and keeps a state for any client. This could be improved by
     *  discarding the state at some later point. 
     * The classic transmission:
     *  > number between 1-100 (in byte not String format)
     *  < number increased by one followed by a space and random string 
     *  > the random string in reversed case
     *  < An 'OK' if this connection was succesful otherwise a 'FAIL'
     * Example:
     *  > 25
     *  < 26' aSd0OJ3kljlio9' (okay not really random ;) )
     *  > 'AsD0oj3KLJLIO9'
     *  > 'OK'
     *  (symbols in quotes are part of a string, otherwise they are in byte form)
     */
    private static final char[] symbols = new char[62];

    static {
        // the alphabeth
        for (int idx = 0; idx < 10; ++idx)
            symbols[idx] = (char) ('0' + idx); 
        for (int idx = 10; idx < 36; ++idx)
            symbols[idx] = (char) ('a' + idx - 10);
        for (int idx = 36; idx < 62; ++idx)
            symbols[idx] = (char) ('A' + idx - 36);
    }

    private static int get_random_int(int min, int max) {
        // random number in the range min max
        // thanks to http://stackoverflow.com/questions/363681/generating-random-number-in-a-range-with-java
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    private static String create_random_string() {
        // generate strings of length 10-20 with a random alphanumeric
        //  lower/upper case combination
        // thanks to http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
        int text_len = get_random_int(10,20);
        char[] text = new char[text_len];
        for (int i = 0; i < text_len; ++i)
            text[i] = symbols[get_random_int(0,61)];        
        return new String(text);
    }

    public static String reverse_case(String text)
        // thanks to http://stackoverflow.com/questions/1729778/how-can-i-invert-the-case-of-a-string-in-java
    {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if (Character.isUpperCase(c))
            {
                chars[i] = Character.toLowerCase(c);
            }
            else if (Character.isLowerCase(c))
            {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return new String(chars);
    }

    public static void main(String[ ] args) 
    {
        DatagramSocket sock = null;
        HashMap<Integer, String> connections = new HashMap<Integer, String>();
        byte[] receive_data;
        byte[] send_data;
        int port;
        InetAddress ip_address = null;
        DatagramPacket receivePacket;
        DatagramPacket sendPacket;
        String sentence, msg;

        // network startup
        try {
            ip_address = InetAddress.getByName("127.0.0.1");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Server started");
        try {
            sock = new DatagramSocket(5000);
        } catch (IOException e) {
            System.err.println("Could not listen on socket :(");
            System.exit(1);
        }

        while (true) {
            // always wait for clients to send packets
            receive_data = new byte[1024];
            send_data = new byte[1024];
            receivePacket = new DatagramPacket(receive_data, receive_data.length);

            //receive
            try {
                sock.receive(receivePacket);
            } catch (IOException e) {
                System.err.println("Could not receive packet");
            }
            port = receivePacket.getPort();
            
            if (! connections.containsKey(port)) {
            // case 1: first packet, get number, create rand string and memorize
                if (receive_data[1] == 0) { 
                    System.out.format("<%d number '%d'\n", port, receive_data[0] & 0xFF);
                    // check message
                    String random_seq = create_random_string();
                    try {
                        connections.put(port, random_seq);
                    } catch (Exception e) {
                        System.out.format("Error on using hash map %s\n", 
                            e.toString());
                    }
                    char resp_num = (char) ((int) receive_data[0] + 1);
                    msg = resp_num + " " + random_seq;
                } else {
                    System.out.format("Error could not process message from port %d\n", 
                        port);
                    msg = "FAIL";
                }
                send_data = msg.getBytes();
                sendPacket = new DatagramPacket(send_data, send_data.length,
                    ip_address, port);
                try {
                    sock.send(sendPacket);
                } catch (IOException e) {
                    System.out.format("Could not send message\n%s\n", e.toString());
                }
                System.out.format(">%d '%s'\n", port, msg);
            }  else { 
            // case 2: second packet, compare input, to memorized string 
                sentence = new String( receivePacket.getData());
                sentence = sentence.trim();
                System.out.format("<%d '%s'\n", port, sentence);

                if (sentence.equals(reverse_case(connections.get(port))))
                    msg = "OK";
                else {
                    msg = "FAIL";
                }
                System.out.format(">%d '%s'\n", port, msg);
                connections.remove(port);
                send_data = msg.getBytes();
                sendPacket = new DatagramPacket(send_data, send_data.length, 
                    ip_address, port);
                try {
                    sock.send(sendPacket);
                } catch (IOException e) {
                    System.out.format("Could not send message\n%s\n", e.toString());
                }
            }
        }
    }
}
