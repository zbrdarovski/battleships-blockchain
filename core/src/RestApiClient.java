import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class RestApiClient {

    public static void winPlayerOne() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();
        connection.setRequestMethod("POST");

        byte[] out = "{\"recipient\":\"0458889acb5fde46b63d5e28ec2d43f350234cf75e7df7d965cea1774251cea1866f6668d68e1f79f40831b7a7f55117ec580cb94634c2504fc913a2558d756d5e\",\"amount\":\"-100\"}".getBytes();
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(out);
        os.flush();

        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            System.out.println("POST was not succeseful.");
        }
    }

    public static void winPlayerTwo() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();
        connection.setRequestMethod("POST");

        byte[] out = "{\"recipient\":\"4654a8cdbb757146700fdfda55c856c68ede0c825dd5c3dcf317688cf89a7eb39296ca128461c54e1662e3fe7fef03fd427e9381750f5182eba647755961534455\",\"amount\":\"-250\"}".getBytes();
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(out);
        os.flush();

        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            System.out.println("POST was not succeseful.");
        }
    }

    public static void buyPlayerOneShipTwo() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();
        connection.setRequestMethod("POST");

        byte[] out = "{\"recipient\":\"0458889acb5fde46b63d5e28ec2d43f350234cf75e7df7d965cea1774251cea1866f6668d68e1f79f40831b7a7f55117ec580cb94634c2504fc913a2558d756d5e\",\"amount\":\"500\"}".getBytes();
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(out);
        os.flush();


        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            System.out.println("POST was not succeseful.");
        }
    }

    public static void buyPlayerOneShipThree() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();
        connection.setRequestMethod("POST");

        byte[] out = "{\"recipient\":\"0458889acb5fde46b63d5e28ec2d43f350234cf75e7df7d965cea1774251cea1866f6668d68e1f79f40831b7a7f55117ec580cb94634c2504fc913a2558d756d5e\",\"amount\":\"1000\"}".getBytes();
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(out);
        os.flush();


        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            System.out.println("POST was not succeseful.");
        }
    }

    public static void buyPlayerOneShipFour() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();
        connection.setRequestMethod("POST");

        byte[] out = "{\"recipient\":\"0458889acb5fde46b63d5e28ec2d43f350234cf75e7df7d965cea1774251cea1866f6668d68e1f79f40831b7a7f55117ec580cb94634c2504fc913a2558d756d5e\",\"amount\":\"2000\"}".getBytes();
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(out);
        os.flush();


        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            System.out.println("POST was not succeseful.");
        }
    }

    public static void buyPlayerTwoShipTwo() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();
        connection.setRequestMethod("POST");

        byte[] out = "{\"recipient\":\"4654a8cdbb757146700fdfda55c856c68ede0c825dd5c3dcf317688cf89a7eb39296ca128461c54e1662e3fe7fef03fd427e9381750f5182eba647755961534455\",\"amount\":\"500\"}".getBytes();
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(out);
        os.flush();


        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            System.out.println("POST was not succeseful.");
        }
    }

    public static void buyPlayerTwoShipThree() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();
        connection.setRequestMethod("POST");

        byte[] out = "{\"recipient\":\"4654a8cdbb757146700fdfda55c856c68ede0c825dd5c3dcf317688cf89a7eb39296ca128461c54e1662e3fe7fef03fd427e9381750f5182eba647755961534455\",\"amount\":\"1000\"}".getBytes();
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(out);
        os.flush();


        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            System.out.println("POST was not succeseful.");
        }
    }

    public static void buyPlayerTwoShipFour() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();
        connection.setRequestMethod("POST");

        byte[] out = "{\"recipient\":\"4654a8cdbb757146700fdfda55c856c68ede0c825dd5c3dcf317688cf89a7eb39296ca128461c54e1662e3fe7fef03fd427e9381750f5182eba647755961534455\",\"amount\":\"2000\"}".getBytes();
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(out);
        os.flush();


        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            System.out.println("POST was not succeseful.");
        }
    }

    public static String getTransactions() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/transactions").openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();

            return response;
        }

        return null;
    }

    public static String getPublicKey() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/public-key").openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();

            return response;
        }
        return null;
    }

    public static String getBlocks() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/blocks").openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();

            return response;
        }
        return null;
    }

    public static String mineTransactions() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3001/mine-transactions").openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();

            return response;
        }
        return null;
    }
}



