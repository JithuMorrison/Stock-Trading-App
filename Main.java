//stock Trading App Code

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.StandardOpenOption;
import javafx.stage.Modality;
import java.util.concurrent.CompletableFuture;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.animation.*;
import static Jithu.A.*;
import Jithu.Mix;
import java.io.FileReader;
import javafx.collections.ObservableList;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Apistocks{
  private static final String ALPHA_VANTAGE_API_KEY = "YOUR_API_HERE";
  private String SYMBOL = "AAPL"; // Replace with the stock symbol you want to track
  StringBuilder res=new StringBuilder();
  Apistocks(String symbo){
    this.SYMBOL=symbo;
  }
  private void updateStockPrice() {
        try {
            URL url = new URL("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + SYMBOL + "&apikey=" + ALPHA_VANTAGE_API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            res=response;
            // Parse JSON response
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  public String parseStockPrice(String jsonResponse) {
      try {
          // Look for the key "05. price" in the JSON response
          int keyIndex = jsonResponse.indexOf("05. price");

          // Check if the key is present in the response
          if (keyIndex != -1) {
              // Find the value associated with the key
              int startIndex = jsonResponse.indexOf(":", keyIndex) + 1;
              int endIndex = jsonResponse.indexOf("\"", startIndex+2);
              // Extract and return the stock price
              return jsonResponse.substring(startIndex+2, endIndex).trim();
          } else {
              // Handle the case where the key is not present in the response
              return "193";
          }
      } catch (Exception e) {
          e.printStackTrace();
          return "Error parsing stock price";
      }
  }
  public String parseStocklowprice(String jsonResponse) {
      try {
          // Look for the key "05. price" in the JSON response
          int keyIndex = jsonResponse.indexOf("04. low");

          // Check if the key is present in the response
          if (keyIndex != -1) {
              // Find the value associated with the key
              int startIndex = jsonResponse.indexOf(":", keyIndex) + 1;
              int endIndex = jsonResponse.indexOf("\"", startIndex+2);
              // Extract and return the stock price
              return jsonResponse.substring(startIndex+2, endIndex).trim();
          } else {
              // Handle the case where the key is not present in the response
              return "19";
          }
      } catch (Exception e) {
          e.printStackTrace();
          return "Error parsing stock price";
      }
  }
  public String parseStockhighprice(String jsonResponse) {
      try {
          // Look for the key "05. price" in the JSON response
          int keyIndex = jsonResponse.indexOf("03. high");

          // Check if the key is present in the response
          if (keyIndex != -1) {
              // Find the value associated with the key
              int startIndex = jsonResponse.indexOf(":", keyIndex) + 1;
              int endIndex = jsonResponse.indexOf("\"", startIndex+2);
              // Extract and return the stock price
              return jsonResponse.substring(startIndex+2, endIndex).trim();
          } else {
              // Handle the case where the key is not present in the response
              return "930";
          }
      } catch (Exception e) {
          e.printStackTrace();
          return "Error parsing stock price";
      }
  }
  public String parseStockvolume(String jsonResponse) {
      try {
          // Look for the key "05. price" in the JSON response
          int keyIndex = jsonResponse.indexOf("06. volume");

          // Check if the key is present in the response
          if (keyIndex != -1) {
              // Find the value associated with the key
              int startIndex = jsonResponse.indexOf(":", keyIndex) + 1;
              int endIndex = jsonResponse.indexOf("\"", startIndex+2);
              // Extract and return the stock price
              return jsonResponse.substring(startIndex+2, endIndex).trim();
          } else {
              // Handle the case where the key is not present in the response
              return "33";
          }
      } catch (Exception e) {
          e.printStackTrace();
          return "Error parsing stock price";
      }
  }
  public String parseStockpercent(String jsonResponse) {
      try {
          // Look for the key "05. price" in the JSON response
          int keyIndex = jsonResponse.indexOf("10. change percent");

          // Check if the key is present in the response
          if (keyIndex != -1) {
              // Find the value associated with the key
              int startIndex = jsonResponse.indexOf(":", keyIndex) + 1;
              int endIndex = jsonResponse.indexOf("%", startIndex+2);
              // Extract and return the stock price
              return jsonResponse.substring(startIndex+2, endIndex).trim();
          } else {
              // Handle the case where the key is not present in the response
              return "0.0";
          }
      } catch (Exception e) {
          e.printStackTrace();
          return "Error parsing stock price";
      }
  }
  public int intege(){
    updateStockPrice();
    String stk=parseStockPrice(res.toString());
    double ppl=Double.parseDouble(stk);
    return (int)ppl;
  }
  public int integ(){
    String stk=parseStocklowprice(res.toString());    
    double ppl=Double.parseDouble(stk);
    return (int)ppl;
  }
  public int integr(){
    String stk=parseStockhighprice(res.toString());
    double ppl=Double.parseDouble(stk);
    return (int)ppl;
  }
  public int volume(){
    String stk=parseStockvolume(res.toString());    
    double ppl=Double.parseDouble(stk);
    return (int)ppl;
  }
  public double percent(){
    String stk=parseStockpercent(res.toString());
    double ppl=Double.parseDouble(stk);
    return ppl;
  }
}

class Storestockdetails{
  public static Map<String, Integer> volume = new HashMap<>();
  public static Map<String, Double> percent = new HashMap<>();
}
class StockPrice{
  public static Map<String, Integer> stockPrices;
  public static Map<String, Integer> stocklowPrices;
  public static Map<String, Integer> stockhighPrices;
  private static Random random = new Random();
  int k=0;

  public StockPrice(){
    stockPrices = new HashMap<>();
    stocklowPrices = new HashMap<>();
    stockhighPrices = new HashMap<>();
    initializeStockPrices(); // Initial update

    // Schedule a task to update the stock prices every 5 seconds (adjust as needed)
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            updateStockPrices();
        }
    }, 0, 5000);
  }

    private static void initializeStockPrices() {
      Apistocks h = new Apistocks("AAPL");
        // Initialize stock prices with random values between 100 and 1000
        stockPrices.put("AAPL",h.intege());
        stocklowPrices.put("AAPL",h.integ());
        stockhighPrices.put("AAPL",h.integr());
        Storestockdetails.volume.put("AAPL", h.volume());
        Storestockdetails.percent.put("AAPL",h.percent());

      Apistocks k = new Apistocks("GOOG");
        stockPrices.put("GOOG", k.intege());
        stocklowPrices.put("GOOG",k.integ());
        stockhighPrices.put("GOOG",k.integr());
        Storestockdetails.volume.put("GOOG", k.volume());
        Storestockdetails.percent.put("GOOG",k.percent());

      Apistocks a = new Apistocks("MSFT");
        stockPrices.put("MSFT", a.intege());
        stocklowPrices.put("MSFT",a.integ());
        stockhighPrices.put("MSFT",a.integr());
      Storestockdetails.volume.put("MSFT", a.volume());
      Storestockdetails.percent.put("MSFT",a.percent());

      Apistocks h1 = new Apistocks("AMZN");
        stockPrices.put("AMZN", h1.intege());
      stocklowPrices.put("AMZN",h1.integ());
      stockhighPrices.put("AMZN",h1.integr());
      Storestockdetails.volume.put("AMZN", h1.volume());
      Storestockdetails.percent.put("AMZN",h1.percent());

      Apistocks h2 = new Apistocks("TSLA");
        stockPrices.put("TSLA", h2.intege());
      stocklowPrices.put("TSLA",h2.integ());
      stockhighPrices.put("TSLA",h2.integr());
      Storestockdetails.volume.put("TSLA", h2.volume());
      Storestockdetails.percent.put("TSLA",h2.percent());

      Apistocks h3 = new Apistocks("Meta Platforms");
        stockPrices.put("Meta Platforms", h3.intege());
      stocklowPrices.put("Meta Platforms",h3.integ());
      stockhighPrices.put("Meta Platforms",h3.integr());
      Storestockdetails.volume.put("Meta Platforms", h3.volume());
      Storestockdetails.percent.put("Meta Platforms",h3.percent());

      Apistocks h4 = new Apistocks("IBM");
        stockPrices.put("IBM", h4.intege());
      stocklowPrices.put("IBM",h4.integ());
      stockhighPrices.put("IBM",h4.integr());
      Storestockdetails.volume.put("IBM", h4.volume());
      Storestockdetails.percent.put("IBM",h4.percent());

      Apistocks h5 = new Apistocks("WMT");
        stockPrices.put("WMT", h5.intege());
      stocklowPrices.put("WMT",h5.integ());
      stockhighPrices.put("WMT",h5.integr());
      Storestockdetails.volume.put("WMT", h5.volume());
      Storestockdetails.percent.put("WMT",h5.percent());

      Apistocks h6 = new Apistocks("MCD");
        stockPrices.put("MCD", h6.intege());
      stocklowPrices.put("MCD",h6.integ());
      stockhighPrices.put("MCD",h6.integr());
      Storestockdetails.volume.put("MCD", h6.volume());
      Storestockdetails.percent.put("MCD",h6.percent());

      Apistocks h7 = new Apistocks("V");
        stockPrices.put("V", h7.intege());
      stocklowPrices.put("V",h7.integ());
      stockhighPrices.put("V",h7.integr());
      Storestockdetails.volume.put("V", h7.volume());
      Storestockdetails.percent.put("V",h7.percent());

      Apistocks h8 = new Apistocks("NOK");
        stockPrices.put("NOK", h8.intege());
      stocklowPrices.put("NOK",h8.integ());
      stockhighPrices.put("NOK",h8.integr());
      Storestockdetails.volume.put("NOK", h8.volume());
      Storestockdetails.percent.put("NOK",h8.percent());

      Apistocks h9 = new Apistocks("NYT");
        stockPrices.put("NYT", h9.intege());
      stocklowPrices.put("NYT",h9.integ());
      stockhighPrices.put("NYT",h9.integr());
      Storestockdetails.volume.put("NYT", h9.volume());
      Storestockdetails.percent.put("NYT",h9.percent());

      Apistocks h10 = new Apistocks("RACE");
        stockPrices.put("RACE", h10.intege());
      stocklowPrices.put("RACE",h10.integ());
      stockhighPrices.put("RACE",h10.integr());
      Storestockdetails.volume.put("RACE", h10.volume());
      Storestockdetails.percent.put("RACE",h10.percent());

      Apistocks h11 = new Apistocks("PFE");
        stockPrices.put("PFE", h11.intege());
      stocklowPrices.put("PFE",h11.integ());
      stockhighPrices.put("PFE",h11.integr());
      Storestockdetails.volume.put("PFE", h11.volume());
      Storestockdetails.percent.put("PFE",h11.percent());

      Apistocks h12 = new Apistocks("OPY");
        stockPrices.put("OPY", h12.intege());
      stocklowPrices.put("OPY",h12.integ());
      stockhighPrices.put("OPY",h12.integr());
      Storestockdetails.volume.put("OPY", h12.volume());
      Storestockdetails.percent.put("OPY",h12.percent());

      /*Apistocks h13 = new Apistocks("NOK");
        stockPrices.put("NOK", h13.intege());
      stocklowPrices.put("NOK",h13.integ());
      stockhighPrices.put("NOK",h13.integr());
      Storestockdetails.volume.put("NOK", h13.volume());
      Storestockdetails.percent.put("NOK",h13.percent());

      Apistocks h14 = new Apistocks("NYT");
        stockPrices.put("NYT", h14.intege());
      stocklowPrices.put("NYT",h14.integ());
      stockhighPrices.put("NYT",h14.integr());
      Storestockdetails.volume.put("NYT", h14.volume());
      Storestockdetails.percent.put("NYT",h14.percent());

      Apistocks h15 = new Apistocks("NKE");
        stockPrices.put("NKE", h15.intege());
      stocklowPrices.put("NKE",h15.integ());
      stockhighPrices.put("NKE",h15.integr());
      Storestockdetails.volume.put("NKE", h15.volume());
      Storestockdetails.percent.put("NKE",h15.percent());

      Apistocks h16 = new Apistocks("MGM");
        stockPrices.put("MGM", h16.intege());
      stocklowPrices.put("MGM",h16.integ());
      stockhighPrices.put("MGM",h16.integr());
      Storestockdetails.volume.put("MGM", h16.volume());
      Storestockdetails.percent.put("MGM",h16.percent());*/

      printStockPrices();
    }

    private static void updateStockPrices() {
        // Update stock prices with a small random change between -50 and 50
        for (Map.Entry<String, Integer> entry : stockPrices.entrySet()) {
            int y=stocklowPrices.get(entry.getKey());
            int c=stockhighPrices.get(entry.getKey());
          int priceChange;

            if(c-y>0){
            priceChange = y+random.nextInt(c-y); // Random value between -50 and 50
            }
          else if(y-c>0){
            priceChange = y-random.nextInt(y-c); // Random value between -50 and 50
          }
          else{
            priceChange=1;
          }
          
            int currentPrice = priceChange;
            stockPrices.put(entry.getKey(), currentPrice);
        }

        printStockPrices();
    }

    private static void printStockPrices() {
        // Print stock prices to the console
        System.out.println("Stock Prices:");
        for (Map.Entry<String, Integer> entry : stockPrices.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
        Print("");
    }
}

interface Pages{
  public BorderPane getRoot();
}
class File{
  public static void Write(String filePath,String textToAppend){
  try (FileWriter fileWriter = new FileWriter(filePath, true);
       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
      bufferedWriter.write(textToAppend);
      Print("Text appended successfully.");
  } catch (IOException e) {
      Print("An error occurred while appending to the file.");
      e.printStackTrace();
  }
}
  public static void Overwrite(String filePath,String newText){
  try (FileWriter fileWriter = new FileWriter(filePath, false)) {
      // The second parameter 'false' indicates that you want to overwrite the file
      fileWriter.write(newText);
      Print("Text overwritten successfully.");
  } catch (IOException e) {
      Print("An error occurred while overwriting the file.");
      e.printStackTrace();
  }
  }
  public static void Create(String filePath,String content){
    try {
        // Create the file and write the content
        Path path = Path.of(filePath);
        Files.write(path, content.getBytes(), StandardOpenOption.CREATE);

        Print("File created successfully.");
    } catch (IOException e) {
        Print("An error occurred while creating the file.");
        e.printStackTrace();
    }
  }
}
class Conv{
  public static double StoD(String s){
    try {
        double result = Double.parseDouble(s);
        return result;
    } catch (NumberFormatException e) {
        Print("Invalid numeric format");
        e.printStackTrace();
        return 0;
    }
  }
}

class Infopage implements Pages{
  private BorderPane root;
  Button[] buttons;
  Stocks mystock=new Stocks();
  String stockname;
  double stockprice;
  String from="dash";
  double currency;
  Text[] texts;
  Text[] textr;
  private VBox companyInfoContainer = new VBox(10);

  public Infopage(Portfolio pf,String ghi){

    currency=pf.user.getBalance();
    from=ghi;

    Stocks stok = new Stocks();
    stok.addstock("TSLA",0);
    stok.addstock("AMZN",0);
    stok.addstock("AAPL",0);
    stok.addstock("GOOG",0);
    stok.addstock("MSFT",0);
    stok.addstock("Meta Platforms",0);
    stok.addstock("IBM",0);
    stok.addstock("MCD",0);
    stok.addstock("NOK",0);
    stok.addstock("NYT",0);
    stok.addstock("OPY",0);
    stok.addstock("PFE",0);
    stok.addstock("RACE",0);
    stok.addstock("V",0);
    stok.addstock("WMT",0);
    
    texts = new Text[stok.stocks.size()];
    textr = new Text[stok.stocks.size()];

    root=new BorderPane();
    ScrollPane leAnchorPane = new ScrollPane();
    leAnchorPane.setPrefHeight(600.0);
    leAnchorPane.setPrefWidth(354.0);
    leAnchorPane.setStyle("-fx-background-color: #0598ff;");

    LinearGradient linearGradient = new LinearGradient(
            0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.RED),
            new Stop(1, Color.BLUE)
    );

    ScrollPane scrollPane = new ScrollPane(companyInfoContainer);
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Set vertical scroll policy

    // Set preferred height for companyInfoContainer
    companyInfoContainer.setPrefHeight(200);

    scrollPane.setPrefHeight(500.0);
    scrollPane.setPrefWidth(345.0);
    scrollPane.setStyle("-fx-background-color: #ffffff;");

    Text currencytext = new Text(""+currency);
    currencytext.setLayoutX(344.0);

    Button myButton = new Button("Return!");
    myButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            // Open a new scene with NewPage
          Pages newPag;
          if(from.equals("port")){
            newPag = new Portfoliopg(pf);
          }
          else{
            newPag = new NewPage(pf);
          }
            Stage newstag = new Stage();
            newstag.setScene(new Scene(newPag.getRoot(), 750, 600));
            newstag.setTitle("Welcome ");
            newstag.show();
            Stage stageu = (Stage) root.getScene().getWindow();
            stageu.close();
        }
    });

    HBox menubo = new HBox();
    menubo.setPrefWidth(1000);
    menubo.setStyle("-fx-background-color: #0598ff;");
    menubo.getChildren().addAll(myButton,currencytext);

    Button appleButton = new Button("Apple");
      Button nokiaButton = new Button("Nokia");
      Button facebookButton = new Button("Facebook");
      Button amazonButton = new Button("Amazon");
      Button nytbutton = new Button("New York Times");
      Button ibmButton = new Button("IBM");
      Button googleButton = new Button("Google");
      Button oppyButton = new Button("Oppenheimer Holdings");
      Button walmartButton = new Button("Walmart");
      Button microButton = new Button("Microsoft");
      Button visaButton = new Button("Visa");
      Button ferrariButton = new Button("Ferrari");
      Button pfizerButton = new Button("Pfizer");
      Button mcdButton = new Button("McDonald's");
      Button teslaButton = new Button("Tesla");
      appleButton.setStyle("-fx-background-color: transparent; ");
        nokiaButton.setStyle("-fx-background-color: transparent; ");
        facebookButton.setStyle("-fx-background-color: transparent; ");
        amazonButton.setStyle("-fx-background-color: transparent; ");
        nytbutton.setStyle("-fx-background-color: transparent; ");
        ibmButton.setStyle("-fx-background-color: transparent; ");
        googleButton.setStyle("-fx-background-color: transparent; ");
        oppyButton.setStyle("-fx-background-color: transparent; ");
        walmartButton.setStyle("-fx-background-color: transparent; ");
        microButton.setStyle("-fx-background-color: transparent; ");
          visaButton.setStyle("-fx-background-color: transparent; ");
          ferrariButton.setStyle("-fx-background-color: transparent; ");
          pfizerButton.setStyle("-fx-background-color: transparent; ");
          mcdButton.setStyle("-fx-background-color: transparent; ");
          teslaButton.setStyle("-fx-background-color: transparent; ");

      nytbutton.setOnAction(e -> handleButtonClick("New York Times"));
      ibmButton.setOnAction(e -> handleButtonClick("IBM"));
      oppyButton.setOnAction(e -> handleButtonClick("Oppenheimer Holdings"));
        walmartButton.setOnAction(e -> handleButtonClick("Walmart"));
        microButton.setOnAction(e -> handleButtonClick("Microsoft"));
        visaButton.setOnAction(e -> handleButtonClick("Visa"));
        ferrariButton.setOnAction(e -> handleButtonClick("Ferrari"));
    pfizerButton.setOnAction(e -> handleButtonClick("Pfizer"));
      mcdButton.setOnAction(e -> handleButtonClick("McDonald's"));
      appleButton.setOnAction(e -> handleButtonClick("Apple"));
      nokiaButton.setOnAction(e -> handleButtonClick("Nokia"));
      googleButton.setOnAction(e -> handleButtonClick("Google"));
      amazonButton.setOnAction(e -> handleButtonClick("Amazon"));
      facebookButton.setOnAction(e -> handleButtonClick("Facebook"));
      teslaButton.setOnAction(e -> handleButtonClick("Tesla"));

      VBox buttonList = new VBox(10);
      buttonList.getChildren().addAll(appleButton, googleButton, amazonButton,
              nokiaButton, nytbutton, ibmButton,oppyButton, walmartButton, microButton, visaButton,ferrariButton, pfizerButton, mcdButton);

      // Set background color for the button list
      buttonList.setStyle("-fx-background-color: skyblue;");
    buttonList.setPrefHeight(600.0);
    buttonList.setPrefWidth(345.0);

    leAnchorPane.setContent(buttonList);

    Timer timero = new Timer(true);
    timero.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            for(Stock x:stok.stocks){
              int post=0;
              Integer inter=pf.stockprice.stockPrices.get(x.name);
              x.price=inter.doubleValue();
              texts[post].setText(x.price+"");
              textr[post].setText(x.price+"");
              post++;
            }
        }
    }, 0, 5000);

    root.setTop(menubo);
    root.setLeft(leAnchorPane);
    root.setCenter(scrollPane);
  }
  public BorderPane getRoot(){
    return root;
  }
  private void handleButtonClick(String stockName) {
      String fileName = "stockinfo.txt";
      String companyInfo = "";

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
          String line;

          while ((line = br.readLine()) != null) {
              if (line.startsWith(stockName)) {
                  companyInfo = "Information:\n"; // Heading
                  companyInfo += line + "\n"; // Start with the company name
                  while ((line = br.readLine()) != null && !line.isEmpty()) {
                      companyInfo += line + "\n"; // Append company information with line breaks
                  }
                  break;
              }
          }
      } catch (IOException e) {
          companyInfo = "Error reading company information.";
          e.printStackTrace();
      }

      // Clear previous labels before adding the new one
      companyInfoContainer.getChildren().clear();

      // Create a new label for the company and set wrapping and max width
      Label companyLabel = new Label(companyInfo);
      companyLabel.setWrapText(true); // Enable text wrapping
      companyLabel.setMaxWidth(companyInfoContainer.getWidth()); // Set maximum width

      // Add the label to the container
      companyInfoContainer.getChildren().add(companyLabel);
  }
}

class Transactionhist implements Pages{
  private BorderPane root;
  Button[] buttons;
  Stocks mystock=new Stocks();
  String stockname;
  double stockprice;
  String from="dash";
  double currency;
  public Transactionhist(Portfolio pf,String ghi){
    currency=pf.user.getBalance();
    from=ghi;
    String filePath = pf.user.getUsername()+"file.txt";
    Path path = Paths.get(filePath);
    try {
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] stoyk=line.split(",");
            mystock.addstock(stoyk[0],Conv.StoD(stoyk[1]));
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reading the file.");
        e.printStackTrace();
    }
    root=new BorderPane();
    AnchorPane leAnchorPane = new AnchorPane();
    leAnchorPane.setPrefHeight(500.0);
    leAnchorPane.setPrefWidth(354.0);
    leAnchorPane.setStyle("-fx-background-color: #0598ff;");

    Timer timero = new Timer(true);
    timero.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            for(Stock x:pf.mystocks.stocks){
              Integer inter=pf.stockprice.stockPrices.get(x.name);
              x.price=inter.doubleValue();
            }
        }
    }, 0, 5000);


    Text fust = new Text("");
    fust.setFill(Color.web("#0598ff"));
    fust.setLayoutX(134.0);
    fust.setLayoutY(250);
    fust.setFont(new Font(30.0));

    Text fustr = new Text("");
    fustr.setFill(Color.web("#0598ff"));
    fustr.setLayoutX(134.0);
    fustr.setLayoutY(170);
    fustr.setFont(new Font(30.0));

    LinearGradient linearGradient = new LinearGradient(
            0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.RED),
            new Stop(1, Color.BLUE)
    );
    ScrollPane scrollPane = new ScrollPane(leAnchorPane);
    AnchorPane cenAnchorPane = new AnchorPane();
    cenAnchorPane.setPrefHeight(500.0);
    cenAnchorPane.setPrefWidth(345.0);
    cenAnchorPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FF0000, #0000FF);");
    
    cenAnchorPane.getChildren().add(fust);
    
    Text currencytext = new Text(""+currency);
    currencytext.setLayoutX(344.0);

    Button myButton = new Button("Return!");
    myButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            // Open a new scene with NewPage
          Pages newPag;
          if(from.equals("port")){
            newPag = new Portfoliopg(pf);
          }
          else{
            newPag = new NewPage(pf);
          }
            Stage newstag = new Stage();
            newstag.setScene(new Scene(newPag.getRoot(), 750, 600));
            newstag.setTitle("Welcome ");
            newstag.show();
            Stage stageu = (Stage) root.getScene().getWindow();
            stageu.close();
        }
    });
    HBox menubo = new HBox();
    menubo.setPrefWidth(750);
    menubo.setStyle("-fx-background-color: #0598ff;");
    menubo.getChildren().addAll(myButton,currencytext);
    int mn=0;
    int i=0;
    buttons = new Button[mystock.stocks.size()];
    for (Stock x:mystock.stocks){
        Button mybu=new Button(x.name);
        mybu.setLayoutY(mn);
        buttons[i]=mybu;
      final int g=i;
      buttons[i].setOnAction(e -> {
          //us.setText("Stock name: "+mystock.stocks.get(g).name);
          stockname=mystock.stocks.get(g).name;
          //det.setText("Stock price: "+mystock.stocks.get(g).price);
          stockprice=mystock.stocks.get(g).price;

          String filePathet = pf.user.getUsername()+"file.txt"; // Replace with the actual path to your file

          // Use try-with-resources to ensure the file is closed properly
          try {
              // Read all lines from the file into a List<String>
              Path pathet = Paths.get(filePathet);
              List<String> lines = Files.readAllLines(pathet);

              // Process each line
              for (String line : lines) {

                String[] fly = new String[3];
                fly=line.split(",");
                if(fly[0].equals(stockname)){
                  fust.setText(fly[2]);
                  fustr.setText(stockname);
                }
                
              }
          } catch (IOException exp) {
              exp.printStackTrace();
              // Handle the exception (e.g., file not found, permission issues, etc.)
          }
      });
        i++;
        mn+=30;
        mybu.setStyle("-fx-background-color: #0598ff;");
        leAnchorPane.getChildren().addAll(mybu);
    }

    root.setTop(menubo);
    root.setLeft(scrollPane);
    root.setCenter(cenAnchorPane);
  }
  public BorderPane getRoot(){
    return root;
  }
}
class Portfoliopg implements Pages{
  private BorderPane root;
  Button[] buttons;
  Stocks mystock;
  String stockname;
  double stockprice;
  double currency;
  //private String fullText;
  //private int textWidth;
  //private int currentIndex = 0;
  public Portfoliopg(Portfolio pf){
    currency=pf.user.getBalance();
    mystock=pf.mystocks;
    root=new BorderPane();
    LinearGradient linearGradient = new LinearGradient(
            0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.RED),
            new Stop(1, Color.BLUE)
    );
    AnchorPane leAnchorPane = new AnchorPane();
    leAnchorPane.setPrefHeight(500.0);
    leAnchorPane.setPrefWidth(354.0);
    leAnchorPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FF0000, #0000FF);");

    ImageView userImage = new ImageView(new Image(getClass().getResourceAsStream("user.png")));
    userImage.setFitHeight(70);
    userImage.setFitWidth(70);
    userImage.setLayoutX(135);
    userImage.setLayoutY(60);
    leAnchorPane.getChildren().add(userImage);
    ScrollPane cenAnchorPane = new ScrollPane();
    cenAnchorPane.setPrefHeight(500.0);
    cenAnchorPane.setPrefWidth(345.0);
    VBox vb = new VBox();
    vb.setPadding(new Insets(110));
    Text currencytext = new Text(""+currency);
    currencytext.setLayoutX(344.0);
    Text usid = new Text("user Id: 21224");
    usid.setFill(Color.web("#FFFFFF"));
    usid.setLayoutX(94.0);
    usid.setLayoutY(161.0);
    usid.setFont(new Font(21.0));
    leAnchorPane.getChildren().add(usid);
    Text us = new Text("username: "+pf.user.getUsername());
    us.setFill(Color.web("#0598ff"));
    //us.setLayoutX(134.0);
    //us.setLayoutY(141.0);
    us.setFont(new Font(21.0));
    vb.getChildren().add(us);
    String mytystock="";
    for(Stock x:mystock.stocks){
      mytystock+=(x.name+",\n");
    }
    //fullText="stocks: "+mytystock;
    vb.setSpacing(50);
    vb.setAlignment(Pos.CENTER);
    Text bus = new Text("stocks: "+mytystock);
    bus.setFill(Color.web("#0598ff"));
    //bus.setLayoutX(134.0);
    //bus.setLayoutY(310.0);
    bus.setTextAlignment(TextAlignment.CENTER);
    bus.setFont(Font.font("Arial",20.0));
    vb.getChildren().add(bus);
    cenAnchorPane.setContent(vb);
    /*Timeline timeline = new Timeline(new KeyFrame(
      Duration.millis(50), // Duration between characters
      event -> {
          if (currentIndex <= fullText.length()) {
              bus.setText(fullText.substring(0, currentIndex));
              currentIndex++;
          }
      }
    ));
    timeline.setCycleCount(fullText.length() + 1);
    timeline.play();
    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1.5));
    // Set the initial text
    bus.setText(fullText);

    // Measure the width of the text
    textWidth = (int) bus.getBoundsInLocal().getWidth();

    // Create a TranslateTransition for the animation
    //bus.setTranslateX(textWidth);
    TranslateTransition translateTransition = new TranslateTransition();
    translateTransition.setNode(bus);
    translateTransition.setByX(-textWidth);
    translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
    translateTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);
    translateTransition.setDuration(Duration.seconds(4)); // Adjust duration as needed

    // Start the animation
    pauseTransition.setOnFinished(event -> translateTransition.play());
    pauseTransition.play();*/

    Button myButton = new Button("Return!");
    myButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            // Open a new scene with NewPage
            NewPage newPag = new NewPage(pf);
            Stage newstag = new Stage();
            newstag.setScene(new Scene(newPag.getRoot(), 750, 600));
            newstag.setTitle("Welcome ");
            newstag.show();
            Stage stageu = (Stage) root.getScene().getWindow();
            stageu.close();
        }
    });
    Button transist = new Button("Transaction history");
    transist.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            // Open a new scene with NewPage
            Transactionhist Tras = new Transactionhist(pf,"port");
            Stage newstag = new Stage();
            newstag.setScene(new Scene(Tras.getRoot(), 750, 600));
            newstag.setTitle("Welcome ");
            newstag.show();
            Stage stageu = (Stage) root.getScene().getWindow();
            stageu.close();
        }
    });
    vb.getChildren().add(transist);
    HBox menubo = new HBox();
    menubo.setPrefWidth(750);
    menubo.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FF0000, #0000FF);");
    menubo.getChildren().addAll(myButton,currencytext);

    root.setTop(menubo);
    root.setLeft(leAnchorPane);
    root.setCenter(cenAnchorPane);
  }
  public BorderPane getRoot(){
    return root;
  }
}
class Stockpage implements Pages{
  private BorderPane root;
  Button[] buttons;
  Stocks mystock;
  String stockname;
  double stockprice;
  double stockbuyprice;
  String from="dash";
  double currency;
  Portfolio pg;
  String userhandle="";
  Text[] texts;
  Text[] textr;

  public Stockpage(Portfolio pf,String ghi){

    currency=pf.user.getBalance();
    from=ghi;
    pg=pf;
    mystock=pf.mystocks;

    texts = new Text[mystock.stocks.size()];
    textr = new Text[mystock.stocks.size()];

    root=new BorderPane();
    AnchorPane leAnchorPane = new AnchorPane();
    leAnchorPane.setPrefHeight(500.0);
    leAnchorPane.setPrefWidth(354.0);

    LinearGradient linearGradient = new LinearGradient(
            0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.RED),
            new Stop(1, Color.BLUE)
    );

    Stop[] stops = new Stop[] {
      new Stop(0.1, javafx.scene.paint.Color.BLACK),
      new Stop(1, javafx.scene.paint.Color.DODGERBLUE)
    };

    // Create a linear gradient with the defined stops
    LinearGradient linearGradient1 = new LinearGradient(
      0,                      // startX
      0,                      // startY
      0,                      // endX
      1,                      // endY
      true,                   // proportional
      CycleMethod.NO_CYCLE,   // cycleMethod
      stops                   // stops
    );

    leAnchorPane.setBackground(new Background(new BackgroundFill(linearGradient1, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

    AnchorPane cenAnchorPane = new AnchorPane();
    cenAnchorPane.setPrefHeight(500.0);
    cenAnchorPane.setPrefWidth(345.0);
    cenAnchorPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FF0000, #0000FF);");

    Text currencytext = new Text(""+currency);
    currencytext.setLayoutX(344.0);
    currencytext.setFill(Color.web("#FFFFFF"));

    Text us = new Text("");
    us.setFill(Color.web("#0598ff"));
    us.setLayoutX(104.0);
    us.setLayoutY(141.0);
    us.setFont(new Font(21.0));
    cenAnchorPane.getChildren().add(us);

    Text yus = new Text("");
    yus.setFill(Color.web("#0598ff"));
    yus.setLayoutX(64.0);
    yus.setLayoutY(170.0);
    yus.setFont(new Font(21.0));
    cenAnchorPane.getChildren().add(yus);

    Text bus = new Text("");
    bus.setFill(Color.web("#0598ff"));
    bus.setLayoutX(64.0);
    bus.setLayoutY(310.0);
    bus.setFont(new Font(21.0));
    cenAnchorPane.getChildren().add(bus);

    Text dust = new Text("");
    dust.setFill(Color.web("#0598ff"));
    dust.setLayoutX(64.0);
    dust.setLayoutY(260.0);
    dust.setFont(new Font(21.0));
    cenAnchorPane.getChildren().add(dust);

    Text det = new Text("");
    det.setFill(Color.web("#0598ff"));
    det.setLayoutX(104.0);
    det.setLayoutY(225.0);
    det.setFont(new Font(21.0));
    cenAnchorPane.getChildren().add(det);

    Text deta = new Text("");
    deta.setFill(Color.web("#0598ff"));
    deta.setLayoutX(64.0);
    deta.setLayoutY(310.0);
    deta.setFont(new Font(21.0));
    cenAnchorPane.getChildren().add(deta);

    Text ceta = new Text("");
    ceta.setFill(Color.web("#0598ff"));
    ceta.setLayoutX(64.0);
    ceta.setLayoutY(340.0);
    ceta.setFont(new Font(21.0));
    cenAnchorPane.getChildren().add(ceta);

    Button myButton = new Button("Return!");
    myButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            // Open a new scene with NewPage
          Pages newPag;
          if(from.equals("stock")){
            newPag = new Page2(pf);
          }
          else{
            newPag = new NewPage(pf);
          }
            Stage newstag = new Stage();
            newstag.setScene(new Scene(newPag.getRoot(), 750, 600));
            newstag.setTitle("Welcome ");
            newstag.show();
            Stage stageu = (Stage) root.getScene().getWindow();
            stageu.close();
        }
    });

    HBox menubo = new HBox();
    menubo.setPrefWidth(750);
    menubo.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #000000, #555555);");
    menubo.getChildren().addAll(myButton,currencytext);
    int mn=0;
    int i=0;
    buttons = new Button[mystock.stocks.size()];

    for (Stock x:mystock.stocks){
        Button mybu=new Button(x.name);
        mybu.setLayoutY(mn);

        Text fus = new Text("");
        fus.setFill(Color.web("#ffffff"));
        fus.setLayoutX(134.0);
        fus.setLayoutY(mn+15);
        fus.setFont(new Font(15.0));
        leAnchorPane.getChildren().add(fus);
        texts[i]=fus;

        Text fusr = new Text("");
        fusr.setFill(Color.web("#ffffff"));
        fusr.setLayoutX(250.0);
        fusr.setLayoutY(mn+15);
        fusr.setFont(new Font(15.0));
        leAnchorPane.getChildren().add(fusr);
        textr[i]=fusr;

        buttons[i]=mybu;

      final int g=i;

      fus.setText(mystock.stocks.get(g).price+"");
      fusr.setText(mystock.stocks.get(g).price+"");

      buttons[i].setOnAction(e -> {
          us.setText("Stock ID: "+mystock.stocks.get(g).name);
          stockname=mystock.stocks.get(g).name;

          String namea="";
          String filePath = "stcokinfo.txt";

          try {
              // Read all lines from the file
              List<String> lines = Files.readAllLines(Paths.get(filePath));

              // Print each line
              for (String line : lines) {

                  String[] parts = line.split(";");

                  if (parts[0].equals(mystock.stocks.get(g).name)) {
                      namea=parts[1];
              }

            }
          } 
          catch (IOException exr) 
            {
              // Handle the exception (e.g., print an error message)
              exr.printStackTrace();
          }

          yus.setText("Stock name: "+namea);
          det.setText("Stock price: "+mystock.stocks.get(g).price);
          stockprice=mystock.stocks.get(g).price;
          dust.setText("Stock price(Buy): "+pf.buyprice.stocks.get(g).price);
          stockbuyprice=pf.buyprice.stocks.get(g).price;
          deta.setText("Stock volume: "+Storestockdetails.volume.get(stockname));
          ceta.setText("Change percent: "+Storestockdetails.percent.get(stockname)+"%");
          bus.setText("");
      });
        i++;
        mn+=30;
        mybu.setStyle("-fx-background-color: transparent; "+ // Set border color
                         "-fx-text-fill: white;");
        leAnchorPane.getChildren().addAll(mybu);
    }

    Timer timero = new Timer(true);
    timero.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            int post=0;
            for(Stock x:mystock.stocks){
              Integer inter=pf.stockprice.stockPrices.get(x.name);
              x.price=inter.doubleValue();
              texts[post].setText(x.price+"");
              textr[post].setText(x.price+"");
              post++;
            }
        }
    }, 0, 5000);


    Button Buy = new Button("Sell");
    Buy.setLayoutX(107.0);
    Buy.setLayoutY(365.0);
    cenAnchorPane.getChildren().add(Buy);
    Buy.setOnAction(e -> showCustomDialog().thenAccept(result -> {  
      if(result==1){
        bus.setText("You Sold "+stockname+"!");
        currency=currency+stockprice;
        pf.user.setBalance(currency);
        currencytext.setText(""+currency);
        mystock.removestock(stockname);
        pf.buyprice.removestock(stockname);
        File.Write(pf.user.getUsername()+"file.txt",stockname+","+stockprice+",Sold\n");
        String fileParth = pf.user.getUsername()+"jile.txt";
        double currentcy=0;
        Path parth = Paths.get(fileParth);
        try {
            List<String> linres = Files.readAllLines(parth);
            int kink=0;
            for(String x:linres){
              String[] arr = x.split(",");
              if(arr[0].equals(stockname)){
                break;
              }
              kink++;
            }
            String hita=linres.remove(kink);
            Files.write(parth, linres, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ei) {
            System.out.println("An error occurred while reading the file.");
            ei.printStackTrace();
        }
        Stockpage newPags = new Stockpage(pf,from);
        Stage newstagf = new Stage();
        newstagf.setScene(new Scene(newPags.getRoot(), 750, 600));
        newstagf.setTitle("Welcome ");
        newstagf.show();
        Stage stageu = (Stage) root.getScene().getWindow();
        stageu.close();
      }
    }));

    Button Trans = new Button("Transfer");
    Trans.setLayoutX(220.0);
    Trans.setLayoutY(365.0);
    cenAnchorPane.getChildren().add(Trans);
    Trans.setOnAction(e -> showCustomDialog1().thenAccept(result -> {  
      if(result==1){
        bus.setText("You Sold "+stockname+"!");
        currency=currency+stockprice;
        pf.user.setBalance(currency);
        currencytext.setText(""+currency);
        mystock.removestock(stockname);
        pf.buyprice.removestock(stockname);
        File.Write(pf.user.getUsername()+"file.txt",stockname+","+stockprice+",Sold\n");
        File.Write(userhandle+"file.txt",stockname+","+stockprice+",Bought\n");
        File.Write(userhandle+"jile.txt",stockname+","+stockprice+"\n");
        String fileParth = pf.user.getUsername()+"jile.txt";
        double currentcy=0;
        Path parth = Paths.get(fileParth);
        try {
            List<String> linres = Files.readAllLines(parth);
            int kink=0;
            for(String x:linres){
              String[] arr = x.split(",");
              if(arr[0].equals(stockname)){
                break;
              }
              kink++;
            }
            String hita=linres.remove(kink);
            Files.write(parth, linres, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ei) {
            System.out.println("An error occurred while reading the file.");
            ei.printStackTrace();
        }
        Stockpage newPags = new Stockpage(pf,from);
        Stage newstagf = new Stage();
        newstagf.setScene(new Scene(newPags.getRoot(), 750, 600));
        newstagf.setTitle("Welcome ");
        newstagf.show();
        Stage stageu = (Stage) root.getScene().getWindow();
        stageu.close();
      }
    }));

    root.setTop(menubo);
    root.setLeft(leAnchorPane);
    root.setCenter(cenAnchorPane);
  }
  public BorderPane getRoot(){
    return root;
  }
  private CompletableFuture<Integer> showCustomDialog() {
      CompletableFuture<Integer> resultFuture = new CompletableFuture<>();
      Stage dialogStage = new Stage();
      dialogStage.initModality(Modality.WINDOW_MODAL);
      GridPane gridPane = new GridPane();
      gridPane.setHgap(10);
      gridPane.setVgap(10);
      gridPane.setAlignment(Pos.CENTER);

      // First line
      Label confirmTextLabel = new Label("Confirm Text");
      gridPane.add(confirmTextLabel, 1, 0, 2, 1);

      // Second line
      Label enterPasswordLabel = new Label("Enter Password");
      gridPane.add(enterPasswordLabel, 1, 1, 2, 1);

      // Third line - Text input field
      PasswordField passwordField = new PasswordField();
      gridPane.add(passwordField, 0, 2, 2, 1);

      // Fourth line - OK and Cancel buttons side by side
      Button okButton = new Button("OK");
      Button cancelButton = new Button("Cancel");

      HBox buttonBox = new HBox(10);
      buttonBox.setAlignment(Pos.CENTER);
      buttonBox.getChildren().addAll(okButton, cancelButton);
      gridPane.add(buttonBox, 0, 3, 2, 1);

      okButton.setOnAction(e -> {
          // Perform action when OK button is clicked
        String passt=passwordField.getText();
          System.out.println("Entered password: " + passt);
          if(passt.equals(pg.user.getPassword())){
          resultFuture.complete(1);
            dialogStage.close();
          }
        else{
          showErrorAlert("Incorrect Password");
        }
      });

      cancelButton.setOnAction(e -> {
        resultFuture.complete(0);
        dialogStage.close();
      });
      dialogStage.setScene(new Scene(gridPane));
      dialogStage.setTitle("Custom Dialog");
    dialogStage.setWidth(250);
    dialogStage.setHeight(170);
      dialogStage.showAndWait();
    return resultFuture;
  }

  private CompletableFuture<Integer> showCustomDialog1() {
      CompletableFuture<Integer> resultFuture = new CompletableFuture<>();
      Stage dialogStage = new Stage();
      dialogStage.initModality(Modality.WINDOW_MODAL);
      GridPane gridPane = new GridPane();
      gridPane.setHgap(10);
      gridPane.setVgap(10);
      gridPane.setAlignment(Pos.CENTER);

      // First line
      Label confirmTextLabel = new Label("Transfer to");
      gridPane.add(confirmTextLabel, 1, 0, 2, 1);
      Label textLabel = new Label("Id");
      gridPane.add(textLabel, 0, 1, 1, 1);
      TextField text=new TextField();
      gridPane.add(text, 1, 1, 1, 1);
      // Second line
      Label enterPasswordLabel = new Label("Enter Password");
      gridPane.add(enterPasswordLabel, 1, 2, 2, 1);

      // Third line - Text input field
      PasswordField passwordField = new PasswordField();
      gridPane.add(passwordField, 0, 3, 2, 1);

      // Fourth line - OK and Cancel buttons side by side
      Button okButton = new Button("Transfer");
      Button cancelButton = new Button("Cancel");

      HBox buttonBox = new HBox(10);
      buttonBox.setAlignment(Pos.CENTER);
      buttonBox.getChildren().addAll(okButton, cancelButton);
      gridPane.add(buttonBox, 0, 4, 2, 1);

      okButton.setOnAction(e -> {
          // Perform action when OK button is clicked
        String passt=passwordField.getText();
        String usname=text.getText();
        String filePawth = "users.txt";
        boolean poss=false;
        Path pawth = Paths.get(filePawth);
        try {
            List<String> linwes = Files.readAllLines(pawth);
            for (String linwe : linwes) {
                if(linwe.equals(usname)){
                  poss=true;
                  userhandle=usname;
                  break;
                }
            }
        } catch (IOException ext) {
            System.out.println("An error occurred while reading the file.");
            ext.printStackTrace();
            return;
        }
          System.out.println("Entered password: " + passt);
        if(poss==true){
          if(passt.equals(pg.user.getPassword())){
          resultFuture.complete(1);
            dialogStage.close();
          }
        else{
          showErrorAlert("Incorrect Password");
        }
        }
        else{
          showErrorAlert("Incorrect handle");
        }
      });

      cancelButton.setOnAction(e -> {
        resultFuture.complete(0);
        dialogStage.close();
      });
      dialogStage.setScene(new Scene(gridPane));
      dialogStage.setTitle("Custom Dialog");
    dialogStage.setWidth(250);
    dialogStage.setHeight(170);
      dialogStage.showAndWait();
    return resultFuture;
  }

  private void showErrorAlert(String message) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
  }
}
class Page2 extends StackPane implements Pages{
    private BorderPane root;
    Button[] buttons;
    String stockname;
    double stockprice;
    double currency;
    Stocks mystocks;
    Text[] texts;
    Text[] textr;

    public Page2(Portfolio pf) {
      currency=pf.user.getBalance();
      mystocks=pf.mystocks;
      root=new BorderPane();

      Stocks stok = new Stocks();
      stok.addstock("TSLA",0);
      stok.addstock("AMZN",0);
      stok.addstock("AAPL",0);
      stok.addstock("GOOG",0);
      stok.addstock("MSFT",0);
      stok.addstock("Meta Platforms",0);
      stok.addstock("IBM",0);
      stok.addstock("MCD",0);
      stok.addstock("NOK",0);
      stok.addstock("NYT",0);
      stok.addstock("OPY",0);
      stok.addstock("PFE",0);
      stok.addstock("RACE",0);
      stok.addstock("V",0);
      stok.addstock("WMT",0);
      /*stok.addstock("NOK",0);
      stok.addstock("NYT",0);
      stok.addstock("NKE",0);
      stok.addstock("MGM",0);*/

      LinearGradient linearGradient = new LinearGradient(
              0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
              new Stop(0, Color.RED),
              new Stop(1, Color.BLUE)
      );

      AnchorPane leAnchorPane = new AnchorPane();
      leAnchorPane.setPrefHeight(500.0);
      leAnchorPane.setPrefWidth(354.0);

      Stop[] stops = new Stop[] {
        new Stop(0.1, javafx.scene.paint.Color.BLACK),
        new Stop(1, javafx.scene.paint.Color.DODGERBLUE)
      };

      // Create a linear gradient with the defined stops
      LinearGradient linearGradient1 = new LinearGradient(
        0,                      // startX
        0,                      // startY
        0,                      // endX
        1,                      // endY
        true,                   // proportional
        CycleMethod.NO_CYCLE,   // cycleMethod
        stops                   // stops
      );

      leAnchorPane.setBackground(new Background(new BackgroundFill(linearGradient1, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

      AnchorPane cenAnchorPane = new AnchorPane();
      cenAnchorPane.setPrefHeight(500.0);
      cenAnchorPane.setPrefWidth(345.0);

      Button myButton = new Button("Return!");
      HBox menubo = new HBox();
      menubo.setPrefWidth(750);
      menubo.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #000000, #555555);");

      Text us = new Text("");
      us.setFill(Color.web("#0598ff"));
      us.setLayoutX(104.0);
      us.setLayoutY(141.0);
      us.setFont(new Font(21.0));
      cenAnchorPane.getChildren().add(us);

      Text yus = new Text("");
      yus.setFill(Color.web("#0598ff"));
      yus.setLayoutX(64.0);
      yus.setLayoutY(170.0);
      yus.setFont(new Font(21.0));
      cenAnchorPane.getChildren().add(yus);

      Text bus = new Text("");
      bus.setFill(Color.web("#0598ff"));
      bus.setLayoutX(104.0);
      bus.setLayoutY(340.0);
      bus.setFont(new Font(21.0));
      cenAnchorPane.getChildren().add(bus);

      Text det = new Text("");
      det.setFill(Color.web("#0598ff"));
      det.setLayoutX(104.0);
      det.setLayoutY(225.0);
      det.setFont(new Font(21.0));
      cenAnchorPane.getChildren().add(det);

      Text deta = new Text("");
      deta.setFill(Color.web("#0598ff"));
      deta.setLayoutX(64.0);
      deta.setLayoutY(260.0);
      deta.setFont(new Font(21.0));
      cenAnchorPane.getChildren().add(deta);

      Text cet = new Text("");
      cet.setFill(Color.web("#0598ff"));
      cet.setLayoutX(64.0);
      cet.setLayoutY(310.0);
      cet.setFont(new Font(21.0));
      cenAnchorPane.getChildren().add(cet);

      Text currencytext = new Text(""+currency);
      currencytext.setLayoutX(344.0);
      currencytext.setFill(Color.web("#FFFFFF"));

      Button mystok = new Button("My stocks");
      mystok.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {

              // Open a new scene with NewPage
              Stockpage newPag = new Stockpage(pf,"stock");
              Stage newstag = new Stage();
              newstag.setScene(new Scene(newPag.getRoot(), 750, 600));
              newstag.setTitle("Welcome ");
              newstag.show();
              Stage stageu = (Stage) root.getScene().getWindow();
              stageu.close();
          }
      });

      Button Buy = new Button("Buy");
      Buy.setLayoutX(210.0);
      Buy.setLayoutY(365.0);
      cenAnchorPane.getChildren().add(Buy);
      Buy.setOnAction(e -> {        
        if(stockprice<=currency){
          bus.setText("You bought "+stockname+"!");
          currency=currency-stockprice;
          pf.user.setBalance(currency);
          currencytext.setText(""+currency);
          mystocks.addstock(stockname,stockprice);
          pf.buyprice.addstock(stockname,stockprice);
          File.Write(pf.user.getUsername()+"file.txt",stockname+","+stockprice+",Bought\n");
          File.Write(pf.user.getUsername()+"jile.txt",stockname+","+stockprice+"\n");
        }
        else{
          bus.setText("Not enough currency!");
        }

      });

      Button Add = new Button("Add");
      Add.setLayoutX(130.0);
      Add.setLayoutY(365.0);
      cenAnchorPane.getChildren().add(Add);
      Add.setOnAction(e -> {        
        if(stockprice<=currency){

          bus.setText("Stock added!");

          pf.addrt.addstock(stockname,stockprice);
              File.Write(pf.user.getUsername()+"watch.txt",stockname+","+stockprice+",Bought\n");
        }
        else{
          bus.setText("Stock not added!");
        }

      });

      int mn=0;
      int i=0;
      cenAnchorPane.getChildren().add(myButton);

      buttons = new Button[stok.stocks.size()];

      texts = new Text[stok.stocks.size()];
      textr = new Text[stok.stocks.size()];

      for (Stock x:stok.stocks){
          Button mybu=new Button(x.name);
          mybu.setLayoutY(mn);

          Text fus = new Text("");
          fus.setFill(Color.web("#ffffff"));
          fus.setLayoutX(134.0);
          fus.setLayoutY(mn+15);
          fus.setFont(new Font(15.0));
          leAnchorPane.getChildren().add(fus);
          texts[i]=fus;

          Text fusr = new Text("");
          fusr.setFill(Color.web("#ffffff"));
          fusr.setLayoutX(250.0);
          fusr.setLayoutY(mn+15);
          fusr.setFont(new Font(15.0));
          leAnchorPane.getChildren().add(fusr);
          textr[i]=fusr;

          buttons[i]=mybu;

        final int g=i;

        fus.setText(stok.stocks.get(g).price+"");
        fusr.setText(stok.stocks.get(g).price+"");

        buttons[i].setOnAction(e -> {
            us.setText("Stock ID: "+stok.stocks.get(g).name);
            String namea="";
            String filePath = "stcokinfo.txt";

            try {
                // Read all lines from the file
                List<String> lines = Files.readAllLines(Paths.get(filePath));

                // Print each line
                for (String line : lines) {

                    String[] parts = line.split(";");

                    if (parts[0].equals(stok.stocks.get(g).name)) {
                        namea=parts[1];
                }

              }
            } 
            catch (IOException exr) 
              {
                // Handle the exception (e.g., print an error message)
                exr.printStackTrace();
            }

            yus.setText("Stock name: "+namea);
            stockname=stok.stocks.get(g).name;
            det.setText("Stock price: "+stok.stocks.get(g).price);
            stockprice=stok.stocks.get(g).price;
            bus.setText("");
            deta.setText("Stock volume: "+Storestockdetails.volume.get(stockname));
            cet.setText("Change percent: "+Storestockdetails.percent.get(stockname)+"%");

        });
          i++;
          mn+=30;
          mybu.setStyle("-fx-background-color: transparent; "+ // Set border color
                           "-fx-text-fill: white;");
          leAnchorPane.getChildren().addAll(mybu);
      }

      Timer timero = new Timer(true);
      timero.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {

              int post=0;

              for(Stock x:stok.stocks){
                Integer inter=pf.stockprice.stockPrices.get(x.name);
                x.price=inter.doubleValue();
                texts[post].setText(x.price+"");
                textr[post].setText(x.price+"");
                post++;
              }
          }
      }, 0, 5000);

      root.setCenter(cenAnchorPane);
      root.setLeft(leAnchorPane);

        myButton.setOnAction(e -> {
          Stage dashb = new Stage();
          NewPage dash = new NewPage(pf);
          dashb.setScene(new Scene(dash.getRoot(), 750, 600));
          dashb.setTitle("Stock Trading App");
          dashb.show();
          Stage stageu = (Stage) root.getScene().getWindow();
          stageu.close();
        });
        menubo.getChildren().addAll(myButton,currencytext,mystok);
        root.setTop(menubo);

    }
    public BorderPane getRoot() {
      return root;
  }
}
class NewPage implements Pages{

    private BorderPane root;
    private Pane slidingPane;
    private static final int NUM_STAGES = 4;
    private static final String[] STAGE_LABELS = {"Intro", "Chart", "Stag3", "Stag4"};
    String username;
    double currency;
    Stocks mystocks;
    Portfolio pf;

    public NewPage(Portfolio pfy) {
      pf=pfy;
      username=pf.user.getUsername();
      mystocks=pf.mystocks;
      currency=pf.user.getBalance();
      root = new BorderPane();
      slidingPane = createSlidingPane();

      Stop[] stops = new Stop[] {
        new Stop(0.1, javafx.scene.paint.Color.BLACK),
        new Stop(0.5, javafx.scene.paint.Color.DEEPPINK),
        new Stop(1, javafx.scene.paint.Color.ORANGE)
      };

      // Create a linear gradient with the defined stops
      LinearGradient linearGradient = new LinearGradient(
        0,                      // startX
        0,                      // startY
        0,                      // endX
        1,                      // endY
        true,                   // proportional
        CycleMethod.NO_CYCLE,   // cycleMethod
        stops                   // stops
      );

      LineChart<Number, Number> lineChart = createLineChart();

      AnchorPane centerAnchorPane = new AnchorPane();
      centerAnchorPane.setPrefHeight(500.0);
      centerAnchorPane.setPrefWidth(345.0);

      Text welcomeText = new Text("Welcome, " + username + "!");
      welcomeText.setLayoutX(225.0);
      welcomeText.setLayoutY(286.0);

      HBox menubox = new HBox();
      menubox.setPrefWidth(750);
      menubox.setStyle("-fx-background-color: linear-gradient(to bottom, #000000, #333333);");

      Button menuButton = new Button("Menu");
      menuButton.setOnAction(e -> toggleSlidingWindow());

      Text currencytext = new Text(""+currency);
      currencytext.setFill(Color.web("#FFFFFF"));

      menubox.getChildren().addAll(menuButton,currencytext);
      root.setTop(menubox);

      // Add the LineChart to the AnchorPane
        AnchorPane.setTopAnchor(lineChart, 50.0);
        AnchorPane.setLeftAnchor(lineChart, -350.0);
      centerAnchorPane.getChildren().add(lineChart);

      Button back=new Button("Logout");
      back.setLayoutX(250.0);
      back.setLayoutY(325.0);
      back.setStyle("-fx-background-color: #0598ff;");

      ImageView userImage = new ImageView(new Image(getClass().getResourceAsStream("help.png")));
      userImage.setFitHeight(200);
      userImage.setFitWidth(200);
      userImage.setLayoutX(165);
      userImage.setLayoutY(50);
      centerAnchorPane.getChildren().add(userImage);

      centerAnchorPane.getChildren().addAll(welcomeText,back);

      Pane[] centerContents = new Pane[NUM_STAGES];
      centerContents[0]=centerAnchorPane;
      for (int i = 1; i < NUM_STAGES; i++) {

          if(i==1){
            centerContents[i] = createCenterContent1(STAGE_LABELS[i]);
          }
          else if(i==2){
            centerContents[i] = createCenterContent2(STAGE_LABELS[i]);
          }
          else if(i==3){
            centerContents[i] = createCenterContent3(STAGE_LABELS[i]);
          }
        else{
          centerContents[i] = createCenterContent(STAGE_LABELS[i]);
        }
      }

      // Bottom pane (TilePane)
      TilePane tilePane = createTilePane(centerContents);

      root.setBottom(tilePane);

      back.setOnAction(e -> {
          // Show the login page again
          String fileParth = username+".txt";
          double currentcy=0;
          Path parth = Paths.get(fileParth);
          try {
              List<String> linres = Files.readAllLines(parth);
              linres.set(3,""+currency);
              Files.write(parth, linres, StandardOpenOption.TRUNCATE_EXISTING);
          } catch (IOException ei) {
              System.out.println("An error occurred while reading the file.");
              ei.printStackTrace();
          }

          Stage loginStage = new Stage();
          Main loginApp = new Main();
          loginApp.start(loginStage);
          Stage stageu = (Stage) root.getScene().getWindow();
          stageu.close();
      });
        root.setCenter(centerAnchorPane);
        root.setLeft(slidingPane);
    }
    public BorderPane getRoot() {
        return root;
    }
  private LineChart<Number, Number> createLineChart() {
      // Create X and Y axes
      NumberAxis xAxis = new NumberAxis();
      NumberAxis yAxis = new NumberAxis();

      // Create the line chart with the axes
      LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
      lineChart.setTitle("Welcome to JOJAJI stock trading application");

      // Create a series and add data points
      XYChart.Series<Number, Number> series = new XYChart.Series<>();
      series.getData().add(new XYChart.Data<>(0, 20));
      series.getData().add(new XYChart.Data<>(1, 23));
      series.getData().add(new XYChart.Data<>(2, 14));
      series.getData().add(new XYChart.Data<>(3, 15));
      series.getData().add(new XYChart.Data<>(4, 24));
      series.getData().add(new XYChart.Data<>(5, 34));


    XYChart.Series<Number, Number> series1 = new XYChart.Series<>();

    // Add data to the series
    series1.getData().add(new XYChart.Data<>(0, 0));
    series1.getData().add(new XYChart.Data<>(1, 10));
    series1.getData().add(new XYChart.Data<>(2, 45));
    series1.getData().add(new XYChart.Data<>(3, 85));
    series1.getData().add(new XYChart.Data<>(4, 60));


    XYChart.Series<Number, Number> series2 = new XYChart.Series<>();

    // Add data to the series
    series2.getData().add(new XYChart.Data<>(0, 17));
    series2.getData().add(new XYChart.Data<>(1, 23));
    series2.getData().add(new XYChart.Data<>(2, 45));
    series2.getData().add(new XYChart.Data<>(3, 41));
    series2.getData().add(new XYChart.Data<>(4, 25));


    XYChart.Series<Number, Number> series3 = new XYChart.Series<>();

    // Add data to the series
    series3.getData().add(new XYChart.Data<>(0, 10));
    series3.getData().add(new XYChart.Data<>(1, 66));
    series3.getData().add(new XYChart.Data<>(2, 3));
    series3.getData().add(new XYChart.Data<>(3, 5));
    series3.getData().add(new XYChart.Data<>(4, 39));


    XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
    series4.getData().add(new XYChart.Data<>(0, 23));
    series4.getData().add(new XYChart.Data<>(1, 27));
    series4.getData().add(new XYChart.Data<>(2, 34));
    series4.getData().add(new XYChart.Data<>(3, 45));
    series4.getData().add(new XYChart.Data<>(4, 54));
    series4.getData().add(new XYChart.Data<>(5, 34));

      // Add the series to the chart
    lineChart.getData().add(series);
    lineChart.getData().add(series1);
    lineChart.getData().add(series2);
    lineChart.getData().add(series3);
    lineChart.getData().add(series4);


      return lineChart;
  }
    private Pane createSlidingPane() {
      VBox slidingContent = new VBox();

      slidingContent.setPrefWidth(345);
      slidingContent.setPrefHeight(600);

      Stop[] stops = new Stop[] {
              new Stop(0.1, javafx.scene.paint.Color.BLACK),
              new Stop(0.5, javafx.scene.paint.Color.DEEPPINK),
              new Stop(1, javafx.scene.paint.Color.ORANGE)
      };

      // Create a linear gradient with the defined stops
      LinearGradient linearGradient = new LinearGradient(
              0,                      // startX
              0,                      // startY
              0,                      // endX
              1,                      // endY
              true,                   // proportional
              CycleMethod.NO_CYCLE,   // cycleMethod
              stops                   // stops
      );

      slidingContent.setStyle("-fx-background-color: linear-gradient(to bottom, #333333, #555555);");

      // Add your menu items or other content to the sliding pane
      Button item1 = new Button("Buy  stocks");
      Button item2 = new Button("Sell Stocks");
      Button item3 = new Button("Portfolio");
      Button item4 = new Button("Transaction history");
      Button item5 = new Button("Stock Info");

      slidingContent.setAlignment(Pos.CENTER);

      item1.setStyle("-fx-background-color: transparent; "+ // Set border color
                       "-fx-text-fill: white;" );
      item2.setStyle("-fx-background-color: transparent; "+ // Set border color
                       "-fx-text-fill: white;" );
      item3.setStyle("-fx-background-color: transparent; "+ // Set border color
                       "-fx-text-fill: white;" );
      item4.setStyle("-fx-background-color: transparent; "+ // Set border color
                       "-fx-text-fill: white;" );
      item5.setStyle("-fx-background-color: transparent; "+ // Set border color
         "-fx-text-fill: white;" );

    item1.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            // Open a new scene with NewPage
            Page2 newPag = new Page2(pf);
            Stage newStag = new Stage();
            newStag.setScene(new Scene(newPag.getRoot(), 750, 600));
            newStag.setTitle("Welcome ");
            newStag.show();
            Stage stageu = (Stage) root.getScene().getWindow();
            stageu.close();
        }
    });

      item2.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {

              // Open a new scene with NewPage
              Stockpage stockpg = new Stockpage(pf,"dash");
              Stage newStags = new Stage();
              newStags.setScene(new Scene(stockpg.getRoot(), 750, 600));
              newStags.setTitle("Welcome ");
              newStags.show();
              Stage stageu = (Stage) root.getScene().getWindow();
              stageu.close();
          }
      });

      item3.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {

              // Open a new scene with NewPage
              Portfoliopg portpg = new Portfoliopg(pf);
              Stage newStaga = new Stage();
              newStaga.setScene(new Scene(portpg.getRoot(), 750, 600));
              newStaga.setTitle("Welcome ");
              newStaga.show();
              Stage stageu = (Stage) root.getScene().getWindow();
              stageu.close();
          }
      });

      item4.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {

              // Open a new scene with NewPage
              Transactionhist tras = new Transactionhist(pf,"dash");
              Stage newStaga = new Stage();
              newStaga.setScene(new Scene(tras.getRoot(), 750, 600));
              newStaga.setTitle("Welcome ");
              newStaga.show();
              Stage stageu = (Stage) root.getScene().getWindow();
              stageu.close();
          }
      });

      item5.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {

              // Open a new scene with NewPage
              Infopage trash = new Infopage(pf,"dash");
              Stage newStagas = new Stage();
              newStagas.setScene(new Scene(trash.getRoot(), 750, 600));
              newStagas.setTitle("Welcome ");
              newStagas.show();
              Stage stageuy = (Stage) root.getScene().getWindow();
              stageuy.close();
          }
      });

      slidingContent.getChildren().addAll(item1, item2, item3,item4,item5);

      Pane pane = new Pane(slidingContent);
      pane.setTranslateX(-347); 
      pane.setPrefWidth(347);
      pane.setPrefHeight(600);
      // Initial position outside the scene

      return pane;
  }
      private StackPane createCenterContent(String label) {
          StackPane centerContent = new StackPane();
          centerContent.getChildren().add(new Button(label));
          return centerContent;
      }

  private AnchorPane createCenterContent1(String label) {
      AnchorPane centerContent = new AnchorPane();
      centerContent.setStyle("-fx-background-color: lightgreen;");
      int mn=100;

      Text heol = new Text("Watchlist!");
      heol.setLayoutX(140);
      heol.setLayoutY(60);

    centerContent.getChildren().add(heol);
    
      Stocks shk = pf.addrt;
      for(Stock x:shk.stocks){

          Button buttoni = new Button(x.name);
          buttoni.setLayoutX(60);
          buttoni.setLayoutY(mn);
          buttoni.setStyle("-fx-background-color: transparent; ");

          Text tec = new Text(pf.stockprice.stockPrices.get(x.name)+"");
          tec.setLayoutX(220);
          tec.setLayoutY(mn+17);

          Text tect = new Text(pf.stockprice.stockPrices.get(x.name)+"");
          tect.setLayoutX(270);
          tect.setLayoutY(mn+17);


          Timer timeror = new Timer(true);
          timeror.scheduleAtFixedRate(new TimerTask() {
              @Override
              public void run() {
                    int post=0;
                    Integer inter=pf.stockprice.stockPrices.get(x.name);
                  try{
                    x.price=inter.doubleValue();
                  }
                  catch(Exception ef){
                      System.out.println("An error occurred while reading the file.");
                      x.price=0.0;
                  }
                    tec.setText(x.price+"");
                    tect.setText(x.price+"");
                    post++;
              }
          }, 0, 5000);

          centerContent.getChildren().add(buttoni);
          centerContent.getChildren().add(tec);
          centerContent.getChildren().add(tect);


          mn+=30;
      }
      return centerContent;
  }

  private AnchorPane createCenterContent2(String label) {
      AnchorPane centerContent = new AnchorPane();
      int mn=100;


      centerContent.setStyle("-fx-background-color: lightblue;");
        Text heo = new Text("Low stocks!");
        heo.setLayoutX(130);
        heo.setLayoutY(60);

        Text lo = new Text("High stocks!");
        lo.setLayoutX(-250);
        lo.setLayoutY(60);

    centerContent.getChildren().add(lo);
    centerContent.getChildren().add(heo);
        // Convert the Map to a List of Map.Entry
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(pf.stockprice.stockPrices.entrySet());

        // Use Collections.sort to sort the List based on values
        Collections.sort(entryList, Map.Entry.comparingByValue());

        int five=0;

        Stocks stlk = new Stocks();
    
        for (Map.Entry<String, Integer> entry : entryList) {

          stlk.addstock(entry.getKey(),entry.getValue());
          
          five++;
          if(five==5){
            break;
          }
        }
    

      for(Stock x : stlk.stocks){

          Button buttoni = new Button(x.name);
          buttoni.setLayoutX(90);
          buttoni.setLayoutY(mn);
          buttoni.setStyle("-fx-background-color: transparent; ");

          Text tec = new Text(pf.stockprice.stockPrices.get(x.name)+"");
          tec.setLayoutX(220);
          tec.setLayoutY(mn+17);

          Text tect = new Text(pf.stockprice.stockPrices.get(x.name)+"");
          tect.setLayoutX(270);
          tect.setLayoutY(mn+17);

          Timer timeror = new Timer(true);
          timeror.scheduleAtFixedRate(new TimerTask() {
              @Override
              public void run() {
                    int post=0;
                    Integer inter=pf.stockprice.stockPrices.get(x.name);
                    x.price=inter.doubleValue();
                    tec.setText(x.price+"");
                    tect.setText(x.price+"");
                    post++;
              }
          }, 0, 5000);

          centerContent.getChildren().add(buttoni);
          centerContent.getChildren().add(tec);
          centerContent.getChildren().add(tect);


          mn+=30;
      }


    entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

      int fivet=0;

      Stocks stlky = new Stocks();

      for (Map.Entry<String, Integer> entry : entryList) {

        stlky.addstock(entry.getKey(),entry.getValue());

        fivet++;
        if(fivet==5){
          break;
        }
      }

    mn=100;
    
    for(Stock x : stlky.stocks){

        Button buttoni = new Button(x.name);
        buttoni.setLayoutX(-310);
        buttoni.setLayoutY(mn);
        buttoni.setStyle("-fx-background-color: transparent; ");

        Text tec = new Text(pf.stockprice.stockPrices.get(x.name)+"");
        tec.setLayoutX(-180);
        tec.setLayoutY(mn+17);

        Text tect = new Text(pf.stockprice.stockPrices.get(x.name)+"");
        tect.setLayoutX(-130);
        tect.setLayoutY(mn+17);

        Timer timeror = new Timer(true);
        timeror.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                  int post=0;
                  Integer inter=pf.stockprice.stockPrices.get(x.name);
                  x.price=inter.doubleValue();
                  tec.setText(x.price+"");
                  tect.setText(x.price+"");
                  post++;
            }
        }, 0, 5000);

        centerContent.getChildren().add(buttoni);
        centerContent.getChildren().add(tec);
        centerContent.getChildren().add(tect);

        mn+=30;
    }
      return centerContent;
  }

  private AnchorPane createCenterContent3(String label) {
      AnchorPane centerContent = new AnchorPane();
      Button appleButton = new Button("Apple");
        Button nokiaButton = new Button("Nokia");
        Button facebookButton = new Button("Facebook");
        Button amazonButton = new Button("Amazon");
        Button nytbutton = new Button("New York Times");
        Button ibmButton = new Button("IBM");
        Button googleButton = new Button("Google");
        Button oppyButton = new Button("Oppenheimer Holdings");
        Button walmartButton = new Button("Walmart");
        Button microButton = new Button("Microsoft");
        Button visaButton = new Button("Visa");
        Button ferrariButton = new Button("Ferrari");
        Button pfizerButton = new Button("Pfizer");
        Button mcdButton = new Button("McDonald's");
        Button teslaButton = new Button("Tesla");
          appleButton.setStyle("-fx-background-color: transparent; ");
          nokiaButton.setStyle("-fx-background-color: transparent; ");
          facebookButton.setStyle("-fx-background-color: transparent; ");
          amazonButton.setStyle("-fx-background-color: transparent; ");
          nytbutton.setStyle("-fx-background-color: transparent; ");
          ibmButton.setStyle("-fx-background-color: transparent; ");
          googleButton.setStyle("-fx-background-color: transparent; ");
          oppyButton.setStyle("-fx-background-color: transparent; ");
          walmartButton.setStyle("-fx-background-color: transparent; ");
          microButton.setStyle("-fx-background-color: transparent; ");
            visaButton.setStyle("-fx-background-color: transparent; ");
            ferrariButton.setStyle("-fx-background-color: transparent; ");
            pfizerButton.setStyle("-fx-background-color: transparent; ");
            mcdButton.setStyle("-fx-background-color: transparent; ");
            teslaButton.setStyle("-fx-background-color: transparent; ");

        final LineChart<Number, Number> lineChar=new LineChart<>(new NumberAxis(), new NumberAxis());
    
        nytbutton.setOnAction(e -> {
          handleButtonClick("New York Times",lineChar);
        });
        ibmButton.setOnAction(e -> handleButtonClick1("IBM",lineChar));
        oppyButton.setOnAction(e -> handleButtonClick2("Oppenheimer Holdings",lineChar));
          walmartButton.setOnAction(e -> handleButtonClick3("Walmart",lineChar));
          microButton.setOnAction(e -> handleButtonClick4("Microsoft",lineChar));
          visaButton.setOnAction(e -> handleButtonClick5("Visa",lineChar));
          ferrariButton.setOnAction(e -> handleButtonClick6("Ferrari",lineChar));
      pfizerButton.setOnAction(e -> handleButtonClick7("Pfizer",lineChar));
        mcdButton.setOnAction(e -> handleButtonClick8("McDonald's",lineChar));
        appleButton.setOnAction(e -> handleButtonClick9("Apple",lineChar));
        nokiaButton.setOnAction(e -> handleButtonClick10("Nokia",lineChar));
        googleButton.setOnAction(e -> handleButtonClick11("Google",lineChar));
        amazonButton.setOnAction(e -> handleButtonClick12("Amazon",lineChar));
        facebookButton.setOnAction(e -> handleButtonClick13("Facebook",lineChar));
        teslaButton.setOnAction(e -> handleButtonClick14("Tesla",lineChar));

        VBox buttonList = new VBox(10);
        buttonList.getChildren().addAll(appleButton, googleButton, amazonButton,
                nokiaButton, nytbutton, ibmButton,oppyButton, walmartButton, microButton, visaButton,ferrariButton, pfizerButton, mcdButton);

        // Set background color for the button list
        buttonList.setStyle("-fx-background-color: lightgreen;");
    buttonList.setLayoutX(-347);
      buttonList.setPrefHeight(516);
      buttonList.setMinHeight(516);
    lineChar.setLayoutX(-150);
    centerContent.getChildren().add(buttonList);
    centerContent.getChildren().add(lineChar);
      return centerContent;
  }

 private void handleButtonClick(String stockName,LineChart<Number,Number> lineChar) {
    
    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it*/
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 20));
    newSeries.getData().add(new XYChart.Data<>(1, 23));
    newSeries.getData().add(new XYChart.Data<>(2, 14));
    newSeries.getData().add(new XYChart.Data<>(3, 15));
    newSeries.getData().add(new XYChart.Data<>(4, 24));
    newSeries.getData().add(new XYChart.Data<>(5, 34));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick1(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 40));
    newSeries.getData().add(new XYChart.Data<>(1, 50));
    newSeries.getData().add(new XYChart.Data<>(2, 39));
    newSeries.getData().add(new XYChart.Data<>(3, 25));
    newSeries.getData().add(new XYChart.Data<>(4, 64));
    newSeries.getData().add(new XYChart.Data<>(5, 60));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick2(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 17));
    newSeries.getData().add(new XYChart.Data<>(1, 28));
    newSeries.getData().add(new XYChart.Data<>(2, 42));
    newSeries.getData().add(new XYChart.Data<>(3, 56));
    newSeries.getData().add(new XYChart.Data<>(4, 87));
    newSeries.getData().add(new XYChart.Data<>(5, 60));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick3(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 69));
    newSeries.getData().add(new XYChart.Data<>(1, 50));
    newSeries.getData().add(new XYChart.Data<>(2, 10));
    newSeries.getData().add(new XYChart.Data<>(3, 45));
    newSeries.getData().add(new XYChart.Data<>(4, 18));
    newSeries.getData().add(new XYChart.Data<>(5, 60));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick4(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 33));
    newSeries.getData().add(new XYChart.Data<>(1, 96));
    newSeries.getData().add(new XYChart.Data<>(2, 84));
    newSeries.getData().add(new XYChart.Data<>(3, 77));
    newSeries.getData().add(new XYChart.Data<>(4, 61));
    newSeries.getData().add(new XYChart.Data<>(5, 25));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick5(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 23));
    newSeries.getData().add(new XYChart.Data<>(1, 47));
    newSeries.getData().add(new XYChart.Data<>(2, 82));
    newSeries.getData().add(new XYChart.Data<>(3, 11));
    newSeries.getData().add(new XYChart.Data<>(4, 19));
    newSeries.getData().add(new XYChart.Data<>(5, 60));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick6(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 17));
    newSeries.getData().add(new XYChart.Data<>(1, 28));
    newSeries.getData().add(new XYChart.Data<>(2, 42));
    newSeries.getData().add(new XYChart.Data<>(3, 56));
    newSeries.getData().add(new XYChart.Data<>(4, 87));
    newSeries.getData().add(new XYChart.Data<>(5, 60));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick16(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 15));
    newSeries.getData().add(new XYChart.Data<>(1, 10));
    newSeries.getData().add(new XYChart.Data<>(2, 57));
    newSeries.getData().add(new XYChart.Data<>(3, 78));
    newSeries.getData().add(new XYChart.Data<>(4, 12));
    newSeries.getData().add(new XYChart.Data<>(5, 23));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick7(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 34));
    newSeries.getData().add(new XYChart.Data<>(1, 45));
    newSeries.getData().add(new XYChart.Data<>(2, 56));
    newSeries.getData().add(new XYChart.Data<>(3, 78));
    newSeries.getData().add(new XYChart.Data<>(4, 67));
    newSeries.getData().add(new XYChart.Data<>(5, 90));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick8(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 40));
    newSeries.getData().add(new XYChart.Data<>(1, 50));
    newSeries.getData().add(new XYChart.Data<>(2, 60));
    newSeries.getData().add(new XYChart.Data<>(3, 90));
    newSeries.getData().add(new XYChart.Data<>(4, 81));
    newSeries.getData().add(new XYChart.Data<>(5, 77));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick9(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 70));
    newSeries.getData().add(new XYChart.Data<>(1, 28));
    newSeries.getData().add(new XYChart.Data<>(2, 45));
    newSeries.getData().add(new XYChart.Data<>(3, 58));
    newSeries.getData().add(new XYChart.Data<>(4, 50));
    newSeries.getData().add(new XYChart.Data<>(5, 66));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick10(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 29));
    newSeries.getData().add(new XYChart.Data<>(1, 36));
    newSeries.getData().add(new XYChart.Data<>(2, 68));
    newSeries.getData().add(new XYChart.Data<>(3, 45));
    newSeries.getData().add(new XYChart.Data<>(4, 98));
    newSeries.getData().add(new XYChart.Data<>(5, 61));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick11(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 27));
    newSeries.getData().add(new XYChart.Data<>(1, 38));
    newSeries.getData().add(new XYChart.Data<>(2, 14));
    newSeries.getData().add(new XYChart.Data<>(3, 67));
    newSeries.getData().add(new XYChart.Data<>(4, 80));
    newSeries.getData().add(new XYChart.Data<>(5, 77));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick12(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 78));
    newSeries.getData().add(new XYChart.Data<>(1, 94));
    newSeries.getData().add(new XYChart.Data<>(2, 39));
    newSeries.getData().add(new XYChart.Data<>(3, 95));
    newSeries.getData().add(new XYChart.Data<>(4, 64));
    newSeries.getData().add(new XYChart.Data<>(5, 60));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick13(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 17));
    newSeries.getData().add(new XYChart.Data<>(1, 98));
    newSeries.getData().add(new XYChart.Data<>(2, 88));
    newSeries.getData().add(new XYChart.Data<>(3, 24));
    newSeries.getData().add(new XYChart.Data<>(4, 46));
    newSeries.getData().add(new XYChart.Data<>(5, 66));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick14(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 24));
    newSeries.getData().add(new XYChart.Data<>(1, 21));
    newSeries.getData().add(new XYChart.Data<>(2, 36));
    newSeries.getData().add(new XYChart.Data<>(3, 78));
    newSeries.getData().add(new XYChart.Data<>(4, 64));
    newSeries.getData().add(new XYChart.Data<>(5, 60));

    lineChar.getData().add(newSeries);

  }
  private void handleButtonClick15(String stockName,LineChart<Number,Number> lineChar) {

    lineChar.setTitle(stockName + " chart");

    ObservableList<XYChart.Series<Number, Number>> seriesList = lineChar.getData();

    // Find Series and remove it
    /*for (XYChart.Series<Number, Number> series : seriesList) {
        seriesList.remove(series);
        break;  // Exit the loop after removing the first series
    }*/

    // Create a new series and add data points
    XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
    newSeries.getData().add(new XYChart.Data<>(0, 11));
    newSeries.getData().add(new XYChart.Data<>(1, 22));
    newSeries.getData().add(new XYChart.Data<>(2, 33));
    newSeries.getData().add(new XYChart.Data<>(3, 45));
    newSeries.getData().add(new XYChart.Data<>(4, 69));
    newSeries.getData().add(new XYChart.Data<>(5, 71));

    lineChar.getData().add(newSeries);

  }

      private TilePane createTilePane(Pane[] centerContents) {
          TilePane tilePane = new TilePane();
          tilePane.setPadding(new Insets(10));
          tilePane.setAlignment(Pos.BOTTOM_CENTER);
          tilePane.setStyle("-fx-background-color: green;");
          ToggleGroup toggleGroup = new ToggleGroup();
          String Imag[]=new String[NUM_STAGES];
          Imag[0]="hw.jpg";
        Imag[1]="user.png";
        Imag[2]="help.png";
        Imag[3]="hw.jpg";
          for (int i = 0; i < NUM_STAGES; i++) {
            ImageView image = new ImageView(new Image(getClass().getResourceAsStream(Imag[i])));
            image.setFitHeight(30);
            image.setFitWidth(30);
              ToggleButton button = new ToggleButton("",image);
              int finalI = i;
              button.setStyle("-fx-background-color: #0598ff;");
              button.setOnAction(event -> {
                  root.setLeft(null);
                  root.setCenter(null);
                  root.setCenter(centerContents[finalI]);
                  adjustTilePaneHeight(tilePane, button);
                  slidingPane.toFront();
                  root.setLeft(slidingPane);
              });
            tilePane.setHgap(10);

              button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                // If selected, color the background red
                if (newValue) {
                  button.setStyle(
                            "-fx-background-color: red;" + "-fx-text-fill: white");
                } else {
                  button.setStyle("-fx-background-color: #0598ff;");
                }
              });
              // Add the button to our ToggleGroup
              toggleGroup.getToggles().add(button);

          }
        // Add all our buttons to the scene
        for (Toggle buton :
                toggleGroup.getToggles()) {
            tilePane.getChildren().add((ToggleButton) buton);
        }

          // Set initial height for the first button
          adjustTilePaneHeight(tilePane, (ToggleButton) tilePane.getChildren().get(0));

          return tilePane;
      }

      private void adjustTilePaneHeight(TilePane tilePane, ToggleButton selectedButton) {
          // Adjust the height of the TilePane to highlight the selected button
          double height = selectedButton.getBoundsInParent().getHeight();
          tilePane.setPrefHeight(height);
      }
    private void toggleSlidingWindow() {
      TranslateTransition transition = new TranslateTransition(Duration.millis(200), slidingPane);
      transition.setByX(slidingPane.getTranslateX() > 0 ? -slidingPane.getWidth() : slidingPane.getWidth());
      transition.play();
  }
}
class Register implements Pages{
  private BorderPane root;
  private Pane slidingPane;
  String username;
  double currency;
  public Register(){
    root=new BorderPane();
    AnchorPane leftAnchorPane = new AnchorPane();
    leftAnchorPane.setPrefHeight(500.0);
    leftAnchorPane.setPrefWidth(354.0);

    RadialGradient radialGradient = new RadialGradient(
            0,      // focus angle
            0.1,    // focus distance
            0.5,    // centerX
            0.5,    // centerY
            0.5,    // radius
            true,   // proportional
            javafx.scene.paint.CycleMethod.NO_CYCLE, // cycle method
            new Stop(0, Color.web("#444444")),       // center color (green)
            new Stop(1, Color.web("#111111"))        // outer color (a darker green)
    );

    leftAnchorPane.setBackground(new Background(new BackgroundFill(radialGradient, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    // Add UI elements to the GridPane
    Label usernameLabel = new Label("Username:");
    TextField usernameField = new TextField();
    grid.add(usernameLabel, 0, 0);
    grid.add(usernameField, 1, 0);

    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();
    grid.add(passwordLabel, 0, 1);
    grid.add(passwordField, 1, 1);

    Label confirmPasswordLabel = new Label("Confirm Password:");
    PasswordField confirmPasswordField = new PasswordField();
    grid.add(confirmPasswordLabel, 0, 2);
    grid.add(confirmPasswordField, 1, 2);

    Label emailLabel = new Label("Email:");
    TextField emailField = new TextField();
    grid.add(emailLabel, 0, 3);
    grid.add(emailField, 1, 3);

    // Add more fields as needed...

    Button registerButton = new Button("Register");
    grid.add(registerButton, 1, 4);
    Button back = new Button("Return");
    grid.add(back, 1, 5);
    back.setOnAction(e -> {
        // Show the login page again
        Stage loginStage = new Stage();
        Main loginApp = new Main();
        loginApp.start(loginStage);
        Stage stageu = (Stage) root.getScene().getWindow();
        stageu.close();
    });
    // Set the action for the Register button
    registerButton.setOnAction(e ->
            handleRegistration(
                    usernameField.getText(),
                    passwordField.getText(),
                    confirmPasswordField.getText(),
                    emailField.getText()
                    // Add more fields as needed...
            )
    );
    root.setLeft(leftAnchorPane);
    root.setCenter(grid);
  }
  private void handleRegistration(String username, String password, String confirmPassword, String email) {
      if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
          showErrorAlert("Please fill in all fields.");
          return;
      }
    String filePath = "users.txt";
    Path path = Paths.get(filePath);
    try {
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            if(line==username){
              showErrorAlert("Username already exists.");
              return;
            }
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reading the file.");
        e.printStackTrace();
        return;
    }

      if (!password.equals(confirmPassword)) {
          showErrorAlert("Passwords do not match.");
          return;
      }
      File.Write("users.txt",username+"\n");
      File.Create(username+"jile.txt","");
      File.Create(username+"file.txt","");
      File.Create(username+".txt","");
      File.Create(username+"watch.txt","");
      File.Write(username+".txt",username+"\n"+password+"\n"+email+"\n"+"10000.0"+"\n");
      showConfAlert("User registered");
      // Print user details (replace this with your storage logic)
      // Add more fields as needed...
  }

  // Helper method to show an error alert
  private void showErrorAlert(String message) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
  }
  private void showConfAlert(String message) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
  }
  public BorderPane getRoot() {
      return root;
  }
}

public class Main extends Application {
    static int iskt=1;
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        AnchorPane groot = new AnchorPane();

        Scene scene = new Scene(root, 750, 600);
        if(iskt==1){
        Scene scenef = new Scene(groot, 750, 600);
        primaryStage.setScene(scenef);

          // Replace these with your actual logo and app name
          ImageView logoImageView = new ImageView(new Image("_557d84c0-4430-4435-bfc7-23e9314d7cc4.jpg"));
          logoImageView.setFitWidth(750);
          logoImageView.setFitHeight(750);
          logoImageView.setTranslateX(0);
          logoImageView.setTranslateY(0);
          Label appNameLabel = new Label("JoJaJi stocks");
          appNameLabel.setTranslateX(350);
          appNameLabel.setTranslateY(60);

          // Create an ImageView for the rotating image
          ImageView rotatingImageView = new ImageView(new Image("https://th.bing.com/th/id/R.5a956f6329634d0a0e0a6e74938f2681?rik=Cg6eDsM2%2b7D1Nw&riu=http%3a%2f%2fcdn.onlinewebfonts.com%2fsvg%2fimg_235526.png&ehk=vFgSGOZY8X5j9NlAkgIDmdgeTgW%2fWvyOEq%2bYgAjtBIE%3d&risl=&pid=ImgRaw&r=0"));
          rotatingImageView.setFitWidth(100);
          rotatingImageView.setFitHeight(100);
          rotatingImageView.setTranslateX(350);
          rotatingImageView.setTranslateY(350);

          // Create a RotateTransition for the rotating image
          RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), rotatingImageView);
          rotateTransition.setByAngle(360);
          rotateTransition.setCycleCount(Animation.INDEFINITE); // Infinite rotation

          // Create a FadeTransition for the logo, app name, and rotating image
          FadeTransition fadeTransition = new FadeTransition(Duration.seconds(10));
          fadeTransition.setFromValue(1.0);
          fadeTransition.setToValue(0.0);

          fadeTransition.setNode(logoImageView);
          fadeTransition.setOnFinished(event -> {
              // Add code to navigate to a new page here
              System.out.println("Transition complete! Navigate to a new page.");
          });

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(10));
        fadeTransition1.setFromValue(1.0);
        fadeTransition1.setToValue(0.0);

        fadeTransition1.setNode(appNameLabel);
        fadeTransition1.setOnFinished(event -> {
            // Add code to navigate to a new page here
            System.out.println("Transition complete! Navigate to a new page.");
        });

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(10));
        fadeTransition2.setFromValue(1.0);
        fadeTransition2.setToValue(0.0);

        fadeTransition2.setNode(rotatingImageView);
        fadeTransition2.setOnFinished(event -> {
            // Add code to navigate to a new page here
            System.out.println("Transition complete! Navigate to a new page.");
        });

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(6));
        fadeTransition3.setFromValue(1.0);
        fadeTransition3.setToValue(0.0);

        fadeTransition3.setNode(groot);
        fadeTransition3.setOnFinished(event -> {
            // Add code to navigate to a new page here
            System.out.println("Transition complete! Navigate to a new page.");
            primaryStage.setScene(scene);
        });

          // Parallelly play the fade transition and the rotate transition
          ParallelTransition parallelTransition = new ParallelTransition(fadeTransition,fadeTransition1,fadeTransition2,fadeTransition3,rotateTransition);
          parallelTransition.play();

          // Add the logo, app name, and rotating image to the groot
          groot.getChildren().addAll(logoImageView, appNameLabel, rotatingImageView);
          iskt=0;
        }
        else{
          primaryStage.setScene(scene);
        }

        AnchorPane leftAnchorPane = new AnchorPane();
        leftAnchorPane.setPrefHeight(500.0);
        leftAnchorPane.setPrefWidth(354.0);

        Stop[] stops = new Stop[] {
                new Stop(0.1, javafx.scene.paint.Color.BLACK),
                new Stop(0.5, javafx.scene.paint.Color.DEEPPINK),
                new Stop(1, javafx.scene.paint.Color.ORANGE)
        };

        // Create a linear gradient with the defined stops
        LinearGradient linearGradient = new LinearGradient(
                0,                      // startX
                0,                      // startY
                0,                      // endX
                1,                      // endY
                true,                   // proportional
                CycleMethod.NO_CYCLE,   // cycleMethod
                stops                   // stops
        );

        leftAnchorPane.setBackground(new Background(new BackgroundFill(linearGradient, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

        Text stockText = new Text("Stock Management");
        stockText.setFill(Color.web("#fcf9f9"));
        stockText.setLayoutX(79.0);
        stockText.setLayoutY(309.0);
        stockText.setFont(new Font(20.0));

        Button register = new Button("Register");
        register.setLayoutX(139.0);
        register.setLayoutY(350.0);
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // Open a new scene with NewPage
                Register tras = new Register();
                Stage newStga = new Stage();
                newStga.setScene(new Scene(tras.getRoot(), 750, 600));
                newStga.setTitle("Welcome ");
                newStga.show();
                primaryStage.close();
            }
        });
      // Create the X and Y axes
      CategoryAxis xAxis = new CategoryAxis();
      NumberAxis yAxis = new NumberAxis();

      // Create the BarChart
      BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
      barChart.setTitle("Bar Graph Example");

      // Create a series for the data
      XYChart.Series<String, Number> series = new XYChart.Series<>();
      // Add data to the series
      series.getData().add(new XYChart.Data<>("Category 1", 20));
      series.getData().add(new XYChart.Data<>("Category 2", 45));
      series.getData().add(new XYChart.Data<>("Category 3", 15));
      series.getData().add(new XYChart.Data<>("Category 4", 30));

      series.setName("Stocks App");

      // Add the series to the chart
      barChart.getData().add(series);

        barChart.setLayoutX(3.0);
        barChart.setLayoutY(14.0);
        barChart.setPrefHeight(232.0);
        barChart.setPrefWidth(334.0);

        leftAnchorPane.getChildren().addAll(stockText, barChart,register);

        AnchorPane centerAnchorPane = new AnchorPane();
        centerAnchorPane.setPrefHeight(500.0);
        centerAnchorPane.setPrefWidth(345.0);

        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(134.0);
        passwordField.setLayoutY(266.0);
        passwordField.setPromptText("password");
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");

        Button loginButton = new Button("Login");
        loginButton.setLayoutX(167.0);
        loginButton.setLayoutY(365.0);
        loginButton.setStyle("-fx-background-color: #0598ff;");
        loginButton.setTextFill(javafx.scene.paint.Color.WHITE);

        // Add event handler to the login button

        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password ?");
        forgotPasswordLink.setLayoutX(142.0);
        forgotPasswordLink.setLayoutY(443.0);

        Text userLoginText = new Text("User Login");
        userLoginText.setFill(Color.web("#0598ff"));
        userLoginText.setLayoutX(134.0);
        userLoginText.setLayoutY(141.0);
        userLoginText.setFont(new Font(21.0));

        TextField userNameTextField = new TextField();
        userNameTextField.setLayoutX(139.0);
        userNameTextField.setLayoutY(222.0);
        userNameTextField.setPromptText("user name");
        userNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
      loginButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            boolean poss=false;
              String username = userNameTextField.getText();
            String filePawth = "users.txt";
            Path pawth = Paths.get(filePawth);
            try {
                List<String> linwes = Files.readAllLines(pawth);
                for (String linwe : linwes) {
                    if(linwe.equals(username)){
                      poss=true;
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
                return;
            }
            if(poss==true){
              System.out.println("Hello, " + username);
              String password = passwordField.getText();
              if (isValidUser(username, password)) {
                  String fileParth = username+".txt";
                  double currentcy=0;
                  Path parth = Paths.get(fileParth);
                  try {
                      List<String> linres = Files.readAllLines(parth);
                      currentcy=Conv.StoD(linres.get(3));
                  } catch (IOException e) {
                      System.out.println("An error occurred while reading the file.");
                      e.printStackTrace();
                  }
                  Registereduser user=new Registereduser();
                  user.setUsername(username);
                  user.setPassword(password);
                  user.setBalance(currentcy);

                  Stocks stks=new Stocks();
                  Stocks stksrt=new Stocks();

                  StockPrice spk = new StockPrice();

                  Stocks addar = new Stocks();

                  String filePath = username+"jile.txt";
                  Path path = Paths.get(filePath);
                  try {
                      List<String> lines = Files.readAllLines(path);
                      for (String line : lines) {
                          String[] stoyk=line.split(",");
                          stks.addstock(stoyk[0],Conv.StoD(stoyk[1]));
                          stksrt.addstock(stoyk[0],Conv.StoD(stoyk[1]));
                      }
                  } catch (IOException e) {
                      System.out.println("An error occurred while reading the file.");
                      e.printStackTrace();
                  }

                String filePathru = username+"watch.txt";
                Path pathru = Paths.get(filePathru);
                try {
                    List<String> linesru = Files.readAllLines(pathru);
                    for (String lineru : linesru) {
                        String[] stoyk=lineru.split(",");
                        addar.addstock(stoyk[0],Conv.StoD(stoyk[1]));
                    }
                } catch (IOException e) {
                    Print("An error occurred while reading the file.");
                    e.printStackTrace();
                }

                  Portfolio pf=new Portfolio(2210564,user,stks,spk,stksrt,addar);
                  showAlert("Login successful!");
                  NewPage newPage = new NewPage(pf);
                  Stage newStage = new Stage();
                  newStage.setScene(new Scene(newPage.getRoot(), 750, 600));
                  newStage.setTitle("Welcome " + username);
                  newStage.show();
                  primaryStage.close();
              } 
              else{
                  showAlert("Invalid password. Please try again.");
              }
            }
            else{
              showAlert("Invalid username. Please try Again.");
            }
          }
      });
        centerAnchorPane.getChildren().addAll(passwordField, loginButton, forgotPasswordLink, userLoginText, userNameTextField);

        root.setLeft(leftAnchorPane);
        root.setCenter(centerAnchorPane);

        primaryStage.setTitle("JavaFX Equivalent");
        primaryStage.show();
    }
  private boolean isValidUser(String username, String password) {
    String fileParth = username+".txt";
    String porseword="";
    Path parth = Paths.get(fileParth);
    try {
        List<String> linres = Files.readAllLines(parth);
        porseword=linres.get(1);
    } catch (IOException e) {
        System.out.println("An error occurred while reading the file.");
        e.printStackTrace();
    }
      return 
        (porseword.equals(password));
  }

  private void showAlert(String message) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
  }
    public static void main(String[] args) {
        launch(args);
    }
}
